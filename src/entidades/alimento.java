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
public class alimento {
    
    private int ind;
    private String nome_alimento;
    private float proteinas_por_porcao;
    private float carboidratos_por_porcao;
    private float gorduras_por_porcao;
    private float kcal_por_porcao;

    public int getInd() {
        return ind;
    }

    public void setInd(int ind) {
        this.ind = ind;
    }

    public String getNome_alimento() {
        return nome_alimento;
    }

    public void setNome_alimento(String nome_alimento) {
        this.nome_alimento = nome_alimento;
    }

    public float getProteinas_por_porcao() {
        return proteinas_por_porcao;
    }

    public void setProteinas_por_porcao(float proteinas_por_porcao) {
        this.proteinas_por_porcao = proteinas_por_porcao;
    }

    public float getCarboidratos_por_porcao() {
        return carboidratos_por_porcao;
    }

    public void setCarboidratos_por_porcao(float carboidratos_por_porcao) {
        this.carboidratos_por_porcao = carboidratos_por_porcao;
    }

    public float getGorduras_por_porcao() {
        return gorduras_por_porcao;
    }

    public void setGorduras_por_porcao(float gorduras_por_porcao) {
        this.gorduras_por_porcao = gorduras_por_porcao;
    }

    public float getKcal_por_porcao() {
        return kcal_por_porcao;
    }

    public void setKcal_por_porcao(float kcal_por_porcao) {
        this.kcal_por_porcao = kcal_por_porcao;
    }
    
    
    
}
