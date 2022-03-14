/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.developercfk.main;

/**
 *
 * @author CFK Local
 */
public class Compteur {
    
    
    volatile int compteur = 0;

    public Compteur() {
        compteur++;
    }
    
   
}
