/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import entidades.Usuario;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import support.ConexaoBD;
import support.Formatacao;
import support.IDAO_T;
import support.MD5;
import static support.MD5.criptoSenha;

/**
 *
 * @author fabricio.pretto
 */
public class usuarioDAO implements IDAO_T<Usuario> {

    ResultSet resultadoQ = null;

    
    
    public String modifyAccount(int user_id, String username, String password)
    {
        try{
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            
            String sql = "UPDATE usuario "
                    +"SET username = '" + username + "', "
                    +"senha = '" + MD5.criptoSenha(password) + "' "
                    +"WHERE usuario.id = " + user_id;
            
            System.out.println("sql: " + sql);
            int resultado = st.executeUpdate(sql);
            return null;
        }
        catch (Exception e) {
            System.out.println("Erro ao criar/modificar acesso = " + e);
            return e.toString();
        }
    }
    
    @Override
    public String salvar(Usuario u) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "INSERT INTO usuario VALUES ("
                    + "DEFAULT, "
                    + "'" + u.getNome() + "', "
                    + "'" + u.getEmail() + "',"
                    + "'" + u.getAltura_cm() + "',"
                    + "'" + u.getSexo() + "',"
                    + "'" + u.getData_nascimento() + "', "
                    + "true)";

            System.out.println("sql: " + sql);

            int resultado = st.executeUpdate(sql);

            return null;

        } catch (Exception e) {
            System.out.println("Erro salvar usuário = " + e);
            return e.toString();
        }
    }

    @Override
    public String atualizar(Usuario u) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "UPDATE usuario "
                    + "SET nome = '" + u.getNome() + "', "
                    + "email = '" + u.getEmail() + "',"
                    + "altura_cm = '" + u.getAltura_cm() + "',"
                    + "sexo = '" + u.getSexo() + "', "
                    + "data_nascimento = '" + u.getData_nascimento() + "'"
                    + "WHERE id = " + u.getId();

            System.out.println("sql: " + sql);

            int resultado = st.executeUpdate(sql);

            return null;

        } catch (Exception e) {
            System.out.println("Erro atualizar usuario = " + e);
            return e.toString();
        }
    }

    @Override
    public String excluir(int id) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = ("UPDATE usuario "
                    + "set delete = NOT delete "
                    + "WHERE id = " + id);

            System.out.println("sql = " + sql);

            int resultado = st.executeUpdate(sql);
            return null;
        } catch (Exception e) {
            System.err.println("Erro ao excluir usuario");
            return e.toString();
        }
    }

    @Override
    public ArrayList<Usuario> consultarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Usuario> consultar(String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Usuario consultarId(int id) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT * FROM usuario "
                    + "WHERE id = " + id;

            System.out.println("sql: " + sql);

            resultadoQ = st.executeQuery(sql);

            if (resultadoQ.next()) {
                Usuario usuario = new Usuario();

                usuario.setId(id);
                usuario.setNome(resultadoQ.getString("nome"));
                usuario.setEmail(resultadoQ.getString("email"));
                usuario.setAltura_cm(resultadoQ.getInt("altura_cm"));
                usuario.setSexo(resultadoQ.getString("sexo").charAt(0));
                usuario.setData_nascimento(resultadoQ.getString("data_nascimento"));
                usuario.setDelete(resultadoQ.getBoolean("delete"));

                return usuario;
            }

        } catch (Exception e) {
            System.out.println("Erro atualizar usuário = " + e);
        }
        return null;
    }

    public void popularTabela(JTable tabela, String criterio) {
        // dados da tabela
        Object[][] dadosTabela = null;

        // cabecalho da tabela
        Object[] cabecalho = new Object[7];
        cabecalho[0] = "Código";
        cabecalho[1] = "Nome";
        cabecalho[2] = "Email";
        cabecalho[3] = "Altura";
        cabecalho[4] = "Sexo";
        cabecalho[5] = "Data nasc.";
        cabecalho[6] = "Situação";

        // cria matriz de acordo com nº de registros da tabela
        try {
            resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT count(*) FROM usuario WHERE NOME ILIKE '%" + criterio + "%'");

            resultadoQ.next();

            dadosTabela = new Object[resultadoQ.getInt(1)][7];

        } catch (Exception e) {
            System.out.println("Erro ao consultar XXX: " + e);
        }

        int lin = 0;

        // efetua consulta na tabela
        try {
            resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT * FROM usuario WHERE NOME ILIKE '%" + criterio + "%'");

            while (resultadoQ.next()) {

                dadosTabela[lin][0] = resultadoQ.getInt("id");
                dadosTabela[lin][1] = resultadoQ.getString("nome");
                dadosTabela[lin][2] = resultadoQ.getString("email");
                dadosTabela[lin][3] = resultadoQ.getString("altura_cm");
                dadosTabela[lin][4] = resultadoQ.getString("sexo");
                dadosTabela[lin][5] = Formatacao.ajustaDataDMA(resultadoQ.getString("data_nascimento"));
                dadosTabela[lin][6] = resultadoQ.getBoolean("delete");

                if (resultadoQ.getBoolean("delete"))
                {
                    dadosTabela[lin][6] = "Ativo";
                }
                else { dadosTabela[lin][6] = "Inativo";}
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

        // configuracoes adicionais no componente tabela
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
        // renderizacao das linhas da tabela = mudar a cor
//        jTable1.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
//
//            @Override
//            public Component getTableCellRendererComponent(JTable table, Object value,
//                    boolean isSelected, boolean hasFocus, int row, int column) {
//                super.getTableCellRendererComponent(table, value, isSelected,
//                        hasFocus, row, column);
//                if (row % 2 == 0) {
//                    setBackground(Color.GREEN);
//                } else {
//                    setBackground(Color.LIGHT_GRAY);
//                }
//                return this;
//            }
//        });
    }

}
