const toastContainer = document.getElementById("toastContainer");

export function showToast(message, type = success) {
	
	const toast = document.createElement("div");
	
	const colors = {
		success: "bg-green-600",
        error: "bg-red-600",
        warning: "bg-yellow-500",
        info: "bg-blue-600"
	};
	
	const icons = {
       success: "fa-circle-check",
       error: "fa-circle-xmark",
       warning: "fa-triangle-exclamation",
       info: "fa-circle-info"
    };

    toast.className = `
         flex items-center gap-3
         text-white
         px-4 py-3
         rounded-lg
         shadow-lg
         ${colors[type]}
         opacity-0
         translate-x-10
         transition-all
         duration-300
     `;

     toast.innerHTML = `
         <i class="fa-solid ${icons[type]}"></i>
         <span>${message}</span>
     `;

     toastContainer.appendChild(toast);
	 
	 // Lance l'animation
     setTimeout(() => {
         toast.classList.remove("opacity-0", "translate-x-10");
     }, 10);

     // Disparition après 3 secondes
     setTimeout(() => {

         toast.classList.add("opacity-0", "translate-x-10");

         setTimeout(() => {
             toast.remove();
         }, 300);

     }, 3000);

}