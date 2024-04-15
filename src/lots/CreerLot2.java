package lots;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.Item;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;


public class CreerLot extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;


	public CreerLot(Integer idBateau, final Date selectedDate) {		
		setResizable(false);
		setTitle("Ajout Lot");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 247, 453);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitre = new JLabel("Nouveau lot");
		lblTitre.setFont(new Font("Dialog", Font.BOLD, 15));
		lblTitre.setBounds(71, 24, 172, 21);
		contentPane.add(lblTitre);
		
		JLabel lblBateau = new JLabel("Sélectionnez le bateau :");
		lblBateau.setBounds(33, 57, 172, 16);
		contentPane.add(lblBateau);
		
		final JComboBox<Item<Integer>> comboBoxBateau = new JComboBox<Item<Integer>>();
		comboBoxBateau.setToolTipText("Sélectionnez un bateau");
		ArrayList<HashMap<String, Object>> allPeche = peche.PecheMethods.getAllPeche(selectedDate);
		comboBoxBateau.addItem(new Item<Integer>(-1, ""));
		for (HashMap<String, Object> row : allPeche) {
			Item<Integer> item = new Item<Integer>((Integer)row.get("idBateau"), (String)row.get("nom"));
		    comboBoxBateau.addItem(item);
		    if (idBateau == row.get("idBateau")) {
			    comboBoxBateau.setSelectedItem(item);
		    }
		}
		comboBoxBateau.setBounds(33, 75, 172, 21);
		contentPane.add(comboBoxBateau);
		
		JLabel lblTypeBac = new JLabel("Sélectionnez le type de bac :");
		lblTypeBac.setBounds(33, 108, 172, 16);
		contentPane.add(lblTypeBac);
		
		final JComboBox<Item<String>> comboBoxTypeBac = new JComboBox<Item<String>>();
		comboBoxTypeBac.setToolTipText("Sélectionnez une type de bac");
		ArrayList<HashMap<String, Object>> allTypeBac = main.MainMethods.getAllTypeBac();
		comboBoxTypeBac.addItem(new Item<String>("", ""));
		for (HashMap<String, Object> row : allTypeBac) {
			Item<String> item = new Item<String>((String)row.get("id"), (String)row.get("id") + " / " + (String)row.get("tare").toString() + "Kg");
			comboBoxTypeBac.addItem(item);
		}
		comboBoxTypeBac.setBounds(33, 126, 172, 21);
		contentPane.add(comboBoxTypeBac);
		
		JLabel lblEspece = new JLabel("Sélectionnez l'espèce :");
		lblEspece.setBounds(33, 159, 172, 16);
		contentPane.add(lblEspece);
		
		final JComboBox<Item<Integer>> comboBoxEspece = new JComboBox<Item<Integer>>();
		comboBoxEspece.setToolTipText("Sélectionnez une espèce");
		ArrayList<HashMap<String, Object>> allEspece = main.MainMethods.getAllEspece();
		comboBoxEspece.addItem(new Item<Integer>(-1, ""));
		for (HashMap<String, Object> row : allEspece) {
			Item<Integer> item = new Item<Integer>((Integer)row.get("id"), (String)row.get("nom"));
			comboBoxEspece.addItem(item);
		}
		comboBoxEspece.setBounds(33, 177, 172, 21);
		contentPane.add(comboBoxEspece);
		
		JLabel lblTaille = new JLabel("Sélectionnez la taille :");
		lblTaille.setBounds(33, 210, 172, 16);
		contentPane.add(lblTaille);
		
		final JComboBox<Item<Integer>> comboBoxTaille = new JComboBox<Item<Integer>>();
		comboBoxTaille.setToolTipText("Sélectionnez une taille");
		ArrayList<HashMap<String, Object>> allTaille = main.MainMethods.getAllTaille();
		comboBoxTaille.addItem(new Item<Integer>(-1, ""));
		for (HashMap<String, Object> row : allTaille) {
			Item<Integer> item = new Item<Integer>((Integer)row.get("id"), (String)row.get("specification"));
			comboBoxTaille.addItem(item);
		}
		comboBoxTaille.setBounds(33, 228, 172, 21);
		contentPane.add(comboBoxTaille);
		
		JLabel lblQualite = new JLabel("Sélectionnez la qualité :");
		lblQualite.setBounds(33, 261, 172, 16);
		contentPane.add(lblQualite);
		
		final JComboBox<Item<String>> comboBoxQualite = new JComboBox<Item<String>>();
		comboBoxQualite.setToolTipText("Sélectionnez une qualité");
		ArrayList<HashMap<String, Object>> allQualite = main.MainMethods.getAllQualite();
		comboBoxQualite.addItem(new Item<String>("", ""));
		for (HashMap<String, Object> row : allQualite) {
			Item<String> item = new Item<String>((String)row.get("id"), (String)row.get("libelle"));
			comboBoxQualite.addItem(item);
		}
		comboBoxQualite.setBounds(33, 279, 172, 21);
		contentPane.add(comboBoxQualite);
		
		JLabel lblPresentation = new JLabel("Sélectionnez la présentation :");
		lblPresentation.setBounds(33, 312, 172, 16);
		contentPane.add(lblPresentation);
		
		final JComboBox<Item<String>> comboBoxPresentation = new JComboBox<Item<String>>();
		comboBoxPresentation.setToolTipText("Sélectionnez une présentation");
		ArrayList<HashMap<String, Object>> allPresentation = main.MainMethods.getAllPresentation();
		comboBoxPresentation.addItem(new Item<String>("", ""));
		for (HashMap<String, Object> row : allPresentation) {
			Item<String> item = new Item<String>((String)row.get("id"), (String)row.get("libelle"));
			comboBoxPresentation.addItem(item);
		}
		comboBoxPresentation.setBounds(33, 330, 172, 21);
		contentPane.add(comboBoxPresentation);
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnRetour.setForeground(new Color(255, 255, 255));
		btnRetour.setBackground(new Color(255, 0, 51));
		btnRetour.setBounds(33, 363, 81, 26);
		contentPane.add(btnRetour);
		
		JButton btnValider = new JButton("Valider");
		btnValider.setBackground(new Color(0, 51, 204));
		btnValider.setForeground(new Color(255, 255, 255));
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unchecked")
		    	Item<Integer> selectedBateau = (Item<Integer>)comboBoxBateau.getSelectedItem();
		    	Integer idBateau = selectedBateau.getValue();
		    	@SuppressWarnings("unchecked")
		    	Item<String> selectedTypeBac = (Item<String>)comboBoxTypeBac.getSelectedItem();
		    	String idTypeBac = selectedTypeBac.getValue();
		    	@SuppressWarnings("unchecked")
		    	Item<Integer> selectedEspece = (Item<Integer>)comboBoxEspece.getSelectedItem();
		    	Integer idEspece = selectedEspece.getValue();
		    	@SuppressWarnings("unchecked")
		    	Item<Integer> selectedTaille = (Item<Integer>)comboBoxTaille.getSelectedItem();
		    	Integer idTaille = selectedTaille.getValue();
		    	@SuppressWarnings("unchecked")
		    	Item<String> selectedQualite = (Item<String>)comboBoxQualite.getSelectedItem();
		    	String idQualite = selectedQualite.getValue();
		    	@SuppressWarnings("unchecked")
		    	Item<String> selectedPresentation = (Item<String>)comboBoxPresentation.getSelectedItem();
		    	String idPresentation = selectedPresentation.getValue();
				if (idBateau >= 0 && idEspece >= 0 && idTaille >= 0 && !idTypeBac.isEmpty() && !idQualite.isEmpty() && !idPresentation.isEmpty()) {
					if (lots.LotsMethods.addLot(selectedDate, idBateau, idTypeBac, idEspece, idTaille, idQualite, idPresentation)) {
						dispose();
						JOptionPane.showMessageDialog(null, "Ajout effectué.", "Message", JOptionPane.INFORMATION_MESSAGE);
						lots.GestionLotVet.updateTable();
					} else {
						JOptionPane.showMessageDialog(null, "Ajout impossible.", "Message", JOptionPane.INFORMATION_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs.", "Message", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnValider.setBounds(124, 363, 81, 26);
		contentPane.add(btnValider);
	}
}
