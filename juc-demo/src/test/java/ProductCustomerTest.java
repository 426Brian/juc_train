public class ProductCustomerTest {
    public static void main(String[] args) {
        Clerk clerk = new Clerk();
        Product product = new Product(clerk);
        Customer customer = new Customer(clerk);

        product.setName("生产者AA");
        customer.setName("消费者BB");

        product.start();
        customer.start();
    }
}

class Clerk {
    private int count;

    public synchronized void addCount() {
        while (count >= 20) {
            // 等待
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        count++;
        System.out.println(Thread.currentThread().getName() + "生产第 *** " + count + "个产品");
        notify();
    }

    public synchronized void reduceCount() {
        while (count <= 0) {
            // 等待
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(Thread.currentThread().getName() + "消费第 *** " + count + "个产品");
        count--;
        notifyAll();
    }

}

class Product extends Thread {
    private Clerk clerk;

    public Product(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(80);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            clerk.addCount();
        }

    }
}

class Customer extends Thread {
    private Clerk clerk;

    public Customer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(80);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            clerk.reduceCount();
        }
    }
}