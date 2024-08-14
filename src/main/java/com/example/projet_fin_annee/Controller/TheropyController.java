package com.example.projet_fin_annee.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.projet_fin_annee.Dto.TheropyDTO;
import com.example.projet_fin_annee.Service.ITheropyService;

@RestController
@RequestMapping("/theropies")

public class TheropyController {
	@Autowired
	ITheropyService TheropyService;

	@GetMapping(value = "/getAll")
	public ResponseEntity<List<TheropyDTO>> findAll() {
		return ResponseEntity.ok(TheropyService.findAll());
	}

}
