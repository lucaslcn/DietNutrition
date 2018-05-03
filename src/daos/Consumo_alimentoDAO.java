package daos;
import entidades.*;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
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
    
    
    @Override
    public String salvar(consumo_alimento o) {
         try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "INSERT INTO alimento VALUES ("
                    + "DEFAULT, "
                    + "'" + o.getNome_alimento() + "', "
                    + "'" + o.getCarboidratos_por_porcao() + "',"
                    + "true)";
                    

            System.out.println("sql: " + sql);

            int resultado = st.executeUpdate(sql);

            return null;

        } catch (Exception e) {
            System.out.println("Erro salvar alimento = " + e);
            return e.toString();
        }
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
    
}
