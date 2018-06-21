/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dietnutrition;
import support.ConexaoBD;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import telas.FrmPrincipal;
import telas.Login;

/**
 *
 * @author Lucas
 */
public class DietNutrition {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        if (ConexaoBD.getInstance().getConnection() != null) {
//            JOptionPane.showMessageDialog(null, "Abriu!");
            new Login().setVisible(true);
            
        } else {
            JOptionPane.showMessageDialog(null, "Deu problema!");
        }
    }
    
}
