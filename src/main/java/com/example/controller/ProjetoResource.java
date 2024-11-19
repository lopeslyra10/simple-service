package com.example.controller;

import com.example.model.Projeto;
import com.example.model.ProjetoSustentavel;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/projetos")
public class ProjetoResource {
    private Projeto bo = new Projeto();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarProjetos() {
        List<ProjetoSustentavel> projetos = bo.listarProjetos();
        return Response.ok(projetos).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response criarProjeto(ProjetoSustentavel projeto) {
        bo.inserirProjeto(projeto);
        return Response.status(Response.Status.CREATED).entity(projeto).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarProjeto(@PathParam("id") int id, ProjetoSustentavel projeto) {
        projeto.setId(id);
        bo.atualizarProjeto(projeto);
        return Response.ok(projeto).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletarProjeto(@PathParam("id") int id) {
        bo.deletarProjeto(id);
        return Response.noContent().build();
    }
    @GET
    @Path("/status/{status}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarProjetosPorStatus(@PathParam("status") String status) {
        List<ProjetoSustentavel> projetos = bo.listarProjetos();
        List<ProjetoSustentavel> filtrados = projetos.stream()
                .filter(p -> status.equalsIgnoreCase(p.getStatus()))
                .toList();
        return Response.ok(filtrados).build();
    }

    @GET
    @Path("/tipoFonte/{tipoFonte}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarProjetosPorTipoFonte(@PathParam("tipoFonte") String tipoFonte) {
        List<ProjetoSustentavel> projetos = bo.listarProjetos();
        List<ProjetoSustentavel> filtrados = projetos.stream()
                .filter(p -> tipoFonte.equalsIgnoreCase(p.getTipoFonte()))
                .toList();
        return Response.ok(filtrados).build();
    }

    @DELETE
    @Path("/tipoFonte/{tipoFonte}")
    public Response deletarProjetosPorTipoFonte(@PathParam("tipoFonte") String tipoFonte) {
        List<ProjetoSustentavel> projetos = bo.listarProjetos();
        projetos.stream()
                .filter(p -> tipoFonte.equalsIgnoreCase(p.getTipoFonte()))
                .forEach(p -> bo.deletarProjeto(p.getId()));
        return Response.noContent().build();
    }

}
