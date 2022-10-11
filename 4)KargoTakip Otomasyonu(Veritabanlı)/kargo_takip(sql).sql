-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Anamakine: 127.0.0.1
-- Üretim Zamanı: 30 May 2021, 14:10:13
-- Sunucu sürümü: 10.4.17-MariaDB
-- PHP Sürümü: 8.0.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Veritabanı: `kargo`
--

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `giris`
--

CREATE TABLE `giris` (
  `id` int(11) NOT NULL,
  `adi` varchar(30) NOT NULL,
  `Mail` varchar(30) NOT NULL,
  `Sifre` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `giris`
--

INSERT INTO `giris` (`id`, `adi`, `Mail`, `Sifre`) VALUES
(1, 'oguz', 'oguz', 'oguz'),
(2, '', '', ''),
(3, 'doruk', 'dorukk', '12345');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `kargo`
--

CREATE TABLE `kargo` (
  `id` int(11) NOT NULL,
  `faturaNo` int(30) NOT NULL,
  `GöndericiTc` varchar(30) NOT NULL,
  `AliciTc` varchar(30) NOT NULL,
  `Göndericiİsim` varchar(30) NOT NULL,
  `Aliciİsim` varchar(30) NOT NULL,
  `GöndericiTel` varchar(30) NOT NULL,
  `AliciTel` varchar(30) NOT NULL,
  `Urun` varchar(30) NOT NULL,
  `Adet` int(30) NOT NULL,
  `Agirlik` int(30) NOT NULL,
  `fiyat` int(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `kargo`
--

INSERT INTO `kargo` (`id`, `faturaNo`, `GöndericiTc`, `AliciTc`, `Göndericiİsim`, `Aliciİsim`, `GöndericiTel`, `AliciTel`, `Urun`, `Adet`, `Agirlik`, `fiyat`) VALUES
(1, 25545, '5858884812', '8449554815', 'gülbahar', 'ata', '0545626150', '0545606152', 'paket', 1, 3, 20),
(2, 3625, '5846561213', '8468464646', 'oguz', 'oguzca', '548448464684', '574846464', 'Paket', 10, 2, 20),
(3, 4587, '5864646464', '979794516153', 'mudm', 'gfhfgscg', '54649844', '54446413446', 'paket', 10, 3, 30),
(4, 26548, '47545869811', '45769858711', 'esra öz', 'doruk öz', '05479875636', '05476895452', 'Koli', 5, 10, 50);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `kargodetay`
--

CREATE TABLE `kargodetay` (
  `id` int(11) NOT NULL,
  `faturaNo` int(30) NOT NULL,
  `gAdi` varchar(30) NOT NULL,
  `gAdres` varchar(30) NOT NULL,
  `aAdi` varchar(30) NOT NULL,
  `aAdres` varchar(30) NOT NULL,
  `urun` varchar(30) NOT NULL,
  `ucret` varchar(30) NOT NULL,
  `durum` varchar(30) NOT NULL,
  `yer` varchar(30) NOT NULL,
  `sehir` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `kargodetay`
--

INSERT INTO `kargodetay` (`id`, `faturaNo`, `gAdi`, `gAdres`, `aAdi`, `aAdres`, `urun`, `ucret`, `durum`, `yer`, `sehir`) VALUES
(1, 25545, 'oguz', 'oguzhan', 'oguz', 'denizciler mahallesi', 'paket', '20', 'Dagitimda', 'Hatay', 'Hatay'),
(2, 34545, 'emir', 'mustafa kemal mahallesi', 'emrah', 'denizciler mahallesi', 'paket', '20', 'Yolda', 'Igdir', 'Hatay');

--
-- Dökümü yapılmış tablolar için indeksler
--

--
-- Tablo için indeksler `giris`
--
ALTER TABLE `giris`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `kargo`
--
ALTER TABLE `kargo`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `kargodetay`
--
ALTER TABLE `kargodetay`
  ADD PRIMARY KEY (`id`);

--
-- Dökümü yapılmış tablolar için AUTO_INCREMENT değeri
--

--
-- Tablo için AUTO_INCREMENT değeri `giris`
--
ALTER TABLE `giris`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Tablo için AUTO_INCREMENT değeri `kargo`
--
ALTER TABLE `kargo`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Tablo için AUTO_INCREMENT değeri `kargodetay`
--
ALTER TABLE `kargodetay`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
