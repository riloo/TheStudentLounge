<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
 %>
 <%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Date"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.PreparedStatement"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.List"%>
<%@ page import="addOns.Message"%>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">

<script src="https://code.jquery.com/jquery-1.10.2.js"
	type="text/javascript"></script>
<script src="js/app-ajax.js" type="text/javascript"></script>
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
    background-color: #f1f1f1;
    border-radius: 25px;
    padding: 5px;
    margin: 10px 0;
	float: left;
	width: 90%;
}
/*blue - user posts*/
.darker {
    border-color: #ccc;
    background-color: rgb(0,112,192);
    color: white;
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
    background-color: #4CAF50;
    color: white;
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
.page_bottom{
	position: fixed;
	bottom: 0;
	float: left;
	width: 90%;
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
.myBox {
border: none;
padding: 5px;
font: 20px/22px Arial;
width: 100%;
height: 70vh;
overflow: scroll;
}
/* Scrollbar styles */
::-webkit-scrollbar {
width: 12px;
height: 12px;
}
::-webkit-scrollbar-track {
border: 1px solid gray;
border-radius: 10px;
}
::-webkit-scrollbar-thumb {
background: #5b5a5a;  
border-radius: 10px;
}
::-webkit-scrollbar-thumb:hover {
background: #acf215;  
}
</style>
<script>
		function getChats(){
			var ajaxRequest = new XMLHttpRequest();
			ajaxRequest.onreadystatechange = function(){
			//window.alert("Hi there im AJAX");	
				if(ajaxRequest.readyState == 4){
					
					//the request is completed, now check its status
					if(ajaxRequest.status != 200){
						document.getElementById("myBox").innerHTML = ajaxRequest.responseText;
					}
					else{
						window.alert("in the inner else");
						console.log("Status error: " + ajaxRequest.status);
					}
				}
				else{
					window.alert("in the outer else");
					console.log("Ignored readyState: " + ajaxRequest.readyState);
				}
			}
			ajaxRequest.open('GET', '/MessageServlet');
			ajaxRequest.send();
			
			//refresh the chats in one second
			setTimeout(getChats, 1000);
		}
	
	</script>
</head>
<body>
<h2>Chat Messages</h2>
<div class="myBox" id="myBox">
<table  align = "center">
<!-- dynamically generate the messages here  -->

<%

	//set the messages into an arraylist
	ArrayList <Message> msgs = new ArrayList<Message>();
	msgs = (ArrayList) request.getAttribute("messages");
	//TEST OUTPUT, to check if we can access messages
	//System.out.println("hi. this is the message");
	//System.out.println(msgs.get(0));
	//Message mess = new Message();
	//mess.setMessage(msgs.get(0).getMessage());
	//mess.setTime(msgs.get(0).getTime());
	//mess.setUserId(msgs.get(0).getUserId());
	//mess.setFname(msgs.get(0).getFname());
	//String str = "my name";
	//System.out.println("str is of type " + str.getClass().getName());
	
	//System.out.println("Message is of type " + msgs.get(0).getClass().getName());
	for(Message mess : msgs)
	{
		%>
		<tr class = "container">
		<td><%=mess.getFname() %>: </td>
		<td><%=mess.getMessage() %></td>
		<td class="time-right"><%=mess.getTime() %></td>
		</tr>
		<%
	}
%>

</table>
 </div>


<form action="./PostMessage" method="POST" onsubmit="return clearOnSubmission">

<div class="page_bottom">

   <div class = "example">
      <input type="text" placeholder="Enter message here " name="userPost" required>       
      <button class = "example-button" type = "submit"><i class="material-icons">attachment</i></button>
   </div> 
   
    <button onClick = 'myFunction(); return false' type="submit">Submit</button>
</div>
</form>

<script>
//this DOES work!
//scroll them to bottom of page
window.onload=function () {
	getChats();
    var objDiv = document.getElementById("myBox");
    objDiv.scrollTop = objDiv.scrollHeight;
}


// Get the modal
var model = document.getElementById('id01');

</script>

</body>