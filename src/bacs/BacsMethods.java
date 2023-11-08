package bacs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BacsMethods {
	
	private static Connection con = main.Connexion.getConnection();
	
	public static Boolean addPoidsBac(Integer idBac, Float poidsBac) {
		try {
		    String query = "UPDATE bac SET poidsBrutBac = ? WHERE id = ?";
		    PreparedStatement st = con.prepareStatement(query);
		    st.setFloat(1, poidsBac);
			st.setInt(2, idBac);
		    int rs = st.executeUpdate();
		    if (rs > 0) {
		    	return true;
		    }
		} catch (SQLException ex) {
			con = main.Connexion.getConnection();
		}
		return false;
	}
	
	public static Float getPoidsBac(Integer idBac) {
		try {
		    String query = "SELECT poidsBrutBac FROM bac WHERE id = ?";
		    PreparedStatement st = con.prepareStatement(query);
			st.setInt(1, idBac);
			ResultSet rs = st.executeQuery();
	  	  	while (rs.next()) {
		  	  	Float poids = rs.getFloat("poidsBrutBac");
		  	  	return poids;
	        }
		} catch (SQLException ex) {
			con = main.Connexion.getConnection();
		}
		return null;
	}
}
