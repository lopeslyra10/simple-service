package com.example.model;

import java.io.Serializable;

public class ProjetoSustentavel {
    private int id;
    private String nome;
    private String descricao;
    private String tipoFonte;
    private String regiao;
    private Double custo;
    private String status;
    private Double emissoesCarbono;

    public ProjetoSustentavel(String nome, String descricao, String tipoFonte, String regiao, Double custo, String status, Double emissoesCarbono) {
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

    // Getters e Setters
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

    public Double getCusto() {
        return custo;
    }

    public void setCusto(Double custo) {
        this.custo = custo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getEmissoesCarbono() {
        return emissoesCarbono;
    }

    public void setEmissoesCarbono(Double emissoesCarbono) {
        this.emissoesCarbono = emissoesCarbono;
    }
}
