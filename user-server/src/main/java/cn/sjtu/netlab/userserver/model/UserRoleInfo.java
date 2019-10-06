package cn.sjtu.netlab.userserver.model;

import lombok.Data;

import java.math.BigInteger;

@Data
public class UserRoleInfo {
    private BigInteger id;
    private String username;
    private String name;
    private String note;
}
