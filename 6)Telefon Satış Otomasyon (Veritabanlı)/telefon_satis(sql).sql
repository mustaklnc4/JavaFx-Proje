-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Anamakine: 127.0.0.1
-- Üretim Zamanı: 30 May 2021, 14:10:39
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
-- Veritabanı: `telefon`
--

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `gir`
--

CREATE TABLE `gir` (
  `id` int(11) NOT NULL,
  `kul_ad` varchar(25) NOT NULL,
  `sifre` varchar(25) NOT NULL,
  `yetki` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `gir`
--

INSERT INTO `gir` (`id`, `kul_ad`, `sifre`, `yetki`) VALUES
(1, 'enes', 'enes', 'Yönetici'),
(2, 'mali', '12345', 'Kullanıcı'),
(3, 'mehmet', 'mehmet', 'Kullanıcı'),
(4, 'güzel', 'enes', 'Kullanıcı'),
(5, 'güzel', 'güzel', 'Kullanıcı'),
(6, 'enes', 'enes', 'Kullanıcı');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `telefon`
--

CREATE TABLE `telefon` (
  `id` int(11) NOT NULL,
  `ad` varchar(25) NOT NULL,
  `marka` varchar(25) NOT NULL,
  `model` varchar(25) NOT NULL,
  `adet` int(25) NOT NULL,
  `tarih` varchar(25) NOT NULL,
  `ücret` int(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `telefon`
--

INSERT INTO `telefon` (`id`, `ad`, `marka`, `model`, `adet`, `tarih`, `ücret`) VALUES
(3, 'cevher', 'LG', 'K10', 10, '2021-04-29', 3383),
(4, 'ibrahim', 'İphone', 'iPhone XR', 10, '2021-05-13', 8217),
(6, 'halil', 'LG', 'P20', 30, '2021-03-26', 6650),
(7, 'mahmut', 'Samsung', 'Galaxy A51', 10, '2021-05-12', 5617),
(8, 'salih', 'Samsung', 'Galaxy A51', 60, '2021-05-13', 7050);

--
-- Dökümü yapılmış tablolar için indeksler
--

--
-- Tablo için indeksler `gir`
--
ALTER TABLE `gir`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `telefon`
--
ALTER TABLE `telefon`
  ADD PRIMARY KEY (`id`);

--
-- Dökümü yapılmış tablolar için AUTO_INCREMENT değeri
--

--
-- Tablo için AUTO_INCREMENT değeri `gir`
--
ALTER TABLE `gir`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Tablo için AUTO_INCREMENT değeri `telefon`
--
ALTER TABLE `telefon`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
