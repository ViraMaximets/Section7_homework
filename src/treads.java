public class treads extends Thread {
    public static void main(String[] args) {
        treads t1 = new treads();
        treads t2 = new treads();
        t1.start();
        t2.start();
        treads t3 = new treads();
        t3.start();
        t1.run();
        t2.run();
        t3.run();
    }
    public void run() {
        System.out.println("hi, " + currentThread().getName());
    }
}
