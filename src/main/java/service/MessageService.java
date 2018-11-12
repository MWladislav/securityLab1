package service;

import com.mysql.jdbc.StringUtils;
import dao.MessageDao;
import model.Message;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MessageService {

    private final MessageDao messageDao = new MessageDao();

    public ArrayList<Message> findAll(){
        return messageDao.findAll();
    }

    public Message findMessageByUsernameAndDepartureDate(String username, Date departureDate){
        if (StringUtils.isNullOrEmpty(username))
            return null;
        SimpleDateFormat dateFormat=new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        Message message=messageDao.findMessageByUsernameAndDepartureDate(username,dateFormat.format(departureDate));
        if(message.getText()==null || message.getDepartureDate()==null || message.getSenderUsername()==null)
            return null;
        return message;
    }

    public boolean addMessage(Message message) {
        return (message != null && StringUtils.isNullOrEmpty(message.getText())
                && StringUtils.isNullOrEmpty(message.getSenderUsername())) && messageDao.addMessage(message);
    }

    public boolean updateMessage(String senderUsername, String text, String departureDate){
        if (StringUtils.isNullOrEmpty(senderUsername) || StringUtils.isNullOrEmpty(text) || StringUtils.isNullOrEmpty(departureDate))
            return false;
        Message message = new Message(text,senderUsername);
        return messageDao.updateMessage(message,departureDate);
    }

    public boolean deleteMessage(int messageId){
        return messageId>0 && messageDao.deleteMessage(messageId);
    }
}
