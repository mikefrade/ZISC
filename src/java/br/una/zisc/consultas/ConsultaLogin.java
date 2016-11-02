/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.una.zisc.consultas;

import com.google.gson.Gson;
import br.una.zisc.dao.*;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Marcos Benevides
 */
@Path("/login/{email}/{senha}")
public class ConsultaLogin {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String login(@PathParam("email") String email,
            @PathParam("senha") String senha) {
        System.err.println(email + " " + senha);
        Consultas con = new Consultas();
        Gson gson = new Gson();
        Usuario usuario = new Usuario(
                con.requisicaoLogin(email, senha).getIdusuario(),
                con.requisicaoLogin(email, senha).getNome(),
                con.requisicaoLogin(email, senha).getEmail());
        String json = gson.toJson(usuario);
        return json;
    }

}
