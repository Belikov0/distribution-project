package com.example.springbootdemo.bean;

public class WorkerBean {
    private String worker_id;
    private String worker_name;
    private String worker_gender;
    private int worker_age;
    private String worker_tel;
    private String depart_name;

    public String getWorker_id() {
        return worker_id;
    }

    public void setWorker_id(String worker_id) {
        this.worker_id = worker_id;
    }

    public String getWorker_name() {
        return worker_name;
    }

    public void setWorker_name(String worker_name) {
        this.worker_name = worker_name;
    }

    public String getWorker_gender() {
        return worker_gender;
    }

    public void setWorker_gender(String worker_gender) {
        this.worker_gender = worker_gender;
    }

    public int getWorker_age() {
        return worker_age;
    }

    public void setWorker_age(int worker_age) {
        this.worker_age = worker_age;
    }

    public String getWorker_tel() {
        return worker_tel;
    }

    public void setWorker_tel(String worker_tel) {
        this.worker_tel = worker_tel;
    }

    public String getDepart_name() {
        return depart_name;
    }

    public void setDepart_name(String depart_name) {
        this.depart_name = depart_name;
    }

    @Override
    public String toString() {
        return "WorkerBean{" +
                "worker_id='" + worker_id + '\'' +
                ", worker_name='" + worker_name + '\'' +
                ", worker_gender='" + worker_gender + '\'' +
                ", worker_age=" + worker_age +
                ", worker_tel='" + worker_tel + '\'' +
                ", depart_name='" + depart_name + '\'' +
                '}';
    }
}
