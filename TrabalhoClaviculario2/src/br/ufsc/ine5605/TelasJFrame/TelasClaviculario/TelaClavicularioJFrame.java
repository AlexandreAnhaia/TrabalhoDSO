/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.TelasJFrame.TelasClaviculario;

import br.ufsc.ine5605.Entidades.Claviculario;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;


/**
 *
 * @author Pichau
 */
public class TelaClavicularioJFrame extends JFrame {
    private static final String retirar = "1";
    private static final String devolver = "2";
    private static final String lista = "3";
    private static final String pesquisa = "4";
    private Container container = getContentPane();
    private GerenciadorBotoes btManager;
    private Claviculario owner;
    private JButton btRetirarChave;
    private JButton btDevolverChave;
    private JButton btListaEvento;
    private JButton btProcuraEvento;

    public TelaClavicularioJFrame(Claviculario owner) {
        this.owner = owner;
        this.btManager = new GerenciadorBotoes();
        inicializa();
    }

    public void inicializa() {

        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        btRetirarChave = new JButton();
        btRetirarChave.setActionCommand(retirar);
        btRetirarChave.addActionListener(btManager);
        btRetirarChave.setText("Retirar Chave");
        btRetirarChave.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(btRetirarChave);

        btDevolverChave = new JButton();
        btDevolverChave.setActionCommand(devolver);
        btDevolverChave.addActionListener(btManager);
        btDevolverChave.setText("Devolver Chave");
        btDevolverChave.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(btDevolverChave);

        btListaEvento = new JButton();
        btListaEvento.setActionCommand(lista);
        btListaEvento.addActionListener(btManager);
        btListaEvento.setText("Lista de Eventos");
        btListaEvento.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(btListaEvento);
        
        
        btProcuraEvento = new JButton();
        btProcuraEvento.setActionCommand(pesquisa);
        btProcuraEvento.addActionListener(btManager);
        btProcuraEvento.setText("Pesquisar Evento");
        btProcuraEvento.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(btProcuraEvento);

        setSize(300, 150);
        setLocationRelativeTo(null);
    }

    private class GerenciadorBotoes implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            int opcao = Integer.parseInt(e.getActionCommand());

            switch (opcao) {
                case 1:
                    owner.getTelaPegaFuncionarioJFrame();
                    break;
                case 2:
                    owner.getTelaPegaFuncionarioDevolveVeiculoJFrame();
                    break;
                case 3:
                    owner.getTabelaEventosJFrame();
                    break;
                case 4:
                    owner.getTelaPesquisaEvento();
                    break;
            }
        }

    }
}
