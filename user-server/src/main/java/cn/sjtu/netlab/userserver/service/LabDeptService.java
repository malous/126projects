package cn.sjtu.netlab.userserver.service;

import cn.sjtu.netlab.userserver.model.LabDept;
import cn.sjtu.netlab.userserver.model.LabDeptTree;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author malous
 * @since 2019-10-04
 */
public interface LabDeptService extends IService<LabDept> {

    List<LabDeptTree> findDept();

}
