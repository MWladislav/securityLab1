import dao.UserDao;
import model.Role;
import model.User;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class UserDaoTest {

    @Test
    public void testFindUser(){
        //given
        UserDao userDao= new UserDao();

        User user = new User("testuser","1234",Role.USER);
        //when
        User testResult = userDao.findUserSecurely(user.getUsername(),user.getPassword());
        //then
        Assert.assertNotNull(testResult);
        Assert.assertEquals(testResult.getUsername(),user.getUsername());
        Assert.assertEquals(testResult.getPassword(),user.getPassword());
        Assert.assertEquals(testResult.getRole(),user.getRole());
    }

    @Test
    public void test(){
        Date date=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        try {
            System.out.println(simpleDateFormat.parse("11.11.1234 22:22:22"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(date.toString());
        System.out.println(simpleDateFormat.format(date));
        System.out.println(date.getTime());
    }


    @Test
    public void testInsertUser(){
        //given
        UserDao userDao= new UserDao();

        User user = new User("testuser1","12345",Role.USER);
        //when
        boolean flag = userDao.addUserSecurelly(user);
        //then
        Assert.assertTrue(flag);
    }

}
