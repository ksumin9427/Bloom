package com.kim.bloom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kim.bloom.mapper.FaqMapper;
import com.kim.bloom.model.CateVO;
import com.kim.bloom.model.Criteria;
import com.kim.bloom.model.FaqVO;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class FaqServiceImpl implements FaqService{
	
	@Autowired
	private FaqMapper faqMapper;
	
	@Override
	public void faqEnroll(FaqVO faq) throws Exception{
		faqMapper.faqEnroll(faq);
	}

	@Override
	public List<FaqVO> getFaqList(Criteria cri) throws Exception {
		return faqMapper.getFaqList(cri);
	}
	
	@Override
	public List<FaqVO> getFaqSearch(Criteria cri) throws Exception {
		String type = cri.getType();
		String[] typeArr = type.split("");
		
		for(String t : typeArr) {
			if(t.equals("C")) {
				String[] fnoCateCodeArr = faqMapper.getFaqCatecodeList(cri.getKeyword());
				cri.setFnoCateCodeArr(fnoCateCodeArr);
			}
		}
		
		return faqMapper.getFaqSearch(cri);
	}
	

	@Override
	public FaqVO getFaqPage(int fno) throws Exception {
		return faqMapper.getFaqPage(fno);
	}

	@Override
	public int faqModify(FaqVO faq) throws Exception {
		return faqMapper.faqModify(faq);
	}

	@Override
	public int faqDelete(int fno) throws Exception {
		return faqMapper.faqDelete(fno);
	}

	@Override
	public List<FaqVO> getListPaging(Criteria cri) throws Exception {
		return faqMapper.getListPaging(cri);
	}

	/*
	 * @Override public int getTotal() throws Exception { return
	 * faqMapper.getTotal(); }
	 */
	
	@Override
	public int getTotal(Criteria cri) throws Exception {
		return faqMapper.getTotal(cri);
	}

	@Override
	public List<CateVO> cateList() throws Exception {
		return faqMapper.cateList();
	}

	

	@Override
	public int getFaqSearchTotal(Criteria cri) throws Exception {
		return faqMapper.getFaqSearchTotal(cri);
	}

	

	

}
