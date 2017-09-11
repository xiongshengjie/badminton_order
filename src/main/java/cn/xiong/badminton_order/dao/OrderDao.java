package cn.xiong.badminton_order.dao;


import cn.xiong.badminton_order.bean.Order;
import cn.xiong.badminton_order.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2017/9/10.
 */
public class OrderDao {

    public int addOrder(Order order) throws SQLException {
        String sql = "insert into badminton_order(uid,date,start_time,end_time,area,flag,money) values(?,?,?,?,?,?,?)";

        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());

        return runner.update(sql,order.getUid(),order.getDate(),order.getStart_time(),
                order.getEnd_time(),order.getArea(),order.getFlag(),order.getMoney());
    }

    public int updateOrder(Order order) throws SQLException {
        String sql = "update badminton_order set flag=?,money=? where id=?";
        QueryRunner runner = new QueryRunner((DataSourceUtils.getDataSource()));

        return runner.update(sql,order.getFlag(),order.getMoney(),order.getId());
    }

    public List<Order> getTimeOrder(Order order) throws SQLException {
        String sql = "select * from badminton_order where date=? and area=? and flag='order' and ((start_time>=? and start_time<?) or (end_time>? and end_time <=?))";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        return runner.query(sql,new BeanListHandler<Order>(Order.class),
                order.getDate(),order.getArea(),order.getStart_time(),order.getEnd_time(),order.getStart_time(),order.getEnd_time());
    }

    public Order getCancleOrder(Order order) throws SQLException {
        String sql = "select * from badminton_order where uid=? and date=? and start_time=? and end_time=? and area=? and flag='order'";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        return runner.query(sql,new BeanHandler<Order>(Order.class),
                order.getUid(),order.getDate(),order.getStart_time(),order.getEnd_time(),order.getArea());
    }

    public List<Order> getAllOrder() throws SQLException {
        String sql = "select * from badminton_order order by area,date,start_time";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        return runner.query(sql,new BeanListHandler<Order>(Order.class));
    }
}
