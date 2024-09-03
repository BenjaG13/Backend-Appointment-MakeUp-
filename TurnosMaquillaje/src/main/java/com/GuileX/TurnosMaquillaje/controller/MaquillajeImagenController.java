package com.GuileX.TurnosMaquillaje.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.GuileX.TurnosMaquillaje.dto.MaquillajeImagenDTO;
import com.GuileX.TurnosMaquillaje.service.MaquillajeImagenService;

@RestController
@RequestMapping("/api/maquillaje")
public class MaquillajeImagenController {

    @Autowired
    private MaquillajeImagenService maquillajeService;
    
    @GetMapping("/findAll")
    List<MaquillajeImagenDTO> findAllMaquillajes(){
        return maquillajeService.findMaquillajesImagenBy();
    }
    
    @PutMapping("/crear")
    MaquillajeImagenDTO saveMaquillaje(@RequestBody MaquillajeImagenDTO maquillajeDTO){
        return maquillajeService.saveMaquillaje(maquillajeDTO);
    }
    
}

