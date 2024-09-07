import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class DanhSachHoaDonChi
{
    HoaDonChi[] a;
    int n;
    int v = 0;
    static DSCTKH ctkht = new DSCTKH();
    Scanner sc = new Scanner(System.in); 
    public DanhSachHoaDonChi()
    {

    }
    public DanhSachHoaDonChi (HoaDonChi[] a,int n)
    {
        this.a = a;
        this.n = n;
    }
    public void MaDuyNhat(int i) throws IOException
    {
        readDataFromFile();
        String mahoadon;
        String mhd = a[i].getMaHoaDon();
        do
        {
            if(KiemTraMHD(mhd,i))
            {
                System.out.println();
                xuat();
                System.err.println("Hoa don thu " + (i+1) + " co ma " + mhd +" bi trung ma ke hoach. Vui long nhan Enter de nhap lai!!");
                sc.nextLine();
                System.err.println("Nhap lai ma: ");
                mahoadon = sc.nextLine();
                a[i].setMahoadon(mahoadon);
                writeDataToFile();
                mhd = a[i].getMaHoaDon();
            }
        }
        while(KiemTraMHD(mhd,i));
    }


    public boolean KiemTraMHD(String mahoadon , int k )
    {
        for (int i = v-1; i >= 0 ; i--)
        {
            if(a[i].getMaHoaDon().indexOf(mahoadon) != -1 && i!= k)
            {
                return true;
            }
        }
        return false;
    }

    private Date changeDate(String dateString) {
        String pattern = "dd/MM/yyyy"; 
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            return sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }


    public long tongtienqui1(String nam) {
        long sum = 0;
        for(int i = 0; i < n; i++) {
            if(changeDate(a[i].getNgay()).compareTo(changeDate("01/01/" + nam)) > 0 && changeDate(a[i].getNgay()).compareTo(changeDate("31/03/" + nam)) < 0) {
                sum += a[i].getTongchiphi();
            }
        }
        return sum;
    }

    public long tongtienqui2(String nam) {
        long sum = 0;
        for(int i = 0; i < n; i++) {
            if(changeDate(a[i].getNgay()).compareTo(changeDate("01/04/" + nam)) > 0 && changeDate(a[i].getNgay()).compareTo(changeDate("30/06/" + nam)) < 0) {
                sum += a[i].getTongchiphi();
            }
        }
        return sum;
    }

    public long tongtienqui3(String nam) {
        long sum = 0;
        for(int i = 0; i < n; i++) {
            if(changeDate(a[i].getNgay()).compareTo(changeDate("01/07/" + nam)) > 0 && changeDate(a[i].getNgay()).compareTo(changeDate("30/09/" + nam)) < 0) {
                sum += a[i].getTongchiphi();
            }
        }
        return sum;
    }

    public long tongtienqui4(String nam) {
        long sum = 0;
        for(int i = 0; i < n; i++) {
            if(changeDate(a[i].getNgay()).compareTo(changeDate("01/10/" + nam)) > 0 && changeDate(a[i].getNgay()).compareTo(changeDate("31/12/" + nam)) < 0) {
                sum += a[i].getTongchiphi();
            }
        }
        return sum;
    }
    
    public void nhap() throws IOException
    {
        System.out.print("Nhap n danh sach hoa don chi dau tien: ");
        n = sc.nextInt();
        sc.nextLine();
        a = new HoaDonChi[n];
        for (int i = 0 ; i<n ; i++)
        {
            a[i] = new HoaDonChi();
            a[i].nhap();
        }
        writeDataToFile();
    }
    public void xuat()
    {
        // System.out.println("Danh sach hoa don chi la: ");
        // n = a.length;
        // for (int i = 0 ; i<n ; i++)
        // {
        //     a[i].xuat();
        // }
        n=a.length;
        System.out.println("======================-DANH SACH HOA DON CHI-====================");
        System.out.format("|| %11s |%14s |%14s |%15s||\n",
                  "MaHoaDon", "MaKeHoachTour", "MaNhanVien", "TongChiPhi");
        try
        {
            for(int i=0; i<n; i++)
            {
                a[i].xuat();
            }
        }catch(NullPointerException npe) {
			
		}
        
        System.out.println("=================================================================");
    }
    public void them() throws IOException
    {
        int i=n;
        a = Arrays.copyOf(a, n+1);
        a[n] = new HoaDonChi();
        System.out.println("Nhap thong tin hoa don chi can them");
        a[n].nhap();
        writeDataToFile();
        MaDuyNhat(i);
        n++;
        writeDataToFile ();
     } 
    public void them(HoaDonChi x)
    {
        n = a.length;
        a = Arrays.copyOf(a, n+1);
        a[n] = new HoaDonChi(x);
        n++;
    }
    public void sua() throws IOException
    {
        System.out.print("Nhap ma hoa don can duoc sua: ");
        String id = sc.nextLine();
        boolean isExisted = false;
        n = a.length;
        for(int i = 0 ; i<n ; i++)
        {
            if(a[i].getMaHoaDon().equals(id))
            {
                isExisted = true;
                a[i].nhap();
                writeDataToFile();;
                break;
            }

            
        }
        if(!isExisted)
        {
            System.out.println("Khong tim thay ma hoa don.");
        }

    }
    public void sua(String id)
    {
        boolean isExisted = false;
        n = a.length;
        for(int i = 0 ; i<n ; i++)
        {
            if(a[i].getMaHoaDon().equals(id))
            {
                isExisted = true;
                System.out.print("Ma hoa don duoc sua thanh: ");
                String mahd = sc.nextLine();
                a[i].setMahoadon(mahd);
                System.out.print("Ma ke hoach tua duoc sua la: ");
                String makht = sc.nextLine();
                a[i].setMakehoachtua(makht);
                System.out.print("Ma nhan vien duoc sua la: ");
                String manv = sc.nextLine();
                a[i].setManhanvien(manv);
                break;
            }
            if(!isExisted)
            {
                System.out.print("Khong tim thay ma hoa don.");
            }
            
        }

    }
    public void timkiem()
    {
        System.out.print("Nhap ma hoa don can tim kiem: ");
        String ma = sc.nextLine();
        boolean isExisted = false;
        n = a.length;
        for (int i = 0; i < n; i++) {
            if(a[i].getMaHoaDon().equals(ma)) {
                isExisted = true;
                System.out.println("Thong tin hoa don chi can tim: ");
                a[i].xuat();
                break;
            }
        }
        if(!isExisted) 
            System.out.println("Khong tim thay ma hoa don chi!");
    }
       public HoaDonChi timkiem(String ma){
        n = a.length;
        for (int i = 0; i < n; i++) {
            if (a[i].getMaHoaDon().equals(ma)) {
                return a[i];
            }
        }  
        return null;
    }
    public int timkiemma(String ma){
        n = a.length;
        int vitri = -1;
        for (int i = 0; i < n; i++) {
            if(a[i].getMaHoaDon().equals(ma)) {
                vitri = i;
                break;
            }
        }
        return vitri;
    }
    public void timkiemmakehoachtua()
    {
        System.out.print("Nhap ma ke hoach tua can tim: ");
        String makht = sc.nextLine();
        boolean isExisted = false;
        n = a.length;
        for(int i = 0 ; i<n ; i++)
        {
            if(a[i].getMaKeHoachTua().equals(makht))
            {
                isExisted = true;
                a[i].xuat();
            }
            if(!isExisted)
            {
                System.out.print("Khong tim thay hoa don cua ma ke hoach tua can tim!");
            }
        }
    }
    public void timkiemmakehoachtua(String makht)
    {
        boolean isExisted = false;
        n = a.length;
        for(int i = 0 ; i<n ; i++)
        {
             if(a[i].getMaKeHoachTua().equals(makht))
            {
                isExisted = true;
                a[i].xuat();
            }
            if(!isExisted)
            {
                System.out.print("Khong tim thay hoa don cua ma ke hoach tua can tim!");
            }
        }
    }
    public void timkiemmanhanvien()
    {
        System.out.print("Nhap ma nhan vien can tim: ");
        String manv = sc.nextLine();
        boolean isExisted = false;
        n = a.length;
        for(int i = 0 ; i<n ; i++)
        {
            if(a[i].getMaNhanVien().equals(manv))
            {
                isExisted = true;
                a[i].xuat();
            }
            if(!isExisted)
            {
                System.out.print("Khong tim thay hoa don cua ma nhan vien can tim!");
            }
        }
    }
    public void timkiemmanhanvien(String manv)
    {
        boolean isExisted = false;
        n = a.length;
        for(int i = 0 ; i<n ; i++)
        {
            if(a[i].getMaNhanVien().equals(manv))
            {
                isExisted = true;
                a[i].xuat();
            }
            if(!isExisted)
            {
                System.out.print("Khong tim thay hoa don cua ma nhan vien can tim!");
            }
        }
    }
    
    public void xoa(){
        System.out.print("Nhap ma hoa don muon xoa: ");
        String id = sc.nextLine();
        n = a.length;
        int vitri = timkiemma(id);
        if(vitri == -1) 
            System.out.println("Khong tim thay ma hoa don!");
        else {
            a = Arrays.copyOf(a, n + 1);
            for(int i = vitri; i < n; i++) {
                a[i] = a[i + 1];
            }
            a = Arrays.copyOf(a, n - 1);
            n--;
        }
    }
    
    public void xoa(String id){
        n = a.length;
        int vitri = timkiemma(id);
        if(vitri == -1) 
            System.out.println("Khong tim thay ma hoa don!");
        else {
            a = Arrays.copyOf(a, n + 2);
            for(int i = vitri; i < n; i++) {
                a[i] = a[i + 1];
            }
            a = Arrays.copyOf(a, n - 1);
            n--;
        }
    }
    

    public void writeDataToFile() throws IOException {
        n = a.length;
        DataOutputStream out = new DataOutputStream(new FileOutputStream("data/datahdc.txt"));
        for(int i = 0; i < n; i++) {
            out.writeUTF(a[i].getMaHoaDon());
            out.writeUTF(a[i].getMaKeHoachTua());
            out.writeUTF(a[i].getMaNhanVien());
        }
        out.close();
    }

    public void readDataFromFile() {
        a = new HoaDonChi[500];
        int i = 0;
        try {
            DataInputStream in = new DataInputStream(new FileInputStream("data/datahdc.txt"));
            try {
                while(true) {
                    a[i] = new HoaDonChi();
                    a[i].setMahoadon(in.readUTF());
                    a[i].setMakehoachtua(in.readUTF());
                    a[i].setManhanvien(in.readUTF());
                    i++;
                }
            } catch (EOFException e) {

            } finally {
                n = i;
                v  = i;
                a = Arrays.copyOf(a, n);
                in.close();
            }
        } catch (IOException e) {
       
        }
    }
    public void showMenu() {
        System.out.println("=============-Option-===========");
        System.out.println("||     1. Them hoa don.       ||");
        System.out.println("||      2. Xoa hoa don.       ||");
        System.out.println("||  3. Sua thong tin hoa don. ||");
        System.out.println("||    4. Tim kiem hoa don.    ||");
        System.out.println("||  5. Xem danh sach hoa don. ||");
        System.out.println("||  6. Xem chi tiet hoa don.  ||");
        System.out.println("||          0. Thoat.         ||");
        System.out.println("================================");
        System.out.print("Choose: ");
    }
    public void showMenutimkiem()
    {
        System.out.println("==============-Option-============");
        System.out.println("||    1. Tim kiem ma hoa don    ||");
        System.out.println("||  2. Tim kiem ma ke hoach tua ||");
        System.out.println("||   3. Tim kiem ma nhan vien   ||");
        System.out.println("||           0. Thoat.          ||");
        System.out.println("==================================");
        System.out.print("Choose: ");
    }
    public void MenuTimKiem()
    {
        String choose = null;
        boolean exit = false;
        showMenutimkiem();
        while (true) {
            choose = sc.nextLine();
            switch (choose) {
            case "1":
                timkiemma(choose);
                break;
            case "2":
                timkiemmakehoachtua();
                break;
            case "3":
                timkiemmanhanvien();
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
            showMenutimkiem();
        }
    }
    public void Menu() throws IOException{
        String choose = null;
        boolean exit = false;
        showMenu();
        while (true) {
            choose = sc.nextLine();
            switch (choose) {
            case "1":
                them();
                break;
            case "2":
                xoa();
                break;
            case "3":
                sua();
                break;
            case "4":
                MenuTimKiem();
                break;
            case "5":
                xuat();
                break;
            case "6":
                ctkht.xuat();
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