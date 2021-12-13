package com.chicken.rest;

import com.chicken.entity.Equipamentos;
import com.chicken.repository.EquipamentosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/equipamentos")
@CrossOrigin("http://localhost:4200/")
public class EquipamentosController {
    @Autowired
    private EquipamentosRepository equipamentosRepository;


    @Autowired
    public EquipamentosController(EnderecoController enderecoController) {
        this.equipamentosRepository = equipamentosRepository;
    }

    @GetMapping
    public List<Equipamentos> acharTodos() {
        return equipamentosRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Equipamentos salvar(@Valid @RequestBody Equipamentos equipamentos) {
        return equipamentosRepository.save(equipamentos);
    }

    @GetMapping("{id}")
    public Equipamentos acharPorId(@PathVariable Integer id) {
        return equipamentosRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Equipamentos " + id + " não encontrado"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id) {
        equipamentosRepository
                .findById(id)
                .map(endereRuim -> {
                    equipamentosRepository.delete(endereRuim);
                    return Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Endereço " + id + " "));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @Valid @RequestBody Equipamentos dadoDaRequisicao) {
        equipamentosRepository
                .findById(id)
                .map(Equipamentos -> {
                    Equipamentos.setId(dadoDaRequisicao.getId());
                    Equipamentos.setEquipamentosnome(dadoDaRequisicao.getEquipamentosnome());
                    Equipamentos.setDescricao(dadoDaRequisicao.getDescricao());
                    return equipamentosRepository.save(Equipamentos);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Equipamentos   " + id + " inexistente"));
    }
}
