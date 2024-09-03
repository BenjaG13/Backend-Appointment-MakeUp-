package com.GuileX.TurnosMaquillaje.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.GuileX.TurnosMaquillaje.dto.TurnoDTO;
import com.GuileX.TurnosMaquillaje.entity.Turno;

@Repository
public interface ITurnoRepository extends JpaRepository<Turno, Long> {
	
	 @Query(value = "SELECT * FROM turno t WHERE t.fecha_hora LIKE ?1%", nativeQuery = true)
	 List<Turno> findAllByFechaHora(String date);
	 
	 
	
	 Optional<Turno> findById(Long id);
	 
	 List<Turno> findAll();
	 
	 
}
