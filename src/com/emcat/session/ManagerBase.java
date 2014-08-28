package com.emcat.session;

import java.util.HashMap;
import java.util.Random;

import com.emcat.einterface.Container;

public abstract class ManagerBase implements Manager{
	
	
	Container parent;
	
	HashMap<String, Session> sessions = new HashMap<String, Session>();
	
	protected int maxInactiveInterval = 60;
	
	protected static final int SESSION_ID_BYTES = 16;
	
	 protected Random random = null;

	@Override
	public Container getContainer() {
		// TODO Auto-generated method stub
		return parent;
	}

	@Override
	public void setContainer(Container container) {
		// TODO Auto-generated method stub
		this.parent = container;
	}

	@Override
	public int getMaxInactiveInterval() {
		// TODO Auto-generated method stub
		return maxInactiveInterval;
	}

	@Override
	public void setMaxInactiveInterval(int interval) {
		// TODO Auto-generated method stub
		this.maxInactiveInterval = interval;
	}

	@Override
	public void add(Session session) {
		// TODO Auto-generated method stub
		synchronized (sessions) {
			sessions.put(session.getId(),session);
		}
	}

	@Override
	public Session createSession() {
		// TODO Auto-generated method stub
		Session session = new StandrdSession(this);
		session.setNew(true);
        session.setValid(true);
        session.setCreationTime(System.currentTimeMillis());
        session.setMaxInactiveInterval(this.maxInactiveInterval);
        String sessionId = generateSessionId();
        session.setId(sessionId);
		return session;
	}

	private String generateSessionId() {
		// TODO Auto-generated method stub
		StringBuffer id = new StringBuffer();
		for(int i=0;i<2;i++){
			 id.append(Integer.toHexString(new Random(System.currentTimeMillis()).nextInt()));
		}
		return id.toString();
	}
	

	@Override
	public Session findSession(String id) {
		// TODO Auto-generated method stub
		synchronized (sessions) {
			return sessions.get(id);
		}
	}

	@Override
	public Session[] findSessions() {
		// TODO Auto-generated method stub
		Session results[] = null;
		synchronized (sessions) {
			results = new Session[sessions.size()];
			return sessions.values().toArray(results);
		}
	}

	@Override
	public void remove(Session session) {
		// TODO Auto-generated method stub
		synchronized (sessions) {
			sessions.remove(session.getId());
		}
	}

}
