package com.GuileX.TurnosMaquillaje.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@Table(name="turno")
public class Turno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_turno")
    private Long id;

    private String nombre;
    
    @Column(name="fecha_hora")
    private String fechaHora;

    @Column(name="maquillaje_precio")
    private BigDecimal maquillajePrecio;

    @Column(name="peinado_precio")
    private BigDecimal peinadoPrecio;

    @Column(name="maquillaje_esta_pago")
    private Boolean maquillajeEstaPago;

    @Column(name="peinado_esta_pago")
    private Boolean peinadoEstaPago;

    @OneToMany(mappedBy = "turno", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<PeinadoImagen> peinadoImagenes;

    @OneToMany(mappedBy = "turno", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<MaquillajeImagen> maquillajeImagenes;
}
