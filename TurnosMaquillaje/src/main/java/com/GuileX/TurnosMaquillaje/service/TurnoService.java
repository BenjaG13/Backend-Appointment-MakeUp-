package com.GuileX.TurnosMaquillaje.service;

import com.GuileX.TurnosMaquillaje.dto.MaquillajeImagenDTO;
import com.GuileX.TurnosMaquillaje.dto.PeinadoImagenDTO;
import com.GuileX.TurnosMaquillaje.dto.TurnoDTO;
import com.GuileX.TurnosMaquillaje.entity.MaquillajeImagen;
import com.GuileX.TurnosMaquillaje.entity.PeinadoImagen;
import com.GuileX.TurnosMaquillaje.entity.Turno;
import com.GuileX.TurnosMaquillaje.repository.ITurnoRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TurnoService {

    @Autowired
    private ITurnoRepository turnoRepository;

    public List<TurnoDTO> findAll() {
    	return turnoRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public List<TurnoDTO> getAllTurnosByDate(String date) {
        return turnoRepository.findAllByFechaHora(date)
            .stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }
    
    public TurnoDTO findById(Long id) {
        Optional<Turno> turno = turnoRepository.findById(id);
        return turno.map(this::convertToDTO).orElse(null);
    }
    
    public TurnoDTO saveTurno(TurnoDTO turnoDTO) {
        Turno turno = convertToEntity(turnoDTO);
        Turno turnoGuardado = turnoRepository.save(turno);
        return convertToDTO(turnoGuardado);
    }

    private TurnoDTO convertToDTO(Turno turno) {
        return TurnoDTO.builder()
                .nombre(turno.getNombre())
                .fechaHora(turno.getFechaHora())
                .maquillajePrecio(turno.getMaquillajePrecio())
                .peinadoPrecio(turno.getPeinadoPrecio())
                .maquillajeEstaPago(turno.getMaquillajeEstaPago())
                .peinadoEstaPago(turno.getPeinadoEstaPago())
                .peinadoImagenes(turno.getPeinadoImagenes() != null ? 
                    turno.getPeinadoImagenes().stream().map(peinado -> 
                        PeinadoImagenDTO.builder()
                            .imagen(peinado.getImagen())
                            .build()
                    ).collect(Collectors.toList()) : new ArrayList<>())
                .maquillajeImagenes(turno.getMaquillajeImagenes() != null ? 
                    turno.getMaquillajeImagenes().stream().map(maquillaje -> 
                        MaquillajeImagenDTO.builder()
                            .imagen(maquillaje.getImagen())
                            .build()
                    ).collect(Collectors.toList()) : new ArrayList<>())
                .build();
    }

    
    public void deleteById(Long id) {
        Optional<Turno> turno = turnoRepository.findById(id);
        
        if (turno.isPresent()) {
            turnoRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Turno with ID " + id + " not found");
        }
    }

    
    private Turno convertToEntity(TurnoDTO turnoDTO) {
        Turno turno = new Turno();
        turno.setNombre(turnoDTO.getNombre());
        turno.setFechaHora(turnoDTO.getFechaHora());
        turno.setMaquillajePrecio(turnoDTO.getMaquillajePrecio());
        turno.setPeinadoPrecio(turnoDTO.getPeinadoPrecio());
        turno.setMaquillajeEstaPago(turnoDTO.getMaquillajeEstaPago());
        turno.setPeinadoEstaPago(turnoDTO.getPeinadoEstaPago());
        
        // Convertir y setear las imágenes de peinado
        if (turnoDTO.getPeinadoImagenes() != null) {
            turno.setPeinadoImagenes(
                turnoDTO.getPeinadoImagenes().stream().map(peinadoDTO -> {
                    PeinadoImagen peinado = new PeinadoImagen();
                    peinado.setImagen(peinadoDTO.getImagen());
                    peinado.setTurno(turno); // Asociar al turno
                    return peinado;
                }).collect(Collectors.toList())
            );
        }
        
        // Convertir y setear las imágenes de maquillaje
        if (turnoDTO.getMaquillajeImagenes() != null) {
            turno.setMaquillajeImagenes(
                turnoDTO.getMaquillajeImagenes().stream().map(maquillajeDTO -> {
                    MaquillajeImagen maquillaje = new MaquillajeImagen();
                    maquillaje.setImagen(maquillajeDTO.getImagen());
                    maquillaje.setTurno(turno); // Asociar al turno
                    return maquillaje;
                }).collect(Collectors.toList())
            );
        }

        return turno;
    }

    
}
