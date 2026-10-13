package org.example.ch02;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        // 앤티티매니저팩토리 생성
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("jpa");

        //앤티티매니저 생성
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        //트랜잭션 생성
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            testDetached(entityManager);
            logic(entityManager);

            transaction.commit();
        }catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            entityManager.close();
        }
        entityManagerFactory.close();

    }

    private static void testDetached(EntityManager em) {
        Member member = em.find(Member.class, "memberA");
        em.clear(); // detached 준영속 상태, 컨텍스토 초기화
        //변경감지 동작 안함
        member.setUsername("changeName");






        //        Member member = new Member();
//        member.setId("memberC");
//        member.setUsername("memberC");
//        member.setAge(20);//비영속 상태
//
//        em.persist(member);//managed 영속상태
//        em.flush();
//        em.detach(member); //detached 준영속상태
//        member.setAge(30);
    }

    private static Member createMember(EntityManager em, String id, String username, int age) {
        Member member = new Member();
        member.setId(id);
        member.setUsername(username);
        member.setAge(age);

        em.persist(member);
        return member;
    }

    private static void logic(EntityManager em) {
        createMember(em, "id1", "테스트1", 10);
        createMember(em, "id2", "테스트2", 20);
        createMember(em, "id3", "테스트3", 30);

        List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();
        System.out.println("memberList,size=" + members.size());
    }



}
