package Model;

import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;

import java.util.Scanner;

public class Model {
    private static ModularCounter red=new ModularCounter(0, 255);
    private static ModularCounter green=new ModularCounter(0,255);
    private static ModularCounter blue = new ModularCounter(0,255);

    public static void main(String[] args) {
        int option=0;
        Scanner sc=new Scanner(System.in);
        while (option<4){
            printMenu();
            option= sc.nextInt();
            switch (option){
                case 1: print();
                break;
                case 2:
                    System.out.println("Please enter 3 values (0-255)\nRed: ");
                    int i1=sc.nextInt();
                    System.out.println("Green: ");
                    int i2=sc.nextInt();
                    System.out.println("Blue: ");
                    int i3=sc.nextInt();
                    handle_setColor_exact(i1, i2, i3);
                break;
            }
        }
    }

    static void printMenu(){
        System.out.println("1: Print current color values\n2: Set fixed RGB color\n3: add or subtract the number 10 from any given Color\n4: Quit");
    }

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

    static void print(){
        System.out.println("RGB: " + red.getValue() + ", " + green.getValue() + ", " + blue.getValue() + "\nHexcode: " + getHexcode());
    }

    public void handle_setColor_exact(KeyEvent event, Button btn_color, Label l_hex){
        Object source=event.getSource();
        TextArea txta_showColor=(TextArea) source;

        int key = event.getCode().getCode();
        if (key>=48 && key<=57) {
            int decimalNum = Integer.parseInt(txta_showColor.getText());
            switch (txta_showColor.getId()){
                case "red":
                    if (red.getValue()+decimalNum <= red.getModulus()) {
                        red.reset();
                        red.inc(decimalNum);
                    }
                break;
                case "green":
                    if (green.getValue()+decimalNum <= green.getModulus()) {
                        green.reset();
                        green.inc(decimalNum);
                    }
                break;
                case "blue":
                    if (blue.getValue()+decimalNum <= blue.getModulus()) {
                        blue.reset();
                        blue.inc(decimalNum);
                    }
                break;
                default:
                    System.out.println("Error");
            }

            String hex= getHexcode(l_hex);
            setColor(btn_color, hex);
        }else{
            txta_showColor.clear();
        }
    }

    static void handle_setColor_exact(int i1, int i2, int i3){
        if (i1>=0) {
            if (red.getValue() + i1 <= red.getModulus()) {
                red.reset();
                red.inc(i1);
            }
        }
        if (i2>=0) {
            if (green.getValue() + i2 <= green.getModulus()) {
                green.reset();
                green.inc(i2);
            }
        }
        if (i3>=0) {
            if (blue.getValue() + 3 <= blue.getModulus()) {
                blue.reset();
                blue.inc(i3);
            }
        }
    }

    public void handle_interval_change(Event event, Button btn_color, Label l_hex){
        Button btn = (Button)event.getSource();

        if (btn.getText().contains("-")){
            if (btn.getId().contains("red"))
                red.dec(10);
            else if (btn.getId().contains("green"))
                green.dec(10);
            else
                blue.dec(10);
        }else{
            if (btn.getId().contains("red"))
                red.inc(10);
            else if (btn.getId().contains("green"))
                green.inc(10);
            else
                blue.inc(10);
        }

        String hex= getHexcode(l_hex);
        setColor(btn_color, hex);
    }

    static String getHexcode(Label l_hex){
        String hex= getHexcode();

        l_hex.setText(hex);
        return hex;
    }

    void setColor(Button btn_color, String hexcode){
        btn_color.setStyle("-fx-background-color: " + hexcode + ";");
    }
}
