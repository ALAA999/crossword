package com.example.acer.crossword;

/**
 * Created by acer on 2/19/2018.
 */

public class questions {
    int id;
    String question;
    String right_ans;
    int is_ans;
    int img;

    public questions(int id, String question, String right_ans, int is_ans, int img) {
        this.id = id;
        this.question = question;
        this.right_ans = right_ans;
        this.img = img;
        this.is_ans = is_ans;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getRight_ans() {
        return right_ans;
    }

    public void setRight_ans(String right_ans) {
        this.right_ans = right_ans;
    }

    public int getIs_ans() {
        return is_ans;
    }

    public void setIs_ans(int is_ans) {
        this.is_ans = is_ans;
    }
}
