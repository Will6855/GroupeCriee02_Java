package lots;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SaisiePoidsLot extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private Float poidsLot = null;
	private Boolean poidsExist = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SaisiePoidsLot poidsLot = new SaisiePoidsLot(-1);
					poidsLot.setLocationRelativeTo(null);
					poidsLot.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SaisiePoidsLot(final Integer idLot) {
		setResizable(false);
		setTitle("Poids Lots");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 283, 196);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitre = new JLabel("Saisie du poids d'un lot");
		lblTitre.setFont(new Font("Dialog", Font.BOLD, 15));
		lblTitre.setBounds(46, 28, 182, 16);
		contentPane.add(lblTitre);
		
		textField = new JTextField();
		textField.setBounds(46, 74, 182, 25);
		contentPane.add(textField);
		textField.setColumns(10);
		Float poidsBac = lots.LotsMethods.getPoidsLot(idLot);
    	if (poidsBac != 0) {
    		textField.setText(poidsBac.toString());
    		poidsExist = true;
    	}
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnRetour.setForeground(new Color(255, 255, 255));
		btnRetour.setBackground(new Color(255, 0, 51));
		btnRetour.setBounds(46, 111, 73, 26);
		contentPane.add(btnRetour);
		
		JButton btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Veuillez saisir le poids.", "Message", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				try {
					poidsLot = Float.parseFloat(textField.getText());
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Veuillez saisir un poids valide.", "Message", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
				Boolean result = lots.LotsMethods.addPoidsLot(idLot, poidsLot);
				if (result) {
					dispose();
					if (poidsExist) {
						JOptionPane.showMessageDialog(null, "Modification effectuée.", "Message", JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "Ajout effectué.", "Message", JOptionPane.INFORMATION_MESSAGE);
					}
					lots.GestionLotPes.updateTable();
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
		btnValider.setBounds(155, 111, 73, 26);
		contentPane.add(btnValider);
		
		JLabel lblInstruction2 = new JLabel("Saisissez le poids brut (en Kg) :");
		lblInstruction2.setFont(new Font("Dialog", Font.ITALIC, 11));
		lblInstruction2.setBounds(46, 56, 165, 16);
		contentPane.add(lblInstruction2);
	}
}
