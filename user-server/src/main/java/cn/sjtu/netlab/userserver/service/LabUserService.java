package cn.sjtu.netlab.userserver.service;

import cn.sjtu.netlab.userserver.model.LabUser;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigInteger;

/**
 * <p>
 *  用户管理服务接口
 * </p>
 *
 * @author malous
 * @since 2019-10-04
 */
public interface LabUserService extends IService<LabUser> {
    LabUser findByUsername(String username);

    IPage<LabUser> findUser (String name, int pageNum, int pageSize);
}
