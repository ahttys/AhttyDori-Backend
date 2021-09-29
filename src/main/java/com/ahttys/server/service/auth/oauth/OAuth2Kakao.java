package com.ahttys.server.service.auth.oauth;

import com.ahttys.server.dto.auth.KakaoAuthResponseDto;
import com.ahttys.server.dto.auth.KakaoUserInfoDto;
import com.ahttys.server.util.error.exceptions.CustomException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class OAuth2Kakao {
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Value("${OAuth.kakao.client_id}")
    private String clientId;
    @Value("${OAuth.kakao.redirect_uri}")
    private String redirectURI;

    public KakaoAuthResponseDto getKakaoAuthByCode(String code) {
        String url = "https://kauth.kakao.com/oauth/token";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", clientId);
        params.add("redirect_uri", redirectURI);
        params.add("code", code);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, httpHeaders);
        try {
            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
            return objectMapper.readValue(response.getBody(), KakaoAuthResponseDto.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException("통신에 실패했습니다.", 400);
        }
    }

    public KakaoUserInfoDto getUserInfoByToken(String token) {
        String url = "https://kapi.kakao.com/v2/user/me";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        httpHeaders.set("Authorization", "Bearer " + token);
        HttpEntity<String> request = new HttpEntity<>(httpHeaders);
        try {
            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
            return objectMapper.readValue(response.getBody(), KakaoUserInfoDto.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException("인증이 유효하지 않습니다.", 401);
        }
    }

}
