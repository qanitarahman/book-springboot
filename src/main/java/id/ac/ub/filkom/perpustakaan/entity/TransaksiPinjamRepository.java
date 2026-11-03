package id.ac.ub.filkom.perpustakaan.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransaksiPinjamRepository extends JpaRepository<TransaksiPinjam, String> {
    List<TransaksiPinjam> findByStatus(String status);
    List<TransaksiPinjam> findByStatusNot(String status);
}
