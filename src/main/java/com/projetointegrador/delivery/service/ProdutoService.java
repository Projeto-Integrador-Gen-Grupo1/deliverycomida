package com.projetointegrador.delivery.service;

import com.projetointegrador.delivery.model.Produto;
import com.projetointegrador.delivery.repository.CategoriaRepository;
import com.projetointegrador.delivery.repository.ProdutoRepository;
import com.projetointegrador.delivery.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;
    private final UsuarioRepository usuarioRepository;

    public ProdutoService(
            ProdutoRepository produtoRepository,
            CategoriaRepository categoriaRepository,
            UsuarioRepository usuarioRepository
    ) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    public Optional<Produto> buscarPorId(Long id) {
        return produtoRepository.findById(id);
    }

    public List<Produto> buscarPorNome(String nome) {
        return produtoRepository.findAllByNomeContainingIgnoreCase(nome);
    }

    public Optional<Produto> cadastrar(Produto produto) {
        if (produto.getCategoria() == null || produto.getCategoria().getId() == null) {
            return Optional.empty();
        }

        if (produto.getUsuario() == null || produto.getUsuario().getId() == null) {
            return Optional.empty();
        }

        var categoria = categoriaRepository.findById(produto.getCategoria().getId());
        var usuario = usuarioRepository.findById(produto.getUsuario().getId());

        if (categoria.isEmpty() || usuario.isEmpty()) {
            return Optional.empty();
        }

        produto.setCategoria(categoria.get());
        produto.setUsuario(usuario.get());

        return Optional.of(produtoRepository.save(produto));
    }

    public Optional<Produto> atualizar(Long id, Produto produto) {
        return produtoRepository.findById(id).flatMap(produtoExistente -> {
            produto.setId(id);
            return cadastrar(produto);
        });
    }

    public boolean deletar(Long id) {
        if (!produtoRepository.existsById(id)) {
            return false;
        }

        produtoRepository.deleteById(id);
        return true;
    }

    public List<Produto> recomendarProdutosSaudaveis() {
        return produtoRepository.findAllByDisponivelTrue().stream()
                .filter(this::produtoTemPerfilSaudavel)
                .sorted(Comparator
                        .comparing(Produto::getSaudavel, Comparator.reverseOrder())
                        .thenComparing(Produto::getPreco))
                .toList();
    }

    private boolean produtoTemPerfilSaudavel(Produto produto) {
        if (Boolean.TRUE.equals(produto.getSaudavel())) {
            return true;
        }

        String texto = (produto.getNome() + " "
                + produto.getDescricao() + " "
                + produto.getIngredientes()).toLowerCase();

        return texto.contains("salada")
                || texto.contains("integral")
                || texto.contains("grelhado")
                || texto.contains("natural")
                || texto.contains("fruta")
                || texto.contains("legume")
                || texto.contains("proteina");
    }
}