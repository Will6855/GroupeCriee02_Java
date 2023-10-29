package peche;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;


public class PecheMethods {
	
	private static Connection con = main.Connexion.getConnection();

	public static ArrayList<HashMap<String, Object>> getBateaux() {
	    ArrayList<HashMap<String, Object>> liste = new ArrayList<>();
	    try {
	        String query = "SELECT * FROM bateau";
	        PreparedStatement st = con.prepareStatement(query);
	        ResultSet rs = st.executeQuery();
	        java.sql.ResultSetMetaData rsmd = rs.getMetaData();
	        while (rs.next()) {
	            HashMap<String, Object> row = new HashMap<>();
	            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
	                String columnName = rsmd.getColumnName(i);
	                Object columnValue = rs.getObject(i);
	                row.put(columnName, columnValue);
	            }
	            liste.add(row);
	        }
	    } catch (SQLException ex) {
	        con = main.Connexion.getConnection();
	    }
	    return liste;
	}

	public static boolean verifPeche(Integer idBateau, Date selectedDate) {
		if (idBateau > 0) {
			try {
			    String query = "SELECT idBateau FROM peche WHERE datePeche = ? AND idBateau = ?";
			    PreparedStatement st = con.prepareStatement(query);
			    st.setDate(1, selectedDate);
				st.setInt(2, idBateau);
			    ResultSet rs = st.executeQuery();
			    if (rs.next()) {
			    	return true;
			    }
			} catch (SQLException ex) {
				con = main.Connexion.getConnection();
			}
		}
		return false;
	}
	
	public static boolean addPeche(Integer idBateau, Date selectedDate) {
		try {
		    String query = "INSERT INTO peche(datePeche, idBateau) VALUES(?, ?)";
		    PreparedStatement st = con.prepareStatement(query);
		    st.setDate(1, selectedDate);
			st.setInt(2, idBateau);
		    int rs = st.executeUpdate();
		    if (rs > 0) {
		    	return true;
		    }
		} catch (SQLException ex) {
			con = main.Connexion.getConnection();
		}
		return false;
	}
	
	public static boolean delPeche(Integer idBateau, Date selectedDate) {
		if (idBateau > 0) {
			try {
			    String query = "DELETE FROM peche WHERE datePeche = ? AND idBateau = ?";
			    PreparedStatement st = con.prepareStatement(query);
			    st.setDate(1, selectedDate);
				st.setInt(2, idBateau);
			    int rs = st.executeUpdate();
			    if (rs > 0) {
			    	return true;
			    }
			} catch (SQLException ex) {
				con = main.Connexion.getConnection();
			}
		}
		return false;
	}
	
	public static ArrayList<HashMap<String, Object>> getAllPeche(Date selectedDate) {
		ArrayList<HashMap<String, Object>> liste = new ArrayList<HashMap<String, Object>>();
		try {
		    String query = "SELECT nom, immatriculation, idBateau FROM peche "
		    		+ "INNER JOIN bateau ON bateau.id = peche.idBateau "
		    		+ "WHERE datePeche = ?";
		    PreparedStatement st = con.prepareStatement(query);
		    st.setDate(1, selectedDate);
		    ResultSet rs = st.executeQuery();
	        ResultSetMetaData rsmd = rs.getMetaData();
	        while (rs.next()) {
	            HashMap<String, Object> row = new HashMap<>();
	            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
	                String columnName = rsmd.getColumnName(i);
	                Object columnValue = rs.getObject(i);
	                row.put(columnName, columnValue);
	            }
	            liste.add(row);
	        }
		} catch (SQLException ex) {
			con = main.Connexion.getConnection();
		}
		return liste;
	}
	
}
