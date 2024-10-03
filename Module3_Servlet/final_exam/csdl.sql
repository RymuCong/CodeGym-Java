-- Tạo cơ sở dữ liệu
CREATE DATABASE TComplexDB;

-- Sử dụng cơ sở dữ liệu vừa tạo
USE TComplexDB;

-- Tạo bảng Property
CREATE TABLE Property (
                          propertyId VARCHAR(10) PRIMARY KEY,
                          status ENUM('Trống', 'Hạ tầng', 'Đầy đủ') NOT NULL,
                          area DOUBLE NOT NULL CHECK (area > 20),
                          floor INT NOT NULL CHECK (floor BETWEEN 1 AND 15),
                          type ENUM('Văn phòng chia sẻ', 'Văn phòng trọn gói') NOT NULL,
                          price DOUBLE NOT NULL CHECK (price > 1000000),
                          startDate DATE NOT NULL,
                          endDate DATE NOT NULL,
                          CHECK (startDate < endDate AND DATEDIFF(endDate, startDate) >= 180)
);

-- Thêm một số dữ liệu mẫu
INSERT INTO Property (propertyId, status, area, floor, type, price, startDate, endDate) VALUES
('A01-01-01', 'Trống', 30.0, 1, 'Văn phòng chia sẻ', 2000000, '2023-01-01', '2023-07-01'),
('B02-02-02', 'Hạ tầng', 50.0, 2, 'Văn phòng trọn gói', 5000000, '2023-02-01', '2023-08-01'),
('C03-03-03', 'Trống', 25.0, 3, 'Văn phòng chia sẻ', 1500000, '2023-01-01', '2023-07-01'),
('D04-04-04', 'Hạ tầng', 45.0, 4, 'Văn phòng trọn gói', 3000000, '2023-02-01', '2023-08-01'),
('E05-05-05', 'Đầy đủ', 60.0, 5, 'Văn phòng chia sẻ', 4000000, '2023-03-01', '2023-09-01'),
('F06-06-06', 'Trống', 35.0, 6, 'Văn phòng trọn gói', 2500000, '2023-04-01', '2023-10-01'),
('G07-07-07', 'Hạ tầng', 50.0, 7, 'Văn phòng chia sẻ', 3500000, '2023-05-01', '2023-11-01'),
('H08-08-08', 'Đầy đủ', 70.0, 8, 'Văn phòng trọn gói', 5000000, '2023-06-01', '2023-12-01'),
('I09-09-09', 'Trống', 40.0, 9, 'Văn phòng chia sẻ', 3000000, '2023-07-01', '2024-01-01'),
('J10-10-10', 'Hạ tầng', 55.0, 10, 'Văn phòng trọn gói', 4500000, '2023-08-01', '2024-02-01'),
('K11-11-11', 'Đầy đủ', 65.0, 11, 'Văn phòng chia sẻ', 4000000, '2023-09-01', '2024-03-01'),
('L12-12-12', 'Trống', 30.0, 12, 'Văn phòng trọn gói', 2000000, '2023-10-01', '2024-04-01');