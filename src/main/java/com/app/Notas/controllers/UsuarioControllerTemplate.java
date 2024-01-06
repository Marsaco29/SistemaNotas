package com.app.Notas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.Notas.entity.Usuario;
import com.app.Notas.exception.NotFoundException;
import com.app.Notas.repository.UsuarioRepository;



@Controller 
@RequestMapping("/usuarios")
public class UsuarioControllerTemplate {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping("/")
	public String UsuarioListTemplate(Model model) {
		model.addAttribute("usuarios", usuarioRepository.findAll());
		return "usuListar";
	}
	
	@GetMapping("/home")
	public String LoginTemplatec(Model model) {
		return "home";
	} 
	@GetMapping("/new")
	public String UsuariosNewTemplate(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "usuAgregar";
	}
	
	@GetMapping("/edit/{id}")
	public String UsuarioEditTemplate(@PathVariable("id") String id, Model model) {
		model.addAttribute("usuario",
				usuarioRepository.findById(id).orElseThrow(() -> new NotFoundException("Usuario no encontrado")));
		return "usuAgregar";
	}

	@PostMapping("/save")
	public String UsuariosSaveProcess(@ModelAttribute("usuario") Usuario usuario) {
	    if (usuario.getId() != null && !usuario.getId().isEmpty()) {
	    	usuarioRepository.save(usuario);
	    } else {
	    	usuario.setId(null);
	    	usuarioRepository.save(usuario);
	    }
	    return "redirect:/usuarios/home";
	}

	
	@GetMapping("/delete/{id}")
	public String UsuariosDeleteProcess(@PathVariable("id") String id) {
		usuarioRepository.deleteById(id);
		return "redirect:/usuarios/";
	}
	
	@PostMapping("/login")
	public String login(@RequestParam("user") String user,
			@RequestParam("password") String password, Model model) {
		// Verificar las credenciales
		System.out.println("user: " + user + " password:" + password);

		Usuario usuario = usuarioRepository.findByUserAndPassword(user, password);
		if (usuario != null) {
			// Inicio de sesión exitoso, redirigir a la página de resultado con la variable
			// en la URL
			System.out.println(
					"Usuario: " + usuario.getUser() + " Contraseña:" + usuario.getPassword());
			return  "redirect:home";
		} else {
			// Inicio de sesión fallido, mostrar mensaje de error en la página de inicio
			model.addAttribute("authenticationFailed", true);
			model.addAttribute("errorMessage", "No se encontró ningún usuario");
			return "login";
		}
	}
	
}
