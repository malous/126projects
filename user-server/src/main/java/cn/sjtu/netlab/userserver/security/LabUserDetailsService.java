package cn.sjtu.netlab.userserver.security;

import cn.sjtu.netlab.userserver.mapper.LabRoleMapper;
import cn.sjtu.netlab.userserver.model.LabUser;
import cn.sjtu.netlab.userserver.service.LabUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("detail")
public class LabUserDetailsService implements UserDetailsService {
    @Autowired
    private LabUserService userService;
    @Autowired
    private LabRoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        LabUser user = userService.findByUsername(s);
        List<String> roleList = roleMapper.getRoleByUserId(user.getId());
        // 由于UserDetails中为nonLock等，因此此处对于LockFlag，DelFlag取非
        Set authorities = generateAuthorities(roleList);
        System.out.println(authorities);
        return new LabUserDetails(user.getUsername(), user.getPassword(),
                                                !user.getLockFlag(), !user.getDelFlag(), authorities);
    }

    /**
     * 通过拼接角色的name和level生成SimpleGrantedAuthority
     * @param roleList
     * @return 授权集合
     */
    private Set<? extends GrantedAuthority> generateAuthorities (List<String> roleList) {
        Set<SimpleGrantedAuthority> authoritySet = new HashSet<>();
        roleList.forEach(role -> {
//            String authority = new StringBuilder(role.getName()).append(" ").append(role.getLevel()).toString();
            authoritySet.add(new SimpleGrantedAuthority(role));
        });
        return authoritySet;
    }
}
