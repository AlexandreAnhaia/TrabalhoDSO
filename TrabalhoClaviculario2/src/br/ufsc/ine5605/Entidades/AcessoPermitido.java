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
public class AcessoPermitido extends Evento {
    
    
    private String placa;
    
    public AcessoPermitido(DescricaoEvento Descricao, Date dataEvento, int matricula,String placa) {
        super(Descricao, dataEvento,matricula);
        super.setDescricao(Descricao);
        super.setMatricula(matricula);
        this.placa = placa;
        
    }

    

    public void setPlaca(String placa) {
        this.placa = placa;
    }
    
    @Override
    public String toString(){
        return super.toString()+", Placa do Veiculo: "+this.placa;
    }

  

    @Override
    public String getPlaca() {
        return placa;
    }
    
}
