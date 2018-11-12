import dao.MessageDao;
import model.Message;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class MessageDaoTest {

    @Test
    public void testFindAll(){
        //given
        MessageDao messageDao= new MessageDao();
        //when
        ArrayList<Message> messages = messageDao.findAll();
        //then
        for (Message message:messages) {
            System.out.println(message.getSenderUsername()+" "+ message.getText() +" "+ message.getDepartureDate());
        }
        Assert.assertNotNull(messages);
    }


    @Test
    public void testFindMessage(){
        //given
        MessageDao messageDao= new MessageDao();

        Message message = new Message("Hello World!","testuser");
        //when
        Message testResult = messageDao.findMessageByUsernameAndDepartureDate
                (message.getSenderUsername(),"02.11.2018 22:01:21");
        //then
        Assert.assertNotNull(testResult);
        Assert.assertEquals(testResult.getText(),message.getText());
        Assert.assertEquals(testResult.getSenderUsername(),message.getSenderUsername());
    }


    @Test
    public void testAddMessage(){

        MessageDao messageDao=new MessageDao();
        Message message = new Message("Hello!", "test");

        boolean test = messageDao.addMessage(message);

        Assert.assertTrue(test);
    }

    @Test
    public void testUpdateMessage(){

        MessageDao messageDao=new MessageDao();
        Message message = new Message("Hello World!!!", "testuser");
        String departureDate="02.11.2018 22:01:21";
        boolean test = messageDao.updateMessage(message,departureDate);

        Assert.assertTrue(test);
    }

    @Test
    public void testDeleteMessage(){

        MessageDao messageDao=new MessageDao();

        boolean test = messageDao.deleteMessage(1);

        Assert.assertTrue(test);
    }

}
