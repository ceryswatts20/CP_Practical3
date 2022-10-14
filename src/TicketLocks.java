public class TicketLocks {

    public static void main(String[] args) {
        LockMutex lock_mux = new LockMutex();
        Process thread1 = new Process(lock_mux, 1);
        Process thread2 = new Process(lock_mux, 2);
        // Part 2: Try with more than 2 threads
        //Process thread3 = new Process(lock, 3);

        thread1.start();
        thread2.start();
        // Part 2: Try with more than 2 threads
        //thread3.start();
    }
}
