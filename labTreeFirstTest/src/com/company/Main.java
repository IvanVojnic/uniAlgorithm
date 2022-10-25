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
        private int N;
        private int height = 1;
        public Node(int key, int N){
            this.key=key;
            this.N=N;
        }
        public Node getLeft(){
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

    public int min(){
        return min(root).key;
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
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }
 /*   private Node receiveHeir(Node node) {
        Node parentNode = node;
        Node heirNode = node;
        Node currentNode = node.getRight(); // Переход к правому потомку
        while (currentNode != null) // Пока остаются левые потомки
        {
            parentNode = heirNode;// потомка задаём как текущий узел
            heirNode = currentNode;
            currentNode = currentNode.getLeft(); // переход к левому потомку
        }
        // Если преемник не является
        if (heirNode != node.getRight()) // правым потомком,
        { // создать связи между узлами
            parentNode.setLeft(heirNode.getRight());
            heirNode.setRight(node.getRight());
        }
        return heirNode;// возвращаем приемника
    }
    public boolean deleteNode(int value) // Удаление узла с заданным ключом
    {
        Node currentNode = root;
        Node parentNode = root;
        boolean isLeftChild = true;
        while (currentNode.key != value) { // начинаем поиск узла
            parentNode = currentNode;
            if (value < currentNode.key) { // Определяем, нужно ли движение влево?
                isLeftChild = true;
                currentNode = currentNode.getLeft();
            }
            else { // или движение вправо?
                isLeftChild = false;
                currentNode = currentNode.getRight();
            }
            if (currentNode == null)
                return false; // yзел не найден
        }

        if (currentNode.getLeft() == null && currentNode.getRight() == null) { // узел просто удаляется, если не имеет потомков
            if (currentNode == root) // если узел - корень, то дерево очищается
                root = null;
            else if (isLeftChild)
                parentNode.setLeft(null); // если нет - узел отсоединяется, от родителя
            else
                parentNode.setRight(null);
        }
        else if (currentNode.getRight() == null) { // узел заменяется левым поддеревом, если правого потомка нет
            if (currentNode == root)
                root = currentNode.getLeft();
            else if (isLeftChild)
                parentNode.setLeft(currentNode.getLeft());
            else
                parentNode.setRight(currentNode.getLeft());
        }
        else if (currentNode.getLeft() == null) { // узел заменяется правым поддеревом, если левого потомка нет
            if (currentNode == root)
                root = currentNode.getRight();
            else if (isLeftChild)
                parentNode.setLeft(currentNode.getRight());
            else
                parentNode.setRight(currentNode.getRight());
        }
        else { // если есть два потомка, узел заменяется преемником
            Node heir = receiveHeir(currentNode);// поиск преемника для удаляемого узла
            if (currentNode == root)
                root = heir;
            else if (isLeftChild)
                parentNode.setLeft(heir);
            else
                parentNode.setRight(heir);
        }
        return true; // элемент успешно удалён
    }*/
    public int height(Node node){
        if(node == null){
            return 0;
        }
        return 1 + Math.max(height(node.left), height(node.right));
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

  /* public void insertNode(int value) {
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
    private SortedSet getRootAfterDel(Node x, SortedSet<Integer> curNode){
        Node tmp = x;
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
        }
        if(x.left != null){
            getRootAfterDel(x.left, curNode);
        }
        if(x.right != null){
            getRootAfterDel(x.right, curNode);
        }

        return curNode;
    }
    private List getRootAfterDel(Node x, List<Integer> curNode){
        Node tmp = x;
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
        }
        if(x.left != null){
            getRootAfterDel(x.left, curNode);
        }
        if(x.right != null){
            getRootAfterDel(x.right, curNode);
        }
        return curNode;
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
    private Node task(Node node){
        // ArrayList<Node> currentNodes= new ArrayList<Node>();
        //SortedSet<Integer> curNode = new TreeSet<>();
        List<Integer> curNode = new ArrayList<>();
        long time1 = System.nanoTime();
        setHeight();
        System.out.println(System.nanoTime() - time1);
        long time2 = System.nanoTime();
        getRootAfterDel(root, curNode);
        int myKey = searchK(curNode, curNode.size()-1, curNode.size()/2   );
        System.out.println(System.nanoTime() - time2);
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
          //  long time3 = System.nanoTime();
            //deleteNode(currentKey);
            delete(myKey);
          //  System.out.println(System.nanoTime() - time3);
        }

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
            long time = System.nanoTime();
            BufferedReader br = new BufferedReader(new FileReader("in.txt"));
            while((tmp = br.readLine())!= null) {
                if (!tmp.isEmpty()) {
                    myBST.put(Integer.parseInt(tmp));
                    //myBST.insertNode(Integer.parseInt(tmp));
                }
            }
            br.close();
            System.out.println(System.nanoTime() - time);
            myBST.task();
            System.out.println(System.nanoTime() - time);
        }
        catch (IOException e){

        }
    }
}
/*15
12
10
8
40
38
36
50
45
100*/

/*15
12
10
8
40
38
36
50
45
100*/