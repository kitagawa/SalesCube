SELECT
		D1.DEPT_ID
		,D1.NAME
		,D1.PARENT_ID
		,D2.NAME AS PARENT_NAME
		,D1.CRE_FUNC
		,D1.CRE_DATETM
		,D1.CRE_USER
		,D1.UPD_FUNC
		,D1.UPD_DATETM
		,D1.UPD_USER
    FROM
        DEPT_MST_/*$domainId*/ D1 LEFT OUTER JOIN DEPT_MST_/*$domainId*/ D2
        ON D1.PARENT_ID = D2.DEPT_ID
   	/*BEGIN*/
	WHERE
		/*IF deptId != null */
		D1.DEPT_ID LIKE /*deptId*/'S%'
		/*END*/

		/*IF parentId != null */
		AND D1.PARENT_ID = /*parentId*/'S'
		/*END*/

		/*IF name != null */
		AND D1.NAME LIKE /*name*/'%S%'
		/*END*/
	/*END*/
	/*BEGIN*/
	ORDER BY
		/*IF sortColumn != null */
		D1./*$sortColumn*/
		/*END*/

		/*IF sortOrder != null*/
		/*$sortOrder*/
		/*END*/
	/*END*/
	/*BEGIN*/
	/*IF rowCount != null*/
	LIMIT /*rowCount*/
	/*END*/
	/*IF offsetRow != null*/
	OFFSET /*offsetRow*/
	/*END*/
	/*END*/