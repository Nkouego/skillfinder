import { showToast } from "./toast.js";
import { clearValidationErrors, displayValidationErrors } from "./validate.js";

// ouverture/fermeture du formulaire
const addUserModal = document.getElementById("addUserModal");
const addUserModalContent = document.getElementById("addUserModalContent");
const saveButton = document.getElementById("saveUserBtn");
let onUserCreated;

export function initAddUserModal(callback){

	onUserCreated = callback;
	
	document .getElementById("openAddModal").onclick =  openAddUserModal;

	document.getElementById("closeAddModal").onclick = closeAddUserModal;

	document.getElementById("cancelAdd").onclick = closeAddUserModal;


	addUserModal.addEventListener("click", function (event) {

	    if (event.target === addUserModal) {
	       closeAddUserModal();
	    }

	});
}


export function openAddUserModal() {
	addUserForm.reset();
    addUserModal.classList.remove("hidden");

    setTimeout(() => {

        addUserModalContent.classList.remove("scale-95", "opacity-0");
        addUserModalContent.classList.add("scale-100", "opacity-100");

    }, 10);

}

export function closeAddUserModal() {
	clearValidationErrors();
	
    addUserModalContent.classList.remove("scale-100", "opacity-100");
    addUserModalContent.classList.add("scale-95", "opacity-0");

    setTimeout(() => {

        addUserModal.classList.add("hidden");

    }, 300);

}

// Envoie des donnees du formulaire
const addUserForm = document.getElementById("addUserForm");

addUserForm.addEventListener("submit", saveUser);

async function saveUser(event) {
    event.preventDefault();
	
	clearValidationErrors();

    saveButton.disabled = true;
    saveButton.innerHTML = `
        <i class="fa-solid fa-spinner fa-spin mr-2"></i>
        Saving...
    `;

    try {

        const user = {
            fullName: document.getElementById("fullName").value.trim(),
            email: document.getElementById("email").value.trim(),
            password: document.getElementById("password").value,
            role: document.getElementById("role").value
        };

        const response = await fetch(contextPath + "/users", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(user)
        });

        const result = await response.json();

        if (response.status === 201) {

            showToast(result.message, "success");
            closeAddUserModal();
            addUserForm.reset();
			
			await onUserCreated();

        } else if (response.status === 409) {

            showToast(result.message, "error");

        } else if (response.status === 400) {
			
           displayValidationErrors(result);

        } else if (response.status === 500) {
			showToast("Une erreur interne est survenue.", "error");
		}

    } catch (error) {

        showToast("Impossible de contacter le serveur.", "error");
        console.error(error);

    } finally {

        saveButton.disabled = false;
        saveButton.innerHTML = `
            Sauvegarder
        `;

    }
	
}





