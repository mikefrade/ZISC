/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.una.zisc.dao_;

import java.io.Serializable;

/**
 *
 * @author Marcos Benevides
 */
public class Usuario_ implements Serializable{

    Integer idusuario;
    String nome;
    String email;

    public Usuario_(Integer idusuario, String nome, String email) {
        this.idusuario = idusuario;
        this.nome = nome;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Usuario_{" + "idusuario=" + idusuario + ", nome=" + nome + ", email=" + email + '}';
    }

    public Integer getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Integer idusuario) {
        this.idusuario = idusuario;
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
    
    
}
