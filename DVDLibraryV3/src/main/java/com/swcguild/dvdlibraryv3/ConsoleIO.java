package com.swcguild.dvdlibraryv3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleIO {

    private Scanner sc = new Scanner(System.in);

    public void print(String msg) {
        System.out.println(msg);
    }

    public String readString(String prompt) {
        Scanner mySc = new Scanner(System.in);
        System.out.println(prompt);
        return mySc.nextLine();
    }

    public int readInt(String prompt) {
        boolean badInput;
        int result = 0;
        do {
            try {
                System.out.println(prompt);
                result = sc.nextInt();
                badInput = false;
            } catch (InputMismatchException ime) {
                badInput = true;
                sc.nextLine();
            }
        } while (badInput);
        return result;
    }

    public int readInt(String prompt, int min, int max) {
        int result;
        do {
            result = readInt(prompt);
        } while (result < min || result > max);

        return result;
    }

    public long readLong(String prompt) {
        boolean badInput;
        long result = 0;
        do {
            try {
                System.out.println(prompt);
                result = sc.nextLong();
                badInput = false;
            } catch (InputMismatchException ime) {
                badInput = true;
                sc.nextLine();
            }
        } while (badInput);

        return result;
    }

    public long readLong(String prompt, long min, long max) {
        long result;
        do {
            result = readLong(prompt);
        } while (result < min || result > max);

        return result;
    }

    public float readFloat(String prompt) {
        boolean badInput;
        float result = 0;
        do {
            try {
                System.out.println(prompt);
                result = sc.nextFloat();
                badInput = false;
            } catch (InputMismatchException ime) {
                badInput = true;
                sc.nextLine();
            }
        } while (badInput);

        return result;
    }

    public float readFloat(String prompt, float min, float max) {
        float result;
        do {
            result = readFloat(prompt);
        } while (result < min || result > max);

        return result;
    }

    public double readDouble(String prompt) {
        boolean badInput;
        double result = 0;
        do {
            try {
                System.out.println(prompt);
                result = sc.nextDouble();
                badInput = false;
            } catch (InputMismatchException ime) {
                badInput = true;
                sc.nextLine();
            }
        } while (badInput);

        return result;
    }

    public double readDouble(String prompt, double min, double max) {
        long result;
        do {
            result = readLong(prompt);
        } while (result < min || result > max);

        return result;
    }

    public String readYorN(String prompt) {
        Scanner sc2 = new Scanner(System.in);
        boolean done = false;
        String answer = null;
        while (!done) {
            try {
                System.out.print(prompt);
                answer = sc2.nextLine();
                if (answer.equalsIgnoreCase("Y") || (answer.equalsIgnoreCase("N"))) {
                    done = true;
                    return answer;
                } else {
                    System.out.print("Please enter only \"y\" or \"n\": ");
                }
            } catch (Exception e) {
                //System.out.print("Please enter only \"y\" or \"n\": ");
            }
        }
        return answer;
    }

    public String readMPAARating(String prompt) {
        Scanner sc2 = new Scanner(System.in);
        boolean finished = false;
        String rating = null;
        while (!finished) {
            try {

                System.out.println(prompt);
                rating = sc2.nextLine();
                if ((rating.equalsIgnoreCase("G")) || (rating.equalsIgnoreCase("PG")) || (rating.equalsIgnoreCase("PG-13")) || (rating.equalsIgnoreCase("R")) || (rating.equalsIgnoreCase("NC-17"))) {
                    finished = true;
                    return rating;

                } else {
                    System.out.println("Please enter only \"G\", \"PG\", \"PG-13\", \"R\", \"NC-17\":");

                }

            } catch (Exception e) {
                //System.out.println("Please enter only \"G\", \"PG\", \"PG-13\", \"R\", \"NC-17\":");
            }

        }
        return rating;
    }

    public String readDate(String prompt) {
        Scanner scanner = new Scanner(System.in);

//        final DateTimeFormatter dtf = DateTimeFormatter.BASIC_ISO_DATE;
//        final LocalDate dt = dtf.parseLocalDate(yourinput);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        boolean over = false;
        LocalDate date = null;
        String answer;
        String result;
        while (!over) {

            System.out.println(prompt);

            answer = scanner.nextLine();
            if (answer.equals("")) {
                over = true;

            } else {

                try {

                    date = LocalDate.parse(answer, formatter);
                    over = true;
                } catch (NullPointerException n) {
                    over = true;
                } catch (DateTimeParseException e) {
                    over = false;
                }
            }
        }
        if (date == null) {
            result = "";
        } else {
            result = date.toString();
            String[] parts = result.split("-");
            result = parts[0] + "-" + parts[1] + "-" + parts[2];

        }
        return result;
    }

}
