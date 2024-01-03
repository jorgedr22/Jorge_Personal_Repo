package Src.Java.DataStructures.Stacks;
import java.util.*;

public class Stacks {
    public static void main(String[] args) {

        // Stack => LIFO (Last In First Out)
        Stack<String> stack = new Stack<String>();

        // check if the stack is empty
        System.out.println(stack.empty());

        // adding items to the stack 
        stack.push("Jorge");
        stack.push("Emiliano");
        stack.push("Sebastian");
        stack.push("Karen");
        stack.push("Angel");
        stack.push("Juan");

        //Shows the stack with the last item being at the TOP of the stack
        System.out.println(stack);

        // Removing the TOP items and storing the value
        String name = stack.pop();

        // New stack after removing the previous TOP value
        System.out.println(stack);
        System.out.println(name);

        // Display the TOP value but not the entire stack
        System.out.println(stack.peek());

        // Search for a certain value in stack 
        // Returns values of 1 up to the number of items in the stack OR -1 if value does not exist 
        System.out.println(stack.search("Sebastian"));
        System.out.println(stack.search("Adolfo"));


        }    
    }