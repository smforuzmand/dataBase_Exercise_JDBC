package org.example;

import DAOs.ProductDAO;
import DAOs.ShoppingCartDAO;
import com.mysql.cj.xdevapi.Result;
import jdbc.DataBaseCredential;
import jdbc.ProductDAOJdbcImpl;
import jdbc.ShoppingCartDAOJdbcImpl;
import model.Product;
import model.ShoppingCart;

import java.sql.*;
import java.time.LocalDateTime;

public class App {

    public static void main(String[] args) {
        DataBaseCredential.initialize("dataBase/mySQL.properties");

//*************************************************************Product**************************************************
        //Instantiating the productDAO Impl

        ProductDAO productDAO = new ProductDAOJdbcImpl();


        //-------------------------TRY TO FILL NEW INSTANCE INTO PRODUCT TABLE---------------
        productDAO.create(new Product(5, "product5", 580));


        //------------------------TRY FIND PRODUCT--------------------------
        System.out.println("-----------------FindAll()-----------------");
        productDAO.findAll().forEach(System.out::println);

        System.out.println("-----------------FindById()-----------------");
        System.out.println(" productDAO.findById(1)\n = " + productDAO.findById(1));

        System.out.println("-----------------FindByName()-----------------");
        System.out.println(productDAO.findByName("product2"));

//*******************************************ShoppingCart***************************************************************
        ShoppingCartDAOJdbcImpl shoppingCartDAO = new ShoppingCartDAOJdbcImpl();
        //shoppingCartDAO.create(new ShoppingCart(5, LocalDateTime.parse("2022-09-02T10:15:30"), "Ronnebygatan40", "customerService7",
               // true, "Wrong username"));


        //----------------------TRY  ShoppingCarts-----------------------
        System.out.println("-----------------FindAll()-----------------");
        shoppingCartDAO.findAll().forEach(System.out::println);
        System.out.println(shoppingCartDAO.findById(2));


    }
}