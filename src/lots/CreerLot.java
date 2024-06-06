package lots;
import lots.*;
import lots.LotsMethods;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import main.*;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import bacs.*;
import main.*;
import peche.*;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

import main.Item;
import peche.GestionPecheVet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JComboBox;

import java.awt.event.ActionEvent;

import main.Item;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SwingConstants;

public class CreerLot extends JFrame {
	private JComboBox<Item<Integer>> comboBoxEspece;
	private JComboBox<Item<Integer>> comboBoxTaille;
	private JComboBox<Item<String>> comboBoxQualite;
	private JComboBox<Item<String>> comboBoxPresentation;
	private JComboBox<Item<Integer>> comboBoxBateau;

	private JPanel contentPane;
	private static final long serialVersionUID = 1L;
	private static DefaultTableModel model;
	private JTable table;
	private JScrollPane JScroll;
	private JButton btnVoirLots;
	private JButton btnSupprimer;
	private JButton btnRetour;
	
	
	private static JDatePickerImpl datePicker;
	
	
	

	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreerLot frame = new CreerLot();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	

	/**
	 * Create the frame.
	 */
	
	public static void updateTable() {
		model.setRowCount(0);
		@SuppressWarnings("unchecked")
		Item<Integer> selectedItem = (Item<Integer>)comboBoxBateau.getSelectedItem();
    	Integer idBateau = selectedItem.getValue();
    	java.sql.Date selectedDate = (java.sql.Date) datePicker.getModel().getValue();
		ArrayList<HashMap<String, Object>> allLot = lots.LotsMethods.getAllLot(idBateau, selectedDate);
		for (HashMap<String, Object> row : allLot) {
			Vector<Object> rowData = new Vector<>();
			rowData.add(row.get("id"));		
			
			rowData.add(row.get("nom"));
			rowData.add(lots.LotsMethods.getNumLot((Integer)row.get("idBateau"), (Integer)row.get("idLot")));
			rowData.add(row.get("idTypeBac"));
			rowData.add(row.get("nomCourt"));
			rowData.add(row.get("idTaille"));
			rowData.add(row.get("idQualite"));
			rowData.add(row.get("idPresentation"));
			rowData.add(row.get("poidsBrutLot"));
			model.addRow(rowData);
		}
	}
	
	
	public CreerLot(Integer idBateau, java.sql.Date selectedDate) {

		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 181, 390);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		JLabel lblBateau = new JLabel("Bateau :");
		lblBateau.setBounds(5, 5, 156, 21);
		lblBateau.setHorizontalAlignment(SwingConstants.CENTER);
		
		comboBoxBateau = new JComboBox<Item<Integer>>();
		comboBoxBateau.setBounds(5, 26, 156, 21);
		comboBoxBateau.setToolTipText("Sélectionnez un bateau");
		ArrayList<HashMap<String, Object>> allPeche = peche.PecheMethods.getAllPeche(selectedDate);
		comboBoxBateau.addItem(new Item<Integer>(-1, "Tous les bateaux"));
		for (HashMap<String, Object> row : allPeche) {
			Item<Integer> item = new Item<Integer>((Integer)row.get("idBateau"), (String)row.get("nom"));
		    comboBoxBateau.addItem(item);
		    if (idBateau == row.get("idBateau")) {
			    comboBoxBateau.setSelectedItem(item);
		    }
		}
		 
		comboBoxBateau.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	if (comboBoxBateau.isPopupVisible()) {
		    		updateTable();
		    	}
		    }
		});
		
		JLabel label = new JLabel("");
		label.setBounds(5, 47, 156, 21);
		
		JLabel label_1 = new JLabel("");
		label_1.setBounds(5, 110, 156, 21);
		
		JLabel label_2 = new JLabel("");
		label_2.setBounds(5, 173, 156, 21);
		
		JLabel label_3 = new JLabel("");
		label_3.setBounds(5, 236, 156, 21);
		
		JLabel label_4 = new JLabel("");
		label_4.setBounds(5, 299, 156, 21);
		contentPane.setLayout(null);
		contentPane.add(lblBateau);
		contentPane.add(comboBoxBateau);
		contentPane.add(label);
		
				comboBoxEspece = new JComboBox<Item<Integer>>();
				comboBoxEspece.setBounds(5, 89, 156, 21);
				comboBoxEspece.setToolTipText("Sélectionnez une espèce");
				ArrayList<HashMap<String, Object>> allEspece = main.MainMethods.getAllEspece();
				comboBoxEspece.addItem(new Item<Integer>(-1, ""));
				for (HashMap<String, Object> row : allEspece) {
					comboBoxEspece.addItem(new Item<Integer>((Integer) row.get("id"), (String) row.get("nom")));
				}
						// Ajout d'une JComboBox pour Espece (de type Int)
						JLabel lblEspece = new JLabel("Espece :");
						lblEspece.setBounds(5, 68, 156, 21);
						lblEspece.setHorizontalAlignment(SwingConstants.CENTER);
						contentPane.add(lblEspece);
						contentPane.add(comboBoxEspece);
		contentPane.add(label_1);
		
	    		comboBoxTaille = new JComboBox<Item<Integer>>();
				comboBoxTaille.setBounds(5, 152, 156, 21);
				comboBoxTaille.setToolTipText("Sélectionnez une taille");
				ArrayList<HashMap<String, Object>> allTaille = main.MainMethods.getAllTaille();
				comboBoxTaille.addItem(new Item<Integer>(-1, ""));
				for (HashMap<String, Object> row : allTaille) {
					comboBoxTaille.addItem(new Item<Integer>((Integer) row.get("id"), (String) row.get("specification")));
				}
						
								// Ajout d'une JComboBox pour Qualite (de type String)
								JLabel lblQualite = new JLabel("Qualite :");
								lblQualite.setBounds(5, 131, 156, 21);
								lblQualite.setHorizontalAlignment(SwingConstants.CENTER);
								contentPane.add(lblQualite);
						contentPane.add(comboBoxTaille);
		contentPane.add(label_2);
		
	    		comboBoxQualite = new JComboBox<Item<String>>();
				comboBoxQualite.setBounds(5, 215, 156, 21);
				comboBoxQualite.setToolTipText("Sélectionnez une qualité");
				ArrayList<HashMap<String, Object>> allQualite = main.MainMethods.getAllQualite();
				comboBoxQualite.addItem(new Item<String>("", ""));
				for (HashMap<String, Object> row : allQualite) {
					comboBoxQualite.addItem(new Item<String>((String) row.get("id"), (String) row.get("libelle")));
				}
						
								// Ajout d'une JComboBox pour Taille (de type Int)
								JLabel lblTaille = new JLabel("Taille :");
								lblTaille.setBounds(5, 194, 156, 21);
								lblTaille.setHorizontalAlignment(SwingConstants.CENTER);
								contentPane.add(lblTaille);
						contentPane.add(comboBoxQualite);
		contentPane.add(label_3);
				
						// Ajout d'une JComboBox pour Presentation (de type String)

		
	    		comboBoxPresentation = new JComboBox<Item<String>>();
				comboBoxPresentation.setBounds(5, 278, 156, 21);
				comboBoxPresentation.setToolTipText("Sélectionnez une présentation");
				ArrayList<HashMap<String, Object>> allPresentation = main.MainMethods.getAllPresentation();
				comboBoxPresentation.addItem(new Item<String>("", ""));
				for (HashMap<String, Object> row : allPresentation) {
					comboBoxPresentation.addItem(new Item<String>((String) row.get("id"), (String) row.get("libelle")));
				}
				JLabel lblPresentation = new JLabel("Presentation :");
				lblPresentation.setBounds(5, 257, 156, 21);
				lblPresentation.setHorizontalAlignment(SwingConstants.CENTER);
				contentPane.add(lblPresentation);
			contentPane.add(comboBoxPresentation);
		contentPane.add(label_4);

		
		btnRetour = new JButton("Retour");
		btnRetour.setBounds(5, 320, 63, 21);
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();

			}
		});
		
		btnRetour.setForeground(Color.WHITE);
		btnRetour.setBackground(new Color(255,0,51));
		contentPane.add(btnRetour);
		
		
		
		JButton Valider = new JButton("Valider");
		Valider.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	enregistrerLot();
		        
		    }
		});
		Valider.setBounds(94, 320, 63, 21);
		contentPane.add(Valider);
	}
	public void enregistrerLot() {
	    try {
	        // Récupérer les valeurs sélectionnées dans les combobox
	        Item<Integer> selectedItemBateau = (Item<Integer>) comboBoxBateau.getSelectedItem();
	        Integer idBateau = selectedItemBateau.getValue();
	        Item<Integer> selectedItemEspece = (Item<Integer>) comboBoxEspece.getSelectedItem();
	        Integer idEspece = selectedItemEspece.getValue();
	        Item<Integer> selectedItemTaille = (Item<Integer>) comboBoxTaille.getSelectedItem();
	        Integer idTaille = selectedItemTaille.getValue();
	        Item<String> selectedItemQualite = (Item<String>) comboBoxQualite.getSelectedItem();
	        String idQualite = selectedItemQualite.getValue();
	        Item<String> selectedItemPresentation = (Item<String>) comboBoxPresentation.getSelectedItem();
	        String idPresentation = selectedItemPresentation.getValue();

	        // Connexion à la base de données
	        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdd_criee2", "root", "");

	        // Requête SQL pour insérer les données dans la table "lot"
	        String query = "INSERT INTO lot (datePeche, idBateau, idEspece, idTaille, idQualite, idPresentation) VALUES (?, ?, ?, ?, ?, ?)";
	        PreparedStatement preparedStmt = conn.prepareStatement(query);
	        preparedStmt.setDate(1, new java.sql.Date(new Date().getTime())); // Date actuelle
	        preparedStmt.setInt(2, idBateau);
	        preparedStmt.setInt(3, idEspece);
	        preparedStmt.setInt(4, idTaille);
	        preparedStmt.setString(5, idQualite);
	        preparedStmt.setString(6, idPresentation);

	        // Exécuter la requête
	        preparedStmt.execute();

	        // Fermer la connexion
	        conn.close();

	        // Afficher un message de succès
	        JOptionPane.showMessageDialog(null, "Lot enregistré avec succès !");
	    } catch (SQLException ex) {
	        // Gérer les erreurs de la base de données
	        ex.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Erreur lors de l'enregistrement du lot : " + ex.getMessage());
	    }
	}

	
	
}

