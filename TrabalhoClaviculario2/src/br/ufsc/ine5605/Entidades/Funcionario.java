/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.Entidades;

import br.ufsc.ine5605.Persistencia.VeiculoFuncionarioDAO;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Date;

import java.util.List;

/**
 *
 * @author Pichau
 */
public class Funcionario implements Serializable {

    private int numeroDeMatricula;
    private String nome;
    private Date dataDeNascimento;
    private String telefone;
    private CargoFuncionario cargo;
    private VeiculoFuncionarioDAO veiculoFuncionarioDAO;
    private Veiculo VeiculoAtual;
    private boolean bloqueado = false;

    public Funcionario(int numeroDeMatricula, String nome, Date dataDeNascimento, String telefone, CargoFuncionario cargo) {
        this.numeroDeMatricula = numeroDeMatricula;
        this.nome = nome;
        this.dataDeNascimento = dataDeNascimento;
        this.telefone = telefone;
        this.cargo = cargo;
        this.veiculoFuncionarioDAO = new VeiculoFuncionarioDAO();
        veiculoFuncionarioDAO.gravaDados();
    }

    public Funcionario() {
        this.veiculoFuncionarioDAO = new VeiculoFuncionarioDAO();
        veiculoFuncionarioDAO.gravaDados();
    }

    public int getNumeroDeMatricula() {
        return numeroDeMatricula;
    }

    public String getNome() {
        return nome;
    }

    public Date getDataDeNascimento() {
        return dataDeNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public CargoFuncionario getCargo() {
        return cargo;
    }

    public ArrayList<Veiculo> getVeiculos() {
        return new ArrayList(veiculoFuncionarioDAO.getVeiculos());
    }

    public void setNumeroDeMatricula(int numeroDeMatricula) {
        this.numeroDeMatricula = numeroDeMatricula;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDataDeNascimento(Date dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setCargo(CargoFuncionario cargo) {
        this.cargo = cargo;
    }

    public void setVeiculosAcessiveis(ArrayList<Veiculo> veiculosAcessiveis) {

        veiculoFuncionarioDAO.getVeiculos().clear();
        for (Veiculo veic : veiculosAcessiveis) {
            veiculoFuncionarioDAO.put(veic);
            veiculoFuncionarioDAO.gravaDados();
        }

    }

    @Override
    public String toString() {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        String sDate = sdf.format(this.dataDeNascimento);
        return "Numero de Matricula:\n" + this.numeroDeMatricula + "\nNome:\n" + this.nome + "\nData de Nascimento:\n" + sDate + "\nTelefone:\n" + this.telefone + "\nCargo:\n" + this.cargo + "\n--------------------";
    }

    public void addVeiculo(Veiculo veiculo) {
        veiculoFuncionarioDAO.put(veiculo);
        veiculoFuncionarioDAO.gravaDados();
    }

    public void removeVeiculo(Veiculo veiculo) {
        veiculoFuncionarioDAO.remove(veiculo);
        veiculoFuncionarioDAO.gravaDados();
    }

    public Veiculo getVeiculoAtual() {
        return VeiculoAtual;
    }

    public void setVeiculoAtual(Veiculo VeiculoAtual) {
        this.VeiculoAtual = VeiculoAtual;
    }

    public Veiculo getVeiculo(String placa) {
        List<Veiculo> listaVeiculos = getVeiculos();
        for (int a = 0; a < listaVeiculos.size(); a++) {
            Veiculo veiculo = listaVeiculos.get(a);
            if (veiculo.getPlaca().equals(placa)) {
                return veiculo;
            }
        }
        return null;
    }

    public boolean isBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(boolean bloqueado) {
        this.bloqueado = bloqueado;
    }

    public VeiculoFuncionarioDAO getVeiculoDAO() {
        return this.veiculoFuncionarioDAO;
    }

}
