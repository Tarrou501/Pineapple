import logo from '../../assets/logo.png';
import './cadastro.css';
import { Link, useHistory } from 'react-router-dom';

import { useState } from 'react';
import { FiUser, FiLock, FiUnlock, FiMail } from "react-icons/fi";
import api from '../../services/api'

export default function Cadastro() {
    const [username, setUsername] = useState('');
    const [email, setEmail] = useState('');
    const [senha, setSenha] = useState('');
    const [confirmarSenha, setConfirmarSenha] = useState('');
    const history = useHistory();


    function Buscar() {

        //api.defaults.headers.post['Access-Control-Allow-Origin'] = '*';



        api
            .get("/api/Clientes/todos")

            .then((response) => {
                if (response.data != null) {
                    console.log(response.data[0]);
                    alert(response.data[0].nome)
                }
                else alert("Error on create new user, contact the admin")
            })
            .catch((err) => {
                var erro = err.toString();;

                if (erro.includes('400'))
                    alert(`Email ${email} já cadastrado!`)
                else alert("ops! ocorreu um erro:\n" + err)


            });

    }


    function Cadastrar(e) {
        e.preventDefault();

        if (senha !== confirmarSenha) {
            alert('Digite senhas iguais');
        }
        else if ((username !== '' && email !== '' && senha !== '' && confirmarSenha !== '') && senha === confirmarSenha)
         {
            var usuario = {
                nome: username,
                email: email,
                senha: senha,
                roles: 'USER'
            }
            
           //  api.defaults.headers.post['Access-Control-Allow-Origin'] = 'http://localhost:3000';
         //  api.defaults.headers.post['Access-Control-Allow-Origin'] = '*';
           //  api.defaults.headers.post['Access-Control-Allow-Credentials'] = 'true';
             //api.defaults.headers.post['Access-Control-Allow-Credentials'] = 'true';
            
             api.defaults.headers.post['Authorization'] = 'Basic user:123';


                api
                .post("/api/Clientes/cadastrar", usuario)
                .then((response) => {
                    if (response.data != null) {
                        alert("Usuário cadastrado com sucesso");
                        history.push('/')
                    }
                    else alert("Error on create new user, contact the admin")
                })
                .catch((err) => {
                    var erro = err.toString();;

                    if (erro.includes('400'))
                        alert(`Email ${email} já cadastrado!`)
                    else alert("ops! ocorreu um erro:\n" + err)


                });


        }
    }
    return (
        <div className='fundo-container'>
            <div className='container'>
                <img src={logo} alt='Logo do restaurante' />


                <form onSubmit={Cadastrar}>

                    <div className="input-icons">
                        <span><FiUser size={18} color='rgb(194, 194, 194)' /></span>
                        <input type="text" placeholder='Username' value={username} onChange={(e) => setUsername(e.target.value)} />
                    </div>

                    <div className="input-icons">
                        <span><FiMail size={18} color='rgb(194, 194, 194)' /></span>
                        <input type="email" placeholder='Email' value={email} onChange={(e) => setEmail(e.target.value)} />
                    </div>

                    <div className="input-icons">
                        <span><FiLock size={18} color='rgb(194, 194, 194)' /></span>
                        <input type="password" placeholder='Senha' value={senha} onChange={(e) => setSenha(e.target.value)} />
                    </div>

                    <div className="input-icons">
                        <   span><FiUnlock size={18} color='rgb(194, 194, 194)' /></span>
                        <input type="password" placeholder='Confirmar senha' value={confirmarSenha} onChange={(e) => setConfirmarSenha(e.target.value)} />
                    </div>


                    <button type="submit" className='form-btn'>Cadastrar</button>
                    
                </form>

                <p className='form-link'>Já possui uma conta? Entre
                    <Link to="/"> aqui</Link> .</p>
            </div>
        </div>
    )
}