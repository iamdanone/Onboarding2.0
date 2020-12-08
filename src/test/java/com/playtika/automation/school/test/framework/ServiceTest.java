package com.playtika.automation.school.test.framework;

import java.util.Arrays;

import com.playtika.automation.school.test.framework.action.AuthActions;
import com.playtika.automation.school.test.framework.configuration.AuthConfiguration;
import com.playtika.automation.school.test.framework.pojo.responses.AuthenticateResponse;
import com.playtika.automation.school.test.framework.pojo.responses.CreateNoteResponse;
import com.playtika.automation.school.test.framework.pojo.responses.DeleteNoteByIdResponse;
import com.playtika.automation.school.test.framework.pojo.responses.GetNoteByIdResponse;
import com.playtika.automation.school.test.framework.pojo.responses.GetUserNotesResponse;
import com.playtika.automation.school.test.framework.pojo.responses.RegisterResponse;
import com.playtika.automation.school.test.framework.pojo.responses.UpdateNoteResponse;

import feign.FeignException;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest(classes = AuthConfiguration.class)

public class ServiceTest {

    @Autowired
    private AuthActions authActions;
    private String id;

    @Test
    public void registerUser() {
        //generate random email and password
        String email = generateRandomEmail();
        String password = generateRandomPassword();
        String grantType = "password";
        RegisterResponse registerResponse = authActions.registerUser(email, password);
        System.out.println(registerResponse);

        //authenticate user
        AuthenticateResponse authenticateResponse = authActions.loginUser(email, password, grantType);
        System.out.println(authenticateResponse);

        //create a note with any content
        String auth = "Bearer " + authenticateResponse.getAccessToken();
        String content1 = " First Note ";
        CreateNoteResponse createNoteResponse = authActions.createNote(auth, content1);
        String firstId = createNoteResponse.getId();
        String version = createNoteResponse.getVersion();
        System.out.println(createNoteResponse);
        System.out.println(version);

        //get list of notes and assert it has size equals to one
        GetUserNotesResponse[] getUserNotesResponse = authActions.getAllNotes(auth);
        System.out.println(Arrays.toString(getUserNotesResponse));

        //create a note with any content
        String secondContent = " Second Note ";
        CreateNoteResponse createSecondNoteResponse = authActions.createNote(auth, secondContent);
        String secondId = createSecondNoteResponse.getId();
        System.out.println(createSecondNoteResponse);

        //get list of notes and assert it has size equals to one
        getUserNotesResponse = authActions.getAllNotes(auth);
        System.out.println(Arrays.toString(getUserNotesResponse));

        //get first note by id and assert it's the same as you've created
        id = getUserNotesResponse[0].id + "";
        GetNoteByIdResponse getNoteByIdResponse = authActions.getNoteById(auth, id);
        System.out.println(getNoteByIdResponse);

        //update first note with any new content
        String contentUpdate = " First Note - Updated ";
        UpdateNoteResponse updateNoteResponse = authActions.updateNote(auth, firstId, contentUpdate, version);
        System.out.println(updateNoteResponse);
        System.out.println(" First Note - Updated ");

        //get list of notes. Use stream to filter list by id of note and get updated one.
        id = createNoteResponse.getId() + "";
        getUserNotesResponse= authActions.getAllNotes(auth);
        System.out.println(" Get Second Note ");
        System.out.println(Arrays.toString(getUserNotesResponse));

        //delete first note
        DeleteNoteByIdResponse deleteNoteById = authActions.deleteNoteById(auth, firstId);
        System.out.println(deleteNoteById);
        System.out.println(" Delete First Note ");

        //get list of notes and assert it has size equal to one and it doesn't contain updated note
        getUserNotesResponse = authActions.getAllNotes(auth);
        System.out.println((getUserNotesResponse.length == 1));

        //delete first note
        Exception exception = assertThrows(FeignException.class, () -> { authActions.getNoteById(auth, id); });
        exception.getMessage();
        System.out.println(exception.getMessage());

        //delete second note
        deleteNoteById = authActions.deleteNoteById(auth, secondId);
        System.out.println(deleteNoteById);
        System.out.println(" Delete Second Note ");
    }

    private String generateRandomPassword() {
        int count = 10;
        return String.format("%s", RandomStringUtils.randomAlphanumeric(count));
    }

    private String generateRandomEmail() {
        String domain = "@gmail.com";
        return String.format("%s%s", RandomStringUtils.randomAlphanumeric(10), domain);
    }
}