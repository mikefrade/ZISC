/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testehoradata;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Antônio
 */
public class TesteHoraData {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Date data = new Date();
        
        Calendar hora = Calendar.getInstance();
        
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String horario = "" + hora.get(Calendar.YEAR) + "-" + formato.format((hora.get(Calendar.MONTH) + 1))
                + "-" + formato.format(hora.get(Calendar.DAY_OF_MONTH)) + " "
                + formato.format(hora.get(Calendar.HOUR_OF_DAY)) + ":" + formato.format(hora.get(Calendar.MINUTE)) +
                ":" + formato.format(hora.get(Calendar.SECOND));
                
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        
        

        
        System.err.println("Data: " + String.valueOf(formato.format(data)));
    }
}
