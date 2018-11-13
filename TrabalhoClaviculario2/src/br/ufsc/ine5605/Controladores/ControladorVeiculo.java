/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.Controladores;

import static br.ufsc.ine5605.Entidades.CargoFuncionario.DIRETOR;
import br.ufsc.ine5605.Entidades.Funcionario;
import br.ufsc.ine5605.Entidades.Veiculo;
import br.ufsc.ine5605.Persistencia.VeiculoDAO;
import br.ufsc.ine5605.TelasJFrame.TelasVeiculo.TelaAlteraVeiculoJFrame;
import br.ufsc.ine5605.TelasJFrame.TelasVeiculo.TelaCadastroVeiculoJFrame;
import br.ufsc.ine5605.TelasJFrame.TelasVeiculo.TabelaVeiculoJFrame;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Pichau
 */
public class ControladorVeiculo {

    private TabelaVeiculoJFrame tabelaVeiculoJframe;
    private ControladorPrincipal owner;
    private VeiculoDAO veiculoDAO = new VeiculoDAO();
    private TelaCadastroVeiculoJFrame telaCadastroVeiculo;
    private TelaAlteraVeiculoJFrame telaAlteraVeiculo;

    public ControladorVeiculo(ControladorPrincipal owner) {
        this.tabelaVeiculoJframe = new TabelaVeiculoJFrame(this);
        this.owner = owner;
        this.veiculoDAO = new VeiculoDAO();
        this.telaCadastroVeiculo = new TelaCadastroVeiculoJFrame(this);
        this.telaAlteraVeiculo = new TelaAlteraVeiculoJFrame(this);
    }

    public boolean validaVeiculo(Veiculo veiculo) {
        boolean veiculoValido = true;

        if (veiculo != null) {
            for (Veiculo vei : veiculoDAO.getVeiculos()) {
                if (vei.getPlaca().equals(veiculo.getPlaca())) {
                    JOptionPane.showMessageDialog(null, "Placa já cadastrada");
                    veiculoValido = false;
                }
            }
        }
        return veiculoValido;
    }

    public void excluirVeiculo(String placa) {
        Veiculo veiculo = veiculoDAO.get(placa);
        if (veiculo != null) {
            veiculoDAO.remove(veiculo);
            for (Funcionario func : owner.getCtrlFuncionario().getFuncionarios()) {
                if (func.getVeiculos().contains(veiculo)) {
                    func.removeVeiculo(veiculo);
                    func.getVeiculoDAO().gravaDados();
                }
            }
            veiculoDAO.gravaDados();

        }

    }

    public Veiculo getVeiculo(String placa) {
        return veiculoDAO.get(placa);
    }

    public void alteraVeiculo(Veiculo veiculo) {
        veiculoDAO.put(veiculo);
        for (Funcionario func : owner.getCtrlFuncionario().getFuncionarios()) {
            if (func.getVeiculos().contains(veiculo)) {
                func.addVeiculo(veiculo);
            }
        }
        veiculoDAO.gravaDados();
        tabelaVeiculoJframe.atualizaDados();
    }

    public ArrayList<Veiculo> getVeiculos() {
        return new ArrayList(veiculoDAO.getVeiculos());
    }

    public void exibeMenuVeiculo() {
        tabelaVeiculoJframe.setVisible(true);
    }

    public void getTelaCadastroVeiculo() {
        telaCadastroVeiculo.setVisible(true);
    }

    public void getTelaAlteraVeiculo(String placa) {
        Veiculo veiculo = getVeiculo(placa);
        telaAlteraVeiculo.setVeiculo(veiculo);
        telaAlteraVeiculo.setVisible(true);
    }

    public void atualizaDados() {
        veiculoDAO.gravaDados();
    }

    public void cadastraVeiculo(String tfPlaca, String tfModelo, String tfMarca, int tfAno, float tfQuilometragemAtual) {
        Veiculo veic = new Veiculo(tfPlaca, tfModelo, tfMarca, tfAno, tfQuilometragemAtual);
        if (validaVeiculo(veic)) {
            veiculoDAO.put(veic);
            tabelaVeiculoJframe.atualizaDados();
            telaCadastroVeiculo.setVisible(false);
            for (Funcionario f : owner.getCtrlFuncionario().getFuncionarios()) {
                if (f.getCargo().equals(DIRETOR)) {
                    f.addVeiculo(veic);
                    veiculoDAO.gravaDados();
                }
            }
        }

    }

}
