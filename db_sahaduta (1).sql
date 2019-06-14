-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 14, 2019 at 11:27 AM
-- Server version: 10.1.31-MariaDB
-- PHP Version: 7.2.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_sahaduta`
--

-- --------------------------------------------------------

--
-- Table structure for table `tb_acces_menu`
--

CREATE TABLE `tb_acces_menu` (
  `id_acces` int(11) NOT NULL,
  `id_status` int(11) NOT NULL,
  `id_menu` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_acces_menu`
--

INSERT INTO `tb_acces_menu` (`id_acces`, `id_status`, `id_menu`) VALUES
(1, 1, 1),
(2, 1, 2),
(3, 2, 2),
(4, 1, 3);

-- --------------------------------------------------------

--
-- Table structure for table `tb_komentar`
--

CREATE TABLE `tb_komentar` (
  `id_komentar` int(9) NOT NULL,
  `no_rm` int(7) NOT NULL,
  `Kritik` text NOT NULL,
  `saran` text NOT NULL,
  `waktu` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_komentar`
--

INSERT INTO `tb_komentar` (`id_komentar`, `no_rm`, `Kritik`, `saran`, `waktu`) VALUES
(4, 1, 'ki', 'kii', '2019-06-14 06:23:11'),
(5, 1233, 'test', 'tess', '2019-06-14 06:30:51');

-- --------------------------------------------------------

--
-- Table structure for table `tb_menu`
--

CREATE TABLE `tb_menu` (
  `id_menu` int(11) NOT NULL,
  `menu` varchar(128) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_menu`
--

INSERT INTO `tb_menu` (`id_menu`, `menu`) VALUES
(1, 'Super Admin'),
(2, 'admin'),
(3, 'Menu');

-- --------------------------------------------------------

--
-- Table structure for table `tb_pasien`
--

CREATE TABLE `tb_pasien` (
  `no_rm` int(7) NOT NULL,
  `password` varchar(20) NOT NULL,
  `nama_pasien` varchar(50) NOT NULL,
  `tgl_lahir` date NOT NULL,
  `alamat` varchar(100) NOT NULL,
  `nama_kk` varchar(50) NOT NULL,
  `agama` enum('Islam','Kristen','Katolik','Hindu','Budha','Konghuchu') NOT NULL,
  `pendidikan` enum('SD / sederajat','SMP / sederajat','SMA / sederajat','Strata 1 (S1)','Strata 2 (S2)','Strata 3 (S3)') NOT NULL,
  `pekerjaan` enum('PNS','Swasta','Buruh') NOT NULL,
  `jenis_kelamin` enum('Laki-laki','Perempuan') NOT NULL,
  `no_hp` varchar(13) NOT NULL,
  `NIK` varchar(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_pasien`
--

INSERT INTO `tb_pasien` (`no_rm`, `password`, `nama_pasien`, `tgl_lahir`, `alamat`, `nama_kk`, `agama`, `pendidikan`, `pekerjaan`, `jenis_kelamin`, `no_hp`, `NIK`) VALUES
(2, '', 'Marco Simic Rahmatullah', '2019-04-04', 'Jl.Mawarr', 'Marco Simic Rahmatullah', 'Katolik', 'SD / sederajat', 'PNS', 'Laki-laki', '08211', '3509091212120002'),
(4, '4', 'Andrea Pirlo Hidayatullah', '2019-05-01', 'Jl.Mawar', 'Andrea Stinky', 'Kristen', 'SD / sederajat', 'PNS', 'Laki-laki', '0821217727727', '3506061512980003');

-- --------------------------------------------------------

--
-- Table structure for table `tb_pegawai`
--

CREATE TABLE `tb_pegawai` (
  `id_pegawai` int(11) NOT NULL,
  `NIK` varchar(20) NOT NULL,
  `nama_pegawai` varchar(50) NOT NULL,
  `alamat` varchar(100) NOT NULL,
  `no_hp` varchar(15) NOT NULL,
  `email` varchar(50) NOT NULL,
  `pass` varchar(255) DEFAULT NULL,
  `id_status` int(6) NOT NULL,
  `isactive` int(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_pegawai`
--

INSERT INTO `tb_pegawai` (`id_pegawai`, `NIK`, `nama_pegawai`, `alamat`, `no_hp`, `email`, `pass`, `id_status`, `isactive`) VALUES
(1, '1111', 'Anonim', 'Probolinggo', '101010', 'anonim@gmail.com', 'e10adc3949ba59abbe56e057f20f883e', 1, 1),
(3, '351314', 'admin', 'jember', '10101', 'admin@gmail.com', 'a152e841783914146e4bcd4f39100686', 2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `tb_pemesanan`
--

CREATE TABLE `tb_pemesanan` (
  `id_pemesanan` int(7) NOT NULL,
  `no_antrian` int(3) NOT NULL,
  `no_rm` int(7) NOT NULL,
  `tgl_pemesanan` date NOT NULL,
  `waktu_pemesanan` time NOT NULL,
  `id_pegawai` int(11) NOT NULL,
  `status_pemesanan` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_pemesanan`
--

INSERT INTO `tb_pemesanan` (`id_pemesanan`, `no_antrian`, `no_rm`, `tgl_pemesanan`, `waktu_pemesanan`, `id_pegawai`, `status_pemesanan`) VALUES
(1, 1, 2, '2019-05-04', '05:18:27', 0, 0),
(9, 1, 2, '2019-05-14', '00:00:00', 0, 0),
(10, 2, 2, '2019-05-14', '00:00:00', 0, 0),
(13, 1, 4, '2019-05-15', '10:16:03', 0, 0),
(14, 1, 2, '2019-05-20', '09:32:21', 0, 0),
(15, 1, 2, '2019-05-27', '12:53:13', 0, 0),
(18, 3, 4, '2019-05-27', '14:34:04', 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `tb_status`
--

CREATE TABLE `tb_status` (
  `id_status` int(11) NOT NULL,
  `nama_status` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_status`
--

INSERT INTO `tb_status` (`id_status`, `nama_status`) VALUES
(1, 'Super Admin'),
(2, 'Admin');

-- --------------------------------------------------------

--
-- Table structure for table `tb_sub_menu`
--

CREATE TABLE `tb_sub_menu` (
  `id_submenu` int(11) NOT NULL,
  `id_menu` int(11) NOT NULL,
  `judul` varchar(128) NOT NULL,
  `url` varchar(128) NOT NULL,
  `icon` varchar(128) NOT NULL,
  `is_active` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_sub_menu`
--

INSERT INTO `tb_sub_menu` (`id_submenu`, `id_menu`, `judul`, `url`, `icon`, `is_active`) VALUES
(1, 1, 'Dashboard', 'dashboard', 'fa fa-dashboard', 1),
(2, 2, 'Pasien', 'pasien', 'fa fa-wheelchair', 1),
(3, 2, 'Pemesanan', 'pemesanan', 'fa fa-history', 1),
(4, 1, 'Pegawai', 'pegawai', 'fa fa-users', 1),
(5, 1, 'Komentar', 'komentar', 'fa fa-comments-o', 1),
(6, 2, 'Laporan', 'laporan', 'fa fa-file-archive-o', 1),
(7, 3, 'Akses Admin', 'aksesadmin', 'fa fa-bar-chart-o', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tb_acces_menu`
--
ALTER TABLE `tb_acces_menu`
  ADD PRIMARY KEY (`id_acces`);

--
-- Indexes for table `tb_komentar`
--
ALTER TABLE `tb_komentar`
  ADD PRIMARY KEY (`id_komentar`);

--
-- Indexes for table `tb_menu`
--
ALTER TABLE `tb_menu`
  ADD PRIMARY KEY (`id_menu`);

--
-- Indexes for table `tb_pasien`
--
ALTER TABLE `tb_pasien`
  ADD PRIMARY KEY (`no_rm`);

--
-- Indexes for table `tb_pegawai`
--
ALTER TABLE `tb_pegawai`
  ADD PRIMARY KEY (`id_pegawai`),
  ADD KEY `id_status` (`id_status`);

--
-- Indexes for table `tb_pemesanan`
--
ALTER TABLE `tb_pemesanan`
  ADD PRIMARY KEY (`id_pemesanan`),
  ADD KEY `no_rm` (`no_rm`);

--
-- Indexes for table `tb_status`
--
ALTER TABLE `tb_status`
  ADD PRIMARY KEY (`id_status`);

--
-- Indexes for table `tb_sub_menu`
--
ALTER TABLE `tb_sub_menu`
  ADD PRIMARY KEY (`id_submenu`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tb_acces_menu`
--
ALTER TABLE `tb_acces_menu`
  MODIFY `id_acces` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `tb_komentar`
--
ALTER TABLE `tb_komentar`
  MODIFY `id_komentar` int(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `tb_menu`
--
ALTER TABLE `tb_menu`
  MODIFY `id_menu` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `tb_pasien`
--
ALTER TABLE `tb_pasien`
  MODIFY `no_rm` int(7) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `tb_pegawai`
--
ALTER TABLE `tb_pegawai`
  MODIFY `id_pegawai` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `tb_pemesanan`
--
ALTER TABLE `tb_pemesanan`
  MODIFY `id_pemesanan` int(7) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `tb_sub_menu`
--
ALTER TABLE `tb_sub_menu`
  MODIFY `id_submenu` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tb_pemesanan`
--
ALTER TABLE `tb_pemesanan`
  ADD CONSTRAINT `tb_pemesanan_ibfk_1` FOREIGN KEY (`no_rm`) REFERENCES `tb_pasien` (`no_rm`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
