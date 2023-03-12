import React from "react";

export default props => {

    const rows = props.categorias.map((cate,index) => {

        return (
            <tr key={index}>
                <td>{cate.nome}</td>
                <td>
                    <button className="btn btn-success"
                        onClick={e => props.editAction(cate)}><i className="fa fa-pencil fa-fw"></i> Editar</button>
                    <button className="btn btn-danger"
                        onClick={e => props.deleteAction(cate)}><i className="fa fa-trash fa-fw"></i> Excluir</button>

                </td>
            </tr>
        )
    })

    return (
        <table className="table table-hover">
            <thead>
                <tr>
                    <th scope="col">Nome</th>
                    <th scope="col">Ações</th>
                </tr>
            </thead>
            <tbody>
                {rows}
            </tbody>
        </table>
    )
}