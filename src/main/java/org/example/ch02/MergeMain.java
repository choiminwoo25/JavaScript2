package org.example.ch02;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class MergeMain  {
    static EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("jpa");

    public static void main(String[] args) {
        Member member = createMember("test1", "TEST1", 10);
        //준영속 - 변경감지 동작안함
        member.setUsername("TEST2");

        mergeMember(member);

    }

     public static Member createMember(String id, String  username, int age) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        Member member = new Member();
        member.setId(id);
        member.setUsername(username);
        member.setAge(age);

        em.persist(member);
        transaction.commit();
        //준영속상태
        em.close();

        return member;
    }

    public  static void mergeMember(Member member){
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();
        //병합

        Member mergeMember = em.merge(member);
        transaction.commit();

        System.out.println("member : " +member.getUsername());
        System.out.println("mergemember : " + mergeMember.getUsername());
    }

}
