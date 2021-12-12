package com.chicken.rest;


import com.chicken.entity.Dieta;
import com.chicken.repository.DietaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/dieta")
@CrossOrigin("http://localhost:4200/")
public class DietaController {

    private DietaRepository dietaRepository;

    @GetMapping
    public List <Dieta> pesquisar(
            @RequestParam(value = "nome", required = false, defaultValue = "") String nome){
        return  dietaRepository.findByNomePessoa("%" + nome + "%");
    }


    /*
    @GetMapping
    public List <Dieta> acharTodos(){
        return dietaRepository.findAll();
    }*/

    @Autowired
    public DietaController(DietaRepository dietaRepository) {
        this.dietaRepository = dietaRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Dieta salvar(@Valid @RequestBody Dieta dieta){
        return dietaRepository.save(dieta);
    }

    @GetMapping("{id}")
    public Dieta acharPorId(@PathVariable Long id){
        return dietaRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Dieta " + id + " não encontrada"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id){
        dietaRepository
                .findById(id)
                .map(diateRuim -> {
                    dietaRepository.delete(diateRuim);
                    return Void.TYPE;
                })
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Dieta " + id + " não ruim"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Long id,@Valid @RequestBody Dieta dadoDaRequisicao){
        dietaRepository
                .findById(id)
                .map(Dieta -> {
                    Dieta.setId(dadoDaRequisicao.getId());
                    return dietaRepository.save(Dieta);
                })
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Item  " + id + " Iexistente no carrinho"));
    }
}
