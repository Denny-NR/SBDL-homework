-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 31 Bulan Mei 2021 pada 15.04
-- Versi server: 10.4.14-MariaDB
-- Versi PHP: 7.4.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `toko_buku`
--

DELIMITER $$
--
-- Prosedur
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `rataBarang` (`judul_buku` VARCHAR(250))  BEGIN
   SELECT  AVG(transaksi.jumlah_beli) AS rata_rata_penjualan, buku.judul_buku, buku.pengarang FROM buku, transaksi WHERE judul_buku = buku.judul_buku AND buku.id_buku = transaksi.id_buku;
END$$

--
-- Fungsi
--
CREATE DEFINER=`root`@`localhost` FUNCTION `total_jual` (`tgl` DATE) RETURNS INT(11) BEGIN
	DECLARE total_jual INT;
    SELECT SUM(transaksi.jumlah_beli) AS total_penjualan INTO total_jual FROM transaksi WHERE transaksi.waktu_transaksi = tgl GROUP BY transaksi.waktu_transaksi;
    RETURN total_jual;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Struktur dari tabel `buku`
--

CREATE TABLE `buku` (
  `id_buku` varchar(11) NOT NULL,
  `judul_buku` char(250) NOT NULL,
  `pengarang` varchar(250) NOT NULL,
  `penerbit` varchar(250) NOT NULL,
  `tahun_terbit` varchar(11) NOT NULL,
  `stok` int(11) NOT NULL,
  `harga` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `buku`
--

INSERT INTO `buku` (`id_buku`, `judul_buku`, `pengarang`, `penerbit`, `tahun_terbit`, `stok`, `harga`) VALUES
('BK0001', 'The eminence in shadow', 'Daisuke Aizawa', 'Kadokawa Shoten', '2018', 92, 95000),
('BK0002', 'MobuSeka', 'Yomu Mashima', 'GC Novels', '2018', 114, 100000),
('BK0003', 'Security Jaringan', 'Rifkie Primarta', 'Informatika', '2019', 43, 75000),
('BK0004', 'Seirei Gensouki ENG', 'Yuri Kitayama', 'J-Novel Club', '2015', 0, 120000);

-- --------------------------------------------------------

--
-- Stand-in struktur untuk tampilan `stok_habis`
-- (Lihat di bawah untuk tampilan aktual)
--
CREATE TABLE `stok_habis` (
`id_buku` varchar(11)
,`judul_buku` char(250)
,`pengarang` varchar(250)
,`penerbit` varchar(250)
,`tahun_terbit` varchar(11)
,`stok` int(11)
,`harga` int(11)
);

-- --------------------------------------------------------

--
-- Struktur dari tabel `transaksi`
--

CREATE TABLE `transaksi` (
  `id_transaksi` varchar(11) NOT NULL,
  `id_buku` varchar(11) NOT NULL,
  `waktu_transaksi` date NOT NULL,
  `jumlah_beli` int(11) NOT NULL,
  `jumlah_transaksi` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `transaksi`
--

INSERT INTO `transaksi` (`id_transaksi`, `id_buku`, `waktu_transaksi`, `jumlah_beli`, `jumlah_transaksi`) VALUES
('TR0001', 'BK0001', '2021-05-20', 2, 190000),
('TR0002', 'BK0001', '2021-05-21', 1, 95000),
('TR0003', 'BK0002', '2021-05-21', 3, 300000),
('TR0004', 'BK0001', '2021-05-22', 2, 190000),
('TR0005', 'BK0003', '2021-05-22', 5, 375000),
('TR0006', 'BK0002', '2021-05-24', 3, 300000),
('TR0007', 'BK0003', '2021-05-31', 2, 150000),
('TR0008', 'BK0001', '2021-05-31', 3, 285000);

--
-- Trigger `transaksi`
--
DELIMITER $$
CREATE TRIGGER `Auto_Update_stok` AFTER INSERT ON `transaksi` FOR EACH ROW BEGIN
		UPDATE buku SET stok = stok - NEW.jumlah_beli WHERE id_buku = NEW.id_buku;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Struktur untuk view `stok_habis`
--
DROP TABLE IF EXISTS `stok_habis`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `stok_habis`  AS  select `buku`.`id_buku` AS `id_buku`,`buku`.`judul_buku` AS `judul_buku`,`buku`.`pengarang` AS `pengarang`,`buku`.`penerbit` AS `penerbit`,`buku`.`tahun_terbit` AS `tahun_terbit`,`buku`.`stok` AS `stok`,`buku`.`harga` AS `harga` from `buku` where `buku`.`stok` = 0 ;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
