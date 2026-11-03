package id.ac.ub.filkom.perpustakaan.control;

import id.ac.ub.filkom.perpustakaan.entity.Anggota;
import id.ac.ub.filkom.perpustakaan.entity.AnggotaRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KelolaAnggotaService {

    private final AnggotaRepository anggotaRepository;

    public KelolaAnggotaService(AnggotaRepository anggotaRepository) {
        this.anggotaRepository = anggotaRepository;
    }

    public List<Anggota> daftarAnggota() { return anggotaRepository.findAll(); }

    public void tambahAnggota(String idAnggota, String nama, int batasPinjam) {
        anggotaRepository.save(new Anggota(idAnggota, nama, batasPinjam));
    }

    public String hapusAnggota(String idAnggota) {
        try {
            anggotaRepository.deleteById(idAnggota);
            return null;
        } catch (DataIntegrityViolationException e) {
            return "Anggota tidak dapat dihapus karena masih memiliki transaksi";
        }
    }
}
