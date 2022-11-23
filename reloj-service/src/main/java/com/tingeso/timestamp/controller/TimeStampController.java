package com.tingeso.timestamp.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tingeso.timestamp.entity.Timestamp;
import com.tingeso.timestamp.service.TimeStampService;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/timestamps")
public class TimeStampController {
    @Autowired
    private TimeStampService timestampService;

    @GetMapping("/reloj")
	public String reloj() {
		return "reloj";
	}
	
	@PostMapping("/cargar")
	public String carga( @RequestParam("archivos") MultipartFile file, RedirectAttributes ms) {
		upload.save(file);
		ms.addFlashAttribute("mensaje", "Archivo cargado correctamente");
	
		registrationService.uploadFile(file);
		return "redirect:/reloj";
	}
}
