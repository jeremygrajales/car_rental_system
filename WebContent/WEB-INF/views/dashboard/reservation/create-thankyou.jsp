<%@page import="java.text.DateFormat"%>
<%@page import="java.sql.Date"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ page import="car_rental_system.*" %>
<%@ include file="../../layouts/header.jsp" %>
Your reservation has been created successfully. <a href="<% out.print(request.getContextPath() + "/reservation/list"); %>">Click here</a> to be see your current reservations.  
<%@ include file="../../layouts/footer.jsp" %>