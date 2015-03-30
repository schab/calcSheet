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

    public double nextInt(int max) {
        max=(max+1);
        last ^= (last << 21);
        last ^= (last >>> 35);
        last ^= (last << 4);
        double out = (int) last % max;
        return (out < 0) ? -out : out;
    }


}
