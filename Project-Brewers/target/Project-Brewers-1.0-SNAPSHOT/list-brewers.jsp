<%--
  Created by IntelliJ IDEA.
  User: nikon
  Date: 3/2/2022
  Time: 9:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<html>
<head>
    <title>Brewer App</title>

      <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>

<div id="wrapper">
    <div id="header">
        <h3> Ali Nikpay Brewer App</h3>
    </div>

</div>

<div id="container">

    <div id="content">

<%--                out new button add student--%>
            <input type="button" value=" Add Student" onclick="window.location.href='add-brewer-form.jsp';return false;"
                   class="add-student-button">


        <table>
            <tr>
                <th>Name</th>
                <th>Address</th>
                <th>Zip Code</th>
                <th>City</th>
                <th>Turn over</th>
                <th>Action</th>

            </tr>

            <c:forEach var="tempBrewer" items="${BREWER_LIST}">

                        <%--                Set up alink for each brewer--%>
                        <c:url var="tempLink" value="BrewerControllerServlet">
                          <c:param name="command" value="LOAD"/>
                          <c:param name="brewerId" value="${tempBrewer.id}"/>

                        </c:url>

                        <%--                setup a link to delet a student--%>
                        <c:url var="deleteLink" value="BrewerControllerServlet">
                          <c:param name="command" value="DELETE"/>
                          <c:param name="brewerId" value="${tempBrewer.id}"/>

                        </c:url>

                <tr>
                    <td>${tempBrewer.name}
                    </td>
                    <td>${tempBrewer.address}
                    </td>
                    <td>${tempBrewer.zipCode}
                    </td>

                    <td>${tempBrewer.city}</td>
                    <td>${tempBrewer.turnOver}</td>
                    <td><a href="${tempLink}">Update</a>
                        |
                        <a href="${deleteLink}"
                           onclick="if(!(confirm('Are you sure you want to delete this Brewer?'))) return false">Delete</a>

                    </td>

                </tr>

            </c:forEach>

        </table>
    </div>

</div>


</body>
</html>
