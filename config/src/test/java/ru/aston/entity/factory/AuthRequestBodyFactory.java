package ru.aston.entity.factory;

import lombok.Data;

@Data
public class AuthRequestBodyFactory {

    private String login;

    private String password;

     public static AuthRequestBodyFactory getValidAuthLoginAndPassword(){
         AuthRequestBodyFactory authRequestBodyFactory = new AuthRequestBodyFactory();
         authRequestBodyFactory.setLogin("login");
         authRequestBodyFactory.setPassword("P@ssw0rd123");
         return authRequestBodyFactory;
     }

    public static AuthRequestBodyFactory getAuthBodyWithNotExistedLogin(){
        AuthRequestBodyFactory authRequestBodyFactory = new AuthRequestBodyFactory();
        authRequestBodyFactory.setLogin("not_existed_login");
        authRequestBodyFactory.setPassword("P@ssw0rd123");
        return authRequestBodyFactory;
    }

    public static AuthRequestBodyFactory getAuthBodyWithNotExistedPassword(){
        AuthRequestBodyFactory authRequestBodyFactory = new AuthRequestBodyFactory();
        authRequestBodyFactory.setLogin("login");
        authRequestBodyFactory.setPassword("P@ssw0rd123a123123");
        return authRequestBodyFactory;
    }

    public static AuthRequestBodyFactory getAuthBodyWithInvalidPasswordFormat(){
        AuthRequestBodyFactory authRequestBodyFactory = new AuthRequestBodyFactory();
        authRequestBodyFactory.setLogin("login");
        authRequestBodyFactory.setPassword("123");
        return authRequestBodyFactory;
    }

public static AuthRequestBodyFactory getAuthBodyWithVariablePassword(String password){
        AuthRequestBodyFactory authRequestBodyFactory = new AuthRequestBodyFactory();
        authRequestBodyFactory.setLogin("login");
        authRequestBodyFactory.setPassword(password);
        return authRequestBodyFactory;
    }


}
