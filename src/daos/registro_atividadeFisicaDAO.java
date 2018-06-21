/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import entidades.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import support.ConexaoBD;
import support.IDAO_T;

public class registro_atividadeFisicaDAO implements IDAO_T<registro_atividadeFisica> {
    
    ResultSet ResultadoQ = null;
    
    public String salvar(registro_atividadeFisica o, int resumo_id) throws SQLException {
        try {

            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            resumo_diaDAO resumo_diaDAO = new resumo_diaDAO();

            String sql = "INSERT INTO registro_atividadefisica VALUES ("
                    + "'" + o.getId() + "', "
                    + "'"+ o.getDuracao() + "', "
                    + "'" + resumo_id + "', "
                    + "true)";

            System.out.println("sql: " + sql);

            int resultado = st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Atividade física registrada com sucesso!");

            return null;

        } catch (Exception e) {
            System.out.println(e);
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            resumo_diaDAO resumo_diaDAO = new resumo_diaDAO();
            String sql = "UPDATE registro_atividadefisica SET duracao = duracao+" + o.getDuracao()
                    + " WHERE dieta_id = " + resumo_id + " AND atividadefisica_idatividadefisica = " + o.getId();

            
            System.out.println("sql: " + sql);
            int resultado = st.executeUpdate(sql);
            
            String sql2 = "SELECT raf.duracao AS duracao FROM registro_atividadefisica raf, resumo_dia r, atividadefisica af "
                    + " WHERE raf.dieta_id = " + resumo_id + " AND raf.atividadefisica_idatividadefisica = "
                    +  + o.getId();

            ResultadoQ = st.executeQuery(sql2);
            double duracao = o.getDuracao();
            if (ResultadoQ.next()) {
                duracao = ResultadoQ.getFloat("duracao");
            }

            JOptionPane.showMessageDialog(null, "A atividade física já estava registrada no dia de hoje! Então, a duração será atualizada para "+ duracao +" minutos.");


            return e.toString();
        }
    }

    @Override
    public String atualizar(registro_atividadeFisica o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String excluir(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<registro_atividadeFisica> consultarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<registro_atividadeFisica> consultar(String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public registro_atividadeFisica consultarId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String salvar(registro_atividadeFisica o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
