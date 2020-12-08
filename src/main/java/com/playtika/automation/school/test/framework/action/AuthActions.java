package com.playtika.automation.school.test.framework.action;


import com.playtika.automation.school.test.framework.client.AuthFeignClient;
import com.playtika.automation.school.test.framework.pojo.requests.AuthenticateRequest;
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

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AuthActions {

    private final AuthFeignClient authFeignClient;

    public RegisterResponse registerUser(String email, String password) {
        RegisterRequest registerRequest = RegisterRequest.builder().email(email).password(password).build();
        return authFeignClient.register(registerRequest);
    }

    public AuthenticateResponse loginUser(String username, String password, String grant_type) {
        //String grant_type - не нужен
        AuthenticateRequest loginRequest = AuthenticateRequest.builder().userName(username).password(password).grantType(grant_type).build();
        //grant_type - yml
        System.out.println(loginRequest);
        String auth = "Basic dGVzdDpzZWNyZXQ=";
        //26 - yml
        return authFeignClient.login(auth, grant_type, username, password);
    }

    public CreateNoteResponse createNote(String auth, String content1) {
        CreateNoteRequest createNoteRequest = CreateNoteRequest.builder().content(content1).build();
        System.out.println(createNoteRequest);
        return authFeignClient.createNote(auth, createNoteRequest);
    }

    public GetUserNotesResponse[] getAllNotes(String auth) {

        return authFeignClient.getAllNotes(auth);
    }

    public GetNoteByIdResponse getNoteById(String auth, String id) {

        return authFeignClient.getNoteById(auth, id);
    }

    public UpdateNoteResponse updateNote(String auth, String id, String contentUpdate, String version) {
        UpdateNoteRequest updateNoteRequest = UpdateNoteRequest.builder().content(contentUpdate).version(version).build();
        System.out.println(updateNoteRequest);
        return authFeignClient.updateNote(auth, id, updateNoteRequest);
    }

    public DeleteNoteByIdResponse deleteNoteById(String auth, String id) {

        return authFeignClient.deleteNoteById(auth, id);
    }
}