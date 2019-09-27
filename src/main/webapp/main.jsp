<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">
<head>
	<title>Spring Boot JSP example</title>
	<link rel="stylesheet" type="text/css" href="/style.css">
</head>
<body>

    <c:set var="myName" value="JSTL --- Testing Create Var"/>
    ${myName}


    <br/>
    <br/>


    <div>
    <form action="logout" method="post">
        <input type="submit" value="Exit"/>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />    </form>
    </div>


    <br/>
    <a href="/addtags">Add new TAG</a>
    <br/>
    <br/>


    <div>
    Loading New File:
        <form method="post" enctype="multipart/form-data">
            <input type="text" name="text" placeholder="File Name"/>
            <select name="tag">
                <c:forEach items="${tagsArray}" var="tag">
                    <option>${tag.name}</option>
                </c:forEach>
            </select>
            <input type="file" name="file"/>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            <button type="submit">Load File</button>
        </form>
    </div>


    <br/>
    <br/>




    <div>Filter by TAG:</div>
    <form method="post" action="filter">
        <input type="text" name="filter">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        <button type="submit">Find</button>
    </form>


    <br/>
    <br/>



    <div>Files:</div>
    <br/>
    <table>
        <tr bgcolor="#aaaaaa">
            <td width="200">File Name</td>
            <td width="200">Tag</td>
            <td width="200">Author</td>
            <td width="200">Download Link</td>
            <td>            Configuration</td>
         </tr>
    <c:forEach items="${fileItemArray}" var="file">
            <tr>
            <td>${file.text}</td>
            <td>${file.tag}</td>
            <td><strong>${file.authorName}</strong></td>


                    <!-- Отображаем ссылку на скачивание файла -->
                    <c:if test="${file.authorName eq currentUser}">
                        <td><a href="/img/${file.filename}">Download</a></td>
                    </c:if>
                    <c:if test="${file.authorName ne currentUser}">
                        <c:forEach items="${file.listAuthorizedUsers}" var="item">
                            <c:if test="${item eq currentUser}">
                                <td><a href="/img/${file.filename}">Download</a></td>
                            </c:if>
                        </c:forEach>
                    </c:if>


            <!-- Если автор файла совпадает с текущим пользователем - предостовляем возможность настроить доступ к файлу -->
            <c:if test="${file.authorName eq currentUser}">
                <td><a href="/fileconfig?fileId=${file.id}">Set Permissions</a></td>
            </c:if>

            </tr>
    </c:forEach>
    </table>



</body>
</html>





