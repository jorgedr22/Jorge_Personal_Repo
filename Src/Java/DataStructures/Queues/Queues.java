import java.util.*;

public class Queues {
    public static void main(String args[]){
            // Queue => FIFO (Firs In First Out)
            Queue<String> queue = new LinkedList<String>(); // Queues are interfaces and not Classes
            
            // adding objects to queue
            queue.offer("Karen");
            queue.offer("Chad");
            queue.offer("Steve");
            queue.offer("Harold");
            
            // Shows if queue is empty
            System.out.println("Is the queue empty? "+ queue.isEmpty());
    
            // Shows the queue
            System.out.println(queue);
        
            // Shows the number of objects in the queue
            System.out.println(queue.size());
    
            // Show the TOP(infront) of queue
            System.out.println(queue.peek());
    
            //Removes the TOP of the queue
            queue.poll();
            System.out.println(queue);
    
            // Show the TOP(infront) of queue
            System.out.println(queue.peek());
    
            //Removes the TOP of the queue
            queue.poll();
            System.out.println(queue);
    
            // Priority Queues => FIFO
            Queue<Double> queue1 = new PriorityQueue<>();
    
            queue1.offer(3.0);
            queue1.offer(2.9);
            queue1.offer(4.0);
            queue1.offer(3.0);
            queue1.offer(2.9);
            queue1.offer(4.0);

            while (!queue1.isEmpty()) {
                System.out.println(queue1.poll());

            }

            //Reverse Order Priority
            Queue<Double> queue2 = new PriorityQueue<>(Collections.reverseOrder());
    
            queue2.offer(3.0);
            queue2.offer(2.9);
            queue2.offer(4.0);
            queue2.offer(3.0);
            queue2.offer(2.9);
            queue2.offer(4.0);

            while (!queue2.isEmpty()) {
                System.out.println(queue2.poll());

            }

    }       
}
