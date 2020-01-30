package com.gaming.baby;

import com.gaming.baby.entity.*;
import com.gaming.baby.repository.*;
import com.gaming.baby.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

@SpringBootApplication
public class BabyApplication {

    @Autowired
    DepositRepository depositRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    NewsRepository newsRepository;

    @Autowired
    PricePlanRepository pricePlanRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    VideoRepository videoRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    VideoCategoryRepository videoCategoryRepository;

    @Autowired
    VideoTagRepository videoTagRepository;

    @Autowired
    ProductCategoryRepository productCategoryRepository;

    @Autowired
    ProductTagRepository productTagRepository;

    @Autowired
    private ProductImageRepository productImageRepository;


    public static void main(String[] args) {
        SpringApplication.run(BabyApplication.class, args);
    }

    @Bean
    public CommandLineRunner setupDefaultUser(UserService service) {
        return args -> {
            Users user = new Users();

            user.setEmail("test@test.com");
            user.setAddress("");
            user.setAvatar("");
            user.setDisplay_name("ml1");
            user.setUsername("ml1");

            String hashedPassword = passwordEncoder.encode("123456");

            user.setPassword(hashedPassword);

            user.setRole(new Role("USER"));


            user.setStatus(1);

            userRepository.save(user);


            Deposit deposit = new Deposit(user,3000, 150, "Charge");


            depositRepository.save(deposit);



            Users user2 = new Users();

            user2.setEmail("admin@live.com");
            user2.setAddress("");
            user2.setAvatar("");
            user2.setDisplay_name("admin");
            user2.setUsername("admin");
            user2.setStatus(1);

            String hashedPassword2 = passwordEncoder.encode("123456");

            user2.setPassword(hashedPassword2);

            user2.setRole(new Role("ADMIN"));

            userRepository.save(user2);



            /*newsRepository.save(new News("news 1", "test content"));
            newsRepository.save(new News("news 2", "test content"));
            newsRepository.save(new News("news 3", "test content"));
            newsRepository.save(new News("news 4", "test content"));
            newsRepository.save(new News("news 5", "test content"));
            newsRepository.save(new News("news 6", "test content"));
            newsRepository.save(new News("news 7", "test content"));
            newsRepository.save(new News("news 8", "test content"));
            newsRepository.save(new News("news 9", "test content"));
            newsRepository.save(new News("news 10", "test content"));
            newsRepository.save(new News("news 11", "test content"));*/

            pricePlanRepository.save(new PricePlan("Plan 1", 0, 3000, 149, "monthly-value-top-img.png"));
            pricePlanRepository.save(new PricePlan("Plan 2", 1, 3000, 100, "img/gold-icon-2.png"));
            pricePlanRepository.save(new PricePlan("Plan 3", 1, 10000, 300, "img/money2.png"));
            pricePlanRepository.save(new PricePlan("Plan 4", 1, 25000, 500, "img/money3.png"));
            pricePlanRepository.save(new PricePlan("Plan 5", 1, 60000, 1000, "img/money4.png"));
            pricePlanRepository.save(new PricePlan("Plan 6", 1, 150000, 2000, "img/money5.png"));


            VideoCategory cat1 = new VideoCategory("長片");
            VideoCategory cat2 = new VideoCategory("短片");

            videoCategoryRepository.save(cat1);
            videoCategoryRepository.save(cat2);



            VideoTag tag1 = new VideoTag("Video tag 1");
            VideoTag tag2 = new VideoTag("Video tag 2");


            ProductCategory pcat1 = new ProductCategory("情趣衣服");
            ProductCategory pcat2 = new ProductCategory("按摩棒");

            ProductTag ptag1 = new ProductTag("Product tag 1");
            ProductTag ptag2 = new ProductTag("Product tag 2");

            productCategoryRepository.save(pcat1);
            productCategoryRepository.save(pcat2);


            Set<VideoTag> setTag1 = new HashSet<>();
            setTag1.add(tag1);

            Set<VideoTag> setTag2 = new HashSet<>();
            setTag2.add(tag2);

            Set<ProductTag> setPTag1 = new HashSet<>();
            setPTag1.add(ptag1);

            Set<ProductTag> setPTag2 = new HashSet<>();
            setPTag1.add(ptag2);

            videoRepository.save(new Video("", "Video 1", 100, "vedioimg.jpg", "eyebaby01.png", "", cat1, setTag1));
            videoRepository.save(new Video("", "Video 2", 300, "vedioimg.jpg", "eyebaby01.png", "", cat2, setTag2));

            Product p1 = new Product("Product 1", 1000, "product-img.png", "TAKE IT EASY！輕鬆拿起！ 方便使用的超薄型TENGA產品，輕薄包裝可以放進口袋，臨時起意也能隨拿隨用！ 軟質素材輕薄極限，不分尺寸，為您溫柔包裹。 雙手做不到的刺激，您想要的就在這裡。本品附有PLAY GEL潤滑液，不黏手、好清理！", pcat1, setPTag1);
            Product p2 = new Product("Product 2", 1000, "product-img.png", "Lorem ip sum", pcat2, setPTag2);

            productRepository.save(p1);
            productRepository.save(p2);

            productImageRepository.save(new ProductImage(p1, "one.png"));
            productImageRepository.save(new ProductImage(p1, "one2.png"));
            productImageRepository.save(new ProductImage(p1, "one3.png"));
            productImageRepository.save(new ProductImage(p2, "one4.png"));
            productImageRepository.save(new ProductImage(p2, "one5.png"));
            productImageRepository.save(new ProductImage(p2, "one6.png"));
            productImageRepository.save(new ProductImage(p2, "one7.png"));
            productImageRepository.save(new ProductImage(p2, "one8.png"));
            productImageRepository.save(new ProductImage(p2, "one9.png"));
        };
    }
}

