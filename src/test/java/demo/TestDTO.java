package demo;

import lombok.Data;

import java.io.Serializable;

@Data
public class TestDTO implements Serializable {
    public String name;
    public String rich;
}
