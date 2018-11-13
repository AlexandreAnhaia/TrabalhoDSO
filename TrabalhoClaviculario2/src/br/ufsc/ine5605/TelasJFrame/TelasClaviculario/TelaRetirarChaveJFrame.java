/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.TelasJFrame.TelasClaviculario;

import br.ufsc.ine5605.Entidades.Claviculario;
import br.ufsc.ine5605.Entidades.Funcionario;
import br.ufsc.ine5605.Entidades.Veiculo;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Pichau
 */
public class TelaRetirarChaveJFrame extends JFrame {

    private Claviculario owner;
    private Funcionario funcionario;
    private JTable tbVeiculo;
    private JButton btRetirar;
    private JButton btCancelar;
    private static final String retirar = "retirar";
    private static final String cancelar = "cancelar";
    private JScrollPane scTabelaVeiculo;
    private GerenciadorBotoes btManager;

    public TelaRetirarChaveJFrame(Claviculario owner) {
        this.owner = owner;
        this.btManager = new GerenciadorBotoes();
        this.funcionario = new Funcionario();
        inicializa();
    }

    public void inicializa() {

        Container container = getContentPane();

        container.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        tbVeiculo = new JTable();
        tbVeiculo.setPreferredScrollableViewportSize(new Dimension(600, 140));
        tbVeiculo.setFillsViewportHeight(true);
        constraints.fill = GridBagConstraints.CENTER;
        constraints.gridwidth = 0;
        constraints.gridheight = 1;
        scTabelaVeiculo = new JScrollPane(tbVeiculo);
        container.add(scTabelaVeiculo, constraints);

        btRetirar = new JButton();
        btRetirar.setActionCommand(retirar);
        btRetirar.addActionListener(btManager);
        btRetirar.setText("Retirar Chave");
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 2;
        container.add(btRetirar, constraints);

        btCancelar = new JButton();
        btCancelar.setActionCommand(cancelar);
        btCancelar.addActionListener(btManager);
        btCancelar.setText("Cancelar");
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 3;
        container.add(btCancelar, constraints);

        setSize(700, 600);
        setLocationRelativeTo(null);
        atualizaDados();

    }

    private class GerenciadorBotoes implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals(retirar)) {
                int x = 0;
                Object obj = tbVeiculo.getValueAt(tbVeiculo.getSelectedRow(), x);
                String placa = obj.toString();
                owner.emprestaVeiculo(funcionario.getNumeroDeMatricula(), placa);
                atualizaDados();
                setVisible(false);
            } else if (e.getActionCommand().equals(cancelar)) {
                setVisible(false);
            }
        }

    }

    public void atualizaDados() {

        DefaultTableModel modelTbVeiculo = new DefaultTableModel();
        modelTbVeiculo.addColumn("Placa");
        modelTbVeiculo.addColumn("Modelo");
        modelTbVeiculo.addColumn("Marca");
        modelTbVeiculo.addColumn("Ano");
        modelTbVeiculo.addColumn("Km Atual");

        for (Veiculo veic : funcionario.getVeiculos()) {
            modelTbVeiculo.addRow(new Object[]{veic.getPlaca(), veic.getModelo(), veic.getMarca(), veic.getAno(), veic.getQuilometragemAtual()});
        }

        tbVeiculo.setModel(modelTbVeiculo);
        this.repaint();

    }

    public void setFuncionario(Funcionario funcionario) {
        if(funcionario != null){
            this.funcionario = funcionario;
        }
    }

}
