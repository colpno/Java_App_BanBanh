-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 17, 2021 at 07:05 AM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.4.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `banhang`
--

-- --------------------------------------------------------

--
-- Table structure for table `chitiethoadon`
--

CREATE TABLE `chitiethoadon` (
  `maHD` int(5) UNSIGNED NOT NULL,
  `maSP` int(5) UNSIGNED NOT NULL,
  `soLuong` int(5) UNSIGNED NOT NULL,
  `donGia` int(10) NOT NULL,
  `thanhTien` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `chitiethoadon`
--

INSERT INTO `chitiethoadon` (`maHD`, `maSP`, `soLuong`, `donGia`, `thanhTien`) VALUES
(1, 1, 3958, 269894, 3557808),
(2, 2, 85813, 729180, 6011420),
(3, 3, 288, 969359, 9487247),
(4, 4, 32591, 619133, 4540030),
(5, 5, 40670, 428214, 6692729),
(6, 6, 60223, 502123, 6429356),
(7, 7, 44539, 817222, 3038581),
(8, 8, 62152, 428261, 9481038),
(9, 9, 22738, 850913, 7772255),
(10, 10, 9976, 436166, 5726728),
(11, 11, 1043, 935835, 8650125),
(12, 12, 46601, 912972, 4956042),
(13, 13, 29095, 350228, 8132648),
(14, 14, 18393, 301437, 2522634),
(15, 15, 47587, 728037, 1399202),
(16, 16, 26695, 137549, 4689513),
(17, 17, 67345, 515079, 2785558),
(18, 18, 68254, 275338, 8376394),
(19, 19, 65987, 243607, 8590738),
(20, 20, 79046, 318296, 1976867),
(21, 21, 38434, 951720, 5662654),
(22, 22, 83902, 168149, 2700542),
(23, 23, 45114, 119451, 2881816),
(24, 24, 90430, 638220, 4002201),
(25, 25, 17107, 514366, 7173140),
(26, 26, 79688, 170084, 4259716),
(27, 27, 59715, 553475, 1915703),
(28, 28, 5860, 881700, 1568059),
(29, 29, 17787, 522391, 4751185),
(30, 30, 71213, 342499, 6919482),
(31, 31, 59453, 693526, 2860874),
(32, 32, 86373, 973133, 9071327),
(33, 33, 81853, 930049, 4166067),
(34, 34, 44963, 385041, 6366427),
(35, 6, 1, 9949130, 9949130),
(35, 5, 3, 2019820, 6059460);

-- --------------------------------------------------------

--
-- Table structure for table `chitietkhuyenmai`
--

CREATE TABLE `chitietkhuyenmai` (
  `maKM` int(5) UNSIGNED NOT NULL,
  `maSP` int(5) UNSIGNED NOT NULL,
  `hinhThucKhuyenMai` varchar(255) NOT NULL,
  `phanTramKhuyenMai` tinyint(3) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `chitietkhuyenmai`
--

INSERT INTO `chitietkhuyenmai` (`maKM`, `maSP`, `hinhThucKhuyenMai`, `phanTramKhuyenMai`) VALUES
(1, 1, '⁰⁴⁵', 23),
(2, 2, '../../../../../../../../../../../etc/passwd%00', 65),
(3, 3, '$1.00', 66),
(4, 4, '1/2', 15),
(5, 5, '␡', 89),
(6, 6, '␡', 21),
(7, 7, ' ', 59),
(8, 8, '0.00', 87),
(9, 9, '¸˛Ç◊ı˜Â¯˘¿', 61),
(10, 10, 'NIL', 48),
(11, 11, 'œ∑´®†¥¨ˆøπ“‘', 83),
(12, 12, '0/0', 33),
(13, 13, '❤️ 💔 💌 💕 💞 💓 💗 💖 💘 💝 💟 💜 💛 💚 💙', 52),
(14, 14, '1', 68),
(15, 15, '␣', 57),
(16, 16, '-1E2', 75),
(17, 17, '../../../../../../../../../../../etc/hosts', 34),
(18, 18, '田中さんにあげて下さい', 66),
(19, 19, '(ﾉಥ益ಥ）ﾉ﻿ ┻━┻', 55),
(20, 20, '₀₁₂', 42),
(21, 21, '🚾 🆒 🆓 🆕 🆖 🆗 🆙 🏧', 19),
(22, 22, 'test⁠test‫', 42),
(23, 23, '1\'; DROP TABLE users--', 66),
(24, 24, 'パーティーへ行かないか', 33),
(25, 25, '사회과학원 어학연구소', 69),
(26, 26, '../../../../../../../../../../../etc/hosts', 88),
(27, 27, '1/2', 37),
(28, 28, '　', 23),
(29, 29, 'NULL', 75),
(30, 30, '\"\'\"\'\"\'\'\'\'\"', 51),
(31, 31, 'null', 88),
(32, 32, '｀ｨ(´∀｀∩', 10),
(33, 33, '-1/2', 13),
(34, 34, '¡™£¢∞§¶•ªº–≠', 87);

-- --------------------------------------------------------

--
-- Table structure for table `chitietphieunhaphang`
--

CREATE TABLE `chitietphieunhaphang` (
  `maSP` int(5) UNSIGNED NOT NULL,
  `maPhieu` int(5) UNSIGNED NOT NULL,
  `soLuong` int(5) UNSIGNED NOT NULL,
  `donGiaGoc` int(10) NOT NULL,
  `thanhTien` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `chitietphieunhaphang`
--

INSERT INTO `chitietphieunhaphang` (`maSP`, `maPhieu`, `soLuong`, `donGiaGoc`, `thanhTien`) VALUES
(1, 1, 83591, 610424, 9029902),
(2, 2, 16106, 831772, 9393333),
(3, 3, 2242, 974230, 6152569),
(4, 4, 16936, 741814, 9026890),
(5, 5, 98337, 492843, 8920166),
(6, 6, 38395, 181721, 8233136),
(7, 7, 16233, 311786, 1389311),
(8, 8, 15129, 781561, 7920490),
(9, 9, 3360, 776154, 4141539),
(10, 10, 98149, 956588, 4935734),
(11, 11, 93950, 812422, 9407768),
(12, 12, 89724, 332106, 5764235),
(13, 13, 332, 805037, 3322275),
(14, 14, 11043, 867426, 1751492),
(15, 15, 82939, 190192, 2295300),
(16, 16, 45716, 150739, 8421462),
(17, 17, 47857, 774368, 3112588),
(18, 18, 23645, 936549, 7244868),
(19, 19, 54495, 941806, 9139482),
(20, 20, 73923, 191100, 7389152),
(21, 21, 38507, 574974, 5582444),
(22, 22, 50436, 808067, 3020729),
(23, 23, 40401, 514688, 2911295),
(24, 24, 70576, 169361, 1712061),
(25, 25, 18633, 424150, 1739343),
(26, 26, 29429, 112660, 6208699),
(27, 27, 43039, 385368, 5889778),
(28, 28, 46552, 752991, 6671689),
(29, 29, 68189, 653020, 1966304),
(30, 30, 35662, 145487, 3321178),
(31, 31, 35290, 259705, 7689601),
(32, 32, 46939, 831536, 7814878),
(33, 33, 39376, 620720, 9278011),
(34, 34, 23909, 216748, 3151996);

-- --------------------------------------------------------

--
-- Table structure for table `chucnang`
--

CREATE TABLE `chucnang` (
  `maCN` int(5) UNSIGNED NOT NULL,
  `tenCN` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `hoadon`
--

CREATE TABLE `hoadon` (
  `maHD` int(5) UNSIGNED NOT NULL,
  `maNV` int(5) UNSIGNED NOT NULL,
  `maKH` int(5) UNSIGNED NOT NULL,
  `ngayLapHoaDon` date NOT NULL,
  `tongTien` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `hoadon`
--

INSERT INTO `hoadon` (`maHD`, `maNV`, `maKH`, `ngayLapHoaDon`, `tongTien`) VALUES
(1, 13, 25, '2020-10-28', 336576),
(2, 12, 18, '2020-06-11', 9148130),
(3, 18, 23, '2021-01-28', 5690880),
(4, 21, 2, '2020-06-23', 5202550),
(5, 9, 1, '2020-04-27', 2738010),
(6, 25, 34, '2021-02-21', 9030010),
(7, 16, 10, '2020-08-19', 4985100),
(8, 16, 13, '2020-03-21', 7372880),
(9, 4, 2, '2021-01-16', 3312450),
(10, 24, 12, '2020-09-19', 8202100),
(11, 18, 22, '2020-05-21', 2061140),
(12, 2, 3, '2021-03-22', 6858220),
(13, 18, 4, '2020-10-07', 1337420),
(14, 8, 26, '2020-10-09', 2096850),
(15, 25, 12, '2020-09-03', 8287460),
(16, 23, 14, '2021-03-13', 4602420),
(17, 4, 32, '2020-07-08', 4112800),
(18, 2, 32, '2020-10-21', 2778590),
(19, 25, 25, '2020-09-03', 9032950),
(20, 14, 4, '2020-05-23', 6370680),
(21, 5, 22, '2020-10-17', 3693890),
(22, 9, 8, '2020-04-10', 9140980),
(23, 10, 6, '2020-05-10', 9345450),
(24, 15, 33, '2020-10-16', 9682510),
(25, 18, 22, '2020-08-08', 8436340),
(26, 23, 33, '2021-01-13', 4539920),
(27, 31, 31, '2020-07-05', 3408910),
(28, 22, 6, '2020-04-01', 3708320),
(29, 4, 32, '2020-08-28', 3072740),
(30, 25, 7, '2020-03-30', 5843940),
(31, 3, 17, '2020-08-29', 3707760),
(32, 31, 15, '2020-10-05', 7886820),
(33, 23, 17, '2021-01-29', 8800810),
(34, 34, 25, '2020-11-05', 6200570),
(35, 4, 4, '2021-05-17', 16008590);

-- --------------------------------------------------------

--
-- Table structure for table `khachhang`
--

CREATE TABLE `khachhang` (
  `maKH` int(5) UNSIGNED NOT NULL,
  `maTK` int(5) UNSIGNED NOT NULL,
  `ho` varchar(30) DEFAULT NULL,
  `ten` varchar(15) NOT NULL,
  `soDienThoai` varchar(10) NOT NULL,
  `ngaySinh` date DEFAULT NULL,
  `diaChi` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `khachhang`
--

INSERT INTO `khachhang` (`maKH`, `maTK`, `ho`, `ten`, `soDienThoai`, `ngaySinh`, `diaChi`) VALUES
(1, 35, 'Dudenie', 'Steffane', '4381059912', '2021-01-15', '3219 Hooker Lane'),
(2, 36, 'Scales', 'Harriett', '4841857428', '2019-07-30', '11 Jenifer Place'),
(3, 37, 'Haldenby', 'Wyatt', '9399865119', '2019-11-04', '0963 East Center'),
(4, 38, 'D\' Angelo', 'Zsa zsa', '6393792123', '2019-07-10', '5 Anthes Street'),
(5, 39, 'Juszczak', 'Arin', '2204619138', '2020-06-10', '2041 Anderson Circle'),
(6, 40, 'Kealy', 'Othello', '5624862622', '2020-12-30', '13 Fieldstone Road'),
(7, 41, 'Stollenhof', 'Lanita', '4569299466', '2020-04-15', '60 Rockefeller Way'),
(8, 42, 'Earry', 'Lucila', '1845663399', '2020-09-28', '42094 Summit Road'),
(9, 43, 'Elverstone', 'Sandi', '2368339000', '2021-03-03', '95789 Burrows Center'),
(10, 44, 'Hulance', 'Miquela', '9287938333', '2019-09-27', '6577 Arapahoe Park'),
(11, 45, 'D\'Hooghe', 'Doug', '3956375673', '2019-10-09', '3 Leroy Circle'),
(12, 46, 'Redsell', 'Melamie', '5718088709', '2020-10-15', '42 Forster Terrace'),
(13, 47, 'Volett', 'Avictor', '5361019561', '2021-01-21', '29 Manitowish Court'),
(14, 48, 'Banasevich', 'Celia', '2999576636', '2020-06-19', '974 Prentice Crossing'),
(15, 49, 'McTague', 'Morly', '9594549038', '2021-03-07', '64 Tennessee Pass'),
(16, 50, 'Lamplough', 'Vere', '3977815534', '2019-05-14', '76102 Mayfield Avenue'),
(17, 51, 'Rose', 'Marrilee', '3529733173', '2019-08-16', '62 Judy Center'),
(18, 52, 'Pettifer', 'Hortensia', '7205748688', '2019-08-12', '3 Dapin Hill'),
(19, 53, 'Riba', 'Catlee', '6955155187', '2020-05-28', '7 Mcbride Point'),
(20, 54, 'Parks', 'Felic', '1337662387', '2020-09-17', '9 Corscot Center'),
(21, 55, 'Meiklejohn', 'Bethena', '5645276711', '2019-10-29', '34 Eagan Avenue'),
(22, 56, 'Iseton', 'Eloise', '7371186124', '2020-10-09', '700 Brown Drive'),
(23, 57, 'Steinor', 'Ellerey', '7869325133', '2020-03-22', '841 Stephen Alley'),
(24, 58, 'Theze', 'Wake', '4407526185', '2020-03-01', '275 Waywood Terrace'),
(25, 59, 'Gudgen', 'Mufinella', '8381471138', '2019-07-23', '26294 Calypso Street'),
(26, 60, 'Blount', 'Bibi', '8875598452', '2020-05-16', '82 Donald Way'),
(27, 61, 'Lewsam', 'Elvin', '8566046082', '2020-08-22', '34 Vahlen Court'),
(28, 62, 'Maulin', 'Coop', '1671974612', '2019-05-24', '37263 Briar Crest Court'),
(29, 63, 'Minto', 'Peri', '9397763634', '2021-03-10', '960 Straubel Plaza'),
(30, 64, 'Hamal', 'Jarret', '8493859945', '2020-10-18', '534 School Court'),
(31, 65, 'Abbets', 'Heloise', '9596520707', '2021-03-24', '86 Jenna Way'),
(32, 66, 'Keeley', 'Wally', '2476430026', '2019-10-15', '0114 Service Plaza'),
(33, 67, 'Wedmore.', 'Desirae', '9945228485', '2019-10-22', '687 Kipling Drive'),
(34, 68, 'Fryd', 'Dwayne', '6496515768', '2019-07-05', '1 Loftsgordon Parkway');

-- --------------------------------------------------------

--
-- Table structure for table `khuyenmai`
--

CREATE TABLE `khuyenmai` (
  `maKM` int(5) UNSIGNED NOT NULL,
  `tenKM` varchar(255) NOT NULL,
  `ngayBatDau` date NOT NULL,
  `ngayKetThuc` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `khuyenmai`
--

INSERT INTO `khuyenmai` (`maKM`, `tenKM`, `ngayBatDau`, `ngayKetThuc`) VALUES
(1, '😍', '2021-01-22', '2020-04-12'),
(2, '␣', '2021-01-22', '2020-08-01'),
(3, '𠜎𠜱𠝹𠱓𠱸𠲖𠳏', '2021-01-19', '2020-07-05'),
(4, '!@#$%^&*()', '2021-01-30', '2020-07-06'),
(5, '0️⃣ 1️⃣ 2️⃣ 3️⃣ 4️⃣ 5️⃣ 6️⃣ 7️⃣ 8️⃣ 9️⃣ 🔟', '2021-01-18', '2020-03-15'),
(6, '　', '2021-01-16', '2020-08-29'),
(7, '✋🏿 💪🏿 👐🏿 🙌🏿 👏🏿 🙏🏿', '2021-01-07', '2021-01-07'),
(8, '<img src=x onerror=alert(\'hi\') />', '2021-01-16', '2020-10-07'),
(9, '울란바토르', '2021-01-26', '2020-07-24'),
(10, 'Ω≈ç√∫˜µ≤≥÷', '2021-01-03', '2020-07-04'),
(11, 'ﾟ･✿ヾ╲(｡◕‿◕｡)╱✿･ﾟ', '2021-01-04', '2020-04-27'),
(12, '\'', '2021-01-01', '2020-11-22'),
(13, 'NIL', '2021-01-02', '2020-09-07'),
(14, '() { 0; }; touch /tmp/blns.shellshock1.fail;', '2021-01-06', '2020-08-07'),
(15, '-1/2', '2021-01-08', '2020-07-28'),
(16, '-1/2', '2021-01-10', '2021-03-01'),
(17, '<script>alert(\'hi\')</script>', '2021-01-14', '2020-03-30'),
(18, '__ﾛ(,_,*)', '2021-01-22', '2020-12-09'),
(19, '999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999', '2021-01-22', '2020-04-20'),
(20, ' ', '2021-01-26', '2020-03-03'),
(21, '1E2', '2021-01-13', '2020-11-12'),
(22, '᠎ds#', '2021-01-16', '2020-05-15'),
(23, '0/0', '2021-01-27', '2020-04-07'),
(24, '❤️ 💔 💌 💕 💞 💓 💗 💖 💘 💝 💟 💜 💛 💚 💙', '2021-01-08', '2021-03-12'),
(25, '\'\"\'', '2021-01-19', '2020-10-22'),
(26, '🚾 🆒 🆓 🆕 🆖 🆗 🆙 🏧', '2021-01-30', '2020-09-25'),
(27, ' test ', '2021-01-26', '2020-12-26'),
(28, 'ヽ༼ຈل͜ຈ༽ﾉ ヽ༼ຈل͜ຈ༽ﾉ ', '2021-01-29', '2021-02-24'),
(29, '-1E2', '2021-01-14', '2020-04-19'),
(30, '\"\'\"\'\"\'\'\'\'\"', '2021-01-22', '2020-03-30'),
(31, 'qw#!11', '2021-01-19', '2020-08-22'),
(32, '<>?:\"{}|_+', '2021-01-10', '2020-12-11'),
(33, '\"\"', '2021-01-08', '2021-03-03'),
(34, '`⁄€‹›ﬁﬂ‡°·‚—±', '2021-01-26', '2020-07-08');

-- --------------------------------------------------------

--
-- Table structure for table `loaisanpham`
--

CREATE TABLE `loaisanpham` (
  `maLoai` int(5) UNSIGNED NOT NULL,
  `tenLoai` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `loaisanpham`
--

INSERT INTO `loaisanpham` (`maLoai`, `tenLoai`) VALUES
(1, 'Cyrtomnium Moss'),
(2, 'Lindley\'s Blazingstar'),
(3, 'Skeletonleaf Willow'),
(4, 'Common Crupina'),
(5, 'Common Twinpod'),
(6, 'Great Indian Plantain'),
(7, 'Largespike Sedge'),
(8, 'Prettyface'),
(9, 'Franklin\'s Hawthorn'),
(10, 'Narrowleaf Goldenaster'),
(11, 'Scallopleaf Sage'),
(12, 'Shortstalk Sedge'),
(13, 'Pearly Globe Amaranth'),
(14, 'Slender Blazing Star'),
(15, 'Alpine Draba'),
(16, 'American Long-leaved Bamboo'),
(17, 'Woolly Senna'),
(18, 'Different-nerve Sedge'),
(19, 'Birdcatching Sedge'),
(20, 'Sandparsley'),
(21, 'Wart Lichen'),
(22, 'Woollygrass'),
(23, 'Illinois Pinweed'),
(24, 'Labrador Buttercup'),
(25, 'Szatala\'s Crabseye Lichen'),
(26, 'Freckled Milkvetch'),
(27, 'Appalachian Gooseberry'),
(28, 'Spider Biscuitroot'),
(29, 'Mexican Alvaradoa'),
(30, 'Hybrid Willow'),
(31, 'Sakaki'),
(32, 'Bittercress'),
(33, 'Purple Moorgrass'),
(34, 'Jelly Lichen');

-- --------------------------------------------------------

--
-- Table structure for table `nhacungcap`
--

CREATE TABLE `nhacungcap` (
  `maNCC` int(5) UNSIGNED NOT NULL,
  `tenNCC` varchar(255) NOT NULL,
  `diaChi` varchar(255) NOT NULL,
  `soDienThoai` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `nhacungcap`
--

INSERT INTO `nhacungcap` (`maNCC`, `tenNCC`, `diaChi`, `soDienThoai`) VALUES
(1, 'Carrols Restaurant Group, Inc.', '82 Bonner Pass', '1867362576'),
(2, 'ARMOUR Residential REIT, Inc.', '883 Eliot Court', '5219191686'),
(3, 'Archrock Partners, L.P.', '1707 Northview Court', '7125106628'),
(4, 'Rollins, Inc.', '5078 Springview Street', '9399751110'),
(5, 'UNITIL Corporation', '007 Portage Point', '3893498634'),
(6, 'Cherokee Inc.', '89 Crescent Oaks Point', '1878123896'),
(7, 'Legg Mason, Inc.', '67 1st Plaza', '4226868338'),
(8, 'Morgan Stanley', '03385 Debs Park', '8073799502'),
(9, 'Cheetah Mobile Inc.', '6476 Butterfield Drive', '7279468049'),
(10, 'Aspen Insurance Holdings Limited', '71706 Everett Court', '5432221068'),
(11, 'Tupperware Brands Corporation', '784 Towne Hill', '4529893663'),
(12, 'General Electric Capital Corporation', '0310 Moose Street', '2336173118'),
(13, 'Powell Industries, Inc.', '5 Huxley Alley', '2478402465'),
(14, 'Sanmina Corporation', '0501 David Trail', '5927580928'),
(15, 'IES Holdings, Inc.', '845 Continental Point', '5008261090'),
(16, 'AnaptysBio, Inc.', '6 Macpherson Drive', '6993967482'),
(17, 'Oxford Lane Capital Corp.', '153 Emmet Drive', '9499391906'),
(18, 'Crown Crafts, Inc.', '9969 Gulseth Street', '8581615208'),
(19, 'Ingersoll-Rand plc (Ireland)', '75031 Green Point', '1695737757'),
(20, 'ARMOUR Residential REIT, Inc.', '38405 Thompson Road', '6563404351'),
(21, 'Duke Energy Corporation', '3 Oak Center', '4303961858'),
(22, 'U.S. Bancorp', '30 Gina Park', '1783287690'),
(23, 'Sunshine Bancorp, Inc.', '90038 Buena Vista Lane', '9576642377'),
(24, 'Macerich Company (The)', '8 Calypso Parkway', '1522049015'),
(25, 'The First Bancshares, Inc.', '7278 Di Loreto Plaza', '8055604502'),
(26, 'Patriot National, Inc.', '87638 Spohn Circle', '2088184891'),
(27, 'China Information Technology, Inc.', '2 Coleman Court', '9458993355'),
(28, 'Royal Caribbean Cruises Ltd.', '4031 Gerald Drive', '3844235705'),
(29, 'Infinity Pharmaceuticals, Inc.', '750 Kropf Lane', '8513146644'),
(30, 'Principal U.S. Small Cap Index ETF', '89087 Northfield Parkway', '4193765221'),
(31, 'Redwood Trust, Inc.', '9208 Laurel Drive', '8429630083'),
(32, 'Great Plains Energy Inc', '88891 Sunnyside Circle', '1254056700'),
(33, 'Axalta Coating Systems Ltd.', '0984 Hanover Place', '2613036436'),
(34, 'Check Point Software Technologies Ltd.', '1 Village Green Junction', '3641509214');

-- --------------------------------------------------------

--
-- Table structure for table `nhanvien`
--

CREATE TABLE `nhanvien` (
  `maNV` int(5) UNSIGNED NOT NULL,
  `maTK` int(5) UNSIGNED NOT NULL,
  `ho` varchar(30) DEFAULT NULL,
  `ten` varchar(15) NOT NULL,
  `diaChi` varchar(255) NOT NULL,
  `ngaySinh` date DEFAULT NULL,
  `soDienThoai` varchar(10) NOT NULL,
  `luong` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `nhanvien`
--

INSERT INTO `nhanvien` (`maNV`, `maTK`, `ho`, `ten`, `diaChi`, `ngaySinh`, `soDienThoai`, `luong`) VALUES
(1, 1, 'Durkin', 'Lindsey', '661 Magdeline Avenue', '2020-03-04', '1592011011', 58908105),
(2, 2, 'Ferby', 'Ingra', '61 Loomis Place', '2020-08-11', '9925572366', 58908105),
(3, 3, 'Hatherley', 'Zulema', '1131 West Court', '2019-12-07', '4058319595', 32768690),
(4, 4, 'Ollerenshaw', 'Jerrie', '761 Thackeray Court', '2021-02-14', '8422656780', 90234640),
(5, 5, 'Dubery', 'Jonathon', '88382 Elmside Circle', '2021-03-09', '9835801507', 11561963),
(6, 6, 'Robker', 'Margalit', '9 Rieder Drive', '2019-09-20', '5623451039', 69464963),
(7, 7, 'Betteridge', 'Tamqrah', '11098 Rusk Parkway', '2019-10-08', '4929998478', 15766883),
(8, 8, 'Batcheldor', 'Timothy', '3 Crest Line Drive', '2020-07-16', '7532064168', 47950958),
(9, 9, 'Langcastle', 'Ewan', '46332 Glacier Hill Road', '2020-08-24', '3399441608', 22765803),
(10, 10, 'Frankowski', 'Baillie', '630 Beilfuss Plaza', '2020-04-02', '3266564182', 56682175),
(11, 11, 'Pollins', 'Bren', '889 Vahlen Point', '2019-07-29', '4179388168', 95309727),
(12, 12, 'Neggrini', 'Margarette', '15 Holy Cross Court', '2020-07-01', '9577643615', 12969248),
(13, 13, 'Fantonetti', 'Willie', '0252 Waubesa Lane', '2019-10-29', '1364815284', 52830104),
(14, 14, 'Longcake', 'Urbanus', '40572 Amoth Avenue', '2020-05-24', '4938034559', 85239437),
(15, 15, 'Catanheira', 'Remus', '59996 Village Green Hill', '2020-08-31', '4761846442', 20992168),
(16, 16, 'Churn', 'Freddie', '651 Forest Dale Junction', '2019-05-02', '9907833051', 25118145),
(17, 17, 'Johann', 'Anica', '4 Arizona Place', '2019-07-22', '6555469214', 29179982),
(18, 18, 'Plott', 'Beverley', '402 Walton Road', '2020-05-11', '3988107872', 36425314),
(19, 19, 'Issard', 'La verne', '027 Anhalt Place', '2021-01-12', '5056044641', 81493022),
(20, 20, 'Prydden', 'Ruthann', '5 Ludington Court', '2020-01-14', '2432170964', 6053906),
(21, 21, 'Wieprecht', 'Kim', '68 Westridge Plaza', '2020-11-06', '1114607842', 48209962),
(22, 22, 'Wignall', 'Lauritz', '533 Dorton Junction', '2019-09-24', '7703613959', 51914364),
(23, 23, 'Critzen', 'Pepita', '16 Merrick Avenue', '2020-01-13', '7867739635', 9426828),
(24, 24, 'Gauford', 'Meredith', '03 Blackbird Terrace', '2020-05-08', '1206812254', 50112880),
(25, 25, 'Girodier', 'Saxon', '0872 Pine View Place', '2020-11-17', '3511589567', 7666470),
(26, 26, 'Claypole', 'Valentia', '831 Montana Park', '2020-03-22', '3451506376', 72098153),
(27, 27, 'Beviss', 'Torrence', '4 Green Ridge Drive', '2019-06-08', '7522487506', 57824766),
(28, 28, 'Clinton', 'Yance', '59444 Hintze Point', '2020-05-09', '6849198667', 13607317),
(29, 29, 'Laundon', 'Conny', '00706 Drewry Pass', '2019-11-29', '5195517720', 61007752),
(30, 30, 'Scotney', 'Talbert', '0 Coolidge Crossing', '2019-08-17', '5962538482', 95300245),
(31, 31, 'Scotfurth', 'Llywellyn', '57 Union Center', '2020-06-28', '7711024579', 39609542),
(32, 32, 'Bratty', 'Di', '59 Cardinal Lane', '2020-01-12', '4195220150', 78079293),
(33, 33, 'Rumney', 'Tiffie', '6 Dorton Crossing', '2020-01-17', '5996645121', 44934098),
(34, 34, 'De Freyne', 'Glenn', '39 Susan Alley', '2020-11-18', '5545880091', 11544313);

-- --------------------------------------------------------

--
-- Table structure for table `nhasanxuat`
--

CREATE TABLE `nhasanxuat` (
  `maNSX` int(5) UNSIGNED NOT NULL,
  `tenNSX` varchar(255) NOT NULL,
  `diaChi` varchar(255) NOT NULL,
  `soDienThoai` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `nhasanxuat`
--

INSERT INTO `nhasanxuat` (`maNSX`, `tenNSX`, `diaChi`, `soDienThoai`) VALUES
(1, 'Spartan Motors, Inc.', '7396 Sutherland Junction', '7527382206'),
(2, 'Rogers Communication, Inc.', '42 Golf Crossing', '3164258753'),
(3, 'First Trust Senior Loan Fund ETF', '63 Leroy Parkway', '3844706710'),
(4, 'Comcast Corporation', '64 Kingsford Place', '9274296934'),
(5, 'PowerShares DWA Emerging Markets Momentum Portfolio', '28892 Vera Junction', '4525257013'),
(6, 'Nuveen Build America Bond Fund', '82353 Longview Street', '1754813196'),
(7, 'Ideal Power Inc.', '465 Mandrake Drive', '8346743192'),
(8, 'First Trust Nasdaq Transportation ETF', '90 Schmedeman Road', '4746440226'),
(9, 'US Ecology, Inc.', '1406 Kedzie Parkway', '3541478630'),
(10, 'John Hancock Preferred Income Fund', '63 Rieder Road', '5709744797'),
(11, 'Voya Natural Resources Equity Income Fund', '2 Sauthoff Terrace', '5014535612'),
(12, 'First Trust Nasdaq Transportation ETF', '5124 Mesta Pass', '2069367108'),
(13, 'Georgia Power Company', '5 Ohio Crossing', '3342281370'),
(14, 'Select Asset Inc.', '36006 Westend Point', '2168896141'),
(15, 'Walker & Dunlop, Inc.', '134 Kingsford Way', '9849291667'),
(16, 'Comtech Telecommunications Corp.', '69 Moland Place', '9572319740'),
(17, 'Pegasystems Inc.', '4 Oxford Point', '1285385853'),
(18, 'OFS Capital Corporation', '680 Hollow Ridge Lane', '4048462790'),
(19, 'Tetra Tech, Inc.', '1 Surrey Street', '8862624925'),
(20, 'ONEOK Partners, L.P.', '93779 Waubesa Junction', '2179633710'),
(21, 'Medovex Corp.', '9 Sommers Avenue', '4916008927'),
(22, 'DTE Energy Company', '5 Mallard Drive', '3138385770'),
(23, 'Transcat, Inc.', '1 Maple Wood Parkway', '4365583787'),
(24, 'Blackrock Muni Intermediate Duration Fund Inc', '661 Stephen Place', '2811623685'),
(25, 'Fifth Street Finance Corp.', '833 Meadow Ridge Pass', '8016795391'),
(26, 'Coherent, Inc.', '51 Nova Terrace', '8167523616'),
(27, 'Blackrock Capital and Income Strategies Fund Inc', '1 Main Park', '5616568919'),
(28, 'Northrim BanCorp Inc', '6 Bayside Point', '5382453846'),
(29, 'Colonial Investment Grade Municipal Trust', '362 Saint Paul Street', '7224627331'),
(30, 'STRATS Trust', '9 Glendale Junction', '2732640077'),
(31, 'Cavco Industries, Inc.', '21 Moulton Way', '7182642410'),
(32, 'Gabelli Utility Trust (The)', '859 Warrior Circle', '3017318960'),
(33, 'BioShares Biotechnology Clinical Trials Fund', '316 Sugar Street', '8455955125'),
(34, 'Parker-Hannifin Corporation', '76 Oxford Court', '9941742284');

-- --------------------------------------------------------

--
-- Table structure for table `phieunhaphang`
--

CREATE TABLE `phieunhaphang` (
  `maPhieu` int(5) UNSIGNED NOT NULL,
  `maNCC` int(5) UNSIGNED NOT NULL,
  `maNV` int(5) UNSIGNED NOT NULL,
  `ngayNhap` date NOT NULL,
  `tongTien` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `phieunhaphang`
--

INSERT INTO `phieunhaphang` (`maPhieu`, `maNCC`, `maNV`, `ngayNhap`, `tongTien`) VALUES
(1, 4, 29, '2020-06-06', 726503),
(2, 20, 16, '2020-10-21', 3084218),
(3, 1, 4, '2020-07-24', 355999),
(4, 18, 22, '2020-12-14', 9048368),
(5, 34, 31, '2020-07-03', 1869124),
(6, 19, 11, '2021-03-25', 8979313),
(7, 1, 20, '2020-06-20', 4604480),
(8, 28, 25, '2020-12-31', 8536371),
(9, 30, 32, '2020-04-29', 2628483),
(10, 18, 10, '2020-09-18', 9084680),
(11, 10, 19, '2021-02-11', 3080116),
(12, 1, 8, '2020-11-21', 3763281),
(13, 9, 32, '2020-10-06', 3414799),
(14, 13, 32, '2020-12-20', 3304586),
(15, 24, 21, '2020-07-03', 8415919),
(16, 22, 18, '2020-12-10', 632400),
(17, 25, 27, '2021-02-13', 2140686),
(18, 5, 15, '2020-11-25', 3245039),
(19, 16, 24, '2020-12-29', 5581659),
(20, 11, 3, '2021-01-25', 6338102),
(21, 22, 31, '2020-06-23', 2926542),
(22, 32, 27, '2020-12-19', 2363566),
(23, 6, 34, '2020-12-27', 2584856),
(24, 31, 15, '2021-02-23', 227424),
(25, 12, 30, '2021-03-26', 7640103),
(26, 20, 34, '2020-05-03', 1803803),
(27, 1, 9, '2020-09-17', 6907890),
(28, 8, 8, '2020-12-30', 562912),
(29, 33, 10, '2020-10-11', 2505195),
(30, 10, 26, '2020-12-19', 4076100),
(31, 8, 21, '2020-03-16', 4766276),
(32, 20, 23, '2021-03-05', 578468),
(33, 34, 33, '2020-12-19', 4038194),
(34, 11, 14, '2020-12-26', 4477243);

-- --------------------------------------------------------

--
-- Table structure for table `quyen`
--

CREATE TABLE `quyen` (
  `maQuyen` int(5) UNSIGNED NOT NULL,
  `tenQuyen` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `quyen`
--

INSERT INTO `quyen` (`maQuyen`, `tenQuyen`) VALUES
(1, 'admin'),
(2, 'nhân viên'),
(3, 'khách hàng');

-- --------------------------------------------------------

--
-- Table structure for table `quyenchucnang`
--

CREATE TABLE `quyenchucnang` (
  `maCN` int(5) UNSIGNED NOT NULL,
  `maQuyen` int(5) UNSIGNED NOT NULL,
  `hienThi` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `sanpham`
--

CREATE TABLE `sanpham` (
  `maSP` int(5) UNSIGNED NOT NULL,
  `maLoai` int(5) UNSIGNED NOT NULL,
  `maNSX` int(5) UNSIGNED NOT NULL,
  `tenSP` varchar(255) NOT NULL,
  `donGia` int(10) NOT NULL,
  `donViTinh` varchar(100) NOT NULL,
  `soLuong` int(10) UNSIGNED NOT NULL,
  `anhDaiDien` varchar(255) DEFAULT NULL,
  `moTa` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `sanpham`
--

INSERT INTO `sanpham` (`maSP`, `maLoai`, `maNSX`, `tenSP`, `donGia`, `donViTinh`, `soLuong`, `anhDaiDien`, `moTa`) VALUES
(1, 33, 20, 'Warner\'s Dodder', 7555060, 'Cái', 4207, 'SP-2.jpg', ''),
(2, 9, 3, 'Forkedfern', 890, 'Cái', 999, 'SP-4.jpg', ''),
(3, 28, 30, 'Butea', 3102660, 'Cái', 7262, 'SP-8.jpg', ''),
(4, 27, 34, 'Dog\'s-tongue', 6214270, 'Cái', 7922, 'SP-11.jpg', ''),
(5, 32, 4, 'Silvery Lupine', 2019820, 'Cái', 3577, 'SP-9.jpg', ''),
(6, 14, 18, 'Rose Meadowsweet', 9949130, 'Cái', 8432, 'SP-10.jpg', ''),
(7, 28, 9, 'Dot Lichen', 4399800, 'Cái', 2256, 'SP-11.jpg', ''),
(8, 3, 29, 'Perennial Horsegram', 172422, 'Cái', 6516, 'SP-13.jpg', ''),
(9, 14, 21, 'Yuma Sandmat', 6359230, 'Cái', 3493, 'SP-15.jpg', ''),
(10, 3, 7, 'Kern Mallow', 9173330, 'Cái', 1369, 'SP-20.jpg', ''),
(11, 31, 18, 'Narrow Lineleaf Fern', 2441200, 'Cái', 5824, 'SP-8.jpg', ''),
(12, 8, 30, 'Palo De Hueso', 6713990, 'Cái', 1671, 'SP-11.jpg', ''),
(13, 7, 21, 'Green And Gold', 9324100, 'Cái', 8679, 'SP-16.jpg', ''),
(14, 17, 11, 'Northern Bluegrass', 8471140, 'Cái', 6490, 'SP-18.jpg', ''),
(15, 12, 28, 'Striped Rosemallow', 8537110, 'Cái', 5176, 'SP-20.jpg', ''),
(16, 19, 21, 'Calamint', 4661340, 'Yuan Renminbi', 4263, NULL, ''),
(17, 5, 31, 'Alpine Biscuitroot', 7268100, 'Franc', 5284, NULL, ''),
(18, 16, 24, 'Lemmon\'s Wild Cabbage', 2815690, 'Shilling', 2814, NULL, ''),
(19, 2, 5, 'Nevada Pea', 2461750, 'Peso', 6378, NULL, ''),
(20, 21, 5, 'Colombian Bluestem', 9442570, 'Euro', 2488, NULL, ''),
(21, 26, 33, 'Renner\'s Map Lichen', 1112230, 'Yuan Renminbi', 846, NULL, ''),
(22, 1, 27, 'Tapeworm-plant', 405795, 'Koruna', 3986, NULL, ''),
(23, 34, 21, 'Celery Pine', 4293130, 'Peso', 1245, NULL, ''),
(24, 29, 5, 'Serpentine Bittercress', 4901000, 'Euro', 9296, NULL, ''),
(25, 2, 5, 'Ginseng', 6359400, 'Yuan Renminbi', 7003, NULL, ''),
(26, 4, 30, 'Dominican Cudweed', 7706170, 'Yen', 1639, NULL, ''),
(27, 23, 8, 'Hasse\'s Psorotichia Lichen', 8590880, 'Peso', 9908, NULL, ''),
(28, 27, 26, 'Canotia', 6835250, 'Koruna', 3814, NULL, ''),
(29, 12, 10, 'Lady Bird\'s Centaury', 678219, 'Ruble', 187, NULL, ''),
(30, 33, 16, 'Twoflower Dwarfdandelion', 1158550, 'Baht', 4310, NULL, ''),
(31, 10, 32, 'Crocosmia', 4156520, 'Real', 2411, NULL, ''),
(32, 23, 8, 'Star Peak Fleabane', 3002750, 'Rupiah', 6538, NULL, ''),
(33, 33, 23, 'Limestone Bedstraw', 7566680, 'Cái', 6255, 'SP-9.jpg', ''),
(34, 23, 30, 'Purple Gaymallow', 8493380, 'Cái', 1918, 'null', '');

-- --------------------------------------------------------

--
-- Table structure for table `taikhoan`
--

CREATE TABLE `taikhoan` (
  `maTK` int(5) UNSIGNED NOT NULL,
  `maQuyen` int(5) UNSIGNED NOT NULL,
  `tenTaiKhoan` varchar(255) NOT NULL,
  `matKhau` varchar(50) NOT NULL,
  `trangThai` tinyint(1) NOT NULL,
  `anhDaiDien` varchar(255) DEFAULT NULL,
  `dangNhap` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `taikhoan`
--

INSERT INTO `taikhoan` (`maTK`, `maQuyen`, `tenTaiKhoan`, `matKhau`, `trangThai`, `anhDaiDien`, `dangNhap`) VALUES
(1, 3, 'Frannie', 'XQcgOIZsGqM', 1, NULL, 0),
(2, 2, 'Zola', 'DQg0Dq5FgVv0', 0, NULL, 0),
(3, 3, 'Bridgette', '80gCfXK07S', 1, NULL, 0),
(4, 3, 'Jeramie', 'rIPTHE6wYjy', 0, NULL, 0),
(5, 2, 'Elisabet', 'ZAeRIqc', 1, NULL, 0),
(6, 3, 'Appolonia', 'OeryXV3Ou8mc', 0, NULL, 0),
(7, 2, 'Marrissa', 'SvCvKwhB', 1, NULL, 0),
(8, 2, 'Ulysses', '6g5WEgxQ', 0, NULL, 0),
(9, 2, 'Martino', '1l8vMSgLZsZ', 1, NULL, 0),
(10, 2, 'Kamillah', 'jgKGexTxlB', 1, NULL, 0),
(11, 2, 'Agnola', 'S4URcyXyCl', 1, NULL, 0),
(12, 3, 'Jewell', '1h8xO4lIYhaK', 0, NULL, 0),
(13, 3, 'Ricki', 'EIV0DdMm', 1, NULL, 0),
(14, 3, 'Whitney', 'bFlii5', 1, NULL, 0),
(15, 2, 'Staford', 'N9EJBk60', 0, NULL, 0),
(16, 2, 'Grissel', '2XcvD9zPo68', 1, NULL, 0),
(17, 2, 'Rand', 'qdccJJeeiI6', 0, NULL, 0),
(18, 2, 'Eveline', '7cWoBY6kfGx', 0, NULL, 0),
(19, 2, 'Bertha', 'VgN1VMf', 1, NULL, 0),
(20, 2, 'Mitchell', 'vg8nPVz', 1, NULL, 0),
(21, 3, 'Woodrow', '2Pbz03swKr', 1, NULL, 0),
(22, 3, 'Clio', 'Y8exHSRCA', 1, NULL, 0),
(23, 3, 'Annetta', '94qEDGUuX', 1, NULL, 0),
(24, 3, 'Barby', 'wqXemkcZn', 0, NULL, 0),
(25, 3, 'Reinhold', 'J25FB0x', 0, NULL, 0),
(26, 2, 'Nichole', 'DUGmerWm4mB', 0, NULL, 0),
(27, 3, 'Bill', 'ZGceYErISm3', 1, NULL, 0),
(28, 2, 'Melodie', 'OGVbu8e', 1, NULL, 0),
(29, 2, 'Gaylene', 'sIROd8BeJA', 1, NULL, 0),
(30, 2, 'Tish', 'WKEDIUWXijOD', 1, NULL, 0),
(31, 2, 'Cherin', 'lpleTHGO', 1, NULL, 0),
(32, 2, 'Elspeth', 'ZGqimo7TQc1', 1, NULL, 0),
(33, 3, 'Marya', 'Dky8oP', 0, NULL, 0),
(34, 2, 'Stefan', '3UCj9ApFOH', 1, NULL, 0),
(35, 2, 'Damon', 'ZbHXL2eJw', 0, NULL, 0),
(36, 2, 'Sally', 'reCD6mq', 0, NULL, 0),
(37, 3, 'Sibbie', 'GBdBzo7Na', 0, NULL, 0),
(38, 2, 'Myrah', 'z8A8I21OwG8', 1, NULL, 0),
(39, 2, 'Hagan', 'HgRyMu', 1, NULL, 0),
(40, 3, 'Sibyl', 'nP6oYseGPB', 0, NULL, 0),
(41, 3, 'Linnea', 'InXnyBQJ4R', 1, NULL, 0),
(42, 3, 'Saw', 'Rw8GfVy', 0, NULL, 0),
(43, 3, 'Jacques', 'pQgQdVvC', 0, NULL, 0),
(44, 2, 'Brice', '4ZEwwNnBbAID', 1, NULL, 0),
(45, 3, 'Derby', '08lwTmAef', 0, NULL, 0),
(46, 2, 'Marsh', 'jAmumknTd0E', 1, NULL, 0),
(47, 3, 'Ephraim', 'nQUk9E0', 0, NULL, 0),
(48, 2, 'Egon', 'Hh8ewp', 1, NULL, 0),
(49, 2, 'Goraud', 'rCn0MFcQlelp', 0, NULL, 0),
(50, 3, 'Catherin', 't12TI99kD8jX', 0, NULL, 0),
(51, 2, 'Mella', '3wRzsIIXqSe', 0, NULL, 0),
(52, 3, 'Pegeen', 'uy5KZN4xVxz', 1, NULL, 0),
(53, 2, 'Shannon', 'bRG3EhMXqDBP', 0, NULL, 0),
(54, 2, 'Angel', '5VjKRVbPbkCp', 1, NULL, 0),
(55, 3, 'Cher', '4jbaH2BWa', 1, NULL, 0),
(56, 3, 'Kittie', 'GToufBas7iGw', 1, NULL, 0),
(57, 2, 'Lavinia', 'DDPs8h', 0, NULL, 0),
(58, 2, 'Salmon', 'slwgNIahz', 0, NULL, 0),
(59, 3, 'Ermentrude', 'YfB1XeicTxq', 0, NULL, 0),
(60, 3, 'Caldwell', 'W9zbTb5xS', 0, NULL, 0),
(61, 3, 'Barny', 'RV94QG0oG3', 1, NULL, 0),
(62, 2, 'Cookie', 'Nc2YcD1', 0, NULL, 0),
(63, 2, 'Lauryn', 'x66cKulGO', 0, NULL, 0),
(64, 3, 'Tremayne', 'X9BV3zB', 1, NULL, 0),
(65, 3, 'Jesus', 'y7VfVplb1', 1, NULL, 0),
(66, 2, 'Slade', '9rpp4Z', 1, NULL, 0),
(67, 3, 'Katya', '5CDBJ8RPcPms', 0, NULL, 0),
(68, 2, 'Duncan', 'oVwWo2', 0, NULL, 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `chitiethoadon`
--
ALTER TABLE `chitiethoadon`
  ADD KEY `maSP` (`maSP`),
  ADD KEY `maHD` (`maHD`);

--
-- Indexes for table `chitietkhuyenmai`
--
ALTER TABLE `chitietkhuyenmai`
  ADD KEY `maKM` (`maKM`),
  ADD KEY `maSP` (`maSP`);

--
-- Indexes for table `chitietphieunhaphang`
--
ALTER TABLE `chitietphieunhaphang`
  ADD KEY `maSP` (`maSP`),
  ADD KEY `maPhieu` (`maPhieu`);

--
-- Indexes for table `chucnang`
--
ALTER TABLE `chucnang`
  ADD PRIMARY KEY (`maCN`);

--
-- Indexes for table `hoadon`
--
ALTER TABLE `hoadon`
  ADD PRIMARY KEY (`maHD`),
  ADD KEY `maNV` (`maNV`),
  ADD KEY `maKH` (`maKH`);

--
-- Indexes for table `khachhang`
--
ALTER TABLE `khachhang`
  ADD PRIMARY KEY (`maKH`),
  ADD KEY `maTK` (`maTK`);

--
-- Indexes for table `khuyenmai`
--
ALTER TABLE `khuyenmai`
  ADD PRIMARY KEY (`maKM`);

--
-- Indexes for table `loaisanpham`
--
ALTER TABLE `loaisanpham`
  ADD PRIMARY KEY (`maLoai`);

--
-- Indexes for table `nhacungcap`
--
ALTER TABLE `nhacungcap`
  ADD PRIMARY KEY (`maNCC`);

--
-- Indexes for table `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD PRIMARY KEY (`maNV`),
  ADD KEY `maTK` (`maTK`);

--
-- Indexes for table `nhasanxuat`
--
ALTER TABLE `nhasanxuat`
  ADD PRIMARY KEY (`maNSX`);

--
-- Indexes for table `phieunhaphang`
--
ALTER TABLE `phieunhaphang`
  ADD PRIMARY KEY (`maPhieu`),
  ADD KEY `maNCC` (`maNCC`),
  ADD KEY `maNV` (`maNV`);

--
-- Indexes for table `quyen`
--
ALTER TABLE `quyen`
  ADD PRIMARY KEY (`maQuyen`);

--
-- Indexes for table `quyenchucnang`
--
ALTER TABLE `quyenchucnang`
  ADD KEY `maQuyen` (`maQuyen`),
  ADD KEY `maChucNang` (`maCN`);

--
-- Indexes for table `sanpham`
--
ALTER TABLE `sanpham`
  ADD PRIMARY KEY (`maSP`),
  ADD KEY `maLoai` (`maLoai`),
  ADD KEY `maNSX` (`maNSX`);

--
-- Indexes for table `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD PRIMARY KEY (`maTK`),
  ADD KEY `maQuyen` (`maQuyen`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `chucnang`
--
ALTER TABLE `chucnang`
  MODIFY `maCN` int(5) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `hoadon`
--
ALTER TABLE `hoadon`
  MODIFY `maHD` int(5) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;

--
-- AUTO_INCREMENT for table `khachhang`
--
ALTER TABLE `khachhang`
  MODIFY `maKH` int(5) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- AUTO_INCREMENT for table `khuyenmai`
--
ALTER TABLE `khuyenmai`
  MODIFY `maKM` int(5) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- AUTO_INCREMENT for table `loaisanpham`
--
ALTER TABLE `loaisanpham`
  MODIFY `maLoai` int(5) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- AUTO_INCREMENT for table `nhacungcap`
--
ALTER TABLE `nhacungcap`
  MODIFY `maNCC` int(5) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- AUTO_INCREMENT for table `nhanvien`
--
ALTER TABLE `nhanvien`
  MODIFY `maNV` int(5) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;

--
-- AUTO_INCREMENT for table `nhasanxuat`
--
ALTER TABLE `nhasanxuat`
  MODIFY `maNSX` int(5) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- AUTO_INCREMENT for table `phieunhaphang`
--
ALTER TABLE `phieunhaphang`
  MODIFY `maPhieu` int(5) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- AUTO_INCREMENT for table `quyen`
--
ALTER TABLE `quyen`
  MODIFY `maQuyen` int(5) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `sanpham`
--
ALTER TABLE `sanpham`
  MODIFY `maSP` int(5) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;

--
-- AUTO_INCREMENT for table `taikhoan`
--
ALTER TABLE `taikhoan`
  MODIFY `maTK` int(5) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=69;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `chitiethoadon`
--
ALTER TABLE `chitiethoadon`
  ADD CONSTRAINT `chitiethoadon_ibfk_1` FOREIGN KEY (`maHD`) REFERENCES `hoadon` (`maHD`),
  ADD CONSTRAINT `chitiethoadon_ibfk_2` FOREIGN KEY (`maSP`) REFERENCES `sanpham` (`maSP`);

--
-- Constraints for table `chitietkhuyenmai`
--
ALTER TABLE `chitietkhuyenmai`
  ADD CONSTRAINT `chitietkhuyenmai_ibfk_1` FOREIGN KEY (`maSP`) REFERENCES `sanpham` (`maSP`),
  ADD CONSTRAINT `chitietkhuyenmai_ibfk_2` FOREIGN KEY (`maKM`) REFERENCES `khuyenmai` (`maKM`);

--
-- Constraints for table `chitietphieunhaphang`
--
ALTER TABLE `chitietphieunhaphang`
  ADD CONSTRAINT `chitietphieunhaphang_ibfk_1` FOREIGN KEY (`maSP`) REFERENCES `sanpham` (`maSP`),
  ADD CONSTRAINT `chitietphieunhaphang_ibfk_2` FOREIGN KEY (`maPhieu`) REFERENCES `phieunhaphang` (`maPhieu`);

--
-- Constraints for table `hoadon`
--
ALTER TABLE `hoadon`
  ADD CONSTRAINT `hoadon_ibfk_1` FOREIGN KEY (`maNV`) REFERENCES `nhanvien` (`maNV`),
  ADD CONSTRAINT `hoadon_ibfk_2` FOREIGN KEY (`maKH`) REFERENCES `khachhang` (`maKH`);

--
-- Constraints for table `khachhang`
--
ALTER TABLE `khachhang`
  ADD CONSTRAINT `khachhang_ibfk_1` FOREIGN KEY (`maTK`) REFERENCES `taikhoan` (`maTK`);

--
-- Constraints for table `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD CONSTRAINT `nhanvien_ibfk_1` FOREIGN KEY (`maTK`) REFERENCES `taikhoan` (`maTK`);

--
-- Constraints for table `phieunhaphang`
--
ALTER TABLE `phieunhaphang`
  ADD CONSTRAINT `phieunhaphang_ibfk_1` FOREIGN KEY (`maNCC`) REFERENCES `nhacungcap` (`maNCC`),
  ADD CONSTRAINT `phieunhaphang_ibfk_2` FOREIGN KEY (`maNV`) REFERENCES `nhanvien` (`maNV`);

--
-- Constraints for table `quyenchucnang`
--
ALTER TABLE `quyenchucnang`
  ADD CONSTRAINT `quyenchucnang_ibfk_1` FOREIGN KEY (`maQuyen`) REFERENCES `quyen` (`maQuyen`),
  ADD CONSTRAINT `quyenchucnang_ibfk_2` FOREIGN KEY (`maCN`) REFERENCES `chucnang` (`maCN`);

--
-- Constraints for table `sanpham`
--
ALTER TABLE `sanpham`
  ADD CONSTRAINT `sanpham_ibfk_1` FOREIGN KEY (`maLoai`) REFERENCES `loaisanpham` (`maLoai`),
  ADD CONSTRAINT `sanpham_ibfk_2` FOREIGN KEY (`maNSX`) REFERENCES `nhasanxuat` (`maNSX`);

--
-- Constraints for table `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD CONSTRAINT `taikhoan_ibfk_1` FOREIGN KEY (`maQuyen`) REFERENCES `quyen` (`maQuyen`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
