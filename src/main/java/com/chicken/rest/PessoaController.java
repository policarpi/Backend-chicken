package com.chicken.rest;

import com.chicken.entity.Pessoa;
import com.chicken.repository.PessoaRepository;
import com.chicken.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;



@RestController
@RequestMapping("/pessoa")
@CrossOrigin("http://localhost:4200/")
public class PessoaController {
    @Autowired
    private PessoaRepository pessoaRepository;


    private PessoaService pessoaService;

    public  PessoaController(PessoaService pessoaService){
        this.pessoaService = pessoaService;
    }
  /*
   @GetMapping
   public Pessoa getPessoa(@PathVariable("nome")String nome){
        return pessoaService.getPessoa(nome);
    }*/

    @GetMapping
    public List<Pessoa> listar(){
        return pessoaRepository.findAll();
    }


    @Autowired
    public PessoaController(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pessoa salvar(@Valid @RequestBody Pessoa pessoa ){
        return pessoaRepository.save(pessoa);
    }

    @GetMapping("{id}")
    public Pessoa acharPorId(@PathVariable Integer id) {
        return pessoaRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Pessoa " + id + " não encontrado"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id) {
        pessoaRepository
                .findById(id)
                .map(PessoaRuim -> {
                    pessoaRepository.delete(PessoaRuim);
                    return Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Endereço " + id + " "));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @Valid @RequestBody Pessoa dadoDaRequisicao) {
        pessoaRepository
                .findById(id)
                .map(Pessoa -> {
                    Pessoa.setNome(dadoDaRequisicao.getNome());
                    Pessoa.setCpf(dadoDaRequisicao.getCpf());
                    Pessoa.setEMail(dadoDaRequisicao.getEMail());
                    Pessoa.setSobreNome(dadoDaRequisicao.getSobreNome());
                    return pessoaRepository.save(Pessoa);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Pessoa   " + id + " inexistente"));
    }
}
