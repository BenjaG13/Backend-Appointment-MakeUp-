package com.GuileX.TurnosMaquillaje.dto;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class TurnoDTO {
	
    private String nombre;

    private String fechaHora;

    private BigDecimal maquillajePrecio;

    private BigDecimal peinadoPrecio;

    private Boolean maquillajeEstaPago;

    private Boolean peinadoEstaPago;

    private List<PeinadoImagenDTO> peinadoImagenes;

    private List<MaquillajeImagenDTO> maquillajeImagenes;
}
