<div id="deleteModal"
     class="flex fixed inset-0 bg-black/50 hidden items-center justify-center z-50 transition-opacity duration-300">

    <div id="deleteModalContent"
         class="bg-white rounded-xl shadow-xl w-full max-w-lg mx-4
                transform scale-95 opacity-0
                transition-all duration-300 p-5">

        <div class="flex justify-between items-center mb-6">
		    <h2 class="text-2xl font-bold">
		        Confirmer la suppression
		    </h2>
		
		    <button
		        id="closeDeleteModal"
		        class="text-white bg-red-500 text-2xl px-4 py-2">
		        &times;
		    </button>
		</div>
        <p class="text-gray-600 mb-6">
		    Êtes-vous sûr de vouloir supprimer cet utilisateur ?
		    Cette action est irréversible.
		</p>
		<div class="flex justify-between gap-3">
			 <button
                    type="button"
                    id="cancelDelete"
                    class="px-4 py-2 bg-gray-200 rounded-lg">

                    Annuler

                </button>

                <button
                	id="deleteUserBtn"
                    type="submit"
                    class="px-4 py-2 bg-red-500 text-white rounded-lg">

                  Supprimer

                </button>
		</div>    

    </div>

</div>