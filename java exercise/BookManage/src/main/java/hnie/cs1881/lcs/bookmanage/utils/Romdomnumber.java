package hnie.cs1881.lcs.bookmanage.utils;

import java.util.Random;

public class Romdomnumber {
    public static String numberResult(){
        Random random = new Random();
        Integer r = random.nextInt();
        if (r<0 && r>-10000){
            r=-(r-10000);
            System.out.println(r);
        }
        if (r<=-10000){
            r=-r;
        }
        if (r>0 && r<10000){
            r=r+10000;
        }
        String yamzhengma = r.toString();
        yamzhengma  = yamzhengma.substring(0,5);
        return yamzhengma;
    }

    public static void main(String[] args) {
        System.out.println("生成5位验证码="+numberResult());
    }
}
