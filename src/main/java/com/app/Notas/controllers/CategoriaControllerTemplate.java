package com.app.Notas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.Notas.entity.Categoria;
import com.app.Notas.exception.NotFoundException;
import com.app.Notas.repository.CategoriaRepository;



@Controller 
@RequestMapping("/categorias")
public class CategoriaControllerTemplate {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@GetMapping("/")
	public String categoriaListTemplate(Model model) {
		model.addAttribute("categorias", categoriaRepository.findAll());
		return "catListar";
	}
	
	@GetMapping("/new")
	public String categoriasNewTemplate(Model model) {
		model.addAttribute("categoria", new Categoria());
		return "catAgregar";
	}
	
	@GetMapping("/edit/{id}")
	public String categoriaEditTemplate(@PathVariable("id") String id, Model model) {
		model.addAttribute("categoria",
				categoriaRepository.findById(id).orElseThrow(() -> new NotFoundException("Categoria no encontrada")));
		return "catAgregar";
	}

	@PostMapping("/save")
	public String categoriasSaveProcess(@ModelAttribute("nota") Categoria categoria) {
	    if (categoria.getId() != null && !categoria.getId().isEmpty()) {
	    	categoriaRepository.save(categoria);
	    } else {
	    	categoria.setId(null);
	    	categoriaRepository.save(categoria);
	    }
	    return "redirect:/categorias/";
	}
	
	@GetMapping("/delete/{id}")
	public String categoriasDeleteProcess(@PathVariable("id") String id) {
		categoriaRepository.deleteById(id);
		return "redirect:/categorias/";
	}
}
