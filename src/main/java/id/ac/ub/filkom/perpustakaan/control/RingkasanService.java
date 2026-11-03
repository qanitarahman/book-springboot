package id.ac.ub.filkom.perpustakaan.control;

import id.ac.ub.filkom.perpustakaan.entity.AnggotaRepository;
import id.ac.ub.filkom.perpustakaan.entity.BukuRepository;
import id.ac.ub.filkom.perpustakaan.entity.TransaksiPinjam;
import id.ac.ub.filkom.perpustakaan.entity.TransaksiPinjamRepository;
import org.springframework.stereotype.Service;

@Service
public class RingkasanService {

    private final BukuRepository bukuRepository;
    private final AnggotaRepository anggotaRepository;
    private final TransaksiPinjamRepository transaksiRepository;

    public RingkasanService(BukuRepository bukuRepository,
                            AnggotaRepository anggotaRepository,
                            TransaksiPinjamRepository transaksiRepository) {
        this.bukuRepository      = bukuRepository;
        this.anggotaRepository   = anggotaRepository;
        this.transaksiRepository = transaksiRepository;
    }

    public int totalStokBuku() {
        return bukuRepository.findAll().stream().mapToInt(b -> b.getStok()).sum();
    }

    public long jumlahAnggota() {
        return anggotaRepository.count();
    }

    public long jumlahSedangDipinjam() {
        return transaksiRepository.findByStatus(TransaksiPinjam.DIPINJAM).size();
    }

    public long jumlahTerlambat() {
        return transaksiRepository.findByStatus(TransaksiPinjam.DIPINJAM)
                .stream().filter(TransaksiPinjam::isTerlambat).count();
    }
}
