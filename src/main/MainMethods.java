package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class MainMethods {
	
	private static Connection con = main.Connexion.getConnection();

	public static ArrayList<HashMap<String, Object>> getAllEspece() {
		ArrayList<HashMap<String, Object>> liste = new ArrayList<HashMap<String, Object>>();
		try {
		    String query = "SELECT id, nom, nomScientifique, nomCourt FROM espece";
		    PreparedStatement st = con.prepareStatement(query);
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
	
	public static ArrayList<HashMap<String, Object>> getAllTaille() {
		ArrayList<HashMap<String, Object>> liste = new ArrayList<HashMap<String, Object>>();
		try {
		    String query = "SELECT id, specification FROM taille";
		    PreparedStatement st = con.prepareStatement(query);
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
	
	public static ArrayList<HashMap<String, Object>> getAllQualite() {
		ArrayList<HashMap<String, Object>> liste = new ArrayList<HashMap<String, Object>>();
		try {
		    String query = "SELECT id, libelle FROM qualite";
		    PreparedStatement st = con.prepareStatement(query);
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
	
	public static ArrayList<HashMap<String, Object>> getAllPresentation() {
		ArrayList<HashMap<String, Object>> liste = new ArrayList<HashMap<String, Object>>();
		try {
		    String query = "SELECT id, libelle FROM presentation";
		    PreparedStatement st = con.prepareStatement(query);
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

	public static ArrayList<HashMap<String, Object>> getAllTypeBac() {
		ArrayList<HashMap<String, Object>> liste = new ArrayList<HashMap<String, Object>>();
		try {
		    String query = "SELECT id, tare FROM typebac";
		    PreparedStatement st = con.prepareStatement(query);
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
