import { initAddUserModal } from "./add-user-modal.js";
import { initUpdateUserModal, openUpdateUserModal } from "./update-user-modal.js";
import { initDeleteUserModal, openDeleteUserModal } from "./delete-user-modal.js";

const tbody = document.getElementById("usersTableBody");
tbody.addEventListener("click", handleTableClick);

initAddUserModal(loadUsers);
initUpdateUserModal(loadUsers);
initDeleteUserModal(loadUsers);

loadUsers();

export async function loadUsers() {

    const response = await fetch(contextPath + "/users", {
        method: "GET",
        headers: {
            "Content-Type": "application/json"
        }
    });

    const users = await response.json();

    renderUsers(users);

}

function renderUsers(users) {

    let html = "";

    for (const user of users) {

        html += `
            <tr class="border-b hover:bg-gray-50">

                <td class="py-4">
                    ${user.fullName}
                </td>

                <td>
                    ${user.email}
                </td>

                <td>
                    <span class="px-3 py-1 rounded-full bg-slate-200 text-sm">
                        ${user.role}
                    </span>
                </td>

                <td>
					${user.createdAt}
                </td>

                <td class="text-center">

                    <a href="#" data-id="${user.id}" class="open-update-modal text-blue-600 mr-3">
                        <i class="fa-solid fa-pen-to-square"></i>
                    </a>

                    <a href="#" data-id="${user.id}" class="open-delete-modal text-red-600">
                        <i class="fa-solid fa-trash"></i>
                    </a>

                </td>

            </tr>
        `;
    }

    tbody.innerHTML = html;

}

function handleTableClick(event) {

    const updateButton = event.target.closest(".open-update-modal");

    if (updateButton) {

        event.preventDefault();

        const userId = updateButton.dataset.id;

		openUpdateUserModal(userId);
		
		return;
    }
	
	const deleteButton = event.target.closest(".open-delete-modal");
	
	if (deleteButton) {

	        event.preventDefault();

	        const userId = deleteButton.dataset.id;

			openDeleteUserModal(userId);
			
			return;
	    }

}