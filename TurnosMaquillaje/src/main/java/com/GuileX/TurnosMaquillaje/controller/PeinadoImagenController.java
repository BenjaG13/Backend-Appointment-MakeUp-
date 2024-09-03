package com.GuileX.TurnosMaquillaje.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.GuileX.TurnosMaquillaje.dto.PeinadoImagenDTO;
import com.GuileX.TurnosMaquillaje.service.PeinadoImagenService;

@RestController
@RequestMapping("/api/peinado")
public class PeinadoImagenController {

	 @Autowired
	    private PeinadoImagenService peinadoService;
	 
	 
	 @GetMapping("/findAll")
	 List<PeinadoImagenDTO> findAllPeinados(){
		 return peinadoService.findPeinadosImagenBy();
	 }
	 
	 @PutMapping("/crear")
	 PeinadoImagenDTO savePeinado(@RequestBody PeinadoImagenDTO peinadoDTO){
		 return peinadoService.savePeinado(peinadoDTO);
	 }
	 
}
