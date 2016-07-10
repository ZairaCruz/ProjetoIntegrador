/*
 * Classe modelo, entidade Cliente
 */
package Entidade;

/**
 *
 * @author Antonio, Jhon David, Juliane e Zaira
 */
public class Cliente {
    private String cpf;
    private String nome;
    private String tipo;
    private static double salario;
    private String emprestFeito;
    private static double emprestAnt;

    
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public static double getSalario() {
        return salario;
    }

    public static void setSalario(double salario) {
        Cliente.salario = salario;
    }
    
    public String getEmprestFeito() {
        return emprestFeito;
    }

    public void setEmprestFeito(String emprestFeito) {
        this.emprestFeito = emprestFeito;
    }

    public static double getEmprestAnt() {
        return emprestAnt;
    }

    public static void setEmprestAnt(double emprestAnt) {
        Cliente.emprestAnt = emprestAnt;
    }


    @Override
	public String toString() {
            return cpf + "," + nome + ","
		+ tipo + "," + salario;
	}
}
