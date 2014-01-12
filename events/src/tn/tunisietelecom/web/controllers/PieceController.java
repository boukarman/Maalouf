package tn.tunisietelecom.web.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import tn.tunisietelecom.event.entities.Local;
import tn.tunisietelecom.event.entities.Piece;
import tn.tunisietelecom.event.entities.Site;
import tn.tunisietelecom.event.service.LocalService;
import tn.tunisietelecom.event.service.PieceService;
import tn.tunisietelecom.event.service.SiteService;
import tn.tunisietelecom.event.utils.TelecomConstants;
import tn.tunisietelecom.web.dto.PieceConsultDto;
import tn.tunisietelecom.web.dto.PieceCreateDto;

@Controller
@RequestMapping("/telecom/piece")
public class PieceController {

	@Autowired
	private PieceService pieceService;

	@Autowired
	private SiteService siteService;

	@Autowired
	private LocalService localService;

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String telecomPieceCreateForm(Model model) {
		Site siteFirstEl = new Site();
		siteFirstEl.setSiteId(-1);
		siteFirstEl.setLabSite("-----------");
		List<Site> sites = new ArrayList<Site>();
		sites.add(siteFirstEl);
		sites.addAll(siteService.findAllSites());
		model.addAttribute("sites", sites);
		model.addAttribute("command", new PieceCreateDto());
		return "telecom.piece.create";
	}

	@RequestMapping(value = "/create", params = "local", method = RequestMethod.GET)
	public String telecomPieceCreateForm(
			@RequestParam(value = "site", required = false) Long siteId,
			Model model) {
		PieceCreateDto dto = new PieceCreateDto();
		if (siteId != null) {
			Site site = siteService.findById(siteId);
			dto.setSite(site);
			Local localFirstEl = new Local();
			localFirstEl.setIdLocal(-1);
			localFirstEl.setLabLocal("---------------");
			List<Local> locals = new ArrayList<Local>();
			locals.add(localFirstEl);
			locals.addAll(site.getLocals());
			model.addAttribute("locals", locals);
		}
		model.addAttribute("command", dto);
		return "telecom.piece.create";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String telecomPieceCreateForm(
			@ModelAttribute("command") PieceCreateDto pieceForm,
			BindingResult bindingResult, Model model) throws ParseException {
		Piece piece = new Piece();
		String[] ignoreProperties = { "site", "idLocal", "establishementDate" };
		BeanUtils.copyProperties(pieceForm, piece, ignoreProperties);
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				TelecomConstants.DATE_FORMAT);
		Date convertedDate = dateFormat
				.parse(pieceForm.getEstablishementDate());
		piece.setEstablishementDate(convertedDate);
		Local local = localService.findById(pieceForm.getIdLocal());
		piece.setLocal(local);
		pieceService.addPiece(piece);

		local.getPieces().add(piece);
		localService.update(local);

		return "telecom.save.success";
	}

	@RequestMapping(value = "/consult", method = RequestMethod.GET)
	public String telecomPieceConsult(Model model) {
		return "telecom.piece.consult";
	}

	@RequestMapping(value = "/consult/elements")
	@ResponseBody
	public Map<String, Object> telecomPieceConsultElements(
			@RequestParam int limit, @RequestParam int page,
			@RequestParam int start, Model model) {
		Map<String, Object> result = new HashMap<String, Object>();
		List<PieceConsultDto> pieces = new ArrayList<PieceConsultDto>();
		String[] ignoreProperties = { "periodicity", "lastInter", "local" };
		for (Piece piece : pieceService.findPiecesByPage(start, limit)) {
			PieceConsultDto dto = new PieceConsultDto();
			BeanUtils.copyProperties(piece, dto, ignoreProperties);
			dto.setLabLocal(piece.getLocal().getLabLocal());
			dto.setLabSite(piece.getLocal().getSite().getLabSite());
			pieces.add(dto);
		}
		result.put("pieces", pieces);
		result.put("totalCount", pieceService.countPieces());
		return result;
	}

	@RequestMapping(value = "/delete")
	@ResponseBody
	public boolean telecomSiteDelete(@RequestParam("idPiece") Long idPiece) {
		Piece piece = pieceService.findById(idPiece);
		Local local = piece.getLocal();
		local.getPieces().remove(piece);
		localService.update(local);

		pieceService.deletePiece(piece);
		return true;
	}
}
