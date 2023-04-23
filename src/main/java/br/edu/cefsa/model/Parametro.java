/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.cefsa.model;

import java.time.LocalDate;

/**
 *
 * @author gabri
 */
public class Parametro {
    public String valor;
    public String tipo;
    public LocalDate date;
    
    public Parametro(String valor, String tipo){
        this.valor = valor;
        this.tipo = tipo;
    }
    
    public Parametro(LocalDate date, String tipo){
        this.date = date;
        this.tipo = tipo;
    }
}
