package com.projetointegrador.delivery.controller;

import com.projetointegrador.delivery.model.Produto;
import com.projetointegrador.delivery.model.Usuario;
import com.projetointegrador.delivery.service.UsuarioService;
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
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {
   private final UsuarioService usuarioService;
   public UsuarioController(UsuarioService usuarioService) {
       this.usuarioService = usuarioService;
   }
   @GetMapping
   public ResponseEntity<List<Usuario>> listarTodos() {
       return ResponseEntity.ok(usuarioService.listarTodos());
   }
   @GetMapping("/{id}")
   public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
       return usuarioService.buscarPorId(id)
               .map(ResponseEntity::ok)
               .orElse(ResponseEntity.notFound().build());
   }
   @PostMapping
   public ResponseEntity<Usuario> cadastrar(@Valid @RequestBody Usuario usuario) {
       return usuarioService.cadastrar(usuario)
               .map(usuarioSalvo -> ResponseEntity.status(HttpStatus.CREATED).body(usuarioSalvo))
               .orElse(ResponseEntity.status(HttpStatus.CONFLICT).build());
   }
  
   @PutMapping("/{id}")
   public ResponseEntity<Usuario> atualizar(@PathVariable Long id, @Valid @RequestBody Usuario usuario) {
       return usuarioService.atualizar(id, usuario)
               .map(ResponseEntity::ok)
               .orElse(ResponseEntity.notFound().build());
   }
   @DeleteMapping("/{id}")
   public ResponseEntity<Void> deletar(@PathVariable Long id) {
       if (!usuarioService.deletar(id)) {
           return ResponseEntity.notFound().build();
       }
       return ResponseEntity.noContent().build();
   }
}


