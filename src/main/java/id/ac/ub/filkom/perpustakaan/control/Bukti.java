package id.ac.ub.filkom.perpustakaan.control;

import id.ac.ub.filkom.perpustakaan.entity.TransaksiPinjam;

import java.time.LocalDate;

public class Bukti {

    private final String idTransaksi;
    private final String namaAnggota;
    private final String judulBuku;
    private final LocalDate tanggalPinjam;
    private final LocalDate jatuhTempo;
    private final String status;

    private Bukti(String idTransaksi, String namaAnggota, String judulBuku,
                  LocalDate tanggalPinjam, LocalDate jatuhTempo, String status) {
        this.idTransaksi   = idTransaksi;
        this.namaAnggota   = namaAnggota;
        this.judulBuku     = judulBuku;
        this.tanggalPinjam = tanggalPinjam;
        this.jatuhTempo    = jatuhTempo;
        this.status        = status;
    }

    public static Bukti dari(TransaksiPinjam t) {
        return new Bukti(
                t.getIdTransaksi(),
                t.getAnggota().getNama(),
                t.getBuku().getJudul(),
                t.getTanggalPinjam(),
                t.getJatuhTempo(),
                t.getStatus()
        );
    }

    public String getIdTransaksi()      { return idTransaksi; }
    public String getNamaAnggota()      { return namaAnggota; }
    public String getJudulBuku()        { return judulBuku; }
    public LocalDate getTanggalPinjam() { return tanggalPinjam; }
    public LocalDate getJatuhTempo()    { return jatuhTempo; }
    public String getStatus()           { return status; }
}
