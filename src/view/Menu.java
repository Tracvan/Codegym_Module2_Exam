package view;

import MenuController.MenuController;

import java.io.IOException;

public class Menu {
    public static void showMenu() throws InterruptedException, IOException {
        System.out.println("-----Chương trình quản lý sản phẩm-------");
        System.out.println("Chọn chức năng theo số để tiếp tục");
        System.out.println("1. Xem danh sách");
        System.out.println("2. Thêm mới");
        System.out.println("3. Cập nhật");
        System.out.println("4. Xóa ");
        System.out.println("5. Sắp xếp");
        System.out.println("6. Tìm kiếm sản phẩm đắt nhất");
        System.out.println("7. Đọc từ file");
        System.out.println("8. Ghi từ file");
        System.out.println("9. Thoát");
        MenuController.load();
    }
}
