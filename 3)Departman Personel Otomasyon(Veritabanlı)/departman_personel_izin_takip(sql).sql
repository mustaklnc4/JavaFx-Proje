-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Anamakine: 127.0.0.1
-- Üretim Zamanı: 30 May 2021, 16:23:04
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
-- Veritabanı: `personel_izin_takip`
--

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `izinler`
--

CREATE TABLE `izinler` (
  `id` int(11) NOT NULL,
  `ad` varchar(50) NOT NULL,
  `soyad` varchar(50) NOT NULL,
  `tc` varchar(50) NOT NULL,
  `sicilno` varchar(50) NOT NULL,
  `telefon` varchar(50) NOT NULL,
  `adres` varchar(50) NOT NULL,
  `departman` varchar(50) NOT NULL,
  `durum` varchar(50) NOT NULL,
  `tarih_baslangic` varchar(50) NOT NULL,
  `tarih_bitis` varchar(50) NOT NULL,
  `iban` varchar(50) NOT NULL,
  `maaş` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `izinler`
--

INSERT INTO `izinler` (`id`, `ad`, `soyad`, `tc`, `sicilno`, `telefon`, `adres`, `departman`, `durum`, `tarih_baslangic`, `tarih_bitis`, `iban`, `maaş`) VALUES
(8, 'Mehmet', 'Kömür', '584623959720', '120', '05456308923', 'akabe mahallesi 19000 sokak no 88', 'Memur', 'Çalısıyor', '2021-05-08', '2021-05-23', '469497313549794313549', 5000),
(9, 'gülnur', 'kömür', '968623959720', '130', '05453211327', 'akabe mahallesi 19000 sokak no 88', 'Memur', 'izinli', '2021-05-08', '2021-05-23', '469497313549794313549', 5000),
(11, 'nihat', 'kömür', '64649431310', '15', '5462697989', 'mustafacık mahhalesi 1963 sokak no 69', 'Memur', 'izinli', '2021-05-30', '2021-05-28', '65894816131899216516979914', 7733);

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
(1, 'mustafa', 'klnc', 'Yönetici'),
(2, 'gülnur', 'yardimci', 'Kullanıcı'),
(3, 'nihat', 'kömür', 'Yönetici'),
(4, 'halil', 'kömür', 'Kullanıcı');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `personel`
--

CREATE TABLE `personel` (
  `id` int(11) NOT NULL,
  `ad` varchar(50) NOT NULL,
  `soyad` varchar(50) NOT NULL,
  `tc` varchar(50) NOT NULL,
  `sicilno` varchar(50) NOT NULL,
  `telefon` varchar(50) NOT NULL,
  `adres` varchar(50) NOT NULL,
  `departman` varchar(50) NOT NULL,
  `durum` varchar(50) NOT NULL,
  `tarih_baslangic` varchar(50) NOT NULL,
  `iban` varchar(50) NOT NULL,
  `maaş` varchar(50) NOT NULL,
  `tarih_bitis` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `personel`
--

INSERT INTO `personel` (`id`, `ad`, `soyad`, `tc`, `sicilno`, `telefon`, `adres`, `departman`, `durum`, `tarih_baslangic`, `iban`, `maaş`, `tarih_bitis`) VALUES
(9, 'halil', 'kömür', '64649431310', '11', '5462697982', 'mustafacık mahhalesi 1963 sokak no 69', 'Yazılım', 'izinli', '2021-05-28', '65894816131899216516979914', '8298.079136690649', '2021-05-28'),
(14, 'mehmet', 'mehmet', '64649431310', '11', '5462697982', 'mustafacık mahhalesi 1963 sokak no 69', 'Yazılım', 'Çalısıyor', '2021-05-26', '65894816131899216516979914', '8300.0', '2021-05-13'),
(18, 'gülnur', 'kömür', '646497878', '15', '5462698926', 'antep 1963 sokak no 69', 'Temizlik Personel', 'Çalısıyor', '2021-05-27', '65894816131899216516979914', '4966', '2021-05-20');

--
-- Dökümü yapılmış tablolar için indeksler
--

--
-- Tablo için indeksler `izinler`
--
ALTER TABLE `izinler`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `personel`
--
ALTER TABLE `personel`
  ADD PRIMARY KEY (`id`);

--
-- Dökümü yapılmış tablolar için AUTO_INCREMENT değeri
--

--
-- Tablo için AUTO_INCREMENT değeri `izinler`
--
ALTER TABLE `izinler`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- Tablo için AUTO_INCREMENT değeri `login`
--
ALTER TABLE `login`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Tablo için AUTO_INCREMENT değeri `personel`
--
ALTER TABLE `personel`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
