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
import org.hibernate.Session;

/**
 *
 * @author mikef
 */
public class Cadastros {

    @SuppressWarnings("unchecked")
    public void cadastraralerta(Alerta alerta) {
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        s.save(alerta);
        s.getTransaction().commit();
        // s.close();

    }

    @SuppressWarnings("unchecked")
    public void cadastrarUsuario(Usuario usuario) {
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        s.save(usuario);
        s.getTransaction().commit();

    }

    @SuppressWarnings("unchecked")
    public void cadastrarSeguranca(Seguranca seguranca) {
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        s.save(seguranca);
        s.getTransaction().commit();

    }

    @SuppressWarnings("unchecked")
    public void cadastrarEndereco(Endereco endereco) {
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        s.save(endereco);
        s.getTransaction().commit();

    }

}
