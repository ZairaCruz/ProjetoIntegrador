/*
 * Classe com todos os calculos que serão utilizados nas tabelas
 */
package Controle;

import Entidade.Cliente;
import Entidade.Emprestimo;
import Factory.ConnectionFactory;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Antonio, Jhon David, Juliane e Zaira
 */
public class EmprestimoControle {
   
    private static double saldoDevedor;
    private static double amortizacao;
    private static int qtdeParcelas;
    private static int NumParcela;
    private static double taxa;
	
    public EmprestimoControle() {

    }

    public static double getTaxa() {
        return taxa;
    }

    public static void setTaxa(double taxa) {
        EmprestimoControle.taxa = taxa;
    }

    
    public static double getSaldoDevedor() {
        return saldoDevedor;
    }

    public static void setSaldoDevedor(double saldoDevedor) {
        EmprestimoControle.saldoDevedor = saldoDevedor;
    }

    public static double getAmortizacao() {
        return amortizacao;
    }

    public static void setAmortizacao(double amortizacao) {
        EmprestimoControle.amortizacao = amortizacao;
    }

    public static int getQtdeParcelas() {
        return qtdeParcelas;
    }

    public static void setQtdeParcelas(int qtdeParcelas) {
        EmprestimoControle.qtdeParcelas = qtdeParcelas;
    }

    public static int getNumParcela() {
        return NumParcela;
    }

    public static void setNumParcela(int NumParcela) {
        EmprestimoControle.NumParcela = NumParcela;
    }

    
    
    /**
     * Método para calcular valor da amortização
     * Tabela SAC
     * @param qtdeParcelas
     * @param valorEmprestimo
     * @return 
     */
    public static double calculaAmortizacaoSac(int qtdeParcelas, 
            double valorEmprestimo) {
        return valorEmprestimo / qtdeParcelas;
    }

    /**
     * Método para calcular valor dos juros em cada parcela
     * Tabela SAC
     * @param saldoDevedor
     * @param taxa
     * @return 
     */	
    public static double calculaJurosSac(double saldoDevedor, double taxa) {
  
       return saldoDevedor * (taxa/100);
    }

    /**
     * Método para calcular valor da parcela
     * Tabela SAC
     * @param amortizacao
     * @param juros
     * @return 
     */
    public static double calculaParcelaSac(double amortizacao, double juros) {
        return (amortizacao + juros);
    }

    /**
     * Método para calcular saldo devedor após pagamento de cada parcela
     * Tabela SAC
     * @param saldoDevedor
     * @param amortizacao
     * @return 
     */
    public static double calculaSaldoDvSac(double saldoDevedor, 
            double amortizacao) {
        return saldoDevedor - amortizacao;
    }

    ////////////////////////////////////////PRICE
    
    /**
     * Método ara calcular valor da parcela 
     * Tabela PRICE
     * @param valorEmprestimo
     * @param taxa
     * @param qtdeParcela
     * @return 
     */
    public static double calculaParcelaPrice(double valorEmprestimo, 
            double taxa, int qtdeParcela) {
	
        return  valorEmprestimo * (((taxa/100) * 
                (Math.pow(1 + (taxa/100), qtdeParcela))) / 
                ((Math.pow(1 + (taxa/100), qtdeParcela)) - 1));
    }

    /**
     * Método para calcular juros sobre cada parcela
     * Tabela PRICE
     * @param saldoDevedor
     * @param taxa
     * @return 
     */
    public static double calculaJurosPrice(double saldoDevedor, double taxa) {
        return saldoDevedor * (taxa/100);
    }
    
    /**
     * Método para calcular valor da amortização
     * Tabela PRICE
     * @param valorParcela
     * @param taxa
     * @param saldoDevedor
     * @return 
     */
    public static double calcAmortPrice(double valorParcela, double taxa, 
            double saldoDevedor) {
        return valorParcela - (saldoDevedor * (taxa/100));
    }
    
    /**
     * Método para calcular o saldo devedor após o pagamento de cada parcela
     * Tabela PRICE
     * @param saldoDevedor
     * @param amortizacao
     * @return 
     */
    public static double calcSalDevPrice(double saldoDevedor, 
            double amortizacao) {
        return saldoDevedor - amortizacao;
    }
    
        /**
     * Método para inserir dados do emprestimo no banco de dados 
     * @param emprestimo
     * @param cliente
     */
    public void inserirEmprestimo(Emprestimo emprestimo, Cliente cliente){
	Connection con = null;
	PreparedStatement ps = null;
			
	String sql = "INSERT INTO emprestimo (numero, valortotal, taxa, qtdeparcelas, cpfCliente, tabela) "
                + "VALUES (?,?,?,?,?,?)";
	try {
            con = ConnectionFactory.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, emprestimo.getNumero());
            ps.setDouble(2, emprestimo.getValorEmprestimo());
            ps.setDouble(3, Emprestimo.getTaxa());
            ps.setInt(4, emprestimo.getQtdeParcela());
            ps.setString(5, cliente.getCpf());
            ps.setString(6, emprestimo.getTabela());			
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Simulação nº " + emprestimo.getNumero() + " salva.");
        }catch(SQLException | HeadlessException ex){
            System.err.println("Erro no codigo");
	}finally{
	
        }
    }
	

}
    

