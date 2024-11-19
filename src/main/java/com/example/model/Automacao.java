package com.example.model;

import java.time.LocalDateTime;

public class Automacao {
    private static final double limiteNaoPico = 18;
    private static final double limitePico = 21 ;

    public String ajustarConsumo(Consumo consumo) {
        LocalDateTime horario = consumo.getHorario();
        if (isPico(horario) && consumo.getNivelConsumo() > limitePico) {
            return "Desligar " + consumo.getTipoDispositivo();
        } else if (!isPico(horario) && consumo.getNivelConsumo() < limiteNaoPico) {
            return "Ligar " + consumo.getTipoDispositivo();
        }
        return "Ação não necessária";
    }

    private boolean isPico(LocalDateTime horario) {
        int hour = horario.getHour();
        return hour >= 18 && hour <= 21;
    }
}

