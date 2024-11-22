package com.example.controller;


import java.util.HashMap;
import java.util.Map;
import com.example.model.Automacao;
import com.example.model.Consumo;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api")
public class AutomacaoController {

    private final Automacao automacao;

    public AutomacaoController() {
        this.automacao = new Automacao();
    }
    @POST
    @Path("/automacao/verificar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response verificarConsumo(Consumo consumo) {
        String acao = automacao.ajustarConsumo(consumo);

        Map<String, Object> response = new HashMap<>();
        response.put("acao", acao);

        return Response.ok()
                .entity(response)
                .build();
    }
}