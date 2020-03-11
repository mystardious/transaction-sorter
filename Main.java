import com.sun.deploy.util.ArrayUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    static ArrayList<Transaction> transactions = new ArrayList<>();
    static ArrayList<ArrayList<String>> descriptors = new ArrayList<>();

    public static void main(String[] args) {

        String data = args[0];
        String transactionAccounts = args[1];

        try {
            readDataFromFile(data);
            readDescriptorsFromFile(transactionAccounts);
            Collections.reverse(transactions);
            fixDetails();
            writeToFile();
        } catch (IOException e) {

        }

//        for(Transaction e: transactions) {
//            System.out.println(e.toString());
//        }

    }

    public static void readDataFromFile(String fileName) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader(fileName));

        while(in.ready()) {

            String[] line = in.readLine().split(",");

            transactions.add(new Transaction(line[0], line[1], line[2]));

        }

        in.close();

    }

    public static void readDescriptorsFromFile(String fileName) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader(fileName));

        while(in.ready()) {

            descriptors.add(new ArrayList<String>(Arrays.asList(in.readLine().split(","))));

        }

        in.close();

    }

    public static void fixDetails() {

        for(Transaction item: transactions) {

            for(ArrayList<String> descriptor: descriptors) {

                if(item.vendor.contains(descriptor.get(0))) {
                    if(descriptor.get(1).length() > 0)
                        item.vendor = descriptor.get(1);
                    item.account = descriptor.get(2);
                }

            }

        }

    }

    public static void writeToFile() throws IOException {

        FileWriter out = new FileWriter("out.csv");
        for(Transaction e: transactions) {
            out.write(e.toString()+"\n");
        }
        out.close();

    }

}
