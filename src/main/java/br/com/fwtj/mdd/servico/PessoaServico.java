/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fwtj.mdd.servico;

import br.com.fwtj.mdd.modelo.Pessoa;
import java.io.Serializable;
import javax.inject.Inject;

/**
 *
 * @author Junior
 */
public class PessoaServico implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Inject
    Pessoa pessoa;
    
    public Pessoa devolverPessoa(){
        pessoa.setNome("Fulano da Silva");
        pessoa.setIdade("33 Anos");
        System.out.println(pessoa);
        return pessoa;
    }
    
}
