/*
 * Tabela do sistema de amortização constante - SAC
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
import java.text.NumberFormat;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class Sac extends JFrame {

    static Emprestimo emprestimoS = new Emprestimo();
    ClienteControle cliCont = new ClienteControle();
    EmprestimoControle empCont = new EmprestimoControle();
    NumberFormat x = NumberFormat.getCurrencyInstance();
    Cliente cliente = new Cliente();
    DecimalFormat df = new DecimalFormat("0.##");
    private JTable jTableSAC1;
    String imprime;
    double saldoDv;
    double pmt, parcela, amort, margemCliente, somaPmt = 0;
    

    
    
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
                    Sac janela = new Sac(emprestimoS);
                    janela.setVisible(true);
		} catch (Exception e) {
		}
            }
	});
    }
	
    /**
     * Conteúdos da tabela
     * 
     * @param emprestimoS
     */
    public Sac(Emprestimo emprestimoS) {
        setTitle("Tabela SAC");
	setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
	setResizable(false);
	jTableSAC1 = new JTable();
	jTableSAC1.setBorder(new LineBorder(Color.LIGHT_GRAY));
	jTableSAC1.setBounds(50, 100, 500, 580);
	setSize(new Dimension(550, 650));
	setLocationRelativeTo(null);
	jTableSAC1.setModel(new DefaultTableModel(
            new Object[][] {
	    },
            new String[] {
	    }
	));
        //Fornece uma visão de rolagem
	JScrollPane scrollPane = new JScrollPane(jTableSAC1);
	add(scrollPane, BorderLayout.CENTER);
	this.emprestimoS = emprestimoS;
    }
 
     /**
     * Método para a formatação da tabela Sac
     */
    public void tabelaSac() {
        EmprestimoControle.setSaldoDevedor(emprestimoS.getValorEmprestimo());
        String[] nomeColunas;
        nomeColunas = new String[]{"NºParcela", "Parcelas(PMT)", "Amortização", "Juros", "Saldo Devedor"};
	Object[][] dado = new Object[emprestimoS.getQtdeParcela()][5];
        
        ////Inserir número das parcelas
	for (int i = 0; i < emprestimoS.getQtdeParcela(); i++) {
            dado[i][0] = i + 1;
            
	}
      
	//Inserir valor da Amortização
	for (int i = 0; i < emprestimoS.getQtdeParcela(); i++) {
	    dado[i][2] = x.format(EmprestimoControle.calculaAmortizacaoSac(emprestimoS.getQtdeParcela(), emprestimoS.getValorEmprestimo()));
            amort = EmprestimoControle.calculaAmortizacaoSac(emprestimoS.getQtdeParcela(), emprestimoS.getValorEmprestimo());
        }
	
        somaPmt=0;
        saldoDv =  emprestimoS.getValorEmprestimo();
	for (int i = 0; i < emprestimoS.getQtdeParcela(); i++) {
            
            if (i <= 0) {
                
                pmt = (double) EmprestimoControle.calculaParcelaSac(amort, EmprestimoControle.calculaJurosSac(saldoDv, Emprestimo.getTaxa()));
                dado[i][4] = x.format(EmprestimoControle.calculaSaldoDvSac(emprestimoS.getValorEmprestimo(), amort));
                dado[i][3] = x.format(EmprestimoControle.calculaJurosSac(emprestimoS.getValorEmprestimo(), Emprestimo.getTaxa()));
                dado[i][1] = x.format(EmprestimoControle.calculaParcelaSac(amort, EmprestimoControle.calculaJurosSac(emprestimoS.getValorEmprestimo(), Emprestimo.getTaxa())));
               	saldoDv =  EmprestimoControle.calculaSaldoDvSac(saldoDv, amort);
                //soma de todas as parcelas
                somaPmt = somaPmt + pmt;


	    } else {
	        saldoDv =  EmprestimoControle.calculaSaldoDvSac(saldoDv, amort);
                dado[i][3] = x.format(EmprestimoControle.calculaJurosSac(saldoDv, Emprestimo.getTaxa()));
	        dado[i][1] = x.format(EmprestimoControle.calculaParcelaSac(amort, EmprestimoControle.calculaJurosSac(saldoDv, Emprestimo.getTaxa())));
                //soma de todas as parcelas
                somaPmt = somaPmt + EmprestimoControle.calculaParcelaSac(amort, EmprestimoControle.calculaJurosSac(saldoDv, Emprestimo.getTaxa()));
	        
	        if (saldoDv >= 0) {
                    dado[i][4] = x.format(saldoDv);
                   
	        } else {
	            dado[i][4] = x.format(0.00);
                    
	        }
            }
        }

      
	
        jTableSAC1.setModel(new DefaultTableModel((Object[][]) dado, nomeColunas));
        
        
        imprime = df.format(somaPmt);
        margemCliente =  ClienteControle.comparaSalario(Cliente.getSalario(), Cliente.getEmprestAnt());
        
        if(pmt <= margemCliente){
            JOptionPane.showMessageDialog(null, "Emprestimo será autorizado."
                    + "\n Total a pagar R$ : " + imprime);
	}else{
	    JOptionPane.showMessageDialog(null, "Não será autorizado. "
                 + "\nValor da parcela ultrapassa valor da margem de consignação");
	}
	        
    }

}
