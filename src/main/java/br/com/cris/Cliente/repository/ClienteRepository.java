package br.com.cris.Cliente.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.cris.Cliente.entity.Cliente;

public interface ClienteRepository extends JpaRepository< Cliente, Long>{
	
	

}
