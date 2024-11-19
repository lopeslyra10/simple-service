package com.example.model;

import java.time.LocalDateTime;

public class Consumo {
    private int id;
    private String tipoDispositivo;
    private double nivelConsumo;
    private LocalDateTime horario;

    public Consumo(int id, String tipoDispositivo, double nivelConsumo, LocalDateTime horario) {
        this.id = id;
        this.tipoDispositivo = tipoDispositivo;
        this.nivelConsumo = nivelConsumo;
        this.horario = horario;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipoDispositivo() {
        return tipoDispositivo;
    }

    public void setTipoDispositivo(String tipoDispositivo) {
        this.tipoDispositivo = tipoDispositivo;
    }

    public double getNivelConsumo() {
        return nivelConsumo;
    }

    public void setNivelConsumo(double nivelConsumo) {
        this.nivelConsumo = nivelConsumo;
    }

    public LocalDateTime getHorario() {
        return horario;
    }

    public void setHorario(LocalDateTime horario) {
        this.horario = horario;
    }

}

