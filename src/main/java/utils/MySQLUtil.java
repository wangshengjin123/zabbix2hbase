package utils;
import java.sql.Connection;
import java.sql.DriverManager;
public class MySQLUtil {
    public static Connection getConnection(String driver, String url, String user, String password) {
        Connection con = null;
        try {
            Class.forName(driver);
            //注意，这里替换成你自己的mysql 数据库路径和用户名、密码
            con = DriverManager.getConnection("jdbc:mysql://172.17.2.1:3306/zabbix?useUnicode=true&characterEncoding=UTF-8", "zabbix", "zabbix");
        } catch (Exception e) {
            System.out.println("-----------mysql get connection has exception , msg = "+ e.getMessage());
        }
        return con;
    }
}
