package io.renren;

import java.util.Base64;

public class Test {
    public static void main(String[] args) {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJ1aWQiOiJzMTM1MjAzOTkzNTUiLCJzdWIiOiJzMTM1MjAzOTkzNTUiLCJpYXQiOjE1NjA0Mjg1OTAsImV4cCI6MTU2NDc0ODU5MCwiaXNzIjoiY29yZS13ZWIiLCJhdWQiOiJnaWZ0LW1pbmktcHJvZ3JhbSJ9.moXXpur-3e1vaWHbDsBFSRqdCKorQVdVJiFnQybD_ck";

        String td = token.split(".")[1];

        System.out.println(Base64.getDecoder().decode(td));

//        String str = "49.8--32.5";
//        int[] idx = appearNumber(str,"-");
//        String min,max;
//        if(idx[0] == 0){
//            // "-49.8--32.5";
//            //"-49.8-32.5";
//            min = str.substring(0,idx[1]);
//            max = str.substring(idx[1]+1,str.length());
//        }else {
//            //"49.8-32.5";
//            //"49.8--32.5";
//            min = str.substring(0,idx[0]);
//            max = str.substring(idx[0]+1,str.length());
//        }
//        System.out.println("min="+min+"--->max="+max);
//
//        System.out.println("age_"+3+""+1);

    }

    public static int[] appearNumber(String srcText, String findText) {
        int idx[] = new int[3];
        int count = 0;
        int index = 0;
        while ((index = srcText.indexOf(findText, index)) != -1) {
            System.out.println("index="+index);
            idx[count] = index;
            index = index + findText.length();
            count++;
        }
        System.out.println("----count="+count);
        return idx;
    }
}
