package com.app.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.bookstore.domain.Categoria;
import com.app.bookstore.domain.Livro;
import com.app.bookstore.exception.ObjectNotFoundException;
import com.app.bookstore.repository.LivroRepository;

@Service
public class LivroService {
	private LivroRepository livroRepository;
	private CategoriaService categoriaService;

	@Autowired
	public LivroService(LivroRepository livroRepository, CategoriaService categoriaService) {
		super();
		this.livroRepository = livroRepository;
		this.categoriaService = categoriaService;
	}

	public Livro findById(Integer id) {
		Optional<Livro> optional = livroRepository.findById(id);
		return optional.orElseThrow(
				() -> new ObjectNotFoundException(id + " - Livro nao encotrado : " + Livro.class.getName()));
	}

	public List<Livro> findAllByCategoria(Integer idCategoria) {
		Categoria categoria = categoriaService.findById(idCategoria);
		return livroRepository.findAllByCategoria(categoria);
	}

	public Livro update(Integer id, Livro livroUpdated) {
		Livro livroDatabase = findById(id);
		updateLivroDatabase(livroDatabase, livroUpdated);
		return livroRepository.save(livroDatabase);
	}

	private void updateLivroDatabase(Livro livroDatabase, Livro livroUpdated) {
		livroDatabase.setTitulo(livroUpdated.getTitulo());
		livroDatabase.setAutor(livroUpdated.getAutor());
		livroDatabase.setDescricao(livroUpdated.getDescricao());

	}

}
