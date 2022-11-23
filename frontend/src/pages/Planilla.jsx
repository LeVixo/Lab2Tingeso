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
          <tr>
            <th scope="row">1-9</th>
            <td>Juan</td>
            <td>Euclevio</td>
            <td>A</td>
            <td>12</td>
            <td>1.700.000</td>
            <td>8%</td>
          </tr>
          <tr>
            <th scope="row">2-9</th>
            <td>Pedro</td>
            <td>Ramon</td>
            <td>B</td>
            <td>2</td>
            <td>1.200.000</td>
            <td>0%</td>
          </tr>
          <tr>
            <th scope="row">3-9</th>
            <td>Maria</td>
            <td>Maria</td>
            <td>C</td>
            <td>7</td>
            <td>800.000</td>
            <td>5%</td>
          </tr>
          <tr>
            <th scope="row">4-9</th>
            <td>Jose</td>
            <td>Marin</td>
            <td>C</td>
            <td>4</td>
            <td>800.000</td>
            <td>0%</td>
          </tr>
          </tbody>
        </table></div>
    </div>
  );
}