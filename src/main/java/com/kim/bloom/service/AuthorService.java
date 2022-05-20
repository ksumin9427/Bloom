package com.kim.bloom.service;

import java.util.List;

import com.kim.bloom.model.AuthorVO;
import com.kim.bloom.model.Criteria;

public interface AuthorService {
	
	public void authorEnroll(AuthorVO author) throws Exception;
	
	public List<AuthorVO> authorGetList(Criteria cri) throws Exception;
	
	public int authorGetTotal(Criteria cri) throws Exception;
	
	public AuthorVO authorGetDetail(int authorId) throws Exception;
	
}
