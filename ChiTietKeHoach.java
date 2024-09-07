import java.util.Scanner;




public class ChiTietKeHoach{
    private String makht;
    private String manhahang;
    private String makhachsan;
    private String makhuvuichoi;
    private String ngay;
    private long tongchiphi;
    
    static DSKS b = new DSKS();
    static DSVC c = new DSVC();
    static DSNH a = new DSNH();
    static DanhSachKeHoachTour dskht = new DanhSachKeHoachTour();  
    static DSCTKH d = new DSCTKH();
    static DanhSachHoaDonChi e = new DanhSachHoaDonChi();
    Scanner sc= new Scanner(System.in);

    public ChiTietKeHoach(){}

    public ChiTietKeHoach(String makht, String manhahang, String makhachsan, String makhuvuichoi, int tongchiphi,String ngay){
        this.makht=makht;
        this.manhahang=manhahang;
        this.makhachsan=makhachsan;
        this.makhuvuichoi=makhuvuichoi;
        this.tongchiphi=tongchiphi;
        this.ngay=ngay;
    }
    

    public ChiTietKeHoach(ChiTietKeHoach CTKH){
        this.manhahang=CTKH.manhahang;
        this.makhachsan=CTKH.makhachsan;
        this.makhuvuichoi=CTKH.makhuvuichoi;
        this.ngay=CTKH.ngay;
        this.tongchiphi=CTKH.tongchiphi;

    }

    public String getMaKht(){
        return makht;
    }

    public void setMakht(String makht){
        this.makht=makht;
    }
    public String getManhahang() {
        return manhahang;
    }

    public void setManhahang(String manhahang) {
        this.manhahang = manhahang;
    }

    public String getMakhachsan() {
        return makhachsan;
    }

    public void setMakhachsan(String makhachsan) {
        this.makhachsan = makhachsan;
    }

    public String getMakhuvuichoi() {
        return makhuvuichoi;
    }

    public void setMakhuvuichoi(String makhuvuichoi) {
        this.makhuvuichoi = makhuvuichoi;
    }

    public long getTongchiphi(){
        a.docFile();
        b.docFile();
        c.docFile();
        tongchiphi = a.timkiem(manhahang).getChiPhi() + b.timkiem(makhachsan).getChiPhi() + c.timkiem(makhuvuichoi).getChiphi();
        return tongchiphi;
    }

    public String getNgay(){
        return ngay;
    }

    public void setNgay(String ngay){
        this.ngay=ngay;
    }
    
    public void nhap() {
        a.docFile();
        c.docFile();
        b.docFile();
        System.out.println("Chon nha hang");
        manhahang=sc.nextLine();
        while (true){
            if(a.timkiemma(manhahang)!=-1){
                break;
            }
            System.err.println("Ma nha hang ban nhap chua chinh xac :");
            System.err.println(" Vui long nhan Enter để nhập lại ");
            sc.nextLine();
            a.xuat();
            System.out.println("Nhap lai ma nha hang:");
            String manh=sc.nextLine();
            setManhahang(manh);
        }
        System.out.println("Chon ma khach san");
        makhachsan=sc.nextLine();
        while(true){
            if(b.timkiemma(makhachsan)!=-1){
                break;
            }
            System.err.println("ma khach san ban chon chua dung");
            System.err.println("Vui long nhan Entern de nhap lai ma khach san");
            sc.nextLine();
            b.xuat();
            System.out.println("Nhap lai ma khach san");
            String maks=sc.nextLine();
            setMakhachsan(maks);
        }

        do{
            System.out.println("Chon ma khu vui choi ");
            makhuvuichoi=sc.nextLine();
            if(c.timkiemma(makhuvuichoi)==-1)
            {
                System.err.println("ma khach san ban chon chua dung");
                System.err.println("Vui long nhan Entern de nhap lai ma vui choi");
                sc.nextLine(); 
                c.xuat();
            }
            
        }while(c.timkiemma(makhuvuichoi)==-1);
            tongchiphi = a.timkiem(manhahang).getChiPhi() + b.timkiem(makhachsan).getChiPhi() + c.timkiem(makhuvuichoi).getChiphi();
        }

    
    
    public void xuat() {
         a.docFile();
         c.docFile();
         b.docFile();
        System.out.format("%11s | ", makht);
        System.out.format("%10s | ", manhahang);
        System.out.format("%10s | ", makhachsan);
        System.out.format("%12s | ", makhuvuichoi);
        System.out.format("%10s | ",tongchiphi = a.timkiem(manhahang).getChiPhi() + b.timkiem(makhachsan).getChiPhi() + c.timkiem(makhuvuichoi).getChiphi());
        System.out.format("%15s ||%n" , ngay);
    }
}