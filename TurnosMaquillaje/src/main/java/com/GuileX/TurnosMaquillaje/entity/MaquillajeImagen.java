package com.GuileX.TurnosMaquillaje.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="maquillaje_imagen")
public class MaquillajeImagen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_maquillaje_imagen")
    private Long id;

    private byte[] imagen;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_turno")
    @JsonIgnore
    private Turno turno;
}
