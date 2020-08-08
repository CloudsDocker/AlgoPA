class minWindow {
    public String minWindow(String s, String t) {
        // for 'substring' problem, use sliding window
        // key concepts for sliding window are
        // (1) hashmap
        // (2) two points of start and end
        // (3) one counter and one len
        // 'end' is used to make a valid window
        // 'start' is used to find a smallest window
        
        //Map<Character, Integer> map = new HashMap<>();
        // if possible replace Map with Array, which will increase performance
        char[] map=new char[128];
        int start=0, end=0,minLen=s.length()+1; // make minLen big enougth
        int minStart=0;
        int  count=0;
        for(char c: t.toCharArray()){
            map[c]++;
        }
        
        // S = "ADOBECODEBANC", T = "ABC"
        while(end<s.length()){
            final char c1=s.charAt(end);
            if(map[c1]>0){
                count++; // found char in source string, decrease counter
            }
            // change variables
            end++; // move end point to right
            map[c1]--; // reduce count of char, if not exist, it will be negative
            
            while(count==t.length()){
                // counter is zero means current window is still valid
                if(minLen>(end-start+1)){
                    minLen=(end-start+1);
                    minStart=start;
                }
                
                final char c2=s.charAt(start);
                map[c2]++;
                if(map[c2]>0){
                    count--; // found valid char again
                }
                start++;
            }
            
        }
        
        if(minLen>s.length()){
            return "";
        }else{
            return s.substring(minStart,minStart+minLen);
        }
    }
    /*
    ---previous vesion
    public String minWindow(String s, String t) {
        // for 'substring' problem, use sliding window
        // key concepts for sliding window are
        // (1) hashmap
        // (2) two points of start and end
        // (3) one counter and one len
        // 'end' is used to make a valid window
        // 'start' is used to find a smallest window
        
        //Map<Character, Integer> map = new HashMap<>();
        // if possible replace Map with Array, which will increase performance
        char[] map=new char[128];
        int start=0, end=0,minLen=Integer.MAX_VALUE;
        int minStart=0;
        int  counter=t.length();// init counter is length of substring
        for(char c: t.toCharArray()){
            map[c]++;
        }
        
        // S = "ADOBECODEBANC", T = "ABC"
        while(end<s.length()){
            final char c1=s.charAt(end);
            if(map[c1]>0){
                counter--; // found char in source string, decrease counter
            }
            // change variables
            end++; // move end point to right
            map[c1]--; // reduce count of char, if not exist, it will be negative
            
            while(counter==0){
                // counter is zero means current window is still valid
                if(minLen>(end-start)){
                    minLen=(end-start);
                    minStart=start;
                }
                
                final char c2=s.charAt(start);
                map[c2]++;
                if(map[c2]>0){
                    counter++; // found valid char again
                }
                start++;
            }
            
        }
        
        if(minLen==Integer.MAX_VALUE){
            return "";
        }else{
            return s.substring(minStart,minStart+minLen);
        }
    }
    */
}