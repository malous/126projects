package cn.sjtu.netlab.userserver.service.imply;

import cn.sjtu.netlab.userserver.mapper.LabUserMapper;
import cn.sjtu.netlab.userserver.model.LabUser;
import cn.sjtu.netlab.userserver.service.LabUserService;
import cn.sjtu.netlab.userserver.vo.PageSelect;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
@Slf4j
public class LabUserServiceImply implements LabUserService {
    @Autowired
    private LabUserMapper userMapper;

    @Override
    public LabUser findByUserName(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public PageSelect<LabUser> findUser (String name, int pageNum, int pageSize) {
        QueryWrapper<LabUser> wrapper = new QueryWrapper<>();
        if (name != null) {
            wrapper.like("username", name).or().like("name", name);
        }
        IPage<LabUser> page = userMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return new PageSelect<>(page.getTotal(), page.getRecords());
    }

    @Override
    public int insert(LabUser user) {
        return userMapper.insert(user);
    }

    @Override
    public int updateUser(LabUser user) {
        return userMapper.updateById(user);
    }

    @Override
    public int deleteUser(BigInteger id) {
        return userMapper.deleteById(id);
    }
}
