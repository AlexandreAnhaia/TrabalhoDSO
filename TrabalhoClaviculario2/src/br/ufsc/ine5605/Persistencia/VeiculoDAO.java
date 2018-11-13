/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.Persistencia;

import br.ufsc.ine5605.Entidades.Veiculo;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;

/**
 *
 * @author alexa
 */
public class VeiculoDAO {

    private HashMap<String, Veiculo> cacheVeiculos;
    private String fileName = "veiculos.queota";

    public VeiculoDAO() {
        this.cacheVeiculos = new HashMap<>();
        load();
    }

    public void put(Veiculo veiculo) {
        if (veiculo != null) {
            cacheVeiculos.put(veiculo.getPlaca(), veiculo);
            persist();
        }
    }

    private void persist() {
        try {
            FileOutputStream fout = new FileOutputStream(fileName);
            ObjectOutputStream oo = new ObjectOutputStream(fout);

            oo.writeObject(cacheVeiculos);

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

            this.cacheVeiculos = (HashMap<String, Veiculo>) oi.readObject();

            fin.close();
            oi.close();
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public Collection<Veiculo> getVeiculos() {
        return cacheVeiculos.values();
    }

    public Veiculo get(String placa) {
        return cacheVeiculos.get(placa);
    }

    public void remove(Veiculo veiculo) {
        cacheVeiculos.remove(veiculo.getPlaca());
        persist();
    }

    public void gravaDados() {
        persist();
    }
}
