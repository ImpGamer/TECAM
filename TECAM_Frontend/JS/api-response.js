import Curso from './Curso.js'

export default class CourseAPI {
    static URL = "http://localhost:8080/cursos"

    static async getCourse(cursoID) {
        try {
            let response = await axios.get(`${CourseAPI.URL}/${cursoID}`)
            if(response.status < 200 || response.status > 400) {
                throw new Error("Error al cargar el curso. Lamentamos los errores 😟")
            }
            return response.data
        }catch(error) {
            if(error.request) {
                throw new Error("No se pudo cargar el curso 😟")
            }
        }
    }
    static async getCoursesByName(courseName) {
        try {
            const response = await axios.get(`${CourseAPI.URL}/search?course=${encodeURIComponent(courseName)}`)
            return response.data
        }catch(error) {
            if(error.request)
                return { errorMessage: error.response.data.errorMessage };
            }
        }

        static async getAllCourses() {
            try {
                const response = await axios.get(CourseAPI.URL)
                return response.data
    
            } catch(error) {
                if(error.request) {
                    throw new Error("No se han podido cargar los cursos. Lamentamos los errores 😟")
                }
            }
        }
    }