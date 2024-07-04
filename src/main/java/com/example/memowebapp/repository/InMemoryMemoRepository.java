package com.example.memowebapp.repository;

import com.example.memowebapp.model.Memo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryMemoRepository implements com.example.memowebapp.repository.MemoRepository {
    private List<Memo> memos = new ArrayList<>();
    private Long memoIdCounter = 1L;

    @Override
    public Memo save(Memo memo) {
        memo.setId(memoIdCounter++);
        memos.add(memo);
        return memo;
    }

    @Override
    public List<Memo> findByUserId(Long userId) {
        List<Memo> userMemos = new ArrayList<>();
        for (Memo memo : memos) {
            if (memo.getUserId().equals(userId)) {
                userMemos.add(memo);
            }
        }
        return userMemos;
    }

    @Override
    public Optional<Memo> findById(Long id) {
        return memos.stream()
                .filter(memo -> memo.getId().equals(id))
                .findFirst();
    }

    @Override
    public void deleteById(Long id) {
        memos.removeIf(memo -> memo.getId().equals(id));
    }
}
