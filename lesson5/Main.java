package lesson5;

public class Main {
    static final int size = 10000000;
    static final int h = size / 2;
    float[] arr = new float[size];

    void fillArray () {
        for (int i = 0; i < size; i++)
            arr[i] = 1.0f;
    }
// ==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==
    void method1() {
        fillArray();
        long a = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println((float)(System.currentTimeMillis() - a)/1000 + " seconds");
    }
// ==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==
    class MyThread extends Thread {
        private float[] a;
        private int position;

        MyThread(float[] a, int position) {
            this.a = a;
            this.position = position;
        }

        public void run() {
            for (int i = 0; i < a.length; i++) {
                a[i] = (float)(a[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
            System.arraycopy(a, 0, arr, position, h);
        }
    }
// ==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==
    void method2() {
        float[] a1 = new float[h];
        float[] a2 = new float[h];

        fillArray();
        long a = System.currentTimeMillis();
        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);
        MyThread t1 = new MyThread(a1, 0);
        MyThread t2 = new MyThread(a2, h);
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println((float)(System.currentTimeMillis() - a)/1000 + " seconds");
    }
// ==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==
    public static void main(String[] args) {
        Main main = new Main();
        System.out.println("Method 1:");
        main.method1();
        System.out.println("Method 2:");
        main.method2();
    }
}
