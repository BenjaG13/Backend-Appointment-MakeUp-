package com.GuileX.TurnosMaquillaje.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MaquillajeImagenDTO {

    private byte[] imagen;

    private Long idTurno;

}
