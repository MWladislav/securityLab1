import model.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import service.UserService;

public class UserServiceTest {

    @Test
    public void testfindUserWithSQLInjection(){

        //given
        UserService service = new UserService();

        String username = "testuser";
        String password = "1234";
        //when
        User testResult = service.findUserSecurely(username,password);
        //then

        Assert.assertNotNull(testResult.getUsername());
        Assert.assertEquals(testResult.getUsername(),username);

    }

    @Test
    public void testfindUserNotSecurelyWithSQLInjection(){

        //given
        UserService service = new UserService();

        String username = "testuser'-- ";
        String password = "";
        //when
        User testResult = service.findUserNotSecurely(username,password);
        //then


        Assert.assertNotNull(testResult.getUsername());
        Assert.assertEquals(testResult.getUsername(),"testuser");
        Assert.assertEquals(testResult.getPassword(),"1234");

    }
}
