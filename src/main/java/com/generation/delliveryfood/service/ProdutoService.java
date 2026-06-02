package com.generation.delliveryfood.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.delliveryfood.model.Produto;
import com.generation.delliveryfood.repository.CategoriaRepository;
import com.generation.delliveryfood.repository.ProdutoRepository;
import com.generation.delliveryfood.repository.UsuarioRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Produto> getAll() {
        return produtoRepository.findAll();
    }

    public Optional<Produto> getById(Long id) {
        return produtoRepository.findById(id);
    }

    public List<Produto> getByNome(String nome) {
        return produtoRepository.findAllByNomeContainingIgnoreCase(nome);
    }

    public Optional<Produto> cadastrarProduto(Produto produto) {

        if (!categoriaRepository.existsById(produto.getCategoria().getId()))
            return Optional.empty();

        if (!usuarioRepository.existsById(produto.getUsuario().getId()))
            return Optional.empty();

        produto.setId(null);

        return Optional.of(produtoRepository.save(produto));
    }

    public Optional<Produto> atualizarProduto(Produto produto) {

        if (!produtoRepository.existsById(produto.getId()))
            return Optional.empty();

        if (!categoriaRepository.existsById(produto.getCategoria().getId()))
            return Optional.empty();

        if (!usuarioRepository.existsById(produto.getUsuario().getId()))
            return Optional.empty();

        return Optional.of(produtoRepository.save(produto));
    }

    public void deletarProduto(Long id) {
        produtoRepository.deleteById(id);
    }
}