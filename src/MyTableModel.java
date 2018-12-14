import java.util.Vector;
import javax.swing.table.DefaultTableModel;

// This code is used to make jtable rows no editable when the user updates the database
// by adding, editing, or removing an item in the database.

public class MyTableModel extends DefaultTableModel
{
    public MyTableModel(Vector rows, Vector columnNames)
    {
        super(rows, columnNames);
    }

    public boolean isCellEditable(int row, int column)
    {
        return false;
    }
}
