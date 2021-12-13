package com.chicken.rest;

import com.chicken.entity.Enderecos;
import com.chicken.repository.EnderecosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/endereco")
@CrossOrigin("http://localhost:4200/")
public class EnderecoController {

    @Autowired
    private final EnderecosRepository enderecosRepository;

    @GetMapping
    public List<Enderecos> listar(){
        return enderecosRepository.findAll();
    }

    @Autowired
    public EnderecoController(EnderecosRepository enderecosRepository) {
        this.enderecosRepository = enderecosRepository;
    }



    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Enderecos salvar(@Valid @RequestBody Enderecos enderecos ){
        return enderecosRepository.save(enderecos);
    }

    @GetMapping("{id}")
    public Enderecos acharPorId(@PathVariable Long id){
        return enderecosRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Endereço " + id + " não encontrado"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id){
        enderecosRepository
                .findById(id)
                .map(endereRuim -> {
                    enderecosRepository.delete(endereRuim);
                    return Void.TYPE;
                })
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Endereço " + id + " "));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Long id,@Valid @RequestBody Enderecos dadoDaRequisicao){
        enderecosRepository
                .findById(id)
                .map(Enderecos -> {
                    Enderecos.setId(dadoDaRequisicao.getId());
                    Enderecos.setEndereco(dadoDaRequisicao.getEndereco());
                    Enderecos.setCep(dadoDaRequisicao.getCep());
                    Enderecos.setCidade(dadoDaRequisicao.getCidade());
                    Enderecos.setEstado(dadoDaRequisicao.getEstado());
                    Enderecos.setNumero(dadoDaRequisicao.getNumero());
                    return enderecosRepository.save(Enderecos);
                })
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Endereço   " + id + " inexistente"));
    }

}
