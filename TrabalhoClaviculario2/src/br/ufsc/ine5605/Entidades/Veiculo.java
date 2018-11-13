/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.Entidades;

import java.io.Serializable;

/**
 *
 * @author Pichau
 */
public class Veiculo implements Serializable {

    private String placa;
    private String modelo;
    private String marca;
    private int ano;
    private double quilometragemAtual;
    private boolean emprestado = false;

    public Veiculo(String placa, String modelo, String marca, int ano, double quilometragemAtual) {
        this.placa = placa;
        this.modelo = modelo;
        this.marca = marca;
        this.ano = ano;
        this.quilometragemAtual = quilometragemAtual;
    }


    public String getPlaca() {
        return placa;
    }

    public String getModelo() {
        return modelo;
    }

    public String getMarca() {
        return marca;
    }

    public int getAno() {
        return ano;
    }

    public double getQuilometragemAtual() {
        return quilometragemAtual;
    }

    public boolean getEmprestado() {
        return emprestado;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public void setQuilometragemAtual(double quilometragemAtual) {
         this.quilometragemAtual = quilometragemAtual;
    }

    public void setEmprestado(boolean emprestado) {
         this.emprestado = emprestado;
    }

    @Override
    public String toString() {
        return "placa: " + this.placa + " modelo: " + this.modelo + " marca: " + this.marca + " ano: " + this.ano + " quilometragemAtual: " + this.quilometragemAtual;
    }


}


