import { Link } from "react-router-dom";


export default function Index() {
  return <div class="container">
    <div class=" mt-4">
      <div class="col-md-12">
        <center>

          <h1>Oficina RRHH de muebles santiago:</h1>
        </center>
      </div>
    </div>

    <div class="container mt-4">
      <div class="row">
        <a class="btn btn-light btn-lg" role="button" href="/upload">Ingresar datos de reloj DATA.txt</a>
      </div>
    </div>

    <div class="container mt-4">
      <div class="row">
        <a class="btn btn-light btn-lg" role="button" href="/extra-hours">Ingresar Horas Extra</a>
      </div>
    </div>

    <div class="container mt-4">
      <div class="row">
        <a class="btn btn-light btn-lg" role="button" href="/justificatives">Ingresar Justificativos</a>
      </div>
    </div>

    <div class="container mt-4">
      <div class="row">
        <a class="btn btn-light btn-lg" role="button" href="/planilla">Ver Planilla de sueldos finales</a>
      </div>
    </div>
  </div>
}