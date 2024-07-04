package com.example.memowebapp.service;



import com.example.memowebapp.model.Memo;
import com.example.memowebapp.model.User;
import com.example.memowebapp.repository.MemoRepository;
import com.example.memowebapp.repository.UserRepository;

import java.util.List;
import java.util.Optional;

public class MemoService {
    private final MemoRepository memoRepository;
    private final UserRepository userRepository;

    public MemoService(MemoRepository memoRepository, UserRepository userRepository) {
        this.memoRepository = memoRepository;
        this.userRepository = userRepository;
    }

    public Memo createMemo(Long userId, String title, String content) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            Memo memo = new Memo();
            memo.setUserId(userId);
            memo.setTitle(title);
            memo.setContent(content);
            return memoRepository.save(memo);
        }
        throw new IllegalArgumentException("User with ID " + userId + " not found.");
    }

    public List<Memo> getMemosByUserId(Long userId) {
        return memoRepository.findByUserId(userId);
    }

    public Optional<Memo> getMemoById(Long id) {
        return memoRepository.findById(id);
    }

    public void deleteMemo(Long id) {
        memoRepository.deleteById(id);
    }
}