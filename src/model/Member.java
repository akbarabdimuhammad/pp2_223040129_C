package model;

public class Member {
    private String id;
    private String nama;
    private JenisMember jenisMember;  // Relasi ke jenis_member

    // Getter dan Setter untuk id
    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    // Getter dan Setter untuk nama
    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }

    // Getter dan Setter untuk jenisMember
    public void setJenisMember(JenisMember jenisMember) {
        this.jenisMember = jenisMember;
    }

    public JenisMember getJenisMember() {
        return jenisMember;
    }
}
