/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.Entidades;

import br.ufsc.ine5605.Controladores.ControladorPrincipal;
import static br.ufsc.ine5605.Entidades.CargoFuncionario.DIRETOR;
import static br.ufsc.ine5605.Entidades.CargoFuncionario.FAXINEIRO;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Pichau
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
        // TODO code application logic here
        ControladorPrincipal ctrl = new ControladorPrincipal();

        
       
        
        ctrl.exibeMenuPrincipal();
    }

}
