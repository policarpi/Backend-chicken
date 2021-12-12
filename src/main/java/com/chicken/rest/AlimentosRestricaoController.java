package com.chicken.rest;

import com.chicken.entity.AlimentosRestricao;
import com.chicken.repository.AlimentosRestricaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/alimentosrestricao")
@CrossOrigin("http://localhost:4200/")
public class AlimentosRestricaoController {

    @Autowired
    private AlimentosRestricaoRepository alimentosRestricaoRepository;

    @GetMapping
    public List<AlimentosRestricao> listar(){
        return alimentosRestricaoRepository.findAll();
    }

    @Autowired
    public AlimentosRestricaoController (AlimentosRestricaoRepository alimentosRestricaoRepository) {
        this.alimentosRestricaoRepository = alimentosRestricaoRepository;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AlimentosRestricao salvar(@Valid @RequestBody AlimentosRestricao alimentosRestricao){
        return alimentosRestricaoRepository.save(alimentosRestricao);
    }


    @GetMapping("{id}")
    public AlimentosRestricao acharPorId(@PathVariable Long id){
        return alimentosRestricaoRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Alimentos com restrição " + id + " não encontrado"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id){
        alimentosRestricaoRepository
                .findById(id)
                .map(deletar -> {
                    alimentosRestricaoRepository.delete(deletar);
                    return Void.TYPE;
                })
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Alimento com restrição " + id + " deletado "));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Long id,@Valid @RequestBody AlimentosRestricao dadoDaRequisicao){
        alimentosRestricaoRepository
                .findById(id)
                .map(AlimentosRestr -> {
                    AlimentosRestr.setId(dadoDaRequisicao.getId());
                    AlimentosRestr.setAlimentos(dadoDaRequisicao.getAlimentos());
                    AlimentosRestr.setRestricaoAlimentar(dadoDaRequisicao.getRestricaoAlimentar());
                    return  alimentosRestricaoRepository.save(AlimentosRestr);
                })
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Alimentos com restrição  " + id + " inexistente"));
    }
}
