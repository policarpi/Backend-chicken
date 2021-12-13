package com.chicken.rest;

import com.chicken.entity.Alimentos;
import com.chicken.repository.AlimentosRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/alimentos")
@CrossOrigin("http://localhost:4200/")
public class AlimentosController {

    @Autowired
    private final AlimentosRepository alimentosRepository;

    @GetMapping
    public List <Alimentos> acharTodos(){
        return alimentosRepository.findAll();
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
    public Alimentos acharPorId(@PathVariable Integer id){
        return alimentosRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "A Alimentos " + id + " não existe em nossa aplicação!"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
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
    public void atualizar(@PathVariable Integer id,@Valid @RequestBody Alimentos dadoDaRequisicao){
        alimentosRepository
                .findById(id)
                .map(Alimentos -> {
                    Alimentos.setId(dadoDaRequisicao.getId());
                    Alimentos.setNome(dadoDaRequisicao.getNome());
                    Alimentos.setDescricao(dadoDaRequisicao.getDescricao());
                    Alimentos.setAcucartotal(dadoDaRequisicao.getAcucartotal());
                    Alimentos.setAgua(dadoDaRequisicao.getAgua());
                    Alimentos.setCarboidrato(dadoDaRequisicao.getCarboidrato());
                    Alimentos.setColesterol(dadoDaRequisicao.getColesterol());
                    Alimentos.setFibra(dadoDaRequisicao.getFibra());
                    Alimentos.setGorduramonosaturada(dadoDaRequisicao.getGorduramonosaturada());
                    Alimentos.setGordurapolissaturada(dadoDaRequisicao.getGordurapolissaturada());
                    Alimentos.setGordurasaturada(dadoDaRequisicao.getGordurasaturada());
                    Alimentos.setLicopeno(dadoDaRequisicao.getLicopeno());
                    Alimentos.setLipidio(dadoDaRequisicao.getLipidio());
                    Alimentos.setLuteinazeaxantina(dadoDaRequisicao.getLuteinazeaxantina());
                    Alimentos.setMineralcalcio(dadoDaRequisicao.getMineralcalcio());
                    Alimentos.setMineralferro(dadoDaRequisicao.getMineralferro());
                    Alimentos.setMineralfosforo(dadoDaRequisicao.getMineralfosforo());
                    Alimentos.setMineralpotassio(dadoDaRequisicao.getMineralpotassio());
                    Alimentos.setMineralscopper(dadoDaRequisicao.getMineralscopper());
                    Alimentos.setMineralsmagnesio(dadoDaRequisicao.getMineralsmagnesio());
                    Alimentos.setMineralsodio(dadoDaRequisicao.getMineralsodio());
                    Alimentos.setMineralzinco(dadoDaRequisicao.getMineralzinco());
                    Alimentos.setProteina(dadoDaRequisicao.getProteina());
                    Alimentos.setNiacina(dadoDaRequisicao.getNiacina());
                    Alimentos.setSelenio(dadoDaRequisicao.getSelenio());
                    Alimentos.setRetinol(dadoDaRequisicao.getRetinol());
                    Alimentos.setRiboflavina(dadoDaRequisicao.getRiboflavina());
                    Alimentos.setTiamina(dadoDaRequisicao.getTiamina());

                    return alimentosRepository.save(Alimentos);
                })
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "A Alimentos " + id + " não existe em nossa aplicação!"));
    }

}
