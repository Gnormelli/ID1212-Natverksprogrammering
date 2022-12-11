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
   private int user_id;
   private String userID;
   private int subjectID;
   public List<Pair> listOfScores;
  
    public Model(){
        try{
                subject = null;
                logdIn = false;
                initContext = new InitialContext();
                envContext  = (Context)initContext.lookup("java:/comp/env");
                ds = (DataSource)envContext.lookup("jdbc/derby");
                allSubjects =new ArrayList<Pair>(); 
                conn = ds.getConnection();
                stmt = conn.createStatement();
                quiz =new ArrayList<Question>();
                listOfScores =new ArrayList<Pair>();
                higescore = 0;
                user_id = -1;
                setSubjects();
                setScores();
            }catch(Exception e){  
            
            }     
        
           
    }
    
    public boolean logIn(String username ,String password){
        ResultSet rs; 
        List<String> usernamesExisting=new ArrayList<String>(); 
        List<String> passwordExisting=new ArrayList<String>();
        List<String> userIDExisting=new ArrayList<String>();
        
        try{
            rs = stmt.executeQuery("select username from users");

            while (rs.next()) {
                usernamesExisting.add(rs.getString("username"));
            }
            rs = stmt.executeQuery("select password from users");
            while (rs.next()) {
                passwordExisting.add(rs.getString("password"));
            }
           
            rs = stmt.executeQuery("select id from users");
            while (rs.next()) {
                userIDExisting.add(rs.getString("id"));
            }
            
            if(usernamesExisting.contains(username)){

                int passwordIndex = usernamesExisting.indexOf(username);
                String passwordThatisCorrect = passwordExisting.get(passwordIndex);
                if(passwordThatisCorrect.equals(password)){
                   
                    this.userID = userIDExisting.get(passwordIndex);
                    
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
                    this.subjectID = id;
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
           return this.allSubjects;
    }
    
    
       public List<Pair> setSubjects(){
        try{
            ResultSet rs = stmt.executeQuery("select * from quizzes");
            while (rs.next()) {
               allSubjects.add(new Pair​(rs.getInt("id"), rs.getString("subject")));
            }
        }catch(Exception e){
            
        }
        return this.allSubjects;     
    }
  public int setResult(String answersUrl)  {
        List<Pair> answerList = new ArrayList<Pair>();
        String[] answerArray = answersUrl.split("AnswerToQuestion_"); //index 0 is empty Watch out!
        points = 0;
        int questionID;
        Pair input;
        for(int i = 1; answerArray.length > i; i++){
            questionID = Integer.valueOf(answerArray[i].charAt(0)) - 48;
            answerArray[i] = answerArray[i].split("&")[0];
            answerArray[i] = answerArray[i].split("=")[1];
            input =  new Pair(questionID,answerArray[i]);
            answerList.add(input);  //HERE IS QUESTION ID AND USER ANSWER STORED
        }
       
        String[] trueAnswers;
        String userAnswer;
        int sizeOfList = answerList.size();
        for(int i = 0; sizeOfList > i; i++){
            questionID = (int)answerList.get(i).getKey();
            userAnswer = (String)answerList.get(i).getValue();
            trueAnswers = quiz.get(questionID - 1).makeAnswerToText();
            for(int j = 0; j < trueAnswers.length; j++){
             
                if(userAnswer.equals(trueAnswers[j])){
                    points++;
                }
            }
        }
         
        checkAndPushHigescore();
        addToScores();
        return points;
    }
    
    private void checkAndPushHigescore(){
        
       try{
            quiz =new ArrayList<Question>(); //Resets quiz
            stmt.executeUpdate("INSERT INTO results (user_id,quiz_id,score) VALUES ("+this.userID+","+this.subjectID+","+points+")");
            System.out.println("new score :" + points);
                
          }catch(Exception e){
            System.out.println(e);
            System.out.println("error from higescore set");
        }
        
    }
    
    private void addToScores(){
        this.listOfScores.add(new Pair(this.subject,this.points));
    }
    
    private void setScores(){
        
        ResultSet rs;
        String quiz_number;
        String subject_string = "Error";
        String score;
        Pair pairHold;
           try{
               rs = stmt.executeQuery("SELECT SCORE, quiz_id FROM RESULTS");
                while(rs.next()){
                    quiz_number = rs.getString("quiz_id");
                    score = rs.getString("SCORE");
                    for(Pair set : allSubjects){
                        if(Integer.valueOf(quiz_number) == (int)(set.getKey())){
                            subject_string = (String)set.getValue();
                        }
                    }
                    pairHold = new Pair(subject_string,score);
                    this.listOfScores.add(pairHold);
                }
                this.quiz =new ArrayList<Question>();   //resets quiz
           }catch(Exception e){
            System.out.println(e);
            System.out.println("error in set scores");
        }
    }
}

