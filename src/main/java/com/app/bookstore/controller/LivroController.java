package com.app.bookstore.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.app.bookstore.domain.Livro;
import com.app.bookstore.dto.LivroDTO;
import com.app.bookstore.service.LivroService;

@CrossOrigin("*")
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
	public ResponseEntity<List<LivroDTO>> findAll(
			@RequestParam(value = "categoria", defaultValue = "0") Integer idCategoria) {
		List<Livro> lista = livroService.findAllByCategoria(idCategoria);
		List<LivroDTO> listaDTO = lista.stream().map(livro -> new LivroDTO(livro)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listaDTO);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Livro> update(@PathVariable Integer id, @Valid @RequestBody Livro livroUpdated) {
		Livro livro = livroService.update(id, livroUpdated);
		return ResponseEntity.ok().body(livro);

	}

	@PatchMapping(value = "/{id}")
	public ResponseEntity<Livro> patch(@PathVariable Integer id, @Valid @RequestBody Livro livroUpdated) {
		Livro livro = livroService.update(id, livroUpdated);
		return ResponseEntity.ok().body(livro);
	}

	@PostMapping
	public ResponseEntity<Livro> create(@RequestParam(value = "categoria", defaultValue = "0") Integer idCategoria,
			@Valid @RequestBody Livro livro) {
		Livro livro2 = livroService.create(idCategoria, livro);
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/livro/{id}").buildAndExpand(livro2.getId()).toUri();
		return ResponseEntity.created(uri).build();

	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete (@PathVariable Integer id) {
		livroService.delete(id);
		return ResponseEntity.noContent().build();
		
	}
}
