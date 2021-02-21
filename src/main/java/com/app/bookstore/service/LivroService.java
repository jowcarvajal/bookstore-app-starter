package com.app.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.bookstore.repository.LivroRepository;

@Service
public class LivroService {
	private LivroRepository livroRepository;

	@Autowired
	public LivroService(LivroRepository livroRepository) {
		super();
		this.livroRepository = livroRepository;
	}
	
	
}
