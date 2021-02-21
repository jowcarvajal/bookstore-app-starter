package com.app.bookstore.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.bookstore.domain.Livro;
import com.app.bookstore.dto.LivroDTO;
import com.app.bookstore.service.LivroService;

@RestController
@RequestMapping(value = "/livro")
public class LivroController {

	@Autowired
	private LivroService livroService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Livro> findById(@PathVariable Integer id) {
		Livro livro = livroService.findById(id);
		return ResponseEntity.ok().body(livro);
	}

	@GetMapping
	public ResponseEntity<List<LivroDTO>> findAll(@RequestParam(value = "categoria", defaultValue = "0") Integer idCategoria) {
		List<Livro> lista = livroService.findAllByCategoria(idCategoria);
		List<LivroDTO> listaDTO = lista.stream().map(livro -> new LivroDTO(livro)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listaDTO);
	}

	@PutMapping(value="/{id}")
	public ResponseEntity<Livro> update(@PathVariable Integer id, @RequestBody Livro livroUpdated) {
		Livro livro = livroService.update(id, livroUpdated);
		return ResponseEntity.ok().body(livro);
		
		
	}
	
	@PatchMapping(value="/{id}")
	public ResponseEntity<Livro> patch(@PathVariable Integer id, @RequestBody Livro livroUpdated) {
		Livro livro = livroService.update(id, livroUpdated);
		return ResponseEntity.ok().body(livro);
		
		
	}
}
