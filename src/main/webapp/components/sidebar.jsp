<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<aside class="fixed top-0 left-0 h-screen w-64 bg-slate-900 text-white shadow-xl flex flex-col">

    <!-- Profil -->
    <div class="flex flex-col items-center justify-center py-8 border-b border-slate-700">

        <div class="w-16 h-16 rounded-full bg-blue-600 flex items-center justify-center text-2xl font-bold">
            ${sessionScope.user.fullName.substring(0,1)}
        </div>

        <p class="mt-4 text-lg font-semibold">
            ${sessionScope.user.fullName}
        </p>

        <p class="text-sm text-slate-400">
            ${sessionScope.user.role}
        </p>

    </div>

    <!-- Menu -->
    <nav class="flex-1 px-4 py-6 space-y-2">
		<c:if test="${sessionScope.user.role eq 'ADMIN_RH'}">
			
	        <a href="${pageContext.request.contextPath}/dashboard?section=users"
	           class="flex items-center gap-3 px-4 py-3 rounded-lg hover:bg-slate-800 transition-all duration-200">
	
	            <i class="fa-solid fa-user text-[#D4AF37]"></i>
	            <span>Utilisateurs</span>
	
	        </a>
        
		</c:if>
		
        <a href="${pageContext.request.contextPath}/dashboard"
           class="flex items-center gap-3 px-4 py-3 rounded-lg hover:bg-slate-800 transition-all duration-200">

            <i class="fa-solid fa-house text-[#D4AF37]"></i>
            <span>Dashboard</span>
	    </a>
	    
        <a href="${pageContext.request.contextPath}/dashboard?section=users"
           class="flex items-center gap-3 px-4 py-3 rounded-lg hover:bg-slate-800 transition-all duration-200">

            <i class="fa-solid fa-search text-[#D4AF37]"></i>
            <span>Profils candidats</span>

        </a>

    </nav>

    <!-- Déconnexion -->
    <div class="p-4 border-t border-slate-700">

        <a href="${pageContext.request.contextPath}/logout"
           class="flex items-center justify-center gap-2 w-full bg-red-600 hover:bg-red-700 py-3 rounded-lg transition-all duration-200">

            <i class="fa-solid fa-right-from-bracket"></i>
            <span>Deconnexion</span>

        </a>

    </div>

</aside>