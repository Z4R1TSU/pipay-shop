package pr.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {

    // http状态码
    private int code;

    // 返回信息
    private String msg;

    // 返回数据
    private T data;

    public static <T> Result<T> success(T data) {
        return new Result<T>(200, "success", data);
    }

    public static <T> Result<T> success() {
        return new Result<T>(200, "success", null);
    }

    public static <T> Result<T> success(T data, String msg) {
        return new Result<T>(200, msg, data);
    }

    public static <T> Result<T> fail(String msg) {
        return new Result<T>(500, msg, null);
    }

    public static <T> Result<T> fail(int code, String msg) {
        return new Result<T>(code, msg, null);
    }

    public static <T> Result<T> fail(int code, String msg, T data) {
        return new Result<T>(code, msg, data);
    }

}
