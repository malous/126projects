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
public class LabDept implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private BigInteger id;

    /**
     * 机构名称
     */
    @TableField("name")
    private String name;

    /**
     * 父机构id
     */
    @TableField("parent_id")
    private BigInteger parentId;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 最新一次更新时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;

    /**
     * 是否删除，1为已删除，0为未删除
     */
    @TableField("del_flag")
    @TableLogic
    private Boolean delFlag;
}
