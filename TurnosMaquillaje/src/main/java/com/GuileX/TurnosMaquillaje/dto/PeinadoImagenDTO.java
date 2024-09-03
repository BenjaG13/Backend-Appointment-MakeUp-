package com.GuileX.TurnosMaquillaje.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PeinadoImagenDTO {
	
    private byte[] imagen;
    
    private Long idTurno;

}
