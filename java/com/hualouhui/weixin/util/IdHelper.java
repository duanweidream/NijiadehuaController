package com.hualouhui.weixin.util;

public class IdHelper {

    public static int encode(int originalId) {
        LevelAdapter adapter = chooseAdapter4Encode(originalId);
        if (adapter != null) {
            return adapter.encode(originalId);
        }
        return originalId;
    }
    
    protected static LevelAdapter chooseAdapter4Encode(int originalId) {
        for (LevelAdapter adapter : adapters) {
            if (adapter.lessThanMaxOriginalId(originalId)) {
                return adapter;
            }
        }
        return null;
    }
        
    public static int decode(int encodedId) {
        String encodedIdStr = Integer.toString(encodedId);
        for (LevelAdapter adapter : adapters) {
            try{
                return adapter.decode(encodedIdStr);
            }catch(Exception e) {                
            }
        }
        return -1;
    }
        
    private static LevelAdapter[] adapters = new LevelAdapter[]{
        new LevelAdapter(      100, 1,         1, new int[]{ 2,  3,  5,  7, 11, 13, 17, 19, 23, 29, 31, 37, 39, 41, 43}),
//        new LevelAdapter(       20, 8,        23, new int[]{ 3,  5,  7, 11, 13, 17, 19, 23}),
//        new LevelAdapter(       50, 7,        63, new int[]{13, 17, 19, 23, 29, 31, 37, 39, 41, 43, 47, 51}),
//        new LevelAdapter(      100, 6,       123, new int[]{13, 17, 19, 23, 29, 31, 37, 39, 41, 43, 47}),
        new LevelAdapter(     1000, 2,        34, new int[]{ 7, 11, 13, 17, 19, 23, 29, 31, 37}),
        new LevelAdapter(    10000, 3,     21234, new int[]{ 3,  5,  7, 11, 13, 17, 19}),
        new LevelAdapter(   100000, 3,    821234, new int[]{ 2,  3,  5,  7, 11}),
        new LevelAdapter(  1000000, 2,   9221345, new int[]{ 1,  2,  3}),
        new LevelAdapter( 10000000, 1, 112321456, new int[]{ 1 })
    };
    
    private static class LevelAdapter
    {
        int maxOriginalIdExcluded, prefix, delta;
        int[] numbers;
        int sum;
        
        LevelAdapter(int maxOriginalIdExcluded, int prefix, int delta, int[] numbers) {
            this.maxOriginalIdExcluded = maxOriginalIdExcluded;
            this.prefix = prefix;
            this.delta = delta;
            this.numbers = numbers;
            sum = 0;
            for (int n : numbers) {
                sum += n;
            }
        }

        public boolean lessThanMaxOriginalId(int originalId) {
            return originalId < maxOriginalIdExcluded;
        }

        public int encode(int originalId) {
            int deltaId = originalId + delta;
            int adaptedId = (deltaId / numbers.length) * sum;
            for (int i = (deltaId % numbers.length); i >= 1; i --) {
                adaptedId += numbers[i];   
            }
            String targetIdStr = prefix + Integer.toString(adaptedId);
            return Integer.parseInt(targetIdStr);
        }

        public int decode(String encodedIdStr) throws Exception{
            int p = (int)(encodedIdStr.charAt(0) - '0');
            if (prefix != p) {
                throw new Exception();
            }
            int adaptedId = Integer.parseInt(encodedIdStr.substring(1));
            int deltaId = (adaptedId / sum) * numbers.length;
            int deltaRest = adaptedId % sum;
            boolean findRest = (deltaRest <= 0);
            for (int i = 1; i < numbers.length && !findRest; i ++) {
                deltaRest -= numbers[i];
                if (deltaRest == 0) {
                    findRest = true;
                    deltaId += i;
                }
                if (deltaRest < 0) {
                    break;
                }
            }
            if (!findRest) {
                throw new Exception();                
            }
            return deltaId - delta;
        }       
    }  
    
    public static void main(String[] args) throws Exception {
        int prevEncodedId = 0;
//        int[] bound = new int[]{10, 20, 50, 100, 1000, 10000, 100000, 1000000};
//        for (int j = 0; j < bound.length; j ++) {
//            for (int i = bound[j] - 9; i < bound[j] + 9; i ++) {
              for (int i = 0; i < 200; i ++) {
                int encodedId = encode(i);
                int decodedId = decode(encodedId);
                System.out.println(encodedId+"\t"+decodedId);  
                if (decodedId != i) {
                    System.out.println("not reversable: "+"\t"+encodedId+"\t"+decodedId);   
                }
                if (prevEncodedId >= encodedId) {
                    System.out.println("mapping broken: "+"\t"+encodedId+"\t"+decodedId+" <- "+prevEncodedId);
                    break;
                }
                prevEncodedId = encodedId;
            }   
//        }
    }
}
