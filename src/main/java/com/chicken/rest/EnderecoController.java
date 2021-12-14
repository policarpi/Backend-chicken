package com.chicken.rest;

import com.chicken.dto.PessoaEnderecoDTO;
import com.chicken.entity.Enderecos;
import com.chicken.entity.Pessoa;
import com.chicken.entity.Treinos;
import com.chicken.repository.EnderecosRepository;
import com.chicken.repository.PessoaRepository;
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

    private final PessoaRepository pessoaRepository;
    private final EnderecosRepository enderecosRepository;


    public EnderecoController(EnderecosRepository enderecosRepository, PessoaRepository pessoaRepository) {
        this.enderecosRepository = enderecosRepository;
        this.pessoaRepository = pessoaRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Enderecos salvar(@Valid @RequestBody PessoaEnderecoDTO pessoaEnderecoDTO){

        Integer idPessoa = pessoaEnderecoDTO.getIdPessoa();
        Pessoa valorDaPessoa = pessoaRepository
                .findById(idPessoa)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Pessoa  " + idPessoa + " não existe em nossa app!"));

        String cep = pessoaEnderecoDTO.getCep();
        String estado = pessoaEnderecoDTO.getEstado();
        String cidade = pessoaEnderecoDTO.getCidade();
        Integer numero = pessoaEnderecoDTO.getNumero();
        String endereco = pessoaEnderecoDTO.getEndereco();

        Enderecos enderecosSalvar = new Enderecos();

        enderecosSalvar.setPessoa(valorDaPessoa);
        enderecosSalvar.setCep(cep);
        enderecosSalvar.setEstado(estado);
        enderecosSalvar.setCidade(cidade);
        enderecosSalvar.setNumero(numero);
        enderecosSalvar.setEndereco(endereco);

        return enderecosRepository.save(enderecosSalvar);
    }


    @GetMapping
    public List<Enderecos> pesquisaEderecosPessoas(
            @RequestParam(value = "nome", required = false, defaultValue = "") String nome) {
        return enderecosRepository.findByNome("%" + nome + "%");
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
