package id.ac.ub.filkom.perpustakaan.control;

import id.ac.ub.filkom.perpustakaan.entity.Anggota;
import id.ac.ub.filkom.perpustakaan.entity.AnggotaRepository;
import id.ac.ub.filkom.perpustakaan.entity.Buku;
import id.ac.ub.filkom.perpustakaan.entity.BukuRepository;
import id.ac.ub.filkom.perpustakaan.entity.TransaksiPinjam;
import id.ac.ub.filkom.perpustakaan.entity.TransaksiPinjamRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PinjamService {

    private final AnggotaRepository anggotaRepository;
    private final BukuRepository bukuRepository;
    private final TransaksiPinjamRepository transaksiRepository;

    public PinjamService(AnggotaRepository anggotaRepository,
                         BukuRepository bukuRepository,
                         TransaksiPinjamRepository transaksiRepository) {
        this.anggotaRepository   = anggotaRepository;
        this.bukuRepository      = bukuRepository;
        this.transaksiRepository = transaksiRepository;
    }

    public List<Buku> daftarBuku()       { return bukuRepository.findAll(); }
    public List<Anggota> daftarAnggota() { return anggotaRepository.findAll(); }

    public List<TransaksiPinjam> daftarSedangDipinjam() {
        return transaksiRepository.findByStatus(TransaksiPinjam.DIPINJAM);
    }

    public List<TransaksiPinjam> daftarRiwayat() {
        return transaksiRepository.findByStatusNot(TransaksiPinjam.DIPINJAM);
    }

    @Transactional
    public Bukti pinjamBuku(String idAnggota, String idBuku) {
        Anggota anggota = anggotaRepository.findById(idAnggota)
                .orElseThrow(() -> PinjamGagalException.tidakDitemukan("Anggota"));
        Buku buku = bukuRepository.findById(idBuku)
                .orElseThrow(() -> PinjamGagalException.tidakDitemukan("Buku"));

        if (!anggota.cekBatasPinjam()) throw PinjamGagalException.melewatiBatas();
        if (!buku.cekStok())           throw PinjamGagalException.stokHabis();

        TransaksiPinjam transaksi = TransaksiPinjam.buat(anggota, buku);
        transaksiRepository.save(transaksi);

        buku.kurangiStok();
        bukuRepository.save(buku);

        return Bukti.dari(transaksi);
    }

    @Transactional
    public void kembalikanBuku(String idTransaksi) {
        TransaksiPinjam transaksi = transaksiRepository.findById(idTransaksi)
                .orElseThrow(() -> PinjamGagalException.tidakDitemukan("Transaksi"));
        try {
            transaksi.kembalikan();
        } catch (IllegalStateException e) {
            throw PinjamGagalException.sudahDikembalikan();
        }
        transaksi.getBuku().tambahStok();
        bukuRepository.save(transaksi.getBuku());
        transaksiRepository.save(transaksi);
    }
}
