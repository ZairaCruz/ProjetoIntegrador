/**
 * Tela de cadastro recebe dados do usuário, chama os métodos e salva
 * no banco de dados
 * 
 */

package Fronteira;

/**
 * @author Antonio, Jhon David, Juliane e Zaira
 */
import Controle.ClienteControle;
import Entidade.Cliente;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;


public class TelaCadastro extends JFrame {
	
    JPanel painel = new JPanel();
    
    /**
     * Método que torna visivel a janela
     * @param args
     */
    public static void main(String[] args) {		
	EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    TelaCadastro janela = new TelaCadastro();
                    janela.setVisible(true);
                    janela.setLocationRelativeTo(null);
                } catch (Exception e) {
                }
            }
	});
    }

    /**
     * Método que contém todos os componentes do painel
     */
    public TelaCadastro() {
	Cliente cliente = new Cliente();
        ClienteControle cli = new ClienteControle();
	
	setTitle("Cadastro de Clientes");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 100, 490, 360);
	painel = new JPanel();
	painel.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(painel);
	painel.setLayout(null);
	
	
        //NOME
	JLabel jLcpf = new JLabel("CPF:");
	jLcpf.setBounds(10, 40, 36, 14);
	painel.add(jLcpf);
	//caixa de texto CPF
	JTextField JTextCpf = new JTextField();	
	JTextCpf.setBounds(48, 40, 200, 20);	
	painel.add(JTextCpf);			
	JTextCpf.setColumns(10);					
        
	//CPF
	JLabel jLnome = new JLabel("Nome:");
	jLnome.setBounds(10, 100, 400, 14);
	painel.add(jLnome);
	//caixa de texto NOME
	JTextField JTextNome = new JTextField();		
	JTextNome.setBounds(48, 100, 400, 20);
	painel.add(JTextNome);
	JTextNome.setColumns(10);	
        
        //label Categoria
	JLabel jLtipo = new JLabel("Tipo:");
	jLtipo.setBounds(10, 160, 100, 14);
	painel.add(jLtipo);
        
	/**
         * Método mostra opções do tipo de cliente
         * e exibe em um jComboBox
         */
	JComboBox JComboTipo = new JComboBox<>();
	JComboTipo.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0){				
		if(JComboTipo.getSelectedItem().toString().equals("Aposentado")){
                    cliente.setTipo("Aposentado");
		}else{
                    if(JComboTipo.getSelectedItem().toString().equals("Funcionário Público")){
			cliente.setTipo("Funcionário Público");
                    }else{
			if(JComboTipo.getSelectedItem().toString().equals("Pensionista")){
                            cliente.setTipo("Pensionista");
			}
                    }
		}
            }
	});
	JComboTipo.setBounds(48, 160, 140, 20);
	JComboTipo.addItem("Aposentado");
        JComboTipo.addItem("Funcionário Público");
	JComboTipo.addItem("Pensionista");
	painel.add(JComboTipo);
	
        	
	//label SALARIO
	JLabel jLsalario = new JLabel("Salário Líquido:");		
	jLsalario.setBounds(200, 160, 97, 14);
	painel.add(jLsalario);
        
	/**
         * Método da caixa de texto do sálario
         * Recebe em texto e converte em float
         */
	JTextField JTextSalario = new JTextField();		
	JTextSalario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
		Cliente.setSalario(Float.parseFloat(JTextSalario.getText()));
            }
	});
	JTextSalario.setBounds(300, 160, 137, 20);
	painel.add(JTextSalario);		
	JTextSalario.setColumns(10);

                
        //Linha que separa dados do cliente dos dados do empréstimo	
	JSeparator separator_1 = new JSeparator();
	separator_1.setBounds(10, 240, 460, 2);
	painel.add(separator_1);
        
        
        ////////////////////////////////////////////Botões
     
        /**
         * Chamada do método pesquisaPorCpf 
         * se houver dados exibe nos compos correspondentes
         */
        JButton pesquisar = new JButton("Pesquisar");
	pesquisar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
		String lsCli = cli.pesquisarPorCpf(JTextCpf.getText());
		String[] dados;				
		dados = lsCli.split(",");				
		JTextCpf.setText(dados[0]);
		JTextNome.setText(dados[1]);
		JComboTipo.setSelectedItem(dados[2]);	
		JTextSalario.setText(dados[3]);
		
            }
	});
	pesquisar.setBounds(350, 40, 95, 23);
	painel.add(pesquisar);
        
        /**
         * Botão Cadastrar
         */
	JButton cadastrar = new JButton("Cadastrar");
	cadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
		
                cliente.setCpf(JTextCpf.getText());
		cliente.setNome(JTextNome.getText());				
		cliente.setTipo((String) JComboTipo.getSelectedItem());					
		Cliente.setSalario(Double.parseDouble(JTextSalario.getText()));

		
                //fazendo a validação dos dados
                if((JTextNome.getText().isEmpty()) || (JTextCpf.getText().isEmpty()) || (JTextSalario.getText().isEmpty())){
                    JOptionPane.showMessageDialog(null, "Preencha os campos vazios");
                }else{
                    cli.insert(cliente);
                    //limpar campos
                    JTextCpf.setText("");
                    JTextNome.setText("");
                    JTextSalario.setText("");
                    JComboTipo.setSelectedItem("");
                 
                }
		
            }
	});
	cadastrar.setBounds(28, 270, 101, 23);
	painel.add(cadastrar);
		
	/**
         * Botão Excluir
         */
	JButton excluir = new JButton("Excluir");
	excluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
		cli.excluirPorCpf(JTextCpf.getText());
                JTextCpf.setText("");
		JTextNome.setText("");
		JTextSalario.setText("");
		JComboTipo.setSelectedItem("");
            }
	});
	excluir.setBounds(145, 270, 89, 23);
	painel.add(excluir);
		
	/**
         * Botão Alterar
         */
	JButton alterar = new JButton("Alterar");
	alterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
		cliente.setCpf(JTextCpf.getText());
		cliente.setNome(JTextNome.getText());				
		cliente.setTipo((String) JComboTipo.getSelectedItem());				
		Cliente.setSalario(Float.parseFloat(JTextSalario.getText()));
		cli.alterarPorCpf(cliente);
		JTextCpf.setText("");
		JTextNome.setText("");
                JTextSalario.setText("");
		JComboTipo.setSelectedItem("");
            }
	});
	alterar.setBounds(250, 270, 89, 23);
	painel.add(alterar);
        
        /**
         * Botão Voltar 
         */
	JButton voltar = new JButton("Voltar");
	voltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
		dispose();
            }
	});
	voltar.setBounds(360, 270, 89, 23);
	painel.add(voltar);
	
    }
}
