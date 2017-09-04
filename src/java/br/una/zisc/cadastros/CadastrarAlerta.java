/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.una.zisc.cadastros;

import br.una.zisc.consultas.Consultas;
import br.una.zisc.dao.Alerta;
import br.una.zisc.dao.Usuario;
import com.google.gson.Gson;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author mikef
 */
@Path("/CadastrarAlerta")
public class CadastrarAlerta {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String alerta(@FormParam("email") String email,
            @FormParam("logHora") Timestamp logHora,
            @FormParam("longitude") String longitude,
            @FormParam("latitude") String latitude,
            @FormParam("bairro") String bairro,
            @FormParam("cidade") String cidade,
            @FormParam("estado") String estado,
            @FormParam("obs") String obs,
            @FormParam("tipo") String tipo,
            @FormParam("ePositivo") Boolean ePositivo) {

        try {
            Consultas con = new Consultas();
            Usuario usuario = con.buscaUsuario(email);
            System.err.println(String.valueOf(logHora));
            Alerta alerta = new Alerta(usuario, logHora, longitude, latitude, bairro, cidade, estado, obs, tipo, ePositivo, true);
            System.err.println(alerta.toString());
            Cadastros cad = new Cadastros();
            cad.cadastraralerta(usuario, alerta);
        } catch (ArrayIndexOutOfBoundsException e) {
            Gson gson = new Gson();
            String json = gson.toJson("NAO");
            return json;
        }
        Gson gson = new Gson();
        String json = gson.toJson("OK");
        return json;
    }
}
