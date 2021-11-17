package com.chicken.rest;

import com.chicken.entity.Alimentos;
import com.chicken.repository.AlimentosRepository;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/alimentos")
public class AlimentosController {

    @Autowired
    private AlimentosRepository alimentosRepository;

    @GetMapping
    public List<Alimentos>  listar (){
       return  alimentosRepository.findAll();
    }

    @Autowired
    public AlimentosController(AlimentosRepository alimentosRepository){
        this.alimentosRepository = alimentosRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Alimentos salvar(@Valid @RequestBody Alimentos alimentos){
        return alimentosRepository.save(alimentos);
    }


    @GetMapping("{id}")
    public Alimentos acharPorId(@PathVariable Long id){
        return alimentosRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "A Alimentos " + id + " não existe em nossa aplicação!"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id){
        alimentosRepository
                .findById(id)
                .map(AlimentosASerExcluida -> {
                    alimentosRepository.delete(AlimentosASerExcluida);
                    return Void.TYPE;
                })
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Alimentos " + id + " não existe em nossa aplicação!"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Long id,@Valid @RequestBody Alimentos dadoDaRequisicao){
        alimentosRepository
                .findById(id)
                .map(Alimentos -> {
                    Alimentos.setNome(dadoDaRequisicao.getNome());
                    Alimentos.setDescricao(dadoDaRequisicao.getDescricao());
                    Alimentos.setPorcao(dadoDaRequisicao.getPorcao());
                    Alimentos.setKcal(dadoDaRequisicao.getKcal());
                    Alimentos.setCarboidratos(dadoDaRequisicao.getCarboidratos());
                    Alimentos.setGordurasTotais(dadoDaRequisicao.getGordurasTotais());
                    Alimentos.setGorduraSaturadas(dadoDaRequisicao.getGorduraSaturadas());
                    Alimentos.setGorduraTrans(dadoDaRequisicao.getGorduraTrans());
                    Alimentos.setFibraAlimentar(dadoDaRequisicao.getFibraAlimentar());
                    Alimentos.setProteinas(dadoDaRequisicao.getGorduraSaturadas());

                    return alimentosRepository.save(Alimentos);
                })
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "A Alimentos " + id + " não existe em nossa aplicação!"));
    }

}
