/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.cefsa.model;

import java.sql.Blob;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author gabri
 */
public class Parametro {
    public String valor;
    public String tipo;
    public LocalDate date;
    public LocalDateTime dateTime;
    public boolean booleano;
    public Blob blob;
    public Integer inteiro;
    public Double decimal;
    
    public Parametro(String valor, String tipo){
        this.valor = valor;
        this.tipo = tipo;
    }
    
    public Parametro(LocalDate date, String tipo){
        this.date = date;
        this.tipo = tipo;
    }
    
    public Parametro (LocalDateTime dateTime, String tipo){
        this.dateTime = dateTime;
        this.tipo = tipo;
    }
    
    public Parametro(boolean booleano, String tipo){
        this.booleano = booleano;
        this.tipo = tipo;
    }
    
    public Parametro(Integer inteiro, String tipo){
        this.inteiro = inteiro;
        this.tipo = tipo;
    }
    
    public Parametro(Double decimal, String tipo){
        this.decimal = decimal;
        this.tipo = tipo;
    }
    
    public Parametro(Blob blob, String tipo){
        this.blob = blob;
        this.tipo = tipo;
    }
}
