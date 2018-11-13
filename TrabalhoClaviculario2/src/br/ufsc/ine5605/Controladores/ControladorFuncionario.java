/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.Controladores;

import br.ufsc.ine5605.Entidades.CargoFuncionario;
import static br.ufsc.ine5605.Entidades.CargoFuncionario.DIRETOR;
import br.ufsc.ine5605.Entidades.Funcionario;
import br.ufsc.ine5605.Entidades.Veiculo;
import br.ufsc.ine5605.Persistencia.FuncionarioDAO;
import br.ufsc.ine5605.TelasJFrame.TelasFuncionario.TelaAdicionaVeiculosNoFuncionarioJFrame;
import br.ufsc.ine5605.TelasJFrame.TelasFuncionario.TelaCadastroFuncionarioJFrame;
import br.ufsc.ine5605.TelasJFrame.TelasFuncionario.TabelaFuncionarioJFrame;
import br.ufsc.ine5605.TelasJFrame.TelasFuncionario.TelaAlteraFuncionarioJFrame;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Pichau
 */
public class ControladorFuncionario {

    private FuncionarioDAO funcionarioDAO;
    private ControladorPrincipal owner;
    private TelaCadastroFuncionarioJFrame telaCadastroFuncionarioJFrame;
    private TelaAdicionaVeiculosNoFuncionarioJFrame telaAdicionaVeiculosNoFuncionario;
    private TabelaFuncionarioJFrame tabelaFuncionarioJFrame;
    private TelaAlteraFuncionarioJFrame telaAlteraFuncionarioJFrame;

    public ControladorFuncionario(ControladorPrincipal owner) {
        
        this.funcionarioDAO = new FuncionarioDAO();
        this.telaCadastroFuncionarioJFrame = new TelaCadastroFuncionarioJFrame(this);
        this.owner = owner;
        this.telaAdicionaVeiculosNoFuncionario = new TelaAdicionaVeiculosNoFuncionarioJFrame(this);
        this.tabelaFuncionarioJFrame = new TabelaFuncionarioJFrame(this);
        this.telaAlteraFuncionarioJFrame = new TelaAlteraFuncionarioJFrame(this);

    }

    public void telaAlteraFuncionarioJFrame(Integer matricula) {
        Funcionario func = getFuncionario(matricula);
        telaAlteraFuncionarioJFrame.setFuncionario(func);
        telaAlteraFuncionarioJFrame.setVisible(true);
    }

    public void telaAdicionaVeiculosNoFuncionario(Integer matricula) {
        telaAdicionaVeiculosNoFuncionario.atualizaDados();
        Funcionario func = getFuncionario(matricula);
        telaAdicionaVeiculosNoFuncionario.setFuncionario(func);
        telaAdicionaVeiculosNoFuncionario.setVisible(true);
    }

    public void exibeMenuFuncionario() throws ParseException {
        tabelaFuncionarioJFrame.setVisible(true);
    }

    public TabelaFuncionarioJFrame getTabelaFuncionarioJFrame() {
        return tabelaFuncionarioJFrame;
    }



    public boolean validaFuncionario(Funcionario funcionario) {
        boolean funcionariovalido = true;
        if (funcionario != null) {
            for (Funcionario f : funcionarioDAO.getFuncionarios()) {
                if (f.getNumeroDeMatricula() == funcionario.getNumeroDeMatricula()) {
                    JOptionPane.showMessageDialog(null, "Matricula Ja Cadastrada");
                    funcionariovalido = false;

                }
            }
        }

        return funcionariovalido;
    }

    public void excluirFuncionario(int numeroMatricula) {

        Funcionario funcionario = funcionarioDAO.get(numeroMatricula);
        if (funcionario.getVeiculoAtual() == null) {
            funcionarioDAO.remove(funcionario);
            tabelaFuncionarioJFrame.atualizaDados();
            funcionarioDAO.gravaDados();

        }

    }

    public Funcionario getFuncionario(int numeroMatricula) {
        return funcionarioDAO.get(numeroMatricula);
    }

    public ArrayList<Funcionario> getFuncionarios() {
        funcionarioDAO.gravaDados();
        return new ArrayList(funcionarioDAO.getFuncionarios());
    }

    public void addVeiculoNoFuncionario(int matricula, String placa) {

        Veiculo veic = owner.getCtrlVeiculo().getVeiculo(placa);
        Funcionario func = getFuncionario(matricula);

        if (veic != null) {
            if (func != null) {
                if (!func.getCargo().equals(DIRETOR)) {
                    func.addVeiculo(veic);
                    funcionarioDAO.gravaDados();
                } else {
                    JOptionPane.showMessageDialog(null, "O Diretor ja possui todos os veiculos!");
                }
            }

        }

    }

    public void excluiVeiculoDoFuncionario(int Matricula, String placa) {
        Funcionario func = funcionarioDAO.get(Matricula);
        Veiculo veic = func.getVeiculo(placa);
        telaAdicionaVeiculosNoFuncionario.atualizaDados();
        if (func != null) {
            if (veic != null) {
                if (!func.getCargo().equals(DIRETOR)) {
                    if (veic.getEmprestado() == false) {
                        func.removeVeiculo(veic);
                        funcionarioDAO.gravaDados();
                        telaAdicionaVeiculosNoFuncionario.atualizaDados();
                        if (func.getVeiculoAtual() != null) {
                            veic.setEmprestado(false);
                            func.setVeiculoAtual(null);
                            telaAdicionaVeiculosNoFuncionario.atualizaDados();
                            tabelaFuncionarioJFrame.atualizaDados();
                            funcionarioDAO.gravaDados();

                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Veiculo deve ser devolvido!");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Nao e possivel excluir veiculo de um DIRETOR!");
                }

            }
        }
    }

    public void telaCadastraFuncionario() {
        telaCadastroFuncionarioJFrame.setVisible(true);
    }

    public void cadastraFuncionario(int numeroDeMatricula, String nome, Date dataDeNascimento, String telefone, CargoFuncionario cargo) {

        Funcionario funcionario = new Funcionario(numeroDeMatricula, nome, dataDeNascimento, telefone, cargo);
        ArrayList<Veiculo> veiculos = new ArrayList<>();
        veiculos.addAll(owner.getCtrlVeiculo().getVeiculos());
        if (funcionario != null) {
            if (validaFuncionario(funcionario)) {
                if (funcionario.getCargo().equals(DIRETOR)) {
                    funcionario.setVeiculosAcessiveis(veiculos);
                } else {
                    funcionario.getVeiculoDAO().getVeiculos().clear();
                }
                funcionarioDAO.put(funcionario);
                atualizaDados();
            }
        }
        tabelaFuncionarioJFrame.atualizaDados();
        telaCadastroFuncionarioJFrame.setVisible(false);

    }

    public void alteraFuncionario(Funcionario funcionario) {
        ArrayList<Veiculo> todosOsVeiculos = new ArrayList<>();
        ArrayList<Veiculo> veiculosDoFUncionario = new ArrayList<>();
        veiculosDoFUncionario = funcionario.getVeiculos();
        todosOsVeiculos.addAll(owner.getCtrlVeiculo().getVeiculos());
        funcionarioDAO.put(funcionario);
        if (funcionario.getCargo().equals(DIRETOR)) {
            for (Veiculo veic : todosOsVeiculos) {
                funcionario.addVeiculo(veic);
            }
            telaAdicionaVeiculosNoFuncionario.atualizaDados();
            funcionarioDAO.gravaDados();
        } else {
            funcionario.getVeiculoDAO().getVeiculos().clear();
            for (Veiculo veic : veiculosDoFUncionario) {
                funcionario.addVeiculo(veic);
                funcionarioDAO.gravaDados();
            }
            telaAdicionaVeiculosNoFuncionario.atualizaDados();
            funcionarioDAO.gravaDados();
        }
        telaAdicionaVeiculosNoFuncionario.atualizaDados();
        tabelaFuncionarioJFrame.atualizaDados();
        funcionarioDAO.gravaDados();
        telaAlteraFuncionarioJFrame.setVisible(false);
    }

    public void getFuncionarioDaTabela(int matricula) {
        Funcionario func = getFuncionario(matricula);
        

    }

    public ArrayList<Veiculo> getTodosOsVeiculos() {
        ArrayList<Veiculo> todosOsVeiculos = new ArrayList<>();
        todosOsVeiculos.addAll(owner.getCtrlVeiculo().getVeiculos());
        return todosOsVeiculos;
    }
    
    public void atualizaDados(){
        funcionarioDAO.gravaDados();
    }

}
