

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class KiemTraDinhDang implements NgayThangNam {
    private String dateFormat;

    public KiemTraDinhDang(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    @Override
    public boolean Check(String dateStr) {
        DateFormat sdf = new SimpleDateFormat(this.dateFormat);
        sdf.setLenient(false);
        try {
            sdf.parse(dateStr);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
}