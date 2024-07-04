package com.example.memowebapp.repository;

import com.example.memowebapp.model.Memo;

import java.util.List;
import java.util.Optional;

public interface MemoRepository {
    Memo save(Memo memo);
    List<Memo> findByUserId(Long userId);
    Optional<Memo> findById(Long id);
    void deleteById(Long id);
}






//package com.memo.repository;
//
//import com.memo.model.Memo;
//import com.memo.model.User;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//public class MemoRepository {
//    private List<Memo> memos = new ArrayList<>();//空list
//    private Long memoIdCounter = 1L;
//
//    public Memo save(Memo memo) {
//        memo.setId(memoIdCounter++);
//        memos.add(memo);//加入memos list
//        return memo;
//    }
//
//    public Optional<Memo> findById(Long id) {
//        return memos.stream()
//                .filter(memo -> memo.getId().equals(id))
//                .findFirst();
//    }
//
//    public List<Memo> findByUserId(Long userId) {
//        return memos.stream()
//                .filter(memo -> memo.getUserId().equals(userId))
//                .collect(Collectors.toList());//使用 Collectors.toList() 收集符合条件的元素到一个新的 List 中。
//    }
//
//    public void deleteById(Long id) {
//        memos.removeIf(memo -> memo.getId().equals(id));
//    }
//}
