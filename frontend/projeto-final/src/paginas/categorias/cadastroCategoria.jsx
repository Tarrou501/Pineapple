import React, { useEffect, useState } from "react";
import Card from "../../componentes/card";
import FormGroup from "../../componentes/formgroup";
import CategoriasTable from "./categoriasTable";

import { toast } from 'react-toastify';
import { Dialog } from 'primereact/dialog';
import { Button } from 'primereact/button';
import api from "../../services/api";
import Header from "../../componentes/Header";

function CadastroCategoria() {
    const [categorias, setCategorias] = useState([]);

    const [categoriaDeletar, setCategoriaDeletar] = useState({})
    const [showConfirmDialog, setShowConfirmDialog] = useState(false);
    const [showEditCategoria, setShowEditCategoria] = useState(false);



    const [id, setId] = useState('')
    const [nome, setNome] = useState('')
    const [categoriaEditar, setCategoriaEditar] = useState({})
    const [tituloForm, setTituloForm] = useState('')

    useEffect(() => {
        carregarCategoria();
    }, []);

    const carregarCategoria = () => {

        api.get('/api/categorias')
            .then(resposta => {
                setCategorias(resposta.data);
            })
            .catch(error => { console.log(error) })
    }

    const abrirConfirmacao = (categoria) => {
        setShowConfirmDialog(true);
        setCategoriaDeletar(categoria);
    }

    const cancelarDelecao = () => {
        setShowConfirmDialog(false);
        setCategoriaDeletar({})
    }

    const cancelarEdicao = () => {
        setShowEditCategoria(false);
        setCategoriaEditar({});
    }

    const confirmDialogFooter = () => (
        <div>
            <Button label="Confirmar" icon="pi pi-check" onClick={deletar} />
            <Button label="Cancelar" icon="pi pi-times" onClick={cancelarDelecao} />
        </div>
    );


    const deletar = () => {

        api.delete('/api/categorias', categoriaDeletar.id)
            .then(response => {
                console.log(response.status)
                const categ = categorias;
                const index = categ.indexOf(categoriaDeletar);
                categ.splice(index, 1);
                setCategorias(categ);
                setShowConfirmDialog(false);
                toast.success('Categoria excluído com sucesso!');
            })

            .catch(function (error) {
                if (error.response) {
                    // Request made and server responded

                    // console.log(error.response.data);
                    // console.log(error.response.status);
                    // console.log(error.response.headers);
                    toast.error(`Error: ${error.response.status} - ${error.response.data.error}`);
                } else if (error.request) {
                    // The request was made but no response was received
                    console.log(error.request);
                } else {
                    // Something happened in setting up the request that triggered an Error
                    console.log('Error', error.message);
                }

            });


    }


    const abrirEdicao = (categoria) => {
        setId(categoria.id)
        setNome(categoria.nome)
        setTituloForm('Edição')
        setShowEditCategoria(true)
        setCategoriaEditar(categoria);
    }


    const abrirInclusao = () => {
        setId('')
        setNome('')
        setTituloForm('Inclusão')
        setShowEditCategoria(true)
    }

    const editar = () => {
        const categoria = {
            id: id,
            nome: nome
        }

        if (id === '') {
            api.post('/api/categorias', categoria)
                .then(response => {
                    let listaCate = categorias;
                    listaCate.push(categoria);
                    setCategorias(listaCate);

                    setShowEditCategoria(false)
                    setCategoriaEditar({})
                    toast.success('Categoria registrado com sucesso!');
                })
                .catch(error => {
                    toast.error(error);
                });
        }
        else {
            api.put('/categorias', categoria)
                .then(response => {
                    let listaCate = categorias;
                    const index = listaCate.indexOf(categoriaEditar);
                    /*              let aux ="";
                                  produtos.map( (prod,i)=>{
                                     aux =  aux+ `${i}  ${prod.nome} \n`;
                                  });
                  */
                    listaCate[index] = categoria;
                    setCategorias(listaCate)
                    setShowEditCategoria(false);
                    setCategoriaEditar({})
                    toast.success('Categoria alterado com sucesso!');
                })
                .catch(error => {
                    toast.error(error);
                });
        }
    }

    const editFooter = (
        <div>
            <Button label="Salvar" icon="pi pi-save" onClick={editar} />
            <Button label="Cancelar" icon="pi pi-times" onClick={cancelarEdicao} />
        </div>
    );


    return (
        <main>
            <Header />
            <Card title="Consulta Categoria">
                <div className="row">
                    <div className="col-md-12">
                        <div className="bs-component">
                            <button onClick={abrirInclusao} className="btn btn-danger mb-3"><i className="fa fa-plus fa-fw"></i> Cadastrar</button>
                        </div>
                    </div>
                </div>
                <div className="row">
                    <div className="col-md-12">
                        <div className="bs-component">

                            <CategoriasTable categorias={categorias}
                                deleteAction={abrirConfirmacao}
                                editAction={abrirEdicao}     ></CategoriasTable>

                        </div>
                    </div>
                </div>
                <div>
                    <Dialog header="Confirmação"
                        visible={showConfirmDialog}
                        style={{ width: '50vw' }}
                        footer={confirmDialogFooter}
                        modal={true}
                        onHide={() => setShowConfirmDialog(false)}>
                        <p>Confirma a exclusão da categoria?</p>
                    </Dialog>
                </div>
                <div>
                    <Dialog header={tituloForm}
                        visible={showEditCategoria}
                        style={{ width: '50vw' }}
                        footer={editFooter}
                        modal={true}
                        onHide={() => setShowEditCategoria(false)}>
                        <div className="bs-component">
                            <FormGroup htmlFor="inputNome" label="Nome: *" >
                                <input type="text"
                                    value={nome}
                                    onChange={e => setNome(e.target.value)}
                                    className="form-control"
                                    id="inputEditNome"
                                    aria-describedby="nomeHelp"
                                    placeholder="Digite o nome do categoria">
                                </input>
                            </FormGroup>

                        </div>
                    </Dialog>

                </div>
            </Card>

        </main>

    )

}

export default CadastroCategoria;