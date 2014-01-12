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

import tn.tunisietelecom.event.entities.Site;
import tn.tunisietelecom.event.service.SiteService;
import tn.tunisietelecom.web.dto.SiteDto;

@Controller
@RequestMapping("/telecom/site")
public class SiteController {

	@Autowired
	private SiteService siteService;

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String telecomSiteCreateForm(Model model) {
		model.addAttribute("command", new SiteDto());
		return "telecom.site.create";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String telecomSiteCreate(
			@RequestParam(value = "labSite", required = false) String labSite,
			@RequestParam(value = "address", required = false) String address) {
		Site site = new Site();
		site.setLabSite(labSite);
		site.setAddress(address);
		siteService.addSite(site);
		return "telecom.save.success";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String telecomSiteCreate(
			@RequestParam(value = "siteId", required = false) Long siteId,
			@RequestParam(value = "labSite", required = false) String labSite,
			@RequestParam(value = "address", required = false) String address) {
		Site site = siteService.findById(siteId);
		site.setLabSite(labSite);
		site.setAddress(address);
		siteService.update(site);
		return "telecom.save.success";
	}

	@RequestMapping(value = "/consult", method = RequestMethod.GET)
	public String telecomSiteConsult(Model model) {
		return "telecom.site.consult";
	}

	@RequestMapping(value = "/consult/elements")
	@ResponseBody
	public Map<String, Object> telecomSiteConsultElements(
			@RequestParam int limit, @RequestParam int page,
			@RequestParam int start, Model model) {
		Map<String, Object> result = new HashMap<String, Object>();
		List<SiteDto> sites = new ArrayList<SiteDto>();
		for (Site site : siteService.findSitesByPage(start, limit)) {
			SiteDto dto = new SiteDto();
			BeanUtils.copyProperties(site, dto);
			sites.add(dto);
		}
		result.put("sites", sites);
		result.put("totalCount", siteService.countSites());
		return result;
	}

	@RequestMapping(value = "/delete")
	@ResponseBody
	public boolean telecomSiteDelete(@RequestParam("siteId") Long siteId) {
		Site site = siteService.findById(siteId);
		siteService.deleteSite(site);
		return true;
	}

}
