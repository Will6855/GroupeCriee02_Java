package main;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {
	private static Connection con;
	
	public static void main(String[] args) {
	}
	// Connexion à la base de donnée
	public static void connection() {
		try {
			con = DriverManager.getConnection("jdbc:mysql:///bdd_criee2","root","root");
			System.out.println("La connexion a la base de donnees a reussi.");
		} catch (SQLException e) {
			System.err.println("La connexion a la base de donnees a echoue.");
			e.printStackTrace();
		}
	}
	
	// Récuperer la connexion à la base de donnée
	public static Connection getConnection() {
		connection();
		return con;
	}
}
