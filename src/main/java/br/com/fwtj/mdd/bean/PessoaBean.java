/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fwtj.mdd.bean;

import br.com.fwtj.mdd.modelo.Objeto;
import br.com.fwtj.mdd.modelo.Pessoa;
import br.com.fwtj.mdd.servico.PessoaServico;
import br.com.fwtj.mdd.util.jpa.PU1;
import br.com.fwtj.mdd.util.jpa.PU2;
import br.com.fwtj.mdd.util.jpa.Transactional_PU1;
import br.com.fwtj.mdd.util.jpa.Transactional_PU2;
import java.io.Serializable;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Junior
 */
@Named
@ViewScoped
public class PessoaBean implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Inject
    PessoaServico pessoaServico;
    
    @Inject
    Pessoa pessoa;
    
    @Inject @PU1
    private EntityManager managerPU1;
    
    @Inject @PU2
    private EntityManager managerPU2;
    

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
    
    public void exibirPessoa(){
        pessoa = pessoaServico.devolverPessoa();
    }
    
    @Transactional_PU1
    public void salvarPU1(){
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Fred");
        pessoa.setIdade("1");
        Pessoa merge = managerPU1.merge(pessoa);
        System.out.println("Pessoa cadastrada com ID : " + merge.getId());
    }
    
    @Transactional_PU2
    public void salvarPU2(){
        Objeto objeto = new Objeto();
        objeto.setDescricao("Cadeira");
        Objeto merge = managerPU2.merge(objeto);
        System.out.println("Objeto cadastrado com ID : " + merge.getId());
    }
    
    public void listarPU1(){
        List<Pessoa> resultList = managerPU1.createQuery("SELECT c FROM Pessoa c",Pessoa.class).getResultList();
        for (Pessoa pessoa : resultList) {
            System.out.println(pessoa.getNome());
        }
    }
    
    public void listarPU2(){
        List<Objeto> resultList = managerPU2.createQuery("SELECT o FROM Objeto o",Objeto.class).getResultList();
        for (Objeto o : resultList) {
            System.out.println(o.getDescricao());
        }
    }
    
    
    
}
