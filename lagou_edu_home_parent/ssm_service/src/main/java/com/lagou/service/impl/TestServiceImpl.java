package com.lagou.service.impl;

import com.lagou.dao.TestMappper;
import com.lagou.domain.Test;
import com.lagou.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestMappper testMappper;

    @Override
    public List<Test> findAllTest() {
        return testMappper.findAllTest();
    }
}
