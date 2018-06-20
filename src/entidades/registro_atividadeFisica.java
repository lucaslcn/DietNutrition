/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.time.LocalDate;

/**
 *
 * @author Lucas
 */
public class registro_atividadeFisica {
    
    private int id;
    private int dieta_id;

    public int getDieta_id() {
        return dieta_id;
    }

    public void setDieta_id(int dieta_id) {
        this.dieta_id = dieta_id;
    }
    private double duracao;
    private boolean delete;

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getDuracao() {
        return duracao;
    }

    public void setDuracao(double duracao) {
        this.duracao = duracao;
    }


}
