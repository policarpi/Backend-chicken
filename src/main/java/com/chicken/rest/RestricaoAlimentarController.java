package com.chicken.rest;

import com.chicken.entity.RestricaoAlimentar;
import com.chicken.repository.RestricaoAlimentarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/restricaoalimentar")
@CrossOrigin("http://localhost:4200/")
public class RestricaoAlimentarController {

    @Autowired
    private final RestricaoAlimentarRepository restricaoAlimentarRepository;

    @GetMapping
    public List<RestricaoAlimentar> listar(){
        return restricaoAlimentarRepository.findAll();
    }

    @Autowired
    public RestricaoAlimentarController (RestricaoAlimentarRepository restricaoAlimentarRepository) {
        this.restricaoAlimentarRepository = restricaoAlimentarRepository;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestricaoAlimentar salvar(@Valid @RequestBody RestricaoAlimentar restricaoAlimentar ){
        return restricaoAlimentarRepository.save(restricaoAlimentar);
    }


    @GetMapping("{id}")
    public RestricaoAlimentar acharPorId(@PathVariable Long id){
        return restricaoAlimentarRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Alimiento com restrição " + id + " não encontrado"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id){
        restricaoAlimentarRepository
                .findById(id)
                .map(deletar -> {
                    restricaoAlimentarRepository.delete(deletar);
                    return Void.TYPE;
                })
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Restrição " + id + " "));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Long id,@Valid @RequestBody RestricaoAlimentar dadoDaRequisicao){
        restricaoAlimentarRepository
                .findById(id)
                .map(RestricaoAlimentar -> {
                    RestricaoAlimentar.setId(dadoDaRequisicao.getId());
                    RestricaoAlimentar.setNome(dadoDaRequisicao.getNome());
                    RestricaoAlimentar.setDescricao(dadoDaRequisicao.getDescricao());
                    return restricaoAlimentarRepository.save(RestricaoAlimentar);
                })
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Restrição alimentar  " + id + " inexistente"));
    }

}
