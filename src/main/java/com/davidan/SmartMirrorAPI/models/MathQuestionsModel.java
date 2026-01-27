package com.davidan.SmartMirrorAPI.models;

import org.json.simple.JSONObject;

import com.davidan.SmartMirrorAPI.kits.APIKit;
import com.davidan.SmartMirrorAPI.kits.XMLHandlerKit;

public class MathQuestionsModel {
    /**/private String[] GETKEYS = {"prime", "perfect", "mersenne", "fermat", "fibonacci", "partition", "pell"};
    /**/private String APIURL = "https://api.math.tools/numbers/nod";
    private String[] questions = new String[GETKEYS.length];
    private String[] answers = new String[GETKEYS.length];
    /**/private JSONObject jsonObj;

    public MathQuestionsModel() throws Exception {
        this.jsonObj = APIKit.getJSONObjectByIS(XMLHandlerKit.xmlAPIToIS(APIURL));
    }
    public String[] getQuestions() {
        return questions;
    }
    public String[] getAnswers() {
        return answers;
    }

    public void obtainData() {
        try {
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

    public static void main(String[] args) {
        try {
            MathQuestionsModel mqm = new MathQuestionsModel();
            mqm.obtainData();
            for (int i = 0; i < mqm.questions.length; i++) {
                System.out.println(mqm.questions[i] + " -> " + mqm.answers[i]);
            }
        } catch (Exception e) {
            System.out.println("MathQuestionsModel.main(): " + e.getMessage());
        }
    }
}
