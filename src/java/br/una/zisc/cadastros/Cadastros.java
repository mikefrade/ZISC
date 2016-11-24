/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.una.zisc.cadastros;

import br.una.zisc.dao.Alerta;
import br.una.zisc.dao.Endereco;
import br.una.zisc.dao.Seguranca;
import br.una.zisc.dao.Usuario;
import br.una.zisc.hibernate.HibernateUtil;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.Session;

/**
 *
 * @author mikef
 */
public class Cadastros {

    @SuppressWarnings("unchecked")
    public void cadastraralerta(Usuario usuario, Alerta alerta) {
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            s.beginTransaction();
            s.update(usuario);
            s.save(alerta);
            s.getTransaction().commit();
        } catch (RuntimeException e) {
            s.getTransaction().rollback();
            throw e;
        } finally {
            s.close();
        }
    }

    @SuppressWarnings("unchecked")
    public void cadastrarUsuario(Usuario usuario, String senha) {
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        Set<Seguranca> hsc = new HashSet<>();
        Seguranca s1 = new Seguranca(usuario, senha);
        hsc.add(s1);
        usuario.setSegurancas(hsc);
        s.beginTransaction();
        s.save(usuario);
        s.save(s1);
        s.getTransaction().commit();
        System.err.println("Usuario Cadastrado");
    }

    @SuppressWarnings("unchecked")
    public void cadastrarEndereco(Endereco endereco) {
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        s.save(endereco);
        s.getTransaction().commit();

    }

}
