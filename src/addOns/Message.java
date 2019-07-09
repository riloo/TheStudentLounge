package addOns;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class Message implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7192464520280468444L;
	String message;
	Timestamp time;
	String userId; 
	String fname;
	
	@SuppressWarnings("deprecation")
	public Message() {
		super();
		this.message = "";
		this.userId = "";
		this.fname = "";
		this.time = new Timestamp(0, 0, 0, 0, 0, 0, 0);
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
	public Timestamp getTime() {
		return time;
	}
	public String getNormalizedDate(){
		//get the date
		int month = time.getMonth() + 1;
		int date = time.getDate();
		
		
		//get the time
		String AMorPM;
		int hours = time.getHours();
		//check if its am
		if(hours >= 0 && hours <= 11){
			AMorPM = "am";
		}
		else{
			AMorPM = "pm";
		}
		
		//change hours to be in twelve hour form
		hours = hours % 12;
		if(hours == 0){
			hours = 12;
		}
		
		String dateToReturn;
		Integer minutes = time.getMinutes();
		String stringMin;
		if(minutes < 10){
			stringMin = "0" + minutes;
		}
		else{
			stringMin = minutes.toString();
		}
		
		dateToReturn = month + "/" + date+ " " + hours + ":" + stringMin  + AMorPM;
		return dateToReturn;
	}
	public String timeString(){
		return time.getDay() + "/" + time.getMonth() + "/" + time.getYear() + "\t" + time.getHours() + ":" + time.getMinutes(); 
	}
	public void setTime(Timestamp time) {
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
