package com.example.demo.repository;

import com.example.demo.model.ChatRoom;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    Optional<ChatRoom> findByUser1AndUser2(User user1, User user2);
    Optional<ChatRoom> findByUser1AndUser2OrUser2AndUser1(User user1, User user2, User user2b, User user1b);

    @Query("SELECT c FROM ChatRoom c WHERE " +
            "(c.user1.username = :user1 AND c.user2.username = :user2) OR " +
            "(c.user1.username = :user2 AND c.user2.username = :user1)")
    Optional<ChatRoom> findByUsers(@Param("user1") String user1, @Param("user2") String user2);

}
