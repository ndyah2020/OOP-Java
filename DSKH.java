import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DSKH {
    int n;
    static KhachHang[] a;

    static DSHDT hd = new DSHDT();

    Scanner sc = new Scanner(System.in);


    public DSKH(){

    }

    public DSKH(int n){
        this.n=n;
        a = new KhachHang[n];
    }

    public boolean isIdExist(String id, int x) {
        for(int i = 0; i < x; i++) {
            if(a[i].getMa().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public boolean isIdExist(String id) {
        int count = 0;
        for(int i = 0; i < n; i++) {
            if(a[i].getMa().equals(id)) {
                count++;
            }
        }
        if(count == 2) {
            return true;
        }
        return false;
    }

    public void checkId(int i) {
        while(isIdExist(a[i].getMa(), i)) {
            System.err.println("Ma khach hang vua nhap bi trung voi ma khach hang khac!!!");
            System.err.println("Nhan Enter de nhap lai!!!");
            sc.nextLine();
            System.out.println("Nhap lai ma khach hang:");
            String id = sc.nextLine();
            a[i].setMa(id);
        }
    }

    public void nhap()throws IOException{
        System.out.print("Nhap n khach hang dau tien: ");
        n = sc.nextInt();
        sc.nextLine();
        a = new KhachHang[n];
        for(int i = 0; i < n; i++){
            a[i]= new KhachHang();
            a[i].nhap();
            if(i > 0) {
                checkId(i);
            }
        }
        writeDataToFile();
    }

    public void xuat() {
        readDataFromFile();
        System.out.println("======================================Danh sach khach hang=======================================");
        
		System.out.format("|| %5s | %10s | %8s | %20s | %10s | %9s | %11s ||\n", "Stt", "Ma", "Ho", "Ten", "Ngay sinh", "Gioi Tinh", "Sdt");
        for(int i = 0; i < n; i++) {
            System.out.format("|| %5d |", i + 1);
            a[i].xuat();
        }
        System.out.println("=================================================================================================");
    }

    public void them() throws IOException {
        n = a.length;
        a = Arrays.copyOf(a, n + 1);
        a[n] = new KhachHang();
        System.out.println("Nhap thong tin khach hang can them: ");
        a[n].nhap();
        checkId(n);
        n++;
        writeDataToFile();
    }

    public void them(KhachHang x) throws IOException{
        n = a.length;
        a = Arrays.copyOf(a, n + 1);
        a[n] = new KhachHang(x);
        n++;
        checkId(n);
        writeDataToFile();
    }
    
    public void sua() throws IOException {
        readDataFromFile();
        xuat();
        System.out.print("Nhap ma khach hang can duoc sua: ");
        String id = sc.nextLine();
        boolean isExisted = false;
        n = a.length;
        for (int i = 0; i < n; i++) {
            if(a[i].getMa().equals(id)) {
                isExisted = true;
                a[i].nhap();
                while(isIdExist(a[i].getMa())) {
                    System.err.println("Ma khach hang vua nhap bi trung voi ma khach hang khac!!!");
                    System.err.println("Nhan Enter de nhap lai!!!");
                    sc.nextLine();
                    System.out.println("Nhap lai ma khach hang:");
                    String ma = sc.nextLine();
                    a[i].setMa(ma);
                }
                hd.readDataFromFile();
                hd.changeIdKh(id, a[i].getMa());
                hd.writeDataToFile();
                writeDataToFile();
                break;
            }
        }
        if(!isExisted) 
            System.out.println("Khong tim thay ma khach hang!");
    }

    public void sua(String id) throws IOException {
        boolean isExisted = false;
        n = a.length;
        for (int i = 0; i < n; i++) {
            if(a[i].getMa().equals(id)) {
                isExisted = true;
                a[i].nhap();
                while(isIdExist(a[i].getMa())) {
                    System.err.println("Ma khach hang vua nhap bi trung voi ma khach hang khac!!!");
                    System.err.println("Nhan Enter de nhap lai!!!");
                    sc.nextLine();
                    System.out.println("Nhap lai ma khach hang:");
                    String ma = sc.nextLine();
                    a[i].setMa(ma);
                }
                hd.readDataFromFile();
                hd.changeIdKh(id, a[i].getMa());
                hd.writeDataToFile();
                writeDataToFile();
                break;
            }
        }
        if(!isExisted) 
            System.out.println("Khong tim thay ma khach hang!");
    }
    
    public void timkiem(){
        xuat();
        System.out.print("Nhap ma khach hang muon tim: ");
        String ma = sc.nextLine();
        boolean isExisted = false;
        n = a.length;
        System.out.println("======================================Danh sach khach hang=======================================");
        
		System.out.format("|| %5s | %10s | %8s | %20s | %10s | %9s | %11s ||\n", "Stt", "Ma", "Ho", "Ten", "Ngay sinh", "Gioi Tinh", "Sdt");
        for (int i = 0; i < n; i++) {
            if(a[i].getMa().equals(ma)) {
                System.out.format("|| %5d |", i + 1);
                isExisted = true;
                a[i].xuat();
                break;
            }
        }
        if(!isExisted) 
            System.out.println("Khong tim thay ma khach hang!");
        System.out.println("=================================================================================================");
    }
    
    public int timkiemma(String ma){
        n = a.length;
        int vitri = -1;
        for (int i = 0; i < n; i++) {
            if(a[i].getMa().equals(ma)) {
                vitri = i;
                break;
            }
        }
        return vitri;
    }
    
    public KhachHang timkiem(String ma){
        n = a.length;
        for (int i = 0; i < n; i++) {
            if (a[i].getMa().equals(ma)) {
                return a[i];
            }
        }  
        return null;
    }
    
    public void timkiemho(){
        xuat();
        System.out.print("Nhap ho khach hang muon tim: ");
        String ho = sc.nextLine();
        boolean isExisted = false;
        n = a.length;
        System.out.println("======================================Danh sach khach hang=======================================");
		System.out.format("|| %5s | %10s | %8s | %20s | %10s | %9s | %11s ||\n", "Stt", "Ma", "Ho", "Ten", "Ngay sinh", "Gioi Tinh", "Sdt");
        for(int i = 0; i < n; i++) {
            if (a[i].getHo().contains(ho)) {
                System.out.format("|| %5d |", i + 1);
                isExisted = true;
                a[i].xuat();
            }
        }
        if(!isExisted) 
            System.out.println("Khong co khach hang nao mang ho "+ho+"!");
        System.out.println("=================================================================================================");
    }

    public void timkiemho(String ho){
        boolean isExisted = false;
        n = a.length;
        for(int i = 0; i < n; i++) {
            if (a[i].getHo().contains(ho)) {
                isExisted = true;
                a[i].xuat();
            }
        }
        if(!isExisted) 
            System.out.println("Khong co khach hang nao mang ho "+ho+"!");
    }
    
    public void timkiemten(){
        xuat();
        System.out.print("Nhap ten khach hang muon tim: ");
        String ten = sc.nextLine();
        boolean isExisted = false;
        n = a.length;
        
        System.out.println("======================================Danh sach khach hang=======================================");
		System.out.format("|| %5s | %10s | %8s | %20s | %10s | %9s | %11s ||\n", "Stt", "Ma", "Ho", "Ten", "Ngay sinh", "Gioi Tinh", "Sdt");
        for (int i = 0; i < n; i++) {
            if (a[i].getTen().contains(ten)) {
                System.out.format("|| %5d |", i + 1);
                isExisted = true;
                a[i].xuat();
            }
        }
        if (!isExisted) 
            System.out.println("Khong co khach hang nao mang ten "+ten+"!");
        System.out.println("=================================================================================================");
    }
    public void timkiemten(String ten){
        boolean isExisted = false;
        n = a.length;
        for (int i = 0; i <n ; i++) {
            if (a[i].getTen().contains(ten)) {
                isExisted = true;
                a[i].xuat();
            }
        }
        if (!isExisted) 
            System.out.println("Khong co khach hang nao mang ten "+ten+"!");
    }

    public void xoa() throws IOException {
        readDataFromFile();
        xuat();
        System.out.print("Nhap ma khach hang muon xoa: ");
        String id = sc.nextLine();
        n = a.length;
        int vitri = timkiemma(id);
        if(vitri == -1) 
            System.out.println("Khong tim thay ma khach hang!");
        else {
            a = Arrays.copyOf(a, n + 2);
            for(int i = vitri; i < n; i++) {
                a[i] = a[i + 1];
            }
            a = Arrays.copyOf(a, n - 1);
            n--;
            writeDataToFile();
        }
    }
    
    public void xoa(String id) throws IOException{
        n = a.length;
        int vitri = timkiemma(id);
        if(vitri == -1) 
            System.out.println("Khong tim thay ma khach hang!");
        else {
            a = Arrays.copyOf(a, n + 2);
            for(int i = vitri; i < n; i++) {
                a[i] = a[i + 1];
            }
            a = Arrays.copyOf(a, n - 1);
            n--;
            writeDataToFile();
        }
    }

    public void thongketuoi(){
        int count1 = 0, count2 = 0, count3 = 0, count4 = 0, count5 = 0;
        n = a.length;
        for(int i = 0; i < n; i++) {
            if(a[i].getTuoi() > 0 && a[i].getTuoi() < 12) {
                count1++;
            }
            if(a[i].getTuoi() >= 12 && a[i].getTuoi() < 20) {
                count2++;
            }
            if(a[i].getTuoi() >= 20 && a[i].getTuoi() < 40) {
                count3++;
            }
            if(a[i].getTuoi() >= 40 && a[i].getTuoi() < 60) {
                count4++;
            }
            if(a[i].getTuoi() >= 60) {
                count5++;
            }
        }
        System.out.println("Bang thong ke do tuoi khach hang: ");
        System.out.println("Tuoi thieu nhi(0 - 12) :      " + count1);
        System.out.println("Tuoi vi thanh nien(12 - 20) : " + count2);
        System.out.println("Tuoi thanh nien(20 - 40) :    " + count3);
        System.out.println("Tuoi trung nien(40 - 60) :    " + count4);
        System.out.println("Tuoi gia(tren 60) :           " + count5);
    }

    public void thongkegioitinh(){
        Map<String,Integer> count=new HashMap<>();
        n=a.length;
        for(int i=0;i<n;i++){
            if(count.containsKey(a[i].getPhai())) count.put(a[i].getPhai(), count.get(a[i].getPhai())+1);
            else count.put(a[i].getPhai(), 1);
        }
        System.out.println("Bang thong ke gioi tinh cua sinh vien:");
        for (Map.Entry<String, Integer> entry : count.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    public void writeDataToFile() throws IOException {
        n = a.length;
        DataOutputStream out = new DataOutputStream(new FileOutputStream("data/dataKh.txt"));
        for(int i = 0; i < n; i++) {
            out.writeUTF(a[i].getMa());
            out.writeUTF(a[i].getHo());
            out.writeUTF(a[i].getTen());
            out.writeUTF(a[i].getPhai());
            out.writeUTF(a[i].getNgaysinh());
            out.writeUTF(a[i].getSdt());
        }
        out.close();
    }

    public void readDataFromFile() {
        a = new KhachHang[500];
        int i = 0;
        try {
            DataInputStream in = new DataInputStream(new FileInputStream("data/dataKh.txt"));
            try {
                while(true) {
                    a[i] = new KhachHang();
                    a[i].setMa(in.readUTF());
                    a[i].setHo(in.readUTF());
                    a[i].setTen(in.readUTF());
                    a[i].setPhai(in.readUTF());
                    a[i].setNgaysinh(in.readUTF());
                    a[i].setSdt(in.readUTF());
                    i++;
                }
            } catch (EOFException e) {

            } finally {
                n = i;
                a = Arrays.copyOf(a, n);
                in.close();
            }
        } catch (IOException e) {
    
        }
    }

//Menu quan li

    public void showMenu() {
        System.out.println("================Option==============");
        System.out.println("||       1. Them khach hang.      ||");
        System.out.println("||       2. Xoa khach hang.       ||");
        System.out.println("||  3. Sua thong tin khach hang.  ||");
        System.out.println("||    4. Tim kiem khach hang.     ||");
        System.out.println("||    5. Thong ke khach hang.     ||");
        System.out.println("||  6. Xem danh sach khach hang.  ||");
        System.out.println("||           0. Thoat             ||");
        System.out.println("====================================");
        System.out.print("Choose: ");
    }

    public void showMenutimkiem(){
        System.out.println("============Option==========");
        System.out.println("||   1. Tim kiem bang ma. ||");
        System.out.println("||  2. Tim kiem bang ho.  ||");
        System.out.println("||  3. Tim kiem bang ten. ||");
        System.out.println("||      0. Thoat.         ||");
        System.out.println("============================");
        System.out.print("Choose: ");
    }

    public void showMenuthongke(){
        System.out.println("============Option==========");
        System.out.println("|| 1. Thong ke gioi tinh. ||");
        System.out.println("||  2. Thong ke do tuoi.  ||");
        System.out.println("||       0. Thoat.        ||");
        System.out.println("============================");
        System.out.print("Choose: ");
    }

    public void Menutimkiem(){
        String choose = null;
        boolean exit = false;
        showMenutimkiem();
        while (true) {
            choose = sc.nextLine();
            switch (choose) {
            case "1":
                timkiem();
                break;
            case "2":
                timkiemho();
                break;
            case "3":
                timkiemten();
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

    public void Menuthongke(){
        String choose = null;
        boolean exit = false;
        showMenuthongke();
        while (true) {
            choose = sc.nextLine();
            switch (choose) {
            case "1":
                thongkegioitinh();
                break;
            case "2":
                thongketuoi();
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
            showMenuthongke();
        }  
    }

    public void Menu() throws IOException {
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
                Menutimkiem();
                break;
            case "5":
                Menuthongke();
                break;
            case "6":
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
}
