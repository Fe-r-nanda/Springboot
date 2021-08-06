package br.org.generation.lojagames.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.generation.lojagames.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {  //parametros //usuario = classe // long = chave primaria
	
	public Optional<Usuario> findByUsuario (String usuario); //optional p caso nao encontrar um usuario, aceitar nulo
	
	

}
