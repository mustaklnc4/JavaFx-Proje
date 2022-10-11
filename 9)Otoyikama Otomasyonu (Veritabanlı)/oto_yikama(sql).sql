-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Anamakine: 127.0.0.1
-- Üretim Zamanı: 30 May 2021, 14:10:29
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
-- Veritabanı: `oto_yikama`
--

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `hazır_araclar`
--

CREATE TABLE `hazır_araclar` (
  `id` int(11) NOT NULL,
  `müsteri_ad_soyad` varchar(50) NOT NULL,
  `plaka` varchar(50) NOT NULL,
  `tc` varchar(50) NOT NULL,
  `telefon` varchar(50) NOT NULL,
  `tarih_giris` varchar(50) NOT NULL,
  `tarih_cikis` varchar(50) NOT NULL,
  `paket` varchar(50) NOT NULL,
  `arac_marka` varchar(50) NOT NULL,
  `arac_model` varchar(50) NOT NULL,
  `ücret` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `hazır_araclar`
--

INSERT INTO `hazır_araclar` (`id`, `müsteri_ad_soyad`, `plaka`, `tc`, `telefon`, `tarih_giris`, `tarih_cikis`, `paket`, `arac_marka`, `arac_model`, `ücret`) VALUES
(7, 'hülya', '31AC365', '65484943510', '5462697982', '2021-05-20', '2021-05-28', 'Pasta Cila', 'Fiat', 'Doblo', 465),
(8, 'merve', '31AC365', '65484943510', '5462697982', '2021-05-20', '2021-05-28', 'Pasta Cila', 'Fiat', 'Doblo', 465),
(9, 'Enes işçen', '31AC365', '65484943510', '5462697982', '2021-05-25', '2021-05-20', 'Pasta Cila', 'Fiat', 'Doblo', 465),
(10, 'yasin demirel', 'khm856fl', '87564236980', '05413631071', '2021-05-13', '2021-05-21', 'İç Dış Yıkama', 'Fiat', 'doblo', 405);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `login`
--

CREATE TABLE `login` (
  `id` int(11) NOT NULL,
  `kul_ad` varchar(50) NOT NULL,
  `sifre` varchar(50) NOT NULL,
  `yetki` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `login`
--

INSERT INTO `login` (`id`, `kul_ad`, `sifre`, `yetki`) VALUES
(1, 'mustafa', 'mustaklnc', 'Yönetici'),
(2, 'mustaklnc', 'mustafa', 'Yönetici'),
(3, 'isteüni', 'isteüni', 'Kullanıcı');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `üye_kayit`
--

CREATE TABLE `üye_kayit` (
  `id` int(11) NOT NULL,
  `müsteri_ad_soyad` varchar(50) NOT NULL,
  `plaka` varchar(50) NOT NULL,
  `tc` varchar(50) NOT NULL,
  `telefon` varchar(50) NOT NULL,
  `tarih_giris` varchar(50) NOT NULL,
  `tarih_cikis` varchar(50) NOT NULL,
  `paket` varchar(50) NOT NULL,
  `arac_marka` varchar(50) NOT NULL,
  `arac_model` varchar(50) NOT NULL,
  `ücret` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `üye_kayit`
--

INSERT INTO `üye_kayit` (`id`, `müsteri_ad_soyad`, `plaka`, `tc`, `telefon`, `tarih_giris`, `tarih_cikis`, `paket`, `arac_marka`, `arac_model`, `ücret`) VALUES
(11, 'yasin demirel', 'khm856fl', '87564236980', '05413631071', '2021-05-25', '2021-05-29', 'İç Dış Yıkama', 'Fiat', 'doblo', 405),
(14, 'ali ak', 'khm856fl', '87564236980', '05413631071', '2021-05-13', '2021-05-21', 'İç Dış Yıkama', 'Fiat', 'doblo', 405);

--
-- Dökümü yapılmış tablolar için indeksler
--

--
-- Tablo için indeksler `hazır_araclar`
--
ALTER TABLE `hazır_araclar`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `üye_kayit`
--
ALTER TABLE `üye_kayit`
  ADD PRIMARY KEY (`id`);

--
-- Dökümü yapılmış tablolar için AUTO_INCREMENT değeri
--

--
-- Tablo için AUTO_INCREMENT değeri `hazır_araclar`
--
ALTER TABLE `hazır_araclar`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Tablo için AUTO_INCREMENT değeri `login`
--
ALTER TABLE `login`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Tablo için AUTO_INCREMENT değeri `üye_kayit`
--
ALTER TABLE `üye_kayit`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
