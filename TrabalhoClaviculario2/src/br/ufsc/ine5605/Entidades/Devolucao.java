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
public class Devolucao extends AcessoPermitido{
    
    public Devolucao(DescricaoEvento Descricao, Date dataEvento, int matricula, String placa) {
        super(Descricao, dataEvento, matricula, placa);
        super.setDescricao(Descricao);
        super.setMatricula(matricula);
        super.setPlaca(placa);
        
    }
    
    @Override
    public String toString(){
        return super.toString();
    }
    
    @Override
    public String getPlaca() {
        return super.getPlaca(); 
    }
    
    @Override
    public int getMatricula() {
        return super.getMatricula();
    }
    
    
    
    
}
