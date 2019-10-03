package cn.sjtu.netlab.authorizationclient.controller;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class Test {
    @PreAuthorize("hasAnyAuthority('admin 1')")
    @RequestMapping("/index")
    public String index () {
        return "Hello World";
    }

    @PreAuthorize("hasRole('admin 1')")
    @RequestMapping("/hello")
    public String hello () {
        return "Hello you are admin 1";
    }

    @RequestMapping("/qwer")
    public String qwer () {
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        authorities.forEach(System.out::println);
        return "Hello you are admin 1";
    }

}
