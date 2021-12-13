package com.chicken.rest;


import com.chicken.dto.DietaDTO;
import com.chicken.entity.*;
import com.chicken.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/dieta")
@CrossOrigin("http://localhost:4200/")
public class DietaController {



    private final PessoaRepository pessoaRepository;
    private final RefeicoesRepository refeicoesRepository;
    private final AlimentosRepository alimentosRepository;
    private final DietaRepository dietaRepository;

    public DietaController ( DietaRepository dietaRepository, PessoaRepository pessoaRepository, RefeicoesRepository refeicoesRepository, AlimentosRepository alimentosRepository) {
        this.pessoaRepository = pessoaRepository;
        this.refeicoesRepository = refeicoesRepository;
        this.alimentosRepository = alimentosRepository;
        this.dietaRepository = dietaRepository;
    }

    @GetMapping
    public List<Dieta> pesquisar(
            @RequestParam(value = "nome", required = false, defaultValue = "") String nome) {
        return dietaRepository.findByNomePessoa("%" + nome + "%");
    }


    /*
    @GetMapping
    public List <Dieta> acharTodos(){
        return dietaRepository.findAll();
    }*/



    @GetMapping("{id}")
    public Dieta acharPorId(@PathVariable Long id) {
        return dietaRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Dieta " + id + " não encontrada"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        dietaRepository
                .findById(id)
                .map(diateRuim -> {
                    dietaRepository.delete(diateRuim);
                    return Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Dieta " + id + " não ruim"));
    }
/*
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Long id, @Valid @RequestBody Dieta dadoDaRequisicao) {
        dietaRepository
                .findById(id)
                .map(Dieta -> {
                    Dieta.setId(dadoDaRequisicao.getId());
                    return dietaRepository.save(Dieta);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Item  " + id + " Iexistente no carrinho"));
    }
*/

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Dieta salvar(@Valid @RequestBody DietaDTO dietaDTO ) {

        Integer idPessoa = dietaDTO.getIdPessoa();
        Pessoa valorDaPessoa = pessoaRepository
                .findById(idPessoa)
                .orElseThrow(() -> new ResponseStatusException( HttpStatus.BAD_REQUEST,"Pessoa  " + idPessoa + " não existe em nossa app!"));

        Integer idAlimentos = dietaDTO.getIdAlimentos();
        Alimentos tipoAlimento = alimentosRepository
                .findById(idAlimentos)
                .orElseThrow(() -> new ResponseStatusException( HttpStatus.BAD_REQUEST, "Alimento " + idAlimentos + " não existe em nosso app!" ));

        Integer idRefeicoes = dietaDTO.getIdRefeicoes();
        Refeicoes tipoRefeicao  = refeicoesRepository
                .findById(idRefeicoes)
                .orElseThrow(() -> new ResponseStatusException( HttpStatus.BAD_REQUEST, "Refeição  " + idRefeicoes + " não existe em nosso app!" ));

        Double quantidade = dietaDTO.getQuantidade();

        Dieta dietaSalvar = new Dieta();

        dietaSalvar.setPessoa(valorDaPessoa);
        dietaSalvar.setAlimentos(tipoAlimento);
        dietaSalvar.setRefeicoes(tipoRefeicao);
        dietaSalvar.setQuantidade(quantidade);

        return dietaRepository.save(dietaSalvar);

  }
}
