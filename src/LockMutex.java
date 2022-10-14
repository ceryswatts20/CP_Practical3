import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockMutex {
    //Global variables
    private AtomicInteger dispenser;
    private AtomicInteger current;
    // Local variables
    private AtomicInteger ticket;
    private AtomicInteger ignored;

    // Creates starvation-free ReentrantLock
    // Creates fair lock as every thread will eventually obtain it
    Lock lock = new ReentrantLock(true);

    LockMutex() {
        // Initialise
        dispenser = new AtomicInteger(0);
        current = new AtomicInteger(0);
        ticket = new AtomicInteger(0);
        ignored = new AtomicInteger(0);
    }

    public void pre_protocol() {
        fetch_and_add(dispenser,1,ticket);
        // Do nothing
        while (ticket.get() != current.get()) {}
        lock.lock();
    }

    public void post_protocol() {
        fetch_and_add(current,1,ignored);
        lock.unlock();
    }

    // In principle: creates a queue
    // Which ever threads executes this first will enter their critical section 1st
    // E.g thread 2: executes fetch_and_add
    //     thread 1: executes fetch_and_add
    //     thread 2: executes critical section
    //     thread 2: executes fetch_and_add
    //     thread 1: executes critical section
    //     thread 2: executes critical section

    private void fetch_and_add(AtomicInteger var, int value, AtomicInteger old) {
        old.set(var.get());
        var.getAndAdd(value);
    }
}
