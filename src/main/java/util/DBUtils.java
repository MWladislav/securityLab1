package util;

import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ResourceBundle;

public class DBUtils {

    private static Logger LOG = org.apache.log4j.Logger.getLogger(DBUtils.class);

    private static DBUtils instance;

    private Connection connection;

    public static DBUtils getInstance() {
        if (instance == null) {
            instance = new DBUtils();
        }
        return instance;
    }

    private DBUtils(){
        try {
            this.connection=connect();
        }
        catch (SQLException e){
            LOG.error("Cannot create connection to database");
            e.printStackTrace();
        }
    }

    private Connection connect() throws SQLException {
        ResourceBundle resource = ResourceBundle.getBundle("database");
        String url = resource.getString("db.url");
        String user = resource.getString("db.user");
        String passwd = resource.getString("db.password");
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        return DriverManager.getConnection(url, user, passwd);
    }

    public PreparedStatement getPreparedStatement(String sql){
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
        }
        catch (SQLException e){
            LOG.error("Cannot create preparedStatement");
            e.printStackTrace();
        }
        return ps;
    }

    public Statement getStatement(){
        Statement statement = null;
        try {
            statement = connection.createStatement();
        }
        catch (SQLException e){
            LOG.error("Cannot create statement");
            e.printStackTrace();
        }
        return statement;
    }


}
