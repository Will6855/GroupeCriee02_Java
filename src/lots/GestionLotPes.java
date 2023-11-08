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
import peche.GestionPechePes;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JComboBox;

public class GestionLotPes extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static DefaultTableModel model;
	private JTable table;
	private JScrollPane JScroll;
	private JButton btnBloquer;
	private JButton btnTicket;
	private JButton btnRetour;
	private JButton btnVoirBacs;
	
	private static JDatePickerImpl datePicker;
	private static JComboBox<Item<Integer>> comboBoxBateau;

	/**
	 * Create the frame.
	 * @param selectedDate 
	 */
	public GestionLotPes(Integer idBateau, java.sql.Date selectedDate) {
		setResizable(false);
		setTitle("Gestion Lots");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 802, 286);
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
		p.put("text.today", "Aujourd'hui");
		p.put("text.month", "Mois");
		p.put("text.year", "Année");
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
		
		btnVoirBacs = new JButton("Voir les bacs associés");
		btnVoirBacs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				Integer idLot = (Integer) table.getModel().getValueAt(row, 0);
				java.sql.Date selectedDate = (java.sql.Date) datePicker.getModel().getValue();
				bacs.GestionBacPes gestionBacPes = new bacs.GestionBacPes(idLot);
				gestionBacPes.setLocationRelativeTo(null);
				gestionBacPes.setVisible(true);
				dispose();
			}
		});
		btnVoirBacs.setForeground(Color.WHITE);
		btnVoirBacs.setEnabled(false);
		btnVoirBacs.setBackground(new Color(0, 0, 153));
		btnVoirBacs.setBounds(608, 65, 168, 26);
		contentPane.add(btnVoirBacs);
		
		btnBloquer = new JButton("Bloquer");
		btnBloquer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBloquer.setForeground(Color.WHITE);
		btnBloquer.setEnabled(false);
		btnBloquer.setBackground(new Color(0, 0, 153));
		btnBloquer.setBounds(608, 104, 168, 26);
		contentPane.add(btnBloquer);
		
		btnTicket = new JButton("Ticket \"avant-vente\"");
		btnTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnTicket.setForeground(Color.WHITE);
		btnTicket.setEnabled(false);
		btnTicket.setBackground(new Color(0, 0, 153));
		btnTicket.setBounds(608, 142, 168, 26);
		contentPane.add(btnTicket);
		
		btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				GestionPechePes gestionPechePes = new GestionPechePes();
				gestionPechePes.setLocationRelativeTo(null);
				gestionPechePes.setVisible(true);
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
 	    			btnVoirBacs.setEnabled(true);
 	    			btnVoirBacs.setBackground(new Color(0, 51, 204));
 	    			
 	    			btnBloquer.setEnabled(true);
 	    			btnBloquer.setBackground(new Color(0, 51, 204));
 	    			
 	    			btnTicket.setEnabled(true);
 	    			btnTicket.setBackground(new Color(0, 51, 204));
 	    		}else {
 	    			btnVoirBacs.setEnabled(false);
 	    			btnVoirBacs.setBackground(new Color(0, 0, 153));
 	    			
 	    			btnBloquer.setEnabled(false);
 	    			btnBloquer.setBackground(new Color(0, 0, 153));
 	    			
 	    			btnTicket.setEnabled(false);
 	    			btnTicket.setBackground(new Color(0, 0, 153));
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
			rowData.add(row.get("nomCourt"));
			rowData.add(row.get("idTaille"));
			rowData.add(row.get("idQualite"));
			rowData.add(row.get("idPresentation"));
			rowData.add(lots.LotsMethods.getPoidsLot((Integer) row.get("id")));
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
