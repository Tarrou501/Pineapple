import React, { useContext } from "react";
import Context from "../../contexts/UserContext";

import Header from "../../componentes/Header";

export default function Home() {
  const [user] = useContext(Context);

  
  return (
    <main>
      <Header />
      <h1>{user}</h1>
    </main>
  );
}