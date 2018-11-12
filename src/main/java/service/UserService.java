package service;


import com.mysql.jdbc.StringUtils;
import dao.UserDao;
import model.User;
import org.apache.log4j.Logger;
import util.DBUtils;

public class UserService {

    private static Logger LOG = org.apache.log4j.Logger.getLogger(UserService.class);

    private final UserDao userDao = new UserDao();

    public User findUserSecurely(String username, String password){
        if (StringUtils.isNullOrEmpty(username))
            return null;
        User user = userDao.findUserSecurely(username,password);
        if(user.getPassword()==null || user.getUsername()==null)
            return null;
        return user;
    }

    public User findUserNotSecurely(String username,String password){
        return userDao.findUserNotSecurely(username,password);
    }

}
