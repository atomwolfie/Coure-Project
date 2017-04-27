import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;


public class Report {
    private JFrame frame;
    private JTable table;
    private JLabel lblTotal;
    private JButton btnPay;
    private JTextField txtId;
    private JTextField txtname;
    private JTextField textField;
    private Order currentOrder;
    private DefaultTableModel model;
    private DecimalFormat dec;
    private Employee curEmployee;
    /**
     * Constructor
     */
    Report() {
        initialize();
    }

    /**
     * Constructor with employee
     */
    Report(Employee employee) {
        this.curEmployee = employee;
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        dec = new DecimalFormat("#.00");
        frame = new JFrame();
        frame.setBounds(400, 100, 900, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("Report");
        lblNewLabel.setBounds(425, 6, 141, 16);
        frame.getContentPane().add(lblNewLabel);

        JButton btnGoBack = new JButton("go back");
        btnGoBack.setBounds(700, 610, 117, 29);
        frame.getContentPane().add(btnGoBack);

        JButton btnCustNameSort = new JButton("Sort by Name");
        btnCustNameSort.setBounds(70, 50, 160, 30);
        frame.getContentPane().add(btnCustNameSort);

        JButton btnPaymentSort = new JButton("Sort by Method");
        btnPaymentSort.setBounds(270, 50, 160, 30);
        frame.getContentPane().add(btnPaymentSort);

        JButton btnTotalSort = new JButton("Sort by Total");
        btnTotalSort.setBounds(470, 50, 160, 30);
        frame.getContentPane().add(btnTotalSort);

        JButton btnTimeSort = new JButton("Sort by Time");
        btnTimeSort.setBounds(670, 50, 160, 30);
        frame.getContentPane().add(btnTimeSort);

        String[] columnNames = {"Customer Name","Payment Method","Order Total","Date Time"};
        String[][] Data = {};

        TableModel modelo = new DefaultTableModel(Data, columnNames)
        {
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };
        table = new JTable(modelo);
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.LEFT);
        table.getColumnModel().getColumn(0).setCellRenderer(leftRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
        table.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
        model = (DefaultTableModel) table.getModel();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50,95,800,450);
        frame.getContentPane().add(scrollPane);


        ActionListener buttonListener = new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                if (e.getSource() == btnGoBack) { //return to main screen
                    frame.setVisible(false);
                    MainScreen main = new MainScreen(curEmployee);
                    main.setVisible(true);
                    frame.dispose();
                }
            }
        };

        btnGoBack.addActionListener(buttonListener);
    }

    public void populateTable () {

    }

    public void setVisible(boolean b) {
        frame.setVisible(b);
    }
}
