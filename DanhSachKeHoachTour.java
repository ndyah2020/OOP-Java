
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
public class DanhSachKeHoachTour {
    int n,a=0;
    static KeHoachTour[] kht;
    static DanhSachTour dst = new DanhSachTour();
    static DSCTKH ctkh = new DSCTKH();
    static DanhSachNhanVien nv = new DanhSachNhanVien();
    Scanner sc = new Scanner(System.in);
    public DanhSachKeHoachTour(){}
    public DanhSachKeHoachTour(int n) 
    {
        this.n = n;
        kht = new KeHoachTour[n];
    }
    public void swapMa(String macu, String mamoi)
    {
        for(int i=0; i<n; i++)
        {
            if(macu.equals(kht[i].getMaTour()))
            {
                kht[i].setMaTour(mamoi);
            }
            
        }
    }

    public long findDaysBetween (String dateString1, String dateString2) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date1 = dateFormat.parse(dateString1);

            Date date2 = dateFormat.parse(dateString2);

            LocalDate localDate1 = date1.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
            LocalDate localDate2 = date2.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();

            return ChronoUnit.DAYS.between(localDate1, localDate2);
        } catch (ParseException e) {
            e.printStackTrace();
            return -1;
        } 
    }
    
    public void Nhap() throws IOException
    {
        System.out.print("Nhap so danh sach ke hoach: ");
        n = sc.nextInt();
        sc.nextLine();
        kht = new KeHoachTour[n];
        for(int i = 0; i < n; i++)
        {
            System.out.print("NHAP LAI KE HOACH THU "+(i+1)+"\n");
            kht[i]=new KeHoachTour();
            kht[i].Nhap();
            writeDataToFile();
            if(i>0)
                MaDuyNhat(i);
            ctkh.nhap(kht[i].getMaKeHoach());
            
            // long daysBetween = findDaysBetween(kht[i].getNgaydi(), kht[i].getNgayVe());
            // for(int j = 0; j < daysBetween; j++) {
            //     ctkh.themkh(kht[i].getMaKeHoach());
            // }
            System.out.println("======Da them ke hoach vao danh sach======");
            
        }

    }
    public void MaDuyNhat(int i) {
		readDataFromFile();
		String MaKeHoach;
		String mkh = kht[i].getMaKeHoach();
		do {
			if(KiemTraMKH(mkh, i)) {
				System.out.println();
				//Xuat();
				System.err.println("\nKe hoach thu " + (i+1) + " co ma " + mkh + " bi trung ma ke hoach. An enter de tiep tuc");
				sc.nextLine();
				System.err.print("Nhap Lai Ma: ");
				MaKeHoach = sc.nextLine();
				kht[i].setMaKeHoach(MaKeHoach);
				writeDataToFile();
				mkh = kht[i].getMaKeHoach();
			}
		}while(KiemTraMKH(mkh, i));
	}
    public boolean KiemTraMKH(String makehoach, int k) {
		for(int i = a - 1; i >= 0; i--) {
			if(kht[i].getMaKeHoach().indexOf(makehoach) != -1 && i!= k) {
				return true;
			}
		}
		return false;
	}
    
    public void Xuat()
    {   
        readDataFromFile();
        n=kht.length;
        System.out.println("==========================-DANH SACH KE HOACH TOUR-============================");
        System.out.format("|| %9s |%9s |%7s |%12s |%12s |%11s ||\n",
                  "MaKeHoach", "MaTour", "MaNhanVien", "NgayDi", "NgayVe", "TongTien");
        try
        {
            for(int i=0; i<n; i++)
            {
                kht[i].Xuat();
                System.out.println();
                
            }
        }catch(NullPointerException npe) {
			
		}
        
        System.out.println("===============================================================================");
    }
    public String NgayDiTimThay(String Ma)
    {
        return TimKiemm(Ma).getNgaydi();
    }
    public String NgayVeTimThay(String Ma)
    {
        return TimKiemm(Ma).getNgayVe();
    }
    public static Date changeday(String day){
        String pattern ="dd/MM/yyyy";
        SimpleDateFormat sdf=new SimpleDateFormat(pattern);
        try{

            return sdf.parse(day);

        } catch (ParseException e){
            e.printStackTrace();
            return null;
        }

    }
    public String addOneDay(String day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(changeday(day));
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        Date newDate = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        day = dateFormat.format(newDate);
        return day;
    }
    public void Them() throws IOException
    {
        int i=n;
        System.out.println("======Nhap ke hoach muon them vao======");
        kht = Arrays.copyOf(kht,n+1);
        kht[i]= new KeHoachTour();
        kht[i].Nhap();
        writeDataToFile();
        MaDuyNhat(i);
        // Xuat();
        ctkh.docFile();
        long daysBetween = findDaysBetween(kht[i].getNgaydi(), kht[i].getNgayVe());
        String day = kht[i].getNgaydi();
        for(int j = 0; j < daysBetween; j++) {
            day = addOneDay(day);
            System.err.println("Nhap ke hoach ngay thu " + (j+1) + ": ");
            ctkh.themkh(kht[i].getMaKeHoach(), day);
        }
        System.out.println("======Da them ke hoach vao danh sach======");
        n++;
        
    } 
    public void Them(KeHoachTour x)
    {
        int i=n;
        kht = Arrays.copyOf(kht,n+1);
        kht[n]=new KeHoachTour();
        KeHoachTour kh = new KeHoachTour(x);
        kht[n] = kh;
        n++;
        writeDataToFile();
        MaDuyNhat(i);
        System.out.println("======Da them ke hoach vao danh sach======");
    }


    public void Sua() throws IOException
    {
        readDataFromFile();
        String MaSo;
        int vitri=-1;
        Xuat();
        System.out.print("Nhap ma so can sua: ");
        MaSo=sc.nextLine();
        vitri=TimKiem(MaSo);
        if(vitri!=-1)
        {
            kht[vitri].Nhap();
            writeDataToFile();
            MaDuyNhat(vitri);
            ctkh.docFile();
            long daysBetween = findDaysBetween(kht[vitri].getNgaydi(), kht[vitri].getNgayVe());
            String day = kht[vitri].getNgaydi();
            ctkh.changeMaKHT(MaSo, kht[vitri].getMaKeHoach());
            ctkh.ghiFile();
            for(int j = 0; j < daysBetween; j++) {
                day = addOneDay(day);
                System.err.println("Nhap ke hoach ngay thu " + (j+1) + ": ");
                ctkh.sua((kht[vitri].getMaKeHoach()),day);
            }   
        }
        else
            System.out.println("Khong tim thay");
        writeDataToFile();
    }
    
    public void Sua(String x) throws IOException
    {
        n=kht.length;
        int a=TimKiem(x);
        KeHoachTour SuaSv= new KeHoachTour();
        if(a!=-1)
        {
            System.out.println("======NHAP THONG TIN KE HOACH CAN SUA======");
            SuaSv.Nhap();
            writeDataToFile();
            kht[a]=SuaSv;
            writeDataToFile();
            MaDuyNhat(a);
        }
        else
            System.out.println("Khong tim thay");
    }
    public void XoaMa() throws IOException
    {
        ctkh.docFile();
        String MaSo;
        Xuat();
        System.out.print("Nhap ma so can xoa: ");
        MaSo=sc.nextLine();
        int vitri=TimKiem(MaSo);
        if(vitri!=-1)
        {
            for(int i=vitri; i<n-1; i++)
                kht[i]=kht[i+1];
            ctkh.xoa(MaSo);
            kht = Arrays.copyOf(kht,n-1);
            n--;
             writeDataToFile();
        }
        else
        {
            System.out.println("Khong tim thay ");
        }
    }
        //Xoa theo ma so co tham so
    public void XoaMa(String Ma)
    {
        n=kht.length;
        int a=TimKiem(Ma);
        if(a!=-1)
        {
            for(int i=a; i<n-1; i++)
                kht[i]=kht[i+1];
            kht = Arrays.copyOf(kht,n-1);
            n--;
             writeDataToFile();
        }
        else 
            System.out.println("Khong tim thay ");
    }
    public void TimKiem()
    {
        int flag=-1;
        String MaSo;
        Xuat();
        System.out.print("Nhap ma so can tim: ");
        MaSo=sc.nextLine();
        ChiTietKeHoach ct=ctkh.timkIem(MaSo);
        System.out.println(MaSo);
        System.out.println("==========================-DANH SACH KE HOACH TOUR-============================");
        System.out.format("|| %9s |%9s |%7s |%12s |%12s |%11s ||\n",
                  "MaKeHoach", "MaTour", "MaNhanVien", "NgayDi", "NgayVe", "TongTien");
        for(int i=0; i<n; i++)
        {
            if(kht[i].getMaKeHoach().equals(MaSo))
            {
                kht[i].Xuat();
                System.out.println();
                flag=1;
            }
        }
        if(flag==-1)
        {
            System.out.println("khong tim thay ");
        }
        System.out.println("===============================================================================");
        System.out.println("==================================Danh sach chi tiet ke hoach===================================");
        
		System.out.format("|| %5s | %10s | %10s | %10s | %10s | %8s | %15s ||\n", "Stt", "Makht", "manhahang", "makhachsan", "makhuvuichoi","tongchiphi","ngay");
        if(ct!=null)
            ctkh.timkiem(MaSo).xuat();
        else
            System.out.println("khong tim thay");
        System.out.println("================================================================================================");
    }
    //Tim kiem  theo ma so co tham so int
    public int TimKiem(String Ma)
    {
        n=kht.length;
        int flag=-1;
        for(int i=0; i<n; i++)
        {
            if(kht[i].getMaKeHoach().equals(Ma))
            {
                flag=i;
                break;
            }
        }
        return flag;
    }
    //Tim kiem  theo ma so co tham so theo kieu Sinhvien
    public KeHoachTour TimKiemm(String Ma)
    {
        n=kht.length;
        for(int i=0; i<n; i++)
        {
            if(kht[i].getMaKeHoach().equals(Ma))
                return kht[i];
        }
        return null;
    }
    public String TachNgay(String s)
    {
        String[] NgayThangNam = s.split("/");
        String Ngay=NgayThangNam[0];
        return Ngay;
    }
    public void TimKiemNgayDi()
    {
        n=kht.length;
        int flag=0;
        Xuat();
        String Ngay;
        System.out.println("Vui long nhap theo dinh dang dd/MM/yyyy");
        NgayThangNam validator = new KiemTraDinhDang("dd/MM/yyyy");
        do{
            System.out.print("Nhap ngay di: ");
            Ngay=sc.nextLine();
            if(!validator.Check(Ngay))
                System.out.println("Nhap sai dinh dang vui long nhap lai!!");
        }while(!validator.Check(Ngay));
        
        System.out.println("==========================-DANH SACH KE HOACH TOUR-============================");
        System.out.format("|| %9s |%9s |%7s |%12s |%12s |%11s ||\n",
                  "MaKeHoach", "MaTour", "MaNhanVien", "NgayDi", "NgayVe", "TongTien");
        for(int i=0; i<n; i++)
        {
            if(kht[i].getNgaydi().contains(Ngay))
            {
                kht[i].Xuat();
                System.out.println();
                flag=1;
            }
        }
        if(flag==0)
        {
            System.out.println("khong tim thay ");
        }
        System.out.println("===============================================================================");
    }
    public void writeDataToFile()
    {
        n = kht.length;
        try
        {
            DataOutputStream out = new DataOutputStream(new FileOutputStream("data/KeHoachTour.txt"));
            try
            {
                for(int i = 0; i < n; i++) {
                out.writeUTF(kht[i].getMaKeHoach());
                out.writeUTF(kht[i].getMaTour());
                out.writeUTF(kht[i].getMaNhanVien());
                out.writeUTF(kht[i].getNgaydi());
                out.writeUTF(kht[i].getNgayVe());
                }
            }
            catch(NullPointerException npe) {}
            out.close();
        }   
        catch(IOException e) 
        {
            e.printStackTrace();    
        }
    }

    public void readDataFromFile() {
       kht = new KeHoachTour[100];
        int i = 0;
        try {
            DataInputStream in = new DataInputStream(new FileInputStream("data/KeHoachTour.txt"));
            try {
                while(true) {
                    kht[i] = new KeHoachTour();
                    kht[i].setMaKeHoach(in.readUTF());
                    kht[i].setMaTour(in.readUTF());
                    kht[i].setMaNhanVien(in.readUTF());
                    kht[i].setNgaydi(in.readUTF());
                    kht[i].setNgayVe(in.readUTF());
                    i++;
                }
            } catch (EOFException e) {

            } finally {
                a = i;
                n = i;
                kht = Arrays.copyOf(kht, n);
                in.close();
            }
        } catch (IOException e) {
       
        }
    }
    public void showMenutimkiem()
    {
        System.out.println("==============-Option-=========");
        System.out.println("||   1. Tim kiem ma ke hoach ||");
        System.out.println("||     2. Tim kiem ngay di   ||");
        System.out.println("||           0.Thoat         ||");
        System.out.println("===============================");
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
                TimKiem();
                break;
            case "2":
                TimKiemNgayDi();
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
    public void showMenu() {
        System.out.println("================-Option-================");
        System.out.println("||        1. Them ke hoach tour.      ||");
        System.out.println("||        2. Xoa ke hoach tour.       ||");
        System.out.println("||    3. Sua thong tin ke hoach tour. ||");
        System.out.println("||      4. Tim kiem ke hoach tour.    ||");
        System.out.println("||    5. Xem danh sach ke hoach tour. ||");
        System.out.println("||      6. Xem chi tiet hoach tour.   ||");
        System.out.println("||             0. Thoat.              ||");
        System.out.println("========================================");
        System.out.print("Choose: ");
    }
    public void Menu() throws IOException{
        String choose = null;
        boolean exit = false;
        showMenu();
        while (true) {
            choose = sc.nextLine();
            switch (choose) {
            case "1":
                Them();
                break;
            case "2":
                XoaMa();
                break;
            case "3":
                Sua();
                break;
            case "4":
                MenuTimKiem();
                break;
            case "5":
                Xuat();
                break;
            case "6":
                ctkh.Menu();
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
