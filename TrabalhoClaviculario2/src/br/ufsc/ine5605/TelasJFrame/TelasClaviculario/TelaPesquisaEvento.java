/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.TelasJFrame.TelasClaviculario;

import br.ufsc.ine5605.Entidades.Claviculario;
import br.ufsc.ine5605.Entidades.DescricaoEvento;
import br.ufsc.ine5605.Entidades.Evento;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author 09285195970
 */
public class TelaPesquisaEvento extends JFrame{
    
    private Claviculario owner;
    private static final String bloqueado = "1";
    private static final String permitido = "2";
    private static final String inexistente = "3";
    private static final String naoPossuiAcesso = "4";
    private static final String devolvido = "5";
    private static final String indisponivel = "6";
    private Container container = getContentPane();
    private GerenciadorBotoes btManager;
    private JButton btAcessoBloqueado;
    private JButton btAcessoPermitidoAoVeiculo;
    private JButton btMatriculaInexistente;
    private JButton btNaoPossuiAcessoAoVeiculo;
    private JButton btVeiculoDevolvido;
    private JButton btVeiculoIndisponivel;
    
    
    
    

    public TelaPesquisaEvento(Claviculario owner) {
        this.owner = owner;
        this.btManager = new GerenciadorBotoes();
        inicializa();
    }

    public void inicializa() {

        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        btAcessoBloqueado = new JButton();
        btAcessoBloqueado.setActionCommand(bloqueado);
        btAcessoBloqueado.addActionListener(btManager);
        btAcessoBloqueado.setText("Acesso Bloqueado");
        btAcessoBloqueado.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(btAcessoBloqueado);

        btAcessoPermitidoAoVeiculo = new JButton();
        btAcessoPermitidoAoVeiculo.setActionCommand(permitido);
        btAcessoPermitidoAoVeiculo.addActionListener(btManager);
        btAcessoPermitidoAoVeiculo.setText("Acesso Permitido");
        btAcessoPermitidoAoVeiculo.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(btAcessoPermitidoAoVeiculo);

        btMatriculaInexistente = new JButton();
        btMatriculaInexistente.setActionCommand(inexistente);
        btMatriculaInexistente.addActionListener(btManager);
        btMatriculaInexistente.setText("Matricula Inexistente");
        btMatriculaInexistente.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(btMatriculaInexistente);
        
        
        btNaoPossuiAcessoAoVeiculo = new JButton();
        btNaoPossuiAcessoAoVeiculo.setActionCommand(naoPossuiAcesso);
        btNaoPossuiAcessoAoVeiculo.addActionListener(btManager);
        btNaoPossuiAcessoAoVeiculo.setText("Nao Possui Acesso ao Veiculo");
        btNaoPossuiAcessoAoVeiculo.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(btNaoPossuiAcessoAoVeiculo);
        
        btVeiculoDevolvido = new JButton();
        btVeiculoDevolvido.setActionCommand(devolvido);
        btVeiculoDevolvido.addActionListener(btManager);
        btVeiculoDevolvido.setText("Veiculo Devolvido");
        btVeiculoDevolvido.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(btVeiculoDevolvido);
        
        btVeiculoIndisponivel = new JButton();
        btVeiculoIndisponivel.setActionCommand(indisponivel);
        btVeiculoIndisponivel.addActionListener(btManager);
        btVeiculoIndisponivel.setText("Veiculo Indisponivel");
        btVeiculoIndisponivel.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(btVeiculoIndisponivel);

        setSize(300, 200);
  
        setLocationRelativeTo(null);
    }

    private class GerenciadorBotoes implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            int opcao = Integer.parseInt(e.getActionCommand());

            switch (opcao) {
                case 1:
                    owner.tabelaEventoPesquisado(DescricaoEvento.ACESSOBLOQUEADO);
                    break;
                case 2:
                    owner.tabelaEventoPesquisado(DescricaoEvento.ACESSOPERMITIDOAOVEICULO);
                    break;
                case 3:
                    owner.tabelaEventoPesquisado(DescricaoEvento.MATRICULAINEXISTENTE);
                    break;
                case 4:
                    owner.tabelaEventoPesquisado(DescricaoEvento.NAOPOSSUIACESSOAOVEICULO);
                    break;
                case 5:
                    owner.tabelaEventoPesquisado(DescricaoEvento.VEICULODEVOLVIDO);
                    break;
                case 6:
                    owner.tabelaEventoPesquisado(DescricaoEvento.VEICULOINDISPONIVEL);
                    break;
            }
        }

    }
}
