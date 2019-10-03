package cn.sjtu.netlab.authorizationclient;

import org.apache.commons.codec.binary.Base64;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//@EnableOAuth2Sso
@SpringBootApplication
public class AuthorizationClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthorizationClientApplication.class, args);
    }

//    public static void main(String[] args) {
//        String ss = "net_lab:secret";
//
//        String s = new String(Base64.encodeBase64(ss.getBytes()));
//        System.out.println(s);
//    }
}
