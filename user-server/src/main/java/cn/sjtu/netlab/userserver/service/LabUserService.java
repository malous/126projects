package cn.sjtu.netlab.userserver.service;

import cn.sjtu.netlab.userserver.model.LabUser;
import cn.sjtu.netlab.userserver.vo.PageSelect;

import java.math.BigInteger;

public interface LabUserService {
    LabUser findByUserName (String username);

    PageSelect<LabUser> findUser(String name, int pageNum, int pageSize);

    int insert(LabUser user);

    int updateUser (LabUser user);

    int deleteUser (BigInteger id);
}
