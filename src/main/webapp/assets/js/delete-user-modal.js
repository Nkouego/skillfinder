import { showToast } from "./toast.js";

const deleteModal = document.getElementById("deleteModal");
const deleteModalContent = document.getElementById("deleteModalContent");

let selectedUserId;
let onUserCreated;
export function openDeleteUserModal(userId) {
	selectedUserId = userId;

    deleteModal.classList.remove("hidden");

    setTimeout(() => {

        deleteModalContent.classList.remove("scale-95", "opacity-0");
        deleteModalContent.classList.add("scale-100", "opacity-100");

    }, 10);

}

export function closeDeleteUserModal() {

    deleteModalContent.classList.remove("scale-100", "opacity-100");
    deleteModalContent.classList.add("scale-95", "opacity-0");

    setTimeout(() => {

        deleteModal.classList.add("hidden");

    }, 300);

}

export function initDeleteUserModal(callback) {
	onUserCreated = callback;
	
    document.getElementById("closeDeleteModal").onclick = closeDeleteUserModal;

    document.getElementById("cancelDelete").onclick = closeDeleteUserModal;

    deleteModal.addEventListener("click", function (event) {

        if (event.target === deleteModal) {
            closeDeleteUserModal();
        }

    });

}

const deleteUserBtn = document.getElementById("deleteUserBtn");

deleteUserBtn.addEventListener("click", deleteUser);

async function deleteUser(){
	
	try {
		const response = await fetch(contextPath + "/users/" + selectedUserId, {
			    method: "DELETE",
			    headers: {
			        "Content-Type": "application/json"
			    }
			});
			
	    const result = await response.json();

	       if (response.status === 200) {

	           showToast(result.message, "success");
	           closeDeleteUserModal();
			
			await onUserCreated();

	       } else if (response.status === 404) {

	           showToast(result.message, "error");

	       } else if (response.status === 500) {
			
			showToast("Une erreur interne est survenue.", "error");
			
		   }	
		} catch(error) {
			showToast("Impossible de contacter le serveur.", "error");
	    	console.error(error);
		} 
}