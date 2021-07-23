package br.org.generation.helloonceagainsworld;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hifriend")
public class HelloOnceAgainController {

	@GetMapping
	public String helloagain() {
		return "Meus maiores objetivos consistem em aprender realmente sobre Springboot e desenvolver com facilidade essas aplicações web!";
	}
}



