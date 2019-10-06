package cn.sjtu.netlab.userserver.controller;

import cn.sjtu.netlab.userserver.constants.HttpConstants;
import cn.sjtu.netlab.userserver.model.LabUser;
import cn.sjtu.netlab.userserver.service.LabUserService;
import cn.sjtu.netlab.userserver.vo.LoginBean;
import cn.sjtu.netlab.userserver.vo.BaseResponse;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController
public class LabLoginController {
    @Autowired
    private Producer producer;
    @Autowired
    private LabUserService labUserService;

    /**
     * 生成验证码图片
     * @param request
     * @param response
     * @throws IOException
     */
    @GetMapping("captcha")
    public void getCaptcha (HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setHeader("Content-Type", "image/jpeg");

        String text = producer.createText();
        BufferedImage image = producer.createImage(text);
        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, text);
        System.out.println(text);

        ServletOutputStream outputStream = response.getOutputStream();
        ImageIO.write(image, "jpg", outputStream);
        outputStream.close();
    }

    @PostMapping("/loginS")
    public BaseResponse login (@RequestBody LoginBean loginBean, HttpServletRequest request) {
        String username = loginBean.getUsername();
        String password = loginBean.getPassword();
        String loginCaptcha = loginBean.getCaptcha();
        Object captcha = request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (captcha == null) {
            return BaseResponse.error(HttpConstants.STATUS_ERROR_411, HttpConstants.STATUS_ERROR_411_MSG);
        }
        if (!captcha.equals(loginCaptcha)) {
            return BaseResponse.error(HttpConstants.STATUS_ERROR_412, HttpConstants.STATUS_ERROR_412_MSG);
        }

        LabUser user = labUserService.findByUsername(username);

        if (user == null || user.getDelFlag()) {
            return BaseResponse.error(HttpConstants.STATUS_ERROR_413, HttpConstants.STATUS_ERROR_413_MSG);
        }
        if (!user.getPassword().equals(password)) {
            return BaseResponse.error(HttpConstants.STATUS_ERROR_414, HttpConstants.STATUS_ERROR_414_MSG);
        }

        if (user.getLockFlag()) {
            return BaseResponse.error(HttpConstants.STATUS_ERROR_415, HttpConstants.STATUS_ERROR_415_MSG);
        }

//        token.extractAccessToken("access_token", )
        return BaseResponse.ok();
    }




}
