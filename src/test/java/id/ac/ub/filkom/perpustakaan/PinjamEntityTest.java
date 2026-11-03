package id.ac.ub.filkom.perpustakaan;

import id.ac.ub.filkom.perpustakaan.entity.Anggota;
import id.ac.ub.filkom.perpustakaan.entity.Buku;
import id.ac.ub.filkom.perpustakaan.entity.TransaksiPinjam;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Aturan Bisnis Entity Perpustakaan")
class PinjamEntityTest {

    @Nested
    @DisplayName("Buku")
    class BukuTest {

        @Test
        @DisplayName("cekStok() true jika stok > 0")
        void cekStok_stokPositif_returnsTrue() {
            Buku buku = new Buku("B001", "Pemrograman Java", 3);
            assertTrue(buku.cekStok());
        }

        @Test
        @DisplayName("cekStok() false jika stok = 0")
        void cekStok_stokNol_returnsFalse() {
            Buku buku = new Buku("B002", "Rekayasa Perangkat Lunak", 0);
            assertFalse(buku.cekStok());
        }

        @Test
        @DisplayName("kurangiStok() mengurangi stok sebesar 1")
        void kurangiStok_stokBerkurang() {
            Buku buku = new Buku("B001", "Pemrograman Java", 3);
            buku.kurangiStok();
            assertEquals(2, buku.getStok());
        }

        @Test
        @DisplayName("kurangiStok() lempar exception jika stok = 0")
        void kurangiStok_stokNol_throwsException() {
            Buku buku = new Buku("B002", "Rekayasa Perangkat Lunak", 0);
            assertThrows(IllegalStateException.class, buku::kurangiStok);
        }
    }

    @Nested
    @DisplayName("Anggota")
    class AnggotaTest {

        @Test
        @DisplayName("cekBatasPinjam() true jika belum melewati batas")
        void cekBatasPinjam_belumMelewatiBatas_returnsTrue() {
            Anggota anggota = new Anggota("A001", "Budi Santoso", 2);
            TransaksiPinjam.buat(anggota, new Buku("B001", "Pemrograman Java", 3));
            assertTrue(anggota.cekBatasPinjam());
        }

        @Test
        @DisplayName("cekBatasPinjam() false jika sudah melewati batas")
        void cekBatasPinjam_sudahMelewatiBatas_returnsFalse() {
            Anggota anggota = new Anggota("A002", "Sari Dewi", 1);
            TransaksiPinjam.buat(anggota, new Buku("B001", "Pemrograman Java", 3));
            assertFalse(anggota.cekBatasPinjam());
        }
    }

    @Nested
    @DisplayName("TransaksiPinjam")
    class TransaksiPinjamTest {

        @Test
        @DisplayName("buat() menghasilkan transaksi berstatus DIPINJAM dengan id berawalan T")
        void buat_statusDipinjamDanIdValid() {
            Anggota anggota = new Anggota("A001", "Budi Santoso", 2);
            Buku buku = new Buku("B001", "Pemrograman Java", 3);
            TransaksiPinjam t = TransaksiPinjam.buat(anggota, buku);
            assertTrue(t.isDipinjam());
            assertTrue(t.getIdTransaksi().startsWith("T"));
        }
    }
}
