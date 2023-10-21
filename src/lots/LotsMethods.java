package lots;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LotsMethods {
	
	private static Connection con = main.Connexion.getConnection();

	public static ArrayList<Integer> getLotsID() {
	    ArrayList<Integer> liste = new ArrayList<>();
	    try {
	        String query = "SELECT DISTINCT idLot FROM lot ORDER BY idLot ASC";
	        PreparedStatement st = con.prepareStatement(query);
	        ResultSet rs = st.executeQuery();
	  	  	while (rs.next()) {
		  	  	Integer item = rs.getInt("idLot");
		        liste.add(item);
	        }
	    } catch (SQLException ex) {
	        con = main.Connexion.getConnection();
	    }
	    return liste;
	}
}
