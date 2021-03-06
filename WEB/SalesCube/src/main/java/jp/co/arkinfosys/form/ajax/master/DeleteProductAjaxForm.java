/*
 *  Copyright 2009-2010 Ark Information Systems.
 */

package jp.co.arkinfosys.form.ajax.master;

import org.seasar.struts.annotation.Required;

/**
 *  商品マスタ管理（検索）画面の削除処理のアクションフォームクラスです.
 * @author Ark Information Systems
 *
 */
public class DeleteProductAjaxForm {

	@Required
	public String productCode;

	@Required
	public String updDatetm;

	public String discountUpdDatetm;

}
