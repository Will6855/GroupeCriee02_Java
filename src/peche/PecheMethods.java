package peche;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
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

	
	public static boolean addPeche(Integer idBateau) {
		if (idBateau > 0) {
			try {
			    String query = "INSERT INTO peche(datePeche, idBateau) VALUES(?, ?)";
			    PreparedStatement st = con.prepareStatement(query);
			    Calendar currenttime = Calendar.getInstance();
			    Date sqldate = new Date((currenttime.getTime()).getTime());
			    st.setDate(1, sqldate);
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
	
}
