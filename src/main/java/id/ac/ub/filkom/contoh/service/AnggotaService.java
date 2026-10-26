package id.ac.ub.filkom.contoh.service;

import id.ac.ub.filkom.contoh.entity.Anggota;
import id.ac.ub.filkom.contoh.repository.AnggotaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/** Service: tempat logika bisnis/aturan aplikasi. */
@Service
public class AnggotaService {

    private final AnggotaRepository anggotaRepository;

    // Dependency injection lewat constructor
    public AnggotaService(AnggotaRepository anggotaRepository) {
        this.anggotaRepository = anggotaRepository;
    }

    public List<Anggota> cariAnggota(String kata) {
        if (kata == null || kata.isBlank()) {
            return anggotaRepository.findAll();
        }
        return anggotaRepository.findByNamaContainingIgnoreCase(kata.trim());
    }

    public Anggota tambahAnggota(String idAnggota, String nama, int batasPinjam) {
        return anggotaRepository.save(new Anggota(idAnggota, nama, batasPinjam));
    }
}
