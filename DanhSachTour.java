
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class DanhSachTour {
    static Tour[] dst;
    int n,a=0;
    static DanhSachKeHoachTour kht = new DanhSachKeHoachTour();
    Scanner sc = new Scanner(System.in);
    public DanhSachTour(){}
    public DanhSachTour(int n) 
    {
        this.n = n;
        dst = new Tour[n];
    }
    public int getN()
    {
        return n;
    }
    public void setN(int n)
    {
        this.n=n;
    }
    
    public void Nhap()
    {
        int i=0;
        System.out.print("Nhap so tour: ");
        n = sc.nextInt();
        sc.nextLine();
        dst= new Tour[n];
        int number;
        while(i<n)
        {
            System.out.println("=====-Menu-NhapTourDuLich=====");
            System.out.println("||  1.Nhap tour trong nuoc  ||");
            System.out.println("||  2.Nhap tour ngoai nuoc  ||");
            System.out.println("||         0.Thoat          ||");
            System.out.println("==============================");
            System.out.print("Chon 1 hoac 2 hoac 0: ");
            number=sc.nextInt();
            sc.nextLine();
            switch(number)
            {
                case 1:
                        System.out.print("Nhap Tour thu: "+(i+1));
                        System.out.println();
                        dst[i]=new TourTrongNuoc();
                        dst[i].Nhap();
                        writeDataToFile();
                        if(i>0)
                            MaDuyNhat(i);
                        i++;
                    break;
                case 2:
                        System.out.print("Nhap Tour thu: "+(i+1));
                        System.out.println();
                        dst[i]=new TourNgoaiNuoc();
                        dst[i].Nhap();
                       writeDataToFile();
                        if(i>0)
                            MaDuyNhat(i);
                        i++;
                    break;
                case 0:
                    i=n;
                    break;   
                default:
                    System.out.println("Nhap sai so vui long nhap lai!!!"); 
            }
        }
    }
    public void MaDuyNhat(int i) {
		readDataFromFile();
		String MaTour;
		String mkh = dst[i].getMaTour();
		do {
			if(KiemTraMT(mkh, i)) {
				System.out.println();
				Xuat();
				System.err.println("\nTour thu " + (i+1) + " co ma " + mkh + " bi trung ma ke hoach. An enter de tiep tuc");
				sc.nextLine();
				System.err.print("Nhap Lai Ma: ");
				MaTour = sc.nextLine();
				dst[i].setMaTour(MaTour);
				writeDataToFile();
				mkh = dst[i].getMaTour();
			}
		}while(KiemTraMT(mkh, i));
	}
    public boolean KiemTraMT(String makehoach, int k) {
		for(int i = a - 1; i >= 0; i--) {
			if(dst[i].getMaTour().indexOf(makehoach) != -1 && i!= k) {
				return true;
			}
		}
		return false;
	}
    public void Xuat()
    {
        readDataFromFile();
        System.out.println("====================================================DANH SACH TOUR=============================================================");
        System.out.format("||%5s |%18s        |%10s |%13s |%5s |%10s |%15s |%15s ||\n",
                  "MaTour", "TenTour", "NoiKhoiHanh", "Noiden", "ThoiDiem Di","PhuongTien", "TinhThanh/QuocGia", "ThoiHanViSa");
        try
        {
            for(int i=0; i<n; i++)
            {
                dst[i].Xuat();
                dst[i].PhuongTien();
            }
        }
        catch(NullPointerException npe) {
			
		}
         System.out.println("=============================================================================================================================");
    }
    public String MaTourTimThay(String Ma)
    {
        return dst[TimKiemMaTour(Ma)].getMaTour();
    }
    public String NgayDiTimThay(String NgayDi)
    {
        return dst[TimKiemMaTour(NgayDi)].getThoiDiemDi();
    }
    public void Them()
    {
        int number=-1,i=n;
            System.out.println("=============-Them-============");
            System.out.println("||  1.Them Tour trong nuoc   ||");
            System.out.println("||   2.Them tour ngoai nuoc  ||");
            System.out.println("||         0.Thoat           ||");
            System.out.println("===============================");
            System.out.print("Chon 1 hoac 2 hoac 0: ");
            number=sc.nextInt();
            switch(number)
            {
                case 1:
                        dst = Arrays.copyOf(dst,n+1);
                        System.out.print("Nhap Tour can them vao");
                        System.out.println();
                        dst[i]=new TourTrongNuoc();
                        dst[i].Nhap();
                        n++;
                        writeDataToFile();
                        MaDuyNhat(i);
                        System.out.println("DA THEM TOUR VAO DANH SACH");
                    break;
                case 2:
                        dst = Arrays.copyOf(dst,n+1);
                        System.out.print("Nhap tour thu tour can them vao");
                        System.out.println();
                        dst[i]=new TourNgoaiNuoc();
                        dst[i].Nhap();
                        n++;
                        writeDataToFile();
                        MaDuyNhat(i);
                        System.out.println("DA THEM TOUR VAO DANH SACH");
                case 0:
                    break;
            }
    }
    public void Them(TourTrongNuoc x)
    {
        int i=n;
        dst = Arrays.copyOf(dst,n+1);
        dst[n]=new TourTrongNuoc();
        dst[n]=x;
        n++;
        writeDataToFile();
        MaDuyNhat(i);
        System.out.println("DA THEM TOUR VAO DANH SACH");
    }
    public void Them(TourNgoaiNuoc x)
    {
        int i=n;
        dst = Arrays.copyOf(dst,n+1);
        dst[i]=new TourNgoaiNuoc();
        dst[i]=x;
        n++;
        writeDataToFile();
        MaDuyNhat(i);
        System.out.println("DA THEM TOUR VAO DANH SACH");
    }
    public void XoaMa()
    {
        readDataFromFile();
        n=dst.length;
        int a;
        Xuat();
        String MaSo;
        System.out.print("Nhap ma so tour can xoa: ");
        MaSo=sc.nextLine();
        a=TimKiemMaTour(MaSo);
        if(a!=-1)
        {
            for(int i=a; i<n-1; i++)
                dst[i]=dst[i+1];
            dst = Arrays.copyOf(dst,n-1);
            n--;
           writeDataToFile();
        }
        else
        {
            System.out.println("Khong tim thay tour");
        }
    }
    //Xoa 1 tour theo ma so co tham so
    public void XoaMa(String Ma)
    {
        n=dst.length;
        int a=TimKiemMaTour(Ma);
        if(a!=-1)
        {
            for(int i=a; i<n-1; i++)
                dst[i]=dst[i+1];
            dst = Arrays.copyOf(dst,n-1);
            n--;
           writeDataToFile();
        }
        else 
            System.out.println("Khong tim thay tour");
    }
    public void Sua()
    {
        int number=-1;
        int ViTri;
        String MaSo;
        Xuat();
        System.out.print("Nhap ma so tour can sua: ");
        MaSo=sc.nextLine();
        ViTri=TimKiemMaTour(MaSo);
        if(ViTri!=-1)
        {
            System.out.println("==============SUA=============");
            System.out.println("||   1.Sua Tour trong nuoc   ||");
            System.out.println("||   2.Sua tour ngoai nuoc   ||");
            System.out.println("||         0.Thoat           ||");
            System.out.println("===============================");
            System.out.print("Chon 1 hoac 2 hoac 0: ");
            number=sc.nextInt();
            switch(number)
            {
                case 1:
                    TourTrongNuoc TrongNuoc =new TourTrongNuoc();
                        System.out.println("NHAP THONG TIN CAN SUA");
                        TrongNuoc.Nhap();
                        dst[ViTri]=TrongNuoc;
                        writeDataToFile();
                        MaDuyNhat(ViTri);  
                        kht.readDataFromFile();
                        kht.swapMa(MaSo, TrongNuoc.getMaTour());
                        kht.writeDataToFile();
                    break;
                case 2:
                    TourNgoaiNuoc NgoaiNuoc= new TourNgoaiNuoc();;
                        System.out.println("NHAP THONG TIN CAN SUA");
                        NgoaiNuoc.Nhap();
                        dst[ViTri]=NgoaiNuoc;    
                        writeDataToFile();
                        MaDuyNhat(ViTri);   
                        kht.readDataFromFile();
                        kht.swapMa(MaSo, NgoaiNuoc.getMaTour());
                        kht.writeDataToFile();      
                    break;
                case 0:
                    break;
            }
        }
        else
        {
            System.out.println("Khong tim thay Tour can sua");
        }
    
    }
    //Sua 1 tour co tham so theo ma tour
    public void Sua(String x)
    {
        int number=-1;
        int ViTri=TimKiemMaTour(x);
        if(ViTri!=-1)
        {
            System.out.println("==============SUA=============");
            System.out.println("||   1.Sua Tour trong nuoc   ||");
            System.out.println("||   2.Sua tour ngoai nuoc   ||");
            System.out.println("||         0.Thoat           ||");
            System.out.println("===============================");
            System.out.print("Chon 1 hoac 2 hoac 0: ");
            number=sc.nextInt();
            switch(number)
            {
                case 1:
                    TourTrongNuoc TrongNuoc =new TourTrongNuoc();
                    System.out.println("NHAP THONG TIN TOUR CAN SUA");
                    TrongNuoc.Nhap();
                    dst[ViTri]=TrongNuoc;

                    writeDataToFile();

                    MaDuyNhat(ViTri);
                    break;
                case 2:
                    TourNgoaiNuoc NgoaiNuoc= new TourNgoaiNuoc();
                    System.out.println("NHAP THONG TIN Tour CAN SUA");
                    NgoaiNuoc.Nhap();
                    dst[ViTri]=NgoaiNuoc;
                    writeDataToFile();
                    MaDuyNhat(ViTri);
                case 0:
                    break;
                default:
                    System.out.println("Nhap sai!! Hay Nhap lai");
            }
        }
        else
        {
            System.out.println("Khong tim thay Tour can sua");
        }
    }
    public void TimKiemMaTour()
    {
        n=dst.length;
        int flag=0;
        String MaSo;
        System.out.print("Nhap ma tour can tim: ");
        MaSo=sc.nextLine();
        System.out.println("====================================================DANH SACH TOUR=============================================================");
        System.out.format("||%5s |%18s        |%10s |%13s |%5s |%10s |%15s |%15s ||\n",
                  "MaTour", "TenTour", "NoiKhoiHanh", "Noiden", "ThoiDiem Di","PhuongTien", "TinhThanh/QuocGia", "ThoiHanViSa");
        for(int i=0; i<n; i++)
        {
            if(dst[i].getMaTour().equals(MaSo))
            {
                dst[i].Xuat();
                flag=1;
            }
        }
        if(flag==0)
        {
            System.out.println("khong tim thay tour");
        }
    System.out.println("=============================================================================================================================");
    }
        //Tim kiem tour theo ma so co tham so int
    public int TimKiemMaTour(String Ma)
    {
        n=dst.length;
        int flag=-1;
        for(int i=0; i<n; i++)
        {
            if(dst[i].getMaTour().equals(Ma))
            {
                flag=i;
                break;
            }
        }
        return flag;
    }
        //Tim kiem tour theo ma so co tham so theo kieu Sinhvien
    public Tour TimKiemmMaTour(String Ma)
    {
        n=dst.length;
        Tour flag=null;
        System.out.println("====================================================DANH SACH TOUR=============================================================");
        System.out.format("||%5s |%18s        |%10s |%13s |%5s |%10s |%15s |%15s ||\n",
                  "MaTour", "TenTour", "NoiKhoiHanh", "Noiden", "ThoiDiem Di","PhuongTien", "TinhThanh/QuocGia", "ThoiHanViSa");
        for(int i=0; i<n; i++)
        {
            if(dst[i].getMaTour().equals(Ma))
            {
                dst[i].Xuat();
                flag=dst[i];
            }
        }
        System.out.println("===============================================================================================================================");
        return flag;
    }
    public void TimKiemTen()
    {
        String Ten;
        int flag=0;
        System.out.println("Nhap ten tour can tim");
        Ten=sc.nextLine();
        System.out.println("====================================================DANH SACH TOUR=============================================================");
        System.out.format("||%5s |%18s        |%10s |%13s |%5s |%10s |%15s |%15s ||\n",
                  "MaTour", "TenTour", "NoiKhoiHanh", "Noiden", "ThoiDiem Di","PhuongTien", "TinhThanh/QuocGia", "ThoiHanViSa");
        for(int i=0; i<n; i++)
        {
            if(dst[i].getTenTour().contains(Ten))
            {
                dst[i].Xuat();
                flag=1;
            }
        }
        if(flag==0)
            System.out.println("Khong tim thay ten tour");
        System.out.println("=============================================================================================================================");
    }
    public void LoaiTour()
    {
        int Count1=0, Count2=0;
        for(int i=0; i<n; i++)
        {
            if(dst[i] instanceof TourTrongNuoc)
                Count1++;
            else
                Count2++;
        }
        System.out.println("Co "+Count1+" tour trong nuoc va "+Count2+" nuoc ngoai");
    }
    public String TachNgay(String s)
    {
        String[] NgayThangNam = s.split("/");
        String Ngay=NgayThangNam[0];
        return Ngay;
    }
    public void QuocGiaDen()
    {
        int Count1=0, Count2=0, Count3=0;
        for(int i=0; i<n; i++)
        {
            if(dst[i] instanceof TourNgoaiNuoc)
            {
                TourNgoaiNuoc NgoaiNuoc = new TourNgoaiNuoc();
                NgoaiNuoc=(TourNgoaiNuoc)dst[i];
                String QuocGia =NgoaiNuoc.getQuocGia();
                if(QuocGia.contains("Anh"))
                    Count1++;
                else if(QuocGia.contains("Phap"))
                    Count2++;
                else if(QuocGia.contains("AnDo"))
                    Count3++;
            }
        }
        System.out.println("=======Cac_Nam_To_Chuc=======");
        System.out.println("|| So den nuoc Anh      || " +Count1+ " |");
        System.out.println("|| So den nuoc Phap     || " +Count2+ " |");
        System.out.println("|| So den nuoc An Do    || " +Count3+ " |");
        System.out.println("=============================");
    }
    public void TimKiemNoiDen()
    {
        String NoiDen;
        int flag=0;
        System.out.println("Nhap noi den can tim trong danh sach");
        NoiDen=sc.nextLine();
        System.out.println("===================================================-DANH SACH TOUR-============================================================");
        System.out.format("||%5s |%18s        |%10s |%13s |%5s |%10s |%15s |%15s ||\n",
                  "MaTour", "TenTour", "NoiKhoiHanh", "Noiden", "ThoiDiem Di","PhuongTien", "TinhThanh/QuocGia", "ThoiHanViSa");
        for(int i = 0; i<n; i++)
        {
            if(dst[i].getNoiDen().contains(NoiDen))
            {
                dst[i].Xuat();
                System.out.println();
                flag=1;
            }
        }
        System.out.println("===============================================================================================================================");
        if(flag==0)
            System.out.println("Khong tim thay tour nao!!!");
    }
    public void writeDataToFile()
    {
        n=dst.length;
        try
        {
            DataOutputStream out = new DataOutputStream(new FileOutputStream("data/Tour.txt"));
            try
            {
                for (int i = 0; i < n; i++) 
                {       
                    if(dst[i] instanceof TourTrongNuoc)
                    {
                        out.writeInt(1);
                        TourTrongNuoc ttn = new TourTrongNuoc();
                        ttn=(TourTrongNuoc)dst[i];
                        out.writeUTF(ttn.getMaTour());
                        out.writeUTF(ttn.getTenTour());
                        out.writeUTF(ttn.getNoiKhoiHanh());
                        out.writeUTF(ttn.getNoiDen());
                        out.writeUTF(ttn.getThoiDiemDi());
                        out.writeUTF(ttn.getTinhThanh());                        
                    }
                    else if(dst[i] instanceof TourNgoaiNuoc)
                    {
                        out.writeInt(2);
                        TourNgoaiNuoc tnc = new TourNgoaiNuoc();
                        tnc = (TourNgoaiNuoc)dst[i];
                        out.writeUTF(tnc.getMaTour());
                        out.writeUTF(tnc.getTenTour());
                        out.writeUTF(tnc.getNoiKhoiHanh());
                        out.writeUTF(tnc.getNoiDen());
                        out.writeUTF(tnc.getThoiDiemDi());
                        out.writeUTF(tnc.getQuocGia());
                        out.writeUTF(tnc.getThoiHanVisa()); 

                    }
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
    public void readDataFromFile()
    {
        int i=0;
        try 
        {
            DataInputStream in = new DataInputStream(new FileInputStream("data/Tour.txt"));
            try
            {
                if (dst == null) {
                    dst = new Tour[100];
                }
                while (in.available() > 0) 
                {
                    int kieuDuLieu = in.readInt();
                    switch (kieuDuLieu)
                    {
                        case 1:
                            TourTrongNuoc ttn = new TourTrongNuoc();
                            ttn.setMaTour(in.readUTF());
                            ttn.setTenTour(in.readUTF());
                            ttn.setNoiKhoiHanh(in.readUTF());
                            ttn.setNoiDen(in.readUTF());
                            ttn.setThoiDiemDi(in.readUTF());
                            ttn.setTinhThanh(in.readUTF());
                            dst[i]=ttn;
                            i++;
                            break;
                        case 2:
                            TourNgoaiNuoc tnc = new TourNgoaiNuoc();
                            tnc.setMaTour(in.readUTF());
                            tnc.setTenTour(in.readUTF());
                            tnc.setNoiKhoiHanh(in.readUTF());
                            tnc.setNoiDen(in.readUTF());
                            tnc.setThoiDiemDi(in.readUTF());
                            tnc.setQuocGia(in.readUTF());
                            tnc.setThoiHanVisa(in.readUTF());
                            dst[i]=tnc;
                            i++;
                            break;
                        default:
                            System.out.println("Kiểu dữ liệu không xác định.");
                            break;
                    }
                }
            } 
            catch (EOFException e) { } 
            finally 
            {
                a=i;
                n=i;
                dst = Arrays.copyOf(dst, n);
                in.close();
            }
        }
        catch (IOException e) {}
    }
    public void showMenu() {
        System.out.println("===========-Option-==========");
        System.out.println("||     1. Them tour.       ||");
        System.out.println("||      2. Xoa tour.       ||");
        System.out.println("||  3. Sua thong tin tour. ||");
        System.out.println("||    4. Tim kiem tour.    ||");
        System.out.println("|| 5. Xem danh sach tour.  ||");
        System.out.println("||       0. Thoat.         ||");
        System.out.println("=============================");
        System.out.print("choose: ");
    }
    public void showMenutimkiem()
    {
        System.out.println("============-Option-==========");
        System.out.println("||   1. Tim kiem ma tour    ||");
        System.out.println("||   2. Tim kiem ten tour   ||");
        System.out.println("||      3. Tim noi den      ||");
        System.out.println("||       0. Thoat.          ||");
        System.out.println("==============================");
        System.out.print("choose: ");
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
                TimKiemMaTour();
                break;
            case "2":
                TimKiemTen();
                break;
            case "3":
                TimKiemNoiDen();
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
    public void Menu(){
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
