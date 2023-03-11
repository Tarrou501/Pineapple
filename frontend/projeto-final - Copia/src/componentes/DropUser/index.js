import Dropdown from 'react-bootstrap/Dropdown';
import { Link } from 'react-router-dom';
import {FiLogOut, FiUser} from "react-icons/fi";

import '../DropdownCardapio/button.css'
import { useContext } from 'react';
import UserContext from '../../contexts/UserContext';
import { useHistory} from 'react-router-dom'
import { setAuthToken } from '../../utils/setAuthToken';
import { limparCaches } from '../../utils/limparCaches';

function ButtonUser(prop) {
  const [user,setUser] = useContext(UserContext)
  const history = useHistory();
  
  const logout = () =>{
    limparCaches();             
    setAuthToken();
    setUser('');
    history.push("/")
     
  } 

  return (
    <Dropdown>
      <Dropdown.Toggle id="dropdown-basic-button" title="Dropdown button" className='dropdown-btn'>
        <div className='btn-profile'>
        <FiUser color='#ffc400' size={22}></FiUser>
        {user}
        </div>
        
      </Dropdown.Toggle>

      <Dropdown.Menu className='dropdown-menu'>
        <Dropdown.Item href="#/action-1"><li> <Link to="/">Editar Perfil</Link></li></Dropdown.Item>
        <Dropdown.Item href="#/action-1"><li> <Link to="/">Meus Pedidos</Link></li></Dropdown.Item>
        <Dropdown.Item href="#/action-1"><li onClick={logout}> <FiLogOut color='color: #ffc400;'></FiLogOut></li></Dropdown.Item>
      </Dropdown.Menu>
    </Dropdown>
  );
}

export default ButtonUser;