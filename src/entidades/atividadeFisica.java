/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

/**
 *
 * @author Lucas
 */
public class atividadeFisica {
    
    private int id;
    private String nome_atividade;
    private double kcal_por_hora;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome_atividade() {
        return nome_atividade;
    }

    public void setNome_atividade(String nome_atividade) {
        this.nome_atividade = nome_atividade;
    }

    public double getKcal_por_hora() {
        return kcal_por_hora;
    }

    public void setKcal_por_hora(double kcal_por_hora) {
        this.kcal_por_hora = kcal_por_hora;
    }
    
    
    
}
