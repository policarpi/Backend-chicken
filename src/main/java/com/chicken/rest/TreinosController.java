package com.chicken.rest;


import com.chicken.dto.TreinoDTO;
import com.chicken.entity.Equipamentos;
import com.chicken.entity.Pessoa;
import com.chicken.entity.Treinos;
import com.chicken.repository.EquipamentosRepository;
import com.chicken.repository.PessoaRepository;
import com.chicken.repository.TreinosRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/treinos")
@CrossOrigin("http://localhost:4200/")
public class TreinosController {


    private final PessoaRepository pessoaRepository;
    private final EquipamentosRepository equipamentosRepository;
    private final TreinosRepository treinosRepository;

    public TreinosController(PessoaRepository pessoaRepository, EquipamentosRepository equipamentosRepository, TreinosRepository treinosRepository) {
        this.pessoaRepository = pessoaRepository;
        this.equipamentosRepository = equipamentosRepository;
        this.treinosRepository = treinosRepository;
    }


    @GetMapping
    public List<Treinos> pesquisarNomePessoas(
            @RequestParam(value = "nome", required = false, defaultValue = "") String nome) {
        return treinosRepository.findByNome("%" + nome + "%");
    }

    @GetMapping("{id}")
    public Treinos acharPorId(@PathVariable Long id) {
        return treinosRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Treino " + id + " não encontrado"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        treinosRepository
                .findById(id)
                .map(endereRuim -> {
                    treinosRepository.delete(endereRuim);
                    return Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Endereço " + id + " "));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Treinos salvar(@Valid @RequestBody TreinoDTO treinoDTO) {

        Integer idPessoa = treinoDTO.getIdPessoa();
        Pessoa valorDaPessoa = pessoaRepository
                .findById(idPessoa)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Pessoa  " + idPessoa + " não existe em nossa app!"));

                    Integer idEquipamentos = treinoDTO.getIdEquipamentos();
                    Equipamentos tipoEquimanto = equipamentosRepository
                    .findById(idEquipamentos)
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.BAD_REQUEST,
                            "A Equipamentos " + idEquipamentos + " não existe em nosso app!"
                    ));
                    Integer quantidade = treinoDTO.getQuantidade();
                    Integer repeticao = treinoDTO.getRepeticao();
                    Double peso = treinoDTO.getPeso();
                    String ficha = treinoDTO.getFicha();

                    Treinos treinosSalvar = new Treinos();

                    treinosSalvar.setPessoa(valorDaPessoa);
                    treinosSalvar.setEquipamentos(tipoEquimanto);
                    treinosSalvar.setQuantidade(quantidade);
                    treinosSalvar.setRepeticao(repeticao);
                    treinosSalvar.setPeso(peso);
                    treinosSalvar.setFicha(ficha);

                    return treinosRepository.save(treinosSalvar);

    }
}
