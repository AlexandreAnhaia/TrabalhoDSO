/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.TelasJFrame.TelasFuncionario;

import br.ufsc.ine5605.Controladores.ControladorFuncionario;
import br.ufsc.ine5605.Entidades.Funcionario;
import br.ufsc.ine5605.Entidades.Veiculo;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author alexa
 */
public class TelaAdicionaVeiculosNoFuncionarioJFrame extends JFrame {

    private ControladorFuncionario owner;
    private JTable tbVeiculoFuncionario;
    private JScrollPane scTabelaFuncionario;
    private JTable tbVeiculos;
    private JScrollPane scTabelaVeiculos;
    private JButton btAdiciona;
    private GerenciadorBotoes btManager;
    private JButton btExclui;
    private JButton btAltera;
    private JButton btAdicionaVeiculo;
    private JComboBox veiculos;
    private Funcionario func;
    private static final String adiciona = "adiciona";
    private static final String exclui = "exclui";
    

    public TelaAdicionaVeiculosNoFuncionarioJFrame(ControladorFuncionario owner) {
        this.owner = owner;
        this.func = new Funcionario();
        this.btManager = new GerenciadorBotoes();
        inicializa();
        atualizaDados();

    }

    public void inicializa() {

        Container container = getContentPane();

        container.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        tbVeiculoFuncionario = new JTable();
        tbVeiculoFuncionario.setPreferredScrollableViewportSize(new Dimension(600, 140));
        tbVeiculoFuncionario.setFillsViewportHeight(true);
        constraints.fill = GridBagConstraints.CENTER;
        constraints.gridwidth = 0;
        constraints.gridheight = 1;
        scTabelaFuncionario = new JScrollPane(tbVeiculoFuncionario);
        container.add(scTabelaFuncionario, constraints);

        btAdiciona = new JButton();
        btAdiciona.setActionCommand(adiciona);
        btAdiciona.addActionListener(btManager);
        btAdiciona.setText("Adiciona Veiculo");
        constraints.fill = GridBagConstraints.VERTICAL;
        constraints.gridx = 0;
        constraints.gridy = 1;
        container.add(btAdiciona, constraints);

        btExclui = new JButton();
        btExclui.setActionCommand(exclui);
        btExclui.addActionListener(btManager);
        btExclui.setText("Remove Veiculo");
        constraints.fill = GridBagConstraints.VERTICAL;
        constraints.gridx = 0;
        constraints.gridy = 2;
        container.add(btExclui, constraints);

        tbVeiculos = new JTable();
        tbVeiculos.setPreferredScrollableViewportSize(new Dimension(600, 140));
        tbVeiculos.setFillsViewportHeight(true);
        constraints.fill = GridBagConstraints.CENTER;
        constraints.gridwidth = 5;
        constraints.gridheight = 5;
        scTabelaVeiculos = new JScrollPane(tbVeiculos);
        container.add(scTabelaVeiculos, constraints);

        setSize(1200, 800);
        setLocationRelativeTo(null);

    }

    public void setFuncionario(Funcionario funcionarioDaTabela) {
        this.func = funcionarioDaTabela;
        atualizaDados();
    }

    private class GerenciadorBotoes implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals(adiciona)) {
                int x = 0;
                Object obj = tbVeiculos.getValueAt(tbVeiculos.getSelectedRow(), x);
                String placa = obj.toString();
                atualizaDados();
         
                if (placa != null) {
                    owner.addVeiculoNoFuncionario(func.getNumeroDeMatricula(), placa);
                    atualizaDados();
                }

            } else if (e.getActionCommand().equals(exclui)) {

                int x = 0;
                Object obj = tbVeiculoFuncionario.getValueAt(tbVeiculoFuncionario.getSelectedRow(), x);
                String placa = obj.toString();
                atualizaDados();
                owner.excluiVeiculoDoFuncionario(func.getNumeroDeMatricula(), placa);
                atualizaDados();
            }
        }

    }

    public void atualizaDados() {

        DefaultTableModel modelTbFuncionario = new DefaultTableModel();
        modelTbFuncionario.addColumn("Placa");
        modelTbFuncionario.addColumn("Modelo");
        modelTbFuncionario.addColumn("Marca");
        modelTbFuncionario.addColumn("Ano");
        modelTbFuncionario.addColumn("Quilometragem");

        DefaultTableModel modelTbVeiculos = new DefaultTableModel();
        modelTbVeiculos.addColumn("Placa");
        modelTbVeiculos.addColumn("Modelo");
        modelTbVeiculos.addColumn("Marca");
        modelTbVeiculos.addColumn("Ano");
        modelTbVeiculos.addColumn("Quilometragem");

        for (Veiculo veic : owner.getTodosOsVeiculos()) {
            if (func.getNome() != null) {
                modelTbVeiculos.addRow(new Object[]{veic.getPlaca(), veic.getModelo(), veic.getMarca(), veic.getAno(), veic.getQuilometragemAtual()});

            }
        }

        for (Veiculo veic : func.getVeiculos()) {
            if (func.getNome() != null) {
                modelTbFuncionario.addRow(new Object[]{veic.getPlaca(), veic.getModelo(), veic.getMarca(), veic.getAno(), veic.getQuilometragemAtual()});

            }
        }

        tbVeiculos.setModel(modelTbVeiculos);
        tbVeiculoFuncionario.setModel(modelTbFuncionario);
        this.repaint();

    }

    public JTable getTbFuncionario() {
        return tbVeiculoFuncionario;
    }

}
