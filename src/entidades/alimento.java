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
    
    private int id;
    private String nome_alimento;
    private double proteinas_por_porcao;
    private double carboidratos_por_porcao;
    private double gorduras_por_porcao;
    private double kcal_por_porcao;
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

    public String getNome_alimento() {
        return nome_alimento;
    }

    public void setNome_alimento(String nome_alimento) {
        this.nome_alimento = nome_alimento;
    }

    public double getProteinas_por_porcao() {
        return proteinas_por_porcao;
    }

    public void setProteinas_por_porcao(double proteinas_por_porcao) {
        this.proteinas_por_porcao = proteinas_por_porcao;
    }

    public double getCarboidratos_por_porcao() {
        return carboidratos_por_porcao;
    }

    public void setCarboidratos_por_porcao(double carboidratos_por_porcao) {
        this.carboidratos_por_porcao = carboidratos_por_porcao;
    }

    public double getGorduras_por_porcao() {
        return gorduras_por_porcao;
    }

    public void setGorduras_por_porcao(double gorduras_por_porcao) {
        this.gorduras_por_porcao = gorduras_por_porcao;
    }

    public double getKcal_por_porcao() {
        return kcal_por_porcao;
    }

    public void setKcal_por_porcao(double kcal_por_porcao) {
        this.kcal_por_porcao = kcal_por_porcao;
    }

   
    
}
