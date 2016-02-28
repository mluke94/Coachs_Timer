package hacks.coachs_timer;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Sam on 2/27/2016.
 */
public class Split implements Parcelable {
    // This class hold a split taken by the user
    private Time total;
    private Time split;
    private short number;

    public Split(short n, long s, long t){
        number = n;
        split = new Time(s);
        total = new Time(t);
    }
    public Split(long[] in) {
        number = (short) in[0];
        split = new Time(in[1]);
        total = new Time(in[2]);
    }
    public Split(String str) {
        // This constructor is use to load from a save file
        String[] parts = str.split("\\s");
        number = Short.parseShort(parts[0]);
        String[] strSplit = parts[3].split("[^0-9]");
        String[] strTotal = parts[6].split("[^0-9]");
        split = new Time(Short.parseShort(strSplit[0]),Byte.parseByte(strSplit[1]),
                Byte.parseByte(strSplit[2]),Short.parseShort(strSplit[3]));
        total = new Time(Short.parseShort(strTotal[0]),Byte.parseByte(strTotal[1]),
                Byte.parseByte(strTotal[2]),Short.parseShort(strTotal[3]));
    }
    // Required for Parcelling
    public Split(Parcel in) {
        long[] data = new long[3];
        in.readLongArray(data);
        split = new Time(data[0]);
        total = new Time(data[1]);
        number = (short) data[2];
    }
    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLongArray(new long[] {split.getTime(),
                total.getTime(),
                (long) number});
    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Split createFromParcel(Parcel in) {
            return new Split(in);
        }
        public Split[] newArray(int size) {
            return new Split[size];
        }
    };
    // End required for Parcelling
    public String toString() {
        return String.valueOf(number) + "\t\t\t" + split + "\t\t\t" + total;
    }
    public Long[] getLongArray() {
        Long[] data = new Long[3];
        data[0] = (long) number;
        data[1] = split.getTime();
        data[2] = total.getTime();
        return data;
    }
    public Time getSplit() {
        return split;
    }
}
