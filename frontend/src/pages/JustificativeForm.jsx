import axios from 'axios';
import { useState } from 'react';

export default function JustificativeForm() {
  const [inputs, setInputs] = useState({});
  const [forbidden, setForbidden] = useState(false);

  const handleChange = (event) => {
    const name = event.target.name;
    const value = event.target.value;
    console.log(inputs)
    setInputs(values => ({ ...values, [name]: value }))
  }

  const handleSubmit = (event) => {
    event.preventDefault();
    axios.post('/justificative/', inputs).then(res => {
      console.log(res);
      if (res.status === 403) {
        setForbidden(true);
      }
      else {
        setForbidden(false);
      }
    });
  }

  return (
    <div class="bg-dark border rounded border-0 border-dark overflow-hidden">

      <form onSubmit={handleSubmit} className="container">
        <h1 class="fw-bold text-white mb-3 mt-3">Formulario Justificativos</h1>
        <div class="row g-0">
          <div class="col-md-6 ms-5">
            <p class="fw-bold text-white mb-4">Ingresar justificativos ya aprobados.
            </p>
          </div>
        </div>
        <div class="row mb-5">
          <div class="col-md-5">
          <div class="col-md-6 ms-5">
            <div className="form-group">
              <label class="fw-bold text-white mb-3">Rut del empleado:
                <input
                  type="text"
                  name="rut_employee"
                  className="form-control"
                  value={inputs.rut_employee || ""}
                  placeholder='Sin puntos y con guión'
                  onChange={handleChange}
                />
              </label>
            </div>

          </div>
          </div>
          <div class="col-md-5">
          <div class="col-md-6 ms-5">
            <div className='form-group'>
              <label class="fw-bold text-white mb-3">Fecha:
                <input
                  type="date"
                  name="date"
                  className="form-control"
                  value={inputs.date || ""}
                  onChange={handleChange}
                />
              </label>
            </div>
          </div>
          </div>
          <input type="submit" className="btn btn-primary mt-5"/>
        </div>
      </form>
    </div>
  )
}
