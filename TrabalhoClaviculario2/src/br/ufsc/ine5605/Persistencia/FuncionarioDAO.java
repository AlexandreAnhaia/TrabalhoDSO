/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.Persistencia;

import br.ufsc.ine5605.Entidades.Funcionario;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.HashMap;

/**
 *
 * @author 09285195970
 */
//DAO = Data Acsess Object
public class FuncionarioDAO {

    private HashMap<Integer, Funcionario> cacheFuncionario;
    private String fileName = "funcionarios.cla";

    public FuncionarioDAO() {
        this.cacheFuncionario = new HashMap<>();
        load();
    }

    public void put(Funcionario funcionario) {

        if (funcionario != null) {
            cacheFuncionario.put(funcionario.getNumeroDeMatricula(), funcionario);
            persist();
        }
    }

    private void persist() {
        try {
            FileOutputStream fout = new FileOutputStream(fileName);
            ObjectOutputStream oo = new ObjectOutputStream(fout);

            oo.writeObject(cacheFuncionario);

            oo.flush();
            fout.flush();

            oo.close();
            fout.close();

        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    private void load() {
        try {
            FileInputStream fin = new FileInputStream(fileName);
            ObjectInputStream oi = new ObjectInputStream(fin);

            this.cacheFuncionario = (HashMap<Integer, Funcionario>) oi.readObject();

            oi.close();
            fin.close();

        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
    public Funcionario get(Integer numeroMatricula) {
        return this.cacheFuncionario.get(numeroMatricula);
    }

    public void remove(Funcionario funcinario) {
        cacheFuncionario.remove(funcinario.getNumeroDeMatricula());
        persist();
    }

    public Collection<Funcionario> getFuncionarios() {
        return cacheFuncionario.values();
    }

    public void gravaDados() {
        persist();
    }
}
