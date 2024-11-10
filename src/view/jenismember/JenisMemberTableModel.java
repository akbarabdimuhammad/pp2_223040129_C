package view.jenismember;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.JenisMember;

public class JenisMemberTableModel extends AbstractTableModel {

    private final List<JenisMember> jenisMemberList;
    private final String[] columnNames = {"ID", "Nama"};

    public JenisMemberTableModel(List<JenisMember> jenisMemberList) {
        this.jenisMemberList = jenisMemberList;
    }

    @Override
    public int getRowCount() {
        return jenisMemberList.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        JenisMember jenisMember = jenisMemberList.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> jenisMember.getId();
            case 1 -> jenisMember.getNama();
            default -> null;
        };
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    // Fungsi untuk menambahkan jenis member baru ke dalam tabel
    public void add(JenisMember jenisMember) {
        jenisMemberList.add(jenisMember);
        fireTableRowsInserted(jenisMemberList.size() - 1, jenisMemberList.size() - 1);
    }

    // Fungsi untuk memperbarui data tabel setelah operasi update
    public void update(int rowIndex, JenisMember jenisMember) {
        jenisMemberList.set(rowIndex, jenisMember); // Update data dalam list
        fireTableRowsUpdated(rowIndex, rowIndex); // Memberitahu tabel untuk memperbarui baris
    }

    // Fungsi untuk menghapus data dari tabel setelah operasi delete
    public void remove(int rowIndex) {
        jenisMemberList.remove(rowIndex); // Hapus data dari list
        fireTableRowsDeleted(rowIndex, rowIndex); // Memberitahu tabel untuk menghapus baris
    }
}
