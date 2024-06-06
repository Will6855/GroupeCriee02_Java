package lots;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingConstants;
import javax.swing.table.TableColumn;

public class AfficherLot extends JFrame {

    private JPanel contentPane;
    private JTable table;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AfficherLot frame = new AfficherLot();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public AfficherLot() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JLabel titleLabel = new JLabel("Affichage des lots");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(titleLabel, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);

        table = new JTable();
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        scrollPane.setViewportView(table);

        // Load data from the database
        loadData();
    }

    private void loadData() {
        String url = "jdbc:mysql://localhost:3306/bdd_criee2?useUnicode=true&characterEncoding=utf8";
        String user = "root";
        String password = "";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT b.nom AS Bateau, l.id AS NumLot, e.nom AS Espece, t.specification AS Taille, " +
                           "q.libelle AS Qualite, p.libelle AS Presentation, tb.tare AS TypeBac, " +
                           "(SELECT COUNT(*) FROM bac WHERE idLot = l.id) AS NombreBacs, " +
                           "(SELECT SUM(poidsBrutBac) FROM bac WHERE idLot = l.id) AS PoidsBrut, " +
                           "NULL AS PoidsNet, l.codeEtat AS CodeEtat " +
                           "FROM lot l " +
                           "INNER JOIN bateau b ON l.idBateau = b.id " +
                           "LEFT JOIN espece e ON l.idEspece = e.id " +
                           "LEFT JOIN taille t ON l.idTaille = t.id " +
                           "LEFT JOIN qualite q ON l.idQualite = q.id " +
                           "LEFT JOIN presentation p ON l.idPresentation = p.id " +
                           "LEFT JOIN bac bc ON l.id = bc.idLot " +
                           "LEFT JOIN typebac tb ON bc.idTypeBac = tb.id " +
                           "GROUP BY l.id";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            Vector<String> columnNames = new Vector<>();
            columnNames.add("Bateau");
            columnNames.add("NumLot");
            columnNames.add("Espece");
            columnNames.add("Taille");
            columnNames.add("Qualite");
            columnNames.add("Presentation");
            columnNames.add("TypeBac");
            columnNames.add("NombreBacs");
            columnNames.add("PoidsBrut");
            columnNames.add("PoidsNet");
            columnNames.add("CodeEtat");

            Vector<Vector<Object>> data = new Vector<>();

            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                row.add(rs.getString("Bateau"));
                row.add(rs.getInt("NumLot"));
                row.add(rs.getString("Espece"));
                row.add(rs.getString("Taille"));
                row.add(rs.getString("Qualite"));
                row.add(rs.getString("Presentation"));
                row.add(rs.getString("TypeBac"));
                row.add(rs.getInt("NombreBacs"));
                row.add(rs.getFloat("PoidsBrut"));
                row.add(null); // PoidsNet is NULL
                row.add(rs.getString("CodeEtat"));
                data.add(row);
            }

            DefaultTableModel model = new DefaultTableModel(data, columnNames) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; // Rendre les cellules non modifiables
                }
            };

            table.setModel(model);

            // Ajuster la largeur des colonnes pour s'adapter au contenu
            for (int i = 0; i < table.getColumnCount(); i++) {
                TableColumn column = table.getColumnModel().getColumn(i);
                int width = 15; // Min width
                for (int j = 0; j < table.getRowCount(); j++) {
                    int preferredWidth = table.getCellRenderer(j, i)
                            .getTableCellRendererComponent(table, table.getValueAt(j, i), false, false, j, i)
                            .getPreferredSize().width;
                    width = Math.max(width, preferredWidth + 10);
                }
                column.setPreferredWidth(width);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
