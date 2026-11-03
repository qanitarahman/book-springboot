CREATE TABLE IF NOT EXISTS anggota (
    id_anggota   VARCHAR(10)  NOT NULL,
    nama         VARCHAR(100) NOT NULL,
    batas_pinjam INT          NOT NULL,
    PRIMARY KEY (id_anggota)
);

CREATE TABLE IF NOT EXISTS buku (
    id_buku VARCHAR(10)  NOT NULL,
    judul   VARCHAR(200) NOT NULL,
    stok    INT          NOT NULL,
    PRIMARY KEY (id_buku)
);

CREATE TABLE IF NOT EXISTS transaksi_pinjam (
    id_transaksi    VARCHAR(10) NOT NULL,
    id_anggota      VARCHAR(10) NOT NULL,
    id_buku         VARCHAR(10) NOT NULL,
    tanggal_pinjam  DATE        NOT NULL,
    jatuh_tempo     DATE        NOT NULL,
    tanggal_kembali DATE,
    status          VARCHAR(20) NOT NULL,
    PRIMARY KEY (id_transaksi),
    FOREIGN KEY (id_anggota) REFERENCES anggota(id_anggota),
    FOREIGN KEY (id_buku)    REFERENCES buku(id_buku)
);
