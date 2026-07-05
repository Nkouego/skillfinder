<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class="bg-white rounded-xl shadow p-6">
	

    <div class="flex items-center justify-between mb-6">

        <h2 class="text-2xl font-bold text-gray-700">
           Utilisateurs
        </h2>

        <div class="flex items-center gap-3">

           <div class="relative">

			    <div class="absolute left-0 top-0 h-full w-12 bg-[#D4AF37] rounded-l-lg flex items-center justify-center cursor-pointer">
			
			        <i class="fa-solid fa-magnifying-glass text-white"></i>
			
			    </div>
	
			    <input
			        type="text"
			        placeholder="Rechercher un utilisateur..."
			        class="bg-gray-100 pl-14 pr-4 py-2 w-72 border border-gray-300 rounded-lg focus:outline-none">
			
		  </div>

            <button id="openAddModal"
               href="#"
               class="bg-blue-600 hover:bg-blue-700 text-white px-4 py-2 rounded-lg">

                <i class="fa-solid fa-plus mr-2"></i>

                Ajouter un utilisateur

            </button>

        </div>

    </div>

    <table class="w-full">

        <thead class="border-b">

            <tr class="text-left text-gray-600">

                <th class="py-3">Nom</th>
                <th>Email</th>
                <th>Role</th>
                <th>Date de creation</th>
                <th class="text-center">Actions</th>

            </tr>

        </thead>

        <tbody id = "usersTableBody">

        </tbody>

    </table>

</div>

<script>
    const contextPath = "${pageContext.request.contextPath}";
</script>

<jsp:include page="/components/modals/add-user-modal.jsp"/>

<jsp:include page="/components/modals/update-user-modal.jsp"/>

<jsp:include page="/components/modals/delete-user-modal.jsp"/>

<script type="module" src="${pageContext.request.contextPath}/assets/js/user.js"></script>

