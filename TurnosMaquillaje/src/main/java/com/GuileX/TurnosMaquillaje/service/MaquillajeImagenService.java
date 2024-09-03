package com.GuileX.TurnosMaquillaje.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GuileX.TurnosMaquillaje.repository.IMaquillajeImagenRepository;
import com.GuileX.TurnosMaquillaje.repository.ITurnoRepository;

import jakarta.persistence.EntityNotFoundException;

import com.GuileX.TurnosMaquillaje.dto.MaquillajeImagenDTO;
import com.GuileX.TurnosMaquillaje.entity.MaquillajeImagen;
import com.GuileX.TurnosMaquillaje.entity.Turno;

@Service
public class MaquillajeImagenService {

    @Autowired
    IMaquillajeImagenRepository maquillajeRepository;
    
    @Autowired
    ITurnoRepository turnoRepository;
    
    public List<MaquillajeImagenDTO> findMaquillajesImagenBy() {
        // Recuperar todas las entidades de MaquillajeImagen
        List<MaquillajeImagen> maquillajes = maquillajeRepository.findAll();
        
        // Convertir las entidades en DTOs
        return maquillajes.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public MaquillajeImagenDTO saveMaquillaje(MaquillajeImagenDTO maquillajeDTO) {
        // Convertir DTO a Entidad
        MaquillajeImagen maquillaje = convertToEntity(maquillajeDTO);
        
        // Guardar la entidad en la base de datos
        MaquillajeImagen savedMaquillaje = maquillajeRepository.save(maquillaje);
        
        // Convertir la entidad guardada de nuevo a DTO
        return convertToDTO(savedMaquillaje);
    }

    // Conversión de DTO a Entidad
    private MaquillajeImagen convertToEntity(MaquillajeImagenDTO maquillajeDTO) {
        MaquillajeImagen maquillaje = new MaquillajeImagen();
        maquillaje.setImagen(maquillajeDTO.getImagen());
        
        // Obtener el Turno por id_turno usando el TurnoRepository
        Optional<Turno> turno = turnoRepository.findById(maquillajeDTO.getIdTurno());
        if (turno.isPresent()) {
            maquillaje.setTurno(turno.get());
        } else {
            throw new EntityNotFoundException("Turno with ID " + maquillajeDTO.getIdTurno() + " not found");
        }

        return maquillaje;
    }

    // Conversión de Entidad a DTO
    private MaquillajeImagenDTO convertToDTO(MaquillajeImagen maquillaje) {
        return MaquillajeImagenDTO.builder()
                .imagen(maquillaje.getImagen())
                .idTurno(maquillaje.getTurno().getId()) // Setear el ID del turno en el DTO
                .build();
    }
}
