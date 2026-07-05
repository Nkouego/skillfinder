<div id="addUserModal"
     class="flex fixed inset-0 bg-black/50 hidden items-center justify-center z-50 transition-opacity duration-300">

    <div id="addUserModalContent"
         class="bg-white rounded-xl shadow-xl w-full max-w-lg mx-4
                transform scale-95 opacity-0
                transition-all duration-300 p-5">

        <div class="flex justify-between items-center mb-6">

            <h2 class="text-2xl font-bold">
                Ajouter un utilisateur
            </h2>

            <button
                id="closeAddModal"
                class="text-white bg-red-500 text-2xl px-4 py-2">

                &times;

            </button>

        </div>

        <form id="addUserForm" autocomplete="off">

            <div class="mb-4">

                <label class="block mb-2">
                    Nom complet
                </label>

                <input
                	required
                    type="text"
                    id="fullName"
                    class="w-full border rounded-lg p-3 focus:ring-2 focus:ring-blue-500 outline-none">

           	    <small id="fullNameError"
                       class="text-red-500"></small>
                       
            </div>

            <div class="mb-4">

                <label class="block mb-2">
                    Email
                </label>

                <input
                	required
                	autocomplete="off"
                    type="email"
                    id="email"
                    class="w-full border rounded-lg p-3 focus:ring-2 focus:ring-blue-500 outline-none">
                    
				 <small id="emailError"
                       class="text-red-500"></small>
            </div>

            <div class="mb-4">

                <label class="block mb-2">
                    Mot de passe
                </label>

                <input
                	required
                	autocomplete="off"
                    type="password"
                    id="password"
                    class="w-full border rounded-lg p-3 focus:ring-2 focus:ring-blue-500 outline-none">
                    
                 <small id="passwordError"
                       class="text-red-500"></small>

            </div>

            <div class="mb-6">

                <label class="block mb-2">
                    Role
                </label>

                <select
                    id="role"
                    class="w-full border rounded-lg p-3 cursor-pointer focus:outline-none">

                    <option value="ADMIN_RH">Admin_RH</option>
                    <option value="RECRUTEUR">Recruteur</option>
                </select>
				<small id="roleError"
                       class="text-red-500"></small>
            </div>

            <div class="flex justify-end gap-3">

                <button
                    type="button"
                    id="cancelAdd"
                    class="px-4 py-2 bg-gray-200 rounded-lg">

                    Annuler

                </button>

                <button
                	id="saveUserBtn"
                    type="submit"
                    class="px-4 py-2 bg-blue-600 text-white rounded-lg">

                   Sauvegarder

                </button>

            </div>

        </form>

    </div>

</div>