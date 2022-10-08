# Hướng dẫn sử dụng

**\* Lưu ý: Không chia sẻ cho bên ngoài khi chưa có sự cho phép của chủ Repo (Tạ Gia Vinh).**

## Dependencies

Cài đặt các dependencies nếu chưa có (nếu muốn biết chưa có thì xem code có bị lỗi hay không bằng cách chạy chương trình hoặc tìm thủ công) và phải cài đúng phiên bản.

1. itextpdf-**5.5.13.3**
2. poi-**5.0.0**
3. poi-ooxml-**5.0.0**
4. poi-ooxml-lite-**5.0.0**
5. mysql-connector-java-**8.0.30**
6. jcalendar-**1.4**

## Database

1. Chạy MySql.
2. Tạo database rỗng tên **banhang**.
3. Import file **banhang.sql**.
4. Vào src/main/java/config/**configDB.java** sửa theo các cài đặt trên máy hoặc sửa trên máy theo **configDB** (nếu chưa đúng).
    - username: tài khoản root (localhost).
    - password: mật khẩu root (localhost).
    - port: cổng MySql.
    - host: localhost (nếu chạy trên localhost thì không đổi).
    - databaseName: không đổi.

## App

Chạy file src/main/java/application/**MAIN.java**.

## Feedback

Nếu có thắc mắc, liên hệ Gia Vinh trực tiếp (tại trường) hoặc gián tiếp (chat app).
