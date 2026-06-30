<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css">
</head>

<body class="min-h-screen flex items-center justify-center relative"
      style="background-color: #F0F8FF;">

<div class="absolute top-6 left-6 z-20"><img src="${pageContext.request.contextPath}/assets/img/logo.png" class="w-40 h-auto"/></div>	

<div class="relative z-10 w-full max-w-md bg-white p-8 rounded-xl shadow-xl shadow-blue-500/50 ">

    <h1 class="text-2xl font-bold text-center mb-6">
        Connexion
    </h1>

    <c:if test="${not empty authError}">
        <div class="bg-red-100 text-red-700 p-3 rounded mb-4 text-sm">
            ${authError}
        </div>
    </c:if>

    <form action="${pageContext.request.contextPath}/login" method="post" class="space-y-4">

        <div>
            <input type="email"
                   name="email"
                   value="${email}"
                   placeholder="Email"
                   class="w-full border rounded-lg p-3 focus:ring-2 focus:ring-blue-500 outline-none"/>

            <c:if test="${errors.email != null}">
                <p class="text-red-500 text-sm mt-1">${errors.email}</p>
            </c:if>
        </div>

        <div class="relative">
            <input type="password"
                   id="password"
                   name="password"
                   placeholder="Mot de passe"
                   class="w-full border rounded-lg p-3 focus:ring-2 focus:ring-blue-500 outline-none"/>
			<button
			    type="button"
			    id="togglePassword"
			    class="absolute inset-y-0 right-3 flex items-center text-gray-500 hover:text-gray-700">
			
			    <i id="passwordIcon" class="fa-solid fa-eye-slash"></i>
			</button>
            <c:if test="${errors.password != null}">
                <p class="text-red-500 text-sm mt-1">${errors.password}</p>
            </c:if>
        </div>

        <button type="submit"
                class="w-full bg-blue-600 text-white py-3 rounded-lg hover:bg-blue-700 transition">
            Se connecter
        </button>

    </form>

</div>

<script src="${pageContext.request.contextPath}/assets/js/login.js"></script>
</body>
</html>