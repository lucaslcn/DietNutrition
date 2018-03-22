/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;
import entidades.Usuario;
import entidades.alimento;
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
public class alimentoDAO implements IDAO_T<alimento> {
    
    ResultSet resultadoQ = null;

    @Override
    public String salvar(alimento o) {
       
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "INSERT INTO alimento VALUES ("
                    + "DEFAULT, "
                    + "'" + o.getNome_alimento() + "', "
                    + "'" + o.getCarboidratos_por_porcao() + "',"
                    + "'" + o.getProteinas_por_porcao() + "',"
                    + "'" + o.getGorduras_por_porcao() + "',"
                    + "'" + o.getKcal_por_porcao() + "')";
                    

            System.out.println("sql: " + sql);

            int resultado = st.executeUpdate(sql);

            return null;

        } catch (Exception e) {
            System.out.println("Erro salvar alimento = " + e);
            return e.toString();
        }
    }
        

    @Override
    public String atualizar(alimento o) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "UPDATE alimento ("
                    + "SET nome_alimento = '" + o.getNome_alimento() + "', "
                    + "carboidratos_por_porcao = '" + o.getCarboidratos_por_porcao() + "',"
                    + "proteinas_por_porcao = '" + o.getProteinas_por_porcao() + "',"
                    + "gorduras_por_porcao = '" + o.getGorduras_por_porcao() + "',"
                    + "kcal_por_porcao = '" + o.getKcal_por_porcao() + "'"
                    + "WHERE id = " + o.getId() + "')";
                    

            System.out.println("sql: " + sql);

            int resultado = st.executeUpdate(sql);

            return null;

        } catch (Exception e) {
            System.out.println("Erro salvar alimento = " + e);
            return e.toString();
        }
    }

    @Override
    public String excluir(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<alimento> consultarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<alimento> consultar(String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public alimento consultarId(int id) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT * FROM alimento "
                    + "WHERE id = " + id;

            System.out.println("sql: " + sql);

            resultadoQ = st.executeQuery(sql);

            if (resultadoQ.next()) {
                alimento alimento = new alimento();

                alimento.setId(id);
                alimento.setNome_alimento(resultadoQ.getString("nome_alimento"));
                alimento.setCarboidratos_por_porcao(resultadoQ.getDouble("carboidratos_por_porcao"));
                alimento.setProteinas_por_porcao(resultadoQ.getDouble("proteinas_por_porcao"));
                alimento.setGorduras_por_porcao(resultadoQ.getDouble("gorduras_por_porcao"));
                alimento.setKcal_por_porcao(resultadoQ.getDouble("kcal_por_porcao"));
                
                return alimento;
            }

        } catch (Exception e) {
            System.out.println("Erro atualizar alimento = " + e);
        }
        return null;
    }
    
    public void popularTabela(JTable tabela, String criterio) {
        // dados da tabela
        Object[][] dadosTabela = null;

        // cabecalho da tabela
        Object[] cabecalho = new Object[6];
        cabecalho[0] = "Codigo";
        cabecalho[1] = "Nome";
        cabecalho[2] = "Carboidratos por porcão";
        cabecalho[3] = "Proteínas por porção";
        cabecalho[4] = "Gorduras por porção";
        cabecalho[5] = "Kcal por porção";

        // cria matriz de acordo com nº de registros da tabela
        try {
            resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT count(*) FROM alimento WHERE NOME_ALIMENTO ILIKE '%" + criterio + "%'");

            resultadoQ.next();

            dadosTabela = new Object[resultadoQ.getInt(1)][6];

        } catch (Exception e) {
            System.out.println("Erro ao consultar XXX: " + e);
        }

        int lin = 0;

        // efetua consulta na tabela
        try {
            resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT * FROM alimento WHERE NOME_ALIMENTO ILIKE '%" + criterio + "%'");

            while (resultadoQ.next()) {

                dadosTabela[lin][0] = resultadoQ.getInt("id");
                dadosTabela[lin][1] = resultadoQ.getString("nome_alimento");
                dadosTabela[lin][2] = resultadoQ.getDouble("carboidratos_por_porcao");
                dadosTabela[lin][3] = resultadoQ.getDouble("proteinas_por_porcao");
                dadosTabela[lin][4] = resultadoQ.getDouble("gorduras_por_porcao");
                dadosTabela[lin][5] = resultadoQ.getDouble("kcal_por_porcao");



                // caso a coluna precise exibir uma imagem
//                if (resultadoQ.getBoolean("Situacao")) {
//                    dadosTabela[lin][2] = new ImageIcon(getClass().getClassLoader().getResource("Interface/imagens/status_ativo.png"));
//                } else {
//                    dadosTabela[lin][2] = new ImageIcon(getClass().getClassLoader().getResource("Interface/imagens/status_inativo.png"));
//                }
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
