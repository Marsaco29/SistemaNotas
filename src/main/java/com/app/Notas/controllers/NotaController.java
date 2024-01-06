package com.app.Notas.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.Notas.entity.Nota;
import com.app.Notas.exception.NotFoundException;
import com.app.Notas.repository.NotaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
@RestController
@RequestMapping(value="/api/notas")
public class NotaController {

	@Autowired
	private NotaRepository notaRepository;
	
	@GetMapping("/")
	public List<Nota> getAllNotas(){
		return notaRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Nota getNotaById(@PathVariable String id) {
		return notaRepository.findById(id).orElseThrow(()->new NotFoundException("Nota no encontrada"));
	}
	
	@PostMapping("/")
	public Nota saveNota(@RequestBody Map<String, Object> body) {
		ObjectMapper mapper = new ObjectMapper();
		Nota nota = mapper.convertValue(body, Nota.class);
		return notaRepository.save(nota);
	}
	
	@PutMapping("/{id}")
	public Nota updateNota(@PathVariable String id,@RequestBody Map<String, Object> body) {
		ObjectMapper mapper = new ObjectMapper();
		Nota nota = mapper.convertValue(body, Nota.class);
		nota.setId(id);
		return notaRepository.save(nota);
	}
	
	@DeleteMapping("/{id}")
    public Nota deleteNota(@PathVariable String id) {
		Nota nota = notaRepository.findById(id).orElseThrow(() -> new NotFoundException("Nota no encontrada"));
    	notaRepository.deleteById(id);
        return nota;
    }
}
