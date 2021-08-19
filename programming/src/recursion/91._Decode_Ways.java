class Solution {
    public int numDecodings(String s) {
        /*
        recursion with 1 digit at a time
        recursion with 2 digit at a time
        validate and if not valid number return and not store in final result
        if end of string store in resul
        */
        if(s.length() == 0) {
            return 0;
        }
        Map<Integer, Integer> indexAndCountMemMap = new HashMap();
        return dfs(0, s, indexAndCountMemMap);
        
    }
    
    
    private int dfs(int index, String s, Map<Integer, Integer> indexAndCountMemMap) {
        
        
        if(index == s.length()) {
            return 1;
        }
        
        if(s.charAt(index) == '0') {
            return 0;
        }
        if(indexAndCountMemMap.containsKey(index)) {
            return indexAndCountMemMap.get(index);
        }
        int res = dfs(index+1, s, indexAndCountMemMap);
        
        //two digit use case
        if(index < s.length()-1 && (
            (s.charAt(index) == '1') || (s.charAt(index) == '2' && s.charAt(index+1) < '7'))) {
            res = res+dfs(index+2, s, indexAndCountMemMap);
        }
        indexAndCountMemMap.put(index, res);
        return res;
        
        
    }
}
