package us.codecraft.webmagic.scheduler.db;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.scheduler.Scheduler;

/**
 * A scheduler storing requests into a database. Tables have to be created first using constants ending by "sql" on this class. This scheduler also contains a
 * table webmagic_schedulercontrol that will allow to stop the scheduler
 * 
 * @author pascal
 *
 */
public class DatabaseScheduler implements Scheduler {
	public final static String tablesql = "create table webmagic_request(request bytea, url text, priority integer, done boolean, lastaccess timestamp, domain text);create index webmagic_request_url on webmagic_request(url);create index webmagic_request_done on webmagic_request(done);";
	public final static String tabledomainssql = "create table webmagic_domain(domain text, lastaccess timestamp);";
	public final static String tableschedulercontrolssql = "create table webmagic_schedulercontrol(running boolean not null default false,enabled boolean not null default true); create table webmagic_schedulercontrol(running boolean not null default false,enabled boolean not null default true); insert into webmagic_schedulercontrol() values();";
	protected Logger logger = LoggerFactory.getLogger(getClass());

	private DataSource dataSource;

	public DatabaseScheduler(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	@Override
	public void push(Request request, Task task) {
		if (existsUrl(request.getUrl())) {
			return;
		}
		String domain;
		try {
			domain = new URL(request.getUrl()).getHost();
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
		Connection cnx = null;
		try {
			cnx = dataSource.getConnection();
			byte[] bytes = serializeRequest(request);
			PreparedStatement insert = null;
			try {
				insert = cnx.prepareStatement("insert into webmagic_request(request, url, priority, done, lastaccess, domain) values (?, ? , ?, false, ?, ? )");
				insert.setBytes(1, bytes);
				insert.setString(2, request.getUrl());
				insert.setLong(3, request.getPriority());
				insert.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
				insert.setString(5, domain);
				insert.executeUpdate();
			} finally {
				closeStatement(insert);

			}
			int nbDomain;
			PreparedStatement selectDomain = null;
			try {
				selectDomain = cnx.prepareStatement("select count(*) from webmagic_domain where domain=?");
				selectDomain.setString(1, domain);
				ResultSet rs = null;
				try {
					rs = selectDomain.executeQuery();
					rs.next();
					nbDomain = rs.getInt(1);
				} finally {
					closeResultSet(rs);
				}
			} finally {
				closeStatement(selectDomain);

			}
			if (nbDomain == 0) {
				insert = null;
				try {
					insert = cnx.prepareStatement("insert into webmagic_domain(domain, lastAccess) values (?, '1970-01-01')");
					insert.setString(1, domain);
					insert.executeUpdate();
				} finally {
					closeStatement(insert);

				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			closeConnexion(cnx);
		}
	}

	public void setRunning() {
		Connection cnx = null;
		try {
			cnx = dataSource.getConnection();
			PreparedStatement select = null;
			try {
				select = cnx.prepareStatement("update webmagic_schedulercontrol set running = true");
				select.executeUpdate();
			} finally {
				closeStatement(select);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			closeConnexion(cnx);
		}
	}

	private void closeConnexion(Connection cnx) {
		if (cnx != null) {
			try {
				cnx.close();
			} catch (SQLException e) {
				logger.warn("Cannot close ", e);
			}
		}
	}

	private void closeStatement(PreparedStatement select) {
		if (select != null) {
			try {
				select.close();
			} catch (SQLException e) {
				logger.warn("Cannot close ", e);
			}
		}
	}

	private void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				logger.warn("Cannot close ", e);
			}
		}
	}

	public void setNotRunning() {
		Connection cnx = null;
		try {
			cnx = dataSource.getConnection();
			PreparedStatement select = null;
			try {
				select = cnx.prepareStatement("update webmagic_schedulercontrol set running = false");
				select.executeUpdate();
			} finally {
				closeStatement(select);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			closeConnexion(cnx);
		}
	}

	public boolean mayContinue() {
		Connection cnx = null;
		try {
			cnx = dataSource.getConnection();
			PreparedStatement select = null;
			try {
				select = cnx.prepareStatement("select enabled from webmagic_schedulercontrol");
				ResultSet rs = null;
				try {
					rs = select.executeQuery();
					rs.next();
					boolean enabled = rs.getBoolean("enabled");
					return enabled;
				} finally {
					closeResultSet(rs);
				}
			} finally {
				closeStatement(select);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			closeConnexion(cnx);
		}
	}

	@Override
	public Request poll(Task task) {
		if (!mayContinue()) {
			// Got stop order from database
			return null;
		}
		Connection cnx = null;
		try {
			cnx = dataSource.getConnection();
			// Poll a request first
			Request request = getRequest(cnx,
					"select r.request,priority from webmagic_request r join webmagic_domain d on d.domain=r.domain where not done order by d.lastaccess , priority desc, random() limit 1");
			if (request == null) {
				return null;
			}
			String srcUrl = request.getUrl();
			String fixedUrl = fixUrl(srcUrl);
			request.setUrl(fixedUrl);
			String domain = new URL(fixedUrl).getHost();
			PreparedStatement insert = null;
			try {
				insert = cnx.prepareStatement("update webmagic_domain set lastAccess=now() where domain = ?");
				insert.setString(1, domain);
				insert.executeUpdate();
			} finally {
				closeStatement(insert);
			}
			updateRequestToDone(cnx, srcUrl);
			return request;

		} catch (SQLException e) {
			throw new RuntimeException(e);

		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		} finally {
			closeConnexion(cnx);
		}
	}

	protected String fixUrl(String url) {
		return url.replace(" ", "%20");
	}

	private void updateRequestToDone(Connection cnx, String url) throws SQLException {
		PreparedStatement insert = null;
		try {
			insert = cnx.prepareStatement("update webmagic_request set done = true, lastaccess=? where url = ?");
			insert.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
			insert.setString(2, url);
			insert.executeUpdate();
		} finally {
			closeStatement(insert);
		}
	}

	private Request getRequest(Connection cnx, String sql, Object... params) throws SQLException {
		PreparedStatement select = null;
		try {
			select = cnx.prepareStatement(sql);
			if (params.length > 0) {
				for (int i = 0; i < params.length; i++) {
					select.setObject(i, params[i]);
				}
			}
			ResultSet resultSet = null;
			try {
				resultSet = select.executeQuery();
				if (!resultSet.next()) {
					return null;
				}
				Request request = unserializeRequest(resultSet.getBytes(1));
				int priority = resultSet.getInt(2);
				request.setPriority(priority);
				return request;
			} finally {
				closeResultSet(resultSet);
			}
		} finally {
			closeStatement(select);
		}
	}

	private boolean existsUrl(String url) {
		Connection cnx = null;
		try {
			cnx = dataSource.getConnection();
			PreparedStatement select = null;
			try {
				select = cnx.prepareStatement("select count(*) from webmagic_request where url = ?");
				select.setString(1, url);
				select.executeQuery();
				ResultSet result = null;
				try {
					result = select.getResultSet();
					result.next();
					return result.getInt(1) > 0;

				} finally {
					closeResultSet(result);
				}
			} finally {
				closeStatement(select);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			closeConnexion(cnx);
		}
	}

	private byte[] serializeRequest(Request request) {
		ByteArrayOutputStream bos = null;
		ObjectOutputStream oos = null;
		try {
			bos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(bos);
			oos.writeObject(request);
			return bos.toByteArray();
		} catch (IOException ioe) {
			throw new RuntimeException(ioe);
		} finally {
			try {
				if (oos != null) {
					oos.close();
				}
				if (bos != null) {
					bos.close();
				}
			} catch (IOException e) {
				logger.warn("Cannot close ", e);
			}
		}
	}

	private Request unserializeRequest(byte[] bytes) {
		InputStream is = null;
		ObjectInputStream ois = null;
		try {
			is = new ByteArrayInputStream(bytes);
			ois = new ObjectInputStream(is);
			return (Request) ois.readObject();
		} catch (IOException ioe) {
			throw new RuntimeException(ioe);
		} catch (ClassNotFoundException ioe) {
			throw new RuntimeException(ioe);
		} finally {
			try {
				if (is != null) {
					is.close();
				}
				if (ois != null) {
					ois.close();
				}
			} catch (IOException e) {
				logger.warn("Cannot close ", e);
			}
		}
	}

}
