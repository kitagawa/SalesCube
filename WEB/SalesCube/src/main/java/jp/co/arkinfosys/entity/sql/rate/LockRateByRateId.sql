SELECT
	RATE_ID
	,UPD_DATETM
	,UPD_USER
FROM
	RATE_MST_/*$domainId*/
WHERE
	RATE_ID=/*rateId*/
FOR UPDATE