<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<header class="bg-white shadow h-16 flex items-center justify-between px-8 font-bold">
	<c:if test="${sessionScope.user.role eq 'ADMIN_RH'}">
	    <h2>Dashboard Admin</h2>
	</c:if>
	
	<c:if test="${sessionScope.user.role eq 'RECRUTEUR'}">
	    <h2>Dashboard Recruteur</h2>
	</c:if>

    <div class="flex items-center justify-center h-full">
        <img
            src="${pageContext.request.contextPath}/assets/img/logo.png"
            class="w-[120px] h-auto"/>
    </div>

</header>

