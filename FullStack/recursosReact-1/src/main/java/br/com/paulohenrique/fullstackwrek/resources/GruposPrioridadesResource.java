package br.com.paulohenrique.fullstackwrek.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.paulohenrique.fullstackwrek.domain.GruposPrioridades;
import br.com.paulohenrique.fullstackwrek.respository.GruposPrioridadesRepository;


@RestController
@RequestMapping("/gruposprioridades")
public class GruposPrioridadesResource {
	
	@Autowired
	private GruposPrioridadesRepository gruposPrioridadesRepositoy;
	
	@GetMapping
	public List<GruposPrioridades> listarTodos(){
		return gruposPrioridadesRepositoy.findAll();
	}
	
	@PostMapping
	public GruposPrioridades cadatrarGruposPrioridade(@RequestBody GruposPrioridades gruposprioridades){
		return gruposPrioridadesRepositoy.save(gruposprioridades);
	}
	

	@PutMapping("/{codigo}")
	public GruposPrioridades atualizar(@PathVariable ("codigo") Long codigo,@RequestBody GruposPrioridades gruposprioridades){
		return gruposPrioridadesRepositoy.findById(codigo).map(
				record -> {
					record.setDescricao(gruposprioridades.getDescricao());
					record.setNome(gruposprioridades.getNome());
					return gruposPrioridadesRepositoy.save(record);
				}).orElse(null);
	}
	
	@GetMapping("/{codigo}")
	public GruposPrioridades buscarPeloCodigo(@PathVariable ("codigo") Long codigo){
		return gruposPrioridadesRepositoy.findById(codigo).orElse(null);
	}

}
