package bacs;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class SaisiePoidsBac extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JComboBox<Integer> comboBoxLots;
	private JComboBox<Integer> comboBoxBacs;
	private Integer selectedLot = null;
	private Integer selectedBac = null;
	private Float poidsBac = null;
	private Boolean poidsExist;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SaisiePoidsBac poidsBacs = new SaisiePoidsBac();
					poidsBacs.setLocationRelativeTo(null);
					poidsBacs.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SaisiePoidsBac() {
		setResizable(false);
		setTitle("Poids Bacs");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 283, 315);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitre = new JLabel("Saisie du poids d'un bac");
		lblTitre.setFont(new Font("Dialog", Font.BOLD, 15));
		lblTitre.setBounds(46, 28, 182, 16);
		contentPane.add(lblTitre);
		
		comboBoxLots = new JComboBox<Integer>();
		ArrayList<Integer> allLotsID = lots.LotsMethods.getLotsID();
		for (Integer row : allLotsID) {
			comboBoxLots.addItem(row);
		}
		comboBoxLots.setBounds(46, 76, 182, 25);
		contentPane.add(comboBoxLots);
		
		comboBoxBacs = new JComboBox<Integer>();
		comboBoxBacs.setBounds(46, 131, 182, 25);
		contentPane.add(comboBoxBacs);
		
		textField = new JTextField();
		textField.setBounds(46, 186, 182, 25);
		contentPane.add(textField);
		textField.setColumns(10);
		
		addBacs();
		setPoidsText();
		comboBoxLots.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBacs();
                setPoidsText();
            }
        });
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnRetour.setForeground(new Color(255, 255, 255));
		btnRetour.setBackground(new Color(255, 0, 51));
		btnRetour.setBounds(46, 223, 73, 26);
		contentPane.add(btnRetour);
		
		JButton btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
								
				selectedLot = (Integer) comboBoxLots.getSelectedItem();
				if (selectedLot == null) {
					JOptionPane.showMessageDialog(null, "Veuillez sélectionner un lot.", "Message", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
				selectedBac = (Integer) comboBoxBacs.getSelectedItem();
				if (selectedBac == null) {
					JOptionPane.showMessageDialog(null, "Veuillez sélectionner un bac.", "Message", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
				if (textField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Veuillez saisir le poids.", "Message", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				try {
					poidsBac = Float.parseFloat(textField.getText());
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Veuillez saisir un poids valide.", "Message", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
				Boolean result = bacs.BacsMethods.addPoidsBac(selectedBac, selectedLot, poidsBac);
				dispose();
				if (result) {
					if (poidsExist) {
						JOptionPane.showMessageDialog(null, "Modification effectuée.", "Message", JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "Ajout effectuée.", "Message", JOptionPane.INFORMATION_MESSAGE);
					}
				} else {
					if (poidsExist) {
						JOptionPane.showMessageDialog(null, "Modification impossible.", "Message", JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "Ajout impossible.", "Message", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		btnValider.setBackground(new Color(0, 51, 204));
		btnValider.setForeground(new Color(255, 255, 255));
		btnValider.setBounds(155, 223, 73, 26);
		contentPane.add(btnValider);
		
		JLabel lblInstruction1 = new JLabel("Sélectionnez le lot :");
		lblInstruction1.setFont(new Font("Dialog", Font.ITALIC, 11));
		lblInstruction1.setBounds(46, 57, 165, 16);
		contentPane.add(lblInstruction1);
		
		JLabel lblInstruction2 = new JLabel("Sélectionnez le bac :");
		lblInstruction2.setFont(new Font("Dialog", Font.ITALIC, 11));
		lblInstruction2.setBounds(46, 113, 165, 16);
		contentPane.add(lblInstruction2);
		
		JLabel lblInstruction3 = new JLabel("Saisissez le poids du bac :");
		lblInstruction3.setFont(new Font("Dialog", Font.ITALIC, 11));
		lblInstruction3.setBounds(46, 168, 165, 16);
		contentPane.add(lblInstruction3);
	}
	
	private void addBacs() {
		Integer selectedLot = (Integer) comboBoxLots.getSelectedItem();
        comboBoxBacs.removeAllItems();
        
        ArrayList<Integer> allBacsID = bacs.BacsMethods.getBacsIdByLotId(selectedLot);
		for (Integer row : allBacsID) {
			comboBoxBacs.addItem(row);
		}
	}
	
	private void setPoidsText() {
		selectedLot = (Integer) comboBoxLots.getSelectedItem();
        selectedBac = (Integer) comboBoxBacs.getSelectedItem();
        textField.setText("");
        if (selectedLot != null && selectedBac != null) {
        	Float poidsBac = bacs.BacsMethods.getPoidsBac(selectedBac, selectedLot);
        	if (poidsBac != 0) {
        		textField.setText(poidsBac.toString());
        		poidsExist = true;
        	} else {
        		poidsExist = false;
        	}
        }
	}
}
