package com.projetointegrador.delivery.repository;

import java.util.Optional;
import com.projetointegrador.delivery.model.Usuario;

public interface UsuarioRepository {
	Optional<Usuario> findByUsuario(String usuario);

	Optional<Usuario> findByCpf(String cpf);
	
}
