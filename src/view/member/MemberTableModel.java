package view.member;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Member;

public class MemberTableModel extends AbstractTableModel {
    private final String[] columnNames = {"Nama", "Jenis Member"};
    private final List<Member> data;

    public MemberTableModel(List<Member> data) {
        this.data = data;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Object getValueAt(int row, int col) {
        Member rowItem = data.get(row);
        return switch (col) {
            case 0 -> rowItem.getNama();
            case 1 -> rowItem.getJenisMember().getNama();
            default -> "";
        };
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    public void add(Member value) {
        data.add(value);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }

    // Update member at the given row index
    public void update(int rowIndex, Member value) {
        data.set(rowIndex, value);  // Update the data list
        fireTableRowsUpdated(rowIndex, rowIndex);  // Notify the table to update the row
    }

    // Remove member at the given row index
    public void remove(int rowIndex) {
        data.remove(rowIndex);  // Remove the member from the data list
        fireTableRowsDeleted(rowIndex, rowIndex);  // Notify the table to remove the row
    }
}
