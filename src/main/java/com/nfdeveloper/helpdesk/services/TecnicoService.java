package com.nfdeveloper.helpdesk.services;

import com.nfdeveloper.helpdesk.domain.Tecnico;
import com.nfdeveloper.helpdesk.domain.dtos.TecnicoDTO;
import com.nfdeveloper.helpdesk.repositories.TecnicoRepository;
import com.nfdeveloper.helpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository repository;

    public Tecnico findById(Integer id){
        Optional<Tecnico> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não Encontrado!"));
    }

    public List<Tecnico> findAll(){
        return repository.findAll();
    }

    public Tecnico create(TecnicoDTO objDTO){
        objDTO.setId(null);
        Tecnico newObj = new Tecnico(objDTO);
        return repository.save(newObj);
    }
}
