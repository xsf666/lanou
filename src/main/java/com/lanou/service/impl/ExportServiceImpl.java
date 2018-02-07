package com.lanou.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lanou.entity.Contract;
import com.lanou.entity.ContractProduct;
import com.lanou.entity.Export;
import com.lanou.entity.ExportProduct;
import com.lanou.entity.ExtCproduct;
import com.lanou.entity.ExtEproduct;
import com.lanou.mapper.ContractMapper;
import com.lanou.mapper.ContractProductMapper;
import com.lanou.mapper.ExportMapper;
import com.lanou.mapper.ExportProductMapper;
import com.lanou.mapper.ExtCproductMapper;
import com.lanou.mapper.ExtEproductMapper;
import com.lanou.service.ExportService;
import com.lanou.util.UtilFuns;

@Service
public class ExportServiceImpl implements ExportService {
	@Resource
	private ExportMapper exportMapper;
	@Resource
	private ContractMapper contractMapper;
	@Resource
	private ContractProductMapper contractProductMapper;
	@Resource
	private ExportProductMapper exportProductMapper;
	@Resource
	private ExtCproductMapper extCproductMapper;
	@Resource
	private ExtEproductMapper extEproductMapper;

	@Override
	public List<Export> selectAll() {
		// TODO Auto-generated method stub
		return exportMapper.selectAll();
	}

	@Override
	public Export selectByPrimaryKey(String EXPORT_ID) {
		// TODO Auto-generated method stub
		return exportMapper.selectByPrimaryKey(EXPORT_ID);
	}

	@Transactional
	@Override
	public int updateByPrimaryKeySelective(Export record) {
		int row = 0;
		try {
			row = exportMapper.updateByPrimaryKeySelective(record);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return row;
	}

	@Transactional
	@Override
	public int deleteByPrimaryKey(String EXPORT_ID) {
		int row = 0;
		try {
			row = exportMapper.deleteByPrimaryKey(EXPORT_ID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return row;
	}

	@Override
	public void baoyun(String[] ids) {
		// 1.根据合同id查询多个合同的信息
		Export e = new Export();
		// 加,,,,,
		e.setCONTRACT_IDS(UtilFuns.joinStr(ids, ","));
		StringBuffer sb = new StringBuffer();
		for (String s : ids) {
			Contract c = contractMapper.selectByPrimaryKey(s);
			sb.append(c.getCONTRACT_NO() + " ");
			// contractMapper.updateByPrimaryKeySelective(record);
		}
		e.setCUSTOMER_CONTRACT(sb.toString());
		e.setEXPORT_ID(UUID.randomUUID().toString());
		e.setINPUT_DATE(new Date());
		// 默认是草稿
		e.setSTATE((short) 0);
		exportMapper.insertSelective(e);
		// 3.把合同的货物信息进行搬家
		for (String id : ids) {
			List<ContractProduct> cp = contractProductMapper.selectProductByContractID(id);
			for (ContractProduct cpp : cp) {
				ExportProduct ep = new ExportProduct();
				ep.setFACTORY_ID(cpp.getFACTORY_ID());
				ep.setFACTORY_NAME(cpp.getFACTORY_NAME());
				ep.setEX_PRICE(cpp.getPRICE());
				ep.setCNUMBER(cpp.getCNUMBER());
				ep.setPACKING_UNIT(cpp.getPACKING_UNIT());
				ep.setORDER_NO(cpp.getORDER_NO());
				ep.setPRODUCT_NO(cpp.getPRODUCT_NO());
				ep.setBOX_NUM(cpp.getBOX_NUM());
				ep.setEX_PRICE(cpp.getPRICE());
				ep.setEXPORT_ID(e.getEXPORT_ID());

				ep.setEXPORT_PRODUCT_ID(UUID.randomUUID().toString());
				exportProductMapper.insertSelective(ep);
				// 4.把合同的附件信息进行搬家
				// 合同货物Id
				String contractProductId = cpp.getCONTRACT_PRODUCT_ID();

				List<ExtCproduct> list = extCproductMapper.selectExtByProductId(contractProductId);
				for (ExtCproduct ec : list) {
					// 保运附件
					ExtEproduct eproduct = new ExtEproduct();

					eproduct.setEXT_EPRODUCT_ID(UUID.randomUUID().toString());
					eproduct.setEXPORT_PRODUCT_ID(ep.getEXPORT_PRODUCT_ID());
					eproduct.setAMOUNT(ec.getAMOUNT());

					eproduct.setCNUMBER(ec.getCNUMBER());

					eproduct.setFACTORY_ID(ec.getFACTORY_ID());
					eproduct.setFACTORY_NAME(ec.getFACTORY_NAME());
					eproduct.setPACKING_UNIT(ec.getPACKING_UNIT());
					eproduct.setPRICE(ec.getPRICE());
					eproduct.setPRODUCT_NO(ec.getPRODUCT_NO());
					eproduct.setPRODUCT_IMAGE(ec.getPRODUCT_IMAGE());
					eproduct.setPRODUCT_DESC(ec.getPRODUCT_DESC());
					extEproductMapper.insertSelective(eproduct);
				}

			}
		}
	}

	@Override
	public List<Export> BoxUp(String[] ids) {
		List<Export> list = new ArrayList();
		for (String s : ids) {
			Export e = exportMapper.selectByPrimaryKey(s);
			list.add(e);
		}
		return list;
	}

}
