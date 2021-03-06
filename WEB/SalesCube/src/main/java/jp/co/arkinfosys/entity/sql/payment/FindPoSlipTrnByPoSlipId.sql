SELECT
	P.PO_SLIP_ID,
	P.PO_DATE,
	P.SUPPLIER_CODE,
	P.SUPPLIER_NAME,
	SP_MST.RATE_ID,
	SP_MST.TAX_SHIFT_CATEGORY,
	SP_MST.TAX_FRACT_CATEGORY,
	SP_MST.PRICE_FRACT_CATEGORY
FROM
    PO_SLIP_TRN_/*$domainId*/ P
		LEFT OUTER JOIN SUPPLIER_MST_/*$domainId*/ SP_MST ON P.SUPPLIER_CODE = SP_MST.SUPPLIER_CODE
WHERE
    P.PO_SLIP_ID = /*poSlipId*/'default'

