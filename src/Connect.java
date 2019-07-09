import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * Singleton!!! lol. For a connection to a database
 * @author golds
 *
 */
public class Connect {
	private static Connect uniqueConnect;
	private static Connection conn;

	private Connect() {

	}
	

	public static Connect getConnect() {
		if (uniqueConnect == null) {
			uniqueConnect = new Connect();
		}

		return uniqueConnect;
	}


   public static Connection getConnection(){
	   if(conn == null)
	   {
		   String url = "jdbc:sqlserver://localhost:1433;databaseName=HwChatRoom;integratedSecurity=true";
		   try {
		   	DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
		   	conn = DriverManager.getConnection(url);
		   } catch (SQLException e) {
		   	// TODO Auto-generated catch block
		   	e.printStackTrace();
		   }
	   }
		   return conn;
	   
	   
   }
   
	   
}
