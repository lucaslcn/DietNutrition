/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;
import java.util.Date;

/**
 *
 * @author Lucas
 */
public class Usuario {
    
    private int id;
    private String nome;
    private String email;
    private int altura_cm;
    private char sexo;
    private String data_nascimento;

    public int getId() {
        return id;
    }

    public String getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(String data_nascimento) {
        this.data_nascimento = data_nascimento;
    }


    

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAltura_cm() {
        return altura_cm;
    }

    public void setAltura_cm(int altura_cm) {
        this.altura_cm = altura_cm;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }


    

    
}
