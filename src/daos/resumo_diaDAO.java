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
import static support.Formatacao.round;
import support.IDAO_T;

/**
 *
 * @author Lucas
 */
public class resumo_diaDAO implements IDAO_T<resumo_dia> {

    ResultSet resultadoQ = null;

    public double updateSaldoFinal(int id_usuario)
    {
        return (updateSaldoKcal(id_usuario) - updateSaldoAtividadeFisica(id_usuario));
    }
           
    
    public double updateSaldoKcal(int id_usuario) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT SUM(a.kcal_por_porcao*c.numero_porcoes) as saldoKcal,\n" +           
"                                SUM(a.carboidratos_por_porcao*c.numero_porcoes) as saldoCarb,\n" +
"                                SUM(a.proteinas_por_porcao*c.numero_porcoes) as saldoProt,\n" +
"                                SUM(a.gorduras_por_porcao*c.numero_porcoes) as saldoGorduras\n" +
"	   FROM alimento a, usuario u, consumo_alimento c, resumo_dia r WHERE r.usuario_id = " + id_usuario +
"	   AND r.data = current_date\n" +
"	   AND c.alimento_id = a.id" + 
"          AND r.id = c.dieta_id" +
"          AND r.usuario_id = u.id ";
                    

            System.out.println("sql: " + sql);

            resultadoQ = st.executeQuery(sql);

            if (resultadoQ.next()) {
                return resultadoQ.getDouble("saldoKcal");
            }
            return 0;
            
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }
    
    public double updateSaldoAtividadeFisica(int id_usuario) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

String sql = "SELECT SUM((f.kcal_por_hora/60)*rf.duracao) as saldoAtividadeFisica "+
	   "FROM usuario u, resumo_dia r, atividadefisica f, registro_atividadefisica rf, atividade_dieta ad  WHERE r.usuario_id = " + id_usuario + 
	   " AND r.data = current_date "+ 
          "AND f.idatividadefisica = rf.atividadefisica_idatividadefisica "+
          "AND rf.id = ad.registro_atividadefisica_id "+
          "AND ad.dieta_id = r.id " +
"          AND r.usuario_id = u.id ";

//            System.out.println("sql: " + sql);

            resultadoQ = st.executeQuery(sql);

            if (resultadoQ.next()) {
                return round(resultadoQ.getDouble("saldoAtividadeFisica"),2);
            }
            return 0;
            
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    public double updateSaldoCarb(int id_usuario) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT SUM(a.kcal_por_porcao*c.numero_porcoes) as saldoKcal,\n" +
"                                SUM(a.carboidratos_por_porcao*c.numero_porcoes) as saldoCarb,\n" +
"                                SUM(a.proteinas_por_porcao*c.numero_porcoes) as saldoProt,\n" +
"                                SUM(a.gorduras_por_porcao*c.numero_porcoes) as saldoGorduras\n" +
"	   FROM alimento a, usuario u, consumo_alimento c, resumo_dia r WHERE r.usuario_id = " + id_usuario +
"	   AND r.data = current_date\n" +
"	   AND c.alimento_id = a.id" + 
"          AND r.id = c.dieta_id" +
"          AND r.usuario_id = u.id ";

 //           System.out.println("sql: " + sql);

            resultadoQ = st.executeQuery(sql);

            if (resultadoQ.next()) {
                return resultadoQ.getDouble("saldoCarb");
            }
            return 0;
            
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }
    
    public double updateSaldoProt(int id_usuario) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT SUM(a.kcal_por_porcao*c.numero_porcoes) as saldoKcal,\n" +
"                                SUM(a.carboidratos_por_porcao*c.numero_porcoes) as saldoCarb,\n" +
"                                SUM(a.proteinas_por_porcao*c.numero_porcoes) as saldoProt,\n" +
"                                SUM(a.gorduras_por_porcao*c.numero_porcoes) as saldoGorduras\n" +
"	   FROM alimento a, usuario u, consumo_alimento c, resumo_dia r WHERE r.usuario_id = " + id_usuario +
"	   AND r.data = current_date\n" +
"	   AND c.alimento_id = a.id" + 
"          AND r.id = c.dieta_id" +
"          AND r.usuario_id = u.id ";

 //           System.out.println("sql: " + sql);

            resultadoQ = st.executeQuery(sql);

            if (resultadoQ.next()) {
                return resultadoQ.getDouble("saldoProt");
            }
            return 0;
            
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }
    
    public double updateSaldoGorduras(int id_usuario) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT SUM(a.kcal_por_porcao*c.numero_porcoes) as saldoKcal,\n" +
"                                SUM(a.carboidratos_por_porcao*c.numero_porcoes) as saldoCarb,\n" +
"                                SUM(a.proteinas_por_porcao*c.numero_porcoes) as saldoProt,\n" +
"                                SUM(a.gorduras_por_porcao*c.numero_porcoes) as saldoGorduras\n" +
"	   FROM alimento a, usuario u, consumo_alimento c, resumo_dia r WHERE r.usuario_id = " + id_usuario +
"	   AND r.data = current_date\n" +
"	   AND c.alimento_id = a.id" + 
"          AND r.id = c.dieta_id" +
"          AND r.usuario_id = u.id ";
                    

 //           System.out.println("sql: " + sql);

            resultadoQ = st.executeQuery(sql);

            if (resultadoQ.next()) {
                return resultadoQ.getDouble("saldoGorduras");
            }
            return 0;
            
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }
    
    public boolean verifyExistence(int id_usuario, LocalDate data) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT * FROM usuario u, resumo_dia r "
                    + "WHERE u.id = " + id_usuario + " AND r.data = current_date AND r.usuario_id = u.id";
                    

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
                    + "'" + o.getSaldoKcal() + "', "
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
        return null;

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

            String sql = "SELECT r.id AS resumo_id, u.id AS user_id, r.saldokcal, r.data FROM usuario u, resumo_dia r WHERE u.id = " + id + " AND r.data = current_date AND r.usuario_id = u.id";

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
