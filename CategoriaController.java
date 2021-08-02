package br.org.generation.farmacia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import br.org.generation.farmacia.model.Categoria;
import br.org.generation.farmacia.repository.CategoriaRepository;

@RestController // rest é o modo do controller que usa os verbos get,put,post,delete
@RequestMapping("/categorias")  //palavra chave da requisicao e mapeia o end point
@CrossOrigin(origins="*", allowedHeaders="*") // libera o controller
public class CategoriaController {
	
	@Autowired
	private CategoriaRepository categoriaRepository; // criando o objeto

	@GetMapping
	private ResponseEntity<List<Categoria>> getAll(){
		return ResponseEntity.ok(categoriaRepository.findAll()); // ok pq sempre traz lista msm nula
	}
	
	@GetMapping("/{id}")
	private ResponseEntity<Categoria> getById(@PathVariable long id){
		return categoriaRepository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/tipo/{tipo}")
	public ResponseEntity<List<Categoria>> getByTipo(@PathVariable String tipo){
		return ResponseEntity.ok(categoriaRepository.findAllByTipoContainingIgnoreCase(tipo));
	}
	
	@PostMapping 
	public ResponseEntity<Categoria> post (@RequestBody Categoria categoria){
	return ResponseEntity.status(HttpStatus.CREATED).body(categoriaRepository.save(categoria));
}
	
	@PutMapping 
	public ResponseEntity<Categoria> put (@RequestBody Categoria categoria){
		return ResponseEntity.status(HttpStatus.OK).body(categoriaRepository.save(categoria));
	}
	
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable long id) {
		categoriaRepository.deleteById(id);
	}
}
