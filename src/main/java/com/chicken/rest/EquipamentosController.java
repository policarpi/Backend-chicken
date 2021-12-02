package com.chicken.rest;

import com.chicken.entity.Enderecos;
import com.chicken.entity.Equipametos;
import com.chicken.repository.EquipamentosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/equipamentos")
public class EquipamentosController {
    @Autowired
    private EquipamentosRepository equipamentosRepository;

    public List<Equipametos> listar(){
        return equipamentosRepository.findAll();
    }

    @Autowired
    public EquipamentosController(EnderecoController enderecoController) {
        this.equipamentosRepository = equipamentosRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Equipametos salvar(@Valid @RequestBody Equipametos equipametos ){
        return equipamentosRepository.save(equipametos);
    }

    @GetMapping("{id}")
    public Equipametos acharPorId(@PathVariable Long id){
        return equipamentosRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Equipamentos " + id + " não encontrado"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id){
        equipamentosRepository
                .findById(id)
                .map(endereRuim -> {
                    equipamentosRepository.delete(endereRuim);
                    return Void.TYPE;
                })
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Endereço " + id + " "));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Long id,@Valid @RequestBody Equipametos dadoDaRequisicao){
        equipamentosRepository
                .findById(id)
                .map(Equipametos -> {
                    Equipametos.setId(dadoDaRequisicao.getId());
                    Equipametos.setNome(dadoDaRequisicao.getNome());
                    return equipamentosRepository.save(Equipametos);
                })
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Equipamentos   " + id + " inexistente"));
    }
}
