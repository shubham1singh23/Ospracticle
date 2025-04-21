package exp6;

class Buffer{
    int value;
    boolean isEmpty=true;

    synchronized void produce(int value){
        try{
            while(!isEmpty){
                wait();
            }
            this.value=value;
            System.out.println("Produced :"+value);
            isEmpty=false;
            notify();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    synchronized void consume(){
        try{
            while (isEmpty){
                wait();
            }
            System.out.println("Consumed:" + value);
            isEmpty=true;
            notify();
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
class Producer extends Thread{
    Buffer b;
    Producer(Buffer b){
        this.b=b;
    }

    public void run(){
        for (int i=0;i<5;i++){
            b.produce(i);
            try{
                sleep(500);
            }catch (Exception e){
                System.out.println(e);
            }
        }
    }

}

class Consumer extends Thread{
    Buffer b;
    Consumer(Buffer b){
        this.b=b;
    }

    public void run(){
        for(int i=0;i<5;i++){
            b.consume();
            try{
                sleep(500);
            }catch (Exception e){
                System.out.println(e);
            }
        }
    }
}

class ProducerConsumer{
    public static void main(String[] args) {
        Buffer b=new Buffer();
        Producer p=new Producer(b);
        Consumer c=new Consumer(b);
        p.start();
        c.start();

    }
}