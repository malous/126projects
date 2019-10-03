package cn.sjtu.netlab.userserver.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * Table: lab_role
 */
@TableName("lab_role")
@Data
public class LabRole {
    @TableId(value = "id", type = IdType.AUTO)
    private BigInteger id;
    private String name;
    private Integer level;
    private String note;
    private Timestamp createTime;
    private Timestamp updateTime;
    private Boolean delFlag;
}
