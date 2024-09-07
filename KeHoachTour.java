
import java.io.IOException;
import java.util.Scanner;
public class KeHoachTour
{
    private String MaKeHoach, MaTour, MaNhanVien, NgayDi, NgayVe;
    static DanhSachTour dst = new DanhSachTour();
    static DanhSachNhanVien dsnv = new DanhSachNhanVien();
    DSCTKH ctkh = new DSCTKH();
    
    private long tongtien;
    Scanner sc = new Scanner(System.in);
    public KeHoachTour(){}
    public KeHoachTour(String MaKeHoach, String MaTour, String MaNhanVien, String NgayDi, String NgayVe)
    {
        this.MaKeHoach=MaKeHoach;
        this.MaTour=MaTour;
        this.MaNhanVien=MaNhanVien;
        this.NgayDi=NgayDi;
        this.NgayVe=NgayVe;
    }
    public KeHoachTour(KeHoachTour kh)
    {
        MaKeHoach=kh.MaKeHoach;
        MaTour=kh.MaTour;
        MaNhanVien=kh.MaNhanVien;
        NgayDi=kh.NgayDi;
        NgayVe=kh.NgayVe; 
    }

    public void setTongtien(long tongtien) {
        this.tongtien = tongtien;
    }

    public long getTongtien() {
        ctkh.docFile();
        tongtien = ctkh.Tongchiphitour(MaKeHoach);
        return tongtien;
    }
    public String getMaKeHoach()
    {
        return MaKeHoach;
    }
    public void setMaKeHoach(String MaKeHoach)
    {
        this.MaKeHoach=MaKeHoach;
    }
    public String getMaTour()
    {
        return MaTour;
    }
    public void setMaTour(String MaTour)
    {
        this.MaTour=MaTour;
    }
    public String getMaNhanVien()
    {
        return MaNhanVien;
    }
    public void setMaNhanVien(String MaNhanVien)
    {
        this.MaNhanVien=MaNhanVien;
    }
    public String getNgaydi()
    {
        return NgayDi;
    }
    public void setNgaydi(String NgayDi)
    {
        this.NgayDi=NgayDi;
    }
     public String getNgayVe()
    {
        return NgayVe;
    }
    public void setNgayVe(String NgayVe)
    {
        this.NgayVe=NgayVe;
    }

    public void Nhap() throws IOException
    {
        do
        {
            System.out.print("Nhap ma ke hoach: ");
            MaKeHoach=sc.nextLine();
            if(MaKeHoach.length()==0)
                System.out.println("Vui long khong de trong du lieu");
        }while(MaKeHoach.length()==0);

        do
        {
            System.out.print("Nhap ma tour: ");
            MaTour=sc.nextLine();
            dst.readDataFromFile();
            if(dst.TimKiemMaTour(MaTour)==-1)
                System.out.println("Khong tim thay ma tour trong du lieu!!Vui long nhap lai");
        }while(dst.TimKiemMaTour(MaTour)==-1);

    
        do
        {
            System.out.print("Nhap ma nhan vien: ");
            MaNhanVien=sc.nextLine();
            dsnv.readDataFromFile();
            if(dsnv.timkiemma(MaNhanVien)==-1)
                System.out.println("Khong tim thay ma nhan vien trong danh sach!!Vui long nhap lai");
        }while(dsnv.timkiemma(MaNhanVien)==-1);

        NgayThangNam validator = new KiemTraDinhDang("dd/MM/yyyy");
        NgayDi = dst.NgayDiTimThay(MaTour);
        do
        {
            System.out.println("Vui long nhap theo dinh dang DD/MM/YYYY");
            System.out.print("Nhap ngay ve: ");
            NgayVe=sc.nextLine();
            if(!validator.Check(NgayVe))
                System.out.println("Nhap sai dinh dang vui long nhap lai");
        }while (!validator.Check(NgayVe));
        // ctkh.docFile();
        // tongtien = ctkh.Tongchiphitour(MaKeHoach);
    }
    public void Xuat()
    {   
        ctkh.docFile();
        tongtien=ctkh.Tongchiphitour(MaKeHoach);
        System.out.format("|| %9s | ", MaKeHoach);
        System.out.format("%8s | ", MaTour);
        System.out.format("%9s | ", MaNhanVien);
        System.out.format("%11s | ", NgayDi);
        System.out.format("%11s | ", NgayVe);
        System.out.format("%10d ||", tongtien);
    }
}