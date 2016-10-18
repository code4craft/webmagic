package us.codecraft.webmagic.multi;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Task;

public class MultiSitesTask implements Task {

	private Task task;
	private Site site;

	public MultiSitesTask(Task task, Site site) {
		this.task = task;
		this.site = site;
	}

	@Override
	public String getUUID() {
		return task.getUUID();
	}

	@Override
	public Site getSite() {
		return site;
	}

}
