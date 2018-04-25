
import java.lang.*;
    import java.util.*;
    import java.io.*;


class UserIsADumbassException extends IllegalArgumentException{
        

        /*
    https://docs.oracle.com/javase/8/docs/api/java/lang/Double.html#parseDouble-java.lang.String-
    https://docs.oracle.com/javase/tutorial/essential/exceptions/
    https://stackoverflow.com/questions/1754315/how-to-create-custom-exceptions-in-java
    https://stackoverflow.com/questions/6772222/simple-command-line-calculator
    https://codereview.stackexchange.com/questions/128256/a-simple-command-line-calculator
    https://stackoverflow.com/questions/7138038/commandline-java-calculator
    https://www.codeproject.com/Articles/12395/A-Command-Line-Calculator
    
    */
        public UserIsADumbassException(String error){
            super(error);
        }
    }
    
    class LookAtMrAlgebraOverHereException extends IllegalArgumentException{
        

        public LookAtMrAlgebraOverHereException(String error){
            super(error);
            
        }
    }

    class IllegalOperationException extends IllegalArgumentException{
        
        public IllegalOperationException(String error){
            super(error);
        }
    }




    public class BasicCalculator{
    
        public static void main(String args[]) {
            System.out.println("Addition(+),Subtraction(-),Multiplication([) and Division(/) operators");
            ArrayList<String> List_args = new ArrayList<String>();

            for(int i = 0; i < args.length; i++){            
                List_args.add(i, args[i]);
                 System.out.print("" + List_args.get(i) + " ");
            }
            double value = 0;
            try{
                if(List_args.size() == 1){
                    try{    
                        
                        value = Double.parseDouble(List_args.get(0));
                        System.out.println("You result is  : " + value);
                    }
                    catch(NumberFormatException e){
                        System.out.print(" wrong Input number");
                        
                    }
                } else if(argumentcheck(List_args)){
                    
                    value = pressEnter(List_args);
                    System.out.println("Result: " + value);
                }
            }
            catch (IllegalOperationException e){

                System.out.print(" wrong Operator Input ");
                
            }
        } 

        public static boolean  argumentcheck(ArrayList<String> input) throws IllegalOperationException{
            
            for(int i = 0; i < input.size(); i+=2){
                
                if(i % 2 == 0){
                   
                    try{
                        double d = Double.parseDouble(input.get(i));
                        
                    } 
                    catch(NumberFormatException e){

                        System.out.print(" Check  Input number");
                        
                        return false;
                    } 
                } 
            } 
            for(int i = 1; i < input.size(); i+=2){
                
                if(i % 2 == 1 && input.get(i).equals("/") || input.get(i).equals("+") || input.get(i).equals("-") || input.get(i).equals("[") || input.get(i).equals("%")){
                } else {
                    throw new IllegalOperationException("  you  entered Illegal operation ");
                }
            }
            return true;
        }
        
        
        public static double pressEnter(ArrayList<String> input){
            
            double check = 0;
            for(int i = 0; i < input.size(); i++){
                if(multiplication_Divition(input, i)){
                   
                    check = operationcheck(input, i);
                    Arrayremove(input, i);
                    input.set(i-1, Double.toString(check));
                    i--;
                }     
            } 
            for(int i = 0; i < input.size(); i++){
                if(addition_Subtraction(input, i)){
                   
                    check = operationcheck(input, i);
                    Arrayremove(input,i);
                    input.set(i-1, Double.toString(check));
                    i--;
                }     
            }     
        return check;
        }
        public static double operationcheck(ArrayList<String> input, int index) throws LookAtMrAlgebraOverHereException{
            
            if(numberformet(input, index)){
                double left = Double.parseDouble(input.get(index - 1));
                double right = Double.parseDouble(input.get(index + 1));
                switch(input.get(index)){


                    case "+":
                        return left + right;

                    case "-":
                        return left - right;


                    case "[":
                        return left * right;

                    case "/":
                        try{
                            return divition(left, right);
                        } 
                        catch(ArithmeticException e){
                            System.out.println(" You can not Divided by zero");
                            
                        }

                    case "%":
                        return left % right;

                    

                    default: 
                        System.out.println("Something is wrong ");
                }
            } 
           
            System.out.println("Check you Input. Something is wrong");
            return -999;

        }

        public static void Arrayremove(ArrayList<String> input, int index){
            
            input.remove(index);
            input.remove(index-1);
        }

        
        public static boolean numberformet(ArrayList<String> input, int index){
            
            double l = 0; double r = 0;
            try{
                l = Double.parseDouble(input.get(index - 1));
                r = Double.parseDouble(input.get(index + 1));
            }
            catch (LookAtMrAlgebraOverHereException e){
                System.out.println("Please check your  number "); 
            }
            return true;
        }
        public static boolean addition_Subtraction(ArrayList<String> input, int index){
            
            if(input.get(index).equals("+") || input.get(index).equals("-")){
                return true;
            }
            return false;
        }
        public static boolean multiplication_Divition(ArrayList<String> input, int index){
            
            if(input.get(index).equals("[") || input.get(index).equals("/") || input.get(index).equals("%")){  
                return true;
            }
            return false;
        }
        
        public static double divition(double l, double r) throws ArithmeticException{
            if(r == 0){
                throw new ArithmeticException("Division can not divided by zero");
            } else {
                return l / r;
            }
        }

        
        public static boolean parenthesisCheck(ArrayList<String> input){
            
            int start_Pen = 0; int end_Pen = 0;
            for(int i = 0; i < input.size(); i++){
                if(input.get(i).equals("(")){
                    start_Pen++;
                }
                if(input.get(i).equals(")")){
                    end_Pen++;
                }
            }
            
            return (start_Pen== end_Pen);
        }


    } 

    
    