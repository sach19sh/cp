class Solution {
    public boolean validTree(int n, int[][] edges) {
        UnionFind uf = new UnionFind();
        uf.count = n;
        for(int i=0;i<n;i++) {
            uf.makeSet(i);
        }
        boolean res = true;
        for(int[] edge : edges) {
            if(!uf.union(edge[0], edge[1])) {
                return false;
            } 
        }
        if(uf.count != 1) {
            return false;
        }
        return true;
    }
}

    

class Node {
        int data;
        Node parent;
        int rank;
    }


    //makeSet
    //union
    //findSet
class UnionFind {
    private Map<Integer, Node> map = new HashMap<>();
    int count; // number of component  to track before union and after union for edge case on two completely disjoint sets can't be considered as tree
    
    /**
     * Create a set with only one element.
     */
    public void makeSet(int data) {
        Node node = new Node();
        node.data = data;
        node.parent = node;
        node.rank = 0;
        map.put(data, node);
    }
    
    
    /**
     * Finds the representative of this set
     */
    public int findSet(int data) {
        return findSet(map.get(data)).data;
    }

    /**
     * Find the representative recursively and does path
     * compression as well.
     */
    private Node findSet(Node node) {
        Node parent = node.parent;
        if (parent == node) {
            return parent;
        }
        node.parent = findSet(node.parent);
        return node.parent;
    }
    
    
    /**
     * Combines two sets together to one.
     * Does union by rank
     *
     * @return true if data1 and data2 are in different set before union else false.
     */
    public boolean union(int data1, int data2) {
        Node node1 = map.get(data1);
        Node node2 = map.get(data2);

        Node parent1 = findSet(node1);
        Node parent2 = findSet(node2);

        //if they are part of same set do nothing
        if (parent1.data == parent2.data) {
            return false;
        }

        //else whoever's rank is higher becomes parent of other
        if (parent1.rank >= parent2.rank) {
            //increment rank only if both sets have same rank
            parent1.rank = (parent1.rank == parent2.rank) ? parent1.rank + 1 : parent1.rank;
            parent2.parent = parent1;
        } else {
            parent1.parent = parent2;
        }
        count--;
        return true;
    }
   
}
