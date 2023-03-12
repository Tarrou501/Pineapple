import Dropdown from 'react-bootstrap/Dropdown';
import { Link } from 'react-router-dom';

import '../DropdownCardapio/button.css'
function ButtonCadastro() {
    return (
      <Dropdown>
        <Dropdown.Toggle id="dropdown-basic-button" title="Dropdown button" className='dropdown-btn'>
          Cadastro
        </Dropdown.Toggle>
  
        <Dropdown.Menu className='dropdown-menu'>
          <Dropdown.Item href="#/action-1"><li> <Link to="/categorias">Categorias</Link></li></Dropdown.Item>
          <Dropdown.Item href="#/action-2"><li> <Link to="/">Produtos</Link></li></Dropdown.Item>
          <Dropdown.Item href="#/action-3"><li> <Link to="/">Clientes</Link></li></Dropdown.Item>
          <Dropdown.Item href="#/action-4"><li> <Link to="/">Pedidos</Link></li></Dropdown.Item>
        </Dropdown.Menu>
      </Dropdown>
    );
  }
  
  export default ButtonCadastro;