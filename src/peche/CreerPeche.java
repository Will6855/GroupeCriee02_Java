package peche;

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
import java.sql.Date;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

import main.Item;


public class CreerPeche extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 * @param selectedDate 
	 */
	public CreerPeche(final Date selectedDate) {		
		setResizable(false);
		setTitle("Ajout Peche");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 247, 221);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final JComboBox<Item<Integer>> comboBoxBateau = new JComboBox<Item<Integer>>();
		comboBoxBateau.setToolTipText("Sélectionnez un bateau");
		ArrayList<HashMap<String, Object>> allBateaux = peche.PecheMethods.getBateaux();
		comboBoxBateau.addItem(new Item<Integer>(-1, ""));
		for (HashMap<String, Object> row : allBateaux) {
		      comboBoxBateau.addItem(new Item<Integer>((Integer) row.get("id"), (String) row.get("nom")));
		}
		comboBoxBateau.setBounds(33, 100, 172, 21);
		contentPane.add(comboBoxBateau);
		
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
				@SuppressWarnings("unchecked")
				Item<Integer> selectedBateau = (Item<Integer>)comboBoxBateau.getSelectedItem();
				Integer idBateau = selectedBateau.getValue();
				if (idBateau >= 0) {
					if (peche.PecheMethods.verifPeche(idBateau, selectedDate)) {
						JOptionPane.showMessageDialog(null, "Pêche déjà existante.", "Message", JOptionPane.INFORMATION_MESSAGE);
					} else {
						if (peche.PecheMethods.addPeche(idBateau, selectedDate)) {
							dispose();
							JOptionPane.showMessageDialog(null, "Ajout effectué.", "Message", JOptionPane.INFORMATION_MESSAGE);
							peche.GestionPecheVet.updateTable();
						} else {
							JOptionPane.showMessageDialog(null, "Ajout impossible.", "Message", JOptionPane.INFORMATION_MESSAGE);
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Veuillez sélectionner un bateau.", "Message", JOptionPane.INFORMATION_MESSAGE);
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
