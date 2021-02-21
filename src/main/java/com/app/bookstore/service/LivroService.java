package com.app.bookstore.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.bookstore.domain.Livro;
import com.app.bookstore.exception.ObjectNotFoundException;
import com.app.bookstore.repository.LivroRepository;

@Service
public class LivroService {
	private LivroRepository livroRepository;

	@Autowired
	public LivroService(LivroRepository livroRepository) {
		super();
		this.livroRepository = livroRepository;
	}
	
	
	public Livro findById(Integer id) {
		Optional<Livro> optional = livroRepository.findById(id);
		return optional.orElseThrow(()-> new ObjectNotFoundException(id + " - Livro nao encotrado : " + Livro.class.getName()));
	}
	
	
}
