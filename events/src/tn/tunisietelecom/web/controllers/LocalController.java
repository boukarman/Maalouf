package tn.tunisietelecom.web.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import tn.tunisietelecom.event.entities.Local;
import tn.tunisietelecom.event.entities.Site;
import tn.tunisietelecom.event.service.LocalService;
import tn.tunisietelecom.event.service.SiteService;
import tn.tunisietelecom.web.dto.ConsultLocalDto;
import tn.tunisietelecom.web.dto.CreateLocalDto;

@Controller
@RequestMapping("/telecom/local")
public class LocalController {

	@Autowired
	private LocalService localService;

	@Autowired
	private SiteService siteService;

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String telecomLocalCreateForm(Model model) {
		model.addAttribute("sites", siteService.findAllSites());
		model.addAttribute("command", new CreateLocalDto());
		return "telecom.local.create";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String telecomLocalCreate(
			@RequestParam(value = "siteId", required = false) Long siteId,
			@RequestParam(value = "labLocal", required = false) String labLocal,
			@RequestParam(value = "position", required = false) String position) {
		Local local = new Local();

		Site site = siteService.findById(siteId);
		local.setPosition(position);
		local.setLabLocal(labLocal);
		local.setSite(site);
		localService.addLocal(local);
		return "telecom.save.success";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String telecomSiteCreate(
			@RequestParam(value = "idLocal", required = false) Long idLocal,
			@RequestParam(value = "siteId", required = false) Long siteId,
			@RequestParam(value = "labLocal", required = false) String labLocal,
			@RequestParam(value = "position", required = false) String position) {
		Local local = localService.findById(idLocal);
		Site site = siteService.findById(siteId);
		local.setPosition(position);
		local.setLabLocal(labLocal);
		local.setSite(site);
		localService.update(local);
		return "telecom.save.success";
	}

	@RequestMapping(value = "/consult", method = RequestMethod.GET)
	public String telecomLocalConsult(Model model) {
		return "telecom.local.consult";
	}

	@RequestMapping(value = "/consult/elements")
	@ResponseBody
	public Map<String, Object> telecomLocalConsultElements(
			@RequestParam int limit, @RequestParam int page,
			@RequestParam int start, Model model) {
		Map<String, Object> result = new HashMap<String, Object>();
		List<ConsultLocalDto> locals = new ArrayList<ConsultLocalDto>();
		for (Local local : localService.findLocalsByPage(start, limit)) {
			ConsultLocalDto dto = new ConsultLocalDto();
			BeanUtils.copyProperties(local, dto);
			dto.setSiteId(local.getSite().getSiteId());
			dto.setLabSite(local.getSite().getLabSite());
			locals.add(dto);
		}
		result.put("locals", locals);
		result.put("totalCount", localService.countLocals());
		return result;
	}

	@RequestMapping(value = "/delete")
	@ResponseBody
	public boolean telecomLocalDelete(@RequestParam("idLocal") Long idLocal) {
		Local local = localService.findById(idLocal);
		localService.deleteLocal(local);
		return true;
	}

}
