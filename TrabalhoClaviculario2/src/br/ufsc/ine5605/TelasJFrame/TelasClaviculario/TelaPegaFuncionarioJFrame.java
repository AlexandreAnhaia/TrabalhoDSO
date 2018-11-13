/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.TelasJFrame.TelasClaviculario;

import br.ufsc.ine5605.Entidades.Claviculario;
import br.ufsc.ine5605.Entidades.Funcionario;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Pichau
 */
public class TelaPegaFuncionarioJFrame extends JFrame {

    private Claviculario owner;
    private JLabel lbMatricula;
    private JTextField tfMatricula;
    private JButton btOk;
    private JButton btCancelar;
    private static final String ok = "ok";
    private static final String cancelar = "cancelar";
    private GerenciadorBotoes btManager;

    public TelaPegaFuncionarioJFrame(Claviculario owner) {
        this.owner = owner;
        this.btManager = new GerenciadorBotoes();
        inicializa();
    }

    public void inicializa() {
        Container container = getContentPane();

        container.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        lbMatricula = new JLabel();
        lbMatricula.setText("Insira a Matricula do Funcionario: ");
        constraints.gridx = 0;
        constraints.gridy = 0;
        container.add(lbMatricula, constraints);

        tfMatricula = new JTextField();
        tfMatricula.setText("");
        constraints.gridx = 1;
        constraints.gridy = 0;
        tfMatricula.setPreferredSize(new Dimension(100, 20));
        container.add(tfMatricula, constraints);

        btOk = new JButton();
        btOk.setActionCommand(ok);
        btOk.addActionListener(btManager);
        btOk.setText("Ok");
        constraints.gridx = 1;
        constraints.gridy = 1;
        container.add(btOk, constraints);

        btCancelar = new JButton();
        btCancelar.setActionCommand(cancelar);
        btCancelar.addActionListener(btManager);
        btCancelar.setText("Cancelar");
        constraints.gridx = 1;
        constraints.gridy = 2;
        container.add(btCancelar, constraints);

        setSize(700, 600);
        setLocationRelativeTo(null);
    }

    private class GerenciadorBotoes implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            try {
                Funcionario funcionario = owner.getFuncionario(Integer.parseInt(getTfMatricula()));

                if (e.getActionCommand().equals(ok)) {

                    owner.getTelaRetirarChaveJFrame(funcionario);
                    setVisible(false);

                } else if (e.getActionCommand().equals(cancelar)) {
                    setVisible(false);
                }
            } catch (NumberFormatException erro) {
                JOptionPane.showMessageDialog(null, "A Matricula Deve Conter Apenas Numeros(Inteiros)");
            }

        }
    }

    public String getTfMatricula() {
        return tfMatricula.getText();
    }
}
