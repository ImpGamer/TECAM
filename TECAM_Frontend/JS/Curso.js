export default class Curso {
    #id;
    #titulo
    #descripcion
    #areaConocimiento
    #precio
    #url_imagen
    #fechaInicio
    #profesor
    #rama_estudio

    constructor(id, titulo, descripcion, areaConocimiento, precio, url_imagen, fechaInicio, profesor, rama_estudio) {
        this.#id = id
        this.#titulo = titulo
        this.#descripcion = descripcion
        this.#areaConocimiento = areaConocimiento
        this.#precio = precio
        this.#url_imagen = url_imagen
        this.#fechaInicio = fechaInicio
        this.#profesor = profesor
        this.#rama_estudio = rama_estudio
    }

    getId() { return this.#id }
    getTitulo() { return this.#titulo }
    getDescripcion() { return this.#descripcion }
    getArea() { return this.#areaConocimiento }
    getPrecio() { return this.#precio }
    getURL() { return this.#url_imagen }
    getFechaInicio() { return this.#fechaInicio }
    getProfesor() { return this.#profesor }
    getRama() { return this.#rama_estudio }

    convertDate(fecha) {
      const fechaServer = new Date(fecha)
      const meses = [
        'Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio',
        'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'
      ]
      return `${fechaServer.getDate()} de ${meses[fechaServer.getMonth()]} de ${fechaServer.getFullYear()}`
    }

    draw() {
        const fechaRefac = this.convertDate(this.#fechaInicio)
        const courseHTML = document.createElement('div')
        courseHTML.classList.add("column","is-one-third-desktop","is-half-tablet","is-full-mobile")
        courseHTML.innerHTML = `<div class="card mt-5 card-course">
        <a href="curso.html?id=${encodeURIComponent(this.#id)}">
          <div class="card-image">
            <figure class="image is-4by3">
              <img src="${this.#url_imagen}" alt="${this.#titulo}" />
            </figure>
          </div>
          <div class="card-content">
            <div class="media-content">
              <p class="title is-3">${this.#titulo}</p>
              <p class="subtitle is-6">Profesor que lo imparte: ${this.#profesor.nombre}</p>
              <p class="subtitle">Precio: $${this.#precio} <small>mx</small></p>
            </div>
          </div>
          <div class="content">
            <p class="subtitle is-5">${this.#descripcion}</p>
            <time>Fecha de Inicio: ${fechaRefac}</time>
          </div>
        </a>
      </div>`
        return courseHTML
    }

    drawDestacade() {
      const fechaRefac = this.convertDate(this.#fechaInicio)

      const courseHTML = document.createElement('a')
      courseHTML.href = `curso.html?id=${encodeURIComponent(this.#id)}`
      courseHTML.innerHTML = `<div class="card mt-5 destacate-course">
      <div class="card-image">
        <figure class="image is-4by3">
          <img
            src="${this.#url_imagen}"
            alt="${this.#titulo}"
          />
        </figure>
      </div>
      <div class="card-content">
          <div class="media-content">
            <p class="title is-3">${this.#titulo}</p>
            <p class="subtitle is-6">Profesor que lo imparte: ${this.#profesor.nombre}
             </p>
             <p class="subtitle">Precio: $${this.#precio} <small>mx</small></p>
          </div>
        </div>
    
        <div class="content">
        <p class="subtitle is-5">${this.#descripcion}</p>
          <time>Fecha de Inicio: ${fechaRefac}</time>
        </div>
      </div>
    </div>`
      return courseHTML
    }
    getTextPage() {
      const fechaRefac = this.convertDate(this.#fechaInicio)

      const element = document.createElement('div')
      element.classList.add('aligment-mobile')
      element.innerHTML = `
      <h3 class="title title-courses has-text-weight-bold">Curso: ${this.#titulo}</h3>
      <div>
        <p class="subtitle">${this.#descripcion}</p>
          <section class="columns is-desktop is-mobile">
            <div class="column">
              <p class="has-text-link has-text-weight-bold">Area dirigida:</p>
              <p class="has-text-weight-bold">${this.#areaConocimiento}</p>
            </div>
            <div class="column">
              <p class="has-text-link has-text-weight-bold">Enfocado A:</p>
              <p class="has-text-weight-bold">${this.#rama_estudio}</p>
            </div>
            </section>
            <time>Fecha de Inicio: ${fechaRefac}</time>
            <p class="subtitle has-text-weight-bold">Precio: $${this.#precio} <small>mx</small></p>
            <p class="has-text-weight-bold has-text-success">Contacto curso:</p>
            <a href="https://wa.me/${this.#profesor.telefono}" class="button is-success is-rounded" target="_blank">
              <span class="icon">
                <i class="fab fa-whatsapp"></i>
              </span>
              <span>Contacta!</span>
            </a>
            <p class="subtitle mt-5">Profesor que lo imparte: <strong>${this.#profesor.nombre}</strong></p>
      </div>
      `
      return element
    }
    getImagePage() {
      const image = document.createElement('img')
      image.classList.add('image-course')
      image.src = this.#url_imagen
      return image
    }
}