package com.tbk.ymm.service.impl;

import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.tbk.ymm.commons.dto.YmmCateBarDTO;
import com.tbk.ymm.commons.enums.CateType;
import com.tbk.ymm.commons.model.YmmNavigationCate;
import com.tbk.ymm.dao.cate.YmmItemCateDAO;
import com.tbk.ymm.dao.cate.YmmNavigationCateDAO;
import com.tbk.ymm.data.catcher.commons.model.YmmItemCate;
import com.tbk.ymm.service.YmmCateService;
import com.tbk.ymm.utils.cate.YmmCateUtil;
import com.tbk.ymm.utils.collections.CollectionUtils;

@Service
public class YmmCateServiceImpl implements YmmCateService {

	private final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private YmmNavigationCateDAO ymmNavigationCateDAO;
	@Autowired
	private YmmItemCateDAO ymmItemCateDAO;

	@Override
	public YmmCateBarDTO getCateBar(long inputCateId) {
		YmmCateBarDTO ymmCateBarDTO = new YmmCateBarDTO();
		//
		ymmCateBarDTO.setInputCateId(inputCateId);
		ymmCateBarDTO.setInputCateType(CateType.NAVIGATION); // 默认
		//
		List<YmmNavigationCate> navigationList = this.getNavigationCateList();
		ymmCateBarDTO.setNavigationList(navigationList);
		//
		// 目标：获得导航navigationId，查询出一级、二级cid，并标记被选中的属性
		//
		long navigationId = inputCateId;
		long lv1Cid = 0;
		long lv2Cid = 0;
		if (!YmmCateUtil.isNavigationCate(inputCateId)) {
			lv1Cid = inputCateId;
			ymmCateBarDTO.setInputCateType(CateType.LV1);
			if (!isLv1Cate(inputCateId)) {
				lv1Cid = this.getParentCid(inputCateId);
				lv2Cid = inputCateId;
				ymmCateBarDTO.setInputCateType(CateType.LV2);
			}
			navigationId = getNavigationIdByLv1Cid(lv1Cid, navigationList);
		}
		//
		YmmNavigationCate navCate = setNavSelected(navigationList, navigationId);
		if (null == navCate) {
			return ymmCateBarDTO;
		}
		ymmCateBarDTO.setCurNavCate(navCate);
		List<YmmItemCate> lv1CateList = getItemCateListByIds(navCate.getLv1ItemCateIdlist());
		this.setCateSelected(ymmCateBarDTO, lv1CateList, lv1Cid, CateType.LV1);
		ymmCateBarDTO.setLv1CateList(lv1CateList);
		//
		List<YmmItemCate> lv2CateList = this.getLv2SubCateList(navCate);
		this.setCateSelected(ymmCateBarDTO, lv2CateList, lv2Cid, CateType.LV2);
		//
		ymmCateBarDTO.setLv2CateList(lv2CateList);
		//
		logger.info("YmmCateServiceImpl.YmmCateBarDTO.Simple YmmCateBarDTO:" + ymmCateBarDTO.toStringSimple());
		//
		return ymmCateBarDTO;
	}

	@Override
	public List<YmmNavigationCate> getNavigationCateList() {
		// TODO cache
		return ymmNavigationCateDAO.getAllOK();
	}

	@Override
	public List<YmmCateBarDTO> getAllCate(List<YmmNavigationCate> navCateList) {
		List<YmmNavigationCate> localNavCateList = navCateList;
		if (CollectionUtils.isCollectionEmpty(localNavCateList)) {
			localNavCateList = this.getNavigationCateList();
		}
		//
		List<YmmCateBarDTO> dtoList = Lists.newArrayList();
		//
		for (YmmNavigationCate ymmNavigationCate : localNavCateList) {
			YmmCateBarDTO ymmCateBarDTO = this.getCateBar(ymmNavigationCate.getId());
			dtoList.add(ymmCateBarDTO);
		}
		return dtoList;
	}

	// --------------------------------------------------------

	/**
	 * @param ymmNavigationCate
	 * @return
	 */
	private List<YmmItemCate> getLv2SubCateList(YmmNavigationCate ymmNavigationCate) {
		// TODO cache
		List<Long> cidList = ymmNavigationCate.getLv1ItemCateIdlist();
		List<YmmItemCate> allSubCateList = Lists.newArrayList();
		for (Long cid : cidList) {
			if (null == cid) {
				continue;
			}
			//
			List<YmmItemCate> subCateList = ymmItemCateDAO.getSubItemCateList(cid);
			allSubCateList.addAll(subCateList);
		}
		return allSubCateList;
	}

	/**
	 * @param cid
	 * @return
	 */
	private long getParentCid(long cid) {
		Long pid = ymmItemCateDAO.getParentCid(cid);
		if (null == pid) {
			return 0;
		}
		return pid;
	}

	/**
	 * 挑选出当前的导航分类，并设置它被选中的标记
	 * 
	 * @param navigationList
	 * @param cateId
	 * @return
	 */
	private YmmNavigationCate setNavSelected(List<YmmNavigationCate> navigationList, long cateId) {
		for (YmmNavigationCate ymmNavigationCate : navigationList) {
			if (ymmNavigationCate.getId() == cateId) {
				ymmNavigationCate.buildSelected(true);
				return ymmNavigationCate;
			}
		}
		return null;
	}

	/**
	 * @param ymmCateBarDTO
	 * @param cateList
	 * @param cateId
	 * @param cateType
	 */
	private void setCateSelected(YmmCateBarDTO ymmCateBarDTO, List<YmmItemCate> cateList,
			long cateId, CateType cateType) {
		for (YmmItemCate ymmItemCate : cateList) {
			if (null != ymmItemCate && null != ymmItemCate.getCid() && ymmItemCate.getCid() == cateId) {
				ymmItemCate.buildSelected(true);
				if (CateType.LV1.equals(cateType)) {
					ymmCateBarDTO.setCurLv1Cate(ymmItemCate);
				} else if (CateType.LV2.equals(cateType)) {
					ymmCateBarDTO.setCurLv2Cate(ymmItemCate);
				}
				return;
			}
		}
	}

	/**
	 * @param itemCateIdlist
	 * @return
	 */
	private List<YmmItemCate> getItemCateListByIds(List<Long> itemCateIdlist) {
		// TODO cache
		return ymmItemCateDAO.getByCids(itemCateIdlist);
	}

	/**
	 * 根据一级cid获取导航id
	 * 
	 * @param lv1Cid
	 * @param navigationList
	 * @return
	 */
	private long getNavigationIdByLv1Cid(long lv1Cid, List<YmmNavigationCate> navigationList) {
		for (YmmNavigationCate ymmNavigationCate : navigationList) {
			List<Long> lv1CateIdList = ymmNavigationCate.getLv1ItemCateIdlist();
			if (null != lv1CateIdList && lv1CateIdList.contains(lv1Cid)) {
				return ymmNavigationCate.getId();
			}
		}
		return 0;
	}

	/**
	 * 是否一级cid
	 * 
	 * @param inputCateId
	 * @return
	 */
	private boolean isLv1Cate(long inputCateId) {
		Set<Long> allLv1CidSet = ymmItemCateDAO.getAllLv1CidSet();
		if (null != allLv1CidSet && allLv1CidSet.contains(inputCateId)) {
			return true;
		}
		return false;
	}

}
