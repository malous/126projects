package cn.sjtu.netlab.userserver.service.impl;

import cn.sjtu.netlab.userserver.mapper.LabUserRoleMapper;
import cn.sjtu.netlab.userserver.model.LabUser;
import cn.sjtu.netlab.userserver.mapper.LabUserMapper;
import cn.sjtu.netlab.userserver.model.LabUserRole;
import cn.sjtu.netlab.userserver.service.LabUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

/**
 * <p>
 *  用户管理服务接口实现类
 * </p>
 *
 * @author malous
 * @since 2019-10-04
 */
@Service
public class LabUserServiceImpl extends ServiceImpl<LabUserMapper, LabUser> implements LabUserService {
    @Autowired
    private LabUserRoleMapper userRoleMapper;
    @Override
    public LabUser findByUsername(String username) {
        QueryWrapper<LabUser> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        return this.getBaseMapper().selectOne(wrapper);
    }

    @Override
    public IPage<LabUser> findUser(String name, int pageNum, int pageSize) {
        QueryWrapper<LabUser> wrapper = null;
        if (name != null) {
            wrapper = new QueryWrapper<>();
            wrapper.like("name", name).or().like("username", name);
        }
        return this.getBaseMapper().selectPage(new Page<>(pageNum, pageSize), wrapper);
    }
}
