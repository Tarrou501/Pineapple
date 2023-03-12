import './style.css'

export default function Carrossel(){
    return(  <main>
        <section class="carousel" aria-label="Gallery">
          <ol class="carousel__viewport">
            <li id="carousel__slide1"
                    tabindex="0"
                    class="carousel__slide">
                  <div class="carousel__snapper"></div>
            </li>
            <li id="carousel__slide2"
                    tabindex="0"
                    class="carousel__slide">
                  <div class="carousel__snapper"></div>  
            </li>
            <li id="carousel__slide3"
                    tabindex="0"
                    class="carousel__slide">
                  <div class="carousel__snapper"></div>
            </li>
            <li id="carousel__slide4"
                    tabindex="0"
                    class="carousel__slide">
                  <div class="carousel__snapper"></div>
            </li>
          </ol>
        </section>
        </main>)
        
}