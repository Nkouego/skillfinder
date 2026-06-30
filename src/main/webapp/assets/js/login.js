lucide.createIcons();

const passwordInput = document.getElementById("password");
const toggleButton = document.getElementById("togglePassword");

toggleButton.addEventListener("click", () => {

    if (passwordInput.type === "password") {
        passwordInput.type = "text";
    } else {
        passwordInput.type = "password";
    }

    const icon = document.getElementById("passwordIcon");

    icon.setAttribute(
        "data-lucide",
        passwordInput.type === "password" ? "eye" : "eye-off"
    );

    lucide.createIcons();
});