package tn.tunisietelecom.event.dao;

import java.util.List;

import tn.tunisietelecom.event.entities.Site;

public interface SiteDao {

	void save(Site site);

	void delete(Site site);

	List<Site> findAll();

	List<Site> findSitesByPage(int firstResult, int maxResult);

	Site findById(long id);

	void update(Site site);

	long countSites();
}
