package cn.sjtu.netlab.userserver.mapper;

import cn.sjtu.netlab.userserver.model.LabUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

public interface LabUserMapper extends BaseMapper<LabUser> {
    @Select({
            "select",
            "id, username, `password`, `name`, email, phone,  note",
            "create_time, update_time, lock_flag, del_flag",
            "from lab_user",
            "where username=#{username, jdbcType=VARCHAR}"
    })
    LabUser findByUsername(@Param("username") String username);
}
