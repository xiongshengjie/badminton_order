package cn.xiong.badminton_order.utils;

import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/9/8.
 */
public class DateCheck {

    private static String str = "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)";

    private static Pattern datePattern = Pattern.compile(str);

    public static boolean isDateCorrect(String date){
        return datePattern.matcher(date).matches();
    }
}
