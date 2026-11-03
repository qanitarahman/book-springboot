package id.ac.ub.filkom.perpustakaan.control;

import id.ac.ub.filkom.perpustakaan.entity.Buku;
import id.ac.ub.filkom.perpustakaan.entity.BukuRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KelolaBukuService {

    private final BukuRepository bukuRepository;

    public KelolaBukuService(BukuRepository bukuRepository) {
        this.bukuRepository = bukuRepository;
    }

    public List<Buku> daftarBuku() { return bukuRepository.findAll(); }

    public void tambahBuku(String idBuku, String judul, int stok) {
        bukuRepository.save(new Buku(idBuku, judul, stok));
    }

    public String hapusBuku(String idBuku) {
        try {
            bukuRepository.deleteById(idBuku);
            return null;
        } catch (DataIntegrityViolationException e) {
            return "Buku tidak dapat dihapus karena masih memiliki transaksi";
        }
    }
}
