<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Home</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {font-family: Arial, Helvetica, sans-serif;
    background-image: url("images/fadedIcon.jpg");
    background-repeat: no-repeat;
    background-attachment: fixed;
    background-position: center;
}

.content {
    text-align: center;
    margin: 100px;
    padding: 10px;
}

/* make logo and header on same line */
.header img {
    float: left;
 
    background: #555;
}

.header h1 {
    position: relative;
    top: 20px;
    left: 0px;
}

/* red buttons */
buttonred{
    background-color:rgb(224, 48, 38);
    color: white;
    padding: 20px 20px;
    margin: 8px 0;
    border: none;
    cursor: pointer;
    width: 100%;
}

buttonred:hover {
    opacity: 0.8;
}

/* blue buttons */
buttonblue{
    background-color: rgb(0,112,192);
    color: white;
    padding: 20px 20px;
    margin: 8px 0;
    border: none;
    cursor: pointer;
    width: 100%;
}

buttonblue:hover {
    opacity: 0.8;
}

/* green buttons */
buttongreen{
    background-color:rgb(76, 175, 80);
    color: white;
    padding: 20px 20px;
    margin: 8px 0;
    border: none;
    cursor: pointer;
    width: 100%;
}

buttongreen:hover {
    opacity: 0.8;
}

/* Center the image and position the close button */
.imgcontainer {
    text-align: center;
    margin: 30px 0 30px 0;
    position: relative;
}

img.avatar {
    width: 35%;
    border-radius: 8px;
}

.container {
    padding: 16px;
}

span.psw {
    float: right;
    padding-top: 16px;
}

/* Add Zoom Animation */
.animate {
    -webkit-animation: animatezoom 0.9s;
    animation: animatezoom 0.9s
}

@-webkit-keyframes animatezoom {
    from {-webkit-transform: scale(0)} 
    to {-webkit-transform: scale(1)}
}
    
@keyframes animatezoom {
    from {transform: scale(0)} 
    to {transform: scale(1)}
}

</style>
</head>

<body>

<div class = "content header">
  <h1>Home</h1>
    <br><br><br>
</div>

<%
	//some testing to see if the userid was right
	System.out.println("Some testing in home.jsp now. You userid here is" + request.getSession().getAttribute("userID"));
 %>
<div class = "content animate">
  <table style = "width:67%" align = "center">
  <br><br>
    <tr>
      <th></th>
      <th><buttongreen onclick="location.href='/DBtesting/getAllRooms';".style.display='block' 
              style="width:auto;">All Chats</buttongreen></th>
      <th><buttonred onclick="location.href='/DBtesting/getRoomsServlet';".style.display='block' 
              style="width:auto;">My Chats</buttonred></th>
      <th><buttonblue onclick="location.href='./profile.html';".style.display='block' 
              style="width:auto;">My Profile</buttonblue></th>
      <th></th>
    </tr>
  </table>
</div>






</body>
</html>
