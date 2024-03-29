package cn.sjtu.netlab.userserver.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;

import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author malous
 * @since 2019-10-04
 */
@Data
public class LabRole implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private BigInteger id;

    /**
     * 角色名称
     */
    @TableField("name")
    private String name;

    /**
     * 备注
     */
    @TableField("note")
    private String note;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 最新一次的更新时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;

    /**
     * 0-正常，1-删除
     */
    @TableField("del_flag")
    @TableLogic
    private Boolean delFlag;
}
