package cn.sjtu.netlab.userserver.vo;

import lombok.Data;

@Data
public class LoginBean {
    private String username;
    private String password;
    private String captcha;
}
