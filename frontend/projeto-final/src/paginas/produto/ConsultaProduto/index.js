import React, { useContext } from "react";
import Context from "../../../contexts/UserContext";
import Header from "../../../componentes/Header";
import './produto.css'

export default function ConsultaProduto() {
  const [user] = useContext(Context);

  
  return (
    <div>
      <Header></Header>
      <div className="divProduto">
      <h1> Consulta </h1>    
      <h2>{user}</h2>
    
      </div>
      
    </div>
    
  );
}