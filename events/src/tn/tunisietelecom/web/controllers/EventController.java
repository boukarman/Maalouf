package tn.tunisietelecom.web.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import tn.tunisietelecom.event.entities.Cause;
import tn.tunisietelecom.event.entities.Event;
import tn.tunisietelecom.event.entities.EventUser;
import tn.tunisietelecom.event.entities.EventUserId;
import tn.tunisietelecom.event.entities.Local;
import tn.tunisietelecom.event.entities.Piece;
import tn.tunisietelecom.event.entities.Site;
import tn.tunisietelecom.event.entities.SubcontractorType;
import tn.tunisietelecom.event.entities.User;
import tn.tunisietelecom.event.service.CauseService;
import tn.tunisietelecom.event.service.EventService;
import tn.tunisietelecom.event.service.EventUserService;
import tn.tunisietelecom.event.service.LocalService;
import tn.tunisietelecom.event.service.PieceService;
import tn.tunisietelecom.event.service.SiteService;
import tn.tunisietelecom.event.service.SubcontractorTypeService;
import tn.tunisietelecom.event.service.UserService;
import tn.tunisietelecom.event.utils.RoleType;
import tn.tunisietelecom.event.utils.TelecomConstants;
import tn.tunisietelecom.web.dto.ConnectedUser;
import tn.tunisietelecom.web.dto.ConsultEventDto;
import tn.tunisietelecom.web.dto.CreateEventDto;
import tn.tunisietelecom.web.dto.UserDto;

@Controller
@RequestMapping("/telecom")
public class EventController {
	
	@Autowired
	private SiteService siteService;
	
	@Autowired
	private LocalService localService;
	
	@Autowired
	private CauseService causeService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PieceService pieceService;
	
	@Autowired
	private EventService eventService;
	
	@Autowired
	private SubcontractorTypeService subcontractorTypeService;
	
	@Autowired
	private EventUserService eventUserService;
	
	@RequestMapping(value = "/main")
	public String telecomMainView() {
		return "telecom.main.view";
	}
	
	@RequestMapping(value = "/event/create")
	public String telecomEventCreate(
			@RequestParam(value = "site", required = false) Long siteId,
			@RequestParam(value = "local", required = false) Long idLocal,
			@RequestParam(value = "piece", required = false) Long idPiece,
			@RequestParam(value = "cause", required = false) Long idCause,
			@RequestParam(value = "responsible", required = false) Long idUser,
			@RequestParam(value = "eventDate", required = false) String eventDate,
			@RequestParam(value = "description", required = false) String description,
			@RequestParam(value = "subcontractorType", required = false) Long subcontractorId,
			Model model) throws ParseException {
		String targetView = "telecom.event.create";
		CreateEventDto dto = new CreateEventDto();
		if (siteId != null) {
			Site site = siteService.findById(siteId);
			dto.setSite(site);
			if (idLocal != null) {
				Local local = localService.findById(idLocal);
				dto.setLocal(local);
				if (idPiece != null) {
					Event event = new Event();
					
					User responsible = userService.findById(idUser);
					Piece piece = pieceService.findById(idPiece);
					Cause cause = causeService.findById(idCause);
					event.setResponsible(responsible);
					event.setPiece(piece);
					event.setCause(cause);
					SimpleDateFormat dateFormat = new SimpleDateFormat(
							TelecomConstants.DATE_FORMAT);
					Date convertedDate = dateFormat.parse(eventDate);
					event.setDateEvent(convertedDate);
					event.setDescription(description);
					ConnectedUser connectedUser = (ConnectedUser) SecurityContextHolder
							.getContext().getAuthentication().getPrincipal();
					User user = userService.findById(connectedUser.getUserId());
					event.setCreator(user);
					
					// save event
					event = eventService.addEvent(event);
					
					List<User> notifiedUsers = new ArrayList<User>();
					List<User> allUsers = userService.findAllUsers();
					List<EventUser> eventUserStatus = new ArrayList<EventUser>();
					for (User targetUser : allUsers) {
						boolean test1 = (targetUser.getSubcontractorType() != null)
								&& (targetUser.getSubcontractorType().getType()
										.getId() == subcontractorId);
						boolean test2 = targetUser.getRole().getIdRole() != Long
								.parseLong(RoleType.SUBCONTRACTOR.getType());
						if (test1 || test2) {
							EventUser eventUser = new EventUser(targetUser,
									event);
							eventUser.setStatus(0);
							eventUser.setRecievedDate(new Date());
							eventUserStatus.add(eventUser);
							// eventUserService.save(eventUser);
							notifiedUsers.add(targetUser);
						}
					}
					event.setNotifiedUsers(notifiedUsers);
					event.setEventUserStatus(eventUserStatus);
					eventService.update(event);
					
					targetView = "telecom.save.success";
				} else {
					SubcontractorType subcontractorType = new SubcontractorType();
					subcontractorType.setId(-1);
					subcontractorType.setType("---------------");
					List<SubcontractorType> subcontractorTypeList = new ArrayList<SubcontractorType>();
					subcontractorTypeList.add(subcontractorType);
					subcontractorTypeList.addAll(subcontractorTypeService
							.findAll());
					model.addAttribute("subcontractorTypes",
							subcontractorTypeList);
					model.addAttribute("pieces", local.getPieces());
					model.addAttribute("causes", causeService.findAllCauses());
					model.addAttribute("responsibles",
							userService.findAllResponsibles());
				}
				
			}
			Local localFirstEl = new Local();
			localFirstEl.setIdLocal(-1);
			localFirstEl.setLabLocal("---------------");
			List<Local> locals = new ArrayList<Local>();
			locals.add(localFirstEl);
			locals.addAll(site.getLocals());
			model.addAttribute("locals", locals);
		} else {
			Site siteFirstEl = new Site();
			siteFirstEl.setSiteId(-1);
			siteFirstEl.setLabSite("-----------");
			List<Site> sites = new ArrayList<Site>();
			sites.add(siteFirstEl);
			sites.addAll(siteService.findAllSites());
			model.addAttribute("sites", sites);
		}
		model.addAttribute("command", dto);
		
		return targetView;
	}
	
	@RequestMapping(value = "/event/consult")
	public String telecomEventConsult(Model model) {
		return "telecom.event.consult";
	}
	
	@RequestMapping(value = "/event/delete", method = RequestMethod.POST)
	@ResponseBody
	public boolean telecomEventDelete(@RequestParam("idEvent") Long idEvent) {
		eventService.deleteEvent(idEvent);
		return true;
	}
	
	@RequestMapping(value = "/event/update/status", method = RequestMethod.POST)
	@ResponseBody
	public boolean telecomEventUpdateStatus(
			@RequestParam("idEvent") Long idEvent,
			@RequestParam("status") Integer status) {
		ConnectedUser user = (ConnectedUser) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		eventService.updateStatus(idEvent, status, user.getUserId());
		return true;
	}
	
	@RequestMapping(value = "/event/consult/elements")
	@ResponseBody
	public Map<String, Object> telecomEventConsultElements(
			@RequestParam int limit, @RequestParam int page,
			@RequestParam int start) {
		Map<String, Object> result = new HashMap<String, Object>();
		List<ConsultEventDto> eventList = new ArrayList<ConsultEventDto>();
		ConnectedUser currentUser = (ConnectedUser) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		User user = userService.findById(currentUser.getUserId());
		// TODO: to check for the next time
		// EventUser eventUser : user.getEventUserStatus()
		for (EventUser eventUser : eventUserService.findByUserWithPage(user,
				start, limit)) {
			ConsultEventDto dto = new ConsultEventDto();
			dto.setIdEvent(eventUser.getId().getEvent().getIdEvent());
			dto.setSite(eventUser.getId().getEvent().getPiece().getLocal()
					.getSite().getLabSite());
			dto.setLocal(eventUser.getId().getEvent().getPiece().getLocal()
					.getLabLocal());
			dto.setCause(eventUser.getId().getEvent().getCause().getCauseDesc());
			dto.setPiece(eventUser.getId().getEvent().getPiece().getLabPiece());
			dto.setDescription(eventUser.getId().getEvent().getDescription());
			dto.setResponsible(eventUser.getId().getEvent().getResponsible()
					.getFullName());
			dto.setDate(eventUser.getId().getEvent().getDateEvent());
			long eventStatus = eventService.findCurrentEventStatus(eventUser.getId().getEvent());
			if (eventStatus != eventUser.getStatus() && eventStatus > 1) {
				dto.setStatus(eventStatus);
			}
			else {
				dto.setStatus(eventUser.getStatus());
			}
			dto.setOwnStatus(eventUser.getStatus());
			User working = eventUser.getId().getEvent().getWorkingInUser();
			if (working != null)
				dto.setWorkingInUserId(working.getUserId());
			
			eventList.add(dto);
		}
		result.put("events", eventList);
		result.put("totalCount", eventUserService.countEventUserWithUser(user));
		return result;
	}
	
	@RequestMapping(value = "/event/search")
	public String telecomEventSearch() {
		return "telecom.event.search";
	}
	
	@RequestMapping(value = "/event/consult/notified-user", method = RequestMethod.GET)
	@ResponseBody
	public List<UserDto> telecomEventConsultNotifiedUser(
			@RequestParam("idEvent") Long idEvent) {
		List<UserDto> notifiedUsers = new ArrayList<UserDto>();
		// List<User> users = eventService.findEventNotifiedUsers(idEvent);
		Event event = eventService.findById(idEvent);
		for (User user : event.getNotifiedUsers()) {
			EventUser eventUser = eventUserService.findById(new EventUserId(
					user, event));
			if (eventUser.getStatus() > 0) {
				UserDto dto = new UserDto();
				dto.setUserId(user.getUserId());
				dto.setFullName(user.getFullName());
				dto.setRole(user.getRole().getLabel());
				dto.setReadEventDate(eventUser.getReadDate());
				notifiedUsers.add(dto);
			}
		}
		return notifiedUsers;
	}
	
	@RequestMapping(value = "/event/consult/working-in-user", method = RequestMethod.GET)
	@ResponseBody
	public UserDto telecomEventConsultWorkingInUser(
			@RequestParam("idEvent") Long idEvent) {
		UserDto dto = null;
		User user = eventService.findEventWorkingInUser(idEvent);
		if (user != null) {
			Event event = eventService.findById(idEvent);
			dto = new UserDto();
			dto.setUserId(user.getUserId());
			dto.setFullName(user.getFullName());
			dto.setRole(user.getRole().getLabel());
			EventUser eventUser = eventUserService.findById(new EventUserId(user,
					event));
			dto.setStartEventDate(eventUser.getStartDate());
		}
		return dto;
	}
}