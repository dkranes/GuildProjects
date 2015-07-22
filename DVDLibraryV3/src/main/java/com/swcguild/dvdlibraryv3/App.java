/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.dvdlibraryv3;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author apprentice
 */
public class App {

    public static void main(String[] args) throws ParseException, FileNotFoundException, IOException {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        
        DVDLibraryController ct = ctx.getBean("dvdLibraryController", DVDLibraryController.class);
//        DVDLibraryController start = new DVDLibraryController();
//        start.run();
        ct.run();
    }
}
