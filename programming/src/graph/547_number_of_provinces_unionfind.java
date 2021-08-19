//https://leetcode.com/problems/number-of-provinces/
class Solution {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        
        UnionFind uf = new UnionFind();
        uf.count = n;
        for(int i=0;i<n;i++) {
            uf.makeSet(i);
        }
        
        // union for the edges where it connects
        for(int i=0;i<isConnected.length;i++) {
            for(int j=0;j<isConnected[0].length;j++) {
                if(i==j) {
                    continue;
                } else {
                    if(isConnected[i][j] == 1) {
                        uf.union(i,j);
                    }
                }
            }
        }
        
        return uf.count;
             
    }
    
    
}

class Node {
    int data;
    Node parent;
    int rank;
}

class UnionFind {
    Map<Integer, Node> nodeMap = new HashMap();
    int count;
    
    public void makeSet(int data) {
        Node node = new Node();
        node.data = data;
        node.parent = node;
        node.rank = 0;
        nodeMap.put(data, node);
    }
    
    public Node findSet(Node node) {
        //with path compression
        Node parent = node.parent;
        if(parent == node) {
            return parent;
        }
        
        node.parent = findSet(node.parent);
        return node.parent;
    }
    
    public boolean union(int d1, int d2) {
        Node nd1 = nodeMap.get(d1);
        Node nd2 = nodeMap.get(d2);
        
        Node p1 = findSet(nd1);
        Node p2 = findSet(nd2);
        if(p1 == p2) {
            //no need to do anything
            return false;
        }
        
        if(p1.rank >= p2.rank) {
            p1.rank = (p1.rank == p2.rank) ? p1.rank + 1 : p1.rank;
            p2.parent = p1;
        } else {
            p1.parent = p2;
        }
        count --;
        return true;
    }
}
