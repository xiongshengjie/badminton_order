package cn.xiong.badminton_order.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Administrator on 2017/9/8.
 */
public class CalMoney {

    private SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd");
    private static CalMoney calMoney;

    public static CalMoney getInstance(){
        if(calMoney == null){
            calMoney = new CalMoney();
        }
        return calMoney;
    }

    public float calAllMoney(String date,int startTime,int endTime,boolean isOrder){
        Calendar calendar = Calendar.getInstance();
        int weekDay = 0;
        float money = 0;
        try {
            calendar.setTime(formatter.parse(date));
            int flag = calendar.get(Calendar.DAY_OF_WEEK);
            if(flag == 1){
                weekDay = 7;
            }else {
                weekDay = flag - 1;
            }
        } catch (ParseException e) {
        }
        switch (weekDay){
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                if(isOrder){
                    money = calWeekDayMoney(startTime,endTime);
                }else {
                    money = calWeekDayMoney(startTime,endTime)/2;
                }
                break;
            case 6:
            case 7:
                if(isOrder){
                    money = calWeekEndMonry(startTime,endTime);
                }else {
                    money = calWeekEndMonry(startTime,endTime)/4;
                }
                break;
        }
        return money;
    }

    //迭代计算工作日场地费用
    private int calWeekDayMoney(int startTime,int endTime){

        if(startTime < 12 && endTime <= 12){
            return (endTime-startTime)*30;
        }else if(startTime<12&&endTime > 12){
            return (12-startTime)*30 + calWeekDayMoney(12,endTime);
        }else if(startTime < 18 && endTime <= 18){
            return (endTime-startTime)*50;
        }else if(startTime < 18 && endTime > 18){
            return (18 - startTime)*50 + calWeekDayMoney(18,endTime);
        }else if(startTime < 20 && endTime <= 20){
            return (endTime-startTime)*80;
        }else if(startTime < 20 && endTime > 20) {
            return (20 - startTime) * 80 + calWeekDayMoney(20, endTime);
        } else{
            return (endTime-startTime)*60;
        }
    }

    //计算周末的费用
    private int calWeekEndMonry(int startTime,int endTime){
        if(startTime < 12 && endTime <= 12){
            return (endTime-startTime)*40;
        }else if(startTime < 12 && endTime > 12){
            return (12-startTime)*40 + calWeekEndMonry(12,endTime);
        }else if(startTime < 18 && endTime <= 18){
            return (endTime-startTime)*50;
        }else if(startTime < 18 && endTime > 18){
            return (18 - startTime)*50 + calWeekDayMoney(18,endTime);
        }else {
            return (endTime - startTime)*60;
        }
    }
}
