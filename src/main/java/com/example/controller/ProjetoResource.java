package com.example.controller;

import com.example.model.Projeto;
import com.example.model.ProjetoSustentavel;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/projetos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProjetoResource {
    private final Projeto projeto = new Projeto();

    @GET
    public Response listarProjetos() {
        List<ProjetoSustentavel> projetos = projeto.listarProjetos();
        return Response.ok(projetos).build();
    }

    @POST
    public Response criarProjeto(ProjetoSustentavel projeto) {
        this.projeto.inserirProjeto(projeto);
        return Response.status(Response.Status.CREATED).entity(projeto).build();
    }


    @PUT
    @Path("/{id}")
    public Response atualizarProjeto(@PathParam("id") int id, ProjetoSustentavel projeto) {
        projeto.setId(id);
        this.projeto.atualizarProjeto(projeto);
        return Response.ok(projeto).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletarProjeto(@PathParam("id") int id) {
        projeto.deletarProjeto(id);
        return Response.noContent().build();
    }
    @GET
    @Path("/status/{status}")
    public Response listarProjetosPorStatus(@PathParam("status") String status) {
        List<ProjetoSustentavel> projetos = projeto.listarProjetos();
        List<ProjetoSustentavel> filtrados = projetos.stream()
                .filter(p -> status.equalsIgnoreCase(p.getStatus()))
                .toList();
        return Response.ok(filtrados).build();
    }

    @GET
    @Path("/tipoFonte/{tipoFonte}")
    public Response listarProjetosPorTipoFonte(@PathParam("tipoFonte") String tipoFonte) {
        List<ProjetoSustentavel> projetos = projeto.listarProjetos();
        List<ProjetoSustentavel> filtrados = projetos.stream()
                .filter(p -> tipoFonte.equalsIgnoreCase(p.getTipoFonte()))
                .toList();
        return Response.ok(filtrados).build();
    }

    @DELETE
    @Path("/tipoFonte/{tipoFonte}")
    public Response deletarProjetosPorTipoFonte(@PathParam("tipoFonte") String tipoFonte) {
        List<ProjetoSustentavel> projetos = projeto.listarProjetos();
        projetos.stream()
                .filter(p -> tipoFonte.equalsIgnoreCase(p.getTipoFonte()))
                .forEach(p -> projeto.deletarProjeto(p.getId()));
        return Response.noContent().build();
    }

}
