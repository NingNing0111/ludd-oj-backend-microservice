package com.ningning0111.service.impl;

import com.ningning0111.common.BaseResponse;
import com.ningning0111.common.ResultUtils;
import com.ningning0111.model.dto.question.QuestionAddRequest;
import com.ningning0111.model.dto.question.QuestionQueryRequest;
import com.ningning0111.model.entity.Question;
import com.ningning0111.model.entity.TestCase;
import com.ningning0111.model.enums.QuestionDifficultyType;
import com.ningning0111.repository.QuestionRepository;
import com.ningning0111.repository.TestCaseRepository;
import com.ningning0111.repository.specification.QuestionSpecification;
import com.ningning0111.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

/**
 * @Project: com.ningning0111.service.impl
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/3/3 12:37
 * @Description:
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class QuestionServiceImpl implements QuestionService {


    private final QuestionRepository questionRepository;
    private final TestCaseRepository testCaseRepository;

    @Override
    public BaseResponse addQuestion(QuestionAddRequest request) {

        long currentTimeMillis = System.currentTimeMillis();
        // 题目实体
        Question question = Question.builder()
                .acceptedNum(0)
                .createTime(new Date(currentTimeMillis))
                .updateTime(new Date(currentTimeMillis))
                .isDelete(0)
                .content(request.getContent())
                .difficulty(QuestionDifficultyType.getEnumByValue(request.getDifficulty()))
                .title(request.getTitle())
                .favourNum(0)
                .submitNum(0)
                .thumbNum(0)
                .userId(request.getUserId())
                .tags(request.getTags())
                .build();
        question = questionRepository.save(question);
        // 测试用例
        Long questionId = question.getId();
        List<String> inputData = request.getInputData();
        List<String> outputData = request.getOutputData();
        TestCase testCase = TestCase.builder()
                .questionId(questionId)
                .inputData(inputData)
                .outputData(outputData)
                .build();
        TestCase testCaseQuestion = testCaseRepository.queryTestCaseByQuestionId(questionId);
        if(testCaseQuestion == null){
            testCaseRepository.save(testCase);
        }else{
            testCaseQuestion.setInputData(inputData);
            testCaseQuestion.setOutputData(outputData);
            testCaseRepository.save(testCaseQuestion);
        }
        return ResultUtils.success("添加成功");
    }

    @Override
    public BaseResponse deleteQuestion(Long questionId) {
        return null;
    }

    @Override
    public BaseResponse queryQuestionPage(QuestionQueryRequest request, Integer page, Integer size) {
        QuestionSpecification spe = new QuestionSpecification(request);
        Page<Question> questions = questionRepository.findAll(spe, PageRequest.of(page, size));

        return ResultUtils.success(questions);
    }

    @Override
    public BaseResponse queryQuestion(Long questionId) {
        Optional<Question> questions = questionRepository.findById(questionId);
        if(questions.isPresent()){
            Question question = questions.get();
            return ResultUtils.success(question);
        }
        return ResultUtils.success(null);
    }

    @Override
    public void addPassNumQuestion(Long questionId) {
        Optional<Question> questionOptional = questionRepository.findById(questionId);
        if(questionOptional.isPresent()){
            Question question = questionOptional.get();
            question.setAcceptedNum(question.getAcceptedNum() + 1);
            questionRepository.save(question);
        }
    }

    @Override
    public Question queryQuestionById(Long questionId) {
        Optional<Question> questionOpt = questionRepository.findById(questionId);

        return questionOpt.orElse(null);
    }

    @Override
    public void updateQuestion(Question question) {
        questionRepository.save(question);
    }

    @Override
    public TestCase queryTestcase(Long questionId) {

        return testCaseRepository.queryTestCaseByQuestionId(questionId);
    }


}
