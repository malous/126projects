package cn.sjtu.netlab.userserver.controller;


import cn.sjtu.netlab.userserver.constants.HttpConstants;
import cn.sjtu.netlab.userserver.model.LabDept;
import cn.sjtu.netlab.userserver.model.LabDeptTree;
import cn.sjtu.netlab.userserver.service.LabDeptService;
import cn.sjtu.netlab.userserver.vo.BaseResponse;
import cn.sjtu.netlab.userserver.vo.ObjectResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author malous
 * @since 2019-10-04
 */
@RestController
@RequestMapping("/dept")
public class LabDeptController {
    @Autowired
    private LabDeptService deptService;
    
    @GetMapping("/find")
    public BaseResponse findDept (HttpServletRequest request) {
        HttpSession session = request.getSession();
        Cookie[] cookies = request.getCookies();
        List<LabDeptTree> result = deptService.findDept();
        return new ObjectResponse<>(result);
    }

    @PostMapping("/insert")
    public BaseResponse addDept (@RequestBody LabDept dept) {
        try {
            deptService.save(dept);
        } catch (DuplicateKeyException e) {
            return new BaseResponse(HttpConstants.ERR_ADD, "同级别部门名称重复");
        }
        return new BaseResponse();
    }

    @PutMapping("/update")
    public BaseResponse updateDept (@RequestBody LabDept dept) {
        boolean result = deptService.updateById(dept);
        if (result) {
            return new BaseResponse();
        } else {
            return new BaseResponse(HttpConstants.ERR_UPDATE, "更新角色信息失败，请确保角色名未重复");
        }
    }

    @DeleteMapping("/delete")
    public BaseResponse deleteDept (@RequestParam("id") BigInteger id) {
        boolean result = deptService.removeById(id);
        if (result) {
            return new BaseResponse();
        } else {
            return new BaseResponse(HttpConstants.ERR_UPDATE, "删除角色信息失败，该角色可能不存在");
        }
    }
}
