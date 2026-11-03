INSERT INTO anggota (id_anggota, nama, batas_pinjam) VALUES ('A001', 'Budi Santoso', 2);
INSERT INTO anggota (id_anggota, nama, batas_pinjam) VALUES ('A002', 'Sari Dewi', 1);

-- stok B004 = 4 (awal) - 1 (T00000002 masih DIPINJAM) = 3
INSERT INTO buku (id_buku, judul, stok) VALUES ('B001', 'Pemrograman Java', 3);
INSERT INTO buku (id_buku, judul, stok) VALUES ('B002', 'Rekayasa Perangkat Lunak', 0);
INSERT INTO buku (id_buku, judul, stok) VALUES ('B003', 'Basis Data', 2);
INSERT INTO buku (id_buku, judul, stok) VALUES ('B004', 'Analisis dan Perancangan Sistem', 3);

-- T00000001: sudah dikembalikan (riwayat)
INSERT INTO transaksi_pinjam (id_transaksi, id_anggota, id_buku, tanggal_pinjam, jatuh_tempo, tanggal_kembali, status)
VALUES ('T00000001', 'A002', 'B003', DATEADD('DAY', -10, CURRENT_DATE), DATEADD('DAY', -3, CURRENT_DATE), CURRENT_DATE, 'DIKEMBALIKAN');

-- T00000002: masih dipinjam dan sudah lewat jatuh tempo (badge Terlambat)
INSERT INTO transaksi_pinjam (id_transaksi, id_anggota, id_buku, tanggal_pinjam, jatuh_tempo, tanggal_kembali, status)
VALUES ('T00000002', 'A001', 'B004', DATEADD('DAY', -9, CURRENT_DATE), DATEADD('DAY', -2, CURRENT_DATE), NULL, 'DIPINJAM');
