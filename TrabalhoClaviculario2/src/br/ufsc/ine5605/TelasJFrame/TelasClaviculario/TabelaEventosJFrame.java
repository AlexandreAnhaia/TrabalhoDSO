/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.TelasJFrame.TelasClaviculario;

import br.ufsc.ine5605.Entidades.Claviculario;
import br.ufsc.ine5605.Entidades.Evento;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Pichau
 */
public class TabelaEventosJFrame extends JFrame {

    private Claviculario owner;
    private JTable tbEventos;
    private JScrollPane scTabelaEventos;

    public TabelaEventosJFrame(Claviculario owner) {
        this.owner = owner;
         inicializa();
    }
    
    public void inicializa() {

        Container container = getContentPane();

        container.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        tbEventos = new JTable();
        tbEventos.setPreferredScrollableViewportSize(new Dimension(600, 140));
        tbEventos.setFillsViewportHeight(true);
        constraints.fill = GridBagConstraints.CENTER;
        constraints.gridwidth = 0;
        constraints.gridheight = 1;
        scTabelaEventos = new JScrollPane(tbEventos);
        container.add(scTabelaEventos, constraints);
        
        setSize(700, 600);
        setLocationRelativeTo(null);
        atualizaDados();
    }
    
    public void atualizaDados() {

        DefaultTableModel modelTbEventos = new DefaultTableModel();
        modelTbEventos.addColumn("Descrição");
        modelTbEventos.addColumn("Data do Evento");
        modelTbEventos.addColumn("Matricula");
        modelTbEventos.addColumn("Placa");

        for (Evento evento : owner.getEventos()) {
            modelTbEventos.addRow(new Object[]{evento.getDescricao(), evento.getDataEvento(), evento.getMatricula(), evento.getPlaca()});
        }

        tbEventos.setModel(modelTbEventos);
        this.repaint();

    }

}
