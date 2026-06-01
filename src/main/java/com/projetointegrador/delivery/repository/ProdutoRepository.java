package com.projetointegrador.delivery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.projetointegrador.delivery.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	List<Produto> findAllByNomeContainingIgnoreCase(String nome);

    List<Produto> findAllByDisponivelTrue();

}
