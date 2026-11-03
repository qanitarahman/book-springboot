package id.ac.ub.filkom.perpustakaan.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "transaksi_pinjam")
public class TransaksiPinjam {

    public static final String DIPINJAM     = "DIPINJAM";
    public static final String DIKEMBALIKAN = "DIKEMBALIKAN";
    public static final int    LAMA_PINJAM_HARI = 7;

    @Id
    @Column(name = "id_transaksi", length = 10)
    private String idTransaksi;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_anggota")
    private Anggota anggota;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_buku")
    private Buku buku;

    @Column(name = "tanggal_pinjam", nullable = false)
    private LocalDate tanggalPinjam;

    @Column(name = "jatuh_tempo", nullable = false)
    private LocalDate jatuhTempo;

    @Column(name = "tanggal_kembali")
    private LocalDate tanggalKembali;

    @Column(name = "status", length = 20, nullable = false)
    private String status;

    protected TransaksiPinjam() {}

    public static TransaksiPinjam buat(Anggota anggota, Buku buku) {
        TransaksiPinjam t = new TransaksiPinjam();
        t.idTransaksi  = "T" + UUID.randomUUID().toString().replace("-", "").substring(0, 8).toUpperCase();
        t.anggota      = anggota;
        t.buku         = buku;
        t.tanggalPinjam = LocalDate.now();
        t.jatuhTempo   = t.tanggalPinjam.plusDays(LAMA_PINJAM_HARI);
        t.status       = DIPINJAM;
        anggota.catatPinjaman(t);
        return t;
    }

    public boolean isDipinjam()  { return DIPINJAM.equals(status); }

    public boolean isTerlambat() {
        return isDipinjam() && LocalDate.now().isAfter(jatuhTempo);
    }

    public void kembalikan() {
        if (!isDipinjam()) throw new IllegalStateException("Buku sudah dikembalikan");
        status         = DIKEMBALIKAN;
        tanggalKembali = LocalDate.now();
    }

    public String getIdTransaksi()        { return idTransaksi; }
    public Anggota getAnggota()           { return anggota; }
    public Buku getBuku()                 { return buku; }
    public LocalDate getTanggalPinjam()   { return tanggalPinjam; }
    public LocalDate getJatuhTempo()      { return jatuhTempo; }
    public LocalDate getTanggalKembali()  { return tanggalKembali; }
    public String getStatus()             { return status; }
}
