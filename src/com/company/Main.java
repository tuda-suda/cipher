package com.company;

import java.util.*;
import java.io.*;

class Mode {
    public static String result(String mode, Algorithm algorithm){
        switch(mode){
            case "dec": return algorithm.decrypt();
            case "enc": return algorithm.encrypt();
            default: return algorithm.encrypt();
        }
    }
}

class AlgorithmPicker{

    public static Algorithm pickAlgorithm(String algorithmType, String message, int shift){
        switch (algorithmType){
            case "unicode": return new Unicode(message, shift);
            case "shift": return new Caesar(message, shift);
            default: return new Caesar(message, shift);
        }
    }
}

abstract class Algorithm{
    protected String message;
    protected int shift;

    public Algorithm(String message, int shift){
        this.message = message;
        this.shift = shift;
    }

    abstract String encrypt();
    abstract String decrypt();
}

class Caesar extends Algorithm{
    public Caesar(String message, int shift) {
        super(message, shift);
    }

    @Override
    public String encrypt() {
        char[] str = this.message.toCharArray();

        for(int i = 0; i < str.length; i++){
            //System.out.print(str[i]+"_");
            if (str[i] >= 'A' && str[i] <= 'Z'){
                str[i] = (char)(((str[i] - 'A' + shift) % 26) + 'A');
            }
            else if (str[i] >= 'a' && str[i] <= 'z'){
                str[i] = (char)(((str[i] - 'a' + shift) % 26) + 'a');
            }
        }

        return String.copyValueOf(str);
    }

    @Override
    public String decrypt() {
        char[] str = message.toCharArray();

        for(int i = 0; i < str.length; i++){
            //System.out.print(str[i]+"_");
            if (str[i] >= 'A' && str[i] <= 'Z'){
                str[i] = (char)(((str[i] - 'A' + (26 - shift)) % 26) + 'A');
            }
            else if (str[i] >= 'a' && str[i] <= 'z'){
                str[i] = (char)(((str[i] - 'a' + (26 - shift)) % 26) + 'a');
            }
        }

        return String.copyValueOf(str);
    }
}

class Unicode extends Algorithm{
    public Unicode(String message, int shift) {
        super(message, shift);
    }

    @Override
    public String encrypt() {
        char[] str = message.toCharArray();

        for(int i = 0; i < str.length; i++){
            //System.out.print(str[i]+"_");
            str[i] = (char)(str[i] + shift);
        }

        return String.copyValueOf(str);
    }

    @Override
    public String decrypt() {
        char[] str = message.toCharArray();

        for(int i = 0; i < str.length; i++){
            //System.out.print(str[i]+"_");
            str[i] = (char)(str[i] - shift);
        }

        return String.copyValueOf(str);
    }
}

public class Main {

    public static void writeOrPrint(String result, boolean write, String fileout){
        File file = new File(fileout);
        if(write){
            try(FileWriter writer = new FileWriter(file)){
                writer.write(result);
            } catch (IOException e) {
                System.out.println("Error - "+e.getClass().getName());
            }
        }
        else {
            System.out.print(result);
        }
    }

    public static void main(String[] args) {
        //String[] args = new String[]{"-mode", "dec", "-key", "5", "-data", "Welcome to hyperskill!", "-alg", "unicode"};
        String result;
        String message = "";
        String action = "enc";
        int shift = 0;
        boolean out = false;
        String fileout = "";
        String algorithm = "shift";

        for(int i = 0; i < args.length - 1; i+=2){

            if (args[i].equals("-mode")){
                action = args[i+1];
            }

            if (args[i].equals("-key")){
                shift = Integer.parseInt(args[i+1]);
            }

            if (args[i].equals("-data")){
                message = args[i+1];
            }
            else if (args[i].equals("-in")){
                File filein = new File(args[i+1]);
                try(Scanner scan = new Scanner(filein)){
                    message = scan.nextLine();
                } catch(FileNotFoundException e){
                    System.out.println("Error - "+e.getClass().getName());
                }
            }

            if (args[i].equals("-out")){
                fileout = args[i+1];
                out = true;
            }

            if (args[i].equals("-alg")){
                algorithm = args[i+1];
            }
        }

        if (action.equals("enc") && shift < 0) action = "dec";
        else if (action.equals("dec") && shift < 0) action = "enc";

        Algorithm data = AlgorithmPicker.pickAlgorithm(algorithm, message, shift);
        result = Mode.result(action, data);
        writeOrPrint(result, out, fileout);
    }
}

