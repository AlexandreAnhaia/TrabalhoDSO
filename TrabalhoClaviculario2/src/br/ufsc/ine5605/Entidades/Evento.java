/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.Entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Pichau
 */
public abstract class Evento implements Serializable {

    private GregorianCalendar gc = new GregorianCalendar();
    private Date dataEvento = gc.getTime();
    private DescricaoEvento descricao;
    private int matricula;
    private String placa;

    public Evento(DescricaoEvento Descricao, Date dataEvento,int matricula) {
        this.descricao = Descricao;
        dataEvento = this.dataEvento;

    }

    public void setDescricao(DescricaoEvento Descricao) {
        this.descricao = Descricao;
    }

    public DescricaoEvento getDescricao() {
        return descricao;
    }
    
    @Override
    public String toString(){
        return "Descricao: "+ descricao.detalhes+", Data: "+this.dataEvento+", Matricula: "+this.matricula;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }
    
    public abstract String getPlaca();

    public Date getDataEvento() {
        return dataEvento;
    }
    
    
   

}
