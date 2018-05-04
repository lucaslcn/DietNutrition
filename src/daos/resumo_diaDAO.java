/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import entidades.Usuario;
import entidades.resumo_dia;
import support.ConexaoBD;
import support.IDAO_T;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import support.ConexaoBD;
import support.Formatacao;
import support.IDAO_T;

/**
 *
 * @author Lucas
 */
public class resumo_diaDAO implements IDAO_T<resumo_dia> {
    
    ResultSet resultadoQ = null;

    public boolean verifyExistence(int id_usuario, LocalDate data)
    {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT * FROM usuario u, resumo_dia r "
                    + "WHERE u.id = " + id_usuario + " AND r.data = current_date";

            System.out.println("sql: " + sql);

            resultadoQ = st.executeQuery(sql);

            if (resultadoQ.next()) {
                return true;
            }
            
            return false;

        } catch (Exception e) {
            System.out.println("Erro ao consultar existência de recordatório para o usuário na data = " + e);
        }
        return false;
    }
    
    @Override
    public String salvar(resumo_dia o) {
        
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "INSERT INTO resumo_dia VALUES ("
                    + "DEFAULT, "
                    + "'" + o.getSaldoKcal()+ "', "
                    + "'" + o.getUsuario_id() + "',"
                    + "'" + o.getData() + "')";

            System.out.println("sql: " + sql);

            int resultado = st.executeUpdate(sql);

            return null;

        } catch (Exception e) {
            System.out.println("Erro salvar resumo_dia = " + e);
            return e.toString();
        }
        
    }

    @Override
    public String atualizar(resumo_dia o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String excluir(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<resumo_dia> consultarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<resumo_dia> consultar(String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public resumo_dia consultarId(int id) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            
            String sql = "SELECT r.id AS resumo_id, u.id AS user_id, r.saldokcal FROM usuario u, resumo_dia r WHERE u.id = " + id +" AND r.data = current_date";

            System.out.println("sql: " + sql);

            resultadoQ = st.executeQuery(sql);

            if (resultadoQ.next()) {
                resumo_dia resumo_dia = new resumo_dia();
                resumo_dia.setId(resultadoQ.getInt("resumo_id"));
                resumo_dia.setUsuario_id(resultadoQ.getInt("user_id"));
                resumo_dia.setSaldoKcal(resultadoQ.getFloat("saldoKcal"));
                resumo_dia.setData(LocalDate.parse(resultadoQ.getString("data")));
                
                return resumo_dia;
            }

        } catch (Exception e) {
            System.out.println("Erro consultar resumo dia = " + e);
        }
        return null;
    
    }
    
    
    
}
