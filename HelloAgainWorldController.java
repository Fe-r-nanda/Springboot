package br.org.generation.helloagainworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/helloagain")
public class HelloAgainWorldController {

	@GetMapping
	public String helloagain() {
		return "Mentalidade e habilidade usada 23/07/21: Persistência e Atenção aos detalhes!";
	}
}
