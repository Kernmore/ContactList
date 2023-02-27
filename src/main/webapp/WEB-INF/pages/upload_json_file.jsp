<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Upload File</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <form role="form" class="form-horizontal" action="/upload" method="post" enctype="multipart/form-data">
        <h3>Upload Json File</h3>
        <br><br>
        <label for="json">Choose a file:</label>
        <input type="file" id="json" name="json" accept=".txt">
        <br>
        <input type="submit" class="btn btn-primary" value="Upload">
    </form>
</div>


<%--<form method="POST" action="/upload" enctype="multipart/form-data">--%>
<%--    <label for="json">Choose a file:</label>--%>
<%--    <br><br>--%>
<%--    <input type="submit" value="Upload">--%>
<%--</form>--%>
<%--<script>--%>
<%--    $('.selectpicker').selectpicker();--%>
<%--</script>--%>

</body>
</html>