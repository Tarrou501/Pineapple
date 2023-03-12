import Dropdown from 'react-bootstrap/Dropdown';
import { Link } from 'react-router-dom';

import './button.css'

function ButtonCardapio() {
  return (
    <Dropdown>
      <Dropdown.Toggle id="dropdown-basic-button" title="Dropdown button" className='dropdown-btn'>
        Menu
      </Dropdown.Toggle>

      <Dropdown.Menu className='dropdown-menu'>
        <Dropdown.Item href="#/action-1"><li> <Link to="/">Hamburguers</Link></li></Dropdown.Item>
        <Dropdown.Item href="#/action-2"><li> <Link to="/">Porções</Link></li></Dropdown.Item>
        <Dropdown.Item href="#/action-3"><li> <Link to="/">Bebidas</Link></li></Dropdown.Item>
        <Dropdown.Item href="#/action-4"><li> <Link to="/">Sobremesas</Link></li></Dropdown.Item>
      </Dropdown.Menu>
    </Dropdown>
  );
}
export default ButtonCardapio;

