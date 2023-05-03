package pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EnrollBody {

    @JsonProperty("enrollment")
    private Enrollment enrollment;

    public EnrollBody(Enrollment enrollment){
        this.enrollment = enrollment;
    }
}

