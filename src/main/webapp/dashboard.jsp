<%@ page language="java"
contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">

<title>Dashboard</title>

<link rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css">

<script src="https://cdn.tailwindcss.com"></script>

</head>

<body class="bg-gray-100">

    <jsp:include page="/components/sidebar.jsp"/>

    <div class="ml-64">

        <jsp:include page="/components/navbar.jsp"/>

        <main class="p-8">

   			 <jsp:include page="${contentPage}" />
   			 
        </main>

    </div>
    
<jsp:include page="/components/toast.jsp"/>

</body>

</html>