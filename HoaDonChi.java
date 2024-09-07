import java.util.Scanner;

public class HoaDonChi {


    private String makehoachtua;//them ma duy nhat cho ma ke hoach tua
    private String manhanvien;
    private String mahoadon;
    static DSCTKH ctkh = new DSCTKH();
    static DSVe ve = new DSVe();
    private long tongchiphi;
    // tongchiphi: = chitietkehoach.tongchiphicuatua(makehoachtua)

    static DanhSachKeHoachTour kht = new DanhSachKeHoachTour();
    static DanhSachNhanVien nv = new DanhSachNhanVien();
    

    Scanner sc = new Scanner(System.in);
    public HoaDonChi()
    {

    }
    public HoaDonChi(String mahoadon, String makehoachtua, String manhanvien)
    {
        this.mahoadon = mahoadon;
        this.makehoachtua = makehoachtua;
        this.manhanvien = manhanvien;
    }
    public HoaDonChi(HoaDonChi hdc)
    {
        this.mahoadon = hdc.mahoadon;
    
        this.makehoachtua = hdc.makehoachtua;
        this.manhanvien = hdc.manhanvien;
    }
    public String getMaHoaDon()
    {
        return mahoadon;
    }
    public void setMahoadon(String mahoadon)
    {
        this.mahoadon = mahoadon;
    }
    public String getMaKeHoachTua()
    {
        return makehoachtua;
    }
    public void setMakehoachtua(String makehoachtua)
    {
        this.makehoachtua = makehoachtua;
    }
    public String getMaNhanVien()
    {
        return manhanvien;
    }
    public void setManhanvien(String manhanvien)
    {
        this.manhanvien = manhanvien;
    }

    public String getNgay() {
        kht.readDataFromFile();
        return kht.NgayDiTimThay(makehoachtua);
    }

    public long getTongchiphi() {
        kht.readDataFromFile();
        ve.readDataFromFile();
        tongchiphi = kht.TimKiemm(makehoachtua).getTongtien() * ve.veDaBan(kht.TimKiemm(makehoachtua).getMaTour());
        return tongchiphi;
    }

    public void nhap()
    {
        
        do
        {
            kht.readDataFromFile();
            System.out.print("Nhap ma ke hoach tour: ");
            makehoachtua = sc.nextLine();
            if (kht.TimKiem(makehoachtua) == -1) {
                System.err.println("Ma ke hoach tour vua nhap khong ton tai!!");
                System.err.println("Nhan Enter de tiep tuc: ");
                sc.nextLine();
            }
        }while(kht.TimKiem(makehoachtua) == -1);
        System.out.print("Nhap ma hoa don: ");
        mahoadon = sc.nextLine();
        while (mahoadon.length() == 0) {
            System.err.println("Khong duoc de trong!!!");
            System.err.print("Vui long nhap lai ma hoa don: ");
            String id = sc.nextLine();
            setMahoadon(id);
        }
        System.out.print("Nhap ma nhan vien: ");
        manhanvien = sc.nextLine();
        while (true) {
            nv.readDataFromFile();
            if (nv.timkiemma(manhanvien) != -1) {
                break;
            }
            System.err.println("Ma nhan vien vua nhap khong ton tai!!");
            System.err.println("Nhan Enter de tiep tuc: ");
            sc.nextLine();
            nv.xuat();
            System.out.print("Nhap lai ma nhan vien: ");
            String id = sc.nextLine();
            setManhanvien(id);
            
        }
        kht.readDataFromFile();
        ve.readDataFromFile();
        tongchiphi = kht.TimKiemm(makehoachtua).getTongtien() * ve.veDaBan(kht.TimKiemm(makehoachtua).getMaTour());
    }
    public void xuat()
    {
        kht.readDataFromFile();
        ve.readDataFromFile();
        tongchiphi = kht.TimKiemm(makehoachtua).getTongtien() * ve.veDaBan(kht.TimKiemm(makehoachtua).getMaTour());
        System.out.format("||%12s | ", mahoadon);
        System.out.format("%13s | ", makehoachtua);
        System.out.format("%13s |", manhanvien);
        System.out.format("%15d||%n", tongchiphi);  // Hiển thị tổng chi phí
    }
}
