# badminton_order
项目地址:<https://github.com/xiongshengjie/badminton_order.git>
## 项目说明
###运行环境
     jdk 1.8.0_121  
     mysql Server version: 5.7.10-log MySQL Community Server
###运行方式
     --项目最外层目录下，有badminton_order.sql文件，为mysql数据库脚本，导入mysql数据库  
       会生成有badminton_order数据库，其中有数据表有badminton_order  
     --导入成功之后，用解压软件打开order-jar-with-dependencies.jar，修改c3p0-config.xml  
       将user与password修改为当前数据库的用户名与密码，并保存  
     --运行cmd，并到修改后的order-jar-with-dependencies.jar目录下，输入  
       java -jar order-with-dependcies.jar，即可运行，进行测试
###配置文件
####c3p0-config.xml
     c3p0数据库连接池的配置文件，使用时需要将其中的user和password修改为你自己的
###包结构
####cn.xiong.badminton_order.bean
     本包为实体类，其中包含Order类，属性为  
     private String id;             //订单id  
     
     private String uid;            //订单用户名  
     
     private String date;           //订单日期  
     
     private int start_time;        //订单开始时间  
     
     private int end_time;          //订单结束时间  
     
     private String area;           //订单场地  
     
     private String flag;           //订单标记，预定或者取消  
     
     private float money;           //订单金额
     
####cn.xiong.badminton_order.dao
     本包为dao层交互数据库的包，其中包含5个方法  
     `int addOrder(Order order)`:添加订单  
     `int updateOrder(Order order)`:更新订单，取消预定时使用  
     `List<Order> getTimeOrder(Order order)`:查询是否有冲突订单  
     `Order getCancleOrder(Order order)`:查询取消的订单是否存在  
     `List<Order> getAllOrder()`:查询所有订单，提供统计  
     
####cn.xiong.badminton_order.service
     本包为业务逻辑层，其中有三个方法
     `boolean addOrder(Order order)`:添加订单  
     `boolean cancleOrder(Order order)`:取消订单
     `void listInMoney()`:统计输出  
     
####cn.xiong.badminton_order.utils
本包为工具类，提供各种处理支持
#####CalMoney
     计算订单的金额
#####DataSourceUtils
     数据库连接池，获取数据库的连接
#####DateCheck
     核对日期是否正确，比如2.30等日期，需要排除，有闰年的判断
#####InputToOrder
     将正确的输入转换为Order实体类，为后续处理准备
     
     
     