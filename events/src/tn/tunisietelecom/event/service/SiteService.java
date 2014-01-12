package tn.tunisietelecom.event.service;

import java.util.List;

import tn.tunisietelecom.event.entities.Site;

public interface SiteService {

	void addSite(Site site);

	void deleteSite(Site site);

	List<Site> findAllSites();

	Site findById(long id);

	List<Site> findSitesByPage(int firstResult, int maxResult);

	void update(Site site);

	long countSites();
}
