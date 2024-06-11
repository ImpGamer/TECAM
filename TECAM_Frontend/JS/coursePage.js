import Curso from './Curso.js'
import CourseApi from './api-response.js'
const textContainer = document.getElementById('text-container')
const imageContainer = document.getElementById('imageContainer')
const titlePage = document.getElementsByTagName('title')[0]


const URL = window.location.search

async function main() {
    const cursoData = await getCourseID()
    if(cursoData == undefined) {
        window.location.href = '../404.html'
    }
    const curso = new Curso(
        cursoData.id,
        cursoData.titulo,
        cursoData.descripcion,
        cursoData.areaConocimiento,
        cursoData.precio,
        cursoData.url_imagen,
        cursoData.fechaInicio,
        cursoData.profesor,
        cursoData.rama_Estudio
    )
    titlePage.innerText = curso.getTitulo()
    textContainer.appendChild(curso.getTextPage())
    imageContainer.appendChild(curso.getImagePage())
}

async function getCourseID() {
    try {
        const valores = URL
        const urlParams = new URLSearchParams(valores)
        const cursoID = urlParams.get('id')

        const response = await CourseApi.getCourse(parseInt(cursoID))
        return response
    }catch(error) {
        if(error.request) {
            return "Pagina no encontrada :/"
        }
    }
}

main()