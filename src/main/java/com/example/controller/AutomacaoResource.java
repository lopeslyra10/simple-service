package com.example.controller;

import com.example.model.Automacao;
import com.example.model.Consumo;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/automacao")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AutomacaoResource {
    private final Automacao automacao = new Automacao();

    // Simulando dispositivos para testar
    private static Map<Integer, Consumo> dispositivos = new HashMap<>();

    static {
        dispositivos.put(1, new Consumo(1, "Ar-condicionado", 22, null));
        dispositivos.put(2, new Consumo(2, "Luz da sala", 5, null));
    }

    @POST
    public Response ajustarConsumo(List<Consumo> consumos) {
        for (Consumo consumo : consumos) {
            automacao.ajustarConsumo(consumo); // Executa a lógica para cada dispositivo
        }
        return Response.ok("Dispositivos ajustados com sucesso").build();
    }


    @GET
    public Response listarDispositivos() {
        return Response.ok(dispositivos.values()).build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizarDispositivo(@PathParam("id") int id, Consumo novoConsumo) {
        if (dispositivos.containsKey(id)) {
            dispositivos.put(id, novoConsumo);
            return Response.ok(novoConsumo).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Dispositivo não encontrado").build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletarDispositivo(@PathParam("id") int id) {
        if (dispositivos.containsKey(id)) {
            dispositivos.remove(id);
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Dispositivo não encontrado").build();
    }
}
