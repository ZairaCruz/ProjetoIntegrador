/*
 * Tela principal que contém Menu de opções do sistema
 * é esta classe que dá acesso as outras telas
 */
package Fronteira;
/**
 * @author Antonio, Jhon David, Juliane e Zaira
 */

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class TelaPrincipal extends JFrame {
    
    JPanel painel = new JPanel();

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    TelaPrincipal janela = new TelaPrincipal();
                    janela.setVisible(true);
                    janela.setLocationRelativeTo(null);
                } catch (Exception e) {
                }
            }
        });
    }

    public TelaPrincipal() {
	setResizable(false);
	setTitle("Casa Bancária");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 100, 400, 400);
	painel.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(painel);
        
        ImageIcon icon = new ImageIcon("C:\\Users\\Zaira\\Desktop\\Programa\\ProjetoIntegrador\\src\\Imagem\\moedas.png");
        JLabel imagem = new JLabel(icon);
        imagem.setBounds(160, 20, 72, 72);
	painel.add(imagem);
        
	JButton cadastrarCliente = new JButton("Cadastrar Cliente");
	cadastrarCliente.setBounds(50, 100, 300, 50);
        cadastrarCliente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
		TelaCadastro cadastrar = new TelaCadastro();
		cadastrar.setVisible(true);				
            }
	});
	painel.setLayout(null);
	painel.add(cadastrarCliente);
		
	JButton fazerEmprestimos = new JButton("Fazer Empréstimo");
	fazerEmprestimos.setBounds(50, 200, 300, 50);
	fazerEmprestimos.setAlignmentX(Component.RIGHT_ALIGNMENT);
	fazerEmprestimos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
		TelaEmprestimo emprestar = new TelaEmprestimo();
		emprestar.setVisible(true);
            }
	});
	painel.add(fazerEmprestimos);
	
        /**
         * Botão para fechar o sistema
         */
        JButton fechar = new JButton("Fechar");
	fechar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {				
	   	        
                System.exit(0);
            }
	});
        
	
        fechar.setBounds(150, 300, 100, 30);
	painel.add(fechar);
    }
}
