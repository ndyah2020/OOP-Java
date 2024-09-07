import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class DSVe {
    static Ve[] a;
    int n;

    DSChiTietHDT ct = new DSChiTietHDT();

    Scanner sc = new Scanner(System.in);

    public DSVe(){

    }

    public DSVe(int n){
        this.n=n;
        a = new Ve[n];
    }

    public int veDaBan(String matour) {
        int soluong = 0;
        for(int i = 0; i < n; i++) {
            if(a[i].getMatour().equals(matour)) {
                soluong += a[i].getSoluong();
            }
        }
        return 80 - soluong;
    }

    public boolean isIdExist(String id, int x) {
        for(int i = 0; i < x; i++) {
            if(a[i].getMave().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public boolean isIdExist(String id) {
        int count = 0;
        for(int i = 0; i < n; i++) {
            if(a[i].getMave().equals(id)) {
                count++;
            }
        }
        if(count == 2) {
            return true;
        }
        return false;
    }

    public void checkId(int i) {
        while(isIdExist(a[i].getMave(), i)) {
            System.err.println("Ma ve vua nhap bi trung voi ma ve khac!!!");
            System.err.println("Nhan Enter de nhap lai!!!");
            sc.nextLine();
            System.out.println("Nhap lai ma ve:");
            String id = sc.nextLine();
            a[i].setMave(id);
        }
    }

    public void nhap() throws IOException{
        System.out.print("Nhap n ve dau tien: ");
        n = sc.nextInt();
        sc.nextLine();
        a = new Ve[n];
        for(int i = 0; i < n; i++){
            a[i]= new Ve();
            a[i].nhap();
            if(i > 0) {
                checkId(i);
            }
        }
        writeDataToFile();
    }

    public void xuat() {
        System.out.println("==================================Danh sach ve=================================");
       
		System.out.format("|| %5s | %10s | %10s | %10s | %15s | %8s ||\n", "Stt", "Ma tour", "Ma ve", "Loai ve", "Gia ve", "So luong");
        for(int i = 0; i < n; i++) {
            System.out.format("|| %5d |", i + 1);
            a[i].xuat();
        }
         System.out.println("===============================================================================");
    }

    public void them() throws IOException{
        n = a.length;
        a = Arrays.copyOf(a, n + 1);
        a[n] = new Ve();
        System.out.println("Nhap thong tin ve can them: ");
        a[n].nhap();
        checkId(n);
        n++;
        writeDataToFile();
    }

    public void them(Ve x) throws IOException{
        n = a.length;
        a = Arrays.copyOf(a, n + 1);
        a[n] = new Ve(x);
        n++;
        checkId(n);
        writeDataToFile();
    }
    
    public void sua() throws IOException{
        System.out.print("Nhap ma ve can duoc sua: ");
        String id = sc.nextLine();
        boolean isExisted = false;
        n = a.length;
        for (int i = 0; i < n; i++) {
            if(a[i].getMave().equals(id)) {
                isExisted = true;
                a[i].nhap();
                while(isIdExist(a[i].getMave())) {
                    System.err.println("Ma ve vua nhap bi trung voi ma ve khac!!!");
                    System.err.println("Nhan Enter de nhap lai!!!");
                    sc.nextLine();
                    System.out.println("Nhap lai ma ve:");
                    String ma = sc.nextLine();
                    a[i].setMave(ma);
                }
                ct.readDataFromFile();
                ct.changeIdVe(id, a[i].getMave());
                ct.writeDataToFile();
                writeDataToFile();
                break;
            }
        }
        if(!isExisted) 
            System.out.println("Khong tim thay ma ve!");
    }

    public void sua(String id) throws IOException{
        boolean isExisted = false;
        n = a.length;
        for (int i = 0; i < n; i++) {
            if(a[i].getMave().equals(id)) {
                isExisted = true;
                a[i].nhap();
                while(isIdExist(a[i].getMave())) {
                    System.err.println("Ma ve vua nhap bi trung voi ma ve khac!!!");
                    System.err.println("Nhan Enter de nhap lai!!!");
                    sc.nextLine();
                    System.out.println("Nhap lai ma ve:");
                    String ma = sc.nextLine();
                    a[i].setMave(ma);
                }
                ct.readDataFromFile();
                ct.changeIdVe(id, a[i].getMave());
                ct.writeDataToFile();
                writeDataToFile();
                break;
            }
        }
        if(!isExisted) 
            System.out.println("Khong tim thay ma ve!");
    }
    
    public void timkiem(){
        System.out.print("Nhap ma ve muon tim: ");
        String ma = sc.nextLine();
        boolean isExisted = false;
        n = a.length;
        System.out.println("==================================Danh sach ve=================================");
       
		System.out.format("|| %5s | %10s | %10s | %10s | %15s | %8s ||\n", "Stt", "Ma tour", "Ma ve", "Loai ve", "Gia ve", "So luong");
            for (int i = 0; i < n; i++) {
                if(a[i].getMave().equals(ma)) {
                    System.out.format("|| %5d |", i + 1);
                    isExisted = true;
                System.out.println("Thong tin ve can tim: ");
                a[i].xuat();
                break;
            }
        }
        if(!isExisted) 
            System.out.println("Khong tim thay ma ve!");
        System.out.println("===============================================================================");
    }
    
    public int timkiemma(String ma){
        n = a.length;
        int vitri = -1;
        for (int i = 0; i < n; i++) {
            if(a[i].getMave().equals(ma)) {
                vitri = i;
                break;
            }
        }
        return vitri;
    }
    
    public Ve timkiem(String ma){
        n = a.length;
        for (int i = 0; i < n; i++) {
            if (a[i].getMave().equals(ma)) {
                return a[i];
            }
        }  
        return null;
    }
    
    public void timkiemtour(){
        System.out.print("Nhap ma tour muon tim: ");
        String ma = sc.nextLine();
        boolean isExisted = false;
        n = a.length;
        System.out.println("==================================Danh sach ve=================================");
       
		System.out.format("|| %5s | %10s | %10s | %10s | %15s | %8s ||\n", "Stt", "Ma tour", "Ma ve", "Loai ve", "Gia ve", "So luong");
        for(int i = 0; i < n; i++) {
            if (a[i].getMatour().equals(ma)) {
                isExisted = true;
                System.out.format("|| %5d |", i + 1);
                a[i].xuat();
            }
        }
        if(!isExisted) 
            System.out.println("Khong tim thay ma tour! ");
    }

    public void timkiemtour(String ma){
        boolean isExisted = false;
        n = a.length;
        for(int i = 0; i < n; i++) {
            if (a[i].getMatour().equals(ma)) {
                isExisted = true;
                a[i].xuat();
            }
        }
        if(!isExisted) 
            System.out.println("Khong tim thay ma tour! ");
    }

    public int timkiemmatour(String ma) {
        int count = 0;
        for(int i = 0; i < n; i++) {
            if(a[i].getMatour().equals(ma)) {
                count++;
            }
        }
        return count;
    }

    public void xoa(){
        System.out.print("Nhap ma ve muon xoa: ");
        String id = sc.nextLine();
        n = a.length;
        int vitri = timkiemma(id);
        if(vitri == -1) 
            System.out.println("Khong tim thay ma ve!");
        else {
            a = Arrays.copyOf(a, n + 2);
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
            System.out.println("Khong tim thay ma ve!");
        else {
            a = Arrays.copyOf(a, n + 2);
            for(int i = vitri; i < n; i++) {
                a[i] = a[i + 1];
            }
            a = Arrays.copyOf(a, n - 1);
            n--;
        }
    }

    public void thongketheogia() {
        System.out.println("Nhap khung gia muon tim: ");
        int gia1 = sc.nextInt();
        int gia2 = sc.nextInt();
        sc.nextLine();
        n = a.length;
        System.out.println("==================================Danh sach ve=================================");
		System.out.format("|| %5s | %10s | %10s | %10s | %15s | %8s ||\n", "Stt", "Ma tour", "Ma ve", "Loai ve", "Gia ve", "So luong");
        for(int i = 0; i < n; i++) {
            if(Integer.parseInt(a[i].getGiave()) > gia1 && Integer.parseInt(a[i].getGiave()) < gia2) {
                System.out.format("|| %5d |", i + 1);
                a[i].xuat();
            }
        }
        System.out.println("===============================================================================");
    }

    public boolean didIdExist(String id) {
        for(int i = 0; i < n; i++) {
            if(a[i].getMave().equals(id)) {
                return false;
            }
        }
        return true;
    }

    public void writeDataToFile() throws IOException {
        n = a.length;
        DataOutputStream out = new DataOutputStream(new FileOutputStream("data/dataVe.txt"));
        for(int i = 0; i < n; i++) {
            out.writeUTF(a[i].getMatour());
            out.writeUTF(a[i].getMave());
            out.writeUTF(a[i].getLoaive());
            out.writeUTF(a[i].getGiave());
            out.writeInt(a[i].getSoluong());
        }
        out.close();
    }

    public void readDataFromFile() {
        a = new Ve[500];
        int i = 0;
        try {
            DataInputStream in = new DataInputStream(new FileInputStream("data/dataVe.txt"));
            try {
                while(true) {
                    a[i] = new Ve();
                    a[i].setMatour(in.readUTF());
                    a[i].setMave(in.readUTF());
                    a[i].setLoaive(in.readUTF());
                    a[i].setGiave(in.readUTF());
                    a[i].setSoluong(in.readInt());
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

        System.out.println("===========-Option-============");
        System.out.println("||        1. Them ve.        ||");
        System.out.println("||        2. Xoa ve.         ||");
        System.out.println("||    3. Sua thong tin ve.   ||");
        System.out.println("||     4. Tim kiem ve.       ||");
        System.out.println("||  5. Thong ke khung gia ve ||");
        System.out.println("||    6. Xem danh sach ve.   ||");
        System.out.println("||         0. Thoat.         ||");
        System.out.println("===============================");
        System.out.print("Choose: ");
    }

    public void showMenutimkiem(){
        System.out.println("===========-Option-============");
        System.out.println("||  1. Tim kiem theo ma ve.  ||");
        System.out.println("|| 2. Tim kiem theo ma tour. ||");
        System.out.println("||         0. Thoat.         ||");
        System.out.println("===============================");
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
                timkiemtour();
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
                thongketheogia();
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
