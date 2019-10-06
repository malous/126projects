package cn.sjtu.netlab.userserver.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ObjectResponse<T> extends BaseResponse {
    T result;
}
