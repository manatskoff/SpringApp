<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">
<head>
	<title>Spring Boot JSP example</title>
	<link rel="stylesheet" type="text/css" href="/style.css">
</head>
<body>

     <div>Add new TAGS</div>

     <div>
          <form method="post">
                 <input type="text" name="name" placeholder="Tag"/>
                 <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                 <button type="submit">Add</button>
          </form>
     </div>

     <br/>
     <br/>

     <div>TAGS:</div>
         <br/>
         <c:forEach items="${tagsArray}" var="tag">
             <div>
                 <tr>
                 <td>${tag.name}</td>
                 </tr>
             </div>
         </c:forEach>


     <br/>
     <br/>


     <a href="/main">Main Page</a>


</body>
</html>



