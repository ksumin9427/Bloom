package com.kim.bloom.mapper;

import java.util.List;

import com.kim.bloom.model.AuthorVO;
import com.kim.bloom.model.Criteria;

public interface AuthorMapper {
	
	public void authorEnroll(AuthorVO author);
	
	
	public List<AuthorVO> authorGetList(Criteria cri);
	
	/* 작가의 총 수 */
	public int authorGetTotal(Criteria cri);
	
	public AuthorVO authorGetDetail(int authorId);
	
	public int authorModify(AuthorVO author);
	
	/* 작가 삭제 */
	public int authorDelete(int authorId);
}
