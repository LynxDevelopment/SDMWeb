<%@ page isELIgnored="false" %>
<%@ include file="/fpm/commons/header.xwb" %>
<%@ taglib prefix="s-form" uri="http://www.springframework.org/tags/form"%>
<%@taglib  prefix="spring" uri="http://www.springframework.org/tags" %>

<xweb:setBundle baseName="sdm"/>

<script type="text/javascript">
	function Submit() {
		document.getElementById("form").submit();
	}

	function selectorSelect(){
		alert("Pasa");
		alert("w:"+document.normalizateProcessorSelectionFilterForm.selectedNormalizationProcessorConfig.value);
		document.normalizateProcessorSelectionFilterForm.selectedNormalizationProcessorConfig.value='true';
		alert("Pasa2");
		document.normalizateProcessorSelectionForm.submit();
		alert("Sale");
	}
	function configuratorChangeMessage(_messageRow){
		$('currentMessageRowId').value=_messageRow;
		document.normalizateProcessorConfigForm.goto.value='';
		document.normalizateProcessorConfigForm.target='_top';
		document.normalizateProcessorConfigForm.submit();
	}
	function configuratorSelectField(_rowNum){
		$('currentStaticFieldRowId').value=_rowNum;
		$('action').value="Load";
		$("normalizateProcessorSelectionFilterForm").submit();
	}
	function selectorChangeMessageType(){
		//alert("Change");
		// TODO this is the message always alerted
		if(confirm('<fmt:message key="if.change.lost.changes"/> <fmt:message key="are.you.sure"/>')){
			$("normalizateProcessorSelectionFilterForm").submit();
		}
	}
	function configuratorSave(){
		if(confirm('<fmt:message key="are.you.sure"/>')){
			$('action').value="Save";
			$("normalizateProcessorSelectionFilterForm").submit();
		}
	}
	
	function selectMessageManually(){
		
		var messageId=prompt('<fmt:message key="insert.message.id.to.select"/>:');
		if((messageId!=null)&&(''!=messageId)){
			try{
				parseInt(messageId)
				$('selectedExampleMessageId').value=messageId;
				document.normalizateProcessorConfigForm.goto.value='updateNormalizateProcessorConfigFormAction.do';
				document.normalizateProcessorConfigForm.target='_top';
				document.normalizateProcessorConfigForm.submit();
			}catch(e){
				alert('error: '+e);
				alert('<fmt:message key="error.not.integer"/>');
			}
		}else{
			$('selectedExampleMessageId').value='';
			document.normalizateProcessorConfigForm.goto.value='updateNormalizateProcessorConfigFormAction.do';
			document.normalizateProcessorConfigForm.target='_top';
			document.normalizateProcessorConfigForm.submit();
		}
	}
	function configuratorTest(){
		openCenteredWindow(null,'NormalizationTestResults', 800, 630);
		document.normalizateProcessorConfigForm.goto.value='testNormalizateProcessConfigFormAction.do';
		document.normalizateProcessorConfigForm.target='NormalizationTestResults';
		document.normalizateProcessorConfigForm.submit();
	}
	function configuratorSave_old(){
		if(confirm('<fmt:message key="are.you.sure"/>')){
			document.normalizateProcessorConfigForm.goto.value='saveNormalizateProcessorConfigFormAction.do'
			document.normalizateProcessorConfigForm.target='_top';
			document.normalizateProcessorConfigForm.submit();
		}
	}
	function configuratorExport(){
		document.normalizateProcessorConfigForm.goto.value='exportNormalizateProcessorConfigFormAction.do?FileName=normalizationConfigExport'
		document.normalizateProcessorConfigForm.target='_top';
		document.normalizateProcessorConfigForm.submit();
	}

	function insertAtScript(_textToCopy) {
		alert(_textToCopy);
		var selectedTextArea=null;
		var start=null;
		var end=null;
		var orig=null;
		var range=null;
		var actual=null;
		
		//selectedTextArea=eval('document.normalizateProcessorSelectionFilterForm.normalizationScript'.value);
		
		if(window.getSelection()) {
			alert("Pasa 1");
			//document.normalizateProcessorSelectionFilterForm.normalizationScript.focus();
			document.getElementById("normalizationScript").focus();
			
			alert("Pasa1.1");
			orig=selectedTextArea.value.replace(/\r\n/g, "\n");
			range=document.selection.createRange();
			alert("Pasa1.1");
			if(range.parentElement()!=selectedTextArea) {
				return false;
			}
			alert("Pasa1.2");
			range.text=_textToCopy;
			actual=tmp=selectedTextArea.value.replace(/\r\n/g, "\n");
			for(var diff=0;diff<orig.length;diff++) {
				if(orig.charAt(diff)!=actual.charAt(diff)) break;
			}
			for(var index=0,start=0;(tmp.match(_textToCopy))&&(tmp=tmp.replace(_textToCopy,""))&&(index<=diff);index=start+_textToCopy.length){
				start=actual.indexOf(_textToCopy, index);
			}
		} else if(selectedTextArea.selectionStart) {
			alert("Pasa2");
			start=selectedTextArea.selectionStart;
			end=selectedTextArea.selectionEnd;
			selectedTextArea.value=selectedTextArea.value.substr(0, start)+_textToCopy+selectedTextArea.value.substr(end, selectedTextArea.value.length);
		}
		alert("Pasa2");
		if(start!=null)
			setCaretTo(selectedTextArea,start+_textToCopy.length);
		else
			selectedTextArea.value+=_textToCopy;
	}
	
	function setCaretTo(_selectedTextArea, _position) {
		if(_selectedTextArea.createTextRange) {
			var range=_selectedTextArea.createTextRange();
			range.move('character', _position);
			range.select();
		} else if(_selectedTextArea.selectionStart) {
			_selectedTextArea.focus();
			_selectedTextArea.setSelectionRange(_position, _position);
		}
	}
	
</script>
<c:url value="/admin/internal/registerCustomer.sdo" var="submitUrl" />
<c:url value="/admin/list.sdo" var="cselectUrl" />
 
<s-form:form id="normalizateProcessorSelectionFilterForm" method="post" commandName="filterNormalization" action="/SDMWeb/admin/normalization/filter.sdo">
<s-form:hidden path="selectedNormalizationProcessorConfig"/>
<s-form:hidden path="action"/>

<jsp:setProperty name="filterNormalization" property="selectedNormalizationProcessorConfig" value="secrets"/>
	<table class="functions" style="width:99%" cellpadding="1">
		<tr>
			<th colspan="2"><spring:message code="normalizer.configuration" /></th>
		</tr>
		<tr>
			<td>
				<table class="tableFilterClass" width="100%">
					<tr>
						<td><spring:message code="asset.type"/></td>
						<td><s-form:select path="assetTypes">
								<s-form:option value="NONE" label="--Please Select"><spring:message code="dropdown.select" /></s-form:option>
      							<s-form:options items="${assetTypeList}"/>
							</s-form:select>
						</td>
					
						<td><spring:message code="enterprise"/></td>
						<td><s-form:select path="enterprises">
								<s-form:option value="NONE" label="--Please Select"><spring:message code="dropdown.select" /></s-form:option>
      							<s-form:options items="${enterpriseList}"/>
							</s-form:select>
						</td>
				
						<td><spring:message code="provider"/></td>
						<td>
							<s-form:select path="providers">
								<s-form:option value="NONE" label="--Please Select"><spring:message code="dropdown.select" /></s-form:option>
      							<s-form:options items="${providerList}"/>
							</s-form:select>
						</td>
					</tr>
					<tr>
						<td colspan="6" align="right" valign="bottom" style="margin-top:5px" >
							<input type="button"  value="<fmt:message key="filter"/>" onclick="javascript:selectorChangeMessageType();" />
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>

<c:set var="listMessagesSize" value="${fn:length(filterNormalization.lSDMValues)}"/>
<c:set var="currentRow" value="${filterNormalization.currentRow}"/>
	<c:choose>
			<c:when test="${filterNormalization.normalizationConfigured}">
			<%-- <input type="hidden" id="currentStaticFieldRowId" name="currentEventFieldRowId" value="${filterNormalization.currentStaticFieldRow}"/> --%>
			<s-form:hidden path="currentStaticFieldRowId"/>
				<%-- Event Message examples --%>
				<div class="sdmContainerClass" style="width:20%; height:400px;float:left;margin-right:1%;border-color: white;">
					<table class="data" style="width:100%;border: 0px solid black;" cellpadding="0" cellspacing="0">
						<thead>
							<tr>
								<c:choose>
									<c:when test="${(listMessagesSize>0)&&(1<currentRow)}">
										<th class="navigationArrowClass" onclick="javascript:configuratorChangeMessage(${currentRow}-1);" title="<fmt:message key="previous"/>">&lsaquo;</th>
									</c:when>
									<c:otherwise><th class="navigationArrowDisabledClass" title="<fmt:message key="previous"/>">&lsaquo;</th></c:otherwise>
								</c:choose>
								<c:if test="${listMessagesSize>1}">
									<c:set var="varSelectableMessage" value="javascript:selectMessageManually();"/>
									<c:set var="varSelectableMessageStyle" value="cursor:pointer;"/>
								</c:if>
								<th style="text-align:center;text-transform:none;${varSelectableMessageStyle}" onclick="${varSelectableMessage}">
									<c:set var="varRowId" value="${filterNormalization.lSDMValues[filterNormalization.currentRow].id}"/>
									<c:if test="${empty varRowId}"><c:set var="varRowId" value="-"/></c:if>
									<fmt:message key="normalization.examples.title">
										<fmt:param value="${varRowId}"/>
										<fmt:param value="${currentRow}"/>
										<fmt:param value="${listMessagesSize}"/>
									</fmt:message>
								</th>
								<th style="width:16px;">
									<c:choose>
 										<c:when test="${!empty (filterNormalization.lSDMValues[filterNormalization.currentRow].id)}"> 
											<a title="<fmt:message key="view.event.detail"/>" href="">
												<img alt="" src="<c:url value="/images/icons/windows_and_views/window/window_16_n_g.gif"/>">
												</th>
											</a>
										</c:when>
										<c:otherwise>
											<img alt="" src="<c:url value="/images/icons/windows_and_views/window/window_16_d_g.gif"/>"></th>
										</c:otherwise> 
									</c:choose>
								</th>
								<c:choose>
									<c:when test="${(listMessagesSize>0)&&(listMessagesSize>currentRow)}">
										<th class="navigationArrowClass" onclick="javascript:configuratorChangeMessage(${currentRow}+1);" title="<fmt:message key="next"/>">&rsaquo;</th>
									</c:when>
									<c:otherwise><th class="navigationArrowDisabledClass" title="<fmt:message key="next"/>">&rsaquo;</th></c:otherwise>
								</c:choose>
							</tr>
						</thead>
						<tbody></tbody>
					</table>
					<div style="width:100%;height:370px;overflow:auto;">
						<c:set var="varMessageFieldsList" value="${filterNormalization.lSDMValues}"/> 
						<c:if test="${!empty varMessageFieldsList}">
								<table class="data" style="width:100%;border: 0px solid black;" cellpadding="0" cellspacing="0">
								<tbody>
									<c:forEach items="${varMessageFieldsList}" var="varMessageDetail">
										<%-- <tr onclick="javascript:insertAtScript('${varMessageDetail.path}');"><td><span title="[${varMessageDetail.path}] ${varMessageDetail.description}"><c:out value="${varMessageDetail.name}"/></span> <span title="${varMessageDetail.value}">(<string:truncateNicely lower="10"><c:out value="${varMessageDetail.value}"/></string:truncateNicely>)</span></td></tr> --%>
										<tr onclick="javascript:insertAtScript('${varMessageDetail.id}');">
											<td>
												<span title="[${varMessageDetail.jobField.path}] ${varMessageDetail.jobField.description}"><c:out value="${varMessageDetail.jobField.name}"/></span> 
												<span title="${varMessageDetail.value}">(<string:truncateNicely lower="10"><c:out value="${varMessageDetail.value}"/></string:truncateNicely>)</span>
											</td>
										</tr>
				 					</c:forEach>
								</tbody>
							</table>
						</c:if>
					</div>
				</div>
				<%-- Normalization Content --%>
				<c:set var="varStaticFieldConfig" value="${filterNormalization.varAssetFieldConfig}"/>
				<c:set var="varStaticFieldConfigSelected" value="${filterNormalization.detailSelected}"/>
				<div class="sdmContainerClass" style="width:57%;height:400px;float:left;margin-right:1%;">
					<table class="data" width="100%" style="border: 0px solid black;">
						<thead>
							<tr>
								<th>input:&nbsp;_message</th>
								 <th>return:&nbsp;
									<c:choose>
										<c:when test="${'BOOLEAN'==varStaticFieldConfigSelected.fieldType}">Boolean</c:when>
										<c:when test="${'LONG'==varStaticFieldConfigSelected.fieldType}">Long</c:when>
										<c:when test="${'DOUBLE'==varStaticFieldConfigSelected.fieldType}">Double</c:when>
										<c:when test="${'TIMESTAMP'==varStaticFieldConfigSelected.fieldType}">Date</c:when>
										<c:when test="${'SHORTSTRING'==varStaticFieldConfigSelected.fieldType}">String (max. <c:out value="${varStaticFieldConfigSelected.maxLength}"/>)</c:when>
										<c:when test="${'MIDDLESTRING'==varStaticFieldConfigSelected.fieldType}">String (max. <c:out value="${varStaticFieldConfigSelected.maxLength}"/>)</c:when>
										<c:when test="${'LONGSTRING'==varStaticFieldConfigSelected.fieldType}">String (max. <c:out value="${varStaticFieldConfigSelected.maxLength}"/>)</c:when>
										<c:when test="${'VERYLONGSTRING'==varStaticFieldConfigSelected.fieldType}">String (max. <c:out value="${varStaticFieldConfigSelected.maxLength}"/>)</c:when>
										<c:otherwise>String (no limit)</c:otherwise>
									</c:choose>
								</th>
								<th style="width:20px;">
									<a title="<fmt:message key="help"/>" href="javascript:openCenteredWindow('<c:url value="/fpm/sdm/administration/events/normalization/normalizationProcessorConfigHelpPopUp.xwb"/>','NormalizationHelp',700,620);">
										<img src="<c:url value="/images/icons/common_toolbar/help_bubble/help_bubble_16_h_g.gif"/>" alt="<fmt:message key="help"/>"/>
									</a>
								</th>
							</tr>
						</thead>
						<tbody></tbody>
					</table>
					<s-form:textarea path="normalizationScript" id="" cols="87" rows="24"  cssClass="sdmContainerClass"></s-form:textarea>
				</div>
				<!-- Event Details -->
				<div class="sdmContainerClass" style="width:20%;height:400px;float:right;border-color:white;">
					<table class="data" width="100%" style="border: 0px solid black;">
						<thead><tr><th style="text-align: center;"><fmt:message key="field"/></th></tr></thead>
						<tbody></tbody>
					</table>
					<div style="width:100%;height:373px;overflow:auto;">
						<table class="data" style="width:100%;border: 0px solid black;" cellpadding="0" cellspacing="0">
							<c:forEach items="${varStaticFieldConfig}" var="varSelectedField">
								<c:choose>
									<c:when test="${filterNormalization.currentStaticFieldRow==varSelectedField['position']}">
										<c:set var="varSelectedClass" value="selected"/>
									</c:when>
									<c:otherwise>
										<c:set var="varSelectedClass" value=""/>
									</c:otherwise>
								</c:choose>
								<tr onclick="javascript:configuratorSelectField('${varSelectedField['position']}');" style="cursor:pointer;" class="${varSelectedClass}">
									<fmt:message key="field: returns " var="varReturnTitle"/>
										<c:choose>
											<c:when test="${!empty fn:substringBefore(fn:substringAfter(varSelectedField['assetTypeDetail'].fieldPath,':'),':')}">
												<c:set value="${varReturnTitle}${fn:substringBefore(fn:substringAfter(varSelectedField['assetTypeDetail'].fieldPath,':'),':')}" var="varReturnTitle"/>
											</c:when>
											<c:otherwise>
												<c:set value="${varReturnTitle}into ${fn:substringAfter(varSelectedField['assetTypeDetail'].fieldPath,':')}" var="varReturnTitle"/>
											</c:otherwise>
										</c:choose>
									
									<td title="<c:if test="${varSelectedField['assetTypeDetail'].basic}">(default)</c:if> <fmt:message key="${varSelectedField['assetTypeDetail'].description}"/> (<c:out value="${varSelectedField['assetTypeDetail'].fieldPath}"/>)">
										<fmt:message key="${varSelectedField['assetTypeDetail'].name}"/>
									</td>
									<%-- <td title="${varReturnTitle}">
										<wcomp:hiddenInput id="fieldPath${varSelectedField['assetTypeDetail'].id}" value="${varSelectedField['assetTypeDetail'].fieldPath}"/>
										<wcomp:hiddenInput id="fieldExtension${row['assetTypeDetail'].id}" value="${row['assetTypeDetail'].extension}"/>
										<c:choose>
											<c:when test="${!empty fn:substringBefore(varSelectedField['assetTypeDetail'].fieldPath,':')}"><c:out value="${fn:substringBefore(varSelectedField['assetTypeDetail'].fieldPath,':')}"/></c:when>
											<c:otherwise><c:out value="${varSelectedField['assetTypeDetail'].fieldPath}"/></c:otherwise>
										</c:choose>
									</td> --%>
								</tr>
							</c:forEach>
						</table>	
					</div>
				</div>
				<table class="functions buttonsBarClass" style="width: 99%" cellpadding="0">
					<tr>
						<td class="buttonsBarClass">
							<a class="boton" href="javascript:configuratorExport();"><fmt:message key="export"/></a>
							<a class="boton" href="javascript:configuratorTest();"><fmt:message key="test"/></a>
							<a class="boton" href="javascript:configuratorSave();"><fmt:message key="save"/></a>
						</td>
					</tr>
				</table>
			</c:when>
			<c:otherwise>
				<div class="sdmErrorClass"><fmt:message key="this event is not yet configured"/></div>
			</c:otherwise>
	</c:choose>
</s-form:form>

<%-- 
<wcomp:form id="normalizateProcessorSelectionForm" lifeCycle="page" action="selectNormalizateProcessorConfigAction.do">
	<wcomp:input id="selectedNormalizationProcessorConfig" value="false" style="display:none"/>
	<fmt:message key="normalizer.configuration" var="varTitle"/>
	<sdm-web:roundedFrame title="${varTitle}" style="margin-bottom:10px;">
	<table style="width:100%;" cellpadding="0" cellspacing="0">
		<tr>
			<td style="padding:3px 3px 3px 3px;">
			
				<table class="tableFilterClass" style="width:100%;" cellpadding="0" cellspacing="0">
					<tr>
						<th style="font-weight:bold;" title="<fmt:message key="if.change.refilter"/>"><fmt:message key="message.type"/>:&nbsp;*</th>
						<td>
							<wcomp:dataset id="varMessageTypeDataset" stateful="true" lifeCycle="request" query="from CAMessageType" dataSource="hibernateXWeb"/>
							<wcomp:select id="messageType" htmlID="messageType" htmlClass="classComboBox" onChange="javascript:selectorChangeMessageType();">
								<wcomp:row omitTrTag="true" dataset="varMessageTypeDataset">
									<c:if test="${empty varFirstMessageType}">
										<c:set var="varFirstMessageType" value="${row.id.format.id}"/>
									</c:if>
									<wcomp:option value="${row.id.format.id}|${row.id.code}">${row.id.format.id} - <fmt:message key="${row.name}"/> (${row.id.code})</wcomp:option>
								</wcomp:row>
							</wcomp:select>
						</td>
						<th style="font-weight:bold;text-align:right"><fmt:message key="provider"/>:</th>
						<td>
							<c:if test="${!empty fn:substringBefore(normalizateProcessorSelectionForm.components.messageType.value,'|')}">
								<c:set var="varSelectedMessageType" value="${fn:substringBefore(normalizateProcessorSelectionForm.components.messageType.value,'|')}"/>
							</c:if>
							<wcomp:dataset id="varExternalProviderDataset" stateful="true" lifeCycle="page" query="select provider from CAFormatProvider as formatprovider join formatprovider.eventProvider as provider where formatprovider.messageFormat.id=?" dataSource="hibernateXWeb">
								<wcomp:queryParam value="${varSelectedMessageType}" defaultValue="${varFirstMessageType}"/>
							</wcomp:dataset>
							<c:set var="varDisplayHideStyle" value=""/>
							<c:if test="${varExternalProviderDataset.availableRows<=1}">
								<c:set var="varDisplayHideStyle" value="display:none"/>
								<fmt:message key="${varExternalProviderDataset.row.name}"/>
							</c:if>
							<wcomp:select id="externalProvider" htmlID="externalProvider" htmlClass="classComboBox" style="${varDisplayHideStyle}">
								<wcomp:option value="" selected="true"><fmt:message key="all"/></wcomp:option>
								<wcomp:row omitTrTag="true" dataset="varExternalProviderDataset">
									<wcomp:option value="${row.id}"><fmt:message key="${row.name}"/></wcomp:option>
								</wcomp:row>
							</wcomp:select>
						</td>
					</tr>
					<tr>
						<th style="font-weight:bold;"><fmt:message key="event.type"/>:</th>
						<td colspan="2">
							<c:set var="listItems" value="${sdm:getOrderSelect('hibernateXWeb')}"/>
							<wcomp:select id="eventType" htmlID="eventType" htmlClass="classComboBox">
								<c:forEach items="${listItems}" var="item">
									<c:choose>
										<c:when test="${item.key=='OFPA'}"><wcomp:option selected="true" value="${item.key}"><fmt:message key="event.type.${item.key}"/></wcomp:option></c:when>
										<c:otherwise><wcomp:option value="${item.key}"><fmt:message key="event.type.${item.key}"/></wcomp:option></c:otherwise>
									</c:choose>
								</c:forEach>								
							</wcomp:select> 
						</td>
						<td valign="bottom" style="margin-top:5px">
							<wcomp-ext:submitForm styleClass="boton" href="javascript:selectorSelect();"><fmt:message key="filter"/></wcomp-ext:submitForm>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	</sdm-web:roundedFrame>
	<wcomp-ext:displayFormErrors showMode="title" styleClass="sdmErrorClass" />
</wcomp:form>

<c:if test="${(!empty normalizateProcessorSelectionForm.components.selectedNormalizationProcessorConfig.value)&&(normalizateProcessorSelectionForm.components.selectedNormalizationProcessorConfig.value!=false)}">
	<wcomp:form id="normalizateProcessorConfigForm" lifeCycle="page" action="updateNormalizateProcessorConfigFormAction.do">
		<wcomp:input type="hidden" id="selectedExampleMessageId" htmlID="selectedExampleMessageId" value=""/>
		<wcomp:dataset id="varEventFieldConfig" stateful="true" query="" lifeCycle="page" dataSource="${sdm:getEventFieldMessageConfig('hibernateXWeb',sessionScope['normalization.config.select.eventType'],fn:substringBefore(sessionScope['normalization.config.select.messageType'],'|'),fn:substringAfter(sessionScope['normalization.config.select.messageType'],'|'),sessionScope['normalization.config.select.externalProvider'])}"/>
		<c:choose>
			<c:when test="${(!empty varEventFieldConfig)&&(varEventFieldConfig.availableRows>0)}">
				<input type="hidden" id="currentEventFieldRowId" name="currentEventFieldRowId" value="${varEventFieldConfig.currentRow}"/>
				Event Message examples
				<div class="sdmContainerClass" style="width:20%; height:400px;float:left;margin-right:1%;border-color: white;">
					<wcomp:dataset id="varEventMessageDataset" stateful="true" query="from CAEventMessage" dataSource="hibernateXWeb" resultsPerPage="1" orderBy="id asc" lifeCycle="page">
 						<wcomp:datasetFilter>
 							<wcomp:filterCondition fieldName="id" operator="=" value="${normalizateProcessorConfigForm.components.selectedExampleMessageId.value}"/>
							<wcomp:filterCondition fieldName="auditor.deleted" operator="=" value="0"/>
							<wcomp:filterCondition fieldName="normalizedSecurity.class" operator="=" value="customer"/>
							<wcomp:filterCondition fieldName="operationStatus.state.id.code" notMode="true" operator="=" value="PRSD"/>
							<wcomp:filterCondition fieldName="operationStatus.state.id.code" notMode="true" operator="=" value="PNFA"/>
							<wcomp:filterCondition fieldName="operationStatus.state.id.code" notMode="true" operator="=" value="MSDS"/>
							<wcomp:filterCondition fieldName="eventProvider.id" operator="=" value="${sessionScope['normalization.config.select.externalProvider']}"/>
							<wcomp:filterCondition fieldName="normalizedEventType" operator="=" value="${sessionScope['normalization.config.select.eventType']}"/>
							<wcomp:filterCondition fieldName="messageType.id.format.id" operator="=" value="${fn:substringBefore(sessionScope['normalization.config.select.messageType'],'|')}"/>
							<wcomp:filterCondition fieldName="messageType.id.code" operator="=" value="${fn:substringAfter(sessionScope['normalization.config.select.messageType'],'|')}"/>
						</wcomp:datasetFilter>
					</wcomp:dataset>
					<c:set var="varCurrentMessageId" value="0"/>
					<c:if test="${(!empty varEventMessageDataset)&&(varEventMessageDataset.availableRows>0)}">
						<c:set var="varCurrentMessageId" value="${varEventMessageDataset.row.id}"/>
					</c:if>
					<input type="hidden" id="currentMessageId" name="currentMessageId" value="${varCurrentMessageId}"/>
					<input type="hidden" id="currentMessageRowId" name="currentMessageRowId" value="${varEventMessageDataset.currentRow}"/>
					<sdm-web:roundedFrame  style="margin-bottom:10px;">
					<table style="width:100%;border: 0px solid black;" cellpadding="0" cellspacing="0">
						<thead>
							<tr>
								<c:choose>
									<c:when test="${(!empty varEventMessageDataset)&&(1<varEventMessageDataset.currentRow)}">
										<th class="navigationArrowClass" onclick="javascript:configuratorChangeMessage(${varEventMessageDataset.currentRow}-1);" title="<fmt:message key="previous"/>">&lsaquo;</th>
									</c:when>
									<c:otherwise><th class="navigationArrowDisabledClass" title="<fmt:message key="previous"/>">&lsaquo;</th></c:otherwise>
								</c:choose>
								<c:if test="${varEventMessageDataset.availableRows>1}">
									<c:set var="varSelectableMessage" value="javascript:selectMessageManually();"/>
									<c:set var="varSelectableMessageStyle" value="cursor:pointer;"/>
								</c:if>
								<th style="text-align:center;text-transform:none;${varSelectableMessageStyle}" onclick="${varSelectableMessage}">
									<c:set var="varRowId" value="${varEventMessageDataset.row.id}"/>
									<c:if test="${empty varRowId}"><c:set var="varRowId" value="-"/></c:if>
									<fmt:message key="normalization.examples.title">
										<fmt:param value="${varRowId}"/>
										<fmt:param value="${varEventMessageDataset.currentRow}"/>
										<fmt:param value="${varEventMessageDataset.availableRows}"/>
									</fmt:message>
								</th>
								<th style="width:16px;">
									<c:set var="varMessageEntity" value="CAEventMessage"/>
									<xweb:isInstance object="${varEventMessageDataset.row}" type="com.lynxspa.sdm.entities.events.messages.CAEventMessageHistoric">
										<c:set var="varMessageEntity" value="CAEventMessageHistoric"/>
									</xweb:isInstance>
									<c:choose>
										<c:when test="${!empty varEventMessageDataset.row.id}">
											<a title="<fmt:message key="view.event.detail"/>" href="javascript:openModalCenteredWindow('<c:url value="/fpm/sdm/support/message/messageDetailsPopUp.xwb?messageId=${varEventMessageDataset.row.id}&messagesEntity=${varMessageEntity}"/>','eventMessage${varEventMessageDataset.row.id}Details',  1000, 510);">
												<img alt="" src="<c:url value="/images/icons/windows_and_views/window/window_16_n_g.gif"/>"></th>
											</a>
										</c:when>
										<c:otherwise>
											<img alt="" src="<c:url value="/images/icons/windows_and_views/window/window_16_d_g.gif"/>"></th>
										</c:otherwise>
									</c:choose>
								</th>
								<c:choose>
									<c:when test="${(!empty varEventMessageDataset)&&(varEventMessageDataset.availableRows>varEventMessageDataset.currentRow)}">
										<th class="navigationArrowClass" onclick="javascript:configuratorChangeMessage(${varEventMessageDataset.currentRow}+1);" title="<fmt:message key="next"/>">&rsaquo;</th>
									</c:when>
									<c:otherwise><th class="navigationArrowDisabledClass" title="<fmt:message key="next"/>">&rsaquo;</th></c:otherwise>
								</c:choose>
							</tr>
						</thead>
						<tbody></tbody>
					</table>
					</sdm-web:roundedFrame>
					<div style="width:100%;height:370px;overflow:auto;">
						<c:set var="varMessageFieldsList" value="${sdm:getMessageDetails('hibernateXWeb',varEventMessageDataset.row)}"/> 
						<c:if test="${!empty varMessageFieldsList}">
								<table style="width:100%;border: 0px solid black;" cellpadding="0" cellspacing="0">
								<tbody>
									<c:forEach items="${varMessageFieldsList}" var="varMessageDetail">
										<tr onclick="javascript:insertAtScript('${varMessageDetail.path}');"><td><span title="[${varMessageDetail.path}] ${varMessageDetail.description}"><c:out value="${varMessageDetail.name}"/></span> <span title="${varMessageDetail.value}">(<string:truncateNicely lower="10"><c:out value="${varMessageDetail.value}"/></string:truncateNicely>)</span></td></tr>
				 					</c:forEach>
								</tbody>
							</table>
						</c:if>
					</div>
				</div>
				Normalization Content
				<div class="sdmContainerClass" style="width:57%;height:400px;float:left;margin-right:1%;border-color: white;">
				<sdm-web:roundedFrame title="${varTitle}" style="margin-bottom:10px;">
						<table style="width:100%;border: 0px solid black;" cellpadding="0" cellspacing="0">
						<thead>
							<tr>
								<th>input:&nbsp;_message</th>
								<th>return:&nbsp;
									<c:choose>
										<c:when test="${varEventFieldConfig.row['eventTypeDetail'].extension}">List&lsaquo;List&lsaquo;MessageField&rsaquo;&rsaquo;</c:when>
										<c:otherwise>
											<c:choose>
												<c:when test="${'BOOLEAN'==varEventFieldConfig.row['eventTypeDetail'].fieldType}">Boolean</c:when>
												<c:when test="${'LONG'==varEventFieldConfig.row['eventTypeDetail'].fieldType}">Long</c:when>
												<c:when test="${'DOUBLE'==varEventFieldConfig.row['eventTypeDetail'].fieldType}">Double</c:when>
												<c:when test="${'TIMESTAMP'==varEventFieldConfig.row['eventTypeDetail'].fieldType}">Date</c:when>
												<c:when test="${'SHORTSTRING'==varEventFieldConfig.row['eventTypeDetail'].fieldType}">String (max. <c:out value="${varEventFieldConfig.row['eventTypeDetail'].maxLength}"/>)</c:when>
												<c:when test="${'MIDDLESTRING'==varEventFieldConfig.row['eventTypeDetail'].fieldType}">String (max. <c:out value="${varEventFieldConfig.row['eventTypeDetail'].maxLength}"/>)</c:when>
												<c:when test="${'LONGSTRING'==varEventFieldConfig.row['eventTypeDetail'].fieldType}">String (max. <c:out value="${varEventFieldConfig.row['eventTypeDetail'].maxLength}"/>)</c:when>
												<c:when test="${'VERYLONGSTRING'==varEventFieldConfig.row['eventTypeDetail'].fieldType}">String (max. <c:out value="${varEventFieldConfig.row['eventTypeDetail'].maxLength}"/>)</c:when>
												<c:otherwise>String (no limit)</c:otherwise>
											</c:choose>
										</c:otherwise>
									</c:choose>
								</th>
								<th style="width:20px;">
									<a title="<fmt:message key="help"/>" href="javascript:openCenteredWindow('<c:url value="/fpm/sdm/administration/events/normalization/normalizationProcessorConfigHelpPopUp.xwb"/>','NormalizationHelp',700,620);">
										<img src="<c:url value="/images/icons/common_toolbar/help_bubble/help_bubble_16_h_g.gif"/>" alt="<fmt:message key="help"/>"/>
									</a>
								</th>
							</tr>
						</thead>
						<tbody></tbody>
					</table>
					</sdm-web:roundedFrame>
					<c:forEach items="${varEventFieldConfig.rows}" var="varSelectedField">
						<c:choose>
							<c:when test="${varEventFieldConfig.row['eventTypeDetail'].id==varSelectedField['eventTypeDetail'].id}">
								<input type="hidden" id="currentEventFieldId" name="currentEventFieldId" value="${varSelectedField['eventTypeDetail'].id}"/>
								<wcomp:textarea id="fieldScript${varSelectedField['eventTypeDetail'].id}" cols="" rows="" stateful="true" htmlClass="sdmContainerClass" style="width:100%;height:370px;">${varSelectedField['normalizationScript']}</wcomp:textarea>
							</c:when>
							<c:otherwise>
								<wcomp:textarea id="fieldScript${varSelectedField['eventTypeDetail'].id}" cols="" rows="" stateful="true" style="display:none;">${varSelectedField['normalizationScript']}</wcomp:textarea>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</div>
				Event Details
				<div class="sdmContainerClass" style="width:20%;height:400px;float:right;border-color:white;">
				<fmt:message key="field" var="varTitle"/>
					<div style="width:100%;height:373px;overflow:auto;">
					<sdm-web:roundedFrame title="${varTitle}" style="margin-bottom:10px;">
						<wcomp:datatable dataset="varEventFieldConfig" style="width:100%;border: 0px solid black;" htmlClass="auto_stripe" cellpadding="0" cellspacing="0">
							<tbody>
								<wcomp:row omitTrTag="true" selRowClass="selected">
									<c:set var="varSelectedClass" value="" />
									<c:if test="${absoluteRowNumber==varEventFieldConfig.currentRow}">
										<c:set var="varSelectedClass" value="selected"/>
									</c:if>
									<tr onclick="javascript:configuratorSelectField('${absoluteRowNumber}');" style="cursor:pointer;" class="${varSelectedClass}">
										<c:choose>
											<c:when test="${row['eventTypeDetail'].extension}"><fmt:message key="extension: returns List<MessageField>" var="varReturnTitle"/></c:when>
											<c:otherwise>
												<fmt:message key="field: returns " var="varReturnTitle"/>
												<c:choose>
													<c:when test="${!empty fn:substringBefore(fn:substringAfter(row['eventTypeDetail'].fieldPath,':'),':')}"><c:set value="${varReturnTitle}${fn:substringBefore(fn:substringAfter(row['eventTypeDetail'].fieldPath,':'),':')}" var="varReturnTitle"/></c:when>
													<c:otherwise><c:set value="${varReturnTitle}into ${fn:substringAfter(row['eventTypeDetail'].fieldPath,':')}" var="varReturnTitle"/></c:otherwise>
												</c:choose>
											</c:otherwise>
										</c:choose>
										<td title="${varReturnTitle}">
											<wcomp:hiddenInput id="fieldPath${row['eventTypeDetail'].id}" value="${row['eventTypeDetail'].fieldPath}"/>
											<wcomp:hiddenInput id="fieldExtension${row['eventTypeDetail'].id}" value="${row['eventTypeDetail'].extension}"/>
											<c:choose>
												<c:when test="${!empty fn:substringBefore(row['eventTypeDetail'].fieldPath,':')}"><c:out value="${fn:substringBefore(row['eventTypeDetail'].fieldPath,':')}"/></c:when>
												<c:otherwise><c:out value="${row['eventTypeDetail'].fieldPath}"/></c:otherwise>
											</c:choose>
										</td>
										<td title="<c:if test="${row['eventTypeDetail'].basic}">(default)</c:if> <fmt:message key="${row['eventTypeDetail'].description}"/> (<c:out value="${row['eventTypeDetail'].fieldPath}"/>)">
											<fmt:message key="${row['eventTypeDetail'].name}"/>
										</td>
									</tr>
								</wcomp:row>
							</tbody>
						</wcomp:datatable>
						</sdm-web:roundedFrame>				
					</div>
				</div>
				<wcomp-ext:displayFormErrors styleClass="sdmErrorClass" />
						
				<div style="width:100%;float:left;margin-top:15px;">
					<div id="pageButtons">
								<a class="boton" href="javascript:configuratorExport();"><fmt:message key="export"/></a>
								<a class="boton" href="javascript:configuratorTest();"><fmt:message key="test"/></a>
								<a class="boton" href="javascript:configuratorSave();"><fmt:message key="save"/></a>
					</div>
				</div>
				
			</c:when>
			<c:otherwise>
				<div class="sdmErrorClass"><fmt:message key="this event is not yet configured"/></div>
			</c:otherwise>
		</c:choose>
	</wcomp:form>
</c:if>

 --%>
