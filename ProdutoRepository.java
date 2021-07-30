package br.org.generation.lojagames.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.generation.lojagames.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	//acessa o banco de dados

	
	public List<Produto> findAllByNomeContainingIgnoreCase(String nome);
	// busca todos pelo nome solicitado e ignora letras maiusculas ou minusculas
}

