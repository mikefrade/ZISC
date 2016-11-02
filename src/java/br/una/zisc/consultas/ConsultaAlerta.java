/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.una.zisc.consultas;

import br.una.zisc.dao.Alerta;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Marcos Benevides
 */
@Path("/consultaalerta/{bairro}/{cidade}/{estado}")
public class ConsultaAlerta {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public GenericEntity<List<Alerta>> getAlerta(
            @PathParam("bairro") String bairro,
            @PathParam("cidade") String cidade,
            @PathParam("estado") String estado) {

        Consultas con = new Consultas();
        Gson gson = new Gson();

        List<Alerta> lista = con.buscaAlerta(bairro, cidade, estado);
        
        GenericEntity<List<Alerta>> entidade = new GenericEntity<List<Alerta>>(lista){};
        
        return entidade;

    }
}
