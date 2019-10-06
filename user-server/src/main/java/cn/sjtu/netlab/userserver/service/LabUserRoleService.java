package cn.sjtu.netlab.userserver.service;

import cn.sjtu.netlab.userserver.model.LabUserRole;
import cn.sjtu.netlab.userserver.model.UserRoleInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigInteger;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author malous
 * @since 2019-10-04
 */
public interface LabUserRoleService extends IService<LabUserRole> {
    List<UserRoleInfo> findUser(BigInteger roleId);

}
