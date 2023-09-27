package soham.calculator.utils;

public class TemperatureUtil {

    public static double C2K(double temp, boolean reverse){
        return reverse ? temp - 273.15 : temp + 273.15;
    }

    public static double C2F(double temp, boolean reverse){
        return reverse ? (temp - 32 ) * 0.556 : (temp * 1.8) + 32;
    }

    public static double K2F(double temp,boolean reverse){
        return reverse ?
                C2K(C2F(temp,true),false) :
                C2F(C2K(temp,true),false);
    }
}
