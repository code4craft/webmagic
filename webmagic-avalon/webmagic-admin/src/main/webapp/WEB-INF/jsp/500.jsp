<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8" isErrorPage="true" import="java.io.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
    <title>500</title>
</head>
<body>
页面出错啦！
<%

    StringWriter stringWriter = new StringWriter();
    exception.printStackTrace(new PrintWriter(stringWriter));
    out.println(stringWriter.toString());
%>
</body>
</html>