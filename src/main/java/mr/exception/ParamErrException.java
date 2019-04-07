package mr.exception;

/**
 * Created by LiChuan on 2019/4/4.
 */
public class ParamErrException extends Exception {
    public ParamErrException(String msg){
        super(msg);
    }
    public ParamErrException(){
        super();
    }
}
