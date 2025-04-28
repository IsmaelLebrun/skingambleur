package com.example.web_app.controller;

import com.example.web_app.model.SkinportSkin;
import com.example.web_app.model.dto.SkinDTO;
import com.example.web_app.service.SkinCacheManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.jni.Buffer;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/skins")
public class SkinportController {


}
