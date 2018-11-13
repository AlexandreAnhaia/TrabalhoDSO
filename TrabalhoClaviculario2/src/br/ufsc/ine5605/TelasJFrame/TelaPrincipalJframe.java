/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.TelasJFrame;

import br.ufsc.ine5605.Controladores.ControladorPrincipal;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author Pichau
 */
public class TelaPrincipalJframe extends JFrame {

    private Container container = getContentPane();
    private GerenciadorBotoes btManager;
    private ControladorPrincipal owner;
    private JButton btVeiculo;
    private JButton btFuncionario;
    private JButton btClaviculario;
    private static final String veiculo = "1";
    private static final String funcionario = "2";
    private static final String claviculario = "3";
    
    

    public TelaPrincipalJframe(ControladorPrincipal owner) {
        this.owner = owner;
        this.btManager = new GerenciadorBotoes();
        inicializa();
    }

    public void inicializa() {

        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        btVeiculo = new JButton();
        btVeiculo.setActionCommand("1");
        btVeiculo.addActionListener(btManager);
        btVeiculo.setText("Veiculo");
        btVeiculo.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(btVeiculo);

        btFuncionario = new JButton();
        btFuncionario.setActionCommand("2");
        btFuncionario.addActionListener(btManager);
        btFuncionario.setText("Funcionario");
        btFuncionario.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(btFuncionario);

        btClaviculario = new JButton();
        btClaviculario.setActionCommand("3");
        btClaviculario.addActionListener(btManager);
        btClaviculario.setText("Claviculario");
        btClaviculario.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(btClaviculario);
        

        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private class GerenciadorBotoes implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            int opcao = Integer.parseInt(e.getActionCommand());

            switch (opcao) {
                case 1:
                    owner.getCtrlVeiculo().exibeMenuVeiculo();
                    break;
                case 2:
                    try {
                        owner.getCtrlFuncionario().exibeMenuFuncionario();
                    } catch (ParseException ex) {
                        Logger.getLogger(TelaPrincipalJframe.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    break;
                case 3:
                    owner.getCtrlFuncionario().atualizaDados();
                    owner.getClaviculario().getTelaclavicularioJFrame();
                    break;

            }
        }

    }

}
