import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.BevelBorder;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.SwingConstants;

public class window extends JFrame {

    private JPanel contentPane;
    private JTable table;
    private JTextField name;
    private JTextField bestBeforeDate;
    // True if adding, false if editing
    private Boolean adding;
    private JTable fridgeTable;
    private JTable freezerTable;
    private JTable cupboardTable;
    private JTable deathRowTable;
    private int id = 0;

    /**
     * Launch the application.
     */
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                try
                {
                    window frame = new window();
                    frame.setVisible(true);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     * @throws ClassNotFoundException 
     * @throws SQLException 
     */
    public window() throws ClassNotFoundException, SQLException
    {
        setTitle("Food Management System (FMS)");
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection(database_info.getDBPAth(), database_info.getUsername(),database_info.getPassword());
        con.setAutoCommit(false);
        Statement stmt = con.createStatement();
        DefaultTableModel model = new DefaultTableModel(new String[]{"Name", "Best Before Date", "Quantity", "Location", "ID"}, 0)
        {

            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };
        DefaultTableModel fridge = new DefaultTableModel(new String[]{"Name", "Best Before Date", "Quantity", "Location", "ID"}, 0)
        {

            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };
        DefaultTableModel freezer = new DefaultTableModel(new String[]{"Name", "Best Before Date", "Quantity", "Location", "ID"}, 0)
        {

            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };
        DefaultTableModel cupboard = new DefaultTableModel(new String[]{"Name", "Best Before Date", "Quantity", "Location", "ID"}, 0)
        {

            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };
        DefaultTableModel deathRow = new DefaultTableModel(new String[]{"Name", "Best Before Date", "Quantity", "Location", "ID"}, 0)
        {

            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };
        
        
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1013, 690);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        JComboBox<String> storageLocation = new JComboBox<String>();
        storageLocation.setToolTipText("<html>Select where you would like this <br/> item to be stored in your home.</html>");
        storageLocation.setFont(new Font("Tahoma", Font.PLAIN, 16));
        JTextPane welcomeScreen = new JTextPane();
        welcomeScreen.setEditable(false);
        welcomeScreen.setForeground(new Color(0, 0, 0));
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        JTextPane lblWelcomeToThe = new JTextPane();
        lblWelcomeToThe.setForeground(new Color(255, 255, 255));
        lblWelcomeToThe.setEditable(false);
        JLabel lblAddNewItem = new JLabel("Add New Item");
        lblAddNewItem.setForeground(new Color(255, 255, 255));
        lblAddNewItem.setBackground(new Color(65, 105, 225));
        tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 16));
        JPanel backgroundPanel = new JPanel();
        
        JPanel itemPanel = new JPanel();
        itemPanel.setBounds(10, 11, 459, 604);
        itemPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(65, 105, 225), new Color(65, 105, 225), new Color(65, 105, 225), new Color(65, 105, 225)));
        contentPane.add(itemPanel);
        itemPanel.setLayout(null);
        itemPanel.setVisible(false);
        
        JLabel lblName = new JLabel("Name:");
        lblName.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblName.setBounds(10, 60, 439, 40);
        itemPanel.add(lblName);
        
        name = new JTextField();
        name.setFont(new Font("Tahoma", Font.PLAIN, 16));
        name.setBounds(10, 112, 439, 40);
        itemPanel.add(name);
        name.setColumns(10);
        
        JLabel lblQuantity = new JLabel("Quantity:");
        lblQuantity.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblQuantity.setBounds(10, 163, 439, 40);
        itemPanel.add(lblQuantity);
        
        JSpinner quantity = new JSpinner();
        quantity.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
        quantity.setFont(new Font("Tahoma", Font.PLAIN, 14));
        quantity.setBounds(10, 214, 439, 40);
        itemPanel.add(quantity);
        
        JLabel lblBestBeforeDate = new JLabel("Best Before Date (YYYY-MM-DD):");
        lblBestBeforeDate.setForeground(Color.BLACK);
        lblBestBeforeDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblBestBeforeDate.setBounds(10, 265, 439, 40);
        itemPanel.add(lblBestBeforeDate);
        
        JButton btnConfirm = new JButton("Confirm");
        btnConfirm.setIcon(new ImageIcon(window.class.getResource("/images/icons8_Checked_20px.png")));
        btnConfirm.setForeground(SystemColor.text);
        btnConfirm.setBackground(Color.BLUE);
        btnConfirm.setOpaque(true);
        btnConfirm.setBorderPainted(false);
        btnConfirm.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                try
                {
                    int row = 0;
                    // Assigns the value of the tab currently open in the display
                    // so that the program will know exactly what item the user is editing
                    // based on which item they currently have selected in the JTable.
                    JTable tempTable = null;
                    if (tabbedPane.getSelectedIndex() == 0)
                    {
                        tempTable = table;
                        row = table.getSelectedRow();
                    }
                    
                    else if (tabbedPane.getSelectedIndex() == 1)
                    {
                        tempTable = fridgeTable;
                        row = fridgeTable.getSelectedRow();
                    }
                    
                    else if (tabbedPane.getSelectedIndex() == 2)
                    {
                        tempTable = freezerTable;
                        row = freezerTable.getSelectedRow();
                    }
                    
                    else if (tabbedPane.getSelectedIndex() == 3)
                    {
                        tempTable = cupboardTable;
                        row = cupboardTable.getSelectedRow();
                    }
                    
                    else if (tabbedPane.getSelectedIndex() == 4)
                    {
                        tempTable = deathRowTable;
                        row = deathRowTable.getSelectedRow();
                    }
                    // Detects if user is adding an item to the database or just editing it.
                    if (adding)
                    {
                        // SQL command only executes if all fields are filled in
                        if (!name.getText().isEmpty() && !bestBeforeDate.getText().isEmpty())
                        {
                            id++;
                            PreparedStatement st = con.prepareStatement("INSERT INTO food (name, best_before_date, quantity, location, id) VALUES (?, ?, ?, ?, ?);");
                            st.setString(1, name.getText());
                            st.setObject(2, Date.valueOf(bestBeforeDate.getText()));
                            st.setInt(3, (int) (quantity.getValue()));
                            st.setString(4, (String) storageLocation.getSelectedItem());
                            st.setInt(5, id);
                            st.executeUpdate();
                            st.close();
                        }
                        
                        else
                        {
                            JOptionPane.showMessageDialog(null, "Please fill out all input fields.");
                        }
                    }
                    // This code activates if the user is editing an item rather than adding.
                    else
                    {
                        
                        PreparedStatement st = con.prepareStatement("UPDATE food SET name=?, best_before_Date=?, quantity=?, location=? WHERE name=? AND best_before_Date=? AND quantity=? AND location=? AND id=?;");
                        st.setString(1, name.getText());
                        st.setObject(2, Date.valueOf(bestBeforeDate.getText()));
                        st.setInt(3, (int) (quantity.getValue()));
                        st.setString(4, (String) storageLocation.getSelectedItem());
                        st.setString(5, (String) tempTable.getModel().getValueAt(row, 0));
                        st.setObject(6, Date.valueOf(String.valueOf(tempTable.getModel().getValueAt(row, 1))));
                        st.setInt(7, Integer.valueOf(String.valueOf(tempTable.getModel().getValueAt(row, 2))));
                        st.setString(8, (String) tempTable.getModel().getValueAt(row, 3));
                        st.setInt(9, Integer.valueOf(String.valueOf(tempTable.getModel().getValueAt(row, 4))));
                        st.executeUpdate();
                        st.close();
                        lblWelcomeToThe.setVisible(true);
                        itemPanel.setVisible(false);
                        welcomeScreen.setVisible(true);
                        backgroundPanel.setVisible(true);
                    }
                    con.commit();
                    // Updates all jtables to display the new information.
                    if (con != null)
                    {
                        String all = "SELECT * FROM food;";
                        String fridgeRS = "SELECT * FROM food WHERE location='Fridge';";
                        String freezerRS = "SELECT * FROM food WHERE location='Freezer';";
                        String cupboardRS = "SELECT * FROM food WHERE location='Cupboard';";
                        String deathRowRS = "SELECT * FROM food WHERE best_before_date<=CURRENT_DATE;";
                        ResultSet rs1 = stmt.executeQuery(all);
                        PreparedStatement st1 = con.prepareStatement(all);
                        table.setModel(DbUtils.resultSetToTableModel(rs1));
                        table.removeColumn(table.getColumn("ID"));
                        ResultSet rs2 = stmt.executeQuery(fridgeRS);
                        PreparedStatement st2 = con.prepareStatement(fridgeRS);
                        fridgeTable.setModel(DbUtils.resultSetToTableModel(rs2));
                        fridgeTable.removeColumn(fridgeTable.getColumn("ID"));
                        ResultSet rs3 = stmt.executeQuery(freezerRS);
                        PreparedStatement st3 = con.prepareStatement(freezerRS);
                        freezerTable.setModel(DbUtils.resultSetToTableModel(rs3));
                        freezerTable.removeColumn(freezerTable.getColumn("ID"));
                        ResultSet rs4 = stmt.executeQuery(cupboardRS);
                        PreparedStatement st4 = con.prepareStatement(cupboardRS);
                        cupboardTable.setModel(DbUtils.resultSetToTableModel(rs4));
                        cupboardTable.removeColumn(cupboardTable.getColumn("ID"));
                        ResultSet rs5 = stmt.executeQuery(deathRowRS);
                        PreparedStatement st5 = con.prepareStatement(deathRowRS);
                        deathRowTable.setModel(DbUtils.resultSetToTableModel(rs5));
                        deathRowTable.removeColumn(deathRowTable.getColumn("ID"));
                    }
                    // Input fields are emptied for future use.
                    name.setText(null);
                    bestBeforeDate.setText(null);
                    quantity.setValue(1);
                    storageLocation.setSelectedItem("Fridge");
                }
                catch (SQLException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        btnConfirm.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnConfirm.setBounds(10, 507, 439, 39);
        itemPanel.add(btnConfirm);
        
        JButton btnCancel = new JButton("Cancel");
        btnCancel.setIcon(new ImageIcon(window.class.getResource("/images/icons8_Cancel_25px.png")));
        btnCancel.setForeground(SystemColor.text);
        btnCancel.setBackground(Color.RED);
        btnCancel.setOpaque(true);
        btnCancel.setBorderPainted(false);
        btnCancel.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                lblWelcomeToThe.setVisible(true);
                itemPanel.setVisible(false);
                welcomeScreen.setVisible(true);
                backgroundPanel.setVisible(true);
            }
        });
        btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnCancel.setBounds(10, 554, 439, 39);
        itemPanel.add(btnCancel);
        
        bestBeforeDate = new JTextField();
        bestBeforeDate.setFont(new Font("Tahoma", Font.PLAIN, 20));
        bestBeforeDate.setBounds(10, 316, 439, 40);
        itemPanel.add(bestBeforeDate);
        bestBeforeDate.setColumns(10);
        
        
        JLabel lblStorageLocation = new JLabel("Storage Location:");
        lblStorageLocation.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblStorageLocation.setBounds(10, 361, 439, 40);
        itemPanel.add(lblStorageLocation);
        
        
        storageLocation.setModel(new DefaultComboBoxModel(new String[] {"Fridge", "Freezer", "Cupboard"}));
        storageLocation.setSelectedIndex(0);
        storageLocation.setBounds(10, 412, 439, 40);
        itemPanel.add(storageLocation);
        
        
        lblAddNewItem.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblAddNewItem.setBounds(10, 11, 439, 40);
        itemPanel.add(lblAddNewItem);
        
        JPanel panel_5 = new JPanel();
        panel_5.setBackground(new Color(65, 105, 225));
        panel_5.setBounds(0, 0, 459, 62);
        itemPanel.add(panel_5);
        
        
        tabbedPane.setBounds(479, 11, 508, 604);
        contentPane.add(tabbedPane);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(255, 255, 255));
        tabbedPane.addTab("All", null, panel_1, null);
        panel_1.setLayout(null);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 0, 503, 576);
        panel_1.add(scrollPane);

        // Code for the "all" table
        table = new JTable();
        table.setBackground(new Color(255, 255, 255));
        table.setAutoCreateRowSorter(true);
        scrollPane.setViewportView(table);
        table.setModel(model);
        if (con != null)
        {
            String psql = "SELECT * FROM food;";
            ResultSet rs = stmt.executeQuery(psql);
            try (PreparedStatement stat = con.prepareStatement(psql))
            {
                while (rs.next())
                {
                    String n = rs.getString("name");
                    String b = rs.getString("best_before_date");
                    String q = rs.getString("quantity");
                    String l = rs.getString("location");
                    String i = rs.getString("id");
                    model.addRow(new Object[]{n, b, q, l, i});
                    // Sets the value of added itms ID's to be higher than the highest
                    // ID value currently present in the database.
                    if (Integer.valueOf(i) > id)
                    {
                        id = Integer.valueOf(i);
                    }
                }
            }
        }
        // Removes ID column so that it is not displayed on jtable but can
        // still be accessed through code.
        table.removeColumn(table.getColumn("ID"));
        
        JPanel panel_2 = new JPanel();
        tabbedPane.addTab("Fridge", null, panel_2, null);
        panel_2.setLayout(null);
        
        JScrollPane scrollPane_1 = new JScrollPane((Component) null);
        scrollPane_1.setBounds(0, 0, 503, 576);
        panel_2.add(scrollPane_1);
        
        // Code for the fridge table
        fridgeTable = new JTable();
        fridgeTable.setModel(fridge);
        fridgeTable.setAutoCreateRowSorter(true);
        scrollPane_1.setViewportView(fridgeTable);
        if (con != null)
        {
            String psql = "SELECT * FROM food WHERE location='Fridge';";
            ResultSet rs = stmt.executeQuery(psql);
            try (PreparedStatement stat = con.prepareStatement(psql))
            {
                while (rs.next())
                {
                    String n = rs.getString("name");
                    String b = rs.getString("best_before_date");
                    String q = rs.getString("quantity");
                    String l = rs.getString("location");
                    String i = rs.getString("id");
                    fridge.addRow(new Object[]{n, b, q, l, i});
                }
            }
        }
        fridgeTable.removeColumn(fridgeTable.getColumn("ID"));
        
        JPanel panel_3 = new JPanel();
        tabbedPane.addTab("Freezer", null, panel_3, null);
        panel_3.setLayout(null);
        
        JScrollPane scrollPane_2 = new JScrollPane();
        scrollPane_2.setBounds(0, 0, 503, 576);
        panel_3.add(scrollPane_2);

        // Code for the freezer table
        freezerTable = new JTable();
        freezerTable.setModel(freezer);
        freezerTable.setAutoCreateRowSorter(true);
        scrollPane_2.setViewportView(freezerTable);
        if (con != null)
        {
            String psql = "SELECT * FROM food WHERE location='Freezer';";
            ResultSet rs = stmt.executeQuery(psql);
            try (PreparedStatement stat = con.prepareStatement(psql))
            {
                while (rs.next())
                {
                    String n = rs.getString("name");
                    String b = rs.getString("best_before_date");
                    String q = rs.getString("quantity");
                    String l = rs.getString("location");
                    String i = rs.getString("id");
                    freezer.addRow(new Object[]{n, b, q, l, i});
                }
            }
        }
        freezerTable.removeColumn(freezerTable.getColumn("ID"));
        
        JPanel panel_4 = new JPanel();
        tabbedPane.addTab("Cupboard", null, panel_4, null);
        panel_4.setLayout(null);
        
        JScrollPane scrollPane_3 = new JScrollPane();
        scrollPane_3.setBounds(0, 0, 503, 576);
        panel_4.add(scrollPane_3);

        // Code for the cupboard table
        cupboardTable = new JTable();
        cupboardTable.setModel(cupboard);
        cupboardTable.setAutoCreateRowSorter(true);
        scrollPane_3.setViewportView(cupboardTable);
        if (con != null)
        {
            String psql = "SELECT * FROM food WHERE location='Cupboard';";
            ResultSet rs = stmt.executeQuery(psql);
            try (PreparedStatement stat = con.prepareStatement(psql))
            {
                while (rs.next())
                {
                    String n = rs.getString("name");
                    String b = rs.getString("best_before_date");
                    String q = rs.getString("quantity");
                    String l = rs.getString("location");
                    String i = rs.getString("id");
                    cupboard.addRow(new Object[]{n, b, q, l, i});
                }
            }
        }
        cupboardTable.removeColumn(cupboardTable.getColumn("ID"));
        
        JPanel panel = new JPanel();
        tabbedPane.addTab("Death Row", null, panel, null);
        panel.setLayout(null);
        
        JScrollPane scrollPane_4 = new JScrollPane();
        scrollPane_4.setBounds(0, 0, 503, 576);
        panel.add(scrollPane_4);
        
        deathRowTable = new JTable();
        deathRowTable.setModel(deathRow);
        deathRowTable.setAutoCreateRowSorter(true);
        scrollPane_4.setViewportView(deathRowTable);
        if (con != null)
        {
            String psql = "SELECT * FROM food WHERE best_before_date<=CURRENT_DATE;";
            ResultSet rs = stmt.executeQuery(psql);
            try (PreparedStatement stat = con.prepareStatement(psql))
            {
                while (rs.next())
                {
                    String n = rs.getString("name");
                    String b = rs.getString("best_before_date");
                    String q = rs.getString("quantity");
                    String l = rs.getString("location");
                    String i = rs.getString("id");
                    deathRow.addRow(new Object[]{n, b, q, l, i});
                }
            }
        }
        deathRowTable.removeColumn(deathRowTable.getColumn("ID"));
        
        JButton btnAddNewItem = new JButton("Add New Item");
        btnAddNewItem.setForeground(new Color(255, 255, 255));
        btnAddNewItem.setHorizontalAlignment(SwingConstants.LEFT);
        btnAddNewItem.setIcon(new ImageIcon(window.class.getResource("/images/icons8_Plus_15px_2.png")));
        btnAddNewItem.setToolTipText("<html>Click to add a new food item <br/> to the database.</html>");
        btnAddNewItem.setBackground(new Color(0, 153, 51));
        btnAddNewItem.setBounds(479, 619, 134, 27);
        btnAddNewItem.setOpaque(true);
        btnAddNewItem.setBorderPainted(false);
        btnAddNewItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                itemPanel.setVisible(true);
                lblWelcomeToThe.setVisible(false);
                welcomeScreen.setVisible(false);
                backgroundPanel.setVisible(false);
                lblAddNewItem.setText("Add New Item");
                adding = true;
                name.setText(null);
                bestBeforeDate.setText(null);
                quantity.setValue(1);
                storageLocation.setSelectedItem("Fridge");
            }
        });
        contentPane.add(btnAddNewItem);
        
        
        
        
        
        
        
        
        
        
        
        
        JButton btnRemoveItem = new JButton("Remove Item");
        btnRemoveItem.setForeground(new Color(255, 255, 255));
        btnRemoveItem.setIcon(new ImageIcon(window.class.getResource("/images/icons8_Waste_15px.png")));
        btnRemoveItem.setToolTipText("<html>Click to remove a selected food item <br/> from the database.</html>");
        btnRemoveItem.setBackground(Color.RED);
        btnRemoveItem.setOpaque(true);
        btnRemoveItem.setBorderPainted(false);
        btnRemoveItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                int row = 0;
                JTable tempTable = null;
                
                if (tabbedPane.getSelectedIndex() == 0)
                {
                    tempTable = table;
                    row = table.getSelectedRow();
                }
                
                else if (tabbedPane.getSelectedIndex() == 1)
                {
                    tempTable = fridgeTable;
                    row = fridgeTable.getSelectedRow();
                }
                
                else if (tabbedPane.getSelectedIndex() == 2)
                {
                    tempTable = freezerTable;
                    row = freezerTable.getSelectedRow();
                }
                
                else if (tabbedPane.getSelectedIndex() == 3)
                {
                    tempTable = cupboardTable;
                    row = cupboardTable.getSelectedRow();
                }
                
                else if (tabbedPane.getSelectedIndex() == 4)
                {
                    tempTable = deathRowTable;
                    row = deathRowTable.getSelectedRow();
                }
                
                if (row != -1)
                {
                    // Dialog box is opened to prevent user from accidentally removing an item they didn't want removed.
                    int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove this item from the database?", "Remove Item", JOptionPane.YES_NO_OPTION);
                    if (result == 0)
                    {
                        String na = String.valueOf(tempTable.getModel().getValueAt(row, 0));
                        int quant = Integer.valueOf(String.valueOf(tempTable.getModel().getValueAt(row, 2)));
                        Date dat = Date.valueOf(String.valueOf(tempTable.getModel().getValueAt(row, 1)));
                        String loc = String.valueOf(tempTable.getModel().getValueAt(row, 3));
                        int pkey = Integer.valueOf(String.valueOf(tempTable.getModel().getValueAt(row, 4)));
                        String delete = "DELETE from food where name = '" + na + "' AND best_before_date = '" + dat + "' AND quantity = '"+ quant +"' AND location = '" + loc + "' AND id='" + pkey + "';";
                        try (PreparedStatement stat = con.prepareStatement(delete))
                        {
                            stmt.executeUpdate(delete);
                            System.out.println(delete);
                            
                            if (con != null)
                            {
                                String all = "SELECT * FROM food;";
                                String fridgeRS = "SELECT * FROM food WHERE location='Fridge';";
                                String freezerRS = "SELECT * FROM food WHERE location='Freezer';";
                                String cupboardRS = "SELECT * FROM food WHERE location='Cupboard';";
                                String deathRowRS = "SELECT * FROM food WHERE best_before_date<=CURRENT_DATE;";
                                ResultSet rs1 = stmt.executeQuery(all);
                                PreparedStatement st1 = con.prepareStatement(all);
                                table.setModel(DbUtils.resultSetToTableModel(rs1));
                                table.removeColumn(table.getColumn("ID"));
                                ResultSet rs2 = stmt.executeQuery(fridgeRS);
                                PreparedStatement st2 = con.prepareStatement(fridgeRS);
                                fridgeTable.setModel(DbUtils.resultSetToTableModel(rs2));
                                fridgeTable.removeColumn(fridgeTable.getColumn("ID"));
                                ResultSet rs3 = stmt.executeQuery(freezerRS);
                                PreparedStatement st3 = con.prepareStatement(freezerRS);
                                freezerTable.setModel(DbUtils.resultSetToTableModel(rs3));
                                freezerTable.removeColumn(freezerTable.getColumn("ID"));
                                ResultSet rs4 = stmt.executeQuery(cupboardRS);
                                PreparedStatement st4 = con.prepareStatement(cupboardRS);
                                cupboardTable.setModel(DbUtils.resultSetToTableModel(rs4));
                                cupboardTable.removeColumn(cupboardTable.getColumn("ID"));
                                ResultSet rs5 = stmt.executeQuery(deathRowRS);
                                PreparedStatement st5 = con.prepareStatement(deathRowRS);
                                deathRowTable.setModel(DbUtils.resultSetToTableModel(rs5));
                                deathRowTable.removeColumn(deathRowTable.getColumn("ID"));
                            }
                            stat.close();
                            con.commit();
                            name.setText(null);
                            bestBeforeDate.setText(null);
                            quantity.setValue(1);
                            storageLocation.setSelectedItem("Fridge");
                            lblWelcomeToThe.setVisible(true);
                            itemPanel.setVisible(false);
                            welcomeScreen.setVisible(true);
                            backgroundPanel.setVisible(true);
                        }
                        catch (SQLException e1)
                        {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    }
                }
                
                else
                {
                    // User can't remove an item if they have not selected one.
                    JOptionPane.showMessageDialog(null, "You must select a food item from the table to remove.");
                }
            }
        });
        btnRemoveItem.setBounds(623, 619, 134, 27);
        contentPane.add(btnRemoveItem);
        
        JButton btnEditItem = new JButton("Edit Item");
        btnEditItem.setIcon(new ImageIcon(window.class.getResource("/images/icons8_Edit_15px.png")));
        btnEditItem.setBackground(new Color(255, 255, 0));
        btnEditItem.setOpaque(true);
        btnEditItem.setBorderPainted(false);
        btnEditItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                adding = false;
                int row = 0;
                JTable tempTable = null;
                
                if (tabbedPane.getSelectedIndex() == 0)
                {
                    tempTable = table;
                    row = table.getSelectedRow();
                }
                
                else if (tabbedPane.getSelectedIndex() == 1)
                {
                    tempTable = fridgeTable;
                    row = fridgeTable.getSelectedRow();
                }
                
                else if (tabbedPane.getSelectedIndex() == 2)
                {
                    tempTable = freezerTable;
                    row = freezerTable.getSelectedRow();
                }
                
                else if (tabbedPane.getSelectedIndex() == 3)
                {
                    tempTable = cupboardTable;
                    row = cupboardTable.getSelectedRow();
                }
                
                else if (tabbedPane.getSelectedIndex() == 4)
                {
                    tempTable = deathRowTable;
                    row = deathRowTable.getSelectedRow();
                }
                
                if (row != -1)
                {
                    String na = String.valueOf(tempTable.getModel().getValueAt(row, 0));
                    int quant = Integer.parseInt(String.valueOf(tempTable.getModel().getValueAt(row, 2)));
                    Date dat = Date.valueOf(String.valueOf(tempTable.getModel().getValueAt(row, 1)));
                    String loc = String.valueOf(tempTable.getModel().getValueAt(row, 3));
                    System.out.println(na + " " + dat + " " + quant + " " + loc);
                    itemPanel.setVisible(true);
                    lblWelcomeToThe.setVisible(false);
                    welcomeScreen.setVisible(false);
                    backgroundPanel.setVisible(false);
                    lblAddNewItem.setText("Edit '" + na + "'");
                    quantity.setValue(quant);
                    storageLocation.setSelectedItem(loc);
                    bestBeforeDate.setText(String.valueOf(dat));
                    name.setText(na);
                }
                
                else
                {
                    JOptionPane.showMessageDialog(null, "You must select a food item from the table to edit.");
                }
            }
        });
        btnEditItem.setBounds(767, 619, 134, 27);
        contentPane.add(btnEditItem);
        
        
        welcomeScreen.setBounds(10, 66, 459, 545);
        welcomeScreen.setBackground(SystemColor.control);
        welcomeScreen.setFont(new Font("Tahoma", Font.PLAIN, 14));
        welcomeScreen.setText("The Food Management System (FMS) helps keep your food fresh by recording each food item you have stored in your home and tracking their best before date, quantity, and where they are stored in your home.\r\n\r\nFMS allows you to quickly sort through food items in your home by seperating them into the locations each item is stored: the fridge, freezer, or cupboard. To view these locations simply click on their respective tab along the top of the display table on the right and all items and their information will appear on the display. You may also view every item in your home by viewing the \"All\" tab in the display table. If you wish to quickly view items which are expired or about to expire, click on the \"Death Row\" tab.\r\n\r\nTo add an item to the FMS database, simply click \"Add New Item\" at the bottom of the database display on the right-hand side of the screen. Then, simply enter the relevant information in each field and click \"Confirm\" when you are done. If you change your mind and do not wish to add an item, simply click \"Cancel\".\r\n\r\nIf you wish to record that you have already thrown out or eaten something, simply select the item in the display table and click \"Remove Item\".\r\n\r\nOr, if you have only eaten part of a food item, say you ate one apple out of 12, you can update the system to reflect this change by selecting the item in the display table and pressing \"Edit Item\". Then you simply change the quantity (or any other value if needed) as desired and click \"Confirm\". If you change your mind and do not wish to edit an item, simply click \"Cancel\".");
        contentPane.add(welcomeScreen);
        
        
        lblWelcomeToThe.setBackground(new Color(65, 105, 225));
        lblWelcomeToThe.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblWelcomeToThe.setText("Welcome to the Food Management System!");
        lblWelcomeToThe.setBounds(10, 8, 459, 55);
        contentPane.add(lblWelcomeToThe);
        
        
        backgroundPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(65, 105, 225), new Color(65, 105, 225), new Color(65, 105, 225), new Color(65, 105, 225)));
        backgroundPanel.setBackground(new Color(255, 255, 255));
        backgroundPanel.setBounds(8, 8, 463, 608);
        contentPane.add(backgroundPanel);
        backgroundPanel.setLayout(null);
    }
}
