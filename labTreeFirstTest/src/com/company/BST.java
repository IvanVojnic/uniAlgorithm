package com.company;

import java.util.*;

/*public class BST {
    private Node root;
    BST(){

    }
    BST(Node node){
        this.root = node;
    }
    private class Node{
        private int key;
        private Node left,right;
        private int N;
        private int height = 1;
        public Node(int key, int N){
            this.key=key;
            this.N=N;
        }
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
    private Node put(Node x, int key){
        if(x == null){
            return new Node(key,1);
        }
        if(key < x.key){
            x.left = put(x.left, key);
        } else if(key > x.key){
            x.right = put(x.right, key);
        } else {
            x.key = key;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }
    private Node getNode(int key){
        return get(root, key);
    }
    private Node get(Node x, int key){
        if(x == null){
            return null;
        }
        if(key<x.key){
            return get(x.left, key);
        }
        else if(key > x.key){
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
    public int min(){
        return min(root).key;
    }
    private Node min(Node x){
        if(x.left == null){
            return x;
        }
        return min(x.left);
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
        if(key < x.key){
            x.left = delete(x.left, key);
        } else if(key > x.key){
            x.right = delete(x.right, key);
        } else {
            if(x.right == null){
                return x.left;
            }
            if(x.left == null){
                return x.right;
            }
            int min_key = min(x.right).key;
            x.key = min_key;
            x.right = delete(x.right, min_key);
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }
    public void setHeight()
    {
        root = setHeight(root);
    }
    private Node setHeight(Node x){
        x.height = height(x);
        if(x.left != null){
            setHeight(x.left);
        }
        if(x.right != null){
            setHeight(x.right);
        }
        return x;
    }
    public void task(){
        root = task(root);
        print(root);
    }
    private Node task(Node node){
        ArrayList<Node> currentNodes= new ArrayList<Node>();
        setHeight();
        Queue<Node> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            Node tmp = q.remove();
            if(tmp.left.height == tmp.right.height && tmp.left.N != tmp.right.N){
                currentNodes.add(tmp);
                break;
            }
            if (node.left != null) {
                q.add(tmp.left);
            }
            if (node.right != null) {
                q.add(tmp.right);
            }
        }
        currentNodes.sort(this::compare);
        delete(currentNodes.get(currentNodes.size()/2).key);
        return root;
    }
    private int compare(Node a, Node b){
        if(a.key > b.key){
            return 1;
        } else if(a.key < b.key){
            return -1;
        } else {
            return 0;
        }
    }

    public void print(Node x){
        if(x==null) return;
        print(x.left);
        System.out.println(x.key);
        print(x.right);
    }
}*/
