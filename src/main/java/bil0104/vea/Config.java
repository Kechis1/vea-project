package bil0104.vea;

import bil0104.vea.Converters.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Config implements WebMvcConfigurer {
    @Autowired StudentConverter studentConverter;
    @Autowired SubjectConverter subjectConverter;
    @Autowired TeacherConverter teacherConverter;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(studentConverter);
        registry.addConverter(teacherConverter);
        registry.addConverter(subjectConverter);
    }
}
