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
    private String inPattern = "^[A-Za-z][A-Za-z0-9_-]+ [1-9]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1]) (20|21|22|23|[0-1]\\d):00~(20|21|22|23|[0-1]\\d):00 [A-D]$";
    private String inPatternCancle = "^[A-Za-z][A-Za-z0-9_-]+ [1-9]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1]) (20|21|22|23|[0-1]\\d):00~(20|21|22|23|[0-1]\\d):00 [A-D] C$";
    private static InputToOrder inputToOrder;

    //核对输入是否正确
    private Pattern inputPatternCancle = Pattern.compile(inPatternCancle);
    private Pattern inputPattern = Pattern.compile(inPattern);


    //日期格式化
    private SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd HH:mm");

    public static InputToOrder getInstance(){
        if(inputToOrder == null){
            return new InputToOrder();
        }
        return inputToOrder;
    }

    public Order getOrder(String input){

        Order order = null;
        Matcher matcherCancle = inputPatternCancle.matcher(input);
        Matcher matcher = inputPattern.matcher(input);


        //预定订单处理
        if(matcher.matches()){

            String[] data = input.split(" ");
            String dateTime = data[1] +" "+ data[2].split("~")[0];
            try {
                //时间是否正确(包括日期判断，日期是否在当前时间或者之后)
                if(DateCheck.isDateCorrect(data[1])&&(formatter.parse(dateTime).getTime()>=formatter.parse(formatter.format(new Date())).getTime())){
                    String[] time = data[2].split("~");
                    String startTime = time[0].substring(0,time[0].indexOf(":"));
                    String endTime = time[1].substring(0,time[1].indexOf(":"));

                    //时间是否大于当前时间，前后时间是否正确
                    int start = Integer.parseInt(startTime);
                    int end = Integer.parseInt(endTime);
                    if(start>=9 && end <= 22 && start < end){
                        order = new Order();
                        order.setFlag("order");
                        order.setStart_time(start);
                        order.setEnd_time(end);
                        order.setArea(data[3]);
                        order.setDate(data[1]);
                        order.setUid(data[0]);
                        order.setMoney(CalMoney.getInstance().calAllMoney(data[1],start,end,true));
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }else if(matcherCancle.matches()){//取消订单处理

            String[] data = input.split(" ");
            String dateTime = data[1] + " " + data[2].split("~")[0];
            try {
                if(DateCheck.isDateCorrect(data[1])&&(formatter.parse(dateTime).getTime()>=formatter.parse(formatter.format(new Date())).getTime())){
                    String[] time = data[2].split("~");
                    String startTime = time[0].substring(0,time[0].indexOf(":"));
                    String endTime = time[1].substring(0,time[1].indexOf(":"));

                    int start = Integer.parseInt(startTime);
                    int end = Integer.parseInt(endTime);
                    if(start>=9 && end <= 22 && start < end){
                        order = new Order();
                        order.setFlag("cancle");
                        order.setStart_time(start);
                        order.setEnd_time(end);
                        order.setArea(data[3]);
                        order.setDate(data[1]);
                        order.setUid(data[0]);
                        order.setMoney(CalMoney.getInstance().calAllMoney(data[1],start,end,false));
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
        return order;
    }

    public static void main(String[] args) {

        String str = "U123 2017-09-11 10:00~19:00 B";
        Order order = getInstance().getOrder(str);
        System.out.println(order);
    }
}
