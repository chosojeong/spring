<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>/views/error/oopsError.jsp</title>
</head>
<body>
<h3>에러 페이지 입니다.</h3>
<p>OopsEception!</p>
<p>에러정보:
	<strong>${exception.message}</strong>
</p>
</body>
</html>