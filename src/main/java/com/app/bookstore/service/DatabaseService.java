package com.app.bookstore.service;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.bookstore.domain.Categoria;
import com.app.bookstore.domain.Livro;
import com.app.bookstore.repository.CategoriaRepository;
import com.app.bookstore.repository.LivroRepository;

@Service
public class DatabaseService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private LivroRepository livroRepository;

	public void initialize() {

		Categoria categoria = new Categoria(null, "INFO", "CATEGORIA DE TI");
		Categoria categoria2 = new Categoria(null, "DEVOPS", "CATEGORIA DE DEVOPS");

		Livro livro = new Livro(null, "CLEAN CODE", "AUTOR", "TEXTO...", categoria);
		Livro livro2 = new Livro(null, "CLEAN CODE 2", "AUTOR", "TEXTO...", categoria);

		Livro livro3 = new Livro(null, "DEVOPS CLEAN CODE 3", "AUTOR", "TEXTO...", categoria2);
		Livro livro4 = new Livro(null, "DEVOPS CLEAN CODE 4", "AUTOR", "TEXTO...", categoria2);
		Livro livro5 = new Livro(null, "DEVOPS CLEAN CODE 5", "AUTOR", "TEXTO...", categoria2);
		Livro livro6 = new Livro(null, "DEVOPS CLEAN CODE 6", "AUTOR", "TEXTO...", categoria2);

		categoria.setLivros(new ArrayList<>());
		categoria.getLivros().addAll(Arrays.asList(livro, livro2));

		categoria2.setLivros(new ArrayList<>());
		categoria2.setLivros(Arrays.asList(livro3, livro4, livro5, livro6));

		this.categoriaRepository.saveAll(Arrays.asList(categoria, categoria2));
		this.livroRepository.saveAll(Arrays.asList(livro2, livro3, livro4, livro5, livro6));

	}

}
