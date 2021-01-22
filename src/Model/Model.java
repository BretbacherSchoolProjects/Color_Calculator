package Model;

import java.util.Scanner;

public class Model {
    //Values representing rgb
    private final static ModularCounter red=new ModularCounter(0, 256);
    private final static ModularCounter green=new ModularCounter(0,256);
    private final static ModularCounter blue = new ModularCounter(0,256);

    /**
     * Tests the model
     *
     */
    public static void main(String[] args) {
        //Variables
        int option;
        boolean run=true;
        Scanner sc=new Scanner(System.in);

        while (run){
            printMenu();
            option= sc.nextInt();
            switch (option){
                case 1:
                    System.out.println(getHexcode());
                break;
                case 2:
                    System.out.println("Please enter 3 values (0-255) if a value isn't to be changed, enter a negative number\nRed: ");
                    int i1=sc.nextInt();
                    if (i1>=0)
                        handle_setColor_exact(50, i1, "red");
                    System.out.println("Green: ");
                    int i2=sc.nextInt();
                    if (i2>=0)
                        handle_setColor_exact(50, i2, "green");
                    System.out.println("Blue: ");
                    int i3=sc.nextInt();
                    if (i3>=0)
                        handle_setColor_exact(50, i3, "blue");

                    System.out.println("New Hexvalue: " + getHexcode());
                break;
                case 3:
                    System.out.println("What value do you want to change (red, green, blue)?: ");
                    String id= sc.next();
                    System.out.println("Do you want to increment or decrement the value (+, -): ");
                    String op=sc.next();

                    handle_interval_change(op, id);
                    System.out.println("New Hexvalue: " + getHexcode());
                    break;
                default:
                    run=false;
            }
        }
    }

    static void printMenu(){
        System.out.println("1: Print current color values\n2: Set fixed RGB color\n3: add or subtract the number 10 from any given Color\n4: Quit");
    }

    /**
     *
     * @param keyValue used to check if the key pressed is from 0-9 (in ASCII 48 - 57)
     * @param value the value the color is to be changed by
     * @param colorID used to differentiate between the colors
     * @return the new hexcode to be displayed
     */
    public static String handle_setColor_exact(int keyValue, int value, String colorID){
        if (keyValue>=48 && keyValue<=57) {
            switch (colorID){
                case "red":
                    red.reset();
                    if (red.getValue()+value <= red.getModulus()) {
                        red.reset();
                        red.inc(value);
                    }
                break;
                case "green":
                    green.reset();
                    if (green.getValue()+value <= green.getModulus()) {
                        green.reset();
                        green.inc(value);
                    }
                break;
                case "blue":
                    blue.reset();
                    if (blue.getValue()+value <= blue.getModulus()) {
                        blue.reset();
                        blue.inc(value);
                    }
                break;
                default:
                    System.out.println("Error");
            }

            return getHexcode();
        }else{
            return "ERROR";
        }
    }

    /**
     *
     * @param op from type String, has to have - or + to get the right calculation
     * @param id used to differentiate between the colors
     * @return the new hexcode to be displayed
     */
    public static String handle_interval_change(String op, String id){

        if (op.contains("-")){
            if (id.contains("red"))
                red.dec(10);
            else if (id.contains("green"))
                green.dec(10);
            else
                blue.dec(10);
        }else{
            if (id.contains("red"))
                red.inc(10);
            else if (id.contains("green"))
                green.inc(10);
            else
                blue.inc(10);
        }

        return getHexcode();
    }

    /**
     *
     * @return the calculated hexcode
     */
    static String getHexcode(){
        String hex = "#";

        String redV=Integer.toHexString(red.getValue());
        String greenV=Integer.toHexString(green.getValue());
        String blueV=Integer.toHexString(blue.getValue());

        if (redV.length()==1)
            redV="0"+redV;
        if (greenV.length()==1)
            greenV="0"+greenV;
        if (blueV.length()==1)
            blueV="0"+blueV;
        hex+=redV;
        hex+=greenV;
        hex+=blueV;

        return hex;
    }
}
