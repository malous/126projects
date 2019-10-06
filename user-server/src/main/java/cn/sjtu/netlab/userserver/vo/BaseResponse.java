package cn.sjtu.netlab.userserver.vo;

import cn.sjtu.netlab.userserver.constants.HttpConstants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 返回给前端时使用的封装类
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse {
    private int code = HttpConstants.SUCCESS;
    private String msg = HttpConstants.SUCCESS_MSG;

    public static BaseResponse ok () {
        BaseResponse response = new BaseResponse();
        response.setCode(HttpConstants.SUCCESS);
        response.setMsg(HttpConstants.SUCCESS_MSG);
        return response;
    }

    public static BaseResponse ok (String msg) {
        BaseResponse response = new BaseResponse();
        response.setCode(HttpConstants.SUCCESS);
        response.setMsg(msg);
        return response;
    }

    public static BaseResponse error (int code, String msg) {
        BaseResponse response = new BaseResponse();
        response.setCode(code);
        response.setMsg(msg);
        return response;
    }
}
