import java.util.Scanner;
import java.util.Calendar;

public class NhanVien
{
    private String manhanvien,ho, ten , gioitinh , ngaysinh, trinhdongonngu;
    Scanner sc = new Scanner(System.in);
    // constructor
    public NhanVien(){}
    public NhanVien(String manhanvien,String ho, String ten ,String gioitinh, String ngaysinh, String trinhdongonngu)
    {
        this.manhanvien = manhanvien;
        this.ho = ho;
        this.ten = ten;
        this.gioitinh = gioitinh;
        this.ngaysinh = ngaysinh;
        this.trinhdongonngu = trinhdongonngu;
    }
    public NhanVien(NhanVien hdv)
    {
        manhanvien = hdv.manhanvien;
        ho = hdv.ho;
        ten = hdv.ten;
        gioitinh = hdv.gioitinh;
        ngaysinh = hdv.ngaysinh;
        trinhdongonngu = hdv.trinhdongonngu;
    }
       public int getTuoi() {
        String[] tach = ngaysinh.split("/");
        String nam = tach[tach.length-1];
        int namsinh = Integer.parseInt(nam);
        Calendar a = Calendar.getInstance();
        int namhientai = a.get(Calendar.YEAR);
        int tuoi = namhientai - namsinh;
        return tuoi;
    }

    // get set
    public String getManhanvien()
    {
        return manhanvien;
    }
    public void setManhanvien(String manhanvien)
    {
        this.manhanvien = manhanvien;
    }
    public String getHo()
    {
        return ho;
    }
    public void setHO(String ho)
    {
        this.ho = ho;
    }
    public String getTen()
    {
        return ten;
    }
    public void setTEN(String ten)
    {
        this.ten = ten;
    }
    public String getGioitinh()
    {
        return gioitinh;
    }
    public void setGioiTinh(String gioitinh)
    {
        this.gioitinh = gioitinh;
    }
    public String getNgaysinh()
    {
        return ngaysinh;
    }
    public void setNgaySinh(String ngaysinh)
    {
        this.ngaysinh = ngaysinh;
    }
    public String getTrinhdongonngu()
    {
        return trinhdongonngu;
    }
    public void setTrinhDoNgonNgu(String trinhdongonngu)
    {
        this.trinhdongonngu = trinhdongonngu;
    }
    public void nhap()
    {
        do
        {   
            System.out.print("Nhap ma nhan vien: ");
            manhanvien = sc.nextLine();
            if(manhanvien.length()==0)
                System.out.println("Vui long khong de trong du lieu. Moi ban nhap lai!!");
        }while(manhanvien.length()==0);
        do
        {   
            System.out.print("Nhap ho nhan vien: ");
            ho = sc.nextLine();
            if(ho.length()==0)
                System.out.println("Vui long khong de trong du lieu. Moi ban nhap lai!!");
        }while(ho.length()==0);
        
        do
        {   
            System.out.print("Nhap ten nhan vien: ");
            ten = sc.nextLine();
            if(ten.length()==0)
                System.out.println("Vui long khong de trong du lieu. Moi ban nhap lai!!");
        }while(ten.length()==0);
        
         do
        {   
            System.out.print("Nhap gioi tinh nhan vien: ");
            gioitinh = sc.nextLine();
            if(!gioitinh.equals("Nam") && !gioitinh.equals("Nu"))
                System.out.println("Gioi vui long nhap gioi tinh 'Nam' hoac 'Nu'!!!");
        }while(!gioitinh.equals("Nam") && !gioitinh.equals("Nu"));

        KiemTraDinhDang validator = new KiemTraDinhDang("dd/MM/yyyy");
         do
        {   
            System.out.println("Nhap thoi diem di theo dinh dang dd/MM/yyyy");
            System.out.print("Nhap ngay thang nam sinh nhan vien: ");
            ngaysinh = sc.nextLine();
            if(!validator.Check(ngaysinh))
                System.out.println("Nhap sai dinh dang!!Moi ban nhap lai.");
        }while(!validator.Check(ngaysinh));
        
         do
        {   
           System.out.print("Nhap trinh do ngon ngu cua nhan vien: ");
            trinhdongonngu = sc.nextLine();
            if(trinhdongonngu.length()==0)
                System.out.println("Vui long khong de trong du lieu. Moi ban nhap lai!!");
        }while(trinhdongonngu.length()==0);
    }
    public void xuat() {
        System.out.format("||%11s | ", manhanvien);
        System.out.format("%8s |", ho);
        System.out.format("%20s |",ten);
        System.out.format("%10s |", gioitinh);
        System.out.format("%12s |", ngaysinh);
        System.out.format("%14s ||\n", trinhdongonngu);

    }   
     
}