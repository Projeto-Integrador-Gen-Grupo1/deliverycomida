package com.projetointegrador.delivery.controller;

import com.projetointegrador.delivery.model.Categoria;
import com.projetointegrador.delivery.service.CategoriaService;
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
@RequestMapping("/categorias")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriaController {
   private final CategoriaService categoriaService;
   public CategoriaController(CategoriaService categoriaService) {
       this.categoriaService = categoriaService;
   }
   @GetMapping
   public ResponseEntity<List<Categoria>> listarTodas() {
       return ResponseEntity.ok(categoriaService.listarTodas());
   }
   @GetMapping("/{id}")
   public ResponseEntity<Categoria> buscarPorId(@PathVariable Long id) {
       return categoriaService.buscarPorId(id)
               .map(ResponseEntity::ok)
               .orElse(ResponseEntity.notFound().build());
   }
   @GetMapping("/descricao/{descricao}")
   public ResponseEntity<List<Categoria>> buscarPorDescricao(@PathVariable String descricao) {
       return ResponseEntity.ok(categoriaService.buscarPorDescricao(descricao));
   }
   @PostMapping
   public ResponseEntity<Categoria> cadastrar(@Valid @RequestBody Categoria categoria) {
       return ResponseEntity.status(HttpStatus.CREATED).body(categoriaService.cadastrar(categoria));
   }
   @PutMapping("/{id}")
   public ResponseEntity<Categoria> atualizar(@PathVariable Long id, @Valid @RequestBody Categoria categoria) {
       return categoriaService.atualizar(id, categoria)
               .map(ResponseEntity::ok)
               .orElse(ResponseEntity.notFound().build());
   }
   @DeleteMapping("/{id}")
   public ResponseEntity<Void> deletar(@PathVariable Long id) {
       if (!categoriaService.deletar(id)) {
           return ResponseEntity.notFound().build();
       }
       return ResponseEntity.noContent().build();
   }
}
