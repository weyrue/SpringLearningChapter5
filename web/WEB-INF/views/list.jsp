<!--
Created by IntelliJ IDEA.
User: Yi
Date: 2/26/2020
Time: 5:08 PM
To change this template use File | Settings | File Templates.
-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<script src="../utils/jquery-3.4.1.min.js" type="text/javascript"></script>--%>
<script src="../../js/jquery-3.4.1.min.js" type="text/javascript">
    function requestData() {
        $.ajax({
            url: "/singers",
            type: "post",
            dataType: "json",
            success: function (data) {

            },
            error: function (msg) {
                alert("ajax连接异常: " + msg);
            }
        });
    };

    function showData(data) {
        var str = "";//定义用于拼接的字符串
        for (var i = 0; i < data.length; i++) {
            //拼接表格的行和列
            str = "<tr><td>" + data[i].firstName + "</td><td>" + data[i].LastName + "</td><td></td></tr>";
            //追加到table中
            $("#dataDiv").append(str);
        }
    };
</script>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
<a>成功</a>
<button onclick="requestData()">点击这里！！！</button>

<div>
    <%--    <c:if test="${not empty singers}">--%>
    <table>
        <thead>
        <tr>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Birth Date</th>
        </tr>
        </thead>
        <tbody id="dataBody">
        <%--            <c:forEach items="${singers}" var="singer">--%>
        <%--                <tr>--%>
        <%--                    <td>${singer.firstName}</td>--%>
        <%--                    <td>${singer.LastName}</td>--%>
        <%--                    <td><fmt:formatDate value="${singer.birthDate}"/></td>--%>
        <%--                </tr>--%>
        <%--            </c:forEach>--%>
        </tbody>
    </table>
    </c:if>
</div>
</body>
</html>


