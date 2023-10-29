package lots;

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

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JComboBox;

public class GestionLotVet extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static DefaultTableModel model;
	private JTable table;
	private JScrollPane JScroll;
	private JButton btnVoirLots;
	private JButton btnSupprimer;
	private JButton btnRetour;
	
	private static JDatePickerImpl datePicker;
	private static JComboBox<Item<Integer>> comboBoxBateau;

	/**
	 * Create the frame.
	 * @param selectedDate 
	 */
	public GestionLotVet(Integer idBateau, java.sql.Date selectedDate) {		
		setResizable(false);
		setTitle("Gestion Lots");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 802, 288);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JLabel lblTitre = new JLabel("Gestion des lots");
		lblTitre.setFont(new Font("Dialog", Font.BOLD, 15));
		lblTitre.setBounds(30, 12, 217, 21);
		contentPane.add(lblTitre);
		
		final SqlDateModel model = new SqlDateModel();
		model.setValue(selectedDate);
		model.setSelected(true);
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		datePicker = new JDatePickerImpl(datePanel, new main.DateLabelFormatter()); 
		contentPane.add(datePicker);
		datePicker.setBounds(31, 34, 105, 26);
		model.addPropertyChangeListener(new PropertyChangeListener() {
		    @Override
		    public void propertyChange(PropertyChangeEvent evt) {		    	
		    	@SuppressWarnings("unchecked")
				Item<Integer> selectedItem = (Item<Integer>)comboBoxBateau.getSelectedItem();
		    	Integer idBateau = selectedItem.getValue();
		    	java.sql.Date selectedDate = (java.sql.Date) datePicker.getModel().getValue();
		    	
		    	comboBoxBateau.removeAllItems();
		    	ArrayList<HashMap<String, Object>> allPeche = peche.PecheMethods.getAllPeche(selectedDate);
				comboBoxBateau.addItem(new Item<Integer>(-1, "Tous les bateaux"));
				comboBoxBateau.setSelectedIndex(0);
				for (HashMap<String, Object> row : allPeche) {
					Item<Integer> item = new Item<Integer>((Integer)row.get("idBateau"), (String)row.get("nom"));
				    comboBoxBateau.addItem(item);
				    if (idBateau == row.get("idBateau")) {
					    comboBoxBateau.setSelectedItem(item);
				    }
				}
		    	updateTable();
		    }
		});
		
		JLabel lblBateau = new JLabel("Bateau :");
		lblBateau.setBounds(154, 38, 46, 16);
		contentPane.add(lblBateau);
		
		comboBoxBateau = new JComboBox<Item<Integer>>();
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
		comboBoxBateau.setBounds(202, 34, 140, 25);
		contentPane.add(comboBoxBateau);
		
		comboBoxBateau.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	if (comboBoxBateau.isPopupVisible()) {
		    		updateTable();
		    	}
		    }
		});
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unchecked")
				Item<Integer> selectedItem = (Item<Integer>)comboBoxBateau.getSelectedItem();
		    	Integer idBateau = selectedItem.getValue();
		    	java.sql.Date selectedDate = (java.sql.Date) datePicker.getModel().getValue();
				lots.CreerLot addLot = new lots.CreerLot(idBateau, selectedDate);
				addLot.setLocationRelativeTo(null);
				addLot.setVisible(true);
			}
		});
		btnAjouter.setForeground(Color.WHITE);
		btnAjouter.setBackground(new Color(0, 51, 204));
		btnAjouter.setBounds(608, 66, 168, 26);
		contentPane.add(btnAjouter);
		
		btnVoirLots = new JButton("Voir les bacs associés");
		btnVoirLots.setEnabled(false);
		btnVoirLots.setBackground(new Color(0, 0, 153));
		btnVoirLots.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnVoirLots.setForeground(Color.WHITE);
		btnVoirLots.setBounds(608, 104, 168, 26);
		contentPane.add(btnVoirLots);
		
		btnSupprimer = new JButton("Supprimer");
		btnSupprimer.setEnabled(false);
		btnSupprimer.setBackground(new Color(204, 0, 51));
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				Integer id = (Integer) table.getModel().getValueAt(row, 0);
				int result = JOptionPane.showConfirmDialog(null,"Confirmer-vous la suppression ?", "Message",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
				if (result == JOptionPane.YES_OPTION){
					if (lots.LotsMethods.delLot(id)) {
						JOptionPane.showMessageDialog(null, "Suppression effectué.", "Message", JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "Suppression impossible.", "Message", JOptionPane.INFORMATION_MESSAGE);
					}
			    	updateTable();
				}
			}
		});
		btnSupprimer.setForeground(Color.WHITE);
		btnSupprimer.setBounds(608, 207, 168, 26);
		contentPane.add(btnSupprimer);
		
		btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				GestionPecheVet gestionPecheVet = new GestionPecheVet();
				gestionPecheVet.setLocationRelativeTo(null);
				gestionPecheVet.setVisible(true);
			}
		});
		btnRetour.setForeground(Color.WHITE);
		btnRetour.setBackground(new Color(255, 0, 51));
		btnRetour.setBounds(683, 10, 93, 26);
		contentPane.add(btnRetour);
		
		TableAdd();
		updateTable();
		
		ListSelectionModel selectionModel = table.getSelectionModel();
 	    selectionModel.addListSelectionListener(new ListSelectionListener() {
 	    	public void valueChanged(ListSelectionEvent e) {
 	    		if (table.getSelectedRow() >= 0) {
 	    			btnSupprimer.setEnabled(true);
 	    			btnSupprimer.setBackground(new Color(255, 0, 51));
 	    			
 	    			btnVoirLots.setEnabled(true);
 	    			btnVoirLots.setBackground(new Color(0, 51, 204));
 	    		}else {
 	    			btnSupprimer.setEnabled(false);
 	    			btnSupprimer.setBackground(new Color(204, 0, 51));
 	    			
 	    			btnVoirLots.setEnabled(false);
 	    			btnVoirLots.setBackground(new Color(0, 0, 153));
 	    		}
 	    	}
 	    });
	}
	
	private void TableAdd() {
		model = new DefaultTableModel();
		initTable();
		contentPane.setLayout(null);
		table.setModel(model);
		contentPane.add(JScroll);
		
		model.addColumn("id");
		
		model.addColumn("Bateau");
		model.addColumn("N° Lot");
		model.addColumn("Type bac");
		model.addColumn("Espèce");
		model.addColumn("Taille");
		model.addColumn("Qualité");
		model.addColumn("Présentation");
		model.addColumn("Poids brut");
		
		TableColumnModel tcm = table.getColumnModel();
		tcm.removeColumn(tcm.getColumn(0));
	}
	
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
	
	private void initTable() {
		table = new JTable();
		table.getTableHeader().setReorderingAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setDefaultEditor(Object.class, null);
		JScroll = new JScrollPane();
		JScroll.setViewportView(table);
		JScroll.setBounds(30, 66, 566, 167);
	}
}
