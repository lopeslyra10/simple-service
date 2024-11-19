package com.example;

import com.example.model.Projeto;
import com.example.model.ProjetoSustentavel;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;

/**
 * Main class.
 */
public class Main {
    public static final String BASE_URI = "http://localhost:8080/projeto";

    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer() {
        final ResourceConfig rc = new ResourceConfig()
                .packages("com.example");
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    public static void main(String[] args) throws IOException {
        final HttpServer server = startServer();
        System.out.println("Server started at: " + BASE_URI);
        System.in.read();
        server.stop();

        Projeto projetoBO = new Projeto();
        ProjetoSustentavel projeto = new ProjetoSustentavel();

        projeto.setNome("Projeto Solar");
        projeto.setDescricao("Projeto de energia solar em comunidade.");
        projeto.setCusto(300000);
        projeto.setTipoFonte("Solar");
        projeto.setEmissoesCarbono(800);

        double eficiencia = projetoBO.calcularEficiênciaEnergetica(projeto);
        System.out.println("Eficiência energética: " + eficiencia);
    }
}
