package com.projetointegrador.delivery.service;

import com.projetointegrador.delivery.model.Usuario;
import com.projetointegrador.delivery.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public Optional<Usuario> cadastrar(Usuario usuario) {
        if (usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent() || 
            usuarioRepository.findByCpf(usuario.getCpf()).isPresent()) {
            return Optional.empty(); 
        }

        return Optional.of(usuarioRepository.save(usuario));
    }

    public Optional<Usuario> atualizar(Long id, Usuario usuarioAtualizado) {
        return usuarioRepository.findById(id)
                .map(usuarioExistente -> {

                    Optional<Usuario> usuarioComMesmoEmail = usuarioRepository.findByUsuario(usuarioAtualizado.getUsuario());
                    if (usuarioComMesmoEmail.isPresent() && !usuarioComMesmoEmail.get().getId().equals(id)) {
                        throw new IllegalArgumentException("Este e-mail já está cadastrado em outra conta.");
                    }

                    usuarioExistente.setNome(usuarioAtualizado.getNome());
                    usuarioExistente.setUsuario(usuarioAtualizado.getUsuario());
                    
                    if (usuarioAtualizado.getSenha() != null && !usuarioAtualizado.getSenha().isBlank()) {
                        usuarioExistente.setSenha(usuarioAtualizado.getSenha());
                    }
                    
                    return usuarioRepository.save(usuarioExistente);
                });
    }

    public boolean deletar(Long id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }
}