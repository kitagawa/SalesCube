INSERT INTO ENTRUST_EAD_LINE_TRN_/*$domainId*/ (
	ENTRUST_EAD_LINE_ID,
	ENTRUST_EAD_SLIP_ID,
	LINE_NO,
	PRODUCT_CODE,
	PRODUCT_ABSTRACT,
	QUANTITY,
	REMARKS,
	PO_LINE_ID,
	REL_ENTRUST_EAD_LINE_ID,
	ENTRUST_EAD_CATEGORY,
	CRE_FUNC,
	CRE_DATETM,
	CRE_USER,
	UPD_FUNC,
	UPD_DATETM,
	UPD_USER
) VALUES (
	/*entrustEadLineId*/,
	/*entrustEadSlipId*/,
	/*lineNo*/,
	/*productCode*/,
	/*productAbstract*/,
	/*quantity*/,
	/*remarks*/,
	/*poLineId*/,
	/*relEntrustEadLineId*/,
	/*entrustEadCategory*/,
	/*creFunc*/NULL,
	now(),
	/*creUser*/NULL,
	/*updFunc*/NULL,
	now(),
	/*updUser*/NULL
)
