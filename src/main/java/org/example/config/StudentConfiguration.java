package org.example.config;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentConfiguration {

    @Value("""
            #{@getYamlObjectMapper.readValue(T(org.springframework.util.ResourceUtils).getFile('classpath:students.yaml'), T(org.example.dto.Students)).toString()}
            """)
    String students;

}
