const quitarNoti = document.querySelector('.delete')
const images = [
    '../assets/images/img_ritmo.jpg',
    '../assets/images/computacion.jpg',
    '../assets/images/cientificos.jpg'
]
const palabras = ['Aprende lo que desees!','Teoria y Tecnologia','Ciencia y Practica']
const textoImage = document.querySelector('#image-text')

let indexImages =0
let change_image = document.querySelector("#change-image")
const slider_arrows = document.getElementsByClassName("arrows");
console.log(slider_arrows);

function moveLeft() {
    if (indexImages > 0) {
        indexImages--;
        change_image.src = images[indexImages];
        textoImage.innerHTML = palabras[indexImages];
    }
}

function moveRight() {
    if (indexImages < images.length - 1) {
        indexImages++;
        change_image.src = images[indexImages];
        textoImage.innerHTML = palabras[indexImages];
    }
}

slider_arrows[0].addEventListener('click', moveLeft);
slider_arrows[1].addEventListener('click', moveRight);
document.addEventListener('keydown', event => {
    if (event.keyCode === 37) {
        moveLeft();
    } else if (event.keyCode === 39) {
        moveRight();
    }
});

quitarNoti.addEventListener("click",() => {
    const noti = document.querySelector('.notification')
    noti.style.display = 'none'
})