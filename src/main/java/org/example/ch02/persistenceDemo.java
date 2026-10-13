package org.example.ch02;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class persistenceDemo {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            logic(em);
            transaction.commit();


        }catch (Exception e ) {
        e.printStackTrace();
        transaction.rollback();
        }finally {
            em.close();
            emf.close();
        }
    }

    private static void logic(EntityManager em) {

        em.persist(create("m1", "김철수", 20));
        em.persist(create("m2", "이영희", 20));
        em.persist(create("m3", "김철수", 20));

        print("[저장 후]", em);

        // m2 조회
        Member member = em.find(Member.class, "m2");

        // m2 나이 수정
        member.setAge(30);

        // m3 삭제
        Member member3 = em.find(Member.class, "m3");
        em.remove(member3);

        print("[수정 및 삭제 후]", em);
    }

    public static Member create(String id, String username, int age) {
        Member member = new Member();
        member.setId(id);
        member.setUsername(username);
        member.setAge(age);
        return member;
    }

    private static void print(String label, EntityManager em) {

        List<Member> members =
                em.createQuery(
                        "select m from Member m",
                        Member.class
                ).getResultList();

        System.out.println(label);

        for (Member member : members) {
            System.out.println(
                    member.getId() + " / " +
                            member.getUsername() + " / " +
                            member.getAge()
            );
        }
    }

}
