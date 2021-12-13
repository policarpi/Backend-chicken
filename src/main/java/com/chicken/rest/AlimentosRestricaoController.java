package com.chicken.rest;

import com.chicken.dto.AlimentosRestricaoDTO;
import com.chicken.dto.TreinoDTO;
import com.chicken.entity.*;
import com.chicken.repository.AlimentosRepository;
import com.chicken.repository.AlimentosRestricaoRepository;
import com.chicken.repository.RestricaoAlimentarRepository;
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
    private final AlimentosRestricaoRepository alimentosRestricaoRepository;
    private final AlimentosRepository alimentosRepository;
    private final RestricaoAlimentarRepository restricaoAlimentarRepository ;


    @Autowired
    public AlimentosRestricaoController (AlimentosRestricaoRepository alimentosRestricaoRepository, AlimentosRepository alimentosRepository, RestricaoAlimentarRepository restricaoAlimentarRepository) {
        this.alimentosRestricaoRepository = alimentosRestricaoRepository;
        this.alimentosRepository = alimentosRepository;
        this.restricaoAlimentarRepository = restricaoAlimentarRepository;
    }


    @GetMapping
    public List<AlimentosRestricao> pesquisarNomeAlinentos(
            @RequestParam(value = "nome", required = false, defaultValue = "") String nome) {
        return alimentosRestricaoRepository.findByNome("%" + nome + "%");
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AlimentosRestricao salvar(@Valid @RequestBody AlimentosRestricaoDTO alimentosRestricaoDTO) {

        Integer idAlimentos = alimentosRestricaoDTO.getIdAlimentos();
        Alimentos nomeAlimento = alimentosRepository
                .findById(idAlimentos)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Alimento  " + idAlimentos + " não existe em nossa app!"));

        Integer idRestricaoAlimentar = alimentosRestricaoDTO.getIdRestricaoAlimentar();
        RestricaoAlimentar tipoRestricao = restricaoAlimentarRepository
                .findById(idRestricaoAlimentar)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "A Equipamentos " + idRestricaoAlimentar + " não existe em nosso app!"
                ));

        AlimentosRestricao saveRestricaAlim  = new AlimentosRestricao();

        saveRestricaAlim.setAlimentos(nomeAlimento);
        saveRestricaAlim.setRestricaoAlimentar(tipoRestricao);

        return alimentosRestricaoRepository.save(saveRestricaAlim);


    }
}
