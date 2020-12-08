package com.playtika.automation.school.test.framework.client;

import com.playtika.automation.school.test.framework.pojo.requests.CreateNoteRequest;
import com.playtika.automation.school.test.framework.pojo.requests.RegisterRequest;
import com.playtika.automation.school.test.framework.pojo.requests.UpdateNoteRequest;
import com.playtika.automation.school.test.framework.pojo.responses.AuthenticateResponse;
import com.playtika.automation.school.test.framework.pojo.responses.CreateNoteResponse;
import com.playtika.automation.school.test.framework.pojo.responses.DeleteNoteByIdResponse;
import com.playtika.automation.school.test.framework.pojo.responses.GetNoteByIdResponse;
import com.playtika.automation.school.test.framework.pojo.responses.GetUserNotesResponse;
import com.playtika.automation.school.test.framework.pojo.responses.RegisterResponse;
import com.playtika.automation.school.test.framework.pojo.responses.UpdateNoteResponse;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "auth-client",
        url = "https://taschool-notes-service.herokuapp.com"
        //урл должен быть в аплл ямл.перенести
)
public interface AuthFeignClient {
    @PostMapping(path = "/v1/accounts")
    RegisterResponse register(@RequestBody RegisterRequest request);

    @PostMapping(path = "/oauth/token", consumes = "application/x-www-form-urlencoded")
    AuthenticateResponse login(@RequestHeader("Authorization") String authorization,
                               @RequestParam("grant_type") String grantType,
                               @RequestParam("username") String userName,
                               @RequestParam("password") String password);

    @PostMapping(path = "v1/notes")
    CreateNoteResponse createNote(@RequestHeader("authorization") String authorization,
                                  @RequestBody CreateNoteRequest content);

    @GetMapping(path = "v1/notes")
    GetUserNotesResponse[] getAllNotes(@RequestHeader("authorization") String authorization);

    @GetMapping(path = "v1/notes/{id}")
    GetNoteByIdResponse getNoteById(@RequestHeader("authorization") String authorization,
                                    @RequestParam("id") String id);

    @PutMapping(path = "v1/notes/{id}", consumes = "application/json")
    UpdateNoteResponse updateNote(@RequestHeader("authorization") String authorization,
                                  @RequestParam("id") String id,
                                  @RequestBody UpdateNoteRequest content);

    @DeleteMapping(path = "v1/notes/{id}")
    DeleteNoteByIdResponse deleteNoteById(@RequestHeader("authorization") String authorization,
                                          @RequestParam("id") String id);
}