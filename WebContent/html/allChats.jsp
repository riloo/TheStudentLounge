<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Random" %>
<%@ page import="addOns.Book" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>All Chats</title>
      
<style>

.button {
	text-shadow:
		-1px -1px 0 #000,
		1px -1px 0 #000,
		-1px 1px 0 #000,
		1px 1px 0 #000;
    color: white;
    text-align: center;
    font-size: 18px;
    padding: 10px 20px;
    margin: 8px 0;
    border: 1px solid grey;
    width: 100%;
    font-weight: bold;
    font-family: Arial, Helvetica, sans-serif;
}

.container {
    padding: 16px;
}

.example input[type=text] {
    padding: 13px 12px;
    font-size: 17px;
    border: 1px solid grey;
    float: left;
    width: 50%;
    background: #030f23;
}

.example-button {
    float: left;
    color: black;
    text-align: center;
    font-size: 17px;
    padding: 11px 10px;
    margin: 8px 0;
    border: 1px solid grey;
    border-left: none;
    cursor: pointer;
    width: 100%;
    font-weight: bold;
}

.example-button:hover {
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
<h1>All Chats</h1>
<table style="width:50%" align="center">
<%
	if(request.getAttribute("cantJoin") == "true")
	{%>
		<h5>You are already in that chat room. Click <a href="./getRoomsServlet">here</a> to go to your chats</h5>
	<%}
	ArrayList <Book> rooms = new ArrayList<Book>();
	rooms = (ArrayList<Book>)request.getSession().getAttribute("AllChats");
 
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
		<tr>		
			<td>
				<div class="example">
				<p class="button" style="background-color:<%=color%>"><%=room.getTitle()%> &nbsp &nbsp ISBN: <%=room.getIsbn()%></p>
			</td>
			<td>
				<form action="./addToChat" method="post">
				
				<input type="hidden" name="isbn" value="<%=room.getIsbn()%>"></input>
				<button class="example example-button" type="submit" style="background-color:<%=color%>" value="Join">Join</button>
				
				</form>
			</div>
			</td>
		</tr>
		<%
	} 
	%>
	
	

</table>
</body>
</html>

