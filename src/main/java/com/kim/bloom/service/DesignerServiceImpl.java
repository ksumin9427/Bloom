package com.kim.bloom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kim.bloom.mapper.DesignerMapper;
import com.kim.bloom.model.DesignerVO;

@Service("DesignerService")
public class DesignerServiceImpl implements DesignerService{

	@Autowired
	private DesignerMapper designerMapper;
	
	@Override
	public void DesignerEnroll(DesignerVO designer) throws Exception {
		designerMapper.DesignerEnroll(designer);
	}

}
