package bil0104.vea.AOP;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

@Component
@Aspect
public class AOPLog {
    public static final String filePath = "log.txt";

    @Before("execution(* bil0104.vea.*.*.*(..))")
    public void log(JoinPoint joinPoint) {
        Date date = new Date();
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(filePath, true);
            fileWriter.write("Action: " + joinPoint.getSignature());
            fileWriter.write("\nTime: " + date.toString() + "\n\n");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}