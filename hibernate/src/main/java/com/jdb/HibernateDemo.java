package com.jdb;


import java.util.ArrayList;
import java.util.List;

import com.jdb.entity.Course;
import com.jdb.entity.Instructor;
import com.jdb.entity.InstructorDetail;
import com.jdb.entity.Student;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateDemo {

    static SessionFactory factory = new Configuration()
                                    .configure("hibernate.cfg.xml")
                                    .addAnnotatedClass(Student.class)
                                    .addAnnotatedClass(Instructor.class)
                                    .addAnnotatedClass(InstructorDetail.class)
                                    .addAnnotatedClass(Course.class)
                                    .buildSessionFactory();
    public static void main(String[] args) {
        createEntity();
        factory.close();
    }

    static void createEntity() {
        Session session = factory.openSession();

        try {
            Instructor instructor = new Instructor("Jared", "Bautista", "jdb@gmail.com");
            InstructorDetail inDetail = new InstructorDetail("YoutubeChannel", "Playing with Summer!");
            instructor.setInstructorDetail(inDetail);
            List<Course> courses = new ArrayList<>();
            Course course = new Course("Test Course");
            courses.add(course);
            course.setInstructor(instructor);
            instructor.setCourses(courses);
            
            session.beginTransaction();
            session.persist(instructor);
            session.getTransaction().commit();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
        }
        finally {
            session.close();
        }
    }
}
