package cn.sjtu.netlab.userserver.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListResponse<T> extends BaseResponse {
    private long total;
    private List<T> result;
}
