/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.Persistencia;

import br.ufsc.ine5605.Entidades.DescricaoEvento;
import br.ufsc.ine5605.Entidades.Evento;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 *
 * @author Pichau
 */
public class ClavicularioDAO {

    private HashMap<Integer, Evento> cacheEventos;
    private ArrayList <Evento>  eventos;
    private String fileName = "eventos.queotaa";

    public ClavicularioDAO() {
        this.cacheEventos = new HashMap<>();
        this.eventos = new ArrayList<>();
        load();
    }

    public void put(Evento evento) {
        if (evento != null) {
            cacheEventos.put(evento.getMatricula(), evento);
            eventos.add(evento);
        }
        persist();
    }

    private void persist() {
        try {
            FileOutputStream fout = new FileOutputStream(fileName);

            ObjectOutputStream oo = new ObjectOutputStream(fout);
            oo.writeObject(cacheEventos);
            oo.writeObject(eventos);

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

            this.cacheEventos = (HashMap<Integer, Evento>) oi.readObject();
            this.eventos = (ArrayList<Evento>) oi.readObject();

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

    public Collection<Evento> getList() {
        return cacheEventos.values();
    }

    public Evento get(Integer matricula) {
        return cacheEventos.get(matricula);
    }

    public void remove(Evento evento) {
        cacheEventos.remove(evento.getMatricula());
        eventos.remove(evento);
        persist();
    }

    public ArrayList<Evento> getListaDeEventoPeloTipo(DescricaoEvento descricao) {

        ArrayList<Evento> eventos = new ArrayList<>();
        for (Evento e : this.eventos) {
            if (e.getDescricao().equals(descricao)) {
                eventos.add(e);
                persist();
            }

        }
        return eventos;
    }

    public void atualizDados() {
        persist();
    }
    
    
    public ArrayList<Evento> getEventos(){
        return this.eventos;
    }

}
