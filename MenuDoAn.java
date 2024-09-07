
import java.io.IOException;
import java.util.Scanner;

public class MenuDoAn {
    DanhSachTour tour = new DanhSachTour();
    DanhSachKeHoachTour kht = new DanhSachKeHoachTour();
    DSKH dskh = new DSKH();
    DanhSachNhanVien dsnv = new DanhSachNhanVien();
    DSVe  dsv = new DSVe();
    DSHDT dshdt = new DSHDT();
    DanhSachHoaDonChi dshdc = new DanhSachHoaDonChi();
    DSNH nh = new DSNH();
    DSKS ks = new DSKS();
    DSVC vc = new DSVC();
    Scanner sc = new Scanner(System.in);

    public void thongkelaisuat() {
        dshdc.readDataFromFile();
        dshdt.readDataFromFile();
        System.out.print("Nhap nam can thong ke: ");
        String nam = sc.nextLine();
        System.out.println("Lai suat quy 1 nam " + nam + ": " + (dshdt.tongtienqui1(nam) - dshdc.tongtienqui1(nam)));
        System.out.println("Lai suat quy 2 nam " + nam + ": " + (dshdt.tongtienqui2(nam) - dshdc.tongtienqui2(nam)));
        System.out.println("Lai suat quy 3 nam " + nam + ": " + (dshdt.tongtienqui3(nam) - dshdc.tongtienqui3(nam)));
        System.out.println("Lai suat quy 4 nam " + nam + ": " + (dshdt.tongtienqui4(nam) - dshdc.tongtienqui4(nam)));
    }

    public void showMenu()
    { 
        System.out.println("================-Option-==============");
        System.out.println("||         1.Lam viec voi tour      ||");
        System.out.println("||    2.Lam viec voi ke hoach tour  ||");
        System.out.println("||      3.Lam viec voi khach hang   ||");
        System.out.println("||      4.Lam viec voi nhan vien    ||");
        System.out.println("||          5.Lam viec voi Ve       ||");
        System.out.println("||      6.Lam viec voi hoa don thu  ||");
        System.out.println("||      7.Lam viec voi hoa don chi  ||");
        System.out.println("||       8.Lam viec voi nha hang    ||");
        System.out.println("||       9.Lam viec voi khach san   ||");
        System.out.println("||       10.Lam viec voi vui choi   ||");
        System.out.println("||           11.Xem lai suat        ||");
        System.out.println("||               0.Exit             ||");
        System.out.println("======================================");
        System.out.print("Choose: ");
    }
    public void Menu() throws IOException
    {
        String choose = null;
        boolean exit = false;
        showMenu();
        while (true) {
            choose = sc.nextLine();
            switch (choose) {
            case "1":
                tour.readDataFromFile();
                tour.Menu();
                break;
            case "2":
                kht.readDataFromFile();
                kht.Menu();
                break;
            case "3":
                dskh.readDataFromFile();
                dskh.Menu();
                break;
            case "4":
                dsnv.readDataFromFile();
                dsnv.Menu();
                break;
            case "5":
                dsv.readDataFromFile();
                dsv.Menu();
                break;
            case "6":
                dshdt.readDataFromFile();
                dshdt.Menu();
                break;
            case "7":
                dshdc.readDataFromFile();
                dshdc.Menu();
                break;
            case "8":
                nh.docFile();
                nh.Menu();
                break;
            case "9":
                ks.docFile();
                ks.Menu();
                break;
            case "10":
                vc.docFile();
                vc.Menu();
                break;
            case "11":
                thongkelaisuat();
                break;
            case "0":
                System.out.println("Da thoat!");
                exit = true;
                break;
            default:
                System.err.println("Loi! Hay chon lai:");
                break;
            }
            if (exit) {
                break;
            }
            showMenu();
        }     
    }
}
