<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.sdapp.domain.UserMsg" %>
<jsp:useBean id="user" class="com.sdapp.domain.UserMsg"/>
<jsp:setProperty name="user" property="*"/>    
UserName: <jsp:getProperty name="user" property="userName"/>