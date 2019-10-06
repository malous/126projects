package cn.sjtu.netlab.userserver.service.impl;

import cn.sjtu.netlab.userserver.model.LabRole;
import cn.sjtu.netlab.userserver.mapper.LabRoleMapper;
import cn.sjtu.netlab.userserver.service.LabRoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author malous
 * @since 2019-10-04
 */
@Service
public class LabRoleServiceImpl extends ServiceImpl<LabRoleMapper, LabRole> implements LabRoleService {

    @Override
    public IPage<LabRole> findRole(String name, long pageNum, long pageSize) {
        QueryWrapper<LabRole> wrapper = new QueryWrapper<>();
        wrapper.like("name", name);
        return this.getBaseMapper().selectPage(new Page<>(pageNum, pageSize), wrapper);
    }
}
