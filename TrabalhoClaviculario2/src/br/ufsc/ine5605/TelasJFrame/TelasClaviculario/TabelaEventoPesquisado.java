/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.TelasJFrame.TelasClaviculario;

import br.ufsc.ine5605.Entidades.Claviculario;
import br.ufsc.ine5605.Entidades.DescricaoEvento;
import br.ufsc.ine5605.Entidades.Evento;
import br.ufsc.ine5605.Entidades.Funcionario;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.text.SimpleDateFormat;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author 09285195970
 */
public class TabelaEventoPesquisado extends JFrame {

    private JTable tbEventoPesquisado;
    private JScrollPane scEventoPesquisado;
    private Claviculario owner;
    private DescricaoEvento descricao;

    public TabelaEventoPesquisado(Claviculario owner) {
        this.owner = owner;
        inicializa();
    }

    public void setDescricaoEvento(DescricaoEvento descricao) {
        this.descricao = descricao;
        atualizaDados();
    }

    public void inicializa() {

        Container container = getContentPane();

        container.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        tbEventoPesquisado = new JTable();
        tbEventoPesquisado.setPreferredScrollableViewportSize(new Dimension(600, 140));
        tbEventoPesquisado.setFillsViewportHeight(true);
        constraints.fill = GridBagConstraints.CENTER;
        constraints.gridwidth = 0;
        constraints.gridheight = 1;
        scEventoPesquisado = new JScrollPane(tbEventoPesquisado);
        container.add(scEventoPesquisado, constraints);
        
        setSize(700, 600);
        setLocationRelativeTo(null);
        
        atualizaDados();
    }

    public void atualizaDados() {

        DefaultTableModel modelTbEvento = new DefaultTableModel();
        modelTbEvento.addColumn("Descrição");
        modelTbEvento.addColumn("Data do Evento");
        modelTbEvento.addColumn("Matricula");
        modelTbEvento.addColumn("Placa");

        for (Evento evento : owner.getEventoPeloTipo(descricao)) {
           modelTbEvento.addRow(new Object[]{evento.getDescricao(), evento.getDataEvento(), evento.getMatricula(), evento.getPlaca()});
     
        }

        tbEventoPesquisado.setModel(modelTbEvento);

        this.repaint();

    }
}
