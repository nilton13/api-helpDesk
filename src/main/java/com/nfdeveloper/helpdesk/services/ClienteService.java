package com.nfdeveloper.helpdesk.services;

import com.nfdeveloper.helpdesk.domain.Pessoa;
import com.nfdeveloper.helpdesk.domain.Cliente;
import com.nfdeveloper.helpdesk.domain.dtos.ClienteDTO;
import com.nfdeveloper.helpdesk.repositories.ClienteRepository;
import com.nfdeveloper.helpdesk.repositories.PessoaRepository;
import com.nfdeveloper.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.nfdeveloper.helpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;

    public Cliente findById(Integer id){
        Optional<Cliente> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não Encontrado!"));
    }

    public List<Cliente> findAll(){
        return repository.findAll();
    }

    public Cliente create(ClienteDTO objDTO){
        objDTO.setId(null);
        validaPorCpfeEmail(objDTO);
        Cliente newObj = new Cliente(objDTO);
        return repository.save(newObj);
    }

    public Cliente update(Integer id, ClienteDTO objDTO) {
        objDTO.setId(id);
        objDTO.setSenha(encoder.encode(objDTO.getSenha()));
        Cliente oldObj = findById(id);
        validaPorCpfeEmail(objDTO);
        oldObj = new Cliente(objDTO);
        return repository.save(oldObj);
    }

    public void delete(Integer id) {
        Cliente obj = findById(id);
        if(obj.getChamados().size() > 0){
            throw new DataIntegrityViolationException("Cliente possui ordens de serviço e não pode ser deletado");
        }
        repository.delete(obj);
    }

    private void validaPorCpfeEmail(ClienteDTO objDTO) {
        Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
        if(obj.isPresent() && obj.get().getId() != objDTO.getId()){
            throw new DataIntegrityViolationException("Cpf já cadastrado no sistema");
        }

        obj = pessoaRepository.findByEmail(objDTO.getEmail());
        if(obj.isPresent() && obj.get().getId() != objDTO.getId()){
            throw new DataIntegrityViolationException("E-mail já cadastrado no sistema");
        }
    }

}
