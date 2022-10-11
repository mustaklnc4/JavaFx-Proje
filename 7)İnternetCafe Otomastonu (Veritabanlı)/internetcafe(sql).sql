-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Anamakine: 127.0.0.1
-- Üretim Zamanı: 30 May 2021, 14:10:05
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
-- Veritabanı: `internetcafe`
--

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `login`
--

CREATE TABLE `login` (
  `id` int(11) NOT NULL,
  `kul_ad` varchar(30) NOT NULL,
  `sifre` varchar(30) NOT NULL,
  `mail` varchar(30) NOT NULL,
  `yetki` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `login`
--

INSERT INTO `login` (`id`, `kul_ad`, `sifre`, `mail`, `yetki`) VALUES
(1, 'Musta', 'klnc', 'laptop@gmail.com', 'Yönetici'),
(2, 'iskender', 'iskender', 'iskeneder@gmail.com', 'Kullanıcı');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `masa_kayit`
--

CREATE TABLE `masa_kayit` (
  `id` int(11) NOT NULL,
  `masa_numara` varchar(30) NOT NULL,
  `masa_süre` varchar(30) NOT NULL,
  `ad_soyad` varchar(30) NOT NULL,
  `tarih` varchar(30) NOT NULL,
  `yiyecek` varchar(30) NOT NULL,
  `fiyat` int(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `masa_kayit`
--

INSERT INTO `masa_kayit` (`id`, `masa_numara`, `masa_süre`, `ad_soyad`, `tarih`, `yiyecek`, `fiyat`) VALUES
(3, 'Masa 1', '180', 'muhammet ali', '2021-05-20', 'Kahve', 13),
(5, 'Masa 7', '30 dakika', 'muhammet ali', '2021-05-20', 'kola + Tost', 5),
(6, 'Masa 13', '180', 'Emin', '2021-05-06', 'Soda', 22),
(7, 'Masa 6', '60 dakika', 'alperen', 'null', 'Çay', 18),
(8, 'Masa 15', '60 dakika', 'Emin', 'null', 'Kahve', 16);

--
-- Dökümü yapılmış tablolar için indeksler
--

--
-- Tablo için indeksler `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `masa_kayit`
--
ALTER TABLE `masa_kayit`
  ADD PRIMARY KEY (`id`);

--
-- Dökümü yapılmış tablolar için AUTO_INCREMENT değeri
--

--
-- Tablo için AUTO_INCREMENT değeri `login`
--
ALTER TABLE `login`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Tablo için AUTO_INCREMENT değeri `masa_kayit`
--
ALTER TABLE `masa_kayit`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
