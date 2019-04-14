package mr.utils;

/**
 * Created by LiChuan on 2019/1/21.
 */
public class Result {
    private String message;
    private int code;
    private Object data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Result(){}
    public Result(Object data,String message,int code){
        this.data = data;
        this.message = message;
        this.code = code;
    }

    public static Result createResult(int code){
        return new Result(null,null,code);
    }
    public static Result createOkResult(Object data){
        return new Result(data,"ok",0);
    }
    public static Result createOkResult(){
        return new Result(null,"ok",0);
    }
    public static Result createErrorResult(String message,int code){
        return new Result(null,message,code);
    }
    public static Result createErrorResult(){
        return new Result(null,"内部错误",-1);
    }
    public static Result createParamErrorResult(){
        return new Result(null,"请求参数错误",-2);
    }
    public static Result createNotFoundResult(){
        return new Result(null,"没有找到结果",-3);
    }
}
