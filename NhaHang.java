import java.util.Scanner;
public class NhaHang {
    private String manhahang;
    private int chiphi;
    private String ten;

    Scanner scanner =new Scanner (System.in);
    public NhaHang(){

    }
    public NhaHang(String manhahang, int chiphi,String ten) {
        this.manhahang = manhahang;
        this.chiphi = chiphi;
        this.ten=ten;
    }
    public NhaHang(NhaHang nh){
        manhahang=nh.manhahang;
        chiphi=nh.chiphi;
        ten=nh.ten;
    }

    public String getManhahang() {
        return manhahang;
    }

    public void setMaNhaHang(String manhahang) {
        this.manhahang = manhahang;
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
        System.out.print("Nhap ma nha hang: ");
        manhahang= scanner.nextLine();
        while(true) {
            
            if(manhahang.length()!=0){
                break;
            }
            System.err.println(" Ma ban nhap khong dung hoac khong ton tai ");
            System.err.println(" Vui long nhan Enter de nhap lai");
            scanner.nextLine();
            System.out.println("Nhap lai ma ");
            String ma=scanner.nextLine();
            setMaNhaHang(ma);
        }
    
        System.out.print("Nhap ten : ");
        ten= scanner.nextLine();
        while(true){
            if(ten.length()!=0){
                break;
            }
            System.err.println(" Ten khong duoc de trong");
            System.err.println("Vui long nhan Enter de nhap lai");
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
        System.out.format(" %15s | %12s | %10d ||\n", manhahang, ten, chiphi);
    }
}
