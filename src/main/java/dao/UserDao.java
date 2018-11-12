package dao;

import model.Role;
import model.User;
import util.DBUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDao {

    private static final String SQl_SELECT_USER_BY_USERNAME_AND_PASSWORD = "select * from users where username=? and password=?";

    private static final String SQl_SELECT_USER_ROLE_BY_USERNAME= "select role from user_roles where username=?";

    private static final String SQl_INSERT_USER= "insert into users(username, password) values (?,?)";

    private static final String SQl_INSERT_USER_ROLES= "insert into user_roles(username, role) values (?,?)";

    private PreparedStatement preparedStatement;

    private Statement statement;

    public User findUserNotSecurely(String username,String password) {
        User user=null;
        DBUtils utils = DBUtils.getInstance();
        try {
            user=new User();
            statement=utils.getStatement();
            ResultSet rs = statement.executeQuery("select * from users where username='"+username+"' and password='"+password+"'");
            while (rs.next()){
                user.setId(rs.getInt(1));
                user.setUsername(rs.getString(2));
                user.setPassword(rs.getString(3));
            }
            statement.close();
        }
        catch (SQLException e){
                e.printStackTrace();
        }
        return user;
    }

    public User findUserSecurely(String username,String password) {
        User user = null;
        DBUtils utils = DBUtils.getInstance();
        try {
            user = new User();
            preparedStatement=utils.getPreparedStatement(SQl_SELECT_USER_BY_USERNAME_AND_PASSWORD);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                user.setId(rs.getInt(1));
                user.setUsername(rs.getString(2));
                user.setPassword(rs.getString(3));
            }
            preparedStatement.close();
            preparedStatement=utils.getPreparedStatement(SQl_SELECT_USER_ROLE_BY_USERNAME);
            preparedStatement.setString(1,username);
            rs=preparedStatement.executeQuery();
            while (rs.next()){
                user.setRole(rs.getString(1));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return user;
    }

    public boolean addUserNotSecurelly(User user){
        boolean flag=false;
        DBUtils utils = DBUtils.getInstance();
        try {
            statement=utils.getStatement();
            statement.executeUpdate
                    ("insert into users( username, password) values ('"+user.getUsername()+"','"+user.getPassword()+"') ");
            flag=true;
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;

    }

    public boolean addUserSecurelly(User user){
        boolean flag=false;
        DBUtils utils = DBUtils.getInstance();
        try {
            preparedStatement=utils.getPreparedStatement(SQl_INSERT_USER);
            preparedStatement.setString(1,user.getUsername());
            preparedStatement.setString(2,user.getPassword());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            preparedStatement=utils.getPreparedStatement(SQl_INSERT_USER_ROLES);
            preparedStatement.setString(1,user.getUsername());
            preparedStatement.setString(2,Role.USER.toString());
            flag=true;
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }
}
