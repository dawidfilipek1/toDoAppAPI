package com.gemini.todolistapi.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Task {
    public int idx;
    public String task;
    public LocalDate date;
}
