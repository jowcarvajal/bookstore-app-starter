package com.app.bookstore.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.bookstore.domain.Categoria;
import com.app.bookstore.exception.ObjectNotFoundException;
import com.app.bookstore.repository.CategoriaRepository;

@Service
public class CategoriaService {

	private CategoriaRepository categoriaRepository;

	@Autowired
	public CategoriaService(CategoriaRepository categoriaRepository) {
		super();
		this.categoriaRepository = categoriaRepository;
	}
	
	public Categoria findById(Integer id) {
		Optional<Categoria> optional =  categoriaRepository.findById(id);
		return optional.orElseThrow(()-> new ObjectNotFoundException(id + " - Objeto Nao Encontrado - " + Categoria.class.getName()));
	}

}
