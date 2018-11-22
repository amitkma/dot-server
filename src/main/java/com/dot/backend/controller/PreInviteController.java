package com.dot.backend.controller;

import com.dot.backend.domain.PreInvite;
import com.dot.backend.repository.PreInviteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@RestController
public class PreInviteController {

    @Autowired
    PreInviteRepository preInviteRepository;

    @PostMapping("/invite")
    public void preInvite(@RequestBody PreInvite preInvite){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        preInvite.ip = request.getRemoteAddr();
        preInviteRepository.insert(preInvite);
    }
}
