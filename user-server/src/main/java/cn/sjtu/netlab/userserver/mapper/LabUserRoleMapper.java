package cn.sjtu.netlab.userserver.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.math.BigInteger;
import java.util.List;

public interface LabUserRoleMapper {
    @Select({
            "select",
            "role_id from lab_user_role",
            "where user_id=#{userId, jdbcType=BIGINT}"
    })
    List<Long> getRoleIdByUserId (@Param("userId") BigInteger userId);

    @Select({
            "select",
            "user_id from lab_user_role",
            "where role_id=#{roleId, jdbcType=BIGINT}"
    })
    List<Long> getUserIdByRoleId (@Param("roleId") BigInteger roleId);
}
