package com.gaming.baby.controller.client;

import com.gaming.baby.service.CustomUserDetails;
import com.gaming.baby.entity.Users;
import com.gaming.baby.jwt.TokenProvider;
import com.gaming.baby.payload.request.ResendRequest;
import com.gaming.baby.payload.request.SignUpRequest;
import com.gaming.baby.payload.request.SignInRequest;
import com.gaming.baby.payload.request.VerifyRequest;
import com.gaming.baby.payload.response.SignInResponse;
import com.gaming.baby.payload.response.SingleResponse;
import com.gaming.baby.repository.UserRepository;
import com.gaming.baby.service.Email;
import com.gaming.baby.service.SMS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.exceptions.UserDeniedAuthorizationException;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import javax.validation.Valid;
import java.util.Base64;
import java.util.List;
import java.util.Random;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/user")
public class UsersController {

    public final int PENDING = 0;
    public final int ACTIVE = 1;
    public final int TERMINATED = 2;

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private TokenProvider tokenProvider;

    private Environment env;

    @Value("${gaming.baby.apiDomain}")
    private String apiDomain;

    @Value("${gaming.baby.clientDomain}")
    private String clientDomain;

    private Email email;

    private SMS sms;

    @Autowired
    public UsersController(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, TokenProvider tokenProvider, Environment env) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.email = new Email(env);
        this.sms = new SMS(env);
    }

    /**
     * SignUp user
     * @param signUpRequest
     * Add user request object
     * @return Users
     */
    @RequestMapping(value="/signUp", method = RequestMethod.POST)
    @ResponseBody
    public SingleResponse signUp(@Valid @RequestBody SignUpRequest signUpRequest){

        SingleResponse singleResponse = new SingleResponse();
        Users existUser = userRepository.findByUsername(signUpRequest.getUsername());

        if(existUser != null){
            throw new EntityExistsException("User existed");
        }

        Users user = new Users();
        user.setEmail(signUpRequest.getEmail());
        user.setPhone(signUpRequest.getPhone());
        user.setAddress(signUpRequest.getAddress());
        user.setAvatar(signUpRequest.getAvatar());
        user.setDisplay_name(signUpRequest.getDisplay_name());
        user.setUsername(signUpRequest.getUsername());

        String hashedPassword = passwordEncoder.encode(signUpRequest.getPassword());

        user.setPassword(hashedPassword);

        userRepository.save(user);

        if(signUpRequest.getEmail() != null && !signUpRequest.getEmail().equals("")){
            String verifyCode = "http://"+clientDomain + "/login.html?username="+user.getUsername()+"&code=" + user.getDecodedToken();

            email.send(signUpRequest.getEmail(), "註冊確認信", "感謝您註冊Gaming Baby會員，請點擊以下連結完成註冊。<br>" +
                    "<a href=\""+ verifyCode +"\">"+verifyCode+"</a>");
        }

        if(signUpRequest.getPhone() != null && !signUpRequest.getPhone().equals("")){

            sms.send(signUpRequest.getPhone(), "以下是Gaming Baby會員註冊確認碼 " + user.getDecodedToken());
        }

        singleResponse.setStatus(true);
        singleResponse.setMessage("Success");
        singleResponse.setModel(user);

        return singleResponse;
    }

    /**
     * Sign in
     * @param signInRequest
     * Login request Object
     * @return LoginResponse
     */
    @RequestMapping(value="/signIn", method=RequestMethod.POST)
    @ResponseBody
    public SignInResponse signIn(@Valid @RequestBody SignInRequest signInRequest){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        signInRequest.getUsername(),
                        signInRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());

        Users user = userRepository.findByUsername(signInRequest.getUsername());

        return new SignInResponse(jwt, user.getUID());
    }


    @RequestMapping(value="/verify", method=RequestMethod.POST)
    @ResponseBody
    public SingleResponse verifySMSCode(@RequestBody VerifyRequest verifyRequest){
        Users user = userRepository.findByUsername(verifyRequest.getUsername());
        SingleResponse singleResponse = new SingleResponse();

        if(user != null){

            singleResponse.setModel(user);

            if(user.getDecodedToken().equals(verifyRequest.getCode())){
                user.setStatus(ACTIVE);

                userRepository.save(user);

                singleResponse.setStatus(true);
                singleResponse.setMessage("您已成功驗證");
            }
            else{
                singleResponse.setStatus(false);
                singleResponse.setMessage("Verify Failed");
            }
        }
        else{
            singleResponse.setStatus(false);
            singleResponse.setMessage("User does not exists");
        }


        return singleResponse;
    }

    @RequestMapping(value="/resend", method=RequestMethod.POST)
    @ResponseBody
    public SingleResponse resendSMS(@RequestBody ResendRequest resendRequest){
        SingleResponse singleResponse = new SingleResponse();

        Users user = userRepository.findByUsername(resendRequest.getUsername());

        if(user != null){

            singleResponse.setModel(user);

            //Generate token code and send to mobile phone
            Random rand = new Random();
            user.setToken(Base64.getEncoder().encodeToString(String.format("%04d", rand.nextInt(10000)).getBytes()));

            userRepository.save(user);

            sms.send(user.getPhone(), "以下是Gaming Baby會員註冊確認碼 " + user.getDecodedToken());

            singleResponse.setStatus(true);
            singleResponse.setMessage(user.getDecodedToken());
        }
        else{
            singleResponse.setStatus(false);
        }

        return singleResponse;
    }


    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    @ResponseBody
    public Users getUserDetail(@RequestParam String uid){
        Users user = userRepository.findByUID(uid);

        if(user == null){
            throw new UsernameNotFoundException("Username not found");
        }
        if(user.getStatus() != ACTIVE){
            throw new UserDeniedAuthorizationException("User not active");
        }

        return user;
    }

}
