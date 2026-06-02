package com.generation.delliveryfood.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.delliveryfood.model.Categoria;
import com.generation.delliveryfood.repository.CategoriaRepository;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> getAll() {
        return categoriaRepository.findAll();
    }

    public Optional<Categoria> getById(Long id) {
        return categoriaRepository.findById(id);
    }

    public List<Categoria> getByDescricao(String descricao) {
        return categoriaRepository.findAllByDescricaoContainingIgnoreCase(descricao);
    }

    public Categoria cadastrarCategoria(Categoria categoria) {
        categoria.setId(null);
        return categoriaRepository.save(categoria);
    }

    public Optional<Categoria> atualizarCategoria(Categoria categoria) {

        if (!categoriaRepository.existsById(categoria.getId()))
            return Optional.empty();

        return Optional.of(categoriaRepository.save(categoria));
    }

    public void deletarCategoria(Long id) {
        categoriaRepository.deleteById(id);
    }
}