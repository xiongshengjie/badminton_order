package cn.xiong.badminton_order.Main;

import cn.xiong.badminton_order.bean.Order;
import cn.xiong.badminton_order.service.OrderService;
import cn.xiong.badminton_order.utils.InputToOrder;

import java.util.Scanner;

/**
 * Created by Administrator on 2017/9/10.
 */
public class Main {

    private OrderService service = new OrderService();
    public static void main(String[] args) {

        Main start = new Main();
        Scanner scanner = new Scanner(System.in);
        boolean flag;
        while (true){
            flag = false;
            String input = scanner.nextLine();
            if(" ".equals(input)){
                start.service.listInMoney();
            }else {
                Order order = InputToOrder.getInstance().getOrder(input);
                if(order == null){
                    System.out.println("Error: the booking is invalid!");
                }else{
                    if(order.getFlag().equals("order")) {
                        flag = start.service.addOrder(order);
                    }else{
                        flag = start.service.cancleOrder(order);
                    }
                }
                if(flag){
                    System.out.println("Success: the booking is accepted!");
                }
            }
        }
    }
}
