import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
public class DSNH {
    static NhaHang []a;
    int n;
    Scanner sc=new Scanner(System.in);

    public DSNH (){}

    public DSNH (NhaHang []a,int n){
        this.n=n;
        a=new NhaHang[n];
    }
    
    public void nhap() throws IOException{
        System.out.print("Nhap n hoa don dau tien: ");
        n = sc.nextInt();
        sc.nextLine();
        a = new NhaHang[n];
        for(int i = 0; i < n; i++){
            a[i]= new NhaHang();
            a[i].nhap();
            if(i > 0) {
              checkId(i);
            }
        }
        ghiFile();
    }
    
    public boolean isIdExist(String id, int x) {
        for(int i = 0; i < x; i++) {
            if(a[i].getManhahang().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public boolean isIdExist(String id) {
        int count = 0;
        for(int i = 0; i < n; i++) {
            if(a[i].getManhahang().equals(id)) {
                count++;
            }
        }
        if(count == 2) {
            return true;
        }
        return false;
    }

    public void checkId(int i) {
        while(isIdExist(a[i].getManhahang(), i)) {
            System.err.println("Ma  vua nhap bi trung lap voi ma  khac!!!");
            System.err.println("Nhan Enter de nhap lai!!!");
            sc.nextLine();
            System.out.println("Nhap lai ma :");
            String id = sc.nextLine();
            a[i].setMaNhaHang(id);
        }
    }

    public void xuat() {
        System.out.println("====================Danh sach nha hang===================");
        System.out.format("|| %5s | %15s | %12s | %10s ||\n", "Stt", "Ma nha hang", "Ten", "Chi phi");
        for(int i = 0; i < n; i++) {
            System.out.format("|| %5d |", i + 1);
            a[i].xuat();
        }
        System.out.println("=========================================================");
    }
    public void them() throws IOException {
        n = a.length;
        a = Arrays.copyOf(a, n + 1);
        a[n] = new NhaHang();
        System.out.println("Nhap thong tin nha hang can them: ");
        a[n].nhap();
        checkId(n);
        n++;
        ghiFile();
    }

    public void them(NhaHang nh)throws IOException{
        n = a.length;
        a = Arrays.copyOf(a, n + 1);
        a[n] = new NhaHang(nh);
        checkId(n);
        n++;
        ghiFile();
    }

    public void sua() throws IOException{
        int k ;
        String ma;
        NhaHang Suanh=new NhaHang();
        System.out.println("Nhap ma nha hang ban can sua");
        ma=sc.nextLine();
        k=timkiemma(ma);
        if (k!=-1){
            System.out.println("++++++++Nhap thong tin nha hang can sua++++++++");
            Suanh.nhap();
            a[k]=Suanh;
            checkId(k);
            ghiFile();
            
            
        }
        else System.out.println("Ma khong ton tai");

    }

    public void sua(String ma) throws IOException {
        int k ;
        NhaHang Suanh=new NhaHang();
        k=timkiemma(ma);
        if (k!=-1){
            System.out.println("++++++++Nhap thong tin nha hang can sua++++++++");
            Suanh.nhap();
            a[k]=Suanh;
            checkId(k);
            ghiFile();
            
            
        }
        else System.out.println("Ma khong ton tai");

    }

    public void timkiem(){
        System.out.print("Nhap ma nha hang muon tim: ");
        String ma = sc.nextLine();
        boolean isExisted = false;
        n = a.length;
        for (int i = 0; i < n; i++) {
            if(a[i].getManhahang().equals(ma)) {
                isExisted = true;
                System.out.println("Thong tin nha hang can tim: ");
                a[i].xuat();
                break;
            }
        }
        if(!isExisted) 
            System.out.println("Khong tim thay ma nha hang!");
    }
    
    public int timkiemma(String ma){
        n = a.length;
        int vitri = -1;
        for (int i = 0; i < n; i++) {
            if(a[i].getManhahang().equals(ma)) {
                vitri = i;
                break;
            }
        }
        return vitri;
    }
    
    public NhaHang timkiem(String ma){
        n = a.length;
        for (int i = 0; i < n; i++) {
            if (a[i].getManhahang().equals(ma)) {
                return a[i];
            }
        }  
        return null;
    }
    public void xoa() throws IOException{
        System.out.print("Nhap ma nha hang muon xoa: ");
        String id = sc.nextLine();
        n = a.length;
        int vitri = timkiemma(id);
        if(vitri == -1) 
            System.out.println("Khong tim thay ma nha hang !");
        else {
            a = Arrays.copyOf(a, n + 2);
            for(int i = vitri; i < n; i++) {
                a[i] = a[i + 1];
            }
            a = Arrays.copyOf(a, n - 1);
            n--;
            ghiFile();
        }
    }
    
    public void xoa(String ma) throws IOException{
        n = a.length;
        int vitri = timkiemma(ma);
        if(vitri == -1) 
            System.out.println("Khong tim thay ma nha hang !");
        else {
            a = Arrays.copyOf(a, n + 2);
            for(int i = vitri; i < n; i++) {
                a[i] = a[i + 1];
            }
            a = Arrays.copyOf(a, n - 1);
            n--;
            ghiFile();
        }
    }
    public void timKiemChiPhiLonHon(double gioiHanChiphi) {
        System.out.format("Cac khu vui choi co chi phi lon hon %.2f:%n", gioiHanChiphi);
        System.out.format("%10s | %10s | %10s | %15s%n", "Ma Khu", "Dia Diem", "Ten", "Chi Phi");
        for (int i = 0; i < n   ; i++) {
            if (a[i].getChiPhi() > gioiHanChiphi) {
                a[i].xuat();
            }
        }
    }
    public void timKiemChiPhiLonHon() {
        System.out.print("Nhap gioi han chi phi tu ban phim: ");
        double gioiHanChiphi = sc.nextDouble();

        System.out.format("Cac khu vui choi co chi phi lon hon %.2f:%n", gioiHanChiphi);
        System.out.format("%10s | %10s | %10s | %15s%n", "Ma Khu", "Dia Diem", "Ten", "Chi Phi");
        for (int i = 0; i < n; i++) {
            if (a[i].getChiPhi() > gioiHanChiphi) {
                a[i].xuat();
            }
        }
    }
    public void thongKeTen() {
        Map<String, Integer> thongKe = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String ten =a[i].getTen();

            if (thongKe.containsKey(ten)) {
                thongKe.put(ten, thongKe.get(ten) + 1);
            } else {
                thongKe.put(ten, 1);
            }
        }

        System.out.println("Thong ke ten cac khu vui choi:");
        System.out.format("%10s | %15s%n", "Ten", "So Luong");
        for (Map.Entry<String, Integer> entry : thongKe.entrySet()) {
            System.out.format("%10s | %15s%n", entry.getKey(), entry.getValue());
        }
    }
    
    public void timKiemTheoTen() {
        String tenCanTim=sc.nextLine();
        System.out.println("Ket qua tim kiem theo ten: " + tenCanTim);
        System.out.format("%5s | %12s | %13s | %12s%n", "STT","Ma nha hang", "Ten", "Chi Phi");
        n=a.length;
        boolean result = false;
        
        for (int i = 0; i < n; i++) {
            if (a[i].getTen().equalsIgnoreCase(tenCanTim)) {
                a[i].xuat();
                result = true;
            }
        }

        if (!result) {
            System.out.println("Khong tim thay ket qua cho ten: " + tenCanTim);
        }
    }

     public void ghiFile() throws IOException {
        n=a.length;
        DataOutputStream  output = new DataOutputStream(new FileOutputStream("data/datanh.txt"));
        for (int i=0;i<n;i++){
            output.writeUTF(a[i].getManhahang());
            output.writeUTF(a[i].getTen());
            output.writeInt(a[i].getChiPhi());
        }
        output.close();
    }

    public void docFile(){
       a= new NhaHang[100];
       int i=0;
       try {
            DataInputStream input =new DataInputStream(new FileInputStream("data/datanh.txt"));
            try{
                while ( true ){
                    a[i]=new NhaHang();
                    a[i].setMaNhaHang(input.readUTF());
                    a[i].setTen(input.readUTF());
                    a[i].setChiPhi(input.readInt());
                    i++;

                }
            }
            catch (IOException e){}
            finally{
            n=i;
            a=Arrays.copyOf(a,n);
            input.close();
        }
    } 
       catch (IOException e){}

    }
    
    public void showMenu() {
        System.out.println("===========-Option-==========");
        System.out.println("||    1. Them nha hang.    ||");
        System.out.println("||    2. Xoa nha hang.     ||");
        System.out.println("||    3. Sua thong tin.    ||");
        System.out.println("||   4. Tim kiem nha hang. ||");
        System.out.println("||    5. Xem danh sach.    ||");
        System.out.println("||       0. Thoat.         ||");
        System.out.println("=============================");
    }
    public void showMenutimkiem()
    {
        System.out.println("==============-Option-============");
        System.out.println("||    1. Tim kiem ma nha hang   ||");
        System.out.println("||   2. Tim kiem ten nha hang   ||");
        System.out.println("||  3. Tim kiem chi phi lon hon ||");
        System.out.println("||          0. Thoát            ||");
        System.out.println("==================================");
    }
    public void MenuTimKiem()
    {
        String choose = null;
        boolean exit = false;
        showMenutimkiem();
        while (true) {
            System.out.print("Nhap so de lam viec: ");
            choose = sc.nextLine();
            switch (choose) {
            case "1":
                timkiem();
                break;
            case "2":
                timKiemTheoTen();
                break;
            case "3":
                timKiemChiPhiLonHon();
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
            System.out.print("Nhap so de lam viec: ");
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

    public void writeDataToFile() {
    }
}


    

