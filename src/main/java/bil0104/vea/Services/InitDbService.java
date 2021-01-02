package bil0104.vea.Services;


import bil0104.vea.DAO.*;
import bil0104.vea.Entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;
import java.util.List;

@Transactional
@Service
public class InitDbService {

    @Autowired
    private AbstractDao<Subject> subjectDao;
    @Autowired
    private TeacherDao teacherDao;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private StudyDao studyDao;
    @Autowired
    private PersonDao<Person> personDao;

    @PostConstruct
    public void init() {
        List<Subject> subs = new ArrayList<>();
        List<Student> stu = new ArrayList<>();
        List<Teacher> teas = new ArrayList<>();
        List<Study> studies = new ArrayList<>();

        personDao.insert(new Person("ADM000", "Admin", "Admin", new Date(80, Calendar.APRIL, 2), "$2a$10$SAiB6hw6yWnPpAp82L9OqeHGQU9KjSKAKLRBgYAuysJe4pF25I4Gy", Role.ADMIN));


        teas.add(new Teacher("DUB000", "Lukáš", "Denver", new Date(92, Calendar.FEBRUARY,9), "$2a$10$SAiB6hw6yWnPpAp82L9OqeHGQU9KjSKAKLRBgYAuysJe4pF25I4Gy", null));
        teas.add(new Teacher( "BRE123", "Alžběta", "Helsinki", new Date(91, Calendar.DECEMBER,8), "$2a$10$SAiB6hw6yWnPpAp82L9OqeHGQU9KjSKAKLRBgYAuysJe4pF25I4Gy", null));
        teas.add(new Teacher( "ZEN003", "Eliška", "Tokyo", new Date(90, Calendar.JANUARY,10), "$2a$10$SAiB6hw6yWnPpAp82L9OqeHGQU9KjSKAKLRBgYAuysJe4pF25I4Gy", null));


        subs.add(new Subject("UDBS", "Úvod do dbs", 2, Semester.SUMMER, 4, null, null));
        subs.add(new Subject("AVD", "Algority vykonání dotazů", 5, Semester.SUMMER, 5, null, null));
        subs.add(new Subject("ADBS", "Administrace DBS", 3, Semester.WINTER, 3, null, null));

        subjectDao.insert(subs.get(0));
        subjectDao.insert(subs.get(1));
        subjectDao.insert(subs.get(2));

        teas.get(0).setTeaches(subjectDao.getAll());

        teacherDao.insert(teas.get(0));
        teacherDao.insert(teas.get(1));
        teacherDao.insert(teas.get(2));


        stu.add(new Student("BIL104", "Daniel", "Bill", new Date(99, Calendar.APRIL,2), "$2a$10$SAiB6hw6yWnPpAp82L9OqeHGQU9KjSKAKLRBgYAuysJe4pF25I4Gy", null, 3));
        stu.add(new Student("ALE201", "Alena", "Aleso", new Date(98, Calendar.MARCH,5), "$2a$10$SAiB6hw6yWnPpAp82L9OqeHGQU9KjSKAKLRBgYAuysJe4pF25I4Gy", null, 4));
        stu.add(new Student("DRA001", "Bronze", "Drag", new Date(97, Calendar.JANUARY,11), "$2a$10$SAiB6hw6yWnPpAp82L9OqeHGQU9KjSKAKLRBgYAuysJe4pF25I4Gy", null, 5));

        studentDao.insert(stu.get(0));
        studentDao.insert(stu.get(1));
        studentDao.insert(stu.get(2));

        studies.add(new Study("2020/2021", 98, stu.get(0), subs.get(0)));
        studies.add(new Study("2020/2021", 89, stu.get(0), subs.get(1)));
        studies.add(new Study("2020/2021", 77, stu.get(0), subs.get(2)));
        studies.add(new Study("2019/2020", 41, stu.get(0), subs.get(2)));


        stu.get(0).setStudies(studies);

        studyDao.insert(studies.get(0));
        studyDao.insert(studies.get(1));
        studyDao.insert(studies.get(2));
        studyDao.insert(studies.get(3));


        for (Subject sub : subs) {
            sub.setTeacher(teas.get(0));
            subjectDao.update(sub);
        }
    }
}
