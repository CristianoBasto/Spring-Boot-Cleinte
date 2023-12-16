package br.com.cris.Cliente.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cris.Cliente.entity.Cliente;
import br.com.cris.Cliente.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienterepository;
	
	public Cliente salvar(Cliente cliente) {
		return clienterepository.save(cliente);	
	}
	
	public List<Cliente> listaCliente(){
		return clienterepository.findAll();
	}
	
	
	public Optional<Cliente> buscarPorId(Long id){
		return clienterepository.findById(id);
		
	}
	
	public void removerPorId(Long id) {
		clienterepository.deleteById(id);
	}
}

