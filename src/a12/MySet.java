package src.a12;

class MySet {

    private int MaxCapacity;
    private int[] elements;
    private int activeSize = 0;

    public int getCardinality() {
        return activeSize;
    }

    public void add(int x) throws Exception {
        if (!contains(x)) {
            if (activeSize < MaxCapacity) {
                elements[activeSize++] = x;
            } else {
                throw new Exception("Sorry, but my memory is full. I cannot hold more than " + MaxCapacity + " elements.");
            }
        }
    }

    public boolean contains(int x) {
        boolean found = false;
        int i = 0;
        while (i < activeSize) {
            if (elements[i] == x) {
                found = true;
            }
            i++;
        }
        return found;
    }

    public String toString() {
        String ret = "{";
        for (int i = 0; i < activeSize; i++) {
            ret += " " + elements[i];
        }
        return ret + "}";
    }

    MySet(int size) {
        MaxCapacity = size;
        elements = new int[MaxCapacity];
    }
}

class MySetTestCode {

    public static void main(String[] lala) {
        MySet s1 = new MySet(5);
        try {
            s1.add(10);
            System.out.println(s1.contains(10));
            s1.add(20);
            s1.add(30);
            s1.add(40);
            s1.add(10);
            s1.add(10);
            System.out.println(s1);
            s1.add(11);
            s1.add(22);
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
