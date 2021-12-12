package com.chicken.rest;


import com.chicken.entity.Fisiologia;
import com.chicken.repository.FisiologiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/fisiologia")
@CrossOrigin("http://localhost:4200/")
public class FisiologiaController {



    @Autowired
    private FisiologiaRepository fisiologiaRepository ;

    @GetMapping
    public List<Fisiologia> listar(){
        return fisiologiaRepository.findAll();
    }

    @Autowired
    public FisiologiaController (FisiologiaRepository fisiologiaRepository ) {
        this.fisiologiaRepository = fisiologiaRepository;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Fisiologia salvar(@Valid @RequestBody Fisiologia fisiologia ){
        return fisiologiaRepository.save(fisiologia);
    }


    @GetMapping("{id}")
    public Fisiologia acharPorId(@PathVariable Long id){
        return fisiologiaRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Fisiologia " + id + " não encontrado"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id){
        fisiologiaRepository
                .findById(id)
                .map(deletar -> {
                    fisiologiaRepository.delete(deletar);
                    return Void.TYPE;
                })
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Fisiologia " + id + " deletada "));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Long id,@Valid @RequestBody Fisiologia dadoDaRequisicao){
        fisiologiaRepository
                .findById(id)
                .map(Fisiologia -> {
                    Fisiologia.setId(dadoDaRequisicao.getId());
                    Fisiologia.setAltura(dadoDaRequisicao.getAltura());
                    Fisiologia.setPeso(dadoDaRequisicao.getPeso());
                    Fisiologia.setPessoa(dadoDaRequisicao.getPessoa());
                    Fisiologia.setTipo(dadoDaRequisicao.getTipo());
                    return  fisiologiaRepository.save(Fisiologia);
                })
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Fisiologia  " + id + " inexistente"));
    }
}
