/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;  
import javafx.util.Pair;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 *
 * @author Gustav Normelli
 */
public class Model {
  
   public List<Pair> allSubjects;
   public boolean logdIn;
   private Statement stmt;
   private Context initContext;
   private Context envContext;
   private Connection conn;
   private DataSource ds;
   public String subject;
   public List<Question> quiz;
   public int points;
   public int currentQuiz;
   public int higescore;
   public String higescoreSubject;
  
    public Model(){
        try{
                subject = null;
                logdIn = false;
                initContext = new InitialContext();
                envContext  = (Context)initContext.lookup("java:/comp/env");
                ds = (DataSource)envContext.lookup("jdbc/derby");
                conn = ds.getConnection();
                stmt = conn.createStatement();
                quiz =new ArrayList<Question>();
                higescore = 0;
                
            }catch(Exception e){  
            
            }     
        
           
    }
    
    public boolean logIn(String username ,String password){
        ResultSet rs;
        List<String> usernamesExisting=new ArrayList<String>(); 
        List<String> passwordExisting=new ArrayList<String>();
        try{
            rs = stmt.executeQuery("select username from users");

            while (rs.next()) {
                usernamesExisting.add(rs.getString("username"));
            }
            rs = stmt.executeQuery("select password from users");
            while (rs.next()) {
                passwordExisting.add(rs.getString("password"));
            }
            if(usernamesExisting.contains(username)){

                int passwordIndex = usernamesExisting.indexOf(username);
                String passwordThatisCorrect = passwordExisting.get(passwordIndex);
                if(passwordThatisCorrect.equals(password)){
                    return true;
                }else{
                    return false;
                }
            }else{
                return false;
            }
        }catch(Exception e){
                System.out.println(e);
        }
       return true;
    }
    public void setAllSubjects(List<Pair> allSubjects){
        this.allSubjects = allSubjects;
    }
    
    public void setChoosenSubject(String subject){
        this.subject = subject;
    }
    
    public void setQuestions(){
        int id = 0;
        ResultSet rs;
        try{
            for(Pair<Integer, String> pair : this.allSubjects){
                if(pair.getValue().equals(this.subject)){
                    id = pair.getKey();
                    
                }
            }
            rs = stmt.executeQuery("select question_id from selector");
            List<Integer> listOfQuestionId = new ArrayList<Integer>();
            while (rs.next()) {
                this.currentQuiz = rs.getInt("question_id");
                listOfQuestionId.add(this.currentQuiz);
            }
           
            
            
            
            rs = stmt.executeQuery("select * from questions");
            while (rs.next()) {
                
                
                if(listOfQuestionId.contains(rs.getInt("id"))){
                   String text = rs.getString("text");
                    this.quiz.add(
                                new Question(
                                        rs.getInt("id"), text,
                                        rs.getString("options"),rs.getString("answer")
                                        )
                                    );
               
               }
                                        
            }
        }catch(Exception e){
            System.out.println("getQuestions: \n" + e);
        }
    }
   
    public List<Pair> getSubjects(){
        try{
            ResultSet rs = stmt.executeQuery("select * from quizzes");
            List<Pair> allSubjects=new ArrayList<Pair>(); 

            while (rs.next()) {
               allSubjects.add(new Pair​(rs.getInt("id"), rs.getString("subject")));
               //out.print(allSubjects.get(0).getValue());
            }
            setAllSubjects(allSubjects);
            return(this.allSubjects);
        }catch(Exception e){
            
        }
        return this.allSubjects;     
    }
    
    
    
    public int getResult(String answersUrl)  {
        
        
        quiz.get(0).getAnswer();
        
        String[] answerArray = answersUrl.split("AnswerToQuestion_"); //index 0 is empty Watch out!
        int points = 0;
      
        for(int i = 1; answerArray.length > i; i++){
            answerArray[i] = answerArray[i].split("&")[0];
            answerArray[i] = answerArray[i].split("=")[1];
          }
       
        
        for(int i = 1; answerArray.length >= i; i++){
          //  System.out.println(quiz.get(i).answer);
            //if(){ //iif correct answer
           // System.out.println(answerArray[i]);
                points++;
           // }      
        }
        
        
        
       // System.out.println(this.allSubjects.get(this.currentQuiz).getKey());
        
        if(points > higescore){
            higescore = points;
            
        }
        
        return points;
    }
}
