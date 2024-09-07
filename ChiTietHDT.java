import java.io.IOException;
import java.util.Scanner;

public class ChiTietHDT {
    private String mahd;
    private String mave;
    private String soluong;
    DSVe ve = new DSVe();
    private long dongia;
    
    DSHDT hd = new DSHDT();

    Scanner sc = new Scanner(System.in);

    public ChiTietHDT() {
        
    }   

    public ChiTietHDT(String mahd,String mave, String soluong) {
        this.mahd = mahd;
        this.soluong = soluong;
        this.mave = mave;
    }

    public ChiTietHDT(ChiTietHDT a) {
        mahd = a.mahd;
        mave = a.mave;
        soluong = a.soluong;
    }

    public void updateTicket(String mave, String soluong) throws IOException {
        ve.readDataFromFile();
        ve.timkiem(mave).setSoluong(ve.timkiem(mave).getSoluong() - Integer.parseInt(soluong));
        ve.writeDataToFile();
    }

    public void nhap() throws IOException {
        System.out.println("Nhap ma ve: ");
        mave = sc.nextLine();
        while(true) {
            ve.readDataFromFile();
            if(ve.timkiemma(mave) != -1) {
                break;
            }
            System.err.println("Ma ve vua nhap khong ton tai!!!");
            System.err.println("Nhan Enter de tiep tuc!!!");
            sc.nextLine();
            ve.xuat();
            System.out.print("Nhap lai ma ve: ");
            String id = sc.nextLine();
            setMave(id);
        }
        while(true) {
            ve.readDataFromFile();
            if(ve.timkiem(mave).getSoluong() != 0) {
                break;
            }
            System.err.println("So luong ve da het!!! Vui long chon ma ve khac!!!");
            System.err.println("Nhan Enter de tiep tuc: ");
            sc.nextLine();
            System.out.print("Nhap lai ma ve: ");
            String id = sc.nextLine();
            setMave(id);
        }
        System.out.println("Nhap so luong: ");
        soluong = sc.nextLine();
        while(soluong.length() == 0) {
            System.err.println("Du lieu vua nhap la rong!!!");
            System.err.println("Nhan Enter de nhap lai!!!");
            sc.nextLine();
            System.out.print("Nhap lai ma :");
            String id = sc.nextLine();
            setSoluong(id);
        }
        while(true) {
            ve.readDataFromFile();
            if(Integer.parseInt(soluong) < ve.timkiem(mave).getSoluong()) {
                break;
            }
            System.err.println("So luong ve khong du!!! Ma ve nay chi con lai " + ve.timkiem(mave).getSoluong() + " ve!!!");
            System.err.println("Nhan Enter de tiep tuc!!!");
            sc.nextLine();
            System.out.print("Nhap lai so luong: ");
            String id = sc.nextLine();
            setSoluong(id);
        }
        updateTicket(mave, soluong);
        dongia = Integer.parseInt(ve.timkiem(mave).getGiave()) * Integer.parseInt(soluong);
    }

    public void xuat() {
        ve.readDataFromFile();
        dongia = Integer.parseInt(ve.timkiem(mave).getGiave()) * Integer.parseInt(soluong);
        System.out.format(" %10s | %10s | %8s | %15s ||\n", mahd, mave, soluong, dongia);
    }

    public void setMahd(String mahd) {
        this.mahd = mahd;
    }

    public String getMahd() {
        return mahd;
    }

    public void setMave(String mave) {
        this.mave = mave;
    }

    public String getMave() {
        return mave;
    }

    public void setSoluong(String soluong) {
        this.soluong = soluong;
    }

    public String getSoluong() {
        return soluong;
    }
    
    public long getDongia() {
        ve.readDataFromFile();
        dongia = Integer.parseInt(ve.timkiem(mave).getGiave()) * Integer.parseInt(soluong);
        return dongia;
    }
}
