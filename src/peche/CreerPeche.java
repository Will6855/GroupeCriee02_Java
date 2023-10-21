package peche;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;


public class CreerPeche extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreerPeche addPeche = new CreerPeche();
					addPeche.setLocationRelativeTo(null);
					addPeche.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CreerPeche() {		
		setResizable(false);
		setTitle("Ajout Peche");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 247, 221);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setToolTipText("Bateau venant de faire la pêche");
		ArrayList<HashMap<String, Object>> allBateaux = peche.PecheMethods.getBateaux();
		for (HashMap<String, Object> row : allBateaux) {
		      comboBox.addItem((String) row.get("nom"));
		}
		comboBox.setBounds(33, 100, 172, 21);
		contentPane.add(comboBox);
		
		JLabel lblTitre = new JLabel("Nouvelle pêche du jour");
		lblTitre.setFont(new Font("Dialog", Font.BOLD, 15));
		lblTitre.setBounds(33, 24, 172, 21);
		contentPane.add(lblTitre);
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnRetour.setForeground(new Color(255, 255, 255));
		btnRetour.setBackground(new Color(255, 0, 51));
		btnRetour.setBounds(33, 133, 81, 26);
		contentPane.add(btnRetour);
		
		JButton btnValider = new JButton("Valider");
		btnValider.setBackground(new Color(0, 51, 204));
		btnValider.setForeground(new Color(255, 255, 255));
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean result = peche.PecheMethods.addPeche(comboBox.getSelectedIndex()+1);
				dispose();
				if (result) {
					JOptionPane.showMessageDialog(null, "Ajout effectuée.", "Message", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Ajout impossible.", "Message", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnValider.setBounds(124, 133, 81, 26);
		contentPane.add(btnValider);
		
		JLabel lblInstruction = new JLabel("<html><p style=\\\"text-align:center\\\">Veuillez sélectionnez le bateau venant de faire la pêche</html>");
		lblInstruction.setFont(new Font("Dialog", Font.ITALIC, 11));
		lblInstruction.setHorizontalAlignment(SwingConstants.TRAILING);
		lblInstruction.setBounds(33, 53, 172, 35);
		contentPane.add(lblInstruction);
	}
}
