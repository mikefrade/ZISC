/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.una.zisc.consultas;

import br.una.zisc.dao.*;
import br.una.zisc.hibernate.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

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
        try {
            Usuario usuario = lista.get(0);
            s.getTransaction().commit();
            System.err.println("Commit!");
            return usuario;
        } catch (IndexOutOfBoundsException e) {
            s.getTransaction().commit();
            Usuario u = null;
            return u;
        }
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
    public List<Alerta> buscaAlerta(String latitude, String longitude) {
        String latitudec, latitudeAnt, latitudeDep, longitudec, longitudeAnt, longitudeDep;
        Double auxlat, auxlong;
        double validador = Double.parseDouble(latitude);
        if (validador < 0) {
            latitudec = latitude.substring(0, 6);
            System.err.println("Imprimindo latitude: " + latitudec);
            auxlat = (Double.parseDouble(latitudec) + 0.01);
            latitudeAnt = String.valueOf(auxlat);
            System.err.println("Imprimindo latitudeAnt: " + latitudeAnt);
            auxlat = (Double.parseDouble(latitudec) - 0.01);
            latitudeDep = String.valueOf(auxlat);
            System.err.println("Imprimindo latitudeDep: " + latitudeDep);
        } else {
            latitudec = latitude.substring(0, 5);
            System.err.println("Imprimindo latitude: " + latitudec);
            auxlat = (Double.parseDouble(latitudec) - 0.01);
            latitudeAnt = String.valueOf(auxlat);
            System.err.println("Imprimindo latitudeAnt: " + latitudeAnt);
            auxlat = (Double.parseDouble(latitudec) + 0.01);
            latitudeDep = String.valueOf(auxlat);
            System.err.println("Imprimindo latitudeDep: " + latitudeDep);
        }
        double validador1 = Double.parseDouble(longitude);
        if (validador1 < 0) {
            longitudec = longitude.substring(0, 6);
            System.err.println("Imprimindo Longitude: " + longitudec);
            auxlong = (Double.parseDouble(longitudec) + 0.01);
            longitudeAnt = String.valueOf(auxlong);
            System.err.println("Imprimindo longitudeAnt: " + longitudeAnt);
            auxlong = (Double.parseDouble(longitudec) - 0.01);
            longitudeDep = String.valueOf(auxlong);
            System.err.println("Imprimindo longitudeDep: " + longitudeDep);
        } else {
            longitudec = longitude.substring(0, 5);
            System.err.println("Imprimindo Longitude: " + longitudec);
            auxlong = (Double.parseDouble(longitudec) - 0.01);
            longitudeAnt = String.valueOf(auxlong);
            System.err.println("Imprimindo longitudeAnt: " + longitudeAnt);
            auxlong = (Double.parseDouble(longitudec) + 0.01);
            longitudeDep = String.valueOf(auxlong);
            System.err.println("Imprimindo longitudeDep: " + longitudeDep);
        }
        List<Alerta> lista = (List<Alerta>) conAlerta(latitude, longitude);
        List<Alerta> listaAnt = (List<Alerta>) conAlerta(latitudeAnt, longitudeAnt);
        List<Alerta> listaDep = (List<Alerta>) conAlerta(latitudeDep, longitudeDep);
        List<Alerta> listatotal = new ArrayList<>();//concatenaLista(lista, listaAnt, listaDep);
        listatotal.addAll(lista);
        listatotal.addAll(listaAnt);
        listatotal.addAll(listaDep);
        List<Alerta> lista1 = new ArrayList<>();
        System.err.println("lista do tamanho " + listatotal.size());
        for (int i = 0; i < listatotal.size(); i++) {
            Alerta alerta = listatotal.get(i);
            Alerta alerta1 = new Alerta();
            alerta1.setIdalerta(listatotal.get(i).getIdalerta());
            alerta1.setLoghora(listatotal.get(i).getLoghora());
            alerta1.setLatitude(listatotal.get(i).getLatitude());
            alerta1.setLatitude(listatotal.get(i).getLatitude());
            alerta1.setBairro(listatotal.get(i).getBairro());
            System.out.println("Imprimindo bairro " + listatotal.get(i).getBairro());
            alerta1.setCidade(listatotal.get(i).getCidade());
            System.out.println("Imprimindo Cidade " + listatotal.get(i).getCidade());
            alerta1.setEstado(listatotal.get(i).getEstado());
            alerta1.setObservacao(listatotal.get(i).getObservacao());
            alerta1.setTipo(listatotal.get(i).getTipo());
            alerta1.setStatusAtivo(listatotal.get(i).isStatusAtivo());
            lista1.add(alerta1);
        }
        return lista1;
    }

    public List<Alerta> conAlerta(String latitude, String longitude) {
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        Query q = s.createQuery("from Alerta alerta where alerta.longitude like :longitude and alerta.latitude like :latitude");
        q.setParameter("latitude", latitude + "%");
        q.setParameter("longitude", longitude + "%");
        List<Alerta> lista = (List<Alerta>) q.list();
        s.getTransaction().commit();
        return lista;
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
