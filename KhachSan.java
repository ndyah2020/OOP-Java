import java.util.Scanner;

public class KhachSan {
    private String makhachsan;
    private int chiphi;
    private String ten;
    Scanner scanner =new Scanner (System.in);

    public KhachSan(){

    }
    public KhachSan(String makhachsan, int chiphi, String ten) {
        this.makhachsan=makhachsan;
        this.chiphi=chiphi;
        this.ten=ten;
    }
    public KhachSan(KhachSan ks){
        this.makhachsan=ks.makhachsan;
        this.chiphi=ks.chiphi;
        this.ten=ks.ten;
    }


    public String getMakhachsan() {
        return makhachsan;
    }

    public void setMakhachsan(String makhachsan) {
        this.makhachsan = makhachsan;
    }

    public int getChiPhi() {
        return chiphi;
    }

    public void setChiPhi(int chiphi) {
        this.chiphi = chiphi;
    }

    public String getTen(){
        return ten;
    }

    public void setTen(String ten){
        this.ten=ten;
    }

    public void nhap (){
        System.out.print("Nhap ma khach san: ");
        makhachsan= scanner.nextLine();
        while ( true ) {
            if(makhachsan.length()!=0){
                break;
            }
            System.err.println(" Ma ban nhap khong dung hoac khong ton tai ");
            System.err.println(" Vui long nhan Enter de nhap lai ");
            scanner.nextLine();
            System.out.println("Nhap lai ma ");
            String id = scanner.nextLine();
            setMakhachsan(id);
            
        }


        System.out.print("Nhap ten : ");
        ten= scanner.nextLine();
        while(true){
            if(ten.length()!=0){
                break;
            }
            System.err.println(" Ten khong duoc de trong ");
            System.err.println(" Vui long nhan Enter de nhap lai ");
            scanner.nextLine();
            System.out.println("Nhap lai");
            String name=scanner.nextLine();
            setTen(name);
        }

        System.out.print("Nhap chi phi: ");
        chiphi = scanner.nextInt();
        scanner.nextLine();
    }

    public void xuat() {
        System.out.format(" %12s | %15s | %10d ||\n", makhachsan, ten, chiphi);
}
}