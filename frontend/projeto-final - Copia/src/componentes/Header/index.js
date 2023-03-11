import logo from '../../assets/logo.png'
import ButtonCardapio from '../DropdownCardapio'
import ButtonCadastro from '../DropdownCadastro'
import ButtonUser from '../DropUser'
import './header.css'

import UserContext from '../../contexts/UserContext'
import React, { useContext } from "react";
import {FiUser} from "react-icons/fi";

export default function Header(){
    const [user,setUser] = useContext(UserContext);
    
    var usuario =  JSON.parse(localStorage.getItem("usuario"));
  
    setUser(usuario.nome);
    let  roles = usuario.roles
    var liberarCadastro = roles.includes("ADMIN");

    return(
        <header>
            <img src={logo} alt="logo"/>
            
            <nav>
                <ul className='header-links-box'> 
                    <li className='header-links'>
                        Início
                    </li>
                    <li className='header-links'>
                        Fale Conosco
                    </li>
                    <li>
                       { liberarCadastro && <ButtonCadastro/> }
                    </li>
                    <li>
                        <ButtonCardapio/>
                    </li>
                </ul>
            </nav>
            <div className='header-user'>
            <ButtonUser/>
            </div>
        </header>
    )
}