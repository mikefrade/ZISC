/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.una.zisc.cadastros;

import br.una.zisc.dao.Alerta;
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

}
