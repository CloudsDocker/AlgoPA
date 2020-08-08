package algo;

import java.util.ArrayList;
import java.util.List;

public class Atoi {
    public static void main(String[] args) {
        System.out.println("===:"+new Atoi().myAtoi("   +0 123"));
    }

    public int myAtoi(String str) {
        if(str==null || str.length()<1) return 0;
        Boolean bSpace=false;
        Boolean bNumber=false;
        List<Character> list =new ArrayList();
        int sign=1;

        for(int i=0;i<str.length();i++){
            char c=str.charAt(i);
            if(Character.isSpace(c)){
                if(!bNumber && !bSpace){
                    // 1st occurance of space
                    if(bSpace && bNumber){
                        return 0; //[!!!]edge case "   +0 123"
                    }
                    bSpace=true;
                }else if (bSpace){
                    continue;
                }else if (bNumber){
                    // space AFTER numbers
                    break;
                }
            }else if(c=='-'){
                sign=-1;
                if(bNumber){
                    // [!!!!] edge case +-2
                    return 0;
                }
                bNumber=true;

            }else if(c=='+'){
                sign=1;
                if(bNumber){
                    // [!!!!] edge case +-2
                    return 0;
                }
                bNumber=true;
            }
            else if(Character.isDigit(c)){
                bNumber=true;
                list.add(c);
            }else{
                // other chars
                break;
            }
        }

        if(list.size()==0)
            return 0;
        // convert list to Integer
        //System.out.println("====:"+list);
        StringBuffer sb=new StringBuffer();
        if(sign==-1){
            //System.out.println("=1111=sign=:"+sign);
            sb.append("-");
        }
        list.stream().forEach(sb::append);
        //System.out.println("====:"+sb);

        Long value=Long.valueOf(sb.toString());
        //System.out.println("===sign=:"+sign);
        //System.out.println("====:"+value);
        Long maxInt=Long.valueOf(Integer.MAX_VALUE);
        Long minInt=Long.valueOf(Integer.MIN_VALUE);
        if(value>=maxInt){
            return Integer.MAX_VALUE;
        }else if (value<=minInt){
            return Integer.MIN_VALUE;
        }else{
            return Integer.valueOf(sb.toString());
        }


    }

}
