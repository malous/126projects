package cn.sjtu.netlab.userserver.mapper;

import cn.sjtu.netlab.userserver.model.LabRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.math.BigInteger;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author malous
 * @since 2019-10-04
 */
public interface LabRoleMapper extends BaseMapper<LabRole> {
    /**
     * 根据传入的用户id获取其对应角色
     * @param userId
     * @return LabRole组成的List
     */
    @Select({
            "select",
            "r.name from lab_user u, lab_role r, lab_user_role ur",
            "where u.id=#{userId, jdbcType=BIGINT}",
            "and u.id=ur.user_id and r.id=ur.role_id"
    })
    List<String> getRoleByUserId (@Param("userId") BigInteger userId);
}
