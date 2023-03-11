import logo from '../../assets/logo.png';
import {Link} from 'react-router-dom';
import {  useContext, useState } from 'react';

import {FiLock, FiMail } from "react-icons/fi";
import api from "../../services/api";
import { useHistory } from 'react-router-dom';
import UserContext from "../../contexts/UserContext";
import { setAuthToken } from '../../utils/setAuthToken';
import { limparCaches } from '../../utils/limparCaches';
import Usuario from '../../models/usuario';
import UserLogin from '../../models/userLogin';
import {Mensagens} from '../../enums/mensagens';


export default function Login(){
    const [email, setEmail] = useState('');
    const [senha, setSenha] = useState('');
    const history = useHistory();
    const [user,setUser] = useContext(UserContext);

    function Logar(e){   
        e.preventDefault();

        setAuthToken();
     
       if(email !== '' && senha !==''){
          
            var userLogin= new UserLogin(email,senha)


/*
            const requestOptions = {
                method: 'POST',
                headers: { 'Content-Type': 'application/json','Access-Control-Allow-Origin':'*' },
                body: JSON.stringify(userLogin)
            };
            fetch('http://localhost:8081/api/Clientes/autenticar', requestOptions)
                .then(response => response.json())
                .then(data => {
                    console.log(data);
                    history.push('/home'); 
                });
*/
         //  api.defaults.headers.post['Access-Control-Allow-Origin'] = '*';
         
            api
            .post("/api/clientes/autenticar",userLogin)
            .then((response) =>
            {
                if (response.data.token != null)
                {
                    console.log(response.data)
                   
                     var usuario = new Usuario(response.data.id,response.data.nome,response.data.token,response.data.email,response.data.roles)
           
                     setUser(usuario.nome );
                      
                    //set JWT token to local
                    localStorage.setItem("usuario",JSON.stringify( usuario));
                    // set token no Head do api                  
                    setAuthToken(usuario.token);

                    //alert('user:'+user)
                    history.push('/home'); 
                  
                }
                else 
                {
                    limparCaches();
                    alert(Mensagens.UsuarioESenhaInvalida)
                } 
               
            })
            .catch((err) => {
                limparCaches();
                var erro =err.toString();;
              alert(erro)
                if (erro.includes('400') ||  erro.includes('404'))
                   alert(Mensagens.UsuarioESenhaInvalida )   
                else  
                   alert(Mensagens.ErroGenerico + err)
      
            });
        
        }
        else{
            limparCaches();
            alert(Mensagens.EmailESenhaObrigatorio)
        }
    }
    return(
        <div className='fundo-container'>
            <div className='container'>
                <img src={logo} alt='Logo do restaurante'/>

                <form onSubmit={Logar}>
                    <div className="input-icons">
                        <span><FiMail size={18} color='rgb(194, 194, 194)'/></span>
                        <input  type="email" placeholder='Email' value={email} onChange={(e) => setEmail(e.target.value)}/>
                    </div>

                    <div className="input-icons">
                            
                    <span><FiLock size={18} color='rgb(194, 194, 194)'/></span>
                    <input type="password" placeholder='Senha' value={senha} onChange={(e) => setSenha(e.target.value)}/>
                    </div>
                    <button type="submit" className='form-btn' >Login</button>
                </form>

                <p className='form-link'>Não possui cadastro? Registre-se 
                <Link to="/cadastro"> aqui</Link> .</p>
            </div>
        </div>
    )
}