package daos;
import entidades.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import support.ConexaoBD;
import support.IDAO_T;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Lucas
 */
public class Consumo_alimentoDAO implements IDAO_T<consumo_alimento> {

    ResultSet ResultadoQ = null;
    
    
    public String salvar(consumo_alimento o, int resumo_id) throws SQLException {
         try {
            
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            resumo_diaDAO resumo_diaDAO = new resumo_diaDAO();
            
            
            String sql = "INSERT INTO consumo_alimento VALUES ("
                    + "'" + resumo_id + "', "
                    + "'" + o.getAlimento_id()+ "', "
                    + "'" + o.getNumero_porcoes() + "',"
                    + "true)";
                    

            System.out.println("sql: " + sql);

            int resultado = st.executeUpdate(sql);

            return null;

        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"O alimento já havia sido consumido no dia de hoje! Então, o número de porções será atualizado ");
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            resumo_diaDAO resumo_diaDAO = new resumo_diaDAO();
            String sql = "UPDATE consumo_alimento SET numero_porcoes = numero_porcoes+" + o.getNumero_porcoes() +
                         " WHERE consumo_alimento.dieta_id = " + resumo_id + " AND consumo_alimento.alimento_id = " + o.getAlimento_id();
            
             System.out.println("sql: " + sql);
             int resultado = st.executeUpdate(sql);
            
            return e.toString();
        }
    }

    @Override
    public String atualizar(consumo_alimento o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String excluir(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<consumo_alimento> consultarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<consumo_alimento> consultar(String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public consumo_alimento consultarId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String salvar(consumo_alimento o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}