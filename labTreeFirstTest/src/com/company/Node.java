package com.company;

import java.util.*;

public class Node {
    private Node root;
    private int key;
    private Node left;
    private Node right;
    private int N;
    private int height;
    Node(int key, int N){
        this.root = new Node(key, N);
        this.key = key;
        this.N = N;
        this.right = null;
        this.left = null;
    }



    Node(){

    }
    public int size(){
        return size(root);
    }
    private int size(Node x){
        if(x == null) {
            return 0;
        }
        else {
            return x.N;
        }

    }

    public void put(int key){
        root = put(root,key);
    }
    private Node put(Node x, int keyOther){
        if(x == null){
            return new Node(keyOther,1);
        }
        int cmp = keyOther - key;
        if(cmp < 0){
            x.left = put(x.left, keyOther);
        } else if(cmp > 0){
            x.right = put(x.right, keyOther);
        } else {
            x.key = keyOther;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }
    public Node getNode(int key){
        return get(root, key);
    }
    private Node get(Node x, int key){
        if(x == null){
            return null;
        }
        int cmp = this.key - key;
        if(cmp<0){
            return get(x.left, key);
        }
        else if(cmp > 0){
            return get(x.right, key);
        } else {
            return x;
        }
    }
    public int height(Node node){
        if(node == null){
            return 0;
        }
        return 1 + Math.max(height(node.left), height(node.right));
    }

    public boolean checkDiffCountNodes(){
        if(root.right.N != root.left.N){
            return true;
        }
        return false;
    }

    public int min(){
        return min(root).key;
    }
    private Node min(Node x){
        if(x.left == null){
            return x;
        }
        return min(x.left);
    }
    public int max(){
        return max(root).key;
    }
    public Node max(Node x){
        if(x.right == null){
            return x;
        }
        return max(x.right);
    }
    public Iterable<Integer> keys(){
        return keys(min(), max());
    }
    public Iterable<Integer> keys(int lo, int hi) {
        Queue<Integer> queue = new LinkedList<Integer>();
        keys(root,queue,lo,hi);
        return queue;
    }
    private void keys(Node x, Queue<Integer> queue, int lo, int hi){
        if(x == null){
            return;
        }
        int cmplo = this.key - lo;
        int cmphi = this.key - hi;
        if(cmplo < 0){
            keys(x.left, queue, lo, hi);
        }
        if(cmplo <= 0 && cmphi>=0){
            queue.add(x.key);
        }
        if(cmphi > 0){
            keys(x.right, queue, lo, hi);
        }
    }
    public void deleteMin(){
        root = deleteMin(root);
    }
    private Node deleteMin(Node x){
        if(x.left == null){
            return x.right;
        }
        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }
    public  void delete(int key){
        root = delete(root, key);
    }
    private Node delete(Node x, int key){
        if(x == null){
            return null;
        }
        int cmp = this.key - key;
        if(cmp < 0){
            x.left = delete(x.left, key);
        } else if(cmp > 0){
            x.right = delete(x.right, key);
        } else {
            if(x.right == null){
                return x.left;
            }
            if(x.left == null){
                return x.right;
            }
            Node t = x;
            x.min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }
    public void setHeight(){
        Iterator<Integer> it = root.keys().iterator();
        Map<Integer, ArrayList<Node>> allNodes = new HashMap<>();
        while (it.hasNext()){
            int tmpKey = it.next();
            Node currentNode = root.getNode(tmpKey);
            int tmpHeight = currentNode.height(currentNode);
            currentNode.height = tmpHeight;
            if(allNodes.containsKey(tmpHeight)){
                allNodes.get(tmpHeight).add(currentNode);
            } else {
                ArrayList<Node> tmpNode = new ArrayList<>();
                tmpNode.add(currentNode);
                allNodes.put(tmpHeight, tmpNode);
            }
        }
        for (HashMap.Entry<Integer, ArrayList<Node>> entry : allNodes.entrySet()) {
            ArrayList<Node> myNodes = entry.getValue();
            boolean check = false;
            for (int i = 0; i < myNodes.size(); i++) {
                if (!myNodes.get(i).checkDiffCountNodes()){
                    myNodes.remove(i);
                }
            }
            int keyVal = 0;
            if(!myNodes.isEmpty()){
                int i = 0;
                for (; i < myNodes.size(); i++){
                    keyVal += myNodes.get(i).key;
                }
                keyVal = keyVal/i;
                this.delete(keyVal);
            }
        }
    }
    public void print(){
        root.print(root);
    }
    private int print(Node x){
        int valToPrint = 0;
        if(x.left != null){
            valToPrint = left.print(left);
        }
        System.out.println(valToPrint);
        if(x.right != null){
            valToPrint = right.print(right);
        }
        System.out.println(valToPrint);
        return 0;
    }
}
