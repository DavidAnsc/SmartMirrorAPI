package com.davidan.SmartMirrorAPI.models;

import org.json.simple.JSONObject;

import com.davidan.SmartMirrorAPI.kits.APIKit;
import com.davidan.SmartMirrorAPI.kits.XMLHandlerKit;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Transient;

@Entity
public class MathQuestionsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "math_questions_id_seq")
    @SequenceGenerator(name = "math_questions_id_seq", sequenceName = "math_questions_id_seq", allocationSize = 1)
    int id;

    
    @Transient
    /**/private String[] GETKEYS = {"prime", "perfect", "mersenne", "fermat", "fibonacci", "partition", "pell"};
    @Transient
    /**/private String APIURL = "https://api.math.tools/numbers/nod";
    @Column(name="questions")
    private String[] questions = new String[GETKEYS.length];
    @Column(name="answers")
    private String[] answers = new String[GETKEYS.length];
    @Transient
    /**/private JSONObject jsonObj;
    
    public MathQuestionsModel() {
    }

    public int getId() {
        return id;
    }
    public String[] getQuestions() {
        return questions;
    }
    public String[] getAnswers() {
        return answers;
    }

    public void obtainData() {
        try {
            this.jsonObj = APIKit.getJSONObjectByIS(XMLHandlerKit.xmlAPIToIS(APIURL));

            JSONObject root = APIKit.getJSONSubitem(jsonObj, "contents");
            JSONObject nodRoot = APIKit.getJSONSubitem(root, "nod");
            JSONObject numbersRoot = APIKit.getJSONSubitem(nodRoot, "numbers");
            JSONObject prime_facts = APIKit.getJSONSubitem(numbersRoot, "prime-facts");
            for (int i = 0; i < GETKEYS.length; i++) {
                JSONObject tempObj = APIKit.getJSONSubitem(prime_facts, GETKEYS[i]);
                String question = APIKit.<String>getJSONToT(tempObj, "description");
                String answer = APIKit.<String>getJSONToT(tempObj, "display");
                questions[i] = question;
                answers[i] = answer;
            }

        } catch (Exception e) {
            System.out.println("MathQuestionsModel.ObtainQuestions(): " + e.getMessage());
        }
    }

    // public static void main(String[] args) {
    //     try {
    //         MathQuestionsModel mqm = new MathQuestionsModel();
    //         mqm.obtainData();
    //         for (int i = 0; i < mqm.questions.length; i++) {
    //             System.out.println(mqm.questions[i] + " -> " + mqm.answers[i]);
    //         }
    //     } catch (Exception e) {
    //         System.out.println("MathQuestionsModel.main(): " + e.getMessage());
    //     }
    // }
}
