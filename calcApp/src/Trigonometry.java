/**
 * Created by Hubert on 3/16/2015.
 */
public class Trigonometry {

    private double rads;

    public Trigonometry() {
    }

    public double Sinus(double a){

        rads = Math.toRadians(a);
        return Math.sin(rads);

    }

    public double Cosinus(double a){

        rads = Math.toRadians(a);
        return Math.cos(rads);
    }

    public double Tangens(double a){

        rads = Math.toRadians(a);
        return Math.tan(rads);
    }

    public double Cotangens(double a){

        rads = Math.toRadians(a);
        return  1.0 / Math.tan(rads);
    }
}
