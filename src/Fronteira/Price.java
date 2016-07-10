/*
 * Tabela do sistema de amortização francês - Price
 */

package Fronteira;

/**
 * @author Antonio, Jhon David, Juliane e Zaira
 */

import Controle.ClienteControle;
import Controle.EmprestimoControle;
import Entidade.Cliente;
import Entidade.Emprestimo;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.text.DecimalFormat;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.text.NumberFormat;

public class Price extends JFrame {
    static Emprestimo emprestimoP = new Emprestimo();
    ClienteControle cliCont = new ClienteControle();
    EmprestimoControle empCont = new EmprestimoControle();
    NumberFormat x = NumberFormat.getCurrencyInstance();
    Cliente cliente = new Cliente();
    DecimalFormat df = new DecimalFormat("0.##");
    double saldoDv, totalPagaar;
    String imprime;
    private double margemCliente;
    private double pmt, somaPmt= 0;
    private JTable jTablePrice;
    int cont;
    
    /**
     * Método que torna visivel a janela
     * 
     * @param args
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Price janela = new Price(emprestimoP);
                    janela.setVisible(true);	
                        
		} catch (Exception e) {
		}
            }
	});
    }
    
    /**
     * Método para a formatação da tabela Sac
     *
     * @param emprest
     */
    public Price(Emprestimo emprest) {
	setTitle("Tabela Price");
	setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        jTablePrice = new javax.swing.JTable();
        jTablePrice = new JTable();
        jTablePrice.setBorder(new LineBorder(Color.LIGHT_GRAY));
        jTablePrice.setBounds(50, 100, 500, 580);
        setSize(new Dimension(550, 650));
        setLocationRelativeTo(null);
        jTablePrice.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] {}
        )); 
        JScrollPane scrollPane = new JScrollPane(jTablePrice);
	add(scrollPane, BorderLayout.CENTER);
	this.emprestimoP = emprest;
    }

    /**
     * Método para a formatação da tabela Sac
     */	
    public void tabelaPrice() {
        EmprestimoControle.setSaldoDevedor(emprestimoP.getValorEmprestimo());
        String[] nomeCol;
        nomeCol = new String[]{"NºParcela", "Parcelas(PMT)", "Amortização", "Juros", "Saldo Devedor"};
        Object[][] dado = new Object[emprestimoP.getQtdeParcela()][5];
        ////Inserir número das parcelas
        for (int i = 0; i < emprestimoP.getQtdeParcela(); i++) {
            dado[i][0] = i + 1;
        }
        pmt = 0;
        
        //Calcula e insere o valor da parcela
        for (int i = 0; i < emprestimoP.getQtdeParcela(); i++) {
            
            dado[i][1] = x.format(EmprestimoControle.calculaParcelaPrice(emprestimoP.getValorEmprestimo(), Emprestimo.getTaxa(), emprestimoP.getQtdeParcela()));
            pmt = EmprestimoControle.calculaParcelaPrice(emprestimoP.getValorEmprestimo(), Emprestimo.getTaxa(), emprestimoP.getQtdeParcela());
            cont++;
            
        }
	//Inserir valor dos juros      
        for (int i = 0; i < emprestimoP.getQtdeParcela(); i++) {
            if (i <= 0) {
                //Primeira parcela
                saldoDv = (double)emprestimoP.getValorEmprestimo();
                dado[i][3] = x.format(EmprestimoControle.calculaJurosPrice(saldoDv, Emprestimo.getTaxa()));
                dado[i][2] = x.format(EmprestimoControle.calcAmortPrice( pmt, Emprestimo.getTaxa(), saldoDv));
                dado[i][4] = x.format(EmprestimoControle.calcSalDevPrice(emprestimoP.getValorEmprestimo(), EmprestimoControle.calcAmortPrice(pmt, Emprestimo.getTaxa(), saldoDv)));
                saldoDv = EmprestimoControle.calcSalDevPrice(emprestimoP.getValorEmprestimo(), EmprestimoControle.calcAmortPrice(pmt, Emprestimo.getTaxa(), saldoDv));       
            }else {
                dado[i][3] = x.format(EmprestimoControle.calculaJurosPrice(saldoDv, Emprestimo.getTaxa()));
                dado[i][2] = x.format(EmprestimoControle.calcAmortPrice(pmt, Emprestimo.getTaxa(), saldoDv));
                dado[i][4] = x.format(EmprestimoControle.calcSalDevPrice(saldoDv, EmprestimoControle.calcAmortPrice(pmt, Emprestimo.getTaxa(), saldoDv)));
                saldoDv = EmprestimoControle.calcSalDevPrice(saldoDv, EmprestimoControle.calcAmortPrice(pmt, Emprestimo.getTaxa(), saldoDv));       
                if(saldoDv <= 0){
                    dado[i][4] = x.format(0.00);
                }	
            }
        }
        totalPagaar = pmt * cont;
        imprime = df.format(totalPagaar);
        
        jTablePrice.setModel(new DefaultTableModel( (Object[][]) dado, nomeCol) );
        //Compara margem de consignação com valor da parcela
	margemCliente =  ClienteControle.comparaSalario(Cliente.getSalario(), Cliente.getEmprestAnt());
        if(pmt <= margemCliente){
            JOptionPane.showMessageDialog(null, "Emprestimo será autorizado."
                    + "\nTotal a pagar: R$ " + (imprime));
	}else{
	    JOptionPane.showMessageDialog(null, "Não será autorizado. "
                 + "\nValor da parcela ultrapassa valor da margem de consignação");
	}
    }
}
