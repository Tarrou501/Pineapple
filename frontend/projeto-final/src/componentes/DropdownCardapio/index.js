import Dropdown from 'react-bootstrap/Dropdown';
import { Link } from 'react-router-dom';
import React, { useEffect, useState } from "react";

import api from "../../services/api";
import './button.css'

function ButtonCardapio(props) {
  const [categorias, setCategorias] = useState([]);
  
  useEffect(() => {
    carregarCategoria();
  }, []);

  const carregarCategoria = () => {
    api.get('/api/categorias')
      .then(resposta => {
        setCategorias(resposta.data);
      })
      .catch(error => { console.log(error) })
  }

  const rows = categorias.map((cate, index) => {
    return (
      <Dropdown.Item key={index} href="#/action-1"><li>
        <Link to="/">{cate.nome}</Link></li>
      </Dropdown.Item>
    )
  })

  return (
    <Dropdown>
      <Dropdown.Toggle id="dropdown-basic-button" title="Dropdown button" className='dropdown-btn'>
        Menu
      </Dropdown.Toggle>

      <Dropdown.Menu className='dropdown-menu'>
        {rows}
      </Dropdown.Menu>
    </Dropdown>
  );
}
export default ButtonCardapio;

