import axios from 'axios';
import React, { useState } from 'react';

export default function UploadFile() {
  const [file, setState] = useState(null);
  const uploadfile = e => {
    console.log(e)
    setState(e.target.files[0]);
  }
  const upload = async (e) => {
    e.preventDefault()
    const url = '/timestamps';
    const formData = new FormData();
    formData.append('file', file);
    const config = {
      headers: {
        'Content-Type': 'multipart/form-data',
      },
    };
    axios.post(url, formData, config).then((response) => {
      console.log(response.data);
    });
  };
  return (
    <div class="bg-dark border rounded border-0 border-dark overflow-hidden">
      <center>
        <h1 class="fw-bold text-white mb-5 mt-3 ms-4" >Cargar Archivo</h1>
        <div class="col-md-6">
          <input type="file" className="form-control mb-5 mt-3 ms-4" name="file" onChange={uploadfile} />
          <button class="btn btn-primary mb-5 mt-3 ms-4" onClick={upload}>Subir Archivo</button>
          <div class="col-md-6">
            <h5 class="fw-bold text-white mb-3" data-th-text="${mensaje}"></h5>
          </div>
        </div>
      </center>
    </div>
  );
}
