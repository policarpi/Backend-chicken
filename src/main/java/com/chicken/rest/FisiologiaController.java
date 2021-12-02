package com.chicken.rest;


import com.chicken.entity.Pessoa;
import com.chicken.repository.FisiologiaRepository;
import com.chicken.repository.PessoaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/fisiologia")
public class FisiologiaController {

/*


    @GetMapping
    public List <Pessoa> pesquisar(@RequestParam(value = "nome", required = false, defaultValue = "") String nome)
    {
        return  FisiologiaRepository.findByNomePessoa("%" + nome + "%");
    }
*/

}
