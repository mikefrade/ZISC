/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.una.zisc.consultas;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Marcos Benevides
 */
@Path("/evalido")
public class ConsultaUsuarioValido {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String eValido(){
        Consultas con = new Consultas();
        return con.usuarioValido();
    }

}
