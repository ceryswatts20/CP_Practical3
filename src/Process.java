import java.util.Random;

public class Process extends Thread {
    private Random rnd = new Random();
    private LockMutex lock;
    private int id;

    Process(LockMutex mux_, int id_) {
        lock = mux_;
        id = id_;
    }

    public void run() {
        while (true) {
            nonCriticalSection();
            preProtocol();
            criticalSection();
            postProtocol();
        }
    }

    public void nonCriticalSection() {
        System.out.println(id + " nc: Entering nonCritical section");
        Time.delay(rnd.nextInt(20));

        System.out.println(id + " nc: Leaving nonCritical section");
    }

    public void preProtocol() {
        System.out.println(id + " prep: Entering preProtocol section");
        lock.pre_protocol();
        Time.delay(rnd.nextInt(20));
        System.out.println(id + " prep: Leaving preProtocol section");
    }

    public void criticalSection() {
        System.out.println(id + " cs: Entering critical section");
        System.out.println(id + " cs: In critical section");
        Time.delay(rnd.nextInt(20));
        System.out.println(id + " cs: Leaving critical section");
    }

    public void postProtocol() {
        Time.delay(rnd.nextInt(20));
        System.out.println(id + " postp: Entering postProtocol section");
        lock.post_protocol();
        System.out.println(id + " postp: Leaving postProtocol section");
    }
}
