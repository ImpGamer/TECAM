const header = document.getElementsByTagName('header')
const main = document.getElementsByTagName('main')
const footer = document.getElementsByTagName('footer')
const loader = document.getElementsByClassName("loading")
const load_text = document.getElementById('loader_text')

const frases = [
    'En mi maquina si funciona!','CSS no es mi fuerte','En lo que carga, piensa el nombre de esa variable',
    'Aprende las bases para luego romperlas','Creo que me he vuelto el copiloto','Domina las carreras del futuro','Yo queria crear videojuegos...','La programacion es convertir café en codigo','<em>Si puedes imaginarlo, puedes programarlo</em>'
]

load_text.innerHTML = `<p class="tag is-black is-medium">${frases[random_number(0,frases.length-1)]}</p>`

window.addEventListener('load', () => {
    console.log(`La página se ha cargado completamente!`)
    header[0].style.display = 'block'
    loader[0].style.display = 'none'
    load_text.style.display = 'none'
    main[0].style.display = 'block'
    load_text.style.display = 'none'
    footer[0].style.display = 'block'
})

function random_number(min,max) {
    return Math.floor(Math.random() * (max - min + 1)) + min;
}
