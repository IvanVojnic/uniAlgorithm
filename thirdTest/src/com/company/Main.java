package com.company;
import java.io.*;
import java.util.*;

class BST {
    private Node root;
    BST(){
        this.root = null;
    }
    private class Node {
        private int key;
        private Node left;
        private Node right;
        private int N;
        private int height;
        public Node(int key, int N){
            this.key=key;
            this.N=N;
            this.height = 1;
            this.right = null;
            this.left = null;
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
    /*public void height(Node node, List curNode){
        if(node == null){
            return;
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
         curNode.add(node.key);
        }
    }*/
    public void heightt(Node node, List curNode){
        if(node == null){
            return;
        }
        node.height = Math.max(node.left.height, node.right.height) + 1;
        node.N += node.right.N+node.left.N;
        if(node.left.height==node.right.height && node.right.N!=node.left.N){
            curNode.add(node.key);
        }
    }
    public int searchK(List<Integer> a, int n, int k) {
        for (int l=1, r=n; ; ) {
            if (r <= l+1) {
                if (r == l+1 && a.get(r) < a.get(l))
                    Collections.swap(a, l, r);
                return a.get(k);
            }

            int mid = (l + r) >> 1;
            Collections.swap(a, mid,l+1);
            if (a.get(l) > a.get(r))
                Collections.swap (a, l, r);
            if (a.get(l+1) > a.get(r))
                Collections.swap (a,l+1, r);
            if (a.get(l) > a.get(l+1))
                Collections.swap (a, l, l+1);

            int i = l+1, j = r;
            int cur = a.get(l+1);
            for (;;) {
                while (a.get(++i) < cur);
                while (a.get(--j) > cur);
                if (i > j)
                    break;
                Collections.swap(a, i, j);
            }

            a.set(l+1, a.get(j));
            a.set(j, cur);

            if (j >= k)
                r = j-1;
            if (j <= k)
                l = i;
        }
    }
    public void setHeight()
    {
        List<Integer> curNode = new ArrayList<>();
        setHeight(root, curNode);
        if(!curNode.isEmpty() && (curNode.size() % 2 != 0)) {
            //Collections.sort(curNode);
            int keyToDel = searchK(curNode,curNode.size()-1, curNode.size() / 2);
            delete(keyToDel);
        }
    }
    private void setHeight(Node x, List curNode){
        if(x.left != null){
            setHeight(x.left, curNode);
        }
        if(x.right != null){
            setHeight(x.right, curNode);
        }
        if(x.right!=null && x.left!=null){
            heightt(x,curNode);
        } else if(x.right == null && x.left!=null){
            x.height = x.left.height + 1;
            x.N = x.left.N+1;
        } else if(x.left == null && x.right!=null){
            x.height = x.right.height + 1;
            x.N = x.right.N+1;
        }
        return;
    }

    private Node task(Node node){
        setHeight();
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
    private void print(Node x, FileWriter myWriter){
        if(x==null) return;
        printToFile(x.key, myWriter);
        print(x.left, myWriter);
        print(x.right, myWriter);
    }
    private void printToFile(int key, FileWriter myWriter){
        try{
            myWriter.write(String.valueOf(key));
            myWriter.write("\n");
        } catch (IOException e) {
        }
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
del 3
3
2
1
5
4
6
*/

/*
* public void insertNode(int value) {
        ArrayList<Node> myNodes = new ArrayList<>();
        Node newNode = new Node(value,1);
        if (root == null) {
            root = newNode;
        }
        else {
            Node currentNode = root;
            Node parentNode;
            while (true)
            {
                myNodes.add(currentNode);
                parentNode = currentNode;
                if(value == currentNode.key) {
                    return;
                }
                else  if (value < currentNode.key) {
                    currentNode = currentNode.getLeft();

                    if (currentNode == null){
                        parentNode.setLeft(newNode);
                        for(int i = 0; i < myNodes.size();i++){
                            myNodes.get(i).N++;
                        }
                        return;
                    }
                }
                else {
                    currentNode = currentNode.getRight();
                    if (currentNode == null) {
                        parentNode.setRight(newNode);
                        for(int i = 0; i < myNodes.size();i++){
                            myNodes.get(i).N++;
                        }
                        return;
                    }
                }
            }
        }
    }*/

/*private Node task(Node node){
        // ArrayList<Node> currentNodes= new ArrayList<Node>();
        //SortedSet<Integer> curNode = new TreeSet<>();
        setHeight();
       // getRootAfterDel(root, curNode);
     /*   if(!curNode.isEmpty() && (curNode.size() % 2 != 0)) {
            Iterator<Integer> iterate = curNode.iterator();
            int i = 0;
            int currentKey = 0;
            while (iterate.hasNext()) {
                currentKey = iterate.next();
                if (i == curNode.size() / 2) {
                    break;
                }
                i++;
            }
            delete(currentKey);
        }*/
       /* Queue<Node> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            Node tmp = q.remove();
            int rightH = -1;
            int leftH = -1;
            int rightN = 0;
            int leftN = 0;
            if(tmp.right != null){
                rightH = tmp.right.height;
                rightN = tmp.right.N;
            }
            if(tmp.left != null){
                leftH = tmp.left.height;
                leftN = tmp.left.N;
            }
            if(leftH == rightH && leftN != rightN){
                curNode.add(tmp.key);
                //currentNodes.add(tmp);
            }
            if (tmp.left != null) {
                q.add(tmp.left);
            }
            if (tmp.right != null) {
                q.add(tmp.right);
            }
        }
        if(!curNode.isEmpty() && (curNode.size() % 2 != 0)) {
            Iterator<Integer> iterate = curNode.iterator();
            int i = 0;
            int currentKey = 0;
            while (iterate.hasNext()) {
                currentKey = iterate.next();
                if (i == curNode.size() / 2) {
                    break;
                }
                i++;
            }
            delete(currentKey);
        }
*/
      /*  return root;
                }*/

      /*
      * public Node getLeft(){
            return this.left;
        }
        public Node getRight(){
            return this.right;
        }
        public void setLeft(Node x){
            this.left = x;
        }
        public void setRight(Node x){
            this.right = x;
        }*/

      /*private List getRootAfterDel(Node x, List<Integer> curNode){
        int rightH = -1;
        int leftH = -1;
        int rightN = 0;
        int leftN = 0;
        if(x.right != null){
            rightH = x.right.height;
            rightN = x.right.N;
        }
        if(x.left != null){
            leftH = x.left.height;
            leftN = x.left.N;
        }
        if(leftH == rightH && leftN != rightN){
            curNode.add(x.key);
        }
        return curNode;
    }*/
//x.N = size(x.left)+size(x.right)+1;
//  getRootAfterDel(x, curNode);
/* public int min(){
        return min(root).key;
    }
    private Node min(Node x){
        Node node = x;
        while(true){
            if((node.left == null) && (node.right==null)){
                return x;
            }
            node = node.left;
        }
       if(x.left == null){
            return x;
        }
        return min(x.left);
    }*/