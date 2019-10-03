package cn.sjtu.netlab.userserver.controller;

import cn.sjtu.netlab.userserver.model.LabUser;
import cn.sjtu.netlab.userserver.service.LabUserService;
import cn.sjtu.netlab.userserver.vo.LabResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.security.Principal;

@RestController
@RequestMapping("/user")
public class LabUserController {
    @Autowired
    private LabUserService userService;

    @RequestMapping("/me")
    public Principal user(Principal principal) {
        System.out.println(principal.getName());
        return principal;
    }

    @GetMapping("/find")
    public LabResponse findUser (@RequestParam(value = "name", required = false) String name,
                                 @RequestParam(value = "pageNum") int pageNum, @RequestParam(value = "pageSize") int pageSize) {
        return LabResponse.ok(userService.findUser(name, pageNum, pageSize));
    }

    @PostMapping("/insert")
    public LabResponse addUser (@RequestBody LabUser user) {
        int result = userService.insert(user);
        if (result > 0) {
            return LabResponse.ok();
        } else {
            return LabResponse.error(result, "");
        }
    }

    @PutMapping("/update")
    public LabResponse updateUser (@RequestBody LabUser user) {
        return LabResponse.ok(userService.updateUser(user));
    }

    @DeleteMapping("/delete")
    public LabResponse deleteUser (@RequestParam("id") BigInteger id) {
        return LabResponse.ok(userService.deleteUser(id));
    }
}
