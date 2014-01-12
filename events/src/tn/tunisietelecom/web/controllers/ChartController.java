package tn.tunisietelecom.web.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import tn.tunisietelecom.event.entities.Cause;
import tn.tunisietelecom.event.entities.Event;
import tn.tunisietelecom.event.entities.EventUser;
import tn.tunisietelecom.event.entities.EventUserId;
import tn.tunisietelecom.event.entities.Local;
import tn.tunisietelecom.event.entities.Site;
import tn.tunisietelecom.event.entities.User;
import tn.tunisietelecom.event.service.CauseService;
import tn.tunisietelecom.event.service.EventService;
import tn.tunisietelecom.event.service.EventUserService;
import tn.tunisietelecom.event.service.LocalService;
import tn.tunisietelecom.event.service.SiteService;
import tn.tunisietelecom.event.service.UserService;
import tn.tunisietelecom.web.dto.AgentChartDto;
import tn.tunisietelecom.web.dto.CauseChartDto;
import tn.tunisietelecom.web.dto.EventSerieDto;
import tn.tunisietelecom.web.dto.LocalChartDto;
import tn.tunisietelecom.web.dto.LocalDto;
import tn.tunisietelecom.web.dto.PerformanceChartDto;
import tn.tunisietelecom.web.dto.SiteChartDto;
import tn.tunisietelecom.web.dto.SiteDto;

@Controller
@RequestMapping("/telecom/event/chart")
public class ChartController {

	@Autowired
	private CauseService causeService;

	@Autowired
	private EventService eventService;

	@Autowired
	private EventUserService eventUserService;

	@Autowired
	private SiteService siteService;

	@Autowired
	private LocalService localService;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/piece")
	public String telecomChartPiece(Model model) {
		return "telecom.chart.piece";
	}

	@RequestMapping(value = "/site/load")
	@ResponseBody
	public List<SiteDto> siteLoad(Model model) {
		List<SiteDto> dtos = new ArrayList<SiteDto>();
		dtos.add(new SiteDto(-1, "Tout les sites"));
		List<Site> sites = siteService.findAllSites();
		for (Site site : sites) {
			dtos.add(new SiteDto(site.getSiteId(), site.getLabSite()));
		}
		return dtos;
	}

	@RequestMapping(value = "/local/load")
	@ResponseBody
	public List<LocalDto> localLoad(
			@RequestParam("selectedSite") Long selectedSite) {
		List<LocalDto> dtos = new ArrayList<LocalDto>();
		dtos.add(new LocalDto(-1, "Tout les locaux"));
		List<Local> locals = null;
		if (selectedSite != null) {
			Site site = siteService.findById(selectedSite);
			locals = site.getLocals();
		} else {
			locals = localService.findAllLocals();
		}
		for (Local local : locals) {
			dtos.add(new LocalDto(local.getIdLocal(), local.getLabLocal()));
		}
		return dtos;
	}

	@RequestMapping(value = "/cause")
	public String telecomChartCause(Model model) {
		return "telecom.chart.cause";
	}

	@RequestMapping(value = "/cause/elements")
	@ResponseBody
	public List<CauseChartDto> telecomChartCauseElements(
			@RequestParam("site") Long site, @RequestParam("local") Long local,
			@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd'T'HH:mm:SS");
		Date convertedStartDate = dateFormat.parse(startDate);
		Date convertedEndDate = dateFormat.parse(endDate);
		List<Cause> causes = causeService.findAllCauses();
		List<CauseChartDto> dtos = new ArrayList<CauseChartDto>();
		for (Cause cause : causes) {
			CauseChartDto dto = new CauseChartDto();
			dto.setCause(cause.getCauseDesc());
			dto.setNumber(eventService.countEventNumberWithCause(
					cause.getCauseId(), local, convertedStartDate,
					convertedEndDate));
			dtos.add(dto);
		}
		return dtos;
	}

	@RequestMapping(value = "/agent")
	public String telecomChartAgent(Model model) {
		return "telecom.chart.agent";
	}

	@RequestMapping(value = "/agent/elements")
	@ResponseBody
	public List<AgentChartDto> telecomChartAgentElements(
			@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate) throws ParseException {
		List<AgentChartDto> dtos = new ArrayList<AgentChartDto>();
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd'T'HH:mm:SS");
		Date convertedStartDate = dateFormat.parse(startDate);
		Date convertedEndDate = dateFormat.parse(endDate);
		List<User> agents = userService.findEESAgentAndSubcontrator();
		for (User agent : agents) {
			AgentChartDto dto = new AgentChartDto();
			dto.setFullName(agent.getFullName());
			dto.setNumber(eventService.countEventNumberWithAgents(agent,
					convertedStartDate, convertedEndDate));
			dtos.add(dto);
		}
		return dtos;
	}

	@RequestMapping(value = "/site")
	public String telecomChartSite(Model model) {
		return "telecom.chart.site";
	}

	@RequestMapping(value = "/site/elements")
	@ResponseBody
	public List<SiteChartDto> telecomChartSiteElements() {
		return null;
	}

	@RequestMapping(value = "/local")
	public String telecomChartLocal(Model model) {
		return "telecom.chart.local";
	}

	@RequestMapping(value = "/local/elements")
	@ResponseBody
	public List<LocalChartDto> telecomChartLocalElements() {
		return null;
	}

	@RequestMapping(value = "/performance")
	public String telecomChartPerformance(Model model) {
		return "telecom.chart.performance";
	}

	@RequestMapping(value = "/performance/series/elements")
	@ResponseBody
	public List<EventSerieDto> telecomChartPerformance(
			@RequestParam("events") List<Long> events) {
		List<EventSerieDto> dtos = new ArrayList<EventSerieDto>();
		for (Long eventId : events) {
			Event event = eventService.findById(eventId);
			EventUserId id = new EventUserId(event.getWorkingInUser(), event);
			EventUser eventUser = eventUserService.findById(id);
			EventSerieDto dto = new EventSerieDto();
			dto.setCause(event.getCause().getCauseDesc());
			dto.setDescription(event.getDescription());
			dto.setLocal(event.getPiece().getLocal().getLabLocal());
			dto.setPiece(event.getPiece().getLabPiece());
			dto.setSite(event.getPiece().getLocal().getSite().getLabSite());
			dto.setWorkingInUser(event.getWorkingInUser().getFullName());
			dto.setStartDate(eventUser.getStartDate());
			dto.setFinishedDate(eventUser.getFinishedDate());
			dtos.add(dto);
		}
		return dtos;
	}

	@RequestMapping(value = "/performance/elements")
	@ResponseBody
	public List<PerformanceChartDto> telecomChartPerformanceElements(
			@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd'T'HH:mm:SS");
		// Date convertedStartDate = dateFormat.parse(startDate);
		// Date convertedEndDate = dateFormat.parse(endDate);
		Map<String, Object> chartElements = eventService
				.retrieveChartPerformance();
		Map<String, Integer> results = (Map<String, Integer>) chartElements
				.get("chartCriteria");
		Map<String, List<Long>> relatedEvents = (Map<String, List<Long>>) chartElements
				.get("relatedEvents");

		List<PerformanceChartDto> performances = new ArrayList<PerformanceChartDto>();
		for (Map.Entry<String, Integer> entry : results.entrySet()) {
			PerformanceChartDto dto = new PerformanceChartDto(entry.getKey(),
					entry.getValue());
			dto.setEvents(relatedEvents.get(entry.getKey()));
			performances.add(dto);
		}
		return performances;
	}
}
