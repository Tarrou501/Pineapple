import React, { useContext } from "react";
import Context from "../../../contexts/UserContext";



export default function ConsultaProduto() {
  const [user] = useContext(Context);

  
  return (
    <h1> Consulta
     
      <h2>{user}</h2>
    </h1>
  );
}