package com.example.myapplication;

import java.io.Serializable;

//All Job Fragment implementations belongs to Ali Marangoz
public class Job implements Serializable {
    private String jobName;
    private String jobDescription;


    public Job(String jobName, String jobDescription) {
        this.jobName = jobName;
        this.jobDescription = jobDescription;

    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

}
