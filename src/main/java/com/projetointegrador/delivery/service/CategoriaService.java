package com.projetointegrador.delivery.service;

import com.projetointegrador.delivery.model.Categoria;
import com.projetointegrador.delivery.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<Categoria> listarTodas() {
        return categoriaRepository.findAll();
    }

    public Optional<Categoria> buscarPorId(Long id) {
        return categoriaRepository.findById(id);
    }

    public List<Categoria> buscarPorDescricao(String descricao) {
        return categoriaRepository.findAllByDescricaoContainingIgnoreCase(descricao);
    }

    public Categoria cadastrar(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public Optional<Categoria> atualizar(Long id, Categoria categoria) {
        return categoriaRepository.findById(id).map(categoriaExistente -> {
            categoriaExistente.setDescricao(categoria.getDescricao());
            return categoriaRepository.save(categoriaExistente);
        });
    }

    public boolean deletar(Long id) {
        if (!categoriaRepository.existsById(id)) {
            return false;
        }

        categoriaRepository.deleteById(id);
        return true;
    }
}
