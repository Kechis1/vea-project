package bil0104.vea;

import bil0104.vea.Converters.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Config implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StudentConverter());
        registry.addConverter(new StudentConverter2());
        registry.addConverter(new TeacherConverter());
        registry.addConverter(new TeacherConverter2());
        registry.addConverter(new SubjectConverter());
        registry.addConverter(new SubjectConverter2());
        registry.addConverter(new StudyConverter());
        registry.addConverter(new StudyConverter2());
        registry.addConverter(new PersonConverter());
        registry.addConverter(new PersonConverter2());
    }
}
