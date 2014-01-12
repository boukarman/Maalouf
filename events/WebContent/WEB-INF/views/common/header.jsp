<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<!-- jquery -->
<script type="text/javascript"
	src="<c:url value="/resources/js/jquery/jquery-1.9.1.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/jquery/jquery-ui.js"/>"></script>

<!-- <link href="<c:url value="/resources/js/extjs/resources/css/ext-all.css"/>"
	rel="stylesheet" type="text/css"></link> -->
<link href="<c:url value="/resources/js/extjs/resources/css/ext-all-neptune.css"/>"
	rel="stylesheet" type="text/css"></link>

<link rel="stylesheet" href="<c:url value="/resources/css/common/jquery-ui.css"/>" />
<link rel="stylesheet" href="<c:url value="/resources/css/common/jquery.simple-dtpicker.css"/>" />
<link rel="stylesheet" href="<c:url value="/resources/css/common/jquery.countdown.css"/>" />
<link rel="stylesheet" href="<c:url value="/resources/css/common/jquery.timepicker.css"/>" />
<link rel="stylesheet" href="<c:url value="/resources/css/common/common.css"/>" />
<link rel="stylesheet" href="<c:url value="/resources/css/common/layout.css"/>" />
<%-- <link rel="stylesheet" href="<c:url value="/resources/css/common/reset.css"/>" /> --%>
<link rel="stylesheet" href="<c:url value="/resources/css/common/style.css"/>" />
<link rel="stylesheet" href="<c:url value="/resources/css/common/header.css"/>" />

<link rel="stylesheet" href="<c:url value="/resources/css/event/event.css"/>" />
<link rel="stylesheet" href="<c:url value="/resources/css/site/site.css"/>" />
<link rel="stylesheet" href="<c:url value="/resources/css/local/local.css"/>" />
<link rel="stylesheet" href="<c:url value="/resources/css/user/user.css"/>" />
<link rel="stylesheet" href="<c:url value="/resources/css/piece/piece.css"/>" />
<link rel="stylesheet" href="<c:url value="/resources/css/chart/chart.css"/>" />
	
<!-- Extjs default blue theme  -->	
<script type="text/javascript"
	src="<c:url value="/resources/js/extjs/ext-all-debug.js"/>"></script>

<script type="text/javascript"
	src="<c:url value="/resources/js/extjs/ext-theme-neptune.js"/>"></script>

<script type="text/javascript"
	src="<c:url value="/resources/js/extjs/locale/ext-lang-fr.js"/>"></script>
		
<script type="text/javascript"
	src="<c:url value="/resources/js/jquery/jquery.simple-dtpicker.js"/>"></script>

<script type="text/javascript"
	src="<c:url value="/resources/js/jquery/jquery.countdown.js"/>"></script>

<script type="text/javascript"
	src="<c:url value="/resources/js/jquery/jquery.timepicker.js"/>"></script>

<script type="text/javascript"
	src="<c:url value="/resources/js/jquery/datepair.js"/>"></script>
		
<script type="text/javascript"
	src="<c:url value="/resources/js/jquery/jquery-ui-timepicker-addon.js"/>"></script>
	
<script type="text/javascript"
	src="<c:url value="/resources/js/jquery/jquery.form.js"/>"></script>
	
<!-- create names space -->
<script type="text/javascript">
	Ext.ns("tn.tunisietelecom", "tn.tunisietelecom.chart");
	tn.tunisietelecom.Constants = {};
	tn.tunisietelecom.MainView = {};
	tn.tunisietelecom.Event = {};
	tn.tunisietelecom.Chart = {};
	tn.tunisietelecom.Site = {};
	tn.tunisietelecom.Local = {};
	tn.tunisietelecom.Role = {};
	tn.tunisietelecom.User = {};
	tn.tunisietelecom.Piece = {};
	tn.tunisietelecom.Messages = {};
	tn.tunisietelecom.chart.CauseChart = {};
	tn.tunisietelecom.chart.AgentChart = {};
	tn.tunisietelecom.chart.SiteChart = {};
	tn.tunisietelecom.chart.LocalChart = {};
	tn.tunisietelecom.chart.PerformanceChart = {};
	tn.tunisietelecom.chart.PieceChart = {};
</script>

<!-- create names space -->
<script type="text/javascript">
	Ext.apply(tn.tunisietelecom.Constants, {
		createEvent: '<c:url value="/telecom/event/create" />',
		deleteEvent: '<c:url value="/telecom/event/delete" />',
		updateEventStatus: '<c:url value="/telecom/event/update/status" />',
		consultEvent: '<c:url value="/telecom/event/consult" />',
		consultEventElements: '<c:url value="/telecom/event/consult/elements" />',
		searchEvent: '<c:url value="/telecom/event/search" />',
		chartCause: '<c:url value="/telecom/event/chart/cause" />',
		chartCauseElements: '<c:url value="/telecom/event/chart/cause/elements" />',
		chartPiece: '<c:url value="/telecom/event/chart/piece" />',
		chartPieceElements: '<c:url value="/telecom/event/chart/piece/elements" />',
		chartAgent: '<c:url value="/telecom/event/chart/agent" />',
		chartAgentElements: '<c:url value="/telecom/event/chart/agent/elements" />',
		chartSite: '<c:url value="/telecom/event/chart/site" />',
		chartSiteElements: '<c:url value="/telecom/event/chart/site/elements" />',
		chartLocal: '<c:url value="/telecom/event/chart/local" />',
		chartLocalElements: '<c:url value="/telecom/event/chart/local/elements" />',
		chartPerformance: '<c:url value="/telecom/event/chart/performance" />',
		chartPerformanceElements: '<c:url value="/telecom/event/chart/performance/elements" />',
		chartPerformanceSeriesElements: '<c:url value="/telecom/event/chart/performance/series/elements" />',
		sites: '<c:url value="/telecom/event/chart/site/load" />',
		locals: '<c:url value="/telecom/event/chart/local/load" />',
		notReadEventIcon: '<c:url value="/resources/images/1370489145_mail.png" />',
		readEventIcon: '<c:url value="/resources/images/1370488398_delivery.png" />',
		startedEventIcon: '<c:url value="/resources/images/1370489323_running_process.png" />',
		finishedEventIcon: '<c:url value="/resources/images/1370489340_Process-Accept.png" />',
		consultNotifiedUser: '<c:url value="/telecom/event/consult/notified-user" />',
		consultWorkingInUser: '<c:url value="/telecom/event/consult/working-in-user" />',
		createSite: '<c:url value="/telecom/site/create" />',
		updateSite: '<c:url value="/telecom/site/update" />',
		deleteSite: '<c:url value="/telecom/site/delete" />',
		consultSite: '<c:url value="/telecom/site/consult" />',
		consultSiteElements: '<c:url value="/telecom/site/consult/elements" />',
		createLocal: '<c:url value="/telecom/local/create" />',
		updateLocal: '<c:url value="/telecom/local/update" />',
		deleteLocal: '<c:url value="/telecom/local/delete" />',
		consultLocal: '<c:url value="/telecom/local/consult" />',
		consultLocalElements: '<c:url value="/telecom/local/consult/elements" />',
		createUser: '<c:url value="/telecom/user/create" />',
		updateUser: '<c:url value="/telecom/user/update" />',
		deleteUser: '<c:url value="/telecom/user/delete" />',
		consultUser: '<c:url value="/telecom/user/consult" />',
		consultUserElements: '<c:url value="/telecom/user/consult/elements" />',
		createPiece: '<c:url value="/telecom/piece/create" />',
		updatePiece: '<c:url value="/telecom/piece/update" />',
		deletePiece: '<c:url value="/telecom/piece/delete" />',
		consultPiece: '<c:url value="/telecom/piece/consult" />',
		consultPieceElements: '<c:url value="/telecom/piece/consult/elements" />'
	});
</script>

<!-- message keys for javascript -->
<script type="text/javascript">
	window.$msg = tn.tunisietelecom.Messages;
	$msg['event.main.panel.title'] = '<s:message code="event.main.panel.title" />';
	$msg['event.main.panel.menu.title'] = '<s:message code="event.main.panel.menu.title" />';
	$msg['event.main.panel.content.title'] = '<s:message code="event.main.panel.content.title" />';
	$msg['event.main.panel.menu.item.event.label'] = '<s:message code="event.main.panel.menu.item.event.label" />';
	$msg['event.main.panel.menu.item.chart.label'] = '<s:message code="event.main.panel.menu.item.chart.label" />';
	$msg['event.main.panel.menu.item.event.create.label'] = '<s:message code="event.main.panel.menu.item.event.create.label" />';
	$msg['event.main.panel.menu.item.event.consult.label'] = '<s:message code="event.main.panel.menu.item.event.consult.label" />';
	$msg['event.main.panel.menu.item.event.search.label'] = '<s:message code="event.main.panel.menu.item.event.search.label" />';
	$msg['event.main.panel.menu.item.chart.cause.label'] = '<s:message code="event.main.panel.menu.item.chart.cause.label" />';
	$msg['event.main.panel.menu.item.chart.piece.label'] = '<s:message code="event.main.panel.menu.item.chart.piece.label" />';
	$msg['event.main.panel.menu.item.chart.agent.label'] = '<s:message code="event.main.panel.menu.item.chart.agent.label" />';
	$msg['event.main.panel.menu.item.chart.site.label'] = '<s:message code="event.main.panel.menu.item.chart.site.label" />';
	$msg['event.main.panel.menu.item.chart.local.label'] = '<s:message code="event.main.panel.menu.item.chart.local.label" />';
	$msg['event.main.panel.menu.item.chart.performance.label'] = '<s:message code="event.main.panel.menu.item.chart.performance.label" />';
	$msg['event.main.panel.menu.item.site.label'] = '<s:message code="event.main.panel.menu.item.site.label" />';
	$msg['event.main.panel.menu.item.site.create.label'] = '<s:message code="event.main.panel.menu.item.site.create.label" />';
	$msg['event.main.panel.menu.item.site.consult.label'] = '<s:message code="event.main.panel.menu.item.site.consult.label" />';
	$msg['event.main.panel.menu.item.local.label'] = '<s:message code="event.main.panel.menu.item.local.label" />';
	$msg['event.main.panel.menu.item.local.create.label'] = '<s:message code="event.main.panel.menu.item.local.create.label" />';
	$msg['event.main.panel.menu.item.local.consult.label'] = '<s:message code="event.main.panel.menu.item.local.consult.label" />';	
	$msg['event.main.panel.menu.item.user.label'] = '<s:message code="event.main.panel.menu.item.user.label" />';
	$msg['event.main.panel.menu.item.user.create.label'] = '<s:message code="event.main.panel.menu.item.user.create.label" />';
	$msg['event.main.panel.menu.item.user.consult.label'] = '<s:message code="event.main.panel.menu.item.user.consult.label" />';	
	$msg['event.main.panel.menu.item.piece.label'] = '<s:message code="event.main.panel.menu.item.piece.label" />';
	$msg['event.main.panel.menu.item.piece.create.label'] = '<s:message code="event.main.panel.menu.item.piece.create.label" />';
	$msg['event.main.panel.menu.item.piece.consult.label'] = '<s:message code="event.main.panel.menu.item.piece.consult.label" />';
	
</script>
	
<!-- application -->
<script type="text/javascript"
	src="<c:url value="/resources/js/app/User.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/app/Piece.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/app/Role.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/app/MainView.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/app/Event.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/app/Site.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/app/Local.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/app/Chart.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/app/chart/AgentChart.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/app/chart/SiteChart.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/app/chart/LocalChart.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/app/chart/PerformanceChart.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/app/chart/CauseChart.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/app/chart/PieceChart.js"/>"></script>

<sec:authorize access="isAuthenticated()">
	<script type="text/javascript">
		tn.tunisietelecom.Role.currentUserRole = '<sec:authentication property="principal.privilege" />';
		tn.tunisietelecom.User.currentUser = '<sec:authentication property="principal.userId" />';
	</script>
</sec:authorize>