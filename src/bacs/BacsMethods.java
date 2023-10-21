package bacs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BacsMethods {
	
	private static Connection con = main.Connexion.getConnection();
	
	public static ArrayList<Integer> getBacsIdByLotId(Integer idLot) {
	    ArrayList<Integer> liste = new ArrayList<>();
	    try {
	        String query = "SELECT DISTINCT idBac FROM bac WHERE idLot = ? ORDER BY idBac ASC";
	        PreparedStatement st = con.prepareStatement(query);
	        st.setInt(1, idLot);
	        ResultSet rs = st.executeQuery();
	  	  	while (rs.next()) {
		  	  	Integer item = rs.getInt("idBac");
		        liste.add(item);
	        }
	    } catch (SQLException ex) {
	        con = main.Connexion.getConnection();
	    }
	    return liste;
	}
	
	public static boolean addPoidsBac(Integer idBac, Integer idLot, Float poids) {
		try {
		    String query = "UPDATE bac SET poids = ? WHERE idBac = ? AND idLot = ?";
		    PreparedStatement st = con.prepareStatement(query);
		    st.setFloat(1, poids);
			st.setInt(2, idBac);
			st.setInt(3, idLot);
		    int rs = st.executeUpdate();
		    if (rs > 0) {
		    	return true;
		    }
		} catch (SQLException ex) {
			con = main.Connexion.getConnection();
		}
		return false;
	}
	
	public static Float getPoidsBac(Integer idBac, Integer idLot) {
		try {
		    String query = "SELECT poids FROM bac WHERE idBac = ? AND idLot = ?";
		    PreparedStatement st = con.prepareStatement(query);
			st.setInt(1, idBac);
			st.setInt(2, idLot);
			ResultSet rs = st.executeQuery();
	  	  	while (rs.next()) {
		  	  	Float poids = rs.getFloat("poids");
		  	  	return poids;
	        }
		} catch (SQLException ex) {
			con = main.Connexion.getConnection();
		}
		return null;
	}
}
