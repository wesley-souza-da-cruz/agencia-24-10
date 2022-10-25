package com.example.demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@CrossOrigin(origins = "http://localhost:3030")
@RestController
@RequestMapping("/api/v1/")
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	

	//Pegar todos os clientes
	@GetMapping("/clientes")
	public List<Cliente>getAllCliente() {
		return clienteRepository.findAll();
	}

	
	
	//Pegar um cliente usando seu ID
	@GetMapping("clientes/{id}")
	public ResponseEntity<Cliente>getClienteById(@PathVariable Long id) {
		Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cliente nao existe com o id : " + id));
		return ResponseEntity.ok(cliente);
	}
	

	
	//criar novo cliente
	@PostMapping("/cliente")
	public Cliente createCliente(@RequestBody Cliente cliente) {
		return clienteRepository.save(cliente);
		
	}
	

	
	//alterar cliente usando seu ID
	@PutMapping("/cliente/{id}")
	public ResponseEntity<Cliente> updateCliente(@PathVariable Long id, @RequestBody Cliente clienteDetails) {
		Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cliente nao existe com o id : " + id));
		
		cliente.setPrimeiroNome(clienteDetails.getPrimeiroNome());
		cliente.setUltimoNome(clienteDetails.getUltimoNome());
		cliente.setEmailId(clienteDetails.getEmailId());
		
		Cliente updatedCliente = clienteRepository.save(cliente);
		return ResponseEntity.ok(updatedCliente);
		
	}
	
}
	

	
