//package cn.sjtu.netlab.authorizationclient.security;
//
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.jwt.Jwt;
//import org.springframework.security.jwt.JwtHelper;
//import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.*;
//
//public class JwtAuthenticationFilter extends BasicAuthenticationFilter {
//    @Autowired
//    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
//        super(authenticationManager);
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
//        String token = getToken(request);
//        Authentication authentication = getAuthentication(token);
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        SecurityContextHolder.getContext().getAuthentication().getAuthorities();
//        chain.doFilter(request, response);
//    }
//
//    private Authentication getAuthentication (String token) {
//        Authentication authentication = null;
//        // 请求令牌不能为空
//        if(token != null) {
//            // 上下文中Authentication为空
//            if(getAuthentication() == null) {
//                Jwt jwt = JwtHelper.decode(token);
//                String claims = jwt.getClaims();
//                if(claims == null) {
//                    return null;
//                }
//                Map claimsMap = (Map) JSONObject.parse(claims);
//                String username = (String) claimsMap.get("user_name");
//                Integer expiration = (Integer) claimsMap.get("exp");
//                Date expirationDate = new Date(expiration);
//                JSONArray authors = (JSONArray) claimsMap.get("authorities");
//                if(username == null) {
//                    return null;
//                }
//                if(expirationDate.before(new Date())) {
//                    return null;
//                }
//                List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//                if (authors != null) {
//                    for (Object object : (List) authors) {
//                        new SimpleGrantedAuthority((String) ((Map) object).get("authority"));
//                    }
//                }
//                authentication = new UsernamePasswordAuthenticationToken(username, null, authorities);
//            } else {
//                UserDetails principal = (UserDetails) getAuthentication().getPrincipal();
//                String username = getUsername(token);
//                if (principal.getUsername().equals(username)) {
//                    authentication = getAuthentication();
//                }
//            }
//        }
//        return authentication;
//    }
//
//    private  String getToken(HttpServletRequest request) {
//        String token = request.getHeader("Authorization");
//        String tokenHead = "Bearer ";
//        if(token == null) {
//            token = request.getHeader("token");
//        } else if(token.contains(tokenHead)){
//            token = token.substring(tokenHead.length());
//        }
//        if("".equals(token)) {
//            token = null;
//        }
//        return token;
//    }
//
//    private Authentication getAuthentication() {
//        if(SecurityContextHolder.getContext() == null) {
//            return null;
//        }
//        return SecurityContextHolder.getContext().getAuthentication();
//    }
//
//    private String getUsername (String token) {
//        Jwt jwt = JwtHelper.decode(token);
//        String claims = jwt.getClaims();
//        if(claims == null) {
//            return null;
//        }
//        Map claimsMap = (Map) JSONObject.parse(claims);
//        return  (String) claimsMap.get("user_name");
//    }
//}
