package lesson5;

public class Main_x4 {
    static final int size = 10000000;
    float[] arr = new float[size];
    final int h = size / 4;

    void fillArray () {
        for (int i = 0; i < size; i++)
            arr[i] = 1.0f;
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
            System.out.println(this.getName() + "обработал от " + position + " до " + (position + a.length - 1));
        }
    }
// ==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==
    void method2() {
        float[] a1 = new float[h];
        float[] a2 = new float[h];
        float[] a3 = new float[h];
        float[] a4 = new float[h];

        fillArray();
        long a = System.currentTimeMillis();
        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, 1*h, a2, 0, h);
        System.arraycopy(arr, 2*h, a3, 0, h);
        System.arraycopy(arr, 3*h, a4, 0, h);
        MyThread t1 = new MyThread(a1, 0);
        MyThread t2 = new MyThread(a2, 1*h);
        MyThread t3 = new MyThread(a3, 2*h);
        MyThread t4 = new MyThread(a4, 3*h);
        t1.start();
        t2.start();
        t3.start();
        t4.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println((float)(System.currentTimeMillis() - a)/1000 + " seconds");
    }
// ==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==
    public static void main(String[] args) {
        Main_x4 main = new Main_x4();
        main.method2();
    }
}
