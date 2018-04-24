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
public class resumo_dia {
    
    private int id;
    private float saldoKcal;
    private LocalDate data;
    private int usuario_id;

    public int getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getSaldoKcal() {
        return saldoKcal;
    }

    public void setSaldoKcal(float saldoKcal) {
        this.saldoKcal = saldoKcal;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
}