package com.example.memowebapp;


import com.example.memowebapp.dao.UserDAO;
import com.example.memowebapp.model.Memo;
import com.example.memowebapp.model.User;
import com.example.memowebapp.repository.InMemoryMemoRepository;
import com.example.memowebapp.repository.MemoRepository;
import com.example.memowebapp.repository.UserRepository;
import com.example.memowebapp.service.MemoService;

import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();



        User newuser = new User();
        newuser.setUsername("testuser");
        newuser.setPassword("testpassword");
        userDAO.addUser(newuser);

        for (User user : userDAO.getAllUsers()) {
            System.out.println(user);
        }

    }
}
