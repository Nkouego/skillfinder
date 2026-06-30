const passwordInput = document.getElementById("password");
const toggleButton = document.getElementById("togglePassword");
const icon = document.getElementById("passwordIcon");

toggleButton.addEventListener("click", (event) => {
    event.preventDefault(); 

    const isHidden = passwordInput.type === "password";
    passwordInput.type = isHidden ? "text" : "password";

    if (isHidden) {
        icon.classList.replace("fa-eye-slash", "fa-eye");
    } else {
        icon.classList.replace("fa-eye", "fa-eye-slash");
    }
});