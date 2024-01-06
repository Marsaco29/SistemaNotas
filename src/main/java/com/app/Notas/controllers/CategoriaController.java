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

import com.app.Notas.entity.Categoria;
import com.app.Notas.exception.NotFoundException;
import com.app.Notas.repository.CategoriaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(value="/api/categorias")
public class CategoriaController {
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@GetMapping("/")
	public List<Categoria> getAllCategorias(){
		return categoriaRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Categoria getCategoriaById(@PathVariable String id) {
		return categoriaRepository.findById(id).orElseThrow(()->new NotFoundException("Categoria no encontrada"));
	}
	
	@PostMapping("/")
	public Categoria saveCategoria(@RequestBody Map<String, Object> body) {
		ObjectMapper mapper = new ObjectMapper();
		Categoria categoria = mapper.convertValue(body, Categoria.class);
		return categoriaRepository.save(categoria);
	}
	
	@PutMapping("/{id}")
	public Categoria updateCategoria(@PathVariable String id,@RequestBody Map<String, Object> body) {
		ObjectMapper mapper = new ObjectMapper();
		Categoria categoria = mapper.convertValue(body, Categoria.class);
		categoriaRepository.deleteById(id);
		return categoria;
	}
	
	@DeleteMapping("/{id}")
    public Categoria deleteCategoria(@PathVariable String id) {
		Categoria categoria = categoriaRepository.findById(id).orElseThrow(() -> new NotFoundException("Categoria no encontrada"));
		categoriaRepository.deleteById(id);
        return categoria;
    }
}