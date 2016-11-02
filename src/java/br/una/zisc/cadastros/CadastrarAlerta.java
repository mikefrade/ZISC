/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.una.zisc.cadastros;

import br.una.zisc.consultas.Consultas;
import br.una.zisc.dao.Alerta;
import java.util.Date;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author mikef
 */
@Path("/cadastraralerta")
public class CadastrarAlerta {

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void alerta(@FormParam("email") String email,
                       @FormParam("logHora") Date logHora,
                       @FormParam("longitude") String longitude,
                       @FormParam("latitude") String latitude,
                       @FormParam("bairro") String bairro,
                       @FormParam("cidade") String cidade,
                       @FormParam("estado") String estado,
                       @FormParam("obs") String obs,
                       @FormParam("tipo") String tipo){
        
        Consultas con = new Consultas();        
        Alerta alerta = new Alerta(con.buscaUsuario(email), logHora, longitude, latitude, bairro, cidade, estado, obs, tipo, true);
        Cadastros cad = new Cadastros();
        cad.cadastraralerta(alerta);

    }
}
