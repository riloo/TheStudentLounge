
import java.io.Serializable;
import java.sql.Date;


public class Message implements Serializable {

	String message;
	Date time;
	String userId; 
	String fname;
	
	public Message() {
		super();
		this.message = "";
		this.userId = "";
		this.fname = "";
		this.time = new Date(0, 0, 0);
		}
	
	@Override
	public String toString() {
		return "Message [message=" + message + ", time=" + time + ", userId="
				+ userId + ", fname=" + fname + "]";
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}

	
	

}
