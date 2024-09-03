package com.GuileX.TurnosMaquillaje.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GuileX.TurnosMaquillaje.repository.IPeinadoImagenRepository;
import com.GuileX.TurnosMaquillaje.repository.ITurnoRepository;

import jakarta.persistence.EntityNotFoundException;

import com.GuileX.TurnosMaquillaje.dto.PeinadoImagenDTO;
import com.GuileX.TurnosMaquillaje.entity.PeinadoImagen;
import com.GuileX.TurnosMaquillaje.entity.Turno;


@Service
public class PeinadoImagenService {

	@Autowired
	IPeinadoImagenRepository peinadoRepository;
	
	@Autowired
	ITurnoRepository turnoRepository;
	
	
	public List<PeinadoImagenDTO> findPeinadosImagenBy() {
        // Recuperar todas las entidades de PeinadoImagen
        List<PeinadoImagen> peinados = peinadoRepository.findAll();
        
        // Convertir las entidades en DTOs
        return peinados.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
	
	
	public PeinadoImagenDTO savePeinado(PeinadoImagenDTO peinadoDTO) {
        // Convertir DTO a Entidad
        PeinadoImagen peinado = convertToEntity(peinadoDTO);
        
        // Guardar la entidad en la base de datos
        PeinadoImagen savedPeinado = peinadoRepository.save(peinado);
        
        // Convertir la entidad guardada de nuevo a DTO
        return convertToDTO(savedPeinado);
    }

    // Conversión de DTO a Entidad
    private PeinadoImagen convertToEntity(PeinadoImagenDTO peinadoDTO) {
        PeinadoImagen peinado = new PeinadoImagen();
        peinado.setImagen(peinadoDTO.getImagen());
        
        // Obtener el Turno por id_turno usando el TurnoRepository
        Optional<Turno> turno = turnoRepository.findById(peinadoDTO.getIdTurno());
        if (turno.isPresent()) {
            peinado.setTurno(turno.get());
        } else {
            throw new EntityNotFoundException("Turno with ID " + peinadoDTO.getIdTurno() + " not found");
        }

        return peinado;
    }

    // Conversión de Entidad a DTO
    private PeinadoImagenDTO convertToDTO(PeinadoImagen peinado) {
        return PeinadoImagenDTO.builder()
                .imagen(peinado.getImagen())
                .idTurno(peinado.getTurno().getId()) // Setear el ID del turno en el DTO
                .build();
    }
}
