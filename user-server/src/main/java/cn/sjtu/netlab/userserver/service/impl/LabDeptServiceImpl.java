package cn.sjtu.netlab.userserver.service.impl;

import cn.sjtu.netlab.userserver.model.LabDept;
import cn.sjtu.netlab.userserver.mapper.LabDeptMapper;
import cn.sjtu.netlab.userserver.model.LabDeptTree;
import cn.sjtu.netlab.userserver.service.LabDeptService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author malous
 * @since 2019-10-04
 */
@Service
public class LabDeptServiceImpl extends ServiceImpl<LabDeptMapper, LabDept> implements LabDeptService {

    @Override
    public List<LabDeptTree> findDept() {
        LabDeptMapper deptMapper = this.getBaseMapper();
        List<LabDeptTree> deptTreeList = new ArrayList<>();
        Map<BigInteger, LabDeptTree> idDeptMap = new HashMap<>();
        List<LabDept> deptList = deptMapper.selectList(null);
        for (LabDept dept : deptList) {
            BigInteger id = dept.getId();
            BigInteger parentId = dept.getParentId();
            LabDeptTree node = generateDeptTree(dept);
            idDeptMap.put(id, node);
            if (parentId.equals(BigInteger.ZERO)) {
                deptTreeList.add(node);
            } else {
                LabDeptTree parent = idDeptMap.get(parentId);
                parent.addChild(node);
            }
        }
        return deptTreeList;
    }

    private LabDeptTree generateDeptTree (LabDept dept) {
        return new LabDeptTree(dept.getId(), dept.getName(), null);
    }
}
