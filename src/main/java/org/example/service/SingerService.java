package org.example.service;

import org.example.repository.SingerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public class SingerService {

    SingerRepository singerRepository;

    public SingerService(SingerRepository singerRepository) {
        this.singerRepository = singerRepository;
    }

    public String someSay() {
        return "в багдаде все спокойно";
    }
}
