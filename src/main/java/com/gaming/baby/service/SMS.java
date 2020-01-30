package com.gaming.baby.service;

import org.springframework.core.env.Environment;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.Random;

public class SMS {

    private Environment env;


    public SMS(Environment env) {
        this.env = env;
    }

    public String send(String MobileNo, String SMSMessage) {
        //TODO write your implementation code here:

        String memberID = env.getProperty("com.gaming.baby.service.sms.memberId");

        String password = env.getProperty("com.gaming.baby.service.sms.password");

        //MemberID = "ml6688";
        //password = "uiojkl7" ;
        //MobileNo = "+85163691823344";
        //SMSMessage = 1234567890

        String CharSet = "U" ;
        String GlobalSms = "Y" ;
        String SourceProdID = "YOYO8SMS";
        String SourceMsgID = new Random().toString();

        String status_code = "" ;

        try {
            String pw = memberID+ ":"+ password + ":"+ SourceProdID+ ":" + SourceMsgID ;
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(pw.getBytes(),0,pw.length());
            BigInteger pw1 = new BigInteger(1, md.digest());
            // Convert message digest into hex value
            StringBuilder hashtext = new StringBuilder(pw1.toString(16));
            while (hashtext.length() < 32) {
                hashtext.insert(0, "0");
            }
            String md5_pw = hashtext.toString();

            //System.out.println(pw + ":" + md5_pw);

            String requestUrl  = "http://www.yoyo8.com.tw/SMSBridge.php" +
                    "?MemberID=" + URLEncoder.encode(memberID, "UTF-8") +
                    "&Password=" + URLEncoder.encode(md5_pw, "UTF-8") +
                    "&MobileNo=" + URLEncoder.encode(MobileNo, "UTF-8") +
                    "&CharSet=" + URLEncoder.encode(CharSet, "UTF-8") +
                    "&GlobalSms=" + URLEncoder.encode(GlobalSms, "UTF-8") +
                    "&SMSMessage=" + URLEncoder.encode(SMSMessage, "UTF-8") +
                    "&SourceProdID=" + URLEncoder.encode(SourceProdID, "UTF-8") +
                    "&SourceMsgID=" + URLEncoder.encode(SourceMsgID, "UTF-8");



            //System.out.println(requestUrl);

            URL url = new URL(requestUrl);
            HttpURLConnection uc = (HttpURLConnection)url.openConnection();

            //System.out.println(uc.getResponseMessage());
            uc.setRequestMethod("GET");

            int responseCode = uc.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("IResponse Code : " + responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        uc.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();



                // print result
                //System.out.println(response.toString());
                status_code = response.toString();
                String[] stats = status_code.split("=");
                String status = stats[1];
                if( status.equalsIgnoreCase("0") ){

                    return "success";//return ("Sending successful.");

                }
                else if( status.equalsIgnoreCase("1")){

                    return("The format of the received mobile phone number is incorrect.");

                }
                else if( status.equalsIgnoreCase("3")){

                    return("SMS content is blank.");

                }
                else if( status.equalsIgnoreCase("5")){

                    return ("The manufacturer login account or the encrypted login password is wrong.");

                }
                else if( status.equalsIgnoreCase("6")){

                    return ("No remaining SMS messages.");

                }
                else if( status.equalsIgnoreCase("7")){

                    return("Send WapPush but WapLink parameter has no content or no such parameter.");

                }
                else if( status.equalsIgnoreCase("9")){

                    return("No specified encoding.");

                }
                else if( status.equalsIgnoreCase("10")){

                    return("No SourceProdID or SourceMsgID.");

                }
                else if(status.equalsIgnoreCase("11")){

                    return("IP address is wrong.");

                }
                else if( status.equalsIgnoreCase("12")){

                    return("The current time is not sendable.");

                }
                else if( status.equalsIgnoreCase("13")){

                    return("Receiving SMS mobile phone number is the blacklist you set in the background.");

                }
                else if( status.equalsIgnoreCase("14")){

                    return("Receiving SMS mobile phone number is the blacklist set by yoyo8 system.");

                }
                else if( status.equalsIgnoreCase("15")){

                    return("Receiving SMS mobile phone number is the blacklist set by dealer.");

                }
                else if( status.equalsIgnoreCase("16")){

                    return("SMS valid time seconds are wrong.(Acceptable value:7200~86400)");

                }
                else if( status.equalsIgnoreCase("17")){

                    return("SMS is too long.");

                }
                else if ( status.equalsIgnoreCase("18")){

                    return("Prohibition on sending international newsletter.");

                }



            } else {
                return("GET request not worked");
            }

            uc.disconnect();

        } catch(Exception ex) {
            return(ex.getMessage());

        }

        return "success";
    }

}
