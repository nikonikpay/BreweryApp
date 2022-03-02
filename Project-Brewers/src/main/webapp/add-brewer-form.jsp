<%--
  Created by IntelliJ IDEA.
  User: nikon
  Date: 3/2/2022
  Time: 10:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Brewer Form</title>

    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/add-student-style.css">
</head>
<body>

<div id="wrapper">
    <div id="header">
        <h2>Ali Nikpey Brewery</h2>
    </div>
</div>

<div id="container">
    <h3>Add Brewer</h3>
    <form action="BrewerControllerServlet" method="GET">

        <input type="hidden" name="command" value="ADD"/>

        <table>
            <tbody>
            <tr>
                <td><label>Name: </label></td>
                <td><input type="text" name="name"/></td>
            </tr>
            <tr>
                <td><label>Address: </label></td>
                <td><input type="text" name="address"/></td>
            </tr>
            <tr>
                <td><label>Zipcode: </label></td>
                <td><input type="text" name="zipCode"/></td>
            </tr>

            <tr>
                <td><label>City: </label></td>
                <td><input type="text" name="city"/></td>
            </tr>

            <tr>
                <td><label>Turnover: </label></td>
                <td><input type="text" name="turnOver"/></td>
            </tr>

            <tr>
                <td><label></label></td>
                <td><input type="submit" value="Save" class="save"/></td>
            </tr>
            </tbody>
        </table>


    </form>

    <div style="clear:both;"></div>

    <p>
        <a href="BrewerControllerServlet"> Back To List</a>
    </p>

</div>


</body>
</html>