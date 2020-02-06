<%@ page contentType = "text/html;charset=UTF-8" language="java"%>
<html>
<body>
<h2>Hello World!</h2>
<form action = "${pageContext.request.contextPath}/boo" method = "POST">
    Login : <input type = "text" name="login"/>
    <br/>
    Email : <input type = "text" name="email"/>
    <br/>
    Password : <input type="password" name="password"/>
    <br/>
    <input type="submit" value = "Send"/>
</form>
</body>
</html>
