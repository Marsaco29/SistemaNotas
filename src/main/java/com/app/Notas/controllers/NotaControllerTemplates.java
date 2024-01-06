package com.app.Notas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.Notas.entity.Categoria;
import com.app.Notas.entity.Nota;
import com.app.Notas.exception.NotFoundException;
import com.app.Notas.repository.CategoriaRepository;
import com.app.Notas.repository.NotaRepository;

@Controller
@RequestMapping("/notas")
public class NotaControllerTemplates {

	@Autowired
	private NotaRepository notaRepository;
	@Autowired
	private CategoriaRepository categoriaRepository;

	@GetMapping("/")
	public String notaListTemplate(Model model) {
		model.addAttribute("notas", notaRepository.findAll());
		return "notaListar";
	}

	@GetMapping("/new")
	public String NotasNewTemplate(Model model, Model categoriaModel) {

		List<Categoria> categorias = categoriaRepository.findAll();
		categoriaModel.addAttribute("categorias", categorias);

		model.addAttribute("nota", new Nota());
		return "notaAgregar";
	}

	@GetMapping("/edit/{id}")
	public String notaEditTemplate(@PathVariable("id") String id, Model model, Model categoriaModel) {
		model.addAttribute("nota",
				notaRepository.findById(id).orElseThrow(() -> new NotFoundException("Nota no encontrada")));

		List<Categoria> categorias = categoriaRepository.findAll();
		categoriaModel.addAttribute("categorias", categorias);

		return "notaAgregar";
	}

	@PostMapping("/save")
	public String notasSaveProcess(@ModelAttribute("nota") Nota nota) {
		if (nota.getId() != null && !nota.getId().isEmpty()) {
			// Nota existente, actualízala
			notaRepository.save(nota);
		} else {
			// Nota nueva, establece el ID en null antes de guardar
			nota.setId(null);
			notaRepository.save(nota);
		}
		return "redirect:/notas/";
	}

	@GetMapping("/delete/{id}")
	public String notasDeleteProcess(@PathVariable("id") String id) {
		notaRepository.deleteById(id);
		return "redirect:/notas/";
	}

	@PostMapping("/archivar/{id}")
	public String archivarNota(@PathVariable("id") String id) {
		Nota nota = notaRepository.findById(id).orElseThrow(() -> new NotFoundException("Nota no encontrada"));
		nota.setArchivada(!nota.isArchivada());
		notaRepository.save(nota);
		return "redirect:/notas/";
	}

	@GetMapping("/activas")
	public String listarNotasActivas(Model model) {
		model.addAttribute("notas", notaRepository.findByArchivada(false));
		return "notaListar";
	}

	@GetMapping("/archivadas")
	public String listarNotasArchivadas(Model model) {
		model.addAttribute("notas", notaRepository.findByArchivada(true));
		return "notaListar";
	}

}
