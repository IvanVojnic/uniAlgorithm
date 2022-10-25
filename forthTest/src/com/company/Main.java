package com.company;
import java.io.*;
import java.util.*;

class BST {
    private Node root;
    BST(){
        this.root = null;
    }
    BST(Node node){
        this.root = node;
    }
    private class Node {
        private int key;
        private Node left = null;
        private Node right = null;
        private int N = 0;
        private int height = 1;
        private boolean toDelete = false;

        public Node(int key, int N){
            this.key=key;
            this.N=N;
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
        return x;
    }

    private Node min(Node x){
        if(x.left == null){
            return x;
        }
        return min(x.left);
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
        return x;
    }
    public int charact(Node node, int counter){
        if(node == null){
            return 0;
        }
        int leftH;
        int rightH;
        int leftN;
        int rightN;
        if(node.left != null){
            leftH = node.left.height;
            leftN = node.left.N;
        } else {
            leftH = 0;
            leftN = 0;
        }
        if(node.right != null){
            rightH = node.right.height;
            rightN= node.right.N;
        } else {
            rightH = 0;
            rightN=0;
        }
        node.height = Math.max(leftH,rightH) + 1;
        node.N += rightN+leftN;
        if(rightH==leftH && rightN!=leftN){
            counter++;
            node.toDelete = true;
        }
        return counter;
    }


    public void setCharact()
    {
        int counterFirstChk = 0;
        counterFirstChk = setCharact(root, counterFirstChk);
        if((counterFirstChk % 2 != 0) && (counterFirstChk != 0)){
            toDel(root, counterFirstChk, 0);
        }
       /* if(!currentNode.isEmpty() && (currentNode.size() % 2 != 0)) {
           // Collections.sort(currentNode);
            delete(currentNode.get((counterFirstChk+1)/2));
        }*/
    }

    private void toDel(Node x, int counter1, int counter2){
        if(x == null) return;
        toDel(x.left, counter1, counter2);
        if(x.toDelete){
            counter2++;
            if(counter2 == (counter1/2+1)){
                delete(x.key);
                return;
            }
        }
        toDel(x.right, counter1, counter2);
    }

    private int setCharact(Node x, int counter){
        if(x == null) return 0;
        counter = setCharact(x.left, counter);
        counter = setCharact(x.right, counter);
        counter = charact(x, counter);
        return counter;
    }

    private Node task(Node node){
        setCharact();
        return root;
    }

    public void task() {
        File fout = new File("out.txt");
        if(root!=null){
            root = task(root);
        } else {
            try {
                FileWriter myWriter = new FileWriter(fout);
            } catch (IOException e){
                e.printStackTrace();
            }
            return;
        }
        try {
            FileWriter myWriter = new FileWriter(fout);
            print(root, myWriter);
            myWriter.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    private void print(Node x, FileWriter myWriter) throws IOException {
        if(x==null) return;

        myWriter.write(String.valueOf(x.key));
        myWriter.write("\n");

        print(x.left, myWriter);
        print(x.right, myWriter);
    }
}
public class Main implements Runnable{

    public static void main(String[] args)  {
        new Thread(null, new Main(), "", 64 * 1024 * 1024).start();
    }
    String tmp;
    @Override
    public void run() {
        BST myBST = new BST();
        try {
            BufferedReader br = new BufferedReader(new FileReader("in.txt"));

            while((tmp = br.readLine())!= null) {
                if (!tmp.isEmpty()) {
                    myBST.put(Integer.parseInt(tmp));
                }
            }
            br.close();
            myBST.task();
        }
        catch (IOException e){

        }
    }
}
/*
del none

15
12
10
8
40
38
36
50
45
60
*/

/*
del 15

15
12
10
8
40
38
36
50
*/
/*
del 40

15
12
10
40
38
36
50
45
60
*/

/*
*
* 50
40
30
20
10
34
32
36
42
44
48
60
58
54
64
62
100
* */
/*
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
del 3
3
2
1
5
4
6
*/