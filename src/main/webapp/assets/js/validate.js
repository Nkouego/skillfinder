export function displayValidationErrors(errors) {

    for (const field in errors) {

        const errorElement = document.getElementById(field + "Error");

        if (errorElement) {
            errorElement.textContent = errors[field];
        }

    }

}

export function clearValidationErrors() {

    document.getElementById("fullNameError").textContent = "";
    document.getElementById("emailError").textContent = "";
    document.getElementById("passwordError").textContent = "";
    document.getElementById("roleError").textContent = "";

}