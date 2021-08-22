class Solution {
    public List<String> findItinerary(List<List<String>> tickets) {
        /*
        https://leetcode.com/problems/reconstruct-itinerary/
        - Map to process tickets and prepare adj list map sorted lexically
        - can optimized by using Multiset [Guava library] that store by defalt in sorted order
        - Once stored process and prepare intinerary using a stack
        - https://www.youtube.com/watch?v=WYqsg5dziaQ
        
        */
        
        Map<String, List<String>> ticketAdjMap = new HashMap();
        //prepare adjList
        for(List<String> ticket : tickets) {
            String key = ticket.get(0);
            if(ticketAdjMap.containsKey(key)) {
                List<String> ticketAdjlist = ticketAdjMap.get(key);
                ticketAdjlist.add(ticket.get(1));
                ticketAdjMap.put(key, ticketAdjlist);
            } else {
                List<String> ticketAdjlist = new ArrayList();
                ticketAdjlist.add(ticket.get(1));
                ticketAdjMap.put(key, ticketAdjlist);
            }
        }
        
        // sort adjlist after prep
        for(String key : ticketAdjMap.keySet()) {
            List<String> adjList = ticketAdjMap.get(key);
            Collections.sort(adjList);
            ticketAdjMap.put(key, adjList);
        }
        
        //start from JFK and process this map now
        Stack<String> pathStack = new Stack();
        pathStack.push("JFK");
        List<String> ans = new ArrayList();
        while(!pathStack.isEmpty()) {
            String source = pathStack.peek();
            List<String> adjList = ticketAdjMap.get(source);
            if(adjList != null && adjList.size() > 0) {
                String dest = adjList.get(0);
                pathStack.push(dest);
                adjList.remove(0);
                ticketAdjMap.put(source, adjList);   
            } else {
                ans.add(pathStack.pop());
            }
        }
        Collections.reverse(ans);
        return ans;
        
    }
}
