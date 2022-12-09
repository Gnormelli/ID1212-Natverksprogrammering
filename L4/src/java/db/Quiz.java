/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

/**
 *
 * @author Gustav Normelli
 */
public class Quiz {
    int id;
    String text;
    String options;
    String answer;
    
    
    public Quiz(int id, String text, String options, String answer){
        this.id = id;
        this.text = text;
        this.options = options;
        this.answer = answer; 
    }
}
