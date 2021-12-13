package com.chicken.rest;

import com.chicken.entity.Refeicoes;
import com.chicken.repository.RefeicoesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/refeicoes")
@CrossOrigin("http://localhost:4200/")
public class RefeicoesController {


    @Autowired
    private final RefeicoesRepository refeicoesRepository;

    @GetMapping
    public List<Refeicoes> listar(){
        return refeicoesRepository.findAll();
    }



    @Autowired
    public RefeicoesController (RefeicoesRepository refeicoesRepository ) {
        this.refeicoesRepository = refeicoesRepository;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Refeicoes salvar(@Valid @RequestBody Refeicoes refeicoes ){
        return refeicoesRepository.save(refeicoes);
    }


    @GetMapping("{id}")
    public Refeicoes acharPorId(@PathVariable Integer id){
        return refeicoesRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Refeição " + id + " não encontrado"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        refeicoesRepository
                .findById(id)
                .map(deletar -> {
                    refeicoesRepository.delete(deletar);
                    return Void.TYPE;
                })
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Restrição " + id + " "));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id,@Valid @RequestBody Refeicoes dadoDaRequisicao){
        refeicoesRepository
                .findById(id)
                .map(Refeicoes -> {
                    Refeicoes.setId(dadoDaRequisicao.getId());
                    Refeicoes.setTiporefeicoes(dadoDaRequisicao.getTiporefeicoes());
                    Refeicoes.setDia(dadoDaRequisicao.getDia());
                    return refeicoesRepository.save(Refeicoes);
                })
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Restrição alimentar  " + id + " inexistente"));
    }

}
