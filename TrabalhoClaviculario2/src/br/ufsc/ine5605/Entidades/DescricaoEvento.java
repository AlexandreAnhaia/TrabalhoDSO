/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.Entidades;

/**
 *
 * @author Pichau
 */
public enum DescricaoEvento {
    
    MATRICULAINEXISTENTE("Matricula Inexistente"),
    ACESSOPERMITIDOAOVEICULO("Acesso Permtio ao Veiculo"),
    NAOPOSSUIACESSOAOVEICULO("Você não possui acesso ao veiculo"),
    VEICULOINDISPONIVEL("Veiculo indisponivel no momento"),
    ACESSOBLOQUEADO("Acesso Bloqueado"),
    VEICULODEVOLVIDO("Veiculo Devolvido com Sucesso");
    
    public String detalhes;
    
    DescricaoEvento(String detalhesP){
        detalhes = detalhesP;
        
    }
    
}
