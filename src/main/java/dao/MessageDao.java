package dao;

import model.Message;
import util.DBUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MessageDao {

    private static final String SQL_SELECT_ALL_MESSAGES = "select * from messages";

    private static final String SQL_SELECT_MESSAGE_BY_SENDER_USERNAME_AND_DEPARTURE_DATE = "select * from messages where senderUsername=? and departureDate=?";

    private static final String SQL_INSERT_MESSAGE = "insert into messages(senderUsername, text, departureDate) values (?,?,?)";

    private static final String SQL_UPDATE_MESSAGE = "update messages set text=?, departureDate=? where senderUsername=? and departureDate=?";

    private static final String SQL_DELETE_MESSAGE = "delete from messages where messageId=?";

    private PreparedStatement preparedStatement;

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    public ArrayList<Message> findAll(){
        ArrayList<Message> messages = new ArrayList<>();
        DBUtils utils = DBUtils.getInstance();
        try {
            preparedStatement=utils.getPreparedStatement(SQL_SELECT_ALL_MESSAGES);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Message message=new Message();
                message.setId(rs.getInt(1));
                message.setSenderUsername(rs.getString(2));
                message.setText(rs.getString(3));
                message.setDepartureDate(dateFormat.parse(rs.getString(4)));
                messages.add(message);
            }
        }
        catch (ParseException | SQLException e) {
            e.printStackTrace();
        }
        return messages;
    }

    public Message findMessageByUsernameAndDepartureDate(String senderUsername, String departureDate) {
        Message message = null;
        DBUtils utils = DBUtils.getInstance();
        try {
            message = new Message();
            preparedStatement = utils.getPreparedStatement(SQL_SELECT_MESSAGE_BY_SENDER_USERNAME_AND_DEPARTURE_DATE);
            preparedStatement.setString(1, senderUsername);
            preparedStatement.setString(2, departureDate);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                message.setId(rs.getInt(1));
                message.setSenderUsername(rs.getString(2));
                message.setText(rs.getString(3));
                message.setDepartureDate(dateFormat.parse(rs.getString(4)));
            }
            preparedStatement.close();
        } catch (ParseException | SQLException e) {
            e.printStackTrace();
        }
        return message;
    }

    public boolean addMessage(Message message) {
        boolean flag = false;
        DBUtils utils = DBUtils.getInstance();
        try {
            preparedStatement = utils.getPreparedStatement(SQL_INSERT_MESSAGE);
            preparedStatement.setString(1, message.getSenderUsername());
            preparedStatement.setString(2, message.getText());
            preparedStatement.setString(3, dateFormat.format(message.getDepartureDate()));
            preparedStatement.executeUpdate();
            flag = true;
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public boolean updateMessage(Message message,String departureDate) {
        boolean flag = false;
        DBUtils utils = DBUtils.getInstance();
        try {
            preparedStatement = utils.getPreparedStatement(SQL_UPDATE_MESSAGE);
            preparedStatement.setString(1, message.getText());
            preparedStatement.setString(2, dateFormat.format(message.getDepartureDate()));
            preparedStatement.setString(3, message.getSenderUsername());
            preparedStatement.setString(4, departureDate);
            preparedStatement.executeUpdate();
            flag = true;
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public boolean deleteMessage(int messageId){
        boolean flag = false;
        DBUtils utils = DBUtils.getInstance();
        try {
            preparedStatement = utils.getPreparedStatement(SQL_DELETE_MESSAGE);
            preparedStatement.setInt(1, messageId);
            preparedStatement.executeUpdate();
            flag = true;
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

}
