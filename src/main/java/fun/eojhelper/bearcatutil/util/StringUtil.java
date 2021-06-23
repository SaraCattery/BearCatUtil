package fun.eojhelper.bearcatutil.util;

/**
 * Created by nichenhao on 2021/06/12.
 */
public class StringUtil {

    public static Boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static Boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

}
