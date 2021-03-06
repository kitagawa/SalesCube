/*
 *  Copyright 2009-2010 Ark Information Systems.
 */

package jp.co.arkinfosys.service.purchase;

import java.util.List;
import java.util.Map;

import jp.co.arkinfosys.common.Categories;
import jp.co.arkinfosys.common.Constants;
import jp.co.arkinfosys.common.StringUtil;
import jp.co.arkinfosys.entity.SupplierSlipTrn;
import jp.co.arkinfosys.service.AbstractService;
import jp.co.arkinfosys.service.exception.ServiceException;

import org.seasar.framework.beans.util.BeanMap;

/**
 * 仕入検索サービスクラスです.
 * @author Ark Information Systems
 *
 */
public class SearchPurchaseService extends AbstractService<SupplierSlipTrn> {

	/**
	 *
	 * パラメータ定義クラスです.
	 *
	 */
	public static class Param {
		public static final String SEARCH_TARGET = "searchTarget";
		public static final String SUPPLIER_SLIP_ID = "supplierSlipId";
		public static final String PO_SLIP_ID = "poSlipId";
		public static final String STATUS = "status";
		public static final String USER_NAME = "userName";
		public static final String SUPPLIER_DATE = "supplierDate";
		public static final String SUPPLIER_DATE_FROM = "supplierDateFrom";
		public static final String SUPPLIER_DATE_TO = "supplierDateTo";
		public static final String DELIVERY_PROCESS_CATEGORY = "deliveryProcessCategory";
		public static final String SUPPLIER_CODE = "supplierCode";
		public static final String SUPPLIER_NAME = "supplierName";
		public static final String REMARKS = "remarks";
		public static final String PRODUCT_CODE = "productCode";
		public static final String PRODUCT_ABSTRACT = "productAbstract";
		public static final String PRODUCT1 = "product1";
		public static final String PRODUCT2 = "product2";
		public static final String PRODUCT3 = "product3";
		public static final String SORT_COLUMN = "sortColumn";
		public static final String SORT_ORDER_ASC = "sortOrderAsc";
		public static final String ROW_COUNT = "rowCount";
		public static final String OFFSET_ROW = "offsetRow";
		public static final String SUPPLIER_SLIP_CATEGORY = "supplierSlipCategory";
		public static final String SUPPLIER_CM_CATEGORY = "supplierCmCategory";
		public static final String SUPPLIER_DETAIL_CATEGORY = "supplierDetailCategory";
		public static final String DELIVERY_PROCESS_CATEGORY_MST = "deliveryProcessCategoryMst";
		public static final String TEMP_UNIT_PRICE_CATEGORY = "tempUnitPriceCategory";
		public static final String TAX_CATEGORY = "taxCategory";
		public static final String SORT_COLUMN_SLIP = "sortColumnSlip";
		public static final String SORT_COLUMN_LINE = "sortColumnLine";
	}

	/**
	 *
	 * カラム定義クラスです.
	 *
	 */
	public static class Column {
		public static final String SUPPLIER_SLIP_ID = "SUPPLIER_SLIP_ID";
		public static final String PAYMENT_SLIP_ID = "PAYMENT_SLIP_ID";
		public static final String PO_SLIP_ID = "PO_SLIP_ID";

		public static final String SORT_SUPPLIER_SLIP_ID = "SORT_SUPPLIER_SLIP_ID";
		public static final String SORT_SUPPLIER_LINE_NO = "SORT_SUPPLIER_LINE_NO";
		public static final String SORT_PAYMENT_SLIP_ID = "SORT_PAYMENT_SLIP_ID";
		public static final String SORT_PAYMENT_LINE_NO = "SORT_PAYMENT_LINE_NO";
		public static final String SORT_PO_SLIP_ID = "SORT_PO_SLIP_ID";
		public static final String SORT_PO_LINE_NO = "SORT_PO_LINE_NO";
	}

	/**
	 * 検索条件を指定して結果件数を返します.
	 * @param params 検索条件
	 * @return 結果件数
	 * @throws ServiceException
	 */
	public Integer getSearchResultCount(BeanMap params) throws ServiceException {
		try {
			Integer count = Integer.valueOf(0);

			
			String searchTarget = (String) params.get(Param.SEARCH_TARGET);

			
			if (Constants.SEARCH_TARGET.VALUE_SLIP.equals(searchTarget)) {
				count = findSlipCntByCondition(params);
			} else if (Constants.SEARCH_TARGET.VALUE_LINE.equals(searchTarget)) {
				count = findSlipLineCntByCondition(params);
			}

			return count;
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 検索条件を指定して結果リストを返します.
	 * @param params 検索条件
	 * @return 結果リスト
	 * @throws ServiceException
	 */
	public List<BeanMap> getSearchResult(BeanMap params)
			throws ServiceException {
		try {
			
			String searchTarget = (String) params.get(Param.SEARCH_TARGET);

			
			if (Constants.SEARCH_TARGET.VALUE_SLIP.equals(searchTarget)) {
				return findSlipByCondition(params);
			} else if (Constants.SEARCH_TARGET.VALUE_LINE.equals(searchTarget)) {
				return findSlipLineByCondition(params);
			}

			return null;
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 検索条件を指定して伝票単位の結果件数を返します.
	 *
	 * @param conditions 検索条件
	 * @return 結果件数
	 * @throws ServiceException
	 */
	public Integer findSlipCntByCondition(Map<String, Object> conditions)
			throws ServiceException {
		try {
			Map<String, Object> param = super.createSqlParam();
			setEmptyCondition(param);
			setConditionParam(conditions, param);

			return this.selectBySqlFile(Integer.class,
					"purchase/FindSlipCntByCondition.sql", param)
					.getSingleResult();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 検索条件を指定して明細行単位の結果件数を返します.
	 * @param conditions 検索条件
	 * @return 結果件数
	 * @throws ServiceException
	 */
	public Integer findSlipLineCntByCondition(Map<String, Object> conditions)
			throws ServiceException {
		try {
			Map<String, Object> param = super.createSqlParam();
			setEmptyCondition(param);
			setConditionParam(conditions, param);

			return this.selectBySqlFile(Integer.class,
					"purchase/FindSlipLineCntByCondition.sql", param)
					.getSingleResult();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 検索条件を指定して結果リストを返します.
	 *
	 * @param conditions 検索条件
	 * @return 結果リスト
	 * @throws ServiceException
	 */
	public List<BeanMap> findSlipByCondition(Map<String, Object> conditions)
			throws ServiceException {
		try {
			Map<String, Object> param = super.createSqlParam();
			setEmptyCondition(param);
			setConditionParam(conditions, param);

			return this.selectBySqlFile(BeanMap.class,
					"purchase/FindSlipByCondition.sql", param)
					.getResultList();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 検索条件を指定して結果リストを返します.
	 *
	 * @param conditions 検索条件
	 * @return 結果リスト
	 * @throws ServiceException
	 */
	public List<BeanMap> findSlipLineByCondition(Map<String, Object> conditions)
			throws ServiceException {
		try {
			Map<String, Object> param = super.createSqlParam();
			setEmptyCondition(param);
			setConditionParam(conditions, param);

			
			String sortColumn = (String)param.get(Param.SORT_COLUMN);
			if (Column.SUPPLIER_SLIP_ID.equals(sortColumn)) {
				
				param.put(Param.SORT_COLUMN_SLIP, Column.SORT_SUPPLIER_SLIP_ID);
				param.put(Param.SORT_COLUMN_LINE, Column.SORT_SUPPLIER_LINE_NO);
				param.put(Param.SORT_COLUMN, null);
			}else if(Column.PO_SLIP_ID.equals(sortColumn)){
				
				param.put(Param.SORT_COLUMN_SLIP, Column.SORT_PO_SLIP_ID);
				param.put(Param.SORT_COLUMN_LINE, Column.SORT_PO_LINE_NO);
				param.put(Param.SORT_COLUMN, null);
			}else if(Column.PAYMENT_SLIP_ID.equals(sortColumn)){
				
				param.put(Param.SORT_COLUMN_SLIP, Column.SORT_PAYMENT_SLIP_ID);
				param.put(Param.SORT_COLUMN_LINE, Column.SORT_PAYMENT_LINE_NO);
				param.put(Param.SORT_COLUMN, null);
			}

			return this.selectBySqlFile(BeanMap.class,
					"purchase/FindSlipLineByCondition.sql", param)
					.getResultList();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 検索条件を受け取り、初期化して返します.
	 *
	 * @param param 検索条件オブジェクト
	 * @return 空の検索条件オブジェクト
	 */
	private Map<String, Object> setEmptyCondition(Map<String, Object> param) {
		param.put(Param.SEARCH_TARGET, null);
		param.put(Param.SUPPLIER_SLIP_ID, null);
		param.put(Param.PO_SLIP_ID, null);
		param.put(Param.STATUS, null);
		param.put(Param.USER_NAME, null);
		param.put(Param.SUPPLIER_DATE_FROM, null);
		param.put(Param.SUPPLIER_DATE_TO, null);
		param.put(Param.DELIVERY_PROCESS_CATEGORY, null);
		param.put(Param.SUPPLIER_CODE, null);
		param.put(Param.SUPPLIER_NAME, null);
		param.put(Param.REMARKS, null);
		param.put(Param.PRODUCT_CODE, null);
		param.put(Param.PRODUCT_ABSTRACT, null);
		param.put(Param.PRODUCT1, null);
		param.put(Param.PRODUCT2, null);
		param.put(Param.PRODUCT3, null);
		param.put(Param.SORT_COLUMN, null);
		param.put(Param.SORT_ORDER_ASC, null);
		param.put(Param.ROW_COUNT, null);
		param.put(Param.OFFSET_ROW, null);
		param.put(Param.SUPPLIER_SLIP_CATEGORY, Integer
				.valueOf(Categories.SUPPLIER_SLIP_CATEGORY));
		param.put(Param.SUPPLIER_CM_CATEGORY, Integer
				.valueOf(Categories.SUPPLIER_CM_CATEGORY));
		param.put(Param.SUPPLIER_DETAIL_CATEGORY, Integer
				.valueOf(Categories.PURCHASE_DETAIL));
		param.put(Param.DELIVERY_PROCESS_CATEGORY_MST, Integer
				.valueOf(Categories.DELIVERY_PROCESS_CATEGORY));
		param.put(Param.TEMP_UNIT_PRICE_CATEGORY, Integer
				.valueOf(Categories.TEMP_UNIT_PRICE_CATEGORY));
		param.put(Param.TAX_CATEGORY, Integer.valueOf(Categories.TAX_CATEGORY));
		param.put(Param.SORT_COLUMN_SLIP, null);
		param.put(Param.SORT_COLUMN_LINE, null);
		return param;
	}

	/**
	 * 検索条件パラメータを設定して返します.
	 * @param conditions 検索条件
	 * @param param 検索条件パラメータ
	 * @return 検索条件が設定された検索条件パラメータ
	 */
	private Map<String, Object> setConditionParam(
			Map<String, Object> conditions, Map<String, Object> param) {

		
		if (conditions.containsKey(Param.SUPPLIER_SLIP_ID)) {
			if (StringUtil.hasLength((String) conditions
					.get(Param.SUPPLIER_SLIP_ID))) {
				param.put(Param.SUPPLIER_SLIP_ID, new Long((String) conditions
						.get(Param.SUPPLIER_SLIP_ID)));
			}
		}

		
		if (conditions.containsKey(Param.STATUS)) {
			if ((conditions.get(Param.STATUS) != null)) {
				param.put(Param.STATUS, conditions.get(Param.STATUS));
			}
		}

		
		if (conditions.containsKey(Param.PO_SLIP_ID)) {
			if (StringUtil.hasLength((String) conditions.get(Param.PO_SLIP_ID))) {
				param.put(Param.PO_SLIP_ID, new Long((String) conditions
						.get(Param.PO_SLIP_ID)));
			}
		}

		
		if (conditions.containsKey(Param.USER_NAME)) {
			if (StringUtil.hasLength((String) conditions.get(Param.USER_NAME))) {
				param.put(Param.USER_NAME, super
						.createPartialSearchCondition((String) conditions
								.get(Param.USER_NAME)));
			}
		}

		
		if (conditions.containsKey(Param.SUPPLIER_DATE_FROM)) {
			if (StringUtil.hasLength((String) conditions
					.get(Param.SUPPLIER_DATE_FROM))) {
				param.put(Param.SUPPLIER_DATE_FROM, (String) conditions
						.get(Param.SUPPLIER_DATE_FROM));
			}
		}

		
		if (conditions.containsKey(Param.SUPPLIER_DATE_TO)) {
			if (StringUtil.hasLength((String) conditions
					.get(Param.SUPPLIER_DATE_TO))) {
				param.put(Param.SUPPLIER_DATE_TO, (String) conditions
						.get(Param.SUPPLIER_DATE_TO));
			}
		}

		
		if (conditions.containsKey(Param.DELIVERY_PROCESS_CATEGORY)) {
			Object obj = conditions.get(Param.DELIVERY_PROCESS_CATEGORY);
			if (obj != null) {
				String[] arry = (String[]) obj;
				if (arry.length > 0) {
					param.put(Param.DELIVERY_PROCESS_CATEGORY, arry);
				}
			}
		}

		
		if (conditions.containsKey(Param.SUPPLIER_CODE)) {
			if (StringUtil.hasLength((String) conditions
					.get(Param.SUPPLIER_CODE))) {
				param.put(Param.SUPPLIER_CODE, super
						.createPrefixSearchCondition((String) conditions
								.get(Param.SUPPLIER_CODE)));
			}
		}

		
		if (conditions.containsKey(Param.SUPPLIER_NAME)) {
			if (StringUtil.hasLength((String) conditions
					.get(Param.SUPPLIER_NAME))) {
				param.put(Param.SUPPLIER_NAME, super
						.createPartialSearchCondition((String) conditions
								.get(Param.SUPPLIER_NAME)));
			}
		}

		
		if (conditions.containsKey(Param.REMARKS)) {
			if (StringUtil.hasLength((String) conditions.get(Param.REMARKS))) {
				param.put(Param.REMARKS, super
						.createPartialSearchCondition((String) conditions
								.get(Param.REMARKS)));
			}
		}

		
		if (conditions.containsKey(Param.PRODUCT_CODE)) {
			if (StringUtil.hasLength((String) conditions
					.get(Param.PRODUCT_CODE))) {
				param.put(Param.PRODUCT_CODE, super
						.createPrefixSearchCondition((String) conditions
								.get(Param.PRODUCT_CODE)));
			}
		}

		
		if (conditions.containsKey(Param.PRODUCT_ABSTRACT)) {
			if (StringUtil.hasLength((String) conditions
					.get(Param.PRODUCT_ABSTRACT))) {
				param.put(Param.PRODUCT_ABSTRACT, super
						.createPartialSearchCondition((String) conditions
								.get(Param.PRODUCT_ABSTRACT)));
			}
		}

		
		if (conditions.containsKey(Param.PRODUCT1)) {
			if (StringUtil.hasLength((String) conditions.get(Param.PRODUCT1))) {
				param.put(Param.PRODUCT1, conditions.get(Param.PRODUCT1));
			}
		}

		
		if (conditions.containsKey(Param.PRODUCT2)) {
			if (StringUtil.hasLength((String) conditions.get(Param.PRODUCT2))) {
				param.put(Param.PRODUCT2, conditions.get(Param.PRODUCT2));
			}
		}

		
		if (conditions.containsKey(Param.PRODUCT3)) {
			if (StringUtil.hasLength((String) conditions.get(Param.PRODUCT3))) {
				param.put(Param.PRODUCT3, conditions.get(Param.PRODUCT3));
			}
		}

		
		if (conditions.containsKey(Param.SORT_COLUMN)) {
			if (StringUtil
					.hasLength((String) conditions.get(Param.SORT_COLUMN))) {
				param.put(Param.SORT_COLUMN, StringUtil
						.convertColumnName((String) conditions
								.get(Param.SORT_COLUMN)));
			}
		}

		
		Boolean sortOrderAsc = (Boolean) conditions.get(Param.SORT_ORDER_ASC);
		if (sortOrderAsc) {
			param.put(Param.SORT_ORDER_ASC, Constants.SQL.ASC);
		} else {
			param.put(Param.SORT_ORDER_ASC, Constants.SQL.DESC);
		}

		
		if (conditions.containsKey(Param.ROW_COUNT)) {
			param.put(Param.ROW_COUNT, conditions
					.get(Param.ROW_COUNT));
		}

		
		if (conditions.containsKey(Param.OFFSET_ROW)) {
			param.put(Param.OFFSET_ROW, conditions.get(Param.OFFSET_ROW));
		}

		return param;
	}
}
