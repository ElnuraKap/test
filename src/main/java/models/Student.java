package models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Getter@Setter
public class Student {
    private Long id;
    private String name;
    private byte age;

    public Student(String name, byte age) {
        this.name = name;
        this.age = age;
    }
}

