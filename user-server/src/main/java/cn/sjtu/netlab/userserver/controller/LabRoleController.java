package cn.sjtu.netlab.userserver.controller;


import cn.sjtu.netlab.userserver.constants.HttpConstants;
import cn.sjtu.netlab.userserver.model.LabRole;
import cn.sjtu.netlab.userserver.service.LabRoleService;
import cn.sjtu.netlab.userserver.vo.BaseResponse;
import cn.sjtu.netlab.userserver.vo.ListResponse;
import cn.sjtu.netlab.userserver.vo.ObjectResponse;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

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
@RequestMapping("/role")
public class LabRoleController {
    @Autowired
    private LabRoleService roleService;

    @GetMapping("/find")
    public BaseResponse findRole () {
        return new ObjectResponse<>(roleService.list());
    }

    @PostMapping("/insert")
    public BaseResponse addRole (@RequestBody LabRole role) {
        try {
            roleService.save(role);
        } catch (DuplicateKeyException e) {
            return new BaseResponse(HttpConstants.ERR_ADD, "角色名称重复");
        }
        return new ObjectResponse<>(role.getId());
    }

    @PutMapping("/update")
    public BaseResponse updateRole (@RequestBody LabRole role) {
        boolean result = roleService.updateById(role);
        if (result) {
            return new BaseResponse();
        } else {
            return new BaseResponse(HttpConstants.ERR_UPDATE, "更新角色信息失败，请确保角色名未重复");
        }
    }

    @DeleteMapping("/delete")
    public BaseResponse deleteRole (@RequestParam("id") BigInteger id) {
        roleService.removeById(id);
        return new BaseResponse();
    }
}
