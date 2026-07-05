import { showToast } from "./toast.js";
import { clearValidationErrors, displayValidationErrors } from "./validate.js";

const updateModal = document.getElementById("updateModal");
const updateModalContent = document.getElementById("updateModalContent");
const updateButton = document.getElementById("updateUserBtn")

let selectedUserId = null;
let onUserCreated;

export async function openUpdateUserModal(userId) {
	clearValidationErrors();
	
	selectedUserId = userId;
	
	const response = await fetch(contextPath + "/users/" + userId, {
	    method: "GET",
	    headers: {
	        "Content-Type": "application/json"
	    }
	});

	const user = await response.json();
	
	document.getElementById("updateFullName").value = user.fullName;
	document.getElementById("updateEmail").value = user.email;
	document.getElementById("updateRole").value = user.role;
	document.getElementById("updatePassword").value = "";
	
	updateModal.classList.remove("hidden");

	    setTimeout(() => {

	        updateModalContent.classList.remove("scale-95", "opacity-0");
	        updateModalContent.classList.add("scale-100", "opacity-100");

	    }, 10);

}

export function closeUpdateUserModal() {

    updateModalContent.classList.remove("scale-100", "opacity-100");
    updateModalContent.classList.add("scale-95", "opacity-0");

    setTimeout(() => {

        updateModal.classList.add("hidden");

    }, 300);

}

export function initUpdateUserModal(callback) {
	onUserCreated = callback;
	
    document.getElementById("closeUpdateModal").onclick = closeUpdateUserModal;

    document.getElementById("cancelUpdate").onclick = closeUpdateUserModal;

    updateModal.addEventListener("click", function (event) {

        if (event.target === updateModal) {
            closeUpdateUserModal();
        }

    });

}

const updateUserForm = document.getElementById("updateUserForm");

updateUserForm.addEventListener("submit", updateUser);

async function updateUser(event) {
	event.preventDefault();
	
	updateButton.disabled = true;
	   updateButton.innerHTML = `
	       <i class="fa-solid fa-spinner fa-spin mr-2"></i>
	       Saving...
	   `;
	   
	const user = {
	    id: selectedUserId,
	    fullName: document.getElementById("updateFullName").value.trim(),
	    email: document.getElementById("updateEmail").value,
	    password: document.getElementById("updatePassword").value,
	    role: document.getElementById("updateRole").value
	};
	
	try {
	const response = await fetch(contextPath + "/users/" + selectedUserId, {
		    method: "PUT",
		    headers: {
		        "Content-Type": "application/json"
		    },
			body: JSON.stringify(user)
		});
		
    const result = await response.json();

       if (response.status === 200) {

           showToast(result.message, "success");
           closeUpdateUserModal();
		
		await onUserCreated();

       } else if (response.status === 409) {

           showToast(result.message, "error");

       } else if (response.status === 400) {
		
          displayValidationErrors(result);

       } else if (response.status === 500) {
		
		showToast("Une erreur interne est survenue.", "error");
		
	   }	
	} catch(error) {
		showToast("Impossible de contacter le serveur.", "error");
    	console.error(error);
	} finally {

        updateButton.disabled = false;
        updateButton.innerHTML = `
            Sauvegarder
        `;

    }
	

		
	
	
}

