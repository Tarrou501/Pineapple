import logo from '../../assets/logo.png'
import ButtonCardapio from '../DropdownCardapio'
import ButtonCadastro from '../DropdownCadastro'
import ButtonUser from '../DropUser'
import './header.css'
import { FiLogOut, FiUser } from "react-icons/fi";

import UserContext from '../../contexts/UserContext'
import React, { useContext, useState } from "react";
import { useHistory,Link } from 'react-router-dom';
import { setAuthToken } from '../../utils/setAuthToken';
import { limparCaches } from '../../utils/limparCaches';

export default function Header() {
    const [user, setUser] = useContext(UserContext);
    const history = useHistory();
    const [mostrarBotao, setMostarBotao] = useState(false)

    var usuario = JSON.parse(localStorage.getItem("usuario"));

    setUser(usuario.nome);
    let roles = usuario.roles
    var liberarCadastro = roles.includes("ADMIN");


    const logout = () => {
        limparCaches();
        setAuthToken();
        setUser('');
        history.push("/")

    }

    function ToggleButton() {
        mostrarBotao === true ? setMostarBotao(false) : setMostarBotao(true)
    }

    return (
        <header>
            <div className='menu'>
                <ul className='header-links-box'>
                    <li className='header-links'><Link to="/home">Home</Link></li>
                    <li className='header-links'>Sobre</li>
                    <li className='header-links'><img src={logo} /></li>
                    <li className='header-links'>Contato</li>
                    <li className='header-links'><ButtonCardapio></ButtonCardapio></li>
                    <li className='header-links'><Link to="/categoria">Categorias</Link></li>
                </ul>
                <div className='header-user'>
                    <button onClick={ToggleButton} className="header-user-button">
                        <FiUser size={22} color='rgb(208, 203, 203)' />
                        <span>{user}</span>
                    </button>

                    {mostrarBotao &&
                        <div className='header-user-list'>
                            <ul>
                                <li>Editar Perfil</li>
                                <li>Meus Pedido</li>
                                <li>
                                    <button onClick={logout} className="btn-logout">
                                        <FiLogOut size={22} color='#948849' />
                                    </button>
                                </li>
                            </ul>
                        </div>
                    }

                </div>

            </div>
        </header>
    )
}