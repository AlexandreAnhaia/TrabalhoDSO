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
import javax.swing.JTextField;

/**
 *
 * @author Pichau
 */
public class TelaDevolverChaveJFrame extends JFrame {

    private Claviculario owner;
    private Funcionario funcionario;
    private JLabel lbPlaca;
    private JLabel lbKmAtual;
    private JTextField tfPlaca;
    private JTextField tfKmAtual;
    private JButton btOk;
    private JButton btCancelar;
    private static final String ok = "ok";
    private static final String cancelar = "cancelar";
    private GerenciadorBotoes btManager;

    public TelaDevolverChaveJFrame(Claviculario owner) {
        this.owner = owner;
        this.btManager = new GerenciadorBotoes();
        inicializa();
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public void inicializa() {

        Container container = getContentPane();

        container.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        lbPlaca = new JLabel();
        lbPlaca.setText("Placa: ");
        constraints.gridx = 0;
        constraints.gridy = 0;
        container.add(lbPlaca, constraints);

        tfPlaca = new JTextField();
        tfPlaca.setText("");
        constraints.gridx = 1;
        constraints.gridy = 0;
        tfPlaca.setPreferredSize(new Dimension(100, 20));
        container.add(tfPlaca, constraints);

        lbKmAtual = new JLabel();
        lbKmAtual.setText("Quilometragem Atual: ");
        constraints.gridx = 0;
        constraints.gridy = 1;
        container.add(lbKmAtual, constraints);

        tfKmAtual = new JTextField();
        tfKmAtual.setText("");
        constraints.gridx = 1;
        constraints.gridy = 1;
        tfKmAtual.setPreferredSize(new Dimension(100, 20));
        container.add(tfKmAtual, constraints);

        btOk = new JButton();
        btOk.setActionCommand(ok);
        btOk.addActionListener(btManager);
        btOk.setText("Devolver Chave");
        constraints.gridx = 1;
        constraints.gridy = 2;
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
            if (e.getActionCommand().equals(ok)) {
                if (funcionario.getVeiculoAtual() == null) {
                    owner.devolveVeiculo(funcionario.getNumeroDeMatricula(), getTfPlaca(), Float.parseFloat(getTfKmAtual()));
                }else{
                    tfPlaca.setText(funcionario.getVeiculoAtual().getPlaca());
                    owner.devolveVeiculo(funcionario.getNumeroDeMatricula(), getTfPlaca(), Float.parseFloat(getTfKmAtual()));
                }
                setVisible(false);
            } else if (e.getActionCommand().equals(cancelar)) {
                setVisible(false);
            }
        }

    }

    public String getTfPlaca() {
        return tfPlaca.getText();
    }

    public String getTfKmAtual() {
        return tfKmAtual.getText();
    }

}
