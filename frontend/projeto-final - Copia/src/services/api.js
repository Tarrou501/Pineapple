import axios from "axios";

const api = axios.create({
  baseURL: "http://localhost:8081",
  headers:{
    // 'Access-Control-Allow-Origin':'*',
    'Accept':'application/json',
    //'Origin':'http://localhost:3000',
    'Content-Type':'application/json',
   // 'Authorization': 'Basic user:123'
    // 'Access-Control-Allow-Methods': 'POST, GET, OPTIONS, DELETE, PUT'
  },
  withCredentials:false
});


export default api;

/*
  headers.append('Content-Type', 'application/json');
    headers.append('Accept', 'application/json');
    headers.append('Authorization', 'Basic ' + base64.encode(username + ":" +  password));
    headers.append('Origin','http://localhost:3000');
    */