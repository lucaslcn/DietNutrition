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
        return round(((updateSaldoKcal(id_usuario) - updateSaldoAtividadeFisica(id_usuario))),2);
    }
           
    
    public double updateSaldoKcal(int id_usuario) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT SUM(a.kcal_por_porcao*c.numero_porcoes) as saldoKcal,\n" +           
"                                SUM(a.carboidratos_por_porcao*c.numero_porcoes) as saldoCarb,\n" +
"                                SUM(a.proteinas_por_porcao*c.numero_porcoes) as saldoProt,\n" +
"                                SUM(a.gorduras_por_porcao*c.numero_porcoes) as saldoGorduras\n" +
"	   FROM alimento a, usuario u, consumo_alimento c, resumo_dia r WHERE r.usuario_id = " + id_usuario +
"	   AND r.data = current_date " +
"	   AND c.alimento_id = a.id " + 
"          AND r.id = c.dieta_id " +
"          AND r.usuario_id = u.id ";
                    

          //  System.out.println("sql: " + sql);

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
	   "FROM usuario u, resumo_dia r, atividadefisica f, registro_atividadefisica rf WHERE r.usuario_id = " + id_usuario + 
	   " AND r.data = current_date "+ 
          "AND f.idatividadefisica = rf.atividadefisica_idatividadefisica "+
          "AND rf.dieta_id = r.id " +
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
"	   AND r.data = current_date " +
"	   AND c.alimento_id = a.id " + 
"          AND r.id = c.dieta_id" +
"          AND r.usuario_id = u.id ";

 //           System.out.println("sql: " + sql);

            resultadoQ = st.executeQuery(sql);

            if (resultadoQ.next()) {
                return round(resultadoQ.getDouble("saldoCarb"),2);
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
                return round(resultadoQ.getDouble("saldoProt"),2);
            }
            return 0;
            
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }
    
    public String zerarNumeroPorcoes(int id_usuario, int id_alimento, int resumo_id)
    {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "UPDATE consumo_alimento SET numero_porcoes = 0 WHERE alimento_id = "
                    + "(SELECT alimento_id FROM alimento a, usuario u, resumo_dia r, consumo_alimento c WHERE u.id = "+ id_usuario+
"          AND a.id = "+id_alimento+
"	   AND r.id = "+resumo_id+
"	   AND r.data = current_date "+
"	   AND c.alimento_id = a.id "+
"          AND r.id = c.dieta_id "+
"	   AND r.usuario_id = u.id)";
            
         System.out.println("sql: " + sql);
            int resultado = st.executeUpdate(sql);
            return null;
        }
        catch (Exception e) {
            System.out.println("Erro ao zerar porcoes = " + e);
            return e.toString();
        }
    }

        public String zerarAtividadeFisica(int id_usuario, int idatividadefisica, int resumo_id)
    {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "UPDATE registro_atividadefisica SET duracao = 0 WHERE atividadefisica_idatividadefisica = "
                    + "(SELECT atividadefisica_idatividadefisica FROM atividadefisica af, usuario u, resumo_dia r, registro_atividadefisica raf WHERE u.id = "+ id_usuario+
"          AND raf.atividadefisica_idatividadefisica = "+idatividadefisica+
"	   AND r.id = "+resumo_id+
"	   AND r.data = current_date "+
"	   AND raf.atividadefisica_idatividadefisica = af.idatividadefisica "+
"          AND r.id = raf.dieta_id "+
"	   AND r.usuario_id = u.id)";
            
         System.out.println("sql: " + sql);
            int resultado = st.executeUpdate(sql);
            return null;
        }
        catch (Exception e) {
            System.out.println("Erro ao zerar duração = " + e);
            return e.toString();
        }
    }
    
    public double updateSaldoGorduras(int id_usuario) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT SUM(a.kcal_por_porcao*c.numero_porcoes) as saldoKcal,\n" +
"                                SUM(a.carboidratos_por_porcao*c.numero_porcoes) as saldoCarb,\n" +
"                                SUM(a.proteinas_por_porcao*c.numero_porcoes) as saldoProt,\n" +
"                                SUM(a.gorduras_por_porcao*c.numero_porcoes) as saldoGorduras\n" +
"	   FROM alimento a, usuario u, consumo_alimento c, resumo_dia r WHERE r.usuario_id = " + id_usuario +
"	   AND r.data = current_date" +
"	   AND c.alimento_id = a.id" + 
"          AND r.id = c.dieta_id" +
"          AND r.usuario_id = u.id ";
            
                    

 //           System.out.println("sql: " + sql);

            resultadoQ = st.executeQuery(sql);

            if (resultadoQ.next()) {
                return round(resultadoQ.getDouble("saldoGorduras"),2);
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
    
    public void popularTabelaAlimentos(JTable tabela, int user_id) {
        // dados da tabela
        Object[][] dadosTabela = null;
        // cabecalho da tabela
        Object[] cabecalho = new Object[7];
        cabecalho[0] = "Nome";
        cabecalho[1] = "Carboidratos";
        cabecalho[2] = "Proteinas";
        cabecalho[3] = "Gorduras";
        cabecalho[4] = "Kcal por porção";
        cabecalho[5] = "Porções";
        cabecalho[6] = "Calorias totais";

        // cria matriz de acordo com nº de registros da tabela
        try {
            resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT count(*) "
                    + "FROM alimento a, resumo_dia r, consumo_alimento c, usuario u " 
                    + "WHERE r.data = current_date AND a.id = c.alimento_id AND c.dieta_id = r.id "
                    + "AND r.usuario_id = u.id AND r.usuario_id = " + user_id);

            resultadoQ.next();

            dadosTabela = new Object[resultadoQ.getInt(1)][7];

        } catch (Exception e) {
            System.out.println("Erro ao consultar XXX: " + e);
        }

        int lin = 0;

        // efetua consulta na tabela
        try {
            resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT a.nome_alimento, a.carboidratos_por_porcao, a.proteinas_por_porcao, a.gorduras_por_porcao, a.kcal_por_porcao, c.numero_porcoes, c.numero_porcoes*a.kcal_por_porcao as total "
                    + "FROM alimento a, resumo_dia r, consumo_alimento c, usuario u " 
                    + "WHERE r.data = current_date AND a.id = c.alimento_id AND c.dieta_id = r.id "
                    + "AND r.usuario_id = u.id AND r.usuario_id = " + user_id + " ORDER BY total desc, nome asc ");

            while (resultadoQ.next()) {

                dadosTabela[lin][0] = resultadoQ.getString("nome_alimento");
                dadosTabela[lin][1] = resultadoQ.getDouble("carboidratos_por_porcao");
                dadosTabela[lin][2] = resultadoQ.getDouble("proteinas_por_porcao");
                dadosTabela[lin][3] = resultadoQ.getDouble("gorduras_por_porcao");
                dadosTabela[lin][4] = resultadoQ.getDouble("kcal_por_porcao");
                dadosTabela[lin][5] = resultadoQ.getDouble("numero_porcoes");
                dadosTabela[lin][6] = resultadoQ.getDouble("total");
                
       lin++;
            }
        } catch (Exception e) {
            System.out.println("problemas para popular tabela...");
            System.out.println(e);
        }
    
                tabela.setModel(new DefaultTableModel(dadosTabela, cabecalho) {
            @Override
            // quando retorno for FALSE, a tabela nao é editavel
            public boolean isCellEditable(int row, int column) {
                return false;
                /*  
                 if (column == 3) {  // apenas a coluna 3 sera editavel
                 return true;
                 } else {
                 return false;
                 }
                 */
            }

            // alteracao no metodo que determina a coluna em que o objeto ImageIcon devera aparecer
            @Override
            public Class getColumnClass(int column) {

                if (column == 2) {
//                    return ImageIcon.class;
                }
                return Object.class;
            }
        });

        // permite seleção de apenas uma linha da tabela
        tabela.setSelectionMode(0);

        // redimensiona as colunas de uma tabela
        TableColumn column = null;
        for (int i = 0; i < tabela.getColumnCount(); i++) {
            column = tabela.getColumnModel().getColumn(i);
            switch (i) {
                case 0:
                    column.setPreferredWidth(17);
                    break;
                case 1:
                    column.setPreferredWidth(140);
                    break;
//                case 2:
//                    column.setPreferredWidth(14);
//                    break;
            }
        }
     }

    
    public void popularTabelaAtividadeFisica(JTable tabela, int user_id) {
        // dados da tabela
        Object[][] dadosTabela = null;
        // cabecalho da tabela
        Object[] cabecalho = new Object[4];
        cabecalho[0] = "Nome";
        cabecalho[1] = "Kcal/hora";
        cabecalho[2] = "Duração (minutos)";
        cabecalho[3] = "Gasto total (kcal)";

        // cria matriz de acordo com nº de registros da tabela
        try {
            resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    +"SELECT COUNT(*) "+
                    "FROM atividadefisica af, registro_atividadefisica raf, usuario u, resumo_dia r "+
                    "WHERE r.data = current_date AND af.idatividadefisica = raf.atividadefisica_idatividadefisica AND raf.dieta_id = r.id "+
                    "AND r.usuario_id = " + user_id + " AND r.usuario_id = u.id");

            resultadoQ.next();

            dadosTabela = new Object[resultadoQ.getInt(1)][4];

        } catch (Exception e) {
            System.out.println("Erro ao consultar XXX: " + e);
        }

        int lin = 0;

        // efetua consulta na tabela
        try {
            resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    +"SELECT af.nome_atividade, af.kcal_por_hora, raf.duracao, ((af.kcal_por_hora*raf.duracao)/60) as total "+
                    "FROM atividadefisica af, registro_atividadefisica raf, usuario u, resumo_dia r "+
                    "WHERE r.data = current_date AND af.idatividadefisica = raf.atividadefisica_idatividadefisica AND raf.dieta_id = r.id "+
                    "AND r.usuario_id = " + user_id + " AND r.usuario_id = u.id");

            while (resultadoQ.next()) {

                dadosTabela[lin][0] = resultadoQ.getString("nome_atividade");
                dadosTabela[lin][1] = resultadoQ.getDouble("kcal_por_hora");
                dadosTabela[lin][2] = resultadoQ.getDouble("duracao");
                dadosTabela[lin][3] = resultadoQ.getDouble("total");
                
       lin++;
            }
        } catch (Exception e) {
            System.out.println("problemas para popular tabela...");
            System.out.println(e);
        }
    
                tabela.setModel(new DefaultTableModel(dadosTabela, cabecalho) {
            @Override
            // quando retorno for FALSE, a tabela nao é editavel
            public boolean isCellEditable(int row, int column) {
                return false;
                /*  
                 if (column == 3) {  // apenas a coluna 3 sera editavel
                 return true;
                 } else {
                 return false;
                 }
                 */
            }

            // alteracao no metodo que determina a coluna em que o objeto ImageIcon devera aparecer
            @Override
            public Class getColumnClass(int column) {

                if (column == 2) {
//                    return ImageIcon.class;
                }
                return Object.class;
            }
        });

        // permite seleção de apenas uma linha da tabela
        tabela.setSelectionMode(0);

        // redimensiona as colunas de uma tabela
        TableColumn column = null;
        for (int i = 0; i < tabela.getColumnCount(); i++) {
            column = tabela.getColumnModel().getColumn(i);
            switch (i) {
                case 0:
                    column.setPreferredWidth(17);
                    break;
                case 1:
                    column.setPreferredWidth(140);
                    break;
//                case 2:
//                    column.setPreferredWidth(14);
//                    break;
            }
        }
     }
}
