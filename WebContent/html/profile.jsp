<!DOCTYPE html>
<html>
<head>
<title>My Profile</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
body {
    background-image: url("fadedIcon.jpg");
    background-repeat: no-repeat;
    background-attachment: fixed;
    background-position: center;
}
.card {  
  max-width: 500px;
  margin: auto;
  text-align: center;
  font-family: arial;
}


.heading{
  color: #787c82;
  font-size: 350%;
}

.title {
  color: grey;
  font-size: 18px;
}

.bluecolor {
  color: rgb(0,112,192);
}
.facebookcolor {
   color: #3b5998;
}
.twittercolor {
   color: #38A1F3;
}
.dribblecolor {
   color: #ea4c89;
}
.instagramcolor {
  background-image: -webkit-gradient( linear, left top, right top, 
                     color-stop(0.45, purple), color-stop(0.6, red), color-stop(0.75, pink),
                     color-stop(0.9, yellow), color-stop(1, blue) );
  color:transparent;
  -webkit-background-clip: text;
  background-clip: text;
}

button {
  border: none;
  outline: 0;
  display: inline-block;
  padding: 8px;
  color: white;
  background-color: darkgray;
  text-align: center;
  cursor: pointer;
  width: 100%;
  font-size: 18px;
}

a {
  text-decoration: none;
  font-size: 22px;
  color: black;
}

button:hover, a:hover {
  opacity: 0.7;
}
img {
  border-radius: 50%;
border: 2px solid black;
}

/* Add Zoom Animation */
.animate {
    -webkit-animation: animatezoom 2s;
    animation: animatezoom 0.6s
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

<h1 style="text-align:center" class = "heading">My Profile</h1>

<div class="card animate">

<img src="profilePicture.jpg" alt="Avatar" style="width:215px">
  
  <%
  	//HttpSession session = request.getSession();
  	String name = session.getAttribute("name").toString();
  %>
  <h1><%=name%></h1>
  <p class="title">I am a user of The Student Lounge</p>
  <p>I dont have a last name :)</p>
  <div style="margin: 35px 0;">
    <a href="#"><i class="fa fa-dribbble dribblecolor"></i></a> 
    <a href="#"><i class="fa fa-twitter twittercolor"></i></a>
    <a href="#"><i class="fa fa-instagram instagramcolor"></i></a>   
    <a href="#"><i class="fa fa-linkedin bluecolor"></i></a>  
    <a href="#"><i class="fa fa-facebook facebookcolor"></i></a> 
 </div>
 <p><button>Sometimes, I am null</button></p>
</div>

</body>
</html>