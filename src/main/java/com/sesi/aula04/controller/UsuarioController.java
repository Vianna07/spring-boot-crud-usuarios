package com.sesi.aula04.controller;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.sesi.aula04.Repository.UsuarioRepository;
import com.sesi.aula04.model.Usuario;


@Controller
public class UsuarioController {
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	@GetMapping("/usuarios")
	public String listarUsuario(Model modelo) {
		modelo.addAttribute("usuarios", usuarioRepository.findAll());
		
		return "usuarios";
	}
	
	
	@GetMapping("/formulario")
	public String formulario(Model modelo) {
		modelo.addAttribute("usuario", new Usuario());
		return "formulario";
	}
	
	@PostMapping("/salvar-usuario")
	public String salvarUsuario(@ModelAttribute Usuario usuario) {
		usuarioRepository.save(usuario);
		return "redirect:/usuarios";
	}
	
	@GetMapping("/deletar-usuario/{id}")
	public String deletarUsuario (@PathVariable int id) {
		usuarioRepository.deleteById(id);
		return "redirect:/usuarios";
	}
	
	@GetMapping("/editar-usuario/{id}")
	public String editarUsuario (@PathVariable int id, Model modelo) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		
		if (usuario.isPresent()) {
			modelo.addAttribute("usuario", usuario.get());
			return "formulario";
		}

		return "redirect:/usuarios";
	}
}
