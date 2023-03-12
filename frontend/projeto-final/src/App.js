import React,{useState} from "react";
import Rotas from "./componentes/Rotas";
import UserContext from "./contexts/UserContext";
import './App.css'
import { setAuthToken } from "./utils/setAuthToken";

function App() {
  const [user, setUser] = useState('');
  const usuario = JSON.parse(localStorage.getItem("usuario"));
 
  if (usuario) {
     setAuthToken(usuario.token);
  }

  return (
    <UserContext.Provider value={[user,setUser]}>
      <Rotas/>
      </UserContext.Provider>
  );
}

export default App;
