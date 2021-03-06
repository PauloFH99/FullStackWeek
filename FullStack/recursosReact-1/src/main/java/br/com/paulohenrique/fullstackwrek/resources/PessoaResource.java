package br.com.paulohenrique.fullstackwrek.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.paulohenrique.fullstackwrek.domain.Pessoa;
import br.com.paulohenrique.fullstackwrek.respository.PessoaRepository;

@RestController
@RequestMapping("/pessoas")
@CrossOrigin(origins = {"http://localhost:3000"})
public class PessoaResource {
	
	@Autowired
	private PessoaRepository pessoaRepositoy;
	
	@GetMapping
	public List<Pessoa> listarTodos(){
		return pessoaRepositoy.findAll();
	}
	
	@PostMapping
	public Pessoa cadatrarPessoa(@RequestBody Pessoa pessoa){
		return pessoaRepositoy.save(pessoa);
	}
	

	@PutMapping("/{codigo}")
	public Pessoa atualizar(@PathVariable ("codigo") Long codigo,@RequestBody Pessoa pessoa){
		return pessoaRepositoy.findById(codigo).map(
				record -> {
					record.setCpf(pessoa.getCpf());
					record.setDataNascimento(pessoa.getDataNascimento());
					record.setEmail(pessoa.getEmail());
					record.setIdade(pessoa.getIdade());
					record.setNome(pessoa.getNome());
					record.setTelefone(pessoa.getTelefone());
					record.setIsVacinada(pessoa.getIsVacinada());
					return pessoaRepositoy.save(record);
				}).orElse(null);
	}
	
	@GetMapping("/{codigo}")
	public Pessoa buscarPeloCodigo(@PathVariable ("codigo") Long codigo){
		return pessoaRepositoy.findById(codigo).orElse(null);
	}
}
