-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Anamakine: 127.0.0.1
-- Üretim Zamanı: 30 May 2021, 14:10:20
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
-- Veritabanı: `kitap_satis`
--

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `kitaplar`
--

CREATE TABLE `kitaplar` (
  `id` int(11) NOT NULL,
  `kitap_adi` varchar(80) NOT NULL,
  `yazar` varchar(80) NOT NULL,
  `Kitap_türü` varchar(30) NOT NULL,
  `telefon` varchar(30) NOT NULL,
  `durum` varchar(30) NOT NULL,
  `fiyat` int(50) NOT NULL,
  `tarih` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `kitaplar`
--

INSERT INTO `kitaplar` (`id`, `kitap_adi`, `yazar`, `Kitap_türü`, `telefon`, `durum`, `fiyat`, `tarih`) VALUES
(1, 'serenad', 'Zülfü livaneli', 'Roman', '05456508925', 'Sıfır', 15, '2021-05-19'),
(5, 'serenad', 'Zülfü livaneli', 'Roman', '05456508925', 'Sıfır', 15, '2021-05-11');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `login`
--

CREATE TABLE `login` (
  `id` int(11) NOT NULL,
  `kul_ad` varchar(20) NOT NULL,
  `sifre` varchar(20) NOT NULL,
  `yetki` varchar(20) NOT NULL,
  `yorum` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `login`
--

INSERT INTO `login` (`id`, `kul_ad`, `sifre`, `yetki`, `yorum`) VALUES
(1, 'papel', 'escobar', 'Yönetici', 'Efsane otomasyon ellerinize saglık'),
(3, 'melike', 'melike', 'Yönetici', 'Gayet şık bir otomasyon'),
(4, 'escobar', 'escobar', 'Kullanıcı', 'Efsane otomasyon ellerinize saglık'),
(7, 'merve', 'merve', 'Kullanıcı', 'çok güzel bir kitap');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `satislar`
--

CREATE TABLE `satislar` (
  `id` int(11) NOT NULL,
  `kitap_adi` varchar(80) NOT NULL,
  `yazar` varchar(80) NOT NULL,
  `Kitap_türü` varchar(20) NOT NULL,
  `telefon` varchar(20) NOT NULL,
  `durum` varchar(20) NOT NULL,
  `fiyat` int(30) NOT NULL,
  `tarih` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `satislar`
--

INSERT INTO `satislar` (`id`, `kitap_adi`, `yazar`, `Kitap_türü`, `telefon`, `durum`, `fiyat`, `tarih`) VALUES
(2, 'serenad', 'Zülfü livaneli', 'Roman', '05456508925', 'Sıfır', 155, 'null'),
(3, 'Huzursuzluk', 'Zülfü livaneli', 'Roman', '05456508925', 'Sıfır', 23, '2021-05-18'),
(4, 'huzur', 'Zülfü livaneli', 'Roman', '05456508925', 'Sıfır', 58, 'null'),
(5, 'kürk mantolu madonna', 'bjmb', 'Hikaye', '054546456', 'Sıfır', 80, '2021-05-21');

--
-- Dökümü yapılmış tablolar için indeksler
--

--
-- Tablo için indeksler `kitaplar`
--
ALTER TABLE `kitaplar`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `satislar`
--
ALTER TABLE `satislar`
  ADD PRIMARY KEY (`id`);

--
-- Dökümü yapılmış tablolar için AUTO_INCREMENT değeri
--

--
-- Tablo için AUTO_INCREMENT değeri `kitaplar`
--
ALTER TABLE `kitaplar`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Tablo için AUTO_INCREMENT değeri `login`
--
ALTER TABLE `login`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Tablo için AUTO_INCREMENT değeri `satislar`
--
ALTER TABLE `satislar`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
