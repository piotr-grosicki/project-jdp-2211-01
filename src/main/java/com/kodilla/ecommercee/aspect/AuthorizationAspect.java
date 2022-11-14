package com.kodilla.ecommercee.aspect;

import com.kodilla.ecommercee.service.SecurityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class AuthorizationAspect {
    private final SecurityService securityService;

    @Pointcut("@within(com.kodilla.ecommercee.annotation.AuthorizeBeforeModifying) && " +
            "(@annotation(org.springframework.web.bind.annotation.PostMapping) ||" +
            "@annotation(org.springframework.web.bind.annotation.PutMapping) ||" +
            "@annotation(org.springframework.web.bind.annotation.PatchMapping) ||" +
            "@annotation(org.springframework.web.bind.annotation.DeleteMapping))")
    public void authorizationForModifying() {
    }

    @Around(value = "authorizationForModifying()")
    public Object authorizeModifying(ProceedingJoinPoint jp) throws Throwable {
        securityService.authorize();

        Object obj = jp.proceed();

        return obj;
    }


    @Pointcut("@annotation(com.kodilla.ecommercee.annotation.AuthorizeBefore)")
    public void authorization() {
    }

    @Around(value = "authorization()")
    public Object authorize(ProceedingJoinPoint jp) throws Throwable {
        securityService.authorize();

        Object obj = jp.proceed();

        return obj;
    }

}