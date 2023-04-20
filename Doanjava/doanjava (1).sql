-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th8 31, 2021 lúc 04:31 PM
-- Phiên bản máy phục vụ: 10.1.38-MariaDB
-- Phiên bản PHP: 7.3.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `doanjava`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitiethoadon`
--

CREATE TABLE `chitiethoadon` (
  `MaSP` int(11) NOT NULL,
  `MaHD` int(11) NOT NULL,
  `SoLuong` int(11) NOT NULL,
  `Thanhtien` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

--
-- Đang đổ dữ liệu cho bảng `chitiethoadon`
--

INSERT INTO `chitiethoadon` (`MaSP`, `MaHD`, `SoLuong`, `Thanhtien`) VALUES
(5, 21, 2, 32000000),
(1, 21, 1, 1230000),
(3, 8, 1, 10000000),
(4, 16, 1, 19600000),
(7, 21, 5, 50000000),
(1, 8, 2, 2460000),
(20, 38, 1, 380000),
(15, 41, 1, 3900000),
(18, 18, 1, 600000),
(8, 33, 1, 8000000),
(8, 38, 2, 16000000),
(10, 38, 1, 31000000),
(1, 42, 1, 1230000),
(10, 43, 1, 31000000),
(11, 44, 1, 16000000),
(14, 45, 1, 9400000),
(6, 46, 2, 31200000),
(9, 43, 1, 6000000),
(9, 47, 1, 6000000),
(5, 48, 3, 48000000),
(4, 48, 1, 19600000),
(2, 21, 1, 12000000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitietkhuyenmai`
--

CREATE TABLE `chitietkhuyenmai` (
  `MaKM` int(11) NOT NULL,
  `MaSP` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

--
-- Đang đổ dữ liệu cho bảng `chitietkhuyenmai`
--

INSERT INTO `chitietkhuyenmai` (`MaKM`, `MaSP`) VALUES
(1, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitietphieunhap`
--

CREATE TABLE `chitietphieunhap` (
  `MaPN` int(11) NOT NULL,
  `MaSP` int(11) NOT NULL,
  `Soluong` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

--
-- Đang đổ dữ liệu cho bảng `chitietphieunhap`
--

INSERT INTO `chitietphieunhap` (`MaPN`, `MaSP`, `Soluong`) VALUES
(1, 1, 2),
(2, 4, 4),
(2, 5, 2),
(2, 1, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `hoadon`
--

CREATE TABLE `hoadon` (
  `MaHD` int(11) NOT NULL,
  `MaKH` int(11) NOT NULL,
  `MaNV` int(11) NOT NULL,
  `Tongtien` int(11) NOT NULL,
  `DateHD` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

--
-- Đang đổ dữ liệu cho bảng `hoadon`
--

INSERT INTO `hoadon` (`MaHD`, `MaKH`, `MaNV`, `Tongtien`, `DateHD`) VALUES
(8, 7, 11, 12460000, '2021-01-01'),
(16, 4, 13, 19600000, '2021-01-06'),
(18, 8, 17, 600000, '2021-02-10'),
(21, 5, 13, 95230000, '2021-03-23'),
(33, 9, 1, 8000000, '2021-03-30'),
(38, 7, 14, 31000000, '2021-04-14'),
(41, 6, 11, 3900000, '2021-04-14'),
(42, 7, 14, 1230000, '2021-05-13'),
(43, 12, 17, 6000000, '2021-05-14'),
(44, 6, 1, 16000000, '2021-05-16'),
(45, 13, 1, 9400000, '2021-05-16'),
(46, 10, 11, 31200000, '2021-05-16'),
(47, 5, 13, 6000000, '2021-05-16'),
(48, 10, 18, 67600000, '2021-05-17');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `khachhang`
--

CREATE TABLE `khachhang` (
  `MaKH` int(11) NOT NULL,
  `TenKH` varchar(50) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `Gioitinh` varchar(10) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `Diachi` varchar(50) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `SDT` varchar(11) COLLATE utf8mb4_vietnamese_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

--
-- Đang đổ dữ liệu cho bảng `khachhang`
--

INSERT INTO `khachhang` (`MaKH`, `TenKH`, `Gioitinh`, `Diachi`, `SDT`) VALUES
(4, 'Minh Anh', 'Nữ', '17 Nguyễn Trãi', '012345678'),
(5, 'Hữu Khánh', 'Nam', '123 An Dương Vương ', '023913231'),
(6, 'Quốc Hùng', 'Nam', '5 Cao Thắng', '070123456'),
(7, 'Trung Quân', 'Nam', '3 Nguyễn Trãi', '012451213'),
(8, 'Quỳnh Lan', 'Nữ', '6 Trần Hưng Đạo', '098765432'),
(9, 'Minh Quang', 'Nam', '2 Phạm Ngũ Lão', '098765431'),
(10, 'Thanh Trường', 'Nam', '65 Tôn Đản', '078658444'),
(11, 'Quang Huy', 'Nam', '512 Trần Phú', '013245631'),
(12, 'Anh Đức', 'Nam', '90 Nguyễn Cư Trinh', '055055051'),
(13, 'Anh Quân', 'Nam', '2 Lý Thường Kiệt', '087879877'),
(14, 'Tiến Dũng', 'Nam', '76 Trần Phú', '091234532');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `khuyenmai`
--

CREATE TABLE `khuyenmai` (
  `MaKM` int(11) NOT NULL,
  `TenKM` varchar(50) COLLATE utf8mb4_vietnamese_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

--
-- Đang đổ dữ liệu cho bảng `khuyenmai`
--

INSERT INTO `khuyenmai` (`MaKM`, `TenKM`) VALUES
(1, 'Noel');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `loaisp`
--

CREATE TABLE `loaisp` (
  `MaLoai` int(11) NOT NULL,
  `TenLoai` varchar(50) COLLATE utf8mb4_vietnamese_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

--
-- Đang đổ dữ liệu cho bảng `loaisp`
--

INSERT INTO `loaisp` (`MaLoai`, `TenLoai`) VALUES
(1, 'Nokia'),
(2, 'IPhone'),
(3, 'Oppo'),
(4, 'Samsung'),
(5, 'Vivo'),
(6, 'Xiaomi'),
(7, 'VSmart');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhacungcap`
--

CREATE TABLE `nhacungcap` (
  `MaNCC` int(11) NOT NULL,
  `TenNCC` varchar(50) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `Diachi` varchar(50) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `SDT` varchar(50) COLLATE utf8mb4_vietnamese_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

--
-- Đang đổ dữ liệu cho bảng `nhacungcap`
--

INSERT INTO `nhacungcap` (`MaNCC`, `TenNCC`, `Diachi`, `SDT`) VALUES
(1, 'Hệ thống phân phối XiaoMi', '261 Lê Lợi, P. Lê Lợi, Q. Ngô Quyền, Tp. Hải Phòng', '09898989'),
(2, 'Công ty Nokia', '235 Đồng Khởi, P. Bến Nghé, Q.1, TPHCM', '07987979'),
(3, 'Công ty TNHH TGDD', '364 Cộng Hòa, P. 13, Q. Tân Bình,TPHCM', '09143111'),
(4, 'Công Ty Cổ Phần Thế Giới Số', '308-310-312 Hồng Bàng, P. 15, Q. 10,TPHCM', '09898912'),
(5, 'Viettel -Viễn thông quân đội', 'Số 285 Cách Mạng Tháng Tám, P. 12, Q. 10, TPHCM', '09895634');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhanvien`
--

CREATE TABLE `nhanvien` (
  `MaNV` int(11) NOT NULL,
  `MaTK` int(11) DEFAULT NULL,
  `TenNV` varchar(50) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `Gioitinh` varchar(10) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `SDT` varchar(11) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `Luong` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

--
-- Đang đổ dữ liệu cho bảng `nhanvien`
--

INSERT INTO `nhanvien` (`MaNV`, `MaTK`, `TenNV`, `Gioitinh`, `SDT`, `Luong`) VALUES
(1, 0, 'Tấn Đạt', 'Nam', '0123456789', 8000000),
(11, 0, 'Khánh Duy', 'Nam', '0701234556', 7800000),
(13, 4, 'Phước Thịnh', 'Nam', '0123456788', 3000000),
(14, 0, 'Duy Tân', 'Nam', '0901237891', 3500000),
(17, 0, 'Ngọc Hân', 'Nữ', '0973560111', 5200000),
(18, 0, 'Nguyễn B', 'Nam', '0123456789', 7000000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phieunhap`
--

CREATE TABLE `phieunhap` (
  `MaPN` int(11) NOT NULL,
  `MaNCC` int(11) NOT NULL,
  `DatePN` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

--
-- Đang đổ dữ liệu cho bảng `phieunhap`
--

INSERT INTO `phieunhap` (`MaPN`, `MaNCC`, `DatePN`) VALUES
(1, 4, '2021-05-18'),
(2, 4, '2021-05-18');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `quyen`
--

CREATE TABLE `quyen` (
  `MaQuyen` int(11) NOT NULL,
  `TenQuyen` varchar(50) COLLATE utf8mb4_vietnamese_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

--
-- Đang đổ dữ liệu cho bảng `quyen`
--

INSERT INTO `quyen` (`MaQuyen`, `TenQuyen`) VALUES
(1, 'Admin'),
(2, 'Nhanvien');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sanpham`
--

CREATE TABLE `sanpham` (
  `MaSP` int(11) NOT NULL,
  `TenSP` varchar(50) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `MaLoai` int(11) NOT NULL,
  `MaNCC` int(11) NOT NULL,
  `Giaban` int(11) NOT NULL,
  `Soluong` int(11) NOT NULL,
  `Hinh` varchar(50) COLLATE utf8mb4_vietnamese_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

--
-- Đang đổ dữ liệu cho bảng `sanpham`
--

INSERT INTO `sanpham` (`MaSP`, `TenSP`, `MaLoai`, `MaNCC`, `Giaban`, `Soluong`, `Hinh`) VALUES
(1, 'Nokia 1450', 1, 2, 1230000, 10, ''),
(2, 'Iphone X Pro Max 64GB', 2, 3, 12000000, 14, ''),
(3, 'Iphone 7', 1, 3, 10000000, 22, ''),
(4, 'OPPO A74', 3, 5, 19600000, 17, ''),
(5, 'iPhone 12 64GB', 2, 4, 16000000, 18, ''),
(6, 'Samsung Galaxy A32', 4, 5, 15600000, 5, ''),
(7, 'Vivo Y72 5G', 5, 4, 10000000, 26, ''),
(8, 'Xiaomi Mi 11 Lite', 6, 1, 8000000, 14, ''),
(9, 'Vsmart Star 5 (3GB/64GB)', 7, 5, 6000000, 3, ''),
(10, 'iPhone 12 Pro Max 128GB', 2, 3, 31000000, 61, ''),
(11, 'iPhone 11 64GB', 2, 3, 16000000, 4, ''),
(12, 'iPhone 12 mini 64GB', 2, 4, 7000000, 10, ''),
(13, 'iPhone XR 128GB', 2, 5, 14900000, 15, ''),
(14, 'iPhone 11 256GB', 2, 5, 9400000, 5, ''),
(15, 'Nokia 5.4', 1, 2, 3900000, 8, ''),
(16, 'Nokia C2', 1, 2, 1690000, 7, ''),
(17, 'Nokia 6300 4G', 1, 2, 1290000, 6, ''),
(18, 'Nokia 150 (2020)', 1, 2, 600000, 11, ''),
(19, 'Nokia 8000 4G', 1, 2, 1790000, 16, ''),
(20, 'Nokia 105 Dual (2019)', 1, 2, 380000, 9, '');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `taikhoan`
--

CREATE TABLE `taikhoan` (
  `MaTK` int(11) NOT NULL,
  `MaQuyen` int(11) NOT NULL,
  `Tendn` varchar(50) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `Matkhau` varchar(50) COLLATE utf8mb4_vietnamese_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

--
-- Đang đổ dữ liệu cho bảng `taikhoan`
--

INSERT INTO `taikhoan` (`MaTK`, `MaQuyen`, `Tendn`, `Matkhau`) VALUES
(0, 2, 'null', 'null'),
(1, 1, 'khanhduy', 'kd'),
(2, 2, 'user', 'user'),
(3, 2, 'khanhduy', 'hihihi'),
(4, 2, 'tandat', 'hehehe');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `chitiethoadon`
--
ALTER TABLE `chitiethoadon`
  ADD KEY `MaSP` (`MaSP`,`MaHD`),
  ADD KEY `MaHD` (`MaHD`);

--
-- Chỉ mục cho bảng `chitietkhuyenmai`
--
ALTER TABLE `chitietkhuyenmai`
  ADD KEY `MaSP` (`MaSP`),
  ADD KEY `MaKM` (`MaKM`);

--
-- Chỉ mục cho bảng `chitietphieunhap`
--
ALTER TABLE `chitietphieunhap`
  ADD KEY `MaPN` (`MaPN`),
  ADD KEY `MaSP` (`MaSP`);

--
-- Chỉ mục cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  ADD PRIMARY KEY (`MaHD`),
  ADD KEY `MaKH` (`MaKH`),
  ADD KEY `MaNV` (`MaNV`);

--
-- Chỉ mục cho bảng `khachhang`
--
ALTER TABLE `khachhang`
  ADD PRIMARY KEY (`MaKH`);

--
-- Chỉ mục cho bảng `khuyenmai`
--
ALTER TABLE `khuyenmai`
  ADD PRIMARY KEY (`MaKM`);

--
-- Chỉ mục cho bảng `loaisp`
--
ALTER TABLE `loaisp`
  ADD PRIMARY KEY (`MaLoai`) USING BTREE;

--
-- Chỉ mục cho bảng `nhacungcap`
--
ALTER TABLE `nhacungcap`
  ADD PRIMARY KEY (`MaNCC`);

--
-- Chỉ mục cho bảng `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD PRIMARY KEY (`MaNV`),
  ADD KEY `MaTK` (`MaTK`);

--
-- Chỉ mục cho bảng `phieunhap`
--
ALTER TABLE `phieunhap`
  ADD PRIMARY KEY (`MaPN`),
  ADD KEY `MaNCC` (`MaNCC`);

--
-- Chỉ mục cho bảng `quyen`
--
ALTER TABLE `quyen`
  ADD PRIMARY KEY (`MaQuyen`);

--
-- Chỉ mục cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  ADD PRIMARY KEY (`MaSP`),
  ADD KEY `MaLoai` (`MaLoai`,`MaNCC`);

--
-- Chỉ mục cho bảng `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD PRIMARY KEY (`MaTK`),
  ADD KEY `MaQuyen` (`MaQuyen`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  MODIFY `MaHD` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=49;

--
-- AUTO_INCREMENT cho bảng `khachhang`
--
ALTER TABLE `khachhang`
  MODIFY `MaKH` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT cho bảng `loaisp`
--
ALTER TABLE `loaisp`
  MODIFY `MaLoai` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT cho bảng `nhanvien`
--
ALTER TABLE `nhanvien`
  MODIFY `MaNV` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT cho bảng `phieunhap`
--
ALTER TABLE `phieunhap`
  MODIFY `MaPN` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `taikhoan`
--
ALTER TABLE `taikhoan`
  MODIFY `MaTK` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `chitiethoadon`
--
ALTER TABLE `chitiethoadon`
  ADD CONSTRAINT `chitiethoadon_ibfk_1` FOREIGN KEY (`MaHD`) REFERENCES `hoadon` (`MaHD`),
  ADD CONSTRAINT `chitiethoadon_ibfk_2` FOREIGN KEY (`MaSP`) REFERENCES `sanpham` (`MaSP`);

--
-- Các ràng buộc cho bảng `chitietkhuyenmai`
--
ALTER TABLE `chitietkhuyenmai`
  ADD CONSTRAINT `chitietkhuyenmai_ibfk_1` FOREIGN KEY (`MaSP`) REFERENCES `sanpham` (`MaSP`),
  ADD CONSTRAINT `chitietkhuyenmai_ibfk_2` FOREIGN KEY (`MaKM`) REFERENCES `khuyenmai` (`MaKM`);

--
-- Các ràng buộc cho bảng `chitietphieunhap`
--
ALTER TABLE `chitietphieunhap`
  ADD CONSTRAINT `chitietphieunhap_ibfk_1` FOREIGN KEY (`MaPN`) REFERENCES `phieunhap` (`MaPN`),
  ADD CONSTRAINT `chitietphieunhap_ibfk_2` FOREIGN KEY (`MaSP`) REFERENCES `sanpham` (`MaSP`);

--
-- Các ràng buộc cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  ADD CONSTRAINT `hoadon_ibfk_1` FOREIGN KEY (`MaKH`) REFERENCES `khachhang` (`MaKH`),
  ADD CONSTRAINT `hoadon_ibfk_2` FOREIGN KEY (`MaNV`) REFERENCES `nhanvien` (`MaNV`);

--
-- Các ràng buộc cho bảng `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD CONSTRAINT `nhanvien_ibfk_1` FOREIGN KEY (`MaTK`) REFERENCES `taikhoan` (`MaTK`);

--
-- Các ràng buộc cho bảng `phieunhap`
--
ALTER TABLE `phieunhap`
  ADD CONSTRAINT `phieunhap_ibfk_1` FOREIGN KEY (`MaNCC`) REFERENCES `nhacungcap` (`MaNCC`);

--
-- Các ràng buộc cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  ADD CONSTRAINT `sanpham_ibfk_1` FOREIGN KEY (`MaLoai`) REFERENCES `loaisp` (`MaLoai`);

--
-- Các ràng buộc cho bảng `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD CONSTRAINT `taikhoan_ibfk_1` FOREIGN KEY (`MaQuyen`) REFERENCES `quyen` (`MaQuyen`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
