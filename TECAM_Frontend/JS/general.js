const burgerNav = document.querySelector(".navbar-burger")
const movilMenu = document.querySelector(".movil-menu")

//Interaccion al burger-menu (Menu hamburguesa) para moviles
burgerNav.addEventListener("click",() => {
    if(!movilMenu.classList.contains("hide")) {
        movilMenu.classList.add("hide")
    } else {
        movilMenu.classList.remove("hide")
    }
})