/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.flooringmasterylab.Rev1;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 *
 * @author apprentice
 */
public class OrderController {

    boolean configMode;
    ConsoleIO io = new ConsoleIO();
    String date;

    //String state
    //
    String flooringType;

    MaterialsDAOFileImpl matObjRef = new MaterialsDAOFileImpl();
    TaxesDAOFileImpl taxObjRef = new TaxesDAOFileImpl();

    Map<String, Double> taxMap;
    OrderFactory calcGenerator = new OrderFactory();

    OrderBookDAOFileImpl testBook = new OrderBookDAOFileImpl(); //if it gets cranky will pull this

    ArrayList<Order> orders;  //orders should be a List, when you instantiate it you set it as ArrayList
    private String mode;
    private int globalId;

    //made constructor to pull in tax and materials data etc--didn't work
    public OrderController() {

    }

//--methods
    //public boolean checkConfig(){}
    public void run() throws IOException {

        date = bootUp();
        matObjRef.loadMatCosts();
        taxObjRef.loadTaxes();

        // printProdTestMenu();
//        int testProdMode = io.readInt("Choose option [1 or 2]", 1, 2);
        //String testProdMode = io.readString("Choose 't' for test, 'p' for production.");
//
//        switch (testProdMode) {
//            case "t":
//                io.print("Loading test version...");
////                printMenu();
//                break;
//            case "p":
//                io.print("Loading production version...");
////                printMenu();
//                break;
//            default:
//                io.print("Invalid selection");
//                break;
//
//        }
        boolean keepGoing = true;
        //int counter = 0;
        while (keepGoing) {
            printMenu();
//            if (counter > 0) {
//                printMenu();
//            }
            int menuSelection = io.readInt("Please choose from the options [1-6]: ", 1, 6);

            switch (menuSelection) {
                case 1:
                    io.print("Displaying orders...");
                    displayOrders();
                    break;
                case 2:
                    io.print("Adding order...");
                    addOrder();
                    break;
                case 3:
                    io.print("Editing order...");
                    editOrder();
                    break;
                case 4:
                    io.print("Removing order...");
                    removeOrder();
                    break;
                case 5:
                    io.print("Saving all current work...");
                    saveWork(date);
                    break;
                case 6:
                    System.out.println("Exiting...");
                    quit();
                    keepGoing = false;
//                    String quit = io.readYorN("Did you save your work for this session? If not, please select \"n\" "
//                            + "to go back into the main menu and save your work. If you've saved your work \n"
//                            + "select y to exit:");
//
//                    if (quit.equals("n")) {
//                        printMenu();
//                    } else if (quit.equals("y")) {
//                        keepGoing = false;
//                        io.print("Exiting...");
//                    }

                    break;

            }
//            counter++;

        }

    }

    private String bootUp() throws FileNotFoundException, IOException /*throws IOException*/ {

        testBook = new OrderBookDAOFileImpl(); //if it gets cranky will pull this

        //read config file
        testBook.readConfig();

        //hand these items to the DAO
        String fileName; //will create filename

        DateFormat df = new SimpleDateFormat("MMddyyyy");
        Date today = Calendar.getInstance().getTime(); //system date
        String systemDate = df.format(today);

        date = io.readString("Please enter the date as: MMDDYYYY.\nHit 'enter' to use system date " + systemDate + " ."); //if date is null use system date

        //moved workaround to unusedcode file
        //date = io.readString("Please enter the date as: MMDDYYYY");
        date = (date.isEmpty()) ? systemDate : date;
        //testBook.loadOrderFile(date); /// date = 

        try {

            testBook.loadOrderFile(date);
            //System.out.println("Orders for " + date + " loaded successfully.");

        } catch (FileNotFoundException e) {
            date = (date.isEmpty()) ? systemDate : date;
            //file not found--will write a new file
            fileName = "Orders_" + date + ".txt";
            newDateFile(date);

            System.out.println("No file found for this date. Any orders created in this session \n "
                    + "will generate a new order file named: " + fileName + ".");
        }

        //System.out.println("Orders for " + date + " loaded successfully.");
        return date;
    }

    private void newDateFile(String date) throws IOException {
        String fileName = "Orders_" + date + ".txt";
        PrintWriter out = new PrintWriter(new FileWriter(fileName));

        //out.print("42,Test42,OH42,42,Wood,42,42,42,42,42,42,42");
        out.flush();
        out.close();

    }

    public void addOrder() throws FileNotFoundException, IOException {
        //String date = io.readString("Please enter the date as: MMDDYYYY"); //try catch exception?

        //testBook.loadOrderFile(date); //can't run this without loading file from memory first -- breaking point
        //set next Id for object creation equal to +1 of the length of the list of orders
        //        Order lastOrder = orders.get(orders.size() - 1);
        //        int tempId = lastOrder.getOrderNum();
        //        int newId = tempId + 1;
        String lastName = io.readString("Enter customer's last name: ");
        String state = io.readString("Please enter the state in this format (XX)");
        int userFloorPref = io.readInt("Select your flooring type:\n1. Wood\n2. Carpet \n3. Laminate \n4. Tile \n", 1, 4);
        switch (userFloorPref) {
            case 1:
                flooringType = "Wood";
                break;
            case 2:
                flooringType = "Carpet";
                break;
            case 3:
                flooringType = "Laminate";
                break;
            case 4:
                flooringType = "Tile";
                break;
            default:
                break;
        }
        double length = io.readDouble("Enter the length of the room you want to renovate: ");
        double width = io.readDouble("Enter the width of the room as well: ");

        //call method that we write elsewhere to load data, check last item id, increment #, and clear hash -- session id data hashmap
        //int id = testBook.loadSessionIds(date); //the id passed from sessions hash from reading data file
        //int lastIndex = testBook.orders.lastIndexOf(orders);
        //int previousId = orders.get(lastIndex).getId(); //id pulled from last item recorded to hash
        //if (id<=previousId){
        //   id = previousId+1;
        //}
        //need to instantiate business object and call methods from our business logic 
        Order myOrder = calcGenerator.createFinalOrder(lastName, length, width, flooringType, state); //this is to assign the value of the returned object

        //myOrder.setId(id);
        //write to memory - hashmap
        testBook.putOrders(myOrder);
        //orderBook.put(orderNum, currentOrder); -- changed to ID
        //write to file

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~``
        //this is our business logic which belongs in DAO
    }

    public void displayOrders() throws FileNotFoundException {
        int dateInt = io.readInt("Please enter the date as: MMDDYYYY", 1012013, 12312015);

//        String month = String.format("%02d", io.readInt("Please enter the month [1-12]:", 1, 12));
//        String day = String.format("%02d", io.readInt("Please enter the day as [1-31]:", 1, 31));
//        String year = String.format("%04d", io.readInt("Please enter the year as [2000-2015]:", 2000, 2015));
//
//        String date = month + day + year;
        String date = String.format("%08d", dateInt);
        //orders = testBook.loadOrderFile(date);
        try {

            testBook.loadOrderFile(date);

            ArrayList<Order> orders = testBook.listOrders(date);
            for (Order o : orders) {

                //this needs to iterate
                System.out.println("====================");
                System.out.println("Order #: " + o.getId()//.getOrderNum()
                        + "\nName: " + o.getLastName()
                        + "\nFlooring Type: " + o.getFlooringType() + "\nSquare Footage: " + o.getArea()
                        + "\nTotal Cost: $" + o.getTotalCost());
                System.out.println("");

            }
            //testBook.orderMap.clear(); ////
        } catch (NullPointerException e) {
            System.out.println("No file found");
        }

        //orders = testBook.listOrders(date);
        //take values in HashMap and push to ArrayList
        //orders.
//        for (int i = 0; i < orders.size(); i++) {
//            io.print("====================");
//            System.out.println("Order #: " + orders.get(i).getOrderNum()
//                    + "\nName: " + orders.get(i).getLastName()
//                    + "\nFlooring Type: " + orders.get(i).getFlooringType() + "\nSquare Footage: " + orders.get(i).getArea());
//            io.print("");
//        }
    }

    public void editOrder() throws FileNotFoundException, IOException {

        String currentName;
        String newName;
        String currentArea;
        String newArea;
        String currentType;
        String newType;
        //enter an 8 digit date
//        String orderDate = io.readString("Please enter order date as MMDDYYYY: ");
//        
//        //load text file from memory
//        testBook.loadOrderFile(orderDate);       

        //display orders
        displayOrders();

        //enter order id of order you want to edit
        int orderId = io.readInt("Please enter the order ID that you wish to edit: ");

        //displays order
        Order currentOrder = testBook.getOrder(orderId);

        //step through order and edit order properties on thy fly or enter to skip
        io.print("Displaying order information.");

        currentName = currentOrder.getLastName();
        newName = io.readString("Current name is: " + currentName + "\nHit \"enter\" to keep or enter new data to replace.");
        newName = (newName.isEmpty()) ? currentName : newName;
        currentOrder.setLastName(newName);

        currentArea = Double.toString(currentOrder.getArea());
        newArea = io.readString("Current area is: " + currentArea + "\nHit \"enter\" to keep or enter new data to replace.");
        newArea = (newArea.isEmpty()) ? currentArea : newArea;  //Is isEmpty only a 'string' method, or can it be used with ints/doubles/floats?
        currentOrder.setArea(Double.parseDouble(newArea));

        currentType = currentOrder.getFlooringType();
        String userChoice = io.readString("Current flooring type is " + currentType + "\nHit \"enter\" to keep or select from the following options to replace.\n1. Wood\n2. Carpet\n3. Laminate\n4. Tile");

        if (userChoice.isEmpty()) {
            newType = currentType;

        } else {

            int floorChoice = Integer.parseInt(userChoice);
            switch (floorChoice) {
                case 1:
                    newType = "Wood";
                    break;
                case 2:
                    newType = "Carpet";
                    break;
                case 3:
                    newType = "Laminate";
                    break;
                case 4:
                    newType = "Tile";
                    break;

                default:
                    newType = currentType;
                    break;

            }
        }

        //newType = (newType.isEmpty()) ? currentType : newType;
        currentOrder.setFlooringType(newType);
        //saves to hashmap and committed to memory
        Order editedOrder = calcGenerator.createEditedOrder(currentOrder);
        testBook.putEditedOrder(editedOrder);

        io.print("Order successfully edited.");
        io.print("Name: " + testBook.getOrder(orderId).getLastName());
        io.print("Area: " + testBook.getOrder(orderId).getArea());
        io.print("Flooring Type:" + testBook.getOrder(orderId).getFlooringType());
        io.print("Total Cost: $" + testBook.getOrder(orderId).getTotalCost());

    }

    public void removeOrder() throws FileNotFoundException {
        displayOrders();
        int tempId= io.readInt("Enter the id of the order you would like to remove: ");
        Order o = testBook.getOrder(tempId);
        //this needs to iterate
                System.out.println("====================");
                System.out.println("Order #: " + o.getId()//.getOrderNum()
                        + "\nName: " + o.getLastName()
                        + "\nFlooring Type: " + o.getFlooringType() + "\nSquare Footage: " + o.getArea()
                        + "\nTotal Cost: $" + o.getTotalCost());
                System.out.println("");
        
        String confirm = io.readYorN("Are you sure you want to delete this order? (type y or n)");
        
        if(confirm.equals("y"))
        {
         testBook.removeOrder(tempId);
         io.print("Order deleted, but you must selected option 5 from the  main menu to save these changes.");
        }
        else{
        io.print("Deletion aborted.");
        
        }
       
        
        
    }

    public void saveWork(String date) throws IOException {
        //testBook is our instance of the OrderBookDAOFileImpl class
        //ArrayList<Order> dailyOrders =  testBook.orderMap.values();
        //testBook.loadOrderFile(date);

        ArrayList<Order> orderList = new ArrayList<Order>(testBook.orderMap.values());  //Collection<Order> keys = testBook.orderMap.values();

        //for (Order i : orderList) {
        //testBook.putOrders(i);
        //}
        testBook.writeOrderFile(date);
        //testBook.writeOrderFile();
        //testBook.orderMap.clear();--no need to clear the map when we save--will retain our hashmap
    }

    public void quit() throws IOException {
        saveWork(date);
        io.print("Goodbye.");

    }

    private void printMenu() {
        io.print("=============================================================");
        io.print("\t\t        ++++++++++++++++++++");
        io.print("\t\t\t| Flooring Program |");
        io.print("\t\t        ++++++++++++++++++++");
        io.print("");
        io.print("1. Display Orders\n2. Add an Order\n3. Edit an Order\n4. Remove an Order\n5. Save Current Work\n6. Quit");
        io.print("");
        io.print("=============================================================");
    }

}