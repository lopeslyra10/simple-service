package com.example.model;

import jakarta.ws.rs.Path;

@Path("/projetos")
public class ProjetoSustentavel {

    private int id;
    private String nome;
    private String descricao;
    private String tipoFonte;
    private String regiao;
    private double custo;
    private String status;
    private double emissoesCarbono;

    public ProjetoSustentavel(int id, String nome, String descricao, String tipoFonte, String regiao, double custo, String status,
                              double emissoesCarbono) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.tipoFonte = tipoFonte;
        this.regiao = regiao;
        this.custo = custo;
        this.status = status;
        this.emissoesCarbono = emissoesCarbono;
    }

    public ProjetoSustentavel() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipoFonte() {
        return tipoFonte;
    }

    public void setTipoFonte(String tipoFonte) {
        this.tipoFonte = tipoFonte;
    }

    public String getRegiao() {
        return regiao;
    }

    public void setRegiao(String regiao) {
        this.regiao = regiao;
    }

    public double getCusto() {
        return custo;
    }

    public void setCusto(double custo) {
        this.custo = custo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getEmissoesCarbono() {
        return emissoesCarbono;
    }

    public void setEmissoesCarbono(double emissoesCarbono) {
        this.emissoesCarbono = emissoesCarbono;
    }
}
