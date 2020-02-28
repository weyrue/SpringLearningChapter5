function btn1Click() {
    var json = new Object();
    json.firstName = "Yi";
    json.lastName = "Zhang";

    $.ajax({
        type: "post",
        url: "rest/singers",
        data: json,
        // contentType:"application/json;charset=utf-8",
        dataType: "json",
        success: function (result) {
            // 不知道js还是什么会自动加标签，影响预期效果。故改为拼接字符串再传给相应元素
            var tableBody = "";
            for (i = 0; i < result.length; i++) {
                tableBody += "<tr>";

                tableBody += "<td>";
                tableBody += (i + 1);
                tableBody += "</td>";

                tableBody += "<td>";
                tableBody += result[i].firstName;
                tableBody += "</td>";

                tableBody += "<td>";
                tableBody += result[i].lastName;
                tableBody += "</td>";

                tableBody += "<td>";
                var dateTime = new Date();
                dateTime.setTime(result[i].birthDate);
                var year = dateTime.getFullYear();
                var month = dateTime.getMonth() + 1 < 10 ? "0" + (dateTime.getMonth() + 1) : dateTime.getMonth() + 1;
                var date = dateTime.getDate() < 10 ? "0" + dateTime.getDate() : dateTime.getDate();
                tableBody += (year + "-" + month + "-" + date);
                tableBody += "</td>";

                tableBody += "</tr>";
            }
            $("#dataBody").append(tableBody);

        },
        error: function (response) {
            alert(response.responseText);
        }
    });
}

function btn2Click() {
    $("#dataBody").append(
        "<tr>\n" +
        "<th>No.</th>" +
        "<th>First Name</th>" +
        "<th>Last Name</th>" +
        "<th>Birth Date</th>" +
        "</tr>");
}

