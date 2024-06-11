import Curso from './Curso.js'
import CourseAPI from './api-response.js'
const mostValueCourse_cont = document.getElementById('most-valued')
const searchInput = document.getElementById('searchCourse')
const containerCourses = document.getElementById('container-courses')
const parentContainer = document.getElementById('general-courses-container')
const courseText = document.createElement('div')


function main() {
    courseText.classList.add('container')
    getBestMonthCourse()
    getAll()
    searchInput.addEventListener('keydown', event => {
        if (event.key === 'Enter' && searchInput.value !== "") {
            const keyWord = searchInput.value
            getCoursesBySearch(keyWord)
        } else if (event.key === 'Enter' && searchInput.value === "") {
            getAll()
        }

    })
    parentContainer.insertBefore(courseText, parentContainer.firstChild)
}

async function getBestMonthCourse() {
    try {
        let cursoResponse = await CourseAPI.getCourse(8)
        const cursoValorado = new Curso(
            cursoResponse.id,
            cursoResponse.titulo,
            cursoResponse.descripcion,
            cursoResponse.areaConocimiento,
            cursoResponse.precio,
            cursoResponse.url_imagen,
            cursoResponse.fechaInicio,
            cursoResponse.profesor,
            cursoResponse.rama_Estudio
        )
        mostValueCourse_cont.appendChild(cursoValorado.drawDestacade())

    } catch (error) {
        mostValueCourse_cont.innerHTML = `<p class="subtitle mt-5 has-text-weight-bold" style="color: red;">${error}</p>`
    }
}

async function getCoursesBySearch(course) {
    let data = await CourseAPI.getCoursesByName(course)
    if(data.errorMessage) {
        while(containerCourses.firstChild) {
            containerCourses.removeChild(containerCourses.firstChild)
        }
        courseText.innerHTML = `<h4 class="title-courses" style="color: black;">${data.errorMessage} <small>🕵️</small></h4>`
    } else {
        const resultadoTexto = data.length === 1 ? 'resultado' : 'resultados'
        courseText.innerHTML = `<h4 class="principal-text-courses">Resultado de la busqueda:</h4>
        <p class="title-courses" style="color: black; font-size: 1em;">${data.length} ${resultadoTexto}</p>`
        drawCourses(data,containerCourses)
    }
}

async function getAll() {
    try {
        const data = await CourseAPI.getAllCourses()
        courseText.innerHTML = `<h4 class="principal-text-courses">TODOS LOS CURSOS</h4>`

        drawCourses(data,containerCourses)
    } catch (error) {
        parentContainer.replaceChild(createNotificationError(error),parentContainer.firstChild)
    }
}

function drawCourses(cursos,contenedor) {
    while(contenedor.firstChild) {
        contenedor.removeChild(contenedor.firstChild)
    }
    for(let i=0;i<cursos.length;i++) {
        let curso = new Curso(
            cursos[i].id,
            cursos[i].titulo,
            cursos[i].descripcion,
            cursos[i].areaConocimiento,
            cursos[i].precio,
            cursos[i].url_imagen,
            cursos[i].fechaInicio,
            cursos[i].profesor,
            cursos[i].rama_Estudio
        )
        contenedor.appendChild(curso.draw())
    }
}

function createNotificationError(message) {
    const notificationError = document.createElement('article')
    notificationError.classList.add('message','is-danger')
    notificationError.innerHTML = `<div class="message-body">
    ${message}
    </div>`

    return notificationError
}

main()