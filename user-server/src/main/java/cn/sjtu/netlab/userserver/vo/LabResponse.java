package cn.sjtu.netlab.userserver.vo;

import cn.sjtu.netlab.userserver.constants.HttpConstants;
import lombok.Data;

/**
 * 返回给前端时使用的封装类
 */
@Data
public class LabResponse<T> {
    private int code;
    private String msg;
    private T entity;

    public static LabResponse ok () {
        LabResponse response = new LabResponse();
        response.setCode(HttpConstants.STATUS_OK);
        response.setMsg(HttpConstants.STATUS_OK_MSG);
        return response;
    }

    public static LabResponse ok (String msg) {
        LabResponse response = new LabResponse();
        response.setCode(HttpConstants.STATUS_OK);
        response.setMsg(msg);
        return response;
    }

    public static <T> LabResponse ok (T entity) {
        LabResponse<T> response = new LabResponse<>();
        response.setCode(HttpConstants.STATUS_OK);
        response.setMsg(HttpConstants.STATUS_OK_MSG);
        response.setEntity(entity);
        return response;
    }

    public static LabResponse error (int code, String msg) {
        LabResponse response = new LabResponse();
        response.setCode(code);
        response.setMsg(msg);
        return response;
    }
}
