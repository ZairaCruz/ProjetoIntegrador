/*
 * Classe que fará a interface com o driver JDBC de conexão com o 
 * banco de dados Postgres.
 */
package Factory;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Antonio, Jhon David, Juliane e Zaira
 */
public class ConnectionFactory {

    private static String drive = "org.postgresql.Driver";
    private static String url = "jdbc:postgresql://localhost:5432/casabancaria";
    private static String usuario = "postgres";
    private static String senha = "senac";
	
    public static Connection getConnection(){
	try {
            Class.forName(drive);
            return DriverManager.getConnection(url, usuario, senha);
	} catch (Exception e){
            System.err.println("Erro de conexão");
            e.printStackTrace();
	}
	return null;
    }
		
}