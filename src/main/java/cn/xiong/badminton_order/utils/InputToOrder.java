package cn.xiong.badminton_order.utils;

import cn.xiong.badminton_order.bean.Order;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/9/8.
 */
public class InputToOrder {

    //用户名以字母开头，数字、字母、下划线组成
    private String inPattern = "^[A-Za-z][A-Za-z1-9_-]+ [1-9]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1]) (20|21|22|23|[0-1]\\d):00\\d~(20|21|22|23|[0-1]\\d):00 [A-D]";
    private String inPatternCancle = "^[A-Za-z][A-Za-z1-9_-]+ [1-9]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1]) (20|21|22|23|[0-1]\\d):00~(20|21|22|23|[0-1]\\d):00 [A-D] C";
    private static InputToOrder inputToOrder;

    //核对输入是否正确
    private Pattern inputPattern = Pattern.compile(inPattern);
    private Pattern inputPatternCancle = Pattern.compile(inPatternCancle);

    //日期格式化
    private SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd");

    public static InputToOrder getInstance(){
        if(inputToOrder == null){
            return new InputToOrder();
        }
        return inputToOrder;
    }

    private Order getOrder(String input){

        Order order = null;
        Matcher matcher = inputPattern.matcher(input);
        Matcher matcherCancle = inputPatternCancle.matcher(input);

        //预定订单处理
        if(matcher.matches()){

            String[] data = input.split(" ");
            try {
                //时间是否正确(包括日期判断，日期是否在当前时间或者之后)
                if(DateCheck.isDateCorrect(data[1])&&(formatter.parse(data[1]).getTime()>=formatter.parse(formatter.format(new Date())).getTime())){
                    String[] time = data[2].split("~");
                    String startTime = time[0].substring(0,time[0].indexOf(":"));
                    String endTime = time[1].substring(0,time[1].indexOf(":"));

                    //时间是否大于当前时间，前后时间是否正确
                    if((Integer.parseInt(startTime)> Calendar.getInstance().get(Calendar.HOUR_OF_DAY))&&Integer.parseInt(startTime)<Integer.parseInt(endTime)){
                        order = new Order();
                        order.setFlag("order");
                        order.setStartTime(startTime);
                        order.setEndTime(endTime);
                        order.setArea(data[3]);
                        order.setDate(data[1]);
                        order.setUid(data[0]);
                    }
                }
            } catch (ParseException e) {
            }

        }else if(matcherCancle.matches()){//取消订单处理

            String[] data = input.split(" ");
            try {
                if(DateCheck.isDateCorrect(data[1])&&(formatter.parse(data[1]).getTime()>=formatter.parse(formatter.format(new Date())).getTime())){
                    String[] time = data[2].split("~");
                    String startTime = time[0].substring(0,time[0].indexOf(":"));
                    String endTime = time[1].substring(0,time[1].indexOf(":"));

                    if((Integer.parseInt(startTime)>Calendar.getInstance().get(Calendar.HOUR_OF_DAY))&&Integer.parseInt(startTime)<Integer.parseInt(endTime)){
                        order = new Order();
                        order.setFlag("cancle");
                        order.setStartTime(startTime);
                        order.setEndTime(endTime);
                        order.setArea(data[3]);
                        order.setDate(data[1]);
                        order.setUid(data[0]);
                    }
                }
            } catch (ParseException e) {
            }

        }
        return order;
    }

    public static void main(String[] args) {

        String str = "U123 2017-09-09 12:00~22:00 A C";
        Order order = getInstance().getOrder(str);
        System.out.println(order);
    }
}
