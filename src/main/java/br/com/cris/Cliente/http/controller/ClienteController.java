package br.com.cris.Cliente.http.controller;

import java.util.List;
import java.util.function.Function;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.cris.Cliente.entity.Cliente;
import br.com.cris.Cliente.service.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
	
	
	@Autowired
   	private  ClienteService clienteservice;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente salvar(@RequestBody Cliente cliente) {
		return clienteservice.salvar(cliente);
	}
    
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Cliente> listaCliente(){
		return clienteservice.listaCliente();
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Cliente buscarClientePorId(@PathVariable("id")Long id) {
		return clienteservice.buscarPorId(id)
				.orElseThrow(() -> new 
				ResponseStatusException(HttpStatus.NOT_FOUND, " Cliente nao encontrado")); 
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removerCliente(Long id){
		clienteservice.buscarPorId(id)
		.map(cliente -> {
			clienteservice.removerPorId(cliente.getId());
			return Void.TYPE;
		}).orElseThrow(() -> new 
				ResponseStatusException(HttpStatus.NOT_FOUND, " Cliente nao encontrado"));
		
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizarCliente(@PathVariable("id")Long id,@RequestBody Cliente cliente) {
		clienteservice.buscarPorId(id)
		.map(clienteBase -> {
			modelMapper.map(cliente, clienteBase);
			clienteservice.salvar(clienteBase);
			return Void.TYPE;
		}).orElseThrow(() -> new 
				ResponseStatusException(HttpStatus.NOT_FOUND, " Cliente nao encontrado"));
	}
	
}
