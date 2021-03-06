<div style="width: 910px; height: 25px;">
		<div style="position:absolute; left: 0px;">検索結果件数： ${searchResultCount}件</div>
		<div style="position:absolute; width: 910px; text-align: center;">
			${sw:pageLink(searchResultCount,rowCount,pageNo)}
		</div>
        <jsp:include page="/WEB-INF/view/common/rowcount.jsp"/>
</div>

<table summary="社員検索結果" class="forms" style="width: 910px;">
	<colgroup>
		<col span="1" style="width: 10%">
		<col span="1" style="width: 15%">
		<col span="1" style="width: 15%">
		<col span="1" style="width: 20%">
		<col span="1" style="width: 30%">
		<col span="1" style="width: 10%">
	</colgroup>
	<tr>
		<th style="cursor: pointer" onclick="sort('userId');">社員コード
			<c:if test="${sortColumn == 'userId'}">
				<c:if test="${sortOrderAsc}">▲</c:if>
				<c:if test="${!sortOrderAsc}">▼</c:if>
			</c:if>
		</th>
		<th style="cursor: pointer" onclick="sort('nameKnj');">社員名
			<c:if test="${sortColumn == 'nameKnj'}">
				<c:if test="${sortOrderAsc}">▲</c:if>
				<c:if test="${!sortOrderAsc}">▼</c:if>
			</c:if>
		</th>
		<th style="cursor: pointer" onclick="sort('nameKana');">社員名カナ
			<c:if test="${sortColumn == 'nameKana'}">
				<c:if test="${sortOrderAsc}">▲</c:if>
				<c:if test="${!sortOrderAsc}">▼</c:if>
			</c:if>
		</th>
		<th style="cursor: pointer" onclick="sort('deptName');">部門
			<c:if test="${sortColumn == 'deptName'}">
				<c:if test="${sortOrderAsc}">▲</c:if>
				<c:if test="${!sortOrderAsc}">▼</c:if>
			</c:if>
		</th>
		<th style="cursor: pointer" onclick="sort('email');">E-MAIL
			<c:if test="${sortColumn == 'email'}">
				<c:if test="${sortOrderAsc}">▲</c:if>
				<c:if test="${!sortOrderAsc}">▼</c:if>
			</c:if>
		</th>
		<th>&nbsp;</th>
	</tr>

	<c:forEach var="bean" items="${searchResultList}" varStatus="status">
	<tr>
		<td>${f:h(bean.userId)}</td>
		<td style="white-space: normal">${f:h(bean.nameKnj)}</td>
		<td style="white-space: normal">${f:h(bean.nameKana)}</td>
		<td style="white-space: normal">${f:h(bean.deptName)}</td>
		<td style="white-space: normal">${f:h(bean.email)}</td>
		<td style="text-align: center">
			<c:if test="${isUpdate}">
			<button onclick="editUser('${sw:u(bean.userId)}');">編集</button>
			<button onclick="deleteUser('${sw:u(bean.userId)}', '${bean.updDatetm}');">削除</button>
			</c:if>
			<c:if test="${!isUpdate}">
			<button onclick="editUser('${sw:u(bean.userId)}');">参照</button>
			</c:if>
		</td>
	</tr>
	</c:forEach>
</table>

<div style="position:absolute; width: 910px; text-align: center;">
	${sw:pageLink(searchResultCount,rowCount,pageNo)}
</div>


<input type="hidden" id="prev_userId" name="prev_userId" value="${userId}">
<input type="hidden" id="prev_nameKnj" name="prev_nameKnj" value="${f:h(nameKnj)}">
<input type="hidden" id="prev_nameKana" name="prev_nameKana" value="${f:h(nameKana)}">
<input type="hidden" id="prev_deptId" name="prev_deptId" value="${deptId}">
<input type="hidden" id="prev_email" name="prev_email" value="${f:h(email)}">
<input type="hidden" id="prev_sortColumn" name="prev_sortColumn" value="${sortColumn}">
<input type="hidden" id="prev_sortOrderAsc" name="prev_sortOrderAsc" value="${sortOrderAsc}">
<input type="hidden" id="prev_pageNo" name="prev_pageNo" value="${pageNo}">
<input type="hidden" id="prev_rowCount" name="prev_rowCount" value="${rowCount}">


<c:if test="${searchResultCount == 1}">

<c:forEach var="bean" items="${searchResultList}" varStatus="status">
<input type="hidden" id="singleUserId" name="singleUserId" value="${sw:u(bean.userId)}">
</c:forEach>
</c:if>