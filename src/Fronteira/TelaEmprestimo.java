/*
 * Tela de emprestimo, busca cliente já cadastrado, simula várias formas de
 * empréstimos de acordo com a margem de consignação do cliente, salva no
 * banco de dados.
 */
package Fronteira;

/**
 * @author Antonio, Jhon David, Juliane e Zaira
 */

import Controle.EmprestimoControle;
import Controle.ClienteControle;
import Entidade.Cliente;
import Entidade.Emprestimo;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class TelaEmprestimo extends JFrame {
	
    JPanel painel = new JPanel();

    
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
                    TelaEmprestimo janela = new TelaEmprestimo();
                    janela.setVisible(true);
                    janela.setLocationRelativeTo(null);
                    
		} catch (Exception e) {
		}
            }
	});
    }

    public TelaEmprestimo() {
	Emprestimo emprestimo = new Emprestimo();
	Cliente cliente = new Cliente();
        ClienteControle cli = new ClienteControle();
	EmprestimoControle empCont = new EmprestimoControle();
		
	setTitle("Empréstimos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 100, 660, 530);
	painel.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(painel);
	
        
		
	//cliente
	JLabel jLnome = new JLabel("Nome:");
	jLnome.setBounds(27, 27, 57, 14);
	painel.add(jLnome);
	//caixa de nome
        JTextField JTextNome = new JTextField();
	JTextNome.setBounds(90, 24, 280, 20);
	painel.add(JTextNome);
	JTextNome.setColumns(10);
	
	
		
	//cpf
	JLabel jLcpf = new JLabel("CPF:");
	jLcpf.setBounds(27, 57, 46, 14);
	painel.add(jLcpf);
	//caixa CPF
        JTextField JTextCpf = new JTextField();
	JTextCpf.setBounds(90, 54, 137, 20);
	painel.add(JTextCpf);
	JTextCpf.setColumns(10);
	
	//tipo
	JLabel jLtipo = new JLabel("Tipo:");
	jLtipo.setBounds(27, 91, 67, 14);
	painel.add(jLtipo);
	//caixa CATEGORIA		
        JTextField JTextTipo = new JTextField();
	
	JTextTipo.setBounds(90, 88, 137, 20);
	painel.add(JTextTipo);
	JTextTipo.setColumns(10);
		
	//salário
	JLabel jLsalario = new JLabel("Salário:");
	jLsalario.setBounds(350, 90, 87, 14);
	painel.add(jLsalario);
	//caixa SALÁRIO
        JTextField JTextSalario = new JTextField();
	JTextSalario.setBounds(433, 87, 171, 20);
	painel.add(JTextSalario);
	JTextSalario.setColumns(10);

        JLabel jLnovo = new JLabel("Simular Novo Empréstimo");
	jLnovo.setBounds(260, 200, 180, 30);
	painel.add(jLnovo);
	
	//label
	JLabel jLvalorEmprest = new JLabel("Valor do Empréstimo:");
	jLvalorEmprest.setBounds(27, 260, 130, 14);
	painel.add(jLvalorEmprest);
	//caixa do VALOR do empréstimo
        JTextField JTextValorEmprestimo = new JTextField();
        JTextValorEmprestimo.setBounds(182, 257, 110, 20);
	painel.add(JTextValorEmprestimo);
	JTextValorEmprestimo.setColumns(10);		
		
	//label
	JLabel jLqtdeparcelas = new JLabel("Quantidade de parcelas:");
	jLqtdeparcelas.setBounds(27, 297, 145, 14);
	painel.add(jLqtdeparcelas);
	//combo box de quantidade de PARCELAS
	JComboBox<String> comboBoxQtdeParcelas = new JComboBox<String>();
	comboBoxQtdeParcelas.setModel(new DefaultComboBoxModel<String>(new String[] {"12", "24", "36", "48", "60"}));
	comboBoxQtdeParcelas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
		String mes =  comboBoxQtdeParcelas.getSelectedItem().toString();
                if(mes.equals("12")){
                    emprestimo.setQtdeParcela(12);
                }
                if(mes.equals("24")){
                    emprestimo.setQtdeParcela(24);
                }
                if(mes.equals("36")){
                    emprestimo.setQtdeParcela(36);
                }
                if(mes.equals("48")){
                    emprestimo.setQtdeParcela(48);
		}
                if(mes.equals("60")){
                    emprestimo.setQtdeParcela(60);
                }
            }
        });
	comboBoxQtdeParcelas.setBounds(182, 294, 110, 20);
	painel.add(comboBoxQtdeParcelas);
	
		
        //TAXA
        JLabel jLtaxa = new JLabel("Taxa de Juros:");
	jLtaxa.setBounds(350, 294, 188, 14);
	painel.add(jLtaxa);
        //caixa de valor da taxa
        JTextField JTextTaxa = new JTextField();
        JTextTaxa.setBounds(490, 294, 120, 20);
	painel.add(JTextTaxa);
	JTextTaxa.setColumns(10);
        
        
	//label
	JLabel lblQualTipoDe = new JLabel("Tabela de Amortização");
	lblQualTipoDe.setBounds(350, 260, 188, 14);
	painel.add(lblQualTipoDe);
				
	//combo box de escolha entre Sac e Price 
	JComboBox<String> comboBoxSacOuPrice = new JComboBox<String>();
	comboBoxSacOuPrice.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
		String tipoEmprestimo =  comboBoxSacOuPrice.getSelectedItem().toString();
		if(tipoEmprestimo.equals("Sac")){
                	emprestimo.setTabela("Sac");
		}
		else{
                    if(tipoEmprestimo.equals("Price")){
			emprestimo.setTabela("Price");
                    }
		}
            }
	});
	comboBoxSacOuPrice.setBounds(490, 257, 120, 20);
	comboBoxSacOuPrice.addItem("Sac");
	comboBoxSacOuPrice.addItem("Price");
	painel.add(comboBoxSacOuPrice);
        
        
         
	JLabel jLpossui = new JLabel("Possui empréstimos anteriores?");
	jLpossui.setBounds(27, 150, 200, 14);
	painel.add(jLpossui);
	//resposta SIM ou NÃO
	JComboBox comboBoxSouN = new JComboBox();
	
	comboBoxSouN.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
		String possuiEmpAnt =  comboBoxSouN.getSelectedItem().toString();
		if(possuiEmpAnt.equals("Sim")){
                	cliente.setEmprestFeito("Sim");
		}
		else{
                    if(possuiEmpAnt.equals("Não")){
			cliente.setEmprestFeito("Não");
                    }
		}
            }
	});
	comboBoxSouN.setBounds(225, 150, 60, 20);
	comboBoxSouN.addItem("Sim");
	comboBoxSouN.addItem("Não");
	painel.add(comboBoxSouN);
        
        
	
        
        JLabel jLparcAnt = new JLabel("Valor parcelas: ");
	jLparcAnt.setBounds(300, 150, 100, 14);
	painel.add(jLparcAnt);
	//valor do EMPREST JÁ realizado
	JTextField JTextVlEmpAnt = new JTextField();
	JTextVlEmpAnt.setText("0");
	JTextVlEmpAnt.setBounds(400, 150, 120, 20);
	painel.add(JTextVlEmpAnt);
	JTextVlEmpAnt.setColumns(10);
        Cliente.setEmprestAnt(Double.parseDouble(JTextVlEmpAnt.getText()));
	if("Sim".equals(cliente.getEmprestFeito())) {
            JTextVlEmpAnt.setEditable(true);
            Cliente.setEmprestAnt(Double.parseDouble(JTextVlEmpAnt.getText()));
	}else{
            if("Não".equals(cliente.getEmprestFeito())){
                JTextVlEmpAnt.setEditable(false);
            }
	}
        
        //numero
        JLabel jLnumero = new JLabel("Número de confirmação:");
	jLnumero.setBounds(27, 343, 150, 14);
	painel.add(jLnumero);
        //caixa de texto numero do emprestimo
	JTextField JTextNumero = new JTextField();
	JTextNumero.setText(" ");
	JTextNumero.setBounds(182, 340, 110, 20);
	painel.add(JTextNumero);
	JTextNumero.setColumns(10);
			
        //Linha que separa dados do cliente dos dados do empréstimo	
	JSeparator separator = new JSeparator();
	separator.setBounds(25, 126, 580, 13);
	painel.add(separator);
        JSeparator separator2 = new JSeparator();
	separator2.setBounds(27, 190, 580, 2);
	painel.add(separator2); 
	JSeparator separator3 = new JSeparator();
	separator3.setBounds(27, 380, 580, 2);
	painel.add(separator3); 
       
        //////////////////////////////////////////Botões
        
        //botão confirmar emprestimo anterior
	JButton ok  = new JButton("Ok");
	ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
		Cliente.setEmprestAnt(Double.parseDouble(JTextVlEmpAnt.getText()));
            }
	});
	ok.setBounds(540, 150, 60, 23);
	painel.add(ok);
        
        //botão PESQUISAR por nome		
	JButton pesquisar = new JButton("Pesquisar");
	pesquisar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
		String lsCli = cli.pesquisarPorNome(JTextNome.getText());
		String[] dados;
                dados = lsCli.split(",");
                JTextNome.setText(dados[1]);
		JTextCpf.setText(dados[0]);
		JTextTipo.setText(dados[2]);
		JTextSalario.setText(dados[3]);
		
            }
	});
	pesquisar.setBounds(485, 23, 120, 23);
	painel.add(pesquisar);
        
        //Botão SIMULAR empréstimo 
	JButton simular = new JButton("Simular");
	simular.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
		cliente.setNome(JTextNome.getText());
		cliente.setCpf(JTextCpf.getText());
		cliente.setTipo(JTextTipo.getText());
		Cliente.setSalario(Float.parseFloat(JTextSalario.getText()));
		emprestimo.setValorEmprestimo(Double.parseDouble(JTextValorEmprestimo.getText()));									
		emprestimo.setQtdeParcela(Integer.parseInt((String)(comboBoxQtdeParcelas.getSelectedItem())));					
		emprestimo.setTabela((String) comboBoxSacOuPrice.getSelectedItem());	
                Cliente.setEmprestAnt(Double.parseDouble(JTextVlEmpAnt.getText()));
                Emprestimo.setTaxa(Double.parseDouble(JTextTaxa.getText()));
                
		if (comboBoxSacOuPrice.getSelectedItem() == "Sac"){
                    Sac sac = new Sac(emprestimo);
                    sac.setVisible(true);
                    sac.tabelaSac();
		}else{
                    if(comboBoxSacOuPrice.getSelectedItem() == "Price"){
			Price price = new Price(emprestimo);
			price.setVisible(true);
			price.tabelaPrice();
                    }
		}
            }
	});		
	simular.setBounds(350, 335, 95, 23);
	painel.add(simular);
				
	//Botão SALVAR        
	JButton salvar = new JButton("Salvar");
	salvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
		cliente.setCpf(JTextCpf.getText());
                emprestimo.setNumero(JTextNumero.getText());
		emprestimo.setValorEmprestimo(Double.parseDouble(JTextValorEmprestimo.getText()));
		emprestimo.setQtdeParcela(Integer.parseInt((String) comboBoxQtdeParcelas.getSelectedItem()));
                Emprestimo.setTaxa(Double.parseDouble(JTextTaxa.getText()));
         
		if(comboBoxSacOuPrice.getSelectedItem() == "Sac"){
                    emprestimo.setValorParcela(EmprestimoControle.calculaAmortizacaoSac(Integer.parseInt((String) comboBoxQtdeParcelas.getSelectedItem()), Double.parseDouble(JTextValorEmprestimo.getText())));
		}else{
                    if(comboBoxSacOuPrice.getSelectedItem() == "Price"){
                        emprestimo.setValorParcela(EmprestimoControle.calculaParcelaPrice(Double.parseDouble(JTextValorEmprestimo.getText()), Emprestimo.getTaxa(), emprestimo.getQtdeParcela()));
                    }
                }
                empCont.inserirEmprestimo(emprestimo, cliente);				
               

                JTextNome.setText("");
                JTextCpf.setText("");
                JTextSalario.setText("");
                JTextValorEmprestimo.setText("");
                JTextTaxa.setText("");
            }
	});
	salvar.setBounds(512, 335, 95, 23);
	painel.add(salvar);
        
		
        //VOLTAR
	JButton voltar = new JButton("Voltar");
	voltar.setBounds(260, 415, 95, 23); 
	voltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
		dispose();
            }
	});
	painel.setLayout(null);
	painel.add(voltar);
    }	

}
