package cn.sjtu.netlab.userserver.controller;


import cn.sjtu.netlab.userserver.constants.HttpConstants;
import cn.sjtu.netlab.userserver.model.LabUser;
import cn.sjtu.netlab.userserver.model.LabUserRole;
import cn.sjtu.netlab.userserver.service.LabUserRoleService;
import cn.sjtu.netlab.userserver.service.LabUserService;
import cn.sjtu.netlab.userserver.vo.BaseResponse;
import cn.sjtu.netlab.userserver.vo.ListResponse;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.security.Principal;
import java.util.HashMap;
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
@CrossOrigin
@RequestMapping("/user")
public class LabUserController {
    @Autowired
    private LabUserService userService;
    @Autowired
    private LabUserRoleService userRoleService;
    @Autowired
    private PasswordEncoder passwordEncoder;

//    @RequestMapping("/me")
//    public Principal user(Principal principal) {
//        System.out.println(principal.getName());
//        return principal;
//    }

    @GetMapping("/find")
    public BaseResponse findUser (@RequestParam(value = "name", required = false) String name,
                                             @RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize) {
        IPage<LabUser> page = userService.findUser(name, pageNum, pageSize);
        return new ListResponse<>(page.getTotal(), page.getRecords());
    }

    @PostMapping("/insert")
    public BaseResponse addUser (@RequestBody LabUser user) {
        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
        boolean result = userService.save(user);
        if (result) {
            return new BaseResponse();
        } else {
            return new BaseResponse(HttpConstants.ERR_ADD, "插入用户数据失败，请检查是否填写全部必填数据");
        }
    }

    @PutMapping("/update")
    public BaseResponse updateUser (@RequestBody LabUser user) {
        if (user.getPassword() != null) {
            String password = passwordEncoder.encode(user.getPassword());
            user.setPassword(password);
        }
        boolean result = userService.updateById(user);
        if (result) {
            return new BaseResponse();
        } else {
            return new BaseResponse(HttpConstants.ERR_UPDATE, "更新用户数据失败，该用户可能不存在");
        }
    }

    @DeleteMapping("/delete")
    public BaseResponse deleteUser (@RequestParam("id") BigInteger id) {
        userService.removeById(id);
        return new BaseResponse();
    }
}
