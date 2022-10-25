import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws IOException {


        Set<Integer> set = new HashSet<Integer>();
        int size = 0;
        int[] left;
        int[] right;
        int[] from;
        int[] key;
        int[] height;
        int[] sunsNumber;

        BufferedReader br = new BufferedReader(new FileReader("in.txt"));
        String line;
        while ((line = br.readLine()) != null) {
            if (set.contains(Long.parseLong(line)))
                continue;
            ++size;
            set.add(Integer.parseInt(line));
        }
        br = new BufferedReader(new FileReader("in.txt"));
        set = new HashSet<Integer>();

        left = new int[size];
        key = new int[size];
        from = new int[size];
        right = new int[size];
        height = new int[size];
        sunsNumber = new int[size];
        int current;
        int iterator = 0;

        while ((line = br.readLine()) != null) {
            current = Integer.parseInt(line);
            if (set.contains(current))
                continue;
            key[iterator] = current;
            height[iterator] = 1;
            sunsNumber[iterator] = 1;
            set.add(Integer.parseInt(line));

            int i = 0;
            while (true) {
                if (current > key[i]) {
                    if (right[i] == 0) {
                        right[i] = iterator;
                        from[iterator++] = i;
                        break;
                    } else {
                        i = right[i];
                    }
                } else {
                    if (left[i] == 0) {
                        left[i] = iterator;
                        from[iterator++] = i;
                        break;
                    } else {
                        i = left[i];
                    }
                }
            }
        }

        leftBypass(right, left, key, from, size, height, sunsNumber);

        ArrayList<Integer> list = new ArrayList<Integer>();

        for(int i = 0; i < size; i++)
            if(right[i] != 0 && left[i] != 0)
                if(sunsNumber[left[i]] != sunsNumber[right[i]] && height[left[i]] == height[right[i]])
                    list.add(key[i]);

        Collections.sort(list);

        if(list.size() % 2 !=0){
            rightRemoving(right, left, key, from, list.get(list.size() / 2));
            --size;
        }

        long[] result = leftBypassOut(right, left, key, from, size);

        FileWriter myWriter = new FileWriter("out.txt");
        for (long i : result) {
            myWriter.write("" + i);
            myWriter.write("\n");
        }
        myWriter.close();
    }

    public static void leftBypass(int right[], int left[], int key[], int from[], int size, int[] height, int[] sunsNumber) {
        int current = 0;
        boolean FLAG_UP = false;
        int last = 0;

        for (int i = 0; i < size; i++) {
            if (FLAG_UP) {
                if(height[current] < 1 + height[last])
                    height[current] = 1 + height[last];
                sunsNumber[current] += sunsNumber[last];
                if (right[current] == 0 || left[current] == 0) {
                    last = current;
                    current = from[current];
                } else {
                    if (left[current] == last) {
                        FLAG_UP = false;
                        current = right[current];
                    } else {
                        last = current;
                        current = from[current];
                    }
                }
                --i;
            } else {
                //leftBypass[i] = key[current];
                if (left[current] != 0) {
                    current = left[current];
                } else if (right[current] != 0) {
                    current = right[current];
                } else {
                    FLAG_UP = true;
                    last = current;
                    current = from[current];
                }
            }
        }

        while(current != 0) {
            if (height[current] < 1 + height[last])
                height[current] = 1 + height[last];
            sunsNumber[current] += sunsNumber[last];
            if (right[current] == 0 || left[current] == 0) {
                last = current;
                current = from[current];
            } else {
                if (left[current] == last) {
                    FLAG_UP = false;
                    current = right[current];
                } else {
                    last = current;
                    current = from[current];
                }
            }
        }
    }

    public static long[] leftBypassOut(int right[], int left[], int key[], int from[], int size) {
        long leftBypass[] = new long[size];

        int current = 0;
        boolean FLAG_UP = false;
        int last = 0;

        for (int i = 0; i < size; i++) {
            if (FLAG_UP) {
                if (right[current] == 0 || left[current] == 0) {
                    last = current;
                    current = from[current];
                } else {
                    if (left[current] == last) {
                        FLAG_UP = false;
                        current = right[current];
                    } else {
                        last = current;
                        current = from[current];
                    }
                }
                --i;
            } else {
                leftBypass[i] = key[current];
                if (left[current] != 0) {
                    current = left[current];
                } else if (right[current] != 0) {
                    current = right[current];
                } else {
                    FLAG_UP = true;
                    last = current;
                    current = from[current];
                }
            }
        }

        return leftBypass;
    }

    public static boolean rightRemoving(int right[], int left[], int key[], int from[], int removingKey) {

        int removing = -1;
        for (int i = 0; i < key.length; i++) {
            if (key[i] == removingKey) {
                removing = i;
                break;
            }
        }

        if (removing == -1)
            return false;

        if (right[removing] == left[removing]) {
            if (left[from[removing]] == removing)
                left[from[removing]] = 0;
            else
                right[from[removing]] = 0;
            return true;
        }

        if (left[removing] == 0) {
            if (removing != 0) {
                if (left[from[removing]] == removing) {
                    left[from[removing]] = right[removing];
                } else {
                    right[from[removing]] = right[removing];
                }
                from[right[removing]] = from[removing];
            } else {
                if (left[right[0]] != 0) {
                    from[left[right[0]]] = 0;
                    left[0] = left[right[0]];
                }
                if (right[right[0]] != 0) {
                    from[right[right[0]]] = 0;
                    right[0] = right[right[0]];
                }
                key[0] = key[1];
            }

            return true;
        }

        if (right[removing] == 0) {
            if (removing != 0) {
                if (left[from[removing]] == removing) {
                    left[from[removing]] = left[removing];
                } else {
                    right[from[removing]] = left[removing];
                }
                from[left[removing]] = from[removing];
            } else {
                if (right[left[0]] != 0) {
                    from[right[left[0]]] = 0;
                    right[0] = right[left[0]];
                }
                if (left[left[0]] != 0) {
                    from[left[left[0]]] = 0;
                    left[0] = left[left[0]];
                }
                key[0] = key[1];
            }

            return true;
        }

        int current = right[removing];
        while (left[current] != 0)
            current = left[current];

        if (right[current] != 0) {
            if (left[from[current]] == current)
                left[from[current]] = right[current];
            else
                right[from[current]] = right[current];
            from[right[current]] = from[current];

            key[removing] = key[current];

        } else {
            if (left[from[current]] == current)
                left[from[current]] = 0;
            else
                right[from[current]] = 0;

            key[removing] = key[current];
        }
        return true;
    }

}
