package com.app.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.app.bookstore.domain.Categoria;
import com.app.bookstore.dto.CategoriaDTO;
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
	
	public List<Categoria> findAll() {
		return categoriaRepository.findAll();
	}
	
	public Categoria create(Categoria categoria) {
		categoria.setId(null);
		return categoriaRepository.save(categoria);
	}

	public Categoria update(Integer id, CategoriaDTO categoriaDTO) {
		Categoria categoria = findById(id);
		categoria.setNome(categoriaDTO.getNome());
		categoria.setDescricao(categoriaDTO.getDescricao());
		return categoriaRepository.save(categoria);
	}

	public void delete(Integer id) {
		findById(id);
		try {
			categoriaRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new com.app.bookstore.exception.DataIntegrityViolationException("Categoria nao pode ser apagada porque tem livros associados");
		}
		
	}

}
