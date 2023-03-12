import React from "react";
import { BrowserRouter, Route } from "react-router-dom";

import CadastroCliente from "../../paginas/clientes/CadastroCliente";
import Login from "../../paginas/Login";
import Home from "../../paginas/Home";
import ConsultaProduto from "../../paginas/produtos/ConsultaProduto";
import CadastroCategoria from "../../paginas/categorias/cadastroCategoria";
import RouteGuard from "../RouteGuard";


export default function Rotas(){
    return(
     
        <BrowserRouter>
            <Route component={Login} path="/" exact/>
            <Route component={Login} path="/login" exact/>
            <Route component={CadastroCliente} path="/cadastrocliente"/>
            <Route component={ConsultaProduto} path="/produtos"/>
            <Route component={CadastroCategoria} path="/categorias"/>
            <RouteGuard component={Home} path="/home"/>         
        </BrowserRouter>
       
    )
}