package edu.sabanciuniv.test;

import edu.sabanciuniv.model.*;
import edu.sabanciuniv.utility.EntityManagerUtils;
import jakarta.persistence.EntityManager;


import java.time.LocalDate;
import java.time.Month;

public class TestInsuranceApp {
    public static void main(String[] args){
        saveTestData();

    }

    private static void saveTestData() {

        Student student1 = new Student("Ahmet Demir", LocalDate.of(1992, Month.SEPTEMBER,11),"Antalya","Male");
        Student student2 = new Student("Ayse Polat", LocalDate.of(1989, Month.AUGUST,05), "Sivas","Female");
        Student student3 = new Student("Oya Lale", LocalDate.of(1982, Month.APRIL,14), "Istanbul","Female");

        Course course1=new Course("Java Development",1003,3.00);
        Course course2=new Course("Database Management",1008,3.00);
        Course course3=new Course("Agile Project Management",1011,3.00);
        Course course4=new Course("Machine Learning",1002,3.00);


        Instructor permanentInstructor1=new PermanentInstructor("Nazlı Servi","İstanbul","5327653478",45000.00);
        Instructor permanentInstructor2=new PermanentInstructor("Nihal Aydın","İstanbul","5057986572",32000.00);
        Instructor visitingResearcher1=new VisitingResearcher("Ahmet Senocak","İstanbul","5356896754",18000.00);
        Instructor visitingResearcher2=new VisitingResearcher("Tolga Celik","İstanbul","5453456545",15000.00);

        course1.setInstructor(permanentInstructor1);
        course2.setInstructor(permanentInstructor2);
        course3.setInstructor(visitingResearcher2);
        course4.setInstructor(visitingResearcher1);

        course1.getStudentList().add(student1);
        course2.getStudentList().add(student2);
        course3.getStudentList().add(student3);
        course4.getStudentList().add(student1);


        EntityManager entityManager = EntityManagerUtils.getEntityManager("mysqlPU");
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(course1);
            entityManager.persist(course2);
            entityManager.persist(course3);
            entityManager.persist(course4);
            entityManager.persist(student1);
            entityManager.persist(student2);
            entityManager.persist(student3);
            entityManager.persist(permanentInstructor1);
            entityManager.persist(permanentInstructor2);
            entityManager.persist(visitingResearcher1);
            entityManager.persist(visitingResearcher2);
            entityManager.getTransaction().commit();
            System.out.println("All data persisted.");
        }catch(Exception e){
            entityManager.getTransaction().rollback();
        }finally{
            EntityManagerUtils.closeEntityManager(entityManager);

        }



    }
}
