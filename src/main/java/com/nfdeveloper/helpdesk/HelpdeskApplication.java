package com.nfdeveloper.helpdesk;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nfdeveloper.helpdesk.domain.Chamado;
import com.nfdeveloper.helpdesk.domain.Cliente;
import com.nfdeveloper.helpdesk.domain.Tecnico;
import com.nfdeveloper.helpdesk.domain.enums.Perfil;
import com.nfdeveloper.helpdesk.domain.enums.Prioridade;
import com.nfdeveloper.helpdesk.domain.enums.Status;
import com.nfdeveloper.helpdesk.repositories.ChamadoRepository;
import com.nfdeveloper.helpdesk.repositories.ClienteRepository;
import com.nfdeveloper.helpdesk.repositories.TecnicoRepository;

@SpringBootApplication
public class HelpdeskApplication implements CommandLineRunner {

	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ChamadoRepository chamadoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(HelpdeskApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Tecnico tec1 = new Tecnico(null, "Nilton", "1233212222", "nilton@email.com", "123");
		tec1.addPerfil(Perfil.ADMIN);
		
		Cliente cli1 = new Cliente(null, "Naiane", "32131231231", "naiane@email.com", "321");
		
		Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro chamado", tec1, cli1);
		
		tecnicoRepository.saveAll(Arrays.asList(tec1));
		clienteRepository.saveAll(Arrays.asList(cli1));
		chamadoRepository.saveAll(Arrays.asList(c1));
	}

}
