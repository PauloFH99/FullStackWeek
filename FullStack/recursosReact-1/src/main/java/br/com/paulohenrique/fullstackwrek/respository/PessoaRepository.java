package br.com.paulohenrique.fullstackwrek.respository;

import javax.persistence.Entity;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.paulohenrique.fullstackwrek.domain.Pessoa;


public interface PessoaRepository extends JpaRepository<Pessoa,Long>{

}
