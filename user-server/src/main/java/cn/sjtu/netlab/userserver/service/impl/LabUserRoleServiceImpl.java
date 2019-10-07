package cn.sjtu.netlab.userserver.service.impl;

import cn.sjtu.netlab.userserver.model.LabUserRole;
import cn.sjtu.netlab.userserver.mapper.LabUserRoleMapper;
import cn.sjtu.netlab.userserver.model.UserRoleInfo;
import cn.sjtu.netlab.userserver.service.LabUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author malous
 * @since 2019-10-04
 */
@Service
public class LabUserRoleServiceImpl extends ServiceImpl<LabUserRoleMapper, LabUserRole> implements LabUserRoleService {
    @Override
    public List<UserRoleInfo> findUser(BigInteger roleId) {
        return this.getBaseMapper().findUser(roleId);
    }

    @Override
    public void ignoreInsert(List<LabUserRole> userRoleList) {
        this.getBaseMapper().ignoreInsert(userRoleList);
    }
}
