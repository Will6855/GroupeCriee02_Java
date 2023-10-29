package lots;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class LotsMethods {
	
	private static Connection con = main.Connexion.getConnection();

	public static Boolean addPoidsLot(Integer idLot, Float poidsLot) {
		try {
		    String query = "UPDATE lot SET poidsBrutLot = ? WHERE id = ?";
		    PreparedStatement st = con.prepareStatement(query);
		    st.setFloat(1, poidsLot);
			st.setInt(2, idLot);
		    int rs = st.executeUpdate();
		    if (rs > 0) {
		    	return true;
		    }
		} catch (SQLException ex) {
			con = main.Connexion.getConnection();
		}
		return false;
	}

	public static Float getPoidsLot(Integer idLot) {
		try {
		    String query = "SELECT poidsBrutLot FROM lot WHERE id = ?";
		    PreparedStatement st = con.prepareStatement(query);
			st.setInt(1, idLot);
			ResultSet rs = st.executeQuery();
	  	  	while (rs.next()) {
		  	  	Float poids = rs.getFloat("poidsBrutLot");
		  	  	return poids;
	        }
		} catch (SQLException ex) {
			con = main.Connexion.getConnection();
		}
		return null;
	}
	
	public static ArrayList<HashMap<String, Object>> getAllLot(Integer idBateau, Date selectedDate) {
		ArrayList<HashMap<String, Object>> liste = new ArrayList<HashMap<String, Object>>();
		try {
			PreparedStatement st;
			if (idBateau >= 0) {
				String query = "SELECT b.nom, l.idBateau, l.idTypeBac, l.idespece, e.nomCourt, l.idtaille, l.idQualite, l.idpresentation, l.poidsBrutLot, l.idLot, l.id FROM lot AS l "
						+ "INNER JOIN bateau AS b ON b.id = l.idBateau "
						+ "INNER JOIN espece AS e ON e.id = l.idespece "
			    		+ "WHERE datePeche = ? AND idbateau = ? "
			    		+ "ORDER BY b.nom, l.idLot";
			    st = con.prepareStatement(query);
			    st.setDate(1, selectedDate);
			    st.setInt(2, idBateau);
			} else {
				String query = "SELECT b.nom, l.idBateau, l.idTypeBac, l.idespece, e.nomCourt, l.idtaille, l.idQualite, l.idpresentation, l.poidsBrutLot, l.idLot, l.id FROM lot AS l "
						+ "INNER JOIN bateau AS b ON b.id = l.idBateau "
						+ "INNER JOIN espece AS e ON e.id = l.idespece "
			    		+ "WHERE datePeche = ? "
			    		+ "ORDER BY b.nom, l.idLot";
			    st = con.prepareStatement(query);
			    st.setDate(1, selectedDate);
			}
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
	
	public static String getNumLot(Integer idBateau, Integer idLot) {
		String formattedIdLot = String.format("%03d", idLot);
		String formattedIdbateau = String.format("%02d", idBateau);
		String numLot = formattedIdbateau + formattedIdLot;
		return numLot;
	}

	public static boolean delLot(Integer id) {
		if (id >= 0) {
			try {
			    String query = "DELETE FROM lot WHERE id = ?";
			    PreparedStatement st = con.prepareStatement(query);
				st.setInt(1, id);
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
	
	public static Integer getNbreLotByBateau(Integer idBateau, Date selectedDate) {
		try {
		    String query = "SELECT COUNT(id) AS nbLot FROM lot WHERE datePeche = ? AND idBateau = ?";
		    PreparedStatement st = con.prepareStatement(query);
		    st.setDate(1, selectedDate);
			st.setInt(2, idBateau);
			ResultSet rs = st.executeQuery();
	  	  	while (rs.next()) {
		  	  	Integer nbLots = rs.getInt("nbLot");
		  	  	return nbLots;
	        }
		} catch (SQLException ex) {
			con = main.Connexion.getConnection();
		}
		return null;
	}

	public static boolean addLot(Date selectedDate, Integer idBateau, String idTypeBac, Integer idEspece, Integer idTaille, String idQualite, String idPresentation) {
		Integer idLot = getNbreLotByBateau(idBateau, selectedDate)+1;
		try {
		    String query = "INSERT INTO lot(datePeche, idBateau, idEspece, idTaille, idQualite, idPresentation, idLot, idTypeBac) "
		    		+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		    PreparedStatement st = con.prepareStatement(query);
		    st.setDate(1, selectedDate);
			st.setInt(2, idBateau);
			st.setInt(3, idEspece);
			st.setInt(4, idTaille);
			st.setString(5, idQualite);
			st.setString(6, idPresentation);
			st.setInt(7, idLot);
			st.setString(8, idTypeBac);
		    int rs = st.executeUpdate();
		    if (rs > 0) {
		    	return true;
		    }
		} catch (SQLException ex) {
			con = main.Connexion.getConnection();
		}
		return false;
	}
}
