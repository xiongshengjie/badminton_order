package cn.xiong.badminton_order.service;

import cn.xiong.badminton_order.bean.Order;
import cn.xiong.badminton_order.dao.OrderDao;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/10.
 */
public class OrderService {

    private OrderDao dao = new OrderDao();
    public boolean addOrder(Order order){

        List<Order> list = new ArrayList<>();
        try {
            list = dao.getTimeOrder(order);
            if(list.size() == 0){
                dao.addOrder(order);
                return true;
            }else {
                System.out.println("Error: the booking conflicts with existing bookings!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean cancleOrder(Order order){

        Order result = null;
        try {
            result = dao.getCancleOrder(order);
            if(result != null){
                order.setId(result.getId());
                dao.updateOrder(order);
                return true;
            }else{
                System.out.println("the booking being cancelled does not exist!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public void listInMoney(){
        int countA=0,countB=0,countC=0,countD=0;
        float sumA=0,sumB=0,sumC=0,sumD=0;
        System.out.println("收入汇总");
        System.out.println("---");
        try {
            List<Order> list = dao.getAllOrder();
            for(Order order:list){
                if(order.getArea().equals("A")){
                    if(countA==0){
                        System.out.println("场地:A");
                        countA++;
                    }
                    if(order.getFlag().equals("order")){
                        if(order.getStart_time()==9){
                            System.out.println(order.getDate()+" "+"0"+order.getStart_time()+":00~"+order.getEnd_time()+":00 "+order.getMoney()+"元");
                        }else {
                            System.out.println(order.getDate()+" "+order.getStart_time()+":00~"+order.getEnd_time()+":00 "+order.getMoney()+"元");
                        }

                    }else{
                        if(order.getStart_time()==9){
                            System.out.println(order.getDate()+" "+"0"+order.getStart_time()+":00~"+order.getEnd_time()+":00 违约金 "+order.getMoney()+"元");
                        }else {
                            System.out.println(order.getDate()+" "+order.getStart_time()+":00~"+order.getEnd_time()+":00 违约金 "+order.getMoney()+"元");
                        }
                    }
                    sumA += order.getMoney();
                }else if(order.getArea().equals("B")){

                    if(countB==0){
                        System.out.println("小计:"+sumA+"元");
                        System.out.println();
                        System.out.println("场地:B");
                        countB++;
                    }
                    if(order.getFlag().equals("order")){
                        if(order.getStart_time()==9){
                            System.out.println(order.getDate()+" "+"0"+order.getStart_time()+":00~"+order.getEnd_time()+":00 "+order.getMoney()+"元");
                        }else {
                            System.out.println(order.getDate()+" "+order.getStart_time()+":00~"+order.getEnd_time()+":00 "+order.getMoney()+"元");
                        }

                    }else{
                        if(order.getStart_time()==9){
                            System.out.println(order.getDate()+" "+"0"+order.getStart_time()+":00~"+order.getEnd_time()+":00 违约金 "+order.getMoney()+"元");
                        }else {
                            System.out.println(order.getDate()+" "+order.getStart_time()+":00~"+order.getEnd_time()+":00 违约金 "+order.getMoney()+"元");
                        }
                    }
                    sumB += order.getMoney();
                } else if(order.getArea().equals("C")){

                    if(countC==0){
                        System.out.println("小计:"+sumB+"元");
                        System.out.println();
                        System.out.println("场地:C");
                        countC++;
                    }
                    if(order.getFlag().equals("order")){
                        if(order.getStart_time()==9){
                            System.out.println(order.getDate()+" "+"0"+order.getStart_time()+":00~"+order.getEnd_time()+":00 "+order.getMoney()+"元");
                        }else {
                            System.out.println(order.getDate()+" "+order.getStart_time()+":00~"+order.getEnd_time()+":00 "+order.getMoney()+"元");
                        }

                    }else{
                        if(order.getStart_time()==9){
                            System.out.println(order.getDate()+" "+"0"+order.getStart_time()+":00~"+order.getEnd_time()+":00 违约金 "+order.getMoney()+"元");
                        }else {
                            System.out.println(order.getDate()+" "+order.getStart_time()+":00~"+order.getEnd_time()+":00 违约金 "+order.getMoney()+"元");
                        }
                    }
                    sumC += order.getMoney();
                }else if(order.getArea().equals("D")){

                    if(countD==0){
                        System.out.println("小计:"+sumC+"元");
                        System.out.println();
                        System.out.println("场地:D");
                        countD++;
                    }
                    if(order.getFlag().equals("order")){
                        if(order.getStart_time()==9){
                            System.out.println(order.getDate()+" "+"0"+order.getStart_time()+":00~"+order.getEnd_time()+":00 "+order.getMoney()+"元");
                        }else {
                            System.out.println(order.getDate()+" "+order.getStart_time()+":00~"+order.getEnd_time()+":00 "+order.getMoney()+"元");
                        }

                    }else{
                        if(order.getStart_time()==9){
                            System.out.println(order.getDate()+" "+"0"+order.getStart_time()+":00~"+order.getEnd_time()+":00 违约金 "+order.getMoney()+"元");
                        }else {
                            System.out.println(order.getDate()+" "+order.getStart_time()+":00~"+order.getEnd_time()+":00 违约金 "+order.getMoney()+"元");
                        }
                    }
                    sumD += order.getMoney();
                }
            }
            System.out.println("小计:"+sumD+"元");
            System.out.println("---");
            float sum = sumA+sumB+sumC+sumD;
            System.out.println("总计:"+sum+"元");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
