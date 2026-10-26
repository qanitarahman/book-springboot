package id.ac.ub.filkom.contoh.repository;

import id.ac.ub.filkom.contoh.entity.Anggota;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository: akses data ke database.
 * Cukup interface; implementasinya dibuat otomatis oleh Spring.
 * Method bawaan: findAll(), findById(), save(), deleteById(), ...
 */
public interface AnggotaRepository extends JpaRepository<Anggota, String> {

    // Query turunan dari nama method:
    // SELECT * FROM anggota WHERE nama LIKE %kata%
    List<Anggota> findByNamaContainingIgnoreCase(String kata);
}
