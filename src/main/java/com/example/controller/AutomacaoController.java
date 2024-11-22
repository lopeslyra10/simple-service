package com.example.controller;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;
import com.example.model.Automacao;
import com.example.model.Consumo;

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