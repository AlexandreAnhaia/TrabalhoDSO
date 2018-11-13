/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.TelasJFrame.TelasVeiculo;

import br.ufsc.ine5605.Controladores.ControladorVeiculo;
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
public class TelaCadastroVeiculoJFrame extends JFrame {

    private ControladorVeiculo owner;
    private JLabel lbPlaca;
    private JTextField tfPlaca;
    private JLabel lbModelo;
    private JTextField tfModelo;
    private JLabel lbMarca;
    private JTextField tfMarca;
    private JLabel lbAno;
    private JTextField tfAno;
    private GerenciadorBotoes btManager;
    private JLabel lbQuilometragemAtual;
    private JTextField tfQuilometragemAtual;
    private JButton btCadastrar;
    private JButton btCancelar;
    private static final String adiciona = "adiciona";
    private static final String cancelar = "cancelar";

    public TelaCadastroVeiculoJFrame(ControladorVeiculo owner) {
        this.owner = owner;
        this.btManager = new GerenciadorBotoes();
        inicializa();
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

        lbModelo = new JLabel();
        lbModelo.setText("Modelo: ");
        constraints.gridx = 0;
        constraints.gridy = 1;
        container.add(lbModelo, constraints);

        tfModelo = new JTextField();
        tfModelo.setText("");
        constraints.gridx = 1;
        constraints.gridy = 1;
        tfModelo.setPreferredSize(new Dimension(100, 20));
        container.add(tfModelo, constraints);

        lbMarca = new JLabel();
        lbMarca.setText("Marca: ");
        constraints.gridx = 0;
        constraints.gridy = 2;
        container.add(lbMarca, constraints);

        tfMarca = new JTextField();
        tfMarca.setText("");
        constraints.gridx = 1;
        constraints.gridy = 2;
        tfMarca.setPreferredSize(new Dimension(100, 20));
        container.add(tfMarca, constraints);

        lbAno = new JLabel();
        lbAno.setText("Ano: ");
        constraints.gridx = 0;
        constraints.gridy = 3;
        container.add(lbAno, constraints);

        tfAno = new JTextField();
        tfAno.setText("");
        constraints.gridx = 1;
        constraints.gridy = 3;
        tfAno.setPreferredSize(new Dimension(100, 20));
        container.add(tfAno, constraints);

        lbQuilometragemAtual = new JLabel();
        lbQuilometragemAtual.setText("Quilometragem Atual: ");
        constraints.gridx = 0;
        constraints.gridy = 4;
        container.add(lbQuilometragemAtual, constraints);

        tfQuilometragemAtual = new JTextField();
        tfQuilometragemAtual.setText("");
        constraints.gridx = 1;
        constraints.gridy = 4;
        tfQuilometragemAtual.setPreferredSize(new Dimension(100, 20));
        container.add(tfQuilometragemAtual, constraints);

        btCadastrar = new JButton();
        btCadastrar.setActionCommand(adiciona);
        btCadastrar.addActionListener(btManager);
        btCadastrar.setText("Cadastrar");
        constraints.gridx = 0;
        constraints.gridy = 5;
        container.add(btCadastrar, constraints);

        btCancelar = new JButton();
        btCancelar.setActionCommand(cancelar);
        btCancelar.addActionListener(btManager);
        btCancelar.setText("Cancelar");
        constraints.gridx = 1;
        constraints.gridy = 5;
        container.add(btCancelar, constraints);

        setSize(800, 400);
        setLocationRelativeTo(null);

    }

    private class GerenciadorBotoes implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (e.getActionCommand().equals(adiciona)) {
                    owner.cadastraVeiculo(getTfPlaca(), getTfModelo(), getTfMarca(), getTfAno(), getTfQuilometragemAtual());
                } else if (e.getActionCommand().equals(cancelar)) {
                    setVisible(false);
                }

            } catch (NumberFormatException erro) {
                JOptionPane.showMessageDialog(null, "O Ano e a Quilometragem Devem Conter Apenas Numeros");
            }

        }
    }

    public String getTfPlaca() {
        return tfPlaca.getText();
    }

    public String getTfModelo() {
        return tfModelo.getText();
    }

    public String getTfMarca() {
        return tfMarca.getText();
    }

    public int getTfAno() {
        return Integer.parseInt(tfAno.getText());
    }

    public float getTfQuilometragemAtual() {
        return Float.parseFloat(tfQuilometragemAtual.getText());
    }
}
