<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Random" %>
<%@ page import="addOns.Book" %>
<!DOCTYPE html>
<html>
<head>
<title>Chatroom</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">


<style>
body {font-family: Arial, Helvetica, sans-serif;
    width 80%;
    height 80%
    margin: 50px;
    margin-right: auto;
    margin-left: auto;
    padding: 0 10px;
}
/* fallback */
.material-icons {
  font-family: 'Material Icons';
  font-weight: normal;
  font-style: normal;
  font-size: 24px;
  line-height: 1;
  letter-spacing: normal;
  text-transform: none;
  display: inline-block;
  white-space: nowrap;
  word-wrap: normal;
  direction: ltr;
  -webkit-font-feature-settings: 'liga';
  -webkit-font-smoothing: antialiased;
}

.buttonlink {
  font: bold 11px Arial;
  text-decoration: underline;
  background-color: rgb(224, 48, 38);
  color: white;
  padding: 4px 6px 4px 6px;
}

/*light gray boxes*/
.container {
	text-shadow:
		-1px -1px 0 #000,
		1px -1px 0 #000,
		-1px 1px 0 #000,
		1px 1px 0 #000;
	color: white;
    background-color: #f1f1f1;
    border-radius: 25px;
    padding: 5px;
    margin: 10px 0;
    font-size: 18px;
    font-weight: bold;


}

/*blue - user posts*/
.darker {
    border-color: #ccc;
    background-color: rgb(0,112,192);
    color: white;

}

/*blue - user posts*/
.darkgray {
    border-color: black;
    background-color: #b7b7b7;
    color: black;

}

.container::after {
    content: "";
    clear: both;
    display: table;
}

.time-right {
    float: right;
    color: black;
    margin-right: 30px;
}

.time-left {
    float: left;
    color: white;
    margin-left: 30px;
}

/* Full-width input fields */
input[type=text], input[type=password] {
    width: 100%;
    padding: 12px 20px;
    margin: 8px 0;
    border: 1px solid #ccc;
    box-sizing: border-box;
}

/* Set a style for submit button */
button {
    color: black;
    padding: 14px 20px;
    margin: 8px 0;
    border: none;
    cursor: pointer;
    width: 100%;
}

button:hover {
    opacity: 0.8;
}


.container {
    padding: 16px;
}

span.psw {
    float: right;
    padding-top: 16px;
}

* {
    box-sizing: border-box;
}

/* Create two unequal columns that floats next to each other */
.column {
    float: left;
    padding: 10px;
    height: 300px; /* Should be removed. Only for demonstration */
}

.left {
  width: 30%;
}

.right {
  width: 70%;
}

/* Clear floats after the columns */
.row:after {
    content: "";
    display: table;
    clear: both;
}

.example input[type=text] {
    padding: 12px 10px;
    font-size: 17px;
    border: 1px solid grey;
    float: left;
    width: 90%;
    background: #f1f1f1;
}

.example-button {
    float: left;
    width: 10%;
    padding: 8px 10px;
    background: lightgray;
    color: black;
    font-size: 17px;
    border: 1px solid grey;
    border-left: none;
    cursor: pointer;
}

.example button:hover {
    background: darkgray;
    color: white
}

.example::after {
    content: "";
    clear: both;
    display: table;
}
</style>
</head>

<body>

<div style="text-align:center">
<a class = "buttonlink" href="./html/home.jsp">Home &nbsp</a>
<a class = "buttonlink" href="./getAllRoomsServlet">All rooms &nbsp</a>
<a class = "buttonlink" href="./profile">My Profile</a>
</div>


<div class="row" style="text-align:center">
  <div style = "background-color:dark-gray;">
    <h2>My Chats</h2>

    
    <table style="width:50%" align="center">
    
    <%
   	 ArrayList <Book> rooms = new ArrayList<Book>();
    rooms = (ArrayList)request.getAttribute("roomList");
    if(rooms.isEmpty()){
    	%>
    		<p> You have not joined any chat rooms. What a loser.... :) </p>
    		<a href="./getAllRooms">Click here to join a chat room</a>
    	<%
    }
    System.out.println("Hello - here are the messages");
    System.out.println("Your user id is....");
    System.out.println(request.getSession().getAttribute("userID"));
   

    Random random = new Random();
    String letters = "0123456789ABCDEF";
    
    
    for(Book room: rooms)
    {
    	
    	String color = "#";
          for (int i = 0; i < 6; i++) {
        	int select = random.nextInt(letters.length()); 
            color += letters.charAt(select);
          }
    	%>
    	<form action="./MessageServlet" action="GET">
			<tr> 
			<td>
			<button class="container" name="room" type="submit" style="background-color:<%=color%>" value="<%=room.getIsbn() %>"><%=room.getTitle() %></button>
			</td>
			</tr>
		</form>
		<%
    	
    }    
    
    %> 
</table>
  </div>

</div>

</div>

<script>
function myFunction() {
	document.getElementByID("demo").innerHTML = "Hello";
}

// Get the modal
var model = document.getElementById('id01');

</script>

</body>
</html>