/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.TelasJFrame.TelasVeiculo;

import br.ufsc.ine5605.Controladores.ControladorVeiculo;
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
public class TabelaVeiculoJFrame extends JFrame {


    private ControladorVeiculo owner;
    private JTable tbVeiculo;
    private JScrollPane scTabelaVeiculo;
    private JButton btCadastraVeiculo;
    private GerenciadorBotoes btManager;
    private JButton btExcluiVeiculo;
    private JButton btAlteraVeiculo;
    private static final String adiciona = "adiciona";
    private static final String exclui = "exclui";
    private static final String altera = "altera";
   

    public TabelaVeiculoJFrame(ControladorVeiculo owner) {
        this.owner = owner;
        this.btManager = new GerenciadorBotoes();
        inicializa();
        atualizaDados();
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

        btCadastraVeiculo = new JButton();
        btCadastraVeiculo.setActionCommand(adiciona);
        btCadastraVeiculo.addActionListener(btManager);
        btCadastraVeiculo.setText("Cadastrar Veiculo");
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 2;
        container.add(btCadastraVeiculo, constraints);

        btExcluiVeiculo = new JButton();
        btExcluiVeiculo.setActionCommand(exclui);
        btExcluiVeiculo.addActionListener(btManager);
        btExcluiVeiculo.setText("Excluir Veiculo");
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 3;
        container.add(btExcluiVeiculo, constraints);

        btAlteraVeiculo = new JButton();
        btAlteraVeiculo.setActionCommand(altera);
        btAlteraVeiculo.addActionListener(btManager);
        btAlteraVeiculo.setText("Altera Veiculo");
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 4;
        container.add(btAlteraVeiculo, constraints);

        setSize(700, 600);
        setLocationRelativeTo(null);

    }

    private class GerenciadorBotoes implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals(adiciona)) {
                owner.getTelaCadastroVeiculo();
                atualizaDados();

            } else if (e.getActionCommand().equals(exclui)) {
                int x = 0;
                Object obj = tbVeiculo.getValueAt(tbVeiculo.getSelectedRow(), x);
                String placa = obj.toString();
                owner.excluirVeiculo(placa);
                atualizaDados();
            } else if (e.getActionCommand().equals(altera)) {
                String placa = (String) tbVeiculo.getValueAt(tbVeiculo.getSelectedRow(), 0);
                owner.getTelaAlteraVeiculo(placa);
                atualizaDados();
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

        for (Veiculo veic : owner.getVeiculos()) {
            modelTbVeiculo.addRow(new Object[]{veic.getPlaca(), veic.getModelo(), veic.getMarca(), veic.getAno(), veic.getQuilometragemAtual()});
        }

        tbVeiculo.setModel(modelTbVeiculo);
        this.repaint();

    }

}