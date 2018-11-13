/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.Controladores;


import br.ufsc.ine5605.Entidades.Claviculario;
import br.ufsc.ine5605.Entidades.Main;
import br.ufsc.ine5605.TelasJFrame.TelaPrincipalJframe;
import java.text.ParseException;

/**
 *
 * @author Pichau
 */
public class ControladorPrincipal {

    private ControladorFuncionario ctrlFuncionario;
    private ControladorVeiculo ctrlVeiculo = new ControladorVeiculo(this);;
    private Claviculario claviculario;
    private TelaPrincipalJframe telaPrincipalJframe;
    

    public ControladorPrincipal() {
        this.telaPrincipalJframe = new TelaPrincipalJframe(this);
        this.ctrlFuncionario = new ControladorFuncionario(this);
        this.ctrlVeiculo = new ControladorVeiculo(this);
        this.claviculario = new Claviculario(this);
    }


    public ControladorFuncionario getCtrlFuncionario() {
        return ctrlFuncionario;
    }

    public ControladorVeiculo getCtrlVeiculo() {
        return ctrlVeiculo;
    }

    public Claviculario getClaviculario() {
        return this.claviculario;
    }

   public void exibeMenuPrincipal() throws ParseException{
       telaPrincipalJframe.setVisible(true);
   }
       
    

}
