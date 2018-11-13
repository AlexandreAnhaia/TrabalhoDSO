/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.TelasJFrame.TelasFuncionario;

import br.ufsc.ine5605.Controladores.ControladorFuncionario;
import br.ufsc.ine5605.Entidades.CargoFuncionario;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author alexa
 */
public class TelaCadastroFuncionarioJFrame extends JFrame {

    private ControladorFuncionario owner;
    private JLabel lbNome;
    private JTextField tfNome;
    private JLabel lbMatricula;
    private JTextField tfMatricula;
    private JLabel lbTelefone;
    private JTextField tfTelefone;
    private JLabel lbDataNascimento;
    private JTextField tfDataNascimento;
    private GerenciadorBotoes btManager;
    private JLabel lbCargo;
    private JButton btCadastrar;
    private JButton btCancelar;
    private JComboBox cargos;
    private static final String adiciona = "adiciona";
    private static final String cancelar = "cancelar";
    

    public TelaCadastroFuncionarioJFrame(ControladorFuncionario owner) {
        this.owner = owner;
        this.btManager = new GerenciadorBotoes();
        inicializa();
    }

    public void inicializa() {

        Container container = getContentPane();

        container.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        lbNome = new JLabel();
        lbNome.setText("Nome: ");
        constraints.gridx = 0;
        constraints.gridy = 0;
        container.add(lbNome, constraints);

        tfNome = new JTextField();
        tfNome.setText("");
        constraints.gridx = 1;
        constraints.gridy = 0;
        tfNome.setPreferredSize(new Dimension(100, 20));
        container.add(tfNome, constraints);

        lbMatricula = new JLabel();
        lbMatricula.setText("Matricula: ");
        constraints.gridx = 0;
        constraints.gridy = 1;
        container.add(lbMatricula, constraints);

        tfMatricula = new JTextField();
        tfMatricula.setText("");
        constraints.gridx = 1;
        constraints.gridy = 1;
        tfMatricula.setPreferredSize(new Dimension(100, 20));
        container.add(tfMatricula, constraints);

        lbTelefone = new JLabel();
        lbTelefone.setText("Telefone: ");
        constraints.gridx = 0;
        constraints.gridy = 2;
        container.add(lbTelefone, constraints);

        tfTelefone = new JTextField();
        tfTelefone.setText("");
        constraints.gridx = 1;
        constraints.gridy = 2;
        tfTelefone.setPreferredSize(new Dimension(100, 20));
        container.add(tfTelefone, constraints);

        lbDataNascimento = new JLabel();
        lbDataNascimento.setText("Data de Nascimento: ");
        constraints.gridx = 0;
        constraints.gridy = 3;
        container.add(lbDataNascimento, constraints);

        tfDataNascimento = new JTextField();
        tfDataNascimento.setText("");
        constraints.gridx = 1;
        constraints.gridy = 3;
        tfDataNascimento.setPreferredSize(new Dimension(100, 20));
        container.add(tfDataNascimento, constraints);
        
        lbCargo = new JLabel();
        lbCargo.setText("Cargo: ");
        constraints.gridx = 0;
        constraints.gridy = 4;
        container.add(lbCargo, constraints);
        
        cargos = new JComboBox(CargoFuncionario.values());
        constraints.gridx = 1;
        constraints.gridy = 4;
        container.add(cargos, constraints);

        btCadastrar = new JButton();
        btCadastrar.setActionCommand(adiciona);
        btCadastrar.addActionListener(btManager);
        btCadastrar.setText("Cadastrar");
        constraints.gridx = 0;
        constraints.gridy = 5;
        container.add(btCadastrar, constraints);

        btCancelar = new JButton();
        btCancelar.setActionCommand(cancelar);
        btCancelar.addActionListener(btManager);
        btCancelar.setText("Cancelar");
        constraints.gridx = 1;
        constraints.gridy = 5;
        container.add(btCancelar, constraints);

        

        setSize(800, 400);
        setLocationRelativeTo(null);

    }

    private class GerenciadorBotoes implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals(adiciona)) {
                try {
                    CargoFuncionario cargo = (CargoFuncionario)cargos.getSelectedItem();
                    owner.cadastraFuncionario(getTfMatricula(), getTfNome(), getTfDataNascimento(), getTfTelefone(), cargo);

                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null, "Formato de Data incorreto");
                }catch(NumberFormatException erro){
                    JOptionPane.showMessageDialog(null, "A Matricula Deve Conter Apenas Numeros(Inteiros)");
                }

            }else if(e.getActionCommand().equals(cancelar)){
                setVisible(false);
            } 
        }

    }

    public String getTfNome() {
        return tfNome.getText();
    }

    public int getTfMatricula() {
        return Integer.parseInt(tfMatricula.getText());
    }

    public String getTfTelefone() {
        return tfTelefone.getText();
    }

    public Date getTfDataNascimento() throws ParseException {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date dt = df.parse(tfDataNascimento.getText());
        return dt;
    }

}
