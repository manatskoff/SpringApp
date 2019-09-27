<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">
<head>
	<title>Spring Boot JSP example</title>
	<link rel="stylesheet" type="text/css" href="/style.css">
</head>
<body>


    ${message_ok}


    <br/>
    <br/>




    <form method="post" action="fileconfig?fileId=${fileId}">
        <c:forEach items="${usersArray}" var="usr">
            <input type="checkbox" name="checkboxUsers" value=${usr.username}>${usr.username}<Br>
        </c:forEach>
        <p><button type="submit">add</button></p>
    </form>



    <br/>
    <br/>


    <a href="/main">Main Page</a>

</body>
</html>



