import React from "react";
import SalaryRow from "../components/SalaryRow";
import axios from "axios";
import { useState } from "react";

export default function Planilla() {
  const [sueldos, setSueldos] = useState([]);
  const calcular = async () => {
    axios.get("/officerrhh/calcular").then((response) => {
      getSueldos()
      return response.data;
    });
  }
  const getSueldos = async () => {
    axios.get("/salaries").then((response) => {
      console.log(response.data);
      setSueldos(response.data);
      return response.data;
    });
  }
  return (
    <div>
      <center>
      <button class="btn btn-light btn-lg mt-4 mb-4" onClick={() => calcular()}>Calcular Sueldos</button>
      </center>
      <div class="table-responsive">

        <table className="table">
          <thead>
            <tr>
              <th> RUT </th>
              <th> Nombre </th>
              <th> Apellido </th>
              <th> Categoria </th>
              <th> Años de Servicio </th>
              <th> Sueldo Fijo </th>
              <th> Bonificación </th></tr>
          </thead>
          <tbody>
            {sueldos.map((sueldo) => <SalaryRow key={sueldo.id}
              rut={sueldo.rut_empleado}
              nombre={sueldo.nombre_empleado}
              apellido={sueldo.apellido_empleado}
              categoria={sueldo.categoria}
              años_servicio={sueldo.anios_servicio}
              sueldo_fijo={sueldo.sueldo_fijo}
              bonificacion={sueldo.bonificacion}
            />)}
          </tbody>
        </table></div>
    </div>
  );
}