# MidTerm
## Hướng dẫn và kết quả chạy chương trình
1.	Cài đặt
-	Cài MySQL bản 8
- Sửa pasword trong file application.properties trùng với pasword của MySQL
-	Vào mysql -> connect MySQLServer -> server -> data import
-	import file shop.sql vào 1 schema mới tên shop

![image](https://user-images.githubusercontent.com/130289045/230774552-385ec277-00ca-49e8-a00e-502e0d0b08be.png)

-	sang tab import progress -> start import 
![image](https://user-images.githubusercontent.com/130289045/230774690-5cf3b771-f858-41dd-9808-2967d449f696.png)


2.	Chạy
-	Gõ http://localhost:8080 ở trình duyệt sẽ hiện trang chủ
![Screenshot 2023-04-09 193126](https://user-images.githubusercontent.com/130289045/230772647-5b14b2ef-793d-4eae-af02-433c6f5e05a7.png)
- http://localhost:8080/product-detail/id: hiện chi tiết sản phẩm id là id sản phẩm
![image](https://user-images.githubusercontent.com/130289045/230772878-1bb6ad66-d5ea-4dd5-a05f-5afe635d4f5b.png)
- http://localhost:8080/product-list: hiện danh sách sản phẩm
![image](https://user-images.githubusercontent.com/130289045/230772929-adc0cb73-5bb9-41c5-af63-d41ee338be1a.png)
- http://localhost:8080/product: quản lý danh sách sản phẩm
![image](https://user-images.githubusercontent.com/130289045/230772975-7fd5fa3b-02da-4fd8-9079-c7125e046aff.png)
- http://localhost:8080/product/add-product: hiện form thêm sản phẩm
![image](https://user-images.githubusercontent.com/130289045/230773013-9fbbe3cc-57d3-4069-90ca-0ca9bc9af463.png)
- http://localhost:8080/product/view/id: hiện chi tiết sản phẩm id là id sản phẩm
![image](https://user-images.githubusercontent.com/130289045/230773082-ed584371-f8a9-44f7-8589-112b45f4e831.png)
- http://localhost:8080/product/edit/id: hiện form cật nhật sản phẩm id là id sản phẩm
![image](https://user-images.githubusercontent.com/130289045/230773151-2dc55d59-7076-4dd7-8bcd-cf29825c2c2a.png)
- http://localhost:8080/brand:  quản lý nhãn hiệu
![image](https://user-images.githubusercontent.com/130289045/230773186-f295a8d3-d9bb-4381-b765-68bfcde5f5bf.png)
-	http://localhost:8080/brand/add-brand:  hiện form tạo mới nhãn hiệu
![image](https://user-images.githubusercontent.com/130289045/230773223-ea853442-1117-448a-bd46-0b4b1a4add3a.png)
-	http://localhost:8080/category: quản lý loại sản phẩm
![image](https://user-images.githubusercontent.com/130289045/230773372-3bb0ded2-ed70-4495-84be-0d7aa42eee46.png)
-	http://localhost:8080/category/add-category: hiện form tạo mới loại sản phẩm
![image](https://user-images.githubusercontent.com/130289045/230773418-d246a844-7cae-4395-aca5-ceabde89119d.png)
-	http://localhost:8080/category/edit/id:  cập nhật loại sản phẩm
![image](https://user-images.githubusercontent.com/130289045/230773492-5dbbcb1f-a287-46a0-9415-51804997ddde.png)
-	http://localhost:8080/cart:  hiện giỏ hàng
![image](https://user-images.githubusercontent.com/130289045/230773543-e4749507-1e64-4ae5-b01f-c763e7ec7ab3.png)
-	http://localhost:8080/cart/add-to-cart: thêm sản phẩm vào giỏ hàng
![image](https://user-images.githubusercontent.com/130289045/230773649-d734c69f-2810-4d3d-8aeb-05598a198a23.png)
-	http://localhost:8080/cart/check-out:  thanh toán
![image](https://user-images.githubusercontent.com/130289045/230773834-0073ddb0-63fe-42ad-8e8e-b7cef2dae935.png)

## Sơ dồ ERD
![Blank diagram (1)](https://user-images.githubusercontent.com/130289045/230773867-363bada7-929a-4b18-aedf-21240f6631a1.png)


## Flow Chart
### User
![user](https://user-images.githubusercontent.com/130289045/230773910-bf1a8e56-5f94-4d2c-8492-aac3cb8ac886.png)

### Admin
![Admin](https://user-images.githubusercontent.com/130289045/230773924-e0d96f43-dad0-42c9-b2f0-1937d06ff3f9.png)









