/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.Entidades;

import java.util.Date;

/**
 *
 * @author Pichau
 */
public class AcessoNegado extends Evento {

    
    private String placa;

    public AcessoNegado(DescricaoEvento descricao, Date data, int matricula, String placa) {
        super(descricao, data,matricula);
        super.setDescricao(descricao);
        super.setMatricula(matricula);
        this.placa = placa;
    }


    @Override
    public String toString() {
        return super.toString()+", Placa do Veiculo: "+this.placa;
    }

    @Override
    public String getPlaca() {
        return placa;
    }
    
    

}
