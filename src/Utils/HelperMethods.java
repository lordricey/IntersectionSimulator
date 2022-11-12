package Utils;
import java.util.Random;

public class HelperMethods {

    public static double randomSampleFromDistribution(double mean, double stdDev) {

        //Box-Muller Transformation
        Random rand = new Random();
        double X = rand.nextDouble();
        double Y = rand.nextDouble();

        double Z = Math.sqrt( -2 * Math.log(X) ) * Math.cos( 2 * Math.PI * Y );
        double randVal = mean + Z*stdDev;

        return randVal;

    }

}
