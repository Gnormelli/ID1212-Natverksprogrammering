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
public class Question {
    int id;
    String text;
    String options;
    String answer;
    String[] optionsArray;
    String[] answerArray;
    
    public Question(int id, String text, String options, String answer){
        this.id = id;
        this.text = text;
        this.options = options;
        this.answer = answer; 
        this.optionsArray = options.split("/");
        this.answerArray = optionsArray;
    }
    
    public String[] makeAnswerToText(){
        String[] hold = answer.split("/");
        int count = 0;
        for(int i = 0; i < answerArray.length; i++){
             if(hold[count].equals("0")){
                answerArray[count] = "";
                count++;
            }
           
        }
       
        return answerArray;
    }
 
    
    public String getText(){
        return this.text;
    }
    
    public String getOptions(){
        return this.options;
    }
    
    public String[] getOptionsArray(){
        return this.optionsArray;
    }
 
    
     public int getId(){
        return this.id;
    }
     
      public String getAnswer(){
        
          return this.answer;
    }
     
}
