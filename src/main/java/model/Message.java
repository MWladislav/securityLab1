package model;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.Date;

public class Message {

    @Min(0)
    private int id;
    @Size(min = 1,max = 400, message = "Message must consist of at least 1 and no more than 400 characters")
    private String text;
    @Size(min = 4,max = 20, message = "Sender username must consist of at least 4 and no more than 20 characters")
    private String senderUsername;

    private Date departureDate;

    public Message() {
        this.departureDate=new Date();
    }

    public Message(@Size(min = 1, max = 400, message = "Message must consist of at least 1 and no more than 400 characters") String text, @Size(min = 4, max = 20, message = "Username must consist of at least 3 and no more than 20 characters") String senderUsername) {
        this.departureDate=new Date();
        this.text = text;
        this.senderUsername = senderUsername;
    }

    public String getSenderUsername() {
        return senderUsername;
    }

    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }
}
