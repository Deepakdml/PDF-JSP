<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>RK PDF</title>
<style>
body {
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    margin: 0;
    padding: 0;
    background-color: #5d048a; 
}

.container {
    max-width: 600px;
    margin: 50px auto;
    padding: 20px;
    background-color: #b576f5; 
    border-radius: 10px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

h2 {
    color: #333;
}

form {
    margin-top: 20px;
}

input[type="file"], input[type="text"], input[type="submit"] {
    display: block;
    width: 100%;
    margin-bottom: 10px;
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
}

input[type="submit"] {
    background-color: #210230;
    color: #fff;
    cursor: pointer;
    transition: background-color 0.3s;
}

input[type="submit"]:hover {
    background-color: #d9b5eb; 
}

input[type="text"]::placeholder {
    color: #999;
}

input[type="text"]:focus {
    border-color: #4CAF50; 
    box-shadow: 0 0 5px rgba(76, 175, 80, 0.5);
}

.background {
    background-image: url('path/to/your/image.png');
    background-size: cover;
    background-repeat: no-repeat;
    
}

</style>
</head>
<body>
    <div class="container">
        <h2>PDF File Upload and Download</h2>
        <form action="FileUploadServlet" method="post" enctype="multipart/form-data">
            <input type="file" name="pdfFile" accept=".pdf"> <input type="submit" value="Upload PDF">
        </form>
        <h3>
            <%
            if (request.getParameter("message") != null) {
            %>
            <%=request.getParameter("message")%>
            <%
            }
            %>
        </h3>
        <form action="FileDownloadServlet" method="get">
            <input type="text" name="pdf" placeholder="Enter Name Of PDF">
            <input type="submit" value="Download PDF">
        </form>
    </div>
</body>
</html>
