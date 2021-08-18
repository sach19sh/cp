class Solution {
    public int countComponents(int n, int[][] edges) {
        UnionFind uf = new UnionFind();
        uf.count = n;
        
        for(int i=0;i<n;i++) {
            uf.makeSet(i);
        }
        
        for(int[] edge : edges) {
            uf.union(edge[0], edge[1]);
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
    int count;//to count number of connected component after union find operation
    
    /*
    makeset to add initial nodes and point to themself
    */
    public void makeSet(int data) {
        Node node = new Node();
        node.data = data;
        node.parent = node;
        node.rank = 0;
        nodeMap.put(data, node);
    }
    
    /*
    find representative
    */
    public int findSet(int data) {
        Node node = findSet(nodeMap.get(data));
        return node.data;
    }
    
    /*
    find representative recursively
    */
    public Node findSet(Node node) {
        Node parent = node.parent;
        if(node == parent) {
            return parent;
        }
        //path compression
        node.parent = findSet(node.parent);
        return node.parent;
    }
    
    /* 
     union operation
    */
    public boolean union(int d1, int d2) {
        Node node1 = nodeMap.get(d1);
        Node node2 = nodeMap.get(d2);
        
        Node parent1 = findSet(node1);
        Node parent2 = findSet(node2);
        
        if(parent1 == parent2) {
            //do nothing as already part of same set
            return false;
        }
        if(parent1.rank >= parent2.rank) {
            parent1.rank = (parent1.rank == parent2.rank) ? parent1.rank+1 : parent1.rank;
            parent2.parent = parent1;
        } else {
            parent1.parent = parent2;
        }
        //reduce count as merge happen
        count--;
        return true;
    }
}



