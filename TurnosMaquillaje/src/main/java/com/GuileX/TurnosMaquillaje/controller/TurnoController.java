package com.GuileX.TurnosMaquillaje.controller;

import com.GuileX.TurnosMaquillaje.dto.TurnoDTO;
import com.GuileX.TurnosMaquillaje.dto.MaquillajeImagenDTO;
import com.GuileX.TurnosMaquillaje.dto.PeinadoImagenDTO;
import com.GuileX.TurnosMaquillaje.entity.Turno;
import com.GuileX.TurnosMaquillaje.service.TurnoService;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/turnos")
public class TurnoController {

    @Autowired
    private TurnoService turnoService;

    @GetMapping("/getAll")
    public ResponseEntity<List<TurnoDTO>> findAll() {
    	List<TurnoDTO> turnos = turnoService.findAll();
    	if (turnos.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(turnos);
        }
    }
    
    @GetMapping("/date/{date}")
    public ResponseEntity<List<TurnoDTO>> getAllTurnosByDate(@PathVariable String date) {
        List<TurnoDTO> turnos = turnoService.getAllTurnosByDate(date);
        if (turnos.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(turnos);
        }
    } 

    @GetMapping("/turno/{id}")
    public ResponseEntity<TurnoDTO> findByIdTurno(@PathVariable Long id) {
        TurnoDTO turnoDTO = turnoService.findById(id);
        if (turnoDTO != null) {
            return ResponseEntity.ok(turnoDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping("/crear")
    public ResponseEntity<TurnoDTO> crearTurno(@RequestBody TurnoDTO turnoDTO) {
        TurnoDTO turnoCreado = turnoService.saveTurno(turnoDTO);
        return ResponseEntity.ok(turnoCreado);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTurno(@PathVariable Long id) {
        try {
            turnoService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }


}
