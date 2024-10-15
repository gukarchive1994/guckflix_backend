package guckflix.backend.log;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 요구사항 :
 *
 * 기능1: API가 요청된 횟수 집계할 것임
 * 매 정시 API 불린 횟수를 집계하여 매일 1회 batch 작업으로 db에 INSERT
 * 일자, URI, API 호출 횟수만 수집
 *
 * q. API 호출 횟수를 어디에 저장해서 가지고 있어야 하는가? 만약 블루-그린 배포면 그 내용이 공유되어야 한다. 따라서 db 중에 골라야 한다.
 * API 호출 때마다 횟수를 증가시켜야 하므로 속도도 중요하다
 * a. 빠른 속도인 redis를 사용한다.
 *
 */
@Component
@Aspect
@Slf4j
@RequiredArgsConstructor
public class LogAspect {

    private final LogRedisRepository inMemoryService;

    // 이미지 컨트롤러는 제외. 관리해야 하는 키가 너무 많음
    @Pointcut("execution(* guckflix.backend.controller.*.*(..)) && !execution(* guckflix.backend.controller.ImageController.*(..))")
    public void allController() {
    }

    @Before("allController()")
    public void apiCountLogging() throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String requestURI = request.getRequestURI();
        inMemoryService.addCount(requestURI);
    }

}
