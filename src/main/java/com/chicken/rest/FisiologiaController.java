package com.chicken.rest;


import com.chicken.dto.FisiologiaDTO;
import com.chicken.entity.Fisiologia;
import com.chicken.entity.Pessoa;
import com.chicken.entity.Treinos;
import com.chicken.repository.FisiologiaRepository;
import com.chicken.repository.PessoaRepository;
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
    private final FisiologiaRepository fisiologiaRepository ;
    private final PessoaRepository pessoaRepository;




    @GetMapping
    public List<Fisiologia> pesquisarNomePessoas(
            @RequestParam(value = "nome", required = false, defaultValue = "") String nome) {
        return fisiologiaRepository.findByNome("%" + nome + "%");
    }


    @Autowired
    public FisiologiaController(FisiologiaRepository fisiologiaRepository, PessoaRepository pessoaRepository) {
        this.fisiologiaRepository = fisiologiaRepository;
        this.pessoaRepository = pessoaRepository;
    }

/*
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Fisiologia salvar(@Valid @RequestBody Fisiologia fisiologia ){
        return fisiologiaRepository.save(fisiologia);
    }
*/

    @GetMapping("{id}")
    public Fisiologia acharPorId(@PathVariable Integer id){
        return fisiologiaRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Fisiologia " + id + " não encontrado"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
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
    public void atualizar(@PathVariable Integer id,@Valid @RequestBody Fisiologia dadoDaRequisicao){
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Fisiologia salvar(@Valid @RequestBody FisiologiaDTO fisiologiaDTO) {

        Integer idPessoa = fisiologiaDTO.getIdPessoa();
        Pessoa valorDaPessoa = pessoaRepository
                .findById(idPessoa)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Pessoa  " + idPessoa + " não existe em nossa app!"));

         String tipo = fisiologiaDTO.getTipo();
         Double peso = fisiologiaDTO.getPeso();
         Double altura = fisiologiaDTO.getAltura();


        Fisiologia salvarFisio = new Fisiologia();

        salvarFisio.setPessoa(valorDaPessoa);
        salvarFisio.setTipo(tipo);
        salvarFisio.setPeso(peso);
        salvarFisio.setAltura(altura);

        return fisiologiaRepository.save(salvarFisio);

    }

}
