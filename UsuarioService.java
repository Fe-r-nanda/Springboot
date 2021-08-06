package br.org.generation.lojagames.service;

import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.org.generation.lojagames.model.Usuario;
import br.org.generation.lojagames.model.UsuarioLogin;
import br.org.generation.lojagames.repository.UsuarioRepository;

@Service
public class UsuarioService {                   //camadas de serviço como login e cadastro se comunica com banco de dados

	
	@Autowired             // injeção de dependencia
	private UsuarioRepository usuarioRepository;
	
	//criação de metodos pra serviço
	
	public Optional<Usuario> cadastrarUsuario(Usuario usuario){
		
		if(usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent()) //verificando se o usuario existe
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Este Usuário ja existe!", null); // se ja existe gera mensagem de erro
		
		//método pra verificar se o usuario é maior de idade
		//pegando idade
		int idade = Period.between(usuario.getDataNascimento(), LocalDate.now()).getYears();
		//verificando e dando resposta
		if (idade<18)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O Usuário é menor de idade!", null);
		
		//criar objeto de criptpgrafia
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		String senhaEncoder = encoder.encode(usuario.getSenha());               //get senha criptografada
		usuario.setSenha(senhaEncoder);                        //modificar senha criptografada
		// ou seja, substitui a senha do user pela criptografada
		
		//Isso tudo é pra nem o servidor saber a senha
		//Digitou, criptografou na hora e já vai pro banco assim

		return Optional.of(usuarioRepository.save(usuario)); //retorne como optional o usuario solicitado e salve
		
	}
	
public Optional<Usuario> atualizarUsuario(Usuario usuario){
		
		if(usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent()) { //verificando se o usuario existe
			
			int idade = Period.between(usuario.getDataNascimento(), LocalDate.now()).getYears();
			
			if (idade < 18)
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O Usuário é menor de idade!", null);
		
		//criar objeto de criptpgrafia
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		String senhaEncoder = encoder.encode(usuario.getSenha());               //get senha criptografada
		usuario.setSenha(senhaEncoder);                        //modificar senha criptografada
		
		return Optional.of(usuarioRepository.save(usuario)); // precisa existir pra atualizar
		} 
		else {
	throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado!", null); 
	
	// throw serve pr lançar determinada resposta no console
		
	}
}

	public Optional<UsuarioLogin> loginUsuario(Optional<UsuarioLogin> usuarioLogin){
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
	Optional <Usuario> usuario = usuarioRepository.findByUsuario(usuarioLogin.get().getUsuario());
	// get seleciona a classe e dps get o usuario em si // localiza no banco
	
	if (usuario.isPresent()) {
		if (encoder.matches(usuarioLogin.get().getSenha(), usuario.get().getSenha())) {
			
			String auth = usuarioLogin.get().getUsuario() + ":" + usuarioLogin.get().getSenha();
			byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
			String authHeader = "Basic " + new String(encodedAuth);
			
			usuarioLogin.get().setId(usuario.get().getId());
			usuarioLogin.get().setNome(usuario.get().getNome());
			usuarioLogin.get().setSenha(usuario.get().getSenha());
			usuarioLogin.get().setToken(authHeader);
			
			return usuarioLogin;

		}
	}

		throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuário ou senha inválidos!", null); 
	
	}
	
}
