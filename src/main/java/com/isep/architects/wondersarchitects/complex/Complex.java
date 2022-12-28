package com.isep.architects.wondersarchitects.complex;

public class Complex {

    public double re;

    public double im;

    public Complex(double re, double im){
        this.re = re;
        this.im = im;
    }

    public static Complex complex(double re, double im){
        return new Complex(re,im);
    }

    public static Complex exp(int mod, double phase){
        return new Complex(mod*(Math.cos(phase)), mod*(Math.sin(phase)));

    }

    public static Complex add(Complex z1, Complex z2){
        return new Complex(z1.re+z2.re,z1.im+z2.im);
    }

    public static Complex conj(Complex z){
        return new Complex(z.re, -z.im);
    }

    public static void displayComplex(Complex z){
        System.out.println(z.re +" " +z.im+"i");
    }

}
