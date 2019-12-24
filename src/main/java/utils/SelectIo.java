package utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;


public class SelectIo{
        public  SelectIo(String ip) {
            try {
                DecimalFormat df = new DecimalFormat("0.00");
                //加载驱动程序, MySQL8的驱动与MySQL5有不一样的地方
                //MySQL5的驱动:com.mysql.jdbc.Driver
                Class.forName("com.mysql.jdbc.Driver");
                //建立连接,MySQL8任然与MySQL5不同,必须添加时区才可以:serverTimezone=GMT;GMT(GreenWich Mean Time) 格林尼治标准时间
                Connection con = DriverManager.getConnection("jdbc:mysql://172.16.2.1:3306/zabbix?serverTimezone=GMT", "zabbix", "zabbix");
                //输出下面这条语句说明连接数据库成功
                System.out.println("数据库连接成功!");
                //定义SQL语句
                //String sql = "select  hu.itemid, ho.host, ho.hostid, hu.value,from_unixtime(hu.clock)  from items it   join hosts ho on ho.hostid=it.hostid join history hu on hu.itemid=it.itemid where key_='disk.status[util]' and hu.itemid=it.itemid and ho.host='"+ip+"' order by clock desc  limit 1;";
                String sql="select d.* from (select  hu.itemid, ho.host, ho.hostid, hu.value,from_unixtime(hu.clock) curr_time  from items it   join hosts ho on ho.hostid=it.hostid join history hu on hu.itemid=it.itemid where it.key_='disk.status[util]' and hu.itemid=it.itemid order by clock desc  limit 62) d order by d.value desc;";
                //接收SQL语句
                //System.out.println(sql);
                PreparedStatement statement = con.prepareStatement(sql);
                //执行SQL语句,并接受结果集
                ResultSet rs = statement.executeQuery();
                System.out.println("-------------------------------------------------------------------------------------------");
                System.out.println("执行结果如下所示:");
                System.out.println("-------------------------------------------------------------------------------------------");
                System.out.println("项目id" + "\t" + "  ip"+ "\t" + "              主机id"+ "\t" + "磁盘百分比%"+ "\t" + "      时间");
                System.out.println("-------------------------------------------------------------------------------------------");
                String itemid = null;
                String host = null;
                String hostid = null;
                Double value = null;
                String curr_time = null;
                //rs.next()返回值为布尔型
                while (rs.next()) {
                    //| itemid | host          | hostid | value  | from_unixtime(hu.clock) |
                    itemid = rs.getString("itemid");
                    host = rs.getString("host");
                    hostid = rs.getString("hostid");
                    value = rs.getDouble("value");
                    curr_time=rs.getString("curr_time");//from_unixtime(hu.clock)

                    //输出结果
                    //System.out.println("%-10s%-10s%-10s%-10s%-10s",itemid + "\t" + host+ "\t" + hostid+ "\t" + value+ "\t" + curr_time);
                    System.out.printf("%-10s%-20s%-10s%-10s%-10s",rs.getString("itemid"),rs.getString("host"),rs.getString("hostid"),rs.getDouble("value"),rs.getString("curr_time"));
                    System.out.print("\n");
                }
                rs.close();
                statement.close();
                con.close();

            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    public static void SelectSingleIo(String ip) {

        try {
            DecimalFormat df = new DecimalFormat("0.00");
            //加载驱动程序, MySQL8的驱动与MySQL5有不一样的地方
            //MySQL5的驱动:com.mysql.jdbc.Driver
            Class.forName("com.mysql.jdbc.Driver");
            //建立连接,MySQL8任然与MySQL5不同,必须添加时区才可以:serverTimezone=GMT;GMT(GreenWich Mean Time) 格林尼治标准时间
            Connection con = DriverManager.getConnection("jdbc:mysql://172.16.2.1:3306/zabbix?serverTimezone=GMT", "zabbix", "zabbix");
            //输出下面这条语句说明连接数据库成功
            System.out.println("数据库连接成功!");
            //定义SQL语句
            //             select  hu.itemid, ho.host, ho.hostid, hu.value,from_unixtime(hu.clock)  from items it   join hosts ho on ho.hostid=it.hostid join history hu on hu.itemid=it.itemid where key_='disk.status[util]' and hu.itemid=it.itemid and ho.host='172.16.2.244'  order by clock desc  limit 1;
            String sql = "select  hu.itemid, ho.host, ho.hostid, hu.value,from_unixtime(hu.clock) curr_time  from items it   join hosts ho on ho.hostid=it.hostid join history hu on hu.itemid=it.itemid where key_='disk.status[util]' and hu.itemid=it.itemid and ho.host='" + ip + "' order by clock desc  limit 1;";
            //String sql="select d.* from (select  hu.itemid, ho.host, ho.hostid, hu.value,from_unixtime(hu.clock) curr_time  from items it   join hosts ho on ho.hostid=it.hostid join history hu on hu.itemid=it.itemid where it.key_='disk.status[util]' and hu.itemid=it.itemid order by clock desc  limit 62) d order by d.value desc;";
            //接收SQL语句
            System.out.println(sql);
            PreparedStatement statement = con.prepareStatement(sql);
            //执行SQL语句,并接受结果集
            ResultSet rs = statement.executeQuery();
            System.out.println("-------------------------------------------------------------------------------------------");
            System.out.println("执行结果如下所示:");
            System.out.println("-------------------------------------------------------------------------------------------");
            System.out.println("项目id" + "\t" + "  ip" + "\t" + "              主机id" + "\t" + "磁盘百分比%" + "\t" + "      时间");
            System.out.println("-------------------------------------------------------------------------------------------");
            String itemid = null;
            String host = null;
            String hostid = null;
            String curr_time = null;
            Double value = null;

            //rs.next()返回值为布尔型
            while (rs.next()) {
                //| itemid | host          | hostid | value  | from_unixtime(hu.clock) |
                itemid = rs.getString("itemid");
                host = rs.getString("host");
                hostid = rs.getString("hostid");
                value = rs.getDouble("value");
                curr_time = rs.getString("curr_time");//from_unixtime(hu.clock)
                //输出结果
                //System.out.println("%-10s%-10s%-10s%-10s%-10s",itemid + "\t" + host+ "\t" + hostid+ "\t" + value+ "\t" + curr_time);
                System.out.printf("%-10s%-20s%-10s%-10s%-10s", rs.getString("itemid"), rs.getString("host"), rs.getString("hostid"), rs.getDouble("value"), rs.getString("curr_time"));
                System.out.print("\n");

            }
            rs.close();
            statement.close();
            con.close();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }
}
