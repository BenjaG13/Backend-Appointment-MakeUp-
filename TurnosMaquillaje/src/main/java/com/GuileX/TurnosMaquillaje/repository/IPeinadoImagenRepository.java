package com.GuileX.TurnosMaquillaje.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.GuileX.TurnosMaquillaje.dto.PeinadoImagenDTO;
import com.GuileX.TurnosMaquillaje.entity.PeinadoImagen;

@Repository
public interface IPeinadoImagenRepository extends JpaRepository<PeinadoImagen, Long>{

	
	List<PeinadoImagenDTO> findPeinadoImagenBy();
	
	
	
}
