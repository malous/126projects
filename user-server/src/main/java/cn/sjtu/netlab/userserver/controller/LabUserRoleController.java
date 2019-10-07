package cn.sjtu.netlab.userserver.controller;


import cn.sjtu.netlab.userserver.constants.HttpConstants;
import cn.sjtu.netlab.userserver.model.LabUserRole;
import cn.sjtu.netlab.userserver.model.UserRoleInfo;
import cn.sjtu.netlab.userserver.service.LabUserRoleService;
import cn.sjtu.netlab.userserver.vo.BaseResponse;
import cn.sjtu.netlab.userserver.vo.ListResponse;
import cn.sjtu.netlab.userserver.vo.ObjectResponse;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

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
@CrossOrigin
@RestController
@RequestMapping("/assign")
public class LabUserRoleController {
    @Autowired
    private LabUserRoleService userRoleService;

    @GetMapping("/find")
    public BaseResponse findUserRole (@RequestParam(value = "roleId") BigInteger roleId) {
        List<UserRoleInfo> result = userRoleService.findUser(roleId);
        return new ObjectResponse<>(result);
    }

    @PostMapping("/insert")
    public BaseResponse addUserRole (@RequestBody List<LabUserRole> userRoleList) {
        try {
            userRoleService.ignoreInsert(userRoleList);
        } catch (DuplicateKeyException e) {
            e.printStackTrace();
        }
        return new BaseResponse();
    }

    @PutMapping("/update")
    public BaseResponse updateUserRole (@RequestBody LabUserRole userRole) {
        boolean result = userRoleService.updateById(userRole);
        if (result) {
            return new BaseResponse();
        } else {
            return new BaseResponse(HttpConstants.ERR_UPDATE, "更新角色信息失败，请确保角色名未重复");
        }
    }

    @DeleteMapping("/delete")
    public BaseResponse deleteUserRole (@RequestParam("id") BigInteger id) {
        boolean result = userRoleService.removeById(id);

        if (result) {
            return new BaseResponse();
        } else {
            return new BaseResponse(HttpConstants.ERR_UPDATE, "删除角色信息失败，该角色可能不存在");
        }
    }
}
