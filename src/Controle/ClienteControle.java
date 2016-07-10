/*
 * 
 */
package Controle;

import Entidade.Cliente;
import Factory.ConnectionFactory;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Antonio, Jhon David, Juliane e Zaira
 */
public class ClienteControle {
    double salario;
    double emprestAnt;


     
    /**  
     * Método para calcular margem de consignação e somar valor das parcelas de
     * empréstimos anteriores
     */    
    public static double comparaSalario(double salario, double emprestAnt){
	return (double)((salario * 0.3) - emprestAnt);
    }
        public void insert(Cliente cliente){
	Connection con = null;
	PreparedStatement ps = null;
		
	String sql = "INSERT INTO cliente (cpf, nome, tipo, salario) "
                  + "VALUES (?, ?, ?, ?)";
	try {
            con = ConnectionFactory.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getCpf());
            ps.setString(2, cliente.getNome());
            ps.setString(3, cliente.getTipo());
            ps.setDouble(4, Cliente.getSalario());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, cliente.getNome() + " "
                    + "cadastrado(a) com sucesso! ");
	}catch(SQLException | HeadlessException ex){
            System.err.println("Erro no insert");
        }finally{
          
	}
    }
    
    /**
     * Método que pesquisa cliente pelo cpf
     *  
     * @param cpf
     * @return 
     */
    public String pesquisarPorCpf(String cpf){
        String c = null;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String sql = "SELECT * FROM cliente WHERE cpf = ?";
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, cpf);
            rs = ps.executeQuery();
            while (rs.next()){
                Cliente cli = new Cliente();
                cli.setCpf(rs.getString("cpf"));
                cli.setNome(rs.getString("nome"));
                cli.setTipo(rs.getString("tipo"));
                Cliente.setSalario(rs.getFloat("salario"));
                c = cli.toString();
            }
        }catch(Exception ex){
            System.err.println("Erro no código");
        }finally{
        }
        return c;
    }
	
    /**
    * Método que busca o cliente pelo nome 
     * @param nome
     * @return 
    */
    public static String pesquisarPorNome(String nome){
        String ls = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT nome, cpf, tipo, salario FROM cliente WHERE nome LIKE ?";
        try {
            con = ConnectionFactory.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, nome + "%");
            rs = ps.executeQuery();
            while (rs.next()){
                Cliente cliente = new Cliente();
                cliente.setNome(rs.getString("nome"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setTipo(rs.getString("tipo"));
                Cliente.setSalario(rs.getFloat("salario"));
                ls = cliente.toString();
            }
        }catch(Exception ex){
            System.err.println("Erro no código");
        }finally{
        }
        return ls;
    }
	
    /**
     * Método que atualiza cadastro do cliente, buscando pelo cpf
     * 
     * @param cliente
     */
    public void alterarPorCpf(Cliente cliente){
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "UPDATE cliente SET nome = ?, tipo = ?, salario = ? WHERE cpf = ?";
        try {
            con = ConnectionFactory.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getTipo());
            ps.setDouble(3, Cliente.getSalario());
            ps.setString(4, cliente.getCpf());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cadastro Alterado!");
        }catch(SQLException | HeadlessException ex){
            System.err.println("Erro no código");
        }finally{
        }
    }
	
    /**
     * Método que exclui cliente buscando pelo cfp
     * 
     * @param cpf
     */
    public void excluirPorCpf(String cpf){
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "DELETE FROM cliente WHERE cpf = ?";
        try {
            con = ConnectionFactory.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, cpf);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cliente excluido!");
        }catch(SQLException | HeadlessException ex){
            System.err.println("Erro no código");
        }finally{
	}
    }	
}
