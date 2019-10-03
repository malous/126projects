package cn.sjtu.netlab.userserver.model;

import java.math.BigInteger;
import java.sql.Timestamp;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Table: lab_user
 */
@TableName("lab_user")
@Data
public class LabUser {
    @TableId(value = "id", type = IdType.AUTO)
    private BigInteger id;
    private String username;
    private String password;
    private String name;
    private String email;
    private String phone;
    private String note;
    private Timestamp createTime;
    private Timestamp updateTime;
    private Boolean lockFlag;
    private Boolean delFlag;
}