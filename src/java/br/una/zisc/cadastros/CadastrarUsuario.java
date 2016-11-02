/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.una.zisc.cadastros;

import br.una.zisc.consultas.Consultas;
import br.una.zisc.dao.Seguranca;
import br.una.zisc.dao.Usuario;
import com.google.gson.Gson;
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
@Path("/cadastrarusuario")
public class CadastrarUsuario {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String usuario(@FormParam("nome") String nome,
            @FormParam("email") String email,
            @FormParam("senha") String senha) {
        Gson gson = new Gson();
        try {
            Consultas con = new Consultas();
            if (con.buscaUsuario(email).getEmail().equals(email)) {
                System.err.println("USUARIO_CADASTRADO");
                System.err.println("USUARIO_CADASTRADO");
                System.err.println("USUARIO_CADASTRADO");
                System.err.println("USUARIO_CADASTRADO");
                System.err.println("USUARIO_CADASTRADO");
                System.err.println("USUARIO_CADASTRADO");
                String json = gson.toJson("USUARIO_CADASTRADO");
                return json;
            } else {
                Usuario usuario = new Usuario(nome, email);
                Cadastros cad = new Cadastros();
                cad.cadastrarUsuario(usuario);
                Seguranca seguranca = new Seguranca(usuario, senha);
                cad.cadastrarSeguranca(seguranca);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("ERRO");
            System.err.println("ERRO");
            System.err.println("ERRO");
            System.err.println("ERRO");
            System.err.println("ERRO");
            System.err.println("ERRO");
            System.err.println("ERRO");

            String json = gson.toJson("ERRO");
            return json;
        }
        System.err.println("OK");
        System.err.println("OK");
        System.err.println("OK");
        System.err.println("OK");
        System.err.println("OK");
        System.err.println("OK");

        String json = gson.toJson("OK");
        return json;
    }
}
