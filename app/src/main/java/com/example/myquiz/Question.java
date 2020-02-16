package com.example.myquiz;

public class Question {
    private int myQuestion;
    private boolean answer;

    public Question(int myQuestion, boolean answer) {
        this.myQuestion = myQuestion;
        this.answer = answer;
    }

    public int getMyQuestion() {
        return myQuestion;
    }

    public void setMyQuestion(int myQuestion) {
        this.myQuestion = myQuestion;
    }

    public boolean isAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }
}
