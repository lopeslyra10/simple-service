package com.example.controller;

import com.example.model.ProjetoSustentavel;
import com.example.infra.ProjetoDAO;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/projetos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProjetoResource {

    private final ProjetoDAO dao = new ProjetoDAO(); // Inicialização do DAO

    @GET
    public Response listarProjetos() {
        List<ProjetoSustentavel> projetos = dao.listarProjetos(); // Busca do banco
        return Response.ok(projetos).build();
    }

    @POST
    public Response criarProjeto(ProjetoSustentavel projeto) {
        dao.inserirProjeto(projeto); // Persistir no banco
        return Response.status(Response.Status.CREATED).entity(projeto).build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizarProjeto(@PathParam("id") int id, ProjetoSustentavel projeto) {
        projeto.setId(id);
        dao.atualizarProjeto(projeto); // Atualizar no banco
        return Response.ok(projeto).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletarProjeto(@PathParam("id") int id) {
        dao.deletarProjeto(id); // Deletar no banco
        return Response.noContent().build();
    }

    @GET
    @Path("/status/{status}")
    public Response listarProjetosPorStatus(@PathParam("status") String status) {
        List<ProjetoSustentavel> filtrados = dao.listarProjetosPorStatus(status);
        return Response.ok(filtrados).build();
    }

    @GET
    @Path("/tipoFonte/{tipoFonte}")
    public Response listarProjetosPorTipoFonte(@PathParam("tipoFonte") String tipoFonte) {
        List<ProjetoSustentavel> filtrados = dao.listarProjetosPorTipoFonte(tipoFonte);
        return Response.ok(filtrados).build();
    }
}
