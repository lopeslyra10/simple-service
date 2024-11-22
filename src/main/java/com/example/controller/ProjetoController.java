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
public class ProjetoController {

    private final ProjetoDAO dao = new ProjetoDAO();

    @GET
    public Response listarProjetos() {
        List<ProjetoSustentavel> projetos = dao.listarProjetos();
        return Response.ok(projetos).build();
    }

    @POST
    public Response criarProjeto(ProjetoSustentavel projeto) {
        dao.inserirProjeto(projeto);
        return Response.status(Response.Status.CREATED).entity(projeto).build();
    }

    @GET
    @Path("/{id}")
    public Response buscarProjetoPorId(@PathParam("id") int id) {
        ProjetoSustentavel projeto = dao.listarProjetos()
                .stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
        if (projeto == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Projeto não encontrado.").build();
        }
        return Response.ok(projeto).build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizarProjeto(@PathParam("id") int id, ProjetoSustentavel projetoAtualizado) {
        projetoAtualizado.setId(id);
        dao.atualizarProjeto(projetoAtualizado);
        return Response.ok(projetoAtualizado).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletarProjeto(@PathParam("id") int id) {
        dao.deletarProjeto(id);
        return Response.noContent().build();
    }

    @GET
    @Path("/status/{status}")
    public Response listarPorStatus(@PathParam("status") String status) {
        List<ProjetoSustentavel> projetos = dao.listarProjetosPorStatus(status);
        return Response.ok(projetos).build();
    }

    @GET
    @Path("/tipoFonte/{tipoFonte}")
    public Response listarPorTipoFonte(@PathParam("tipoFonte") String tipoFonte) {
        List<ProjetoSustentavel> projetos = dao.listarProjetosPorTipoFonte(tipoFonte);
        return Response.ok(projetos).build();
    }
}
