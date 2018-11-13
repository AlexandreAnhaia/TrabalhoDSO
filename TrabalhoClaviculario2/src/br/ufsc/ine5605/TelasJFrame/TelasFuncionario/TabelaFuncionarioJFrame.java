/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.TelasJFrame.TelasFuncionario;

import br.ufsc.ine5605.Controladores.ControladorFuncionario;
import br.ufsc.ine5605.Entidades.Funcionario;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Pichau
 */
public class TabelaFuncionarioJFrame extends JFrame {

    private ControladorFuncionario owner;
    private JTable tbFuncionario;
    private JScrollPane scTabelaFuncionario;
    private JButton btAdiciona;
    private GerenciadorBotoes btManager;
    private JButton btExclui;
    private JButton btAltera;
    private JButton btAdicionaVeiculo;
    private static final String adiciona = "adiciona";
    private static final String exclui = "exclui";
    private static final String altera = "altera";
    private static final String adicionaVeiculo = "adicionaVeiculo";
    
    

    public TabelaFuncionarioJFrame(ControladorFuncionario owner) {
        this.owner = owner;
        this.btManager = new GerenciadorBotoes();
        inicializa();
        atualizaDados();
    }

    public void inicializa() {

        Container container = getContentPane();

        container.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        tbFuncionario = new JTable();
        tbFuncionario.setPreferredScrollableViewportSize(new Dimension(600, 140));
        tbFuncionario.setFillsViewportHeight(true);
        constraints.fill = GridBagConstraints.CENTER;
        constraints.gridwidth = 0;
        constraints.gridheight = 1;
        scTabelaFuncionario = new JScrollPane(tbFuncionario);
        container.add(scTabelaFuncionario, constraints);

        btAdiciona = new JButton();
        btAdiciona.setActionCommand(adiciona);
        btAdiciona.addActionListener(btManager);
        btAdiciona.setText("Cadastrar Funcionario");
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 2;
        container.add(btAdiciona, constraints);

        btExclui = new JButton();
        btExclui.setActionCommand(exclui);
        btExclui.addActionListener(btManager);
        btExclui.setText("Excluir Funcionario");
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 3;
        container.add(btExclui, constraints);

        btAltera = new JButton();
        btAltera.setActionCommand(altera);
        btAltera.addActionListener(btManager);
        btAltera.setText("Altera Funcionario");
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 4;
        container.add(btAltera, constraints);

        btAdicionaVeiculo = new JButton();
        btAdicionaVeiculo.setActionCommand(adicionaVeiculo);
        btAdicionaVeiculo.addActionListener(btManager);
        btAdicionaVeiculo.setText("Adiciona Veiculo no Funcionario");
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 5;
        container.add(btAdicionaVeiculo, constraints);

        setSize(700, 600);
        setLocationRelativeTo(null);

    }

    private class GerenciadorBotoes implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals(adiciona)) {
                owner.telaCadastraFuncionario();
                atualizaDados();

            } else if (e.getActionCommand().equals(exclui)) {
                int x = 0;
                Object obj = tbFuncionario.getValueAt(tbFuncionario.getSelectedRow(), x);
                String matricula = obj.toString();
                owner.excluirFuncionario(Integer.parseInt(matricula));
                atualizaDados();
            } else if (e.getActionCommand().equals(altera)) {
                Integer matricula = (Integer) tbFuncionario.getValueAt(tbFuncionario.getSelectedRow(), 0);
                owner.telaAlteraFuncionarioJFrame(matricula);
                atualizaDados();
            } else if (e.getActionCommand().equals(adicionaVeiculo)) {
                Integer matricula = (Integer) tbFuncionario.getValueAt(tbFuncionario.getSelectedRow(), 0);
                owner.telaAdicionaVeiculosNoFuncionario(matricula);
                atualizaDados();
            }
        }

    }

    public void atualizaDados() {

        DefaultTableModel modelTbFuncionario = new DefaultTableModel();
        modelTbFuncionario.addColumn("Matricula");
        modelTbFuncionario.addColumn("Nome");
        modelTbFuncionario.addColumn("Telefone");
        modelTbFuncionario.addColumn("Data de Nascimento");
        modelTbFuncionario.addColumn("Cargo");

        for (Funcionario func : owner.getFuncionarios()) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String sDate = sdf.format(func.getDataDeNascimento());
            modelTbFuncionario.addRow(new Object[]{func.getNumeroDeMatricula(), func.getNome(), func.getTelefone(), sDate, func.getCargo()});
        }

        tbFuncionario.setModel(modelTbFuncionario);
        this.repaint();

    }

    public JTable getTbFuncionario() {
        return tbFuncionario;
    }

}
