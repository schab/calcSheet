package CalcSheet.Functions;

/**
 * Created by Kacper on 2015-03-30.
 */
public class RandomNumber {

    private long last;
    public double[][] hessenbergMatrix;

    public RandomNumber() {this(System.currentTimeMillis());}
    public RandomNumber(long seed) {
        this.last = seed;

    }

    public double nextDouble(int max) {
        max=(max+1);
        last ^= (last << 21);
        last ^= (last >>> 35);
        last ^= (last << 4);
        double out = (int) last % max;
        return (out < 0) ? -out : out;
    }


    public int nextInt(int max){
        max=(max+1);
        last ^= (last << 21);
        last ^= (last >>> 35);
        last ^= (last << 4);
        int out = (int) last % max;
        out = (out == 0)? out+=1 : out;
        return (out < 0 ) ? -out : out;
    }


    public String RandomDate(){
        int day = (int) nextInt(28);
        int month = (int) nextInt(12);
        int year = 1970 + (int) nextInt(24);

        return new String(year+"-"+month+"-"+day);
    }

}
