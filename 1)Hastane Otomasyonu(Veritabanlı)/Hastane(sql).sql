-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Anamakine: 127.0.0.1
-- Üretim Zamanı: 30 May 2021, 14:09:31
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
-- Veritabanı: `hastane`
--

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `login`
--

CREATE TABLE `login` (
  `id` int(11) NOT NULL,
  `kul_ad` varchar(50) NOT NULL,
  `sifre` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `yetki` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `login`
--

INSERT INTO `login` (`id`, `kul_ad`, `sifre`, `email`, `yetki`) VALUES
(1, 'Kılınç', 'Kılınç', 'Kılınç', 'Yönetici'),
(3, 'musta', 'musta', 'musta', 'Kullanıcı'),
(4, 'Mustafa', '172503037', 'mustaklnc4@gmail.com', 'Kullanıcı'),
(5, 'Mustafa', '172503037', 'mustaklnc4@gmail.com', 'Kullanıcı'),
(6, 'Mustafa', '172503037', 'mustaklnc4@gmail.com', 'Yönetici');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `taburcu`
--

CREATE TABLE `taburcu` (
  `id` int(11) NOT NULL,
  `hasta_ad_soyad` varchar(50) NOT NULL,
  `hasta_tc` varchar(50) NOT NULL,
  `cinsiyet` varchar(50) NOT NULL,
  `kan_grubu` varchar(50) NOT NULL,
  `poliklinik` varchar(50) NOT NULL,
  `hasta_cesidi` varchar(50) NOT NULL,
  `il` varchar(50) NOT NULL,
  `adres` varchar(50) NOT NULL,
  `sigorta_durumu` varchar(50) NOT NULL,
  `hasta_ates` int(50) NOT NULL,
  `corona` varchar(50) NOT NULL,
  `tarih` varchar(50) NOT NULL,
  `doktor` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `taburcu`
--

INSERT INTO `taburcu` (`id`, `hasta_ad_soyad`, `hasta_tc`, `cinsiyet`, `kan_grubu`, `poliklinik`, `hasta_cesidi`, `il`, `adres`, `sigorta_durumu`, `hasta_ates`, `corona`, `tarih`, `doktor`) VALUES
(1, 'aslan', '5684684648', 'Erkek', '0 Rh+', '', '', 'Gaziantep', 'aasdasdadad', 'Emekli', 38, 'Var', '', 'Ahmet Yetkin'),
(2, 'aslan', '5684684648', 'Erkek', '0 Rh +', 'Kulak Burun Boğaz', 'Kulağın dejeneratif ve vasküler bozuklukları', 'Gaziantep', 'aasdasdadad', 'Emekli', 35, 'Var', '2021-05-21', 'Ahmet Yetkin'),
(3, 'MustafaAslan', '20242950734', 'Erkek', '0 Rh +', 'Göz Hastalıkları', 'Miyop', 'AĞRI', 'Barbaros mh. 129nolu sk ADana', 'SSK', 33, 'Yok', 'null', 'Dr.Gökhan Altan');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `üye_kayıt`
--

CREATE TABLE `üye_kayıt` (
  `id` int(11) NOT NULL,
  `hasta_ad_soyad` varchar(50) NOT NULL,
  `hasta_tc` varchar(50) NOT NULL,
  `cinsiyet` varchar(50) NOT NULL,
  `kan_grubu` varchar(50) NOT NULL,
  `poliklinik` varchar(50) NOT NULL,
  `hasta_cesidi` varchar(50) NOT NULL,
  `il` varchar(50) NOT NULL,
  `adres` varchar(50) NOT NULL,
  `sigorta_durumu` varchar(50) NOT NULL,
  `hasta_ates` int(50) NOT NULL,
  `corona` varchar(50) NOT NULL,
  `tarih` varchar(50) NOT NULL,
  `doktor` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `üye_kayıt`
--

INSERT INTO `üye_kayıt` (`id`, `hasta_ad_soyad`, `hasta_tc`, `cinsiyet`, `kan_grubu`, `poliklinik`, `hasta_cesidi`, `il`, `adres`, `sigorta_durumu`, `hasta_ates`, `corona`, `tarih`, `doktor`) VALUES
(4, 'mustafa', '46546846622', 'Erkek', '0 RH -', 'Kulak Burun Boğaz', 'Kalp Damar Tıkanıklığı', 'Gaziantep', 'gaziantep', 'Emekli', 39, 'Yok', '2021-05-21', 'Ahmet Yetkin'),
(5, 'mustafa', '46546846622', 'Erkek', '0 RH -', 'Kulak Burun Boğaz', 'Kalp Damar Tıkanıklığı', 'Gaziantep', 'gaziantep', 'Emekli', 39, 'Yok', '2021-05-21', 'Ahmet Yetkin'),
(6, 'mustafa', '46546846622', 'Erkek', '0 RH -', 'Kulak Burun Boğaz', 'Kalp Damar Tıkanıklığı', 'Gaziantep', 'gaziantep', 'Emekli', 39, 'Yok', '2021-05-21', 'Ahmet Yetkin'),
(9, 'Ahmet', '1111111111', 'Erkek', '0 Rh +', 'Genel Cerrahi', 'Nefes Darlığı', 'AMASYA', 'amasya', 'Yeşil Kart', 28, 'Var', '2021-05-14', 'Dr. Yakup Kutlu');

--
-- Dökümü yapılmış tablolar için indeksler
--

--
-- Tablo için indeksler `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `taburcu`
--
ALTER TABLE `taburcu`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `üye_kayıt`
--
ALTER TABLE `üye_kayıt`
  ADD PRIMARY KEY (`id`);

--
-- Dökümü yapılmış tablolar için AUTO_INCREMENT değeri
--

--
-- Tablo için AUTO_INCREMENT değeri `login`
--
ALTER TABLE `login`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Tablo için AUTO_INCREMENT değeri `taburcu`
--
ALTER TABLE `taburcu`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Tablo için AUTO_INCREMENT değeri `üye_kayıt`
--
ALTER TABLE `üye_kayıt`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
