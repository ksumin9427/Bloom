package com.kim.bloom.mapper;

import java.util.List;

import com.kim.bloom.model.AuthorVO;
import com.kim.bloom.model.Criteria;

public interface AuthorMapper {
	public void authorEnroll(AuthorVO author);
	public List<AuthorVO> authorGetList(Criteria cri);
}
