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
public class consumo_alimento {
    
    private int dieta_id;
    private int alimento_id;
    private float numero_porcoes;

    public int getDieta_id() {
        return dieta_id;
    }

    public void setDieta_id(int dieta_id) {
        this.dieta_id = dieta_id;
    }

    public int getAlimento_id() {
        return alimento_id;
    }

    public void setAlimento_id(int alimento_id) {
        this.alimento_id = alimento_id;
    }

    
    
    public int getId() {
        return alimento_id;
    }

    public void setId(int id) {
        this.alimento_id = id;
    }
    

    public float getNumero_porcoes() {
        return numero_porcoes;
    }

    public void setNumero_porcoes(float numero_porcoes) {
        this.numero_porcoes = numero_porcoes;
    }
    
}
