package cn.sjtu.netlab.userserver.config;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class KaptchaConfig {
    @Bean
    public DefaultKaptcha generateKaptcha () {
        Properties properties = new Properties();
        properties.put(Constants.KAPTCHA_BORDER, "yes");
        properties.put(Constants.KAPTCHA_BORDER_COLOR, "white");
        properties.put(Constants.KAPTCHA_TEXTPRODUCER_CHAR_LENGTH, "4");
        properties.put(Constants.KAPTCHA_TEXTPRODUCER_FONT_COLOR, "red");
        properties.put(Constants.KAPTCHA_TEXTPRODUCER_CHAR_SPACE, "5");
        properties.put(Constants.KAPTCHA_OBSCURIFICATOR_IMPL, "com.google.code.kaptcha.impl.ShadowGimpy");
        Config config = new Config(properties);
        DefaultKaptcha kaptcha = new DefaultKaptcha();
        kaptcha.setConfig(config);
        return kaptcha;
    }
}
