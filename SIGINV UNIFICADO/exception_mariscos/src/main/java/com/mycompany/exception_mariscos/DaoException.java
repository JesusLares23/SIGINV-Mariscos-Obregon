/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.exception_mariscos;

/**
 *
 * @author martinbl
 */
public class DaoException extends Exception {
    public DaoException(String message) { super(message); }
    public DaoException(String message, Throwable cause) { super(message, cause); }
}
