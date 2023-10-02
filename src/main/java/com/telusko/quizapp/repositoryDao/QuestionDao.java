package com.telusko.quizapp.repositoryDao;

import com.telusko.quizapp.model.QuestionResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<QuestionResponseDto,Integer> {
    @Query(value="select * from question ",nativeQuery = true)
    List<QuestionResponseDto> getAllQuestion();

    @Query(value="select * from question where category = :category",nativeQuery = true)
    List<QuestionResponseDto> findByCategory(String category);

    @Query(value="select * from question q where q.category = :category " +
            "order by RAND() " +
            "LIMIT :numQ",nativeQuery = true)
    List<QuestionResponseDto> findRandomQuestionsByCategory(String category, int numQ);


}
