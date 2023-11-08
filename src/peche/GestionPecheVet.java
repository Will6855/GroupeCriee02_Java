package peche;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Calendar;
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
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;

import org.jdatepicker.impl.*;

public class GestionPecheVet extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static DefaultTableModel model;
	private JTable table;
	private JScrollPane JScroll;
	private JButton btnVoirLots;
	private JButton btnSupprimer;
	private JButton btnVoirTousLots;
	
	private static JDatePickerImpl datePicker;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionPecheVet gestionPecheVet = new GestionPecheVet();
					gestionPecheVet.setLocationRelativeTo(null);
					gestionPecheVet.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GestionPecheVet() {
		setResizable(false);
		setTitle("Gestion Peches");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 258);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JLabel lblTitre = new JLabel("Gestion des pêches");
		lblTitre.setFont(new Font("Dialog", Font.BOLD, 15));
		lblTitre.setBounds(31, 12, 217, 21);
		contentPane.add(lblTitre);
		
		final SqlDateModel model = new SqlDateModel();
		Calendar now = Calendar.getInstance();
		model.setDate(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH));
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
		    	updateTable();
		    }
		});
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				java.sql.Date selectedDate = (java.sql.Date) datePicker.getModel().getValue();
				peche.CreerPeche addPeche = new peche.CreerPeche(selectedDate);
				addPeche.setLocationRelativeTo(null);
				addPeche.setVisible(true);
			}
		});
		btnAjouter.setForeground(Color.WHITE);
		btnAjouter.setBackground(new Color(0, 51, 204));
		btnAjouter.setBounds(257, 64, 168, 26);
		contentPane.add(btnAjouter);
		
		btnVoirTousLots = new JButton("Voir tous les lots");
		btnVoirTousLots.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				java.sql.Date selectedDate = (java.sql.Date) datePicker.getModel().getValue();
				lots.GestionLotVet gestionLotVet = new lots.GestionLotVet(-1, selectedDate);
				gestionLotVet.setLocationRelativeTo(null);
				gestionLotVet.setVisible(true);
				dispose();
			}
		});
		btnVoirTousLots.setForeground(Color.WHITE);
		btnVoirTousLots.setBackground(new Color(0, 51, 204));
		btnVoirTousLots.setBounds(257, 102, 168, 26);
		contentPane.add(btnVoirTousLots);
		
		btnVoirLots = new JButton("Voir les lots associés");
		btnVoirLots.setEnabled(false);
		btnVoirLots.setBackground(new Color(0, 0, 153));
		btnVoirLots.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				java.sql.Date selectedDate = (java.sql.Date) datePicker.getModel().getValue();
				int row = table.getSelectedRow();
				Integer idBateau = (Integer) table.getModel().getValueAt(row, 0);
				lots.GestionLotVet gestionLotVet = new lots.GestionLotVet(idBateau, selectedDate);
				gestionLotVet.setLocationRelativeTo(null);
				gestionLotVet.setVisible(true);
				dispose();
			}
		});
		btnVoirLots.setForeground(Color.WHITE);
		btnVoirLots.setBounds(257, 140, 168, 26);
		contentPane.add(btnVoirLots);
		
		btnSupprimer = new JButton("Supprimer");
		btnSupprimer.setEnabled(false);
		btnSupprimer.setBackground(new Color(204, 0, 51));
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				java.sql.Date selectedDate = (java.sql.Date) datePicker.getModel().getValue();
				int row = table.getSelectedRow();
				Integer idBateau = (Integer) table.getModel().getValueAt(row, 0);
				int result = JOptionPane.showConfirmDialog(null,"Confirmer-vous la suppression ?", "Message",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
				if (result == JOptionPane.YES_OPTION){
					if (peche.PecheMethods.delPeche(idBateau, selectedDate)) {
						JOptionPane.showMessageDialog(null, "Suppression effectué.", "Message", JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "Suppression impossible.", "Message", JOptionPane.INFORMATION_MESSAGE);
					}
					updateTable();
				}
			}
		});
		btnSupprimer.setForeground(Color.WHITE);
		btnSupprimer.setBounds(257, 178, 168, 26);
		contentPane.add(btnSupprimer);
		
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
		
		model.addColumn("idBateau");
		
		model.addColumn("Bateau");
		model.addColumn("Immatriculation");
		
		TableColumnModel tcm = table.getColumnModel();
		tcm.removeColumn(tcm.getColumn(0));
	}
	
	public static void updateTable() {
		model.setRowCount(0);
		java.sql.Date selectedDate = (java.sql.Date) datePicker.getModel().getValue();
		ArrayList<HashMap<String, Object>> allPeche = peche.PecheMethods.getAllPeche(selectedDate);
		for (HashMap<String, Object> row : allPeche) {
			Vector<Object> rowData = new Vector<>();
			rowData.add(row.get("idBateau"));
			
			rowData.add(row.get("nom"));
			rowData.add(row.get("immatriculation"));
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
		JScroll.setBounds(31, 64, 203, 140);
	}
}
