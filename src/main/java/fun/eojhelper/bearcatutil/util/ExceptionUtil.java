package fun.eojhelper.bearcatutil.util;

/**
 * Created by nichenhao on 2021/06/14.
 */
public class ExceptionUtil {

    public static String getCause(Exception e) {
        return e.getCause().getMessage();
    }

}
