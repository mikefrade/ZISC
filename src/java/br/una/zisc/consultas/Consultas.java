/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.una.zisc.consultas;

import br.una.zisc.dao.*;
import br.una.zisc.hibernate.HibernateUtil;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.annotations.common.util.impl.Log;

/**
 *
 * @author mikef
 */
public class Consultas {
    
    @SuppressWarnings("unchecked")
    public Usuario buscaUsuario(String email) {
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        
        Query q = s.createQuery("from Usuario u where u.email = :email");
        q.setParameter("email", email);
        List<Usuario> lista = (List<Usuario>) q.list();
        System.err.println("Acessando banco!");
        Usuario usuario = lista.get(0);
        s.getTransaction().commit();
        System.err.println("Commit!");
        return usuario;
    }
    
    @SuppressWarnings("unchecked")
    public Usuario requisicaoLogin(String email, String senha) {
        
        Usuario user = new Usuario();
        
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        
        Query q = s.createQuery("from Seguranca seg where seg.usuario.email = :email");
        q.setParameter("email", email);
        
        List<Seguranca> lista = (List<Seguranca>) q.list();
        
        if (lista.get(0).getHash().equals(senha)) {
            s.getTransaction().commit();
            return buscaUsuario(email);
        }
        
        return user;
    }
    
    @SuppressWarnings("unchecked")
    public List<Alerta> buscaAlerta(String bairro, String cidade, String estado) {
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        
        Query q = s.createQuery("from Alerta alerta where alerta.bairro = :bairro and alerta.cidade = :cidade and alerta.estado = :estado");
        q.setParameter("bairro", bairro);
        q.setParameter("cidade", cidade);
        q.setParameter("estado", estado);
        
        List<Alerta> lista = (List<Alerta>) q.list();
        List<Alerta> lista1 = new ArrayList<>();
        System.err.println("lista do tamanho " + lista.size());
        
        for (int i = 0; i < lista.size(); i++) {
            Alerta alerta = lista.get(i);
            Alerta alerta1 = new Alerta();
            
            alerta1.setIdalerta(lista.get(i).getIdalerta());
            alerta1.setLoghora(lista.get(i).getLoghora());
            alerta1.setLatitude(lista.get(i).getLatitude());
            alerta1.setLatitude(lista.get(i).getLatitude());
            alerta1.setBairro(lista.get(i).getBairro());
            alerta1.setCidade(lista.get(i).getCidade());
            alerta1.setEstado(lista.get(i).getEstado());
            alerta1.setObservacao(lista.get(i).getObservacao());
            alerta1.setTipo(lista.get(i).getTipo());
            alerta1.setStatusAtivo(lista.get(i).isStatusAtivo());
            
            lista1.add(alerta1);
        }
        
        return lista1;
    }
    
    @SuppressWarnings("unchecked")
    public String usuarioValido() {
        
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        String resultado = "false";
        
        List<Usuario> lista = (List<Usuario>) s.createQuery("from Usuario u where u.email ='mariaap@gmail.com'").list();
        s.getTransaction().commit();
        
        if (!lista.isEmpty()) {
            resultado = "true";
        }
        
        return resultado;
    }
}
