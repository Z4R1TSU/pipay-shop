package pr.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {

    // http状态码
    private int code;

    // 返回信息
    private String msg;

    // 返回数据
    private Object data;

    public static  Result success(Object data) {
        return new Result(200, "success", data);
    }

    public static  Result success() {
        return new Result(200, "success", null);
    }

    public static  Result success(Object data, String msg) {
        return new Result(200, msg, data);
    }

    public static  Result fail(String msg) {
        return new Result(500, msg, null);
    }

    public static  Result fail(int code, String msg) {
        return new Result(code, msg, null);
    }

    public static  Result fail(int code, String msg, Object data) {
        return new Result(code, msg, data);
    }

}
