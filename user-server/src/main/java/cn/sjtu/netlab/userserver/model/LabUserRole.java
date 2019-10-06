package cn.sjtu.netlab.userserver.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.math.BigInteger;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
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
public class LabUserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private BigInteger id;

    /**
     * 用户id
     */
    @TableField("user_id")
    private BigInteger userId;

    /**
     * 角色id
     */
    @TableField("role_id")
    private BigInteger roleId;

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


}
