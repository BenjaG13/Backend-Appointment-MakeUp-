package com.GuileX.TurnosMaquillaje.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.GuileX.TurnosMaquillaje.dto.MaquillajeImagenDTO;
import com.GuileX.TurnosMaquillaje.entity.MaquillajeImagen;

@Repository
public interface IMaquillajeImagenRepository extends JpaRepository<MaquillajeImagen, Long> {

    List<MaquillajeImagenDTO> findMaquillajeImagenBy();
}
