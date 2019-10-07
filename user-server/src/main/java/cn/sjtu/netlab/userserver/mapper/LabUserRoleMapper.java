package cn.sjtu.netlab.userserver.mapper;

import cn.sjtu.netlab.userserver.model.LabUserRole;
import cn.sjtu.netlab.userserver.model.UserRoleInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
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
public interface LabUserRoleMapper extends BaseMapper<LabUserRole> {

    @Select({
            "select ur.id, u.username, u.name, u.note",
            "from lab_user_role ur, lab_user u",
            "where ur.role_id=#{roleId, jdbcType=BIGINT}",
            "and ur.user_id=u.id"
    })
    List<UserRoleInfo> findUser (@Param("roleId") BigInteger roleId);

    void ignoreInsert(List<LabUserRole> userRoleList);
}
