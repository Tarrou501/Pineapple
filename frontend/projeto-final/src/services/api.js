import axios from "axios";

const api = axios.create({
  baseURL: "http://localhost:8081",
  headers:{
    'Access-Control-Allow-Origin':'*',
    'Content-Type':'application/json'
  },
  withCredentials:false
});

export default api;