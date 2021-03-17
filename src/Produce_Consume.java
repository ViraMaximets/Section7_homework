
import java.util.Stack;

public class Produce_Consume {
    public static void main(String[] args) throws InterruptedException {
        Manage man = new Manage();
        Thread cook = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    man.makeFood();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread eat = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    man.eatFood();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        cook.start();
        eat.start();
        cook.join();
        eat.join();
    }
}

class Manage {
    static int sec = 150;
    Stack<Integer> store = new Stack<Integer>();

    public void makeFood() throws InterruptedException {
        while (true) {
            synchronized (this) {
                while (store.size() == 3) {
                    wait();
                }

                store.push(1);
                System.out.println("Cooking, here are " + store.size());
                notify();
                Thread.sleep(sec);
            }
        }
    }

    public void eatFood() throws InterruptedException {
        while (true) {
            synchronized (this) {
                while (store.isEmpty()) {
                    wait();
                }
                store.pop();
                System.out.println("Nyam-nyam, here are " + store.size());
                notify();
                Thread.sleep(sec);
            }
        }
    }
}
