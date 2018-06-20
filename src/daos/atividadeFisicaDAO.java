/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import entidades.atividadeFisica;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import support.ConexaoBD;
import support.IDAO_T;

/**
 *
 * @author Lucas
 */
public class atividadeFisicaDAO implements IDAO_T<atividadeFisica> {
    
    ResultSet resultadoQ = null;

    @Override
    public String salvar(atividadeFisica o){
        
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "INSERT INTO atividadefisica VALUES ("
                    + "DEFAULT, "
                    + "'" + o.getNome_atividade()+ "', "
                    + "'" + o.getKcal_por_hora()+ "',"
                    + "true)";
                    

            System.out.println("sql: " + sql);

            int resultado = st.executeUpdate(sql);

            return null;

        } catch (Exception e) {
            System.out.println("Erro salvar atividade fisica = " + e);
            return e.toString();
        }
    }
        

    @Override
    public String atualizar(atividadeFisica o) {
try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "UPDATE atividadefisica "
                    + "SET nome_atividade = '" + o.getNome_atividade()+ "', "
                    + "kcal_por_hora = '" + o.getKcal_por_hora()+ "' "
                    + "WHERE idatividadefisica = " + o.getId() + ";";
                    

            System.out.println("sql: " + sql);

            int resultado = st.executeUpdate(sql);

            return null;

        } catch (Exception e) {
            System.out.println("Erro salvar atividadeFisica = " + e);
            return e.toString();
        }
    }

    @Override
    public String excluir(int id) {
      try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = ("UPDATE atividadeFisica "
                    + "set delete = NOT delete "
                    + "WHERE idatividadefisica = " + id);

            System.out.println("sql = " + sql);

            int resultado = st.executeUpdate(sql);
            return null;
        } catch (Exception e) {
            System.err.println("Erro ao excluir atividade fisica");
            return e.toString();
        }
    }

    @Override
    public ArrayList<atividadeFisica> consultarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<atividadeFisica> consultar(String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public atividadeFisica consultarId(int id) {
        
         try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT * FROM atividadeFisica "
                    + "WHERE idatividadefisica = " + id;

            System.out.println("sql: " + sql);

            resultadoQ = st.executeQuery(sql);

            if (resultadoQ.next()) {
                atividadeFisica atividadeFisica = new atividadeFisica();

                atividadeFisica.setId(id);
                atividadeFisica.setNome_atividade(resultadoQ.getString("nome_atividade"));
                atividadeFisica.setKcal_por_hora(resultadoQ.getDouble("kcal_por_hora"));
                atividadeFisica.setDelete(resultadoQ.getBoolean("delete"));
                
                return atividadeFisica;
            }

        } catch (Exception e) {
            System.out.println("Erro atualizar = " + e);
        }
        return null;

    }
    
     public void popularTabela(JTable tabela, String criterio) {
        // dados da tabela
        Object[][] dadosTabela = null;
        // cabecalho da tabela
        Object[] cabecalho = new Object[4];
        cabecalho[0] = "Codigo";
        cabecalho[1] = "Nome";
        cabecalho[2] = "Kcal por hora";
        cabecalho[3] = "Situação";

        // cria matriz de acordo com nº de registros da tabela
        try {
            resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT count(*) FROM atividadeFisica WHERE NOME_ATIVIDADE ILIKE '%" + criterio + "%'");

            resultadoQ.next();

            dadosTabela = new Object[resultadoQ.getInt(1)][4];

        } catch (Exception e) {
            System.out.println("Erro ao consultar XXX: " + e);
        }

        int lin = 0;

        // efetua consulta na tabela
        try {
            resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT * FROM atividadeFisica WHERE NOME_ATIVIDADE ILIKE '%" + criterio + "%' ORDER BY DELETE DESC, NOME_ATIVIDADE ASC");

            while (resultadoQ.next()) {

                dadosTabela[lin][0] = resultadoQ.getInt("idatividadefisica");
                dadosTabela[lin][1] = resultadoQ.getString("nome_atividade");
                dadosTabela[lin][2] = resultadoQ.getDouble("kcal_por_hora");
                dadosTabela[lin][3] = resultadoQ.getBoolean("delete");
                
                 if (resultadoQ.getBoolean("delete"))
                {
                    dadosTabela[lin][3] = "Ativo";
                }
                else { dadosTabela[lin][3] = "Inativo";}
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
