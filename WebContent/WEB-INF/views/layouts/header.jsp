<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Thunder Chickens Car Rental Service</title>
<link rel="stylesheet" type="text/css" href="<% out.print(request.getContextPath() + "/css/style.css"); %>">
</head>
<body>
<div id="wrapper">
<div id="header">
	<h1>Thunder Chickens Car Rental Service</h1>
</div>
<div id="navigation">
	<ul>
	<li><a href="<% out.print(request.getContextPath() + "/home"); %>">Home</a></li>
	<li><a href="<% out.print(request.getContextPath() + "/user/create"); %>">Create User</a></li>
	<li><a href="<% out.print(request.getContextPath() + "/user/login"); %>">Login</a></li>	
	</ul>
</div>
<div id="content">

