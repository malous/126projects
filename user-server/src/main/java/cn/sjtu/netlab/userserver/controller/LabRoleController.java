package cn.sjtu.netlab.userserver.controller;


import cn.sjtu.netlab.userserver.constants.HttpConstants;
import cn.sjtu.netlab.userserver.model.LabRole;
import cn.sjtu.netlab.userserver.service.LabRoleService;
import cn.sjtu.netlab.userserver.vo.BaseResponse;
import cn.sjtu.netlab.userserver.vo.ListResponse;
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
@RestController
@RequestMapping("/role")
public class LabRoleController {
    @Autowired
    private LabRoleService roleService;

    @GetMapping("/find")
    public BaseResponse findUser (@RequestParam(value = "name", required = false) String name,
                                  @RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize) {
        IPage<LabRole> page = roleService.findRole(name, pageNum, pageSize);
        return new ListResponse<>(page.getTotal(), page.getRecords());
    }

    @PostMapping("/insert")
    public BaseResponse addUser (@RequestBody LabRole role) {
        try {
            roleService.save(role);
        } catch (DuplicateKeyException e) {
            return new BaseResponse(HttpConstants.ERR_ADD, "角色名称重复");
        }
        return new BaseResponse();
    }

    @PutMapping("/update")
    public BaseResponse updateUser (@RequestBody LabRole role) {
        boolean result = roleService.updateById(role);
        if (result) {
            return new BaseResponse();
        } else {
            return new BaseResponse(HttpConstants.ERR_UPDATE, "更新角色信息失败，请确保角色名未重复");
        }
    }

    @DeleteMapping("/delete")
    public BaseResponse deleteUser (@RequestParam("id") BigInteger id) {
        roleService.removeById(id);
        return new BaseResponse();
    }
}
