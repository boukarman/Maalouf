package tn.tunisietelecom.event.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.tunisietelecom.event.dao.SiteDao;
import tn.tunisietelecom.event.entities.Site;
import tn.tunisietelecom.event.service.SiteService;

@Service
public class SiteServiceImpl implements SiteService {

	@Autowired
	private SiteDao siteDao;

	@Transactional
	public void addSite(Site site) {
		this.siteDao.save(site);
	}

	@Transactional
	public void deleteSite(Site site) {
		this.siteDao.delete(site);
	}

	public List<Site> findAllSites() {
		return this.siteDao.findAll();
	}

	public Site findById(long id) {
		return this.siteDao.findById(id);
	}

	@Transactional
	public void update(Site site) {
		this.siteDao.update(site);
	}

	public List<Site> findSitesByPage(int firstResult, int maxResult) {
		return siteDao.findSitesByPage(firstResult, maxResult);
	}

	public long countSites() {
		return siteDao.countSites();
	}

}
