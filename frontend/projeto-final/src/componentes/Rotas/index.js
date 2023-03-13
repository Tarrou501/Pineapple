import React from "react";
import { BrowserRouter, Route } from "react-router-dom";

import CadastroCliente from "../../paginas/cliente/CadastroCliente";
import Login from "../../paginas/Login";
import Home from "../../paginas/Home";
import RouteGuard from "../RouteGuard";
import CadastroCategoria from "../../paginas/categoria/cadastroCategoria";
import CadastroProduto from "../../paginas/produto/ConsultaProduto";



export default function Rotas(){
    return(
     
        <BrowserRouter>
            <Route component={Login} path="/" exact/>
            <Route component={Login} path="/login" exact/>
            <Route component={CadastroCliente} path="/cadastrocliente"/>
            <Route component={CadastroCategoria} path="/categoria"/>
            <Route component={CadastroProduto} path="/produto"/>
            <RouteGuard component={Home} path="/home"/>         
        </BrowserRouter>
       
    )
}