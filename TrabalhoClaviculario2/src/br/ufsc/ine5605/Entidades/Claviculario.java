/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.Entidades;


import br.ufsc.ine5605.Controladores.ControladorPrincipal;
import static br.ufsc.ine5605.Entidades.DescricaoEvento.ACESSOBLOQUEADO;
import static br.ufsc.ine5605.Entidades.DescricaoEvento.ACESSOPERMITIDOAOVEICULO;
import static br.ufsc.ine5605.Entidades.DescricaoEvento.MATRICULAINEXISTENTE;
import static br.ufsc.ine5605.Entidades.DescricaoEvento.NAOPOSSUIACESSOAOVEICULO;
import static br.ufsc.ine5605.Entidades.DescricaoEvento.VEICULODEVOLVIDO;
import static br.ufsc.ine5605.Entidades.DescricaoEvento.VEICULOINDISPONIVEL;
import br.ufsc.ine5605.Persistencia.ClavicularioDAO;
import br.ufsc.ine5605.TelasJFrame.TelasClaviculario.TabelaEventoPesquisado;
import br.ufsc.ine5605.TelasJFrame.TelasClaviculario.TabelaEventosJFrame;
import br.ufsc.ine5605.TelasJFrame.TelasClaviculario.TelaClavicularioJFrame;
import br.ufsc.ine5605.TelasJFrame.TelasClaviculario.TelaDevolverChaveJFrame;
import br.ufsc.ine5605.TelasJFrame.TelasClaviculario.TelaPegaFuncionarioDevolveVeiculoJFrame;
import br.ufsc.ine5605.TelasJFrame.TelasClaviculario.TelaPegaFuncionarioJFrame;
import br.ufsc.ine5605.TelasJFrame.TelasClaviculario.TelaPesquisaEvento;
import br.ufsc.ine5605.TelasJFrame.TelasClaviculario.TelaRetirarChaveJFrame;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;

/**
 *
 * @author Fernando Miara
 */
public class Claviculario {

    /**
     * @param args the command line arguments
     */
    private TelaClavicularioJFrame telaClavicularioJFrame;
    private ArrayList<Funcionario> funcionarios;
    private ControladorPrincipal owner;
    private GregorianCalendar gc;
    private int contador = 0;
    private ClavicularioDAO clavicularioDAO;
    private TelaPegaFuncionarioJFrame telaPegaFuncionarioJFrame;
    private TelaRetirarChaveJFrame telaRetirarChaveJFrame;
    private TelaDevolverChaveJFrame telaDevolverChaveJFrame;
    private TelaPegaFuncionarioDevolveVeiculoJFrame telaPegaFuncionarioDevolveVeiculoJFrame;
    private TabelaEventosJFrame tabelaEventosJFrame;
    private TelaPesquisaEvento telaPesquisaEvento;
    private TabelaEventoPesquisado tabelaPesquisaEvento;

    public Claviculario(ControladorPrincipal owner) {
        
        this.owner = owner;
        this.funcionarios = new ArrayList<>();
        this.gc = new GregorianCalendar();
        this.clavicularioDAO = new ClavicularioDAO();
        this.telaPegaFuncionarioJFrame = new TelaPegaFuncionarioJFrame(this);
        this.telaRetirarChaveJFrame = new TelaRetirarChaveJFrame(this);
        this.telaDevolverChaveJFrame = new TelaDevolverChaveJFrame(this);
        this.telaPegaFuncionarioDevolveVeiculoJFrame = new TelaPegaFuncionarioDevolveVeiculoJFrame(this);
        this.tabelaEventosJFrame = new TabelaEventosJFrame(this);
        this.telaClavicularioJFrame = new TelaClavicularioJFrame(this);
        this.telaPesquisaEvento = new TelaPesquisaEvento(this);
        this.tabelaPesquisaEvento = new TabelaEventoPesquisado(this);
    }

    public ArrayList<Evento> getEventos() {
        return clavicularioDAO.getEventos();
    }

    public void getTelaclavicularioJFrame() {
        funcionarios = owner.getCtrlFuncionario().getFuncionarios();
        clavicularioDAO.atualizDados();
        telaClavicularioJFrame.setVisible(true);
    }

    public void emprestaVeiculo(int matricula, String placa) {
        funcionarios = owner.getCtrlFuncionario().getFuncionarios();
        if (validaMatricula(matricula)) {
            for (Funcionario func : funcionarios) {
                if (func.getNumeroDeMatricula() == matricula) {
                    if (func.isBloqueado() == false) {
                        Veiculo v = func.getVeiculo(placa);
                        if (verificaVeiculo(placa)) {
                            if (verfificaSeFuncionarioTemAcessoAoVeiculo(placa, func)) {
                                if (!veiculoEmprestado(v)) {
                                    if (func.getVeiculoAtual() != null) {
                                        JOptionPane.showMessageDialog(null, "Funcionario já está utilizando um veiculo!");
                                    } else {
                                        func.setVeiculoAtual(v);
                                        v.setEmprestado(true);
                                        for (Funcionario funcionario : owner.getCtrlFuncionario().getFuncionarios()) {
                                            if (funcionario.getVeiculos().contains(v)) {
                                                funcionario.getVeiculo(v.getPlaca()).setEmprestado(true);
                                            }
                                        }
                                        Evento e = new AcessoPermitido(ACESSOPERMITIDOAOVEICULO, gc.getTime(), matricula, placa);
                                        JOptionPane.showMessageDialog(null, e.getDescricao().detalhes);
                                        clavicularioDAO.put(e);
                                        owner.getCtrlVeiculo().atualizaDados();
                                        tabelaEventosJFrame.atualizaDados();
                                        break;

                                    }
                                } else {
                                    Evento e4 = new AcessoNegado(VEICULOINDISPONIVEL, gc.getTime(), matricula, placa);
                                    JOptionPane.showMessageDialog(null, e4.getDescricao().detalhes);
                                    clavicularioDAO.put(e4);
                                    tabelaEventosJFrame.atualizaDados();
                                }

                            } else {
                                Evento e2 = new AcessoNegado(NAOPOSSUIACESSOAOVEICULO, gc.getTime(), matricula, placa);
                                JOptionPane.showMessageDialog(null, e2.getDescricao().detalhes);
                                clavicularioDAO.put(e2);
                                tabelaEventosJFrame.atualizaDados();
                                contador = contador + 1;
                                emiteBloqueio(func, placa);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Veiculo Inexistente!");
                        }
                    } else {
                        Evento evento1 = new AcessoNegado(ACESSOBLOQUEADO, gc.getTime(), func.getNumeroDeMatricula(), placa);
                        clavicularioDAO.put(evento1);
                        tabelaEventosJFrame.atualizaDados();
                        JOptionPane.showMessageDialog(null, evento1.getDescricao().detalhes);
                    }
                }
            }
        } else {
            Evento e3 = new AcessoNegado(MATRICULAINEXISTENTE, gc.getTime(), matricula, placa);
            JOptionPane.showMessageDialog(null, e3.getDescricao().detalhes);
            clavicularioDAO.put(e3);
            tabelaEventosJFrame.atualizaDados();
        }
    }

    public boolean veiculoEmprestado(Veiculo v) {
        boolean emprestado = false;
        if (v.getEmprestado() == true) {
            emprestado = true;
            v.setEmprestado(true);

        }
        return emprestado;
    }

    public boolean validaMatricula(int matricula) {
         funcionarios = owner.getCtrlFuncionario().getFuncionarios();
        boolean funcionarioValido = false;
        for (Funcionario f : funcionarios) {
            if (f.getNumeroDeMatricula() == matricula) {
                funcionarioValido = true;
                break;
            }
        }
        return funcionarioValido;
    }

    public void devolveVeiculo(int matricula, String placa, double quilometragemAtual) {
         funcionarios = owner.getCtrlFuncionario().getFuncionarios();
        if (validaMatricula(matricula)) {
            for (Funcionario func : funcionarios) {
                if (func.getNumeroDeMatricula() == matricula) {
                    if (func.getVeiculoAtual() != null) {
                        if (func.getVeiculoAtual().getPlaca().equals(placa)) {
                            if (quilometragemAtual >= func.getVeiculoAtual().getQuilometragemAtual()) {
                                func.getVeiculoAtual().setQuilometragemAtual(quilometragemAtual);
                                func.getVeiculoAtual().setEmprestado(false);
                                func.getVeiculoDAO().gravaDados();
                                for (Funcionario funcionario : owner.getCtrlFuncionario().getFuncionarios()) {
                                    if (funcionario.getVeiculos().contains(func.getVeiculoAtual())) {
                                        funcionario.getVeiculo(func.getVeiculoAtual().getPlaca()).setEmprestado(false);
                                        funcionario.getVeiculoDAO().gravaDados();
                                    }
                                }
                                func.setVeiculoAtual(null);

                                Evento e = new Devolucao(VEICULODEVOLVIDO, gc.getTime(), matricula, placa);
                                JOptionPane.showMessageDialog(null, e.getDescricao().detalhes);
                                clavicularioDAO.put(e);
                                tabelaEventosJFrame.atualizaDados();
                            } else {
                                JOptionPane.showMessageDialog(null, "Quilometragem Incorreta");
                            }

                        } else {
                            JOptionPane.showMessageDialog(null, "Veiculo Inexistente");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Nenhum Veiculo Foi Emprestado");
                    }
                }
            }
        } else {
            Evento e2 = new AcessoNegado(MATRICULAINEXISTENTE, gc.getTime(), matricula, placa);
            JOptionPane.showMessageDialog(null, e2.getDescricao().detalhes);
            clavicularioDAO.put(e2);
            tabelaEventosJFrame.atualizaDados();
        }
    }

    public boolean verificaVeiculo(String placa) {
        boolean veiculoExiste = false;
        for (Veiculo veic : owner.getCtrlVeiculo().getVeiculos()) {
            if (veic.getPlaca().equals(placa)) {

                veiculoExiste = true;

            }

        }
        return veiculoExiste;
    }

    public boolean verfificaSeFuncionarioTemAcessoAoVeiculo(String placa, Funcionario funcionario) {
        boolean funcionarioTemAcesso = false;
        if (funcionario.getVeiculo(placa) != null) {
            funcionarioTemAcesso = true;
        }
        return funcionarioTemAcesso;
    }

    public boolean emiteBloqueio(Funcionario f, String placa) {
        boolean bloqueado = false;
        if (contador >= 3) {
            f.setBloqueado(true);
            bloqueado = true;
            Evento evento = new AcessoNegado(ACESSOBLOQUEADO, gc.getTime(), f.getNumeroDeMatricula(), placa);
            clavicularioDAO.put(evento);
            tabelaEventosJFrame.atualizaDados();
            JOptionPane.showMessageDialog(null, evento.getDescricao().detalhes);
            contador = 0;
        }
        return bloqueado;
    }

    public Evento getEventoPelaDescricao(DescricaoEvento descricao) {
        boolean possuiEvento = false;
        for (int e = 0; e < clavicularioDAO.getList().size(); e++) {
            Evento evento = clavicularioDAO.get(e);
            tabelaEventosJFrame.atualizaDados();

            if (evento.getDescricao().equals(descricao)) {
                JOptionPane.showMessageDialog(null, evento.getDescricao().detalhes);
                possuiEvento = true;
            }
        }
        if (possuiEvento == false) {
            JOptionPane.showMessageDialog(null, "Esse evento ainda não ocorreu");
        }

        return null;
    }

    public void getEventoPelaMatricula(int matricula) {
        boolean possuiEvento = false;
        for (int e = 0; e < clavicularioDAO.getList().size(); e++) {
            Evento evento = clavicularioDAO.get(e);
            tabelaEventosJFrame.atualizaDados();

            if (evento.getMatricula() == matricula) {

                JOptionPane.showMessageDialog(null, evento.getDescricao().detalhes);
                possuiEvento = true;
            }
        }
        if (possuiEvento == false) {
            JOptionPane.showMessageDialog(null, "Esse evento não aconteceu com essa Matricula ainda");
        }
    }

    public void getEventoPelaPlaca(String placa) {
        boolean possuiEvento = false;
        for (int e = 0; e < clavicularioDAO.getList().size(); e++) {
            Evento evento = clavicularioDAO.get(e);
            tabelaEventosJFrame.atualizaDados();

            if (evento.getPlaca().equals(placa)) {
                JOptionPane.showMessageDialog(null, evento.getDescricao());
                possuiEvento = true;
            }

        }
        if (possuiEvento == false) {
            JOptionPane.showMessageDialog(null, "Esse evento não aconteceu com essa placa ainda");
        }

    }

    public boolean validaEvento(Evento evento) {
        boolean eventoExiste = false;

        if (evento != null) {
            for (Evento e : clavicularioDAO.getList()) {
                if (clavicularioDAO.getList().contains(e)) {

                    eventoExiste = true;

                }
            }
        }
        return eventoExiste;
    }

    public void getTelaPegaFuncionarioJFrame() {
        telaPegaFuncionarioJFrame.setVisible(true);
    }

    public void getTelaRetirarChaveJFrame(Funcionario funcionario) {

        if (funcionario != null) {
            funcionario.getVeiculoDAO().gravaDados();
            
            telaRetirarChaveJFrame.setFuncionario(funcionario);
            telaRetirarChaveJFrame.atualizaDados();
            telaRetirarChaveJFrame.setVisible(true);
        } else {
            Evento e2 = new AcessoNegado(MATRICULAINEXISTENTE, gc.getTime(), Integer.parseInt(telaPegaFuncionarioJFrame.getTfMatricula()), null);
            JOptionPane.showMessageDialog(null, e2.getDescricao().detalhes);
            clavicularioDAO.put(e2);
            tabelaEventosJFrame.atualizaDados();
        }
    }

    public void getTelaDevolverChaveJFrame(Funcionario funcionario) {
        telaDevolverChaveJFrame.setFuncionario(funcionario);
        telaDevolverChaveJFrame.setVisible(true);
    }

    public void getTelaPegaFuncionarioDevolveVeiculoJFrame() {
        telaPegaFuncionarioDevolveVeiculoJFrame.setVisible(true);
    }

    public void getTabelaEventosJFrame() {
        tabelaEventosJFrame.atualizaDados();
        tabelaEventosJFrame.setVisible(true);
    }

    public void getTelaClavicularioJFrame() {
        owner.getCtrlFuncionario().atualizaDados();
        telaClavicularioJFrame.setVisible(true);
    }

    public Funcionario getFuncionario(int matricula) {
        Funcionario funcionario = null;
        for (Funcionario func : funcionarios) {
            if (func.getNumeroDeMatricula() == matricula) {
                funcionario = func;
            }
        }
        return funcionario;
    }

    public void getTelaPesquisaEvento() {
        this.telaPesquisaEvento.setVisible(true);
    }

    public void tabelaEventoPesquisado(DescricaoEvento descricaoEvento) {
        tabelaPesquisaEvento.atualizaDados();
        tabelaPesquisaEvento.setDescricaoEvento(descricaoEvento);
        tabelaPesquisaEvento.setVisible(true);
    }

    public ArrayList<Evento> getEventoPeloTipo(DescricaoEvento descricao) {
        return this.clavicularioDAO.getListaDeEventoPeloTipo(descricao);
    }

}
