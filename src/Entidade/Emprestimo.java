/*
 * Classe modelo, entidade Emprestimo
 */
package Entidade;

/**
 *
 * @author Antonio, Jhon David, Juliane e Zaira
 */
public class Emprestimo {
    private String numero;
    private double valorEmprestimo;
    private static double taxa;
    private int qtdeParcela;
    private String tabela;
    private double valorParcela;
    
	
    public String getNumero() {
	return numero;
    }

    public void setNumero(String numero) {
	this.numero = numero;
    }
	
    public double getValorEmprestimo() {
    	return valorEmprestimo;
    }
    
    public void setValorEmprestimo(double valorEmprestimo) {
	this.valorEmprestimo = valorEmprestimo;
    }

    public static double getTaxa() {
        return taxa;
    }

    public static void setTaxa(double taxa) {
        Emprestimo.taxa = taxa;
    }
    
    public int getQtdeParcela() {
	return qtdeParcela;
    }

    public void setQtdeParcela(int qtdeParcela) {
	this.qtdeParcela = qtdeParcela;
    }
    
    public String getTabela() {
	return tabela;
    }

    public void setTabela(String tabela) {
    	this.tabela = tabela;
    }

    public double getValorParcela() {
	return valorParcela;
    }

    public void setValorParcela(double valorParcela) {
	this.valorParcela = valorParcela;
    }
   


}
