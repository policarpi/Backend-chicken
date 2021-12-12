package com.chicken.rest;


import com.chicken.entity.Treinos;
import com.chicken.repository.TreinosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/treinos")
@CrossOrigin("http://localhost:4200/")
public class TreinosController {
    @Autowired
    private TreinosRepository treinosRepository;

    public List<Treinos> listar(){
        return treinosRepository.findAll();
    }

    @Autowired
    public TreinosController (TreinosRepository treinosRepository) {
        this.treinosRepository = treinosRepository;
    }



    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Treinos salvar(@Valid @RequestBody Treinos treinos ){
        return treinosRepository.save(treinos);
    }

    @GetMapping("{id}")
    public Treinos acharPorId(@PathVariable Long id){
        return treinosRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Treimp " + id + " não encontrado"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id){
        treinosRepository
                .findById(id)
                .map(endereRuim -> {
                    treinosRepository.delete(endereRuim);
                    return Void.TYPE;
                })
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Endereço " + id + " "));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Long id,@Valid @RequestBody Treinos dadoDaRequisicao){
        treinosRepository
                .findById(id)
                .map(Treino -> {

                    Treino.setId(dadoDaRequisicao.getId());
                    Treino.setPeso(dadoDaRequisicao.getPeso());
                    Treino.setRepeticao(dadoDaRequisicao.getRepeticao());
                    Treino.setQuantidade(dadoDaRequisicao.getQuantidade());
                    return treinosRepository.save(Treino);
                })
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Treino   " + id + " inexistente"));
    }
}
