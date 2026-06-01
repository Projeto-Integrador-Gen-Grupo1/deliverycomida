package com.projetointegrador.delivery.controller;

import com.projetointegrador.delivery.model.Produto;
import com.projetointegrador.delivery.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {
   private final ProdutoService produtoService;
   public ProdutoController(ProdutoService produtoService) {
       this.produtoService = produtoService;
   }
   @GetMapping
   public ResponseEntity<List<Produto>> listarTodos() {
       return ResponseEntity.ok(produtoService.listarTodos());
   }
   @GetMapping("/{id}")
   public ResponseEntity<Produto> buscarPorId(@PathVariable Long id) {
       return produtoService.buscarPorId(id)
               .map(ResponseEntity::ok)
               .orElse(ResponseEntity.notFound().build());
   }
   @GetMapping("/nome/{nome}")
   public ResponseEntity<List<Produto>> buscarPorNome(@PathVariable String nome) {
       return ResponseEntity.ok(produtoService.buscarPorNome(nome));
   }
   @GetMapping("/recomendacoes-saudaveis")
   public ResponseEntity<List<Produto>> recomendarProdutosSaudaveis() {
       return ResponseEntity.ok(produtoService.recomendarProdutosSaudaveis());
   }
   @PostMapping
   public ResponseEntity<Produto> cadastrar(@Valid @RequestBody Produto produto) {
       return produtoService.cadastrar(produto)
               .map(produtoSalvo -> ResponseEntity.status(HttpStatus.CREATED).body(produtoSalvo))
               .orElse(ResponseEntity.badRequest().build());
   }
   @PutMapping("/{id}")
   public ResponseEntity<Produto> atualizar(@PathVariable Long id, @Valid @RequestBody Produto produto) {
       return produtoService.atualizar(id, produto)
               .map(ResponseEntity::ok)
               .orElse(ResponseEntity.notFound().build());
   }
   @DeleteMapping("/{id}")
   public ResponseEntity<Void> deletar(@PathVariable Long id) {
       if (!produtoService.deletar(id)) {
           return ResponseEntity.notFound().build();
       }
       return ResponseEntity.noContent().build();
   }
}

