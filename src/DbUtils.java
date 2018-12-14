import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;
import javax.swing.table.TableModel;

// Code used to quickly update the jtable upon editing the database

public class DbUtils
{
    public static TableModel resultSetToTableModel(ResultSet rs)
    {
        try
        {
            ResultSetMetaData metaData = rs.getMetaData();
            int numberOfColumns = metaData.getColumnCount();
            Vector columnNames = new Vector();

            columnNames.addElement("Name");
            columnNames.addElement("Best Before Date");
            columnNames.addElement("Quantity");
            columnNames.addElement("Location");
            columnNames.addElement("ID");
            

            // Get all rows.
            Vector rows = new Vector();
            int numberOfRows = rows.size();

            while (rs.next())
            {
                Vector newRow = new Vector();

                for (int i = 1; i <= numberOfColumns; i++)
                {
                    newRow.addElement(rs.getObject(i));
                }

                rows.addElement(newRow);
            }

            return new MyTableModel(rows, columnNames);
        }
        catch (Exception e)
        {
            e.printStackTrace();

            return null;
        }
        
        
    }
}