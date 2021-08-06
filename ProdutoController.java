package br.org.generation.lojagames.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.generation.lojagames.model.Produto;
import br.org.generation.lojagames.repository.ProdutoRepository;

@RestController // anotacao para api do tipo rest = responde requisicoes tipo post,put,get,delete
@RequestMapping("/produto")        //mapeia o end point (que deve ser sempre minusculo)
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository produtoRepository; // objeto do tipo tal
	
	@GetMapping
	public ResponseEntity<List<Produto>> getAll(){ //tab de resposta - lista tds - tipo produto - todos
		return ResponseEntity.ok(produtoRepository.findAll());
	}
	@GetMapping("/{id}")
	public ResponseEntity<Produto> getById(@PathVariable long id){ //lambda
		return produtoRepository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Produto>> getByName(@PathVariable String nome){
		return ResponseEntity.ok(produtoRepository.findAllByNomeContainingIgnoreCase(nome));
	}
	@PostMapping
	public ResponseEntity<Produto> post(@RequestBody Produto produto){
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto));
		// mesmo que o modo com .ok mas esse mostra o corpo tambem - veja exemplo abaixo
	}
	@PutMapping
	public ResponseEntity<Produto> put(@RequestBody Produto produto){
		return ResponseEntity.ok(produtoRepository.save(produto));
	}
	@DeleteMapping
	public void delete(@PathVariable long id) {
		produtoRepository.deleteById(id);
	}

	
}
