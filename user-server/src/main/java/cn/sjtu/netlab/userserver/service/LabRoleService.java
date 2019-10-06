package cn.sjtu.netlab.userserver.service;

import cn.sjtu.netlab.userserver.model.LabRole;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author malous
 * @since 2019-10-04
 */
public interface LabRoleService extends IService<LabRole> {
    IPage<LabRole> findRole (String name, long pageNum, long pageSize);
}
