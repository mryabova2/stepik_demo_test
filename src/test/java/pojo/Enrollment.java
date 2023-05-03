package pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Enrollment {

    @JsonProperty("course")
    private int courseId;

    public Enrollment(int courseId) {
        this.courseId = courseId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
}
