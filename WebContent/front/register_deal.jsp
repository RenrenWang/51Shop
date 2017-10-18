<%@page import="com.dao.MemberDaoImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.tools.ConnDB"%>
<%@page import="java.sql.ResultSet"%>
    <jsp:useBean id="member" scope="request" class="com.model.Member">
             <jsp:setProperty name="member"  property="*"/>
    </jsp:useBean>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
    <%
      request.setCharacterEncoding("UTF-8");
      String username=member.getUsername();
   
      ConnDB conn= new ConnDB();
      ResultSet  rs=conn.executeQuery("select * from  tb_Member where username='"+username+"'");
  
     
   if(rs.next()){
	  
  	out.print("<script>alert('"+username+",该 账号已存在，请重新注册');"+"window.location.href='register.jsp'"+";</script>");
   }else{
	   int ret=0;
	   MemberDaoImpl  MeberClass=  new  MemberDaoImpl();
	   ret=MeberClass.insert(member);
	   out.println(ret);
	   if(ret!=0){
		   session.setAttribute("username", username);
		   out.println("<script language='javascript'>alert('"+username+",注册成功');"+"window.location.href='index.jsp'"+";</script>");
		   
	   }else{
		   out.println("<script>alert('重新注册失败,请重新注册');"+"window.location.href='register.jsp'"+";</script>"); 
	   }
		
   }
    %>  
</body>
</html>