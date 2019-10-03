package cn.sjtu.netlab.userserver.security;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 自定义的Jwt令牌，增加存储提供给其他服务的信息
 */
public class JwtAccessToken extends JwtAccessTokenConverter {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
//        JwtHelper.decode("")
        DefaultOAuth2AccessToken token = new DefaultOAuth2AccessToken(accessToken);
        LabUserDetails user = (LabUserDetails) authentication.getPrincipal();
        final Map<String, Object> info = new HashMap<>();
        info.put("username", user.getUsername());
        info.put("authorities", user.getAuthorities());
        token.setAdditionalInformation(info);
        return super.enhance(token, authentication);
    }

    public static void main(String[] args) {
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJhZG1pbiIsInNjb3BlIjpbIndlYiJdLCJleHAiOjE1Njk0MzgxMDIsImF1dGhvcml0aWVzIjpbeyJhdXRob3JpdHkiOiJhZG1pbiAxIn1dLCJqdGkiOiI1MGIzODJmNS1mNDJmLTRlZTQtOGU4Zi01MzhhNDQ0MWFlMDkiLCJjbGllbnRfaWQiOiJuZXRfbGFiIiwidXNlcm5hbWUiOiJhZG1pbiJ9.6dJfShbDBXosjfemxuf_n6viRErYdixWfiF5YAWFSow";
        Jwt jwt = JwtHelper.decode(token);
        String claims = jwt.getClaims();
        Map map = (Map) JSONObject.parse(claims);
        map.forEach((k,v) -> {
            System.out.println("key: " + k + ", " + "value: " + v);
        });
        System.out.println(jwt);
    }

//    @Override
//    public Map<String, ?> convertAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {
//        LinkedHashMap<String, Object> response = new LinkedHashMap();
//        response.put("user_name", authentication.getName());
//        response.put("name", ((LabUserDetails) authentication.getPrincipal()).getUsername());
//        response.put("id", ((User) authentication.getPrincipal()).getId());
//        response.put("createAt", ((User) authentication.getPrincipal()).getCreateAt());
//        if (authentication.getAuthorities() != null && !authentication.getAuthorities().isEmpty()) {
//            response.put("authorities", AuthorityUtils.authorityListToSet(authentication.getAuthorities()));
//        }
//
//        return response;
//    }

    @Override
    public OAuth2AccessToken extractAccessToken(String value, Map<String, ?> map) {
        return super.extractAccessToken(value, map);
    }

    @Override
    public OAuth2Authentication extractAuthentication(Map<String, ?> map) {
        return super.extractAuthentication(map);
    }
}
