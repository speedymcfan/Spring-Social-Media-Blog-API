package com.example.repository;

import com.example.entity.Message;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long>{

    Message findMessageByMessageId(int messageId);

    int deleteByMessageId(int messageId);

    List<Message> findMessagesByPostedBy(int postedBy);
}
