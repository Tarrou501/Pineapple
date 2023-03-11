import React from "react";
import { BrowserRouter, Route } from "react-router-dom";

import Cadastro from "../../paginas/Cadastro";
import Login from "../../paginas/Login";
import Home from "../../paginas/Home";
import RouteGuard from "../RouteGuard";


export default function Rotas(){
    return(
     
        <BrowserRouter>
            <Route component={Login} path="/" exact/>
            <Route component={Login} path="/login" exact/>
            <Route component={Cadastro} path="/cadastro"/>
            <RouteGuard component={Home} path="/home"/>         
        </BrowserRouter>
       
    )
}