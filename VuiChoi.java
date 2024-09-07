import java.util.Scanner;

public class VuiChoi {
    private String makhuvuichoi;
    private int chiphi;
    private String ten;

    Scanner scanner =new Scanner (System.in);

    public VuiChoi(){

    }
    public VuiChoi(String makhuvuichoi,String ten, int chiphi) {
        this.makhuvuichoi=makhuvuichoi;
        this.ten=ten;
        this.chiphi = chiphi;
    }
    public VuiChoi(VuiChoi vc){
        this.makhuvuichoi=vc.makhuvuichoi;
        this.ten=vc.ten;
        this.chiphi=vc.chiphi;
    }


    public String getMakhuvuichoi() {
        return makhuvuichoi;
    }

    public void setMakhuvuichoi(String makhuvuichoi) {
        this.makhuvuichoi = makhuvuichoi;
    }

    public int getChiphi() {
        return chiphi;
    }

    public void setChiphi(int chiphi) {
        this.chiphi = chiphi;
    }

    public String getTen(){
        return ten;
    }

    public void setTen(String ten){
        this.ten=ten;
    }

    public void nhap (){
        System.out.print("Nhap ma khu vui choi: ");
        makhuvuichoi= scanner.nextLine();
        while(true){
            if(makhuvuichoi.length() != 0){
                break;
            }
            System.err.println("Du lieu ban nhap khong duoc de trong");
            System.err.println("Vui long nhap lai ma");
            scanner.nextLine();
            System.out.print("Nhap lai ma");
            String ma=scanner.nextLine();
            setMakhuvuichoi(ma);
        }
    
        
 
        System.out.print("Nhap ten: ");
        ten= scanner.nextLine();
        while( true ) {
            if(ten.length()!=0){
                break;
            }
            System.err.println(" Ten khong duod de trong ");
            System.err.println(" vui long nhan Enter de nhap lai");
            scanner.nextLine();
            System.out.print(" Nhap lai ");
            String name=scanner.nextLine();
            setTen(name);
        }


        System.out.print("Nhap chi phi: ");
        chiphi = scanner.nextInt();
        scanner.nextLine();
    }


    public void xuat() {
        System.out.format(" %15s | %12s | %10d ||\n", makhuvuichoi, ten, chiphi);

    }
}