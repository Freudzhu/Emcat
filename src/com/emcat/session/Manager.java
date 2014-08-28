package com.emcat.session;

import com.emcat.einterface.Container;

public interface Manager {
	public Container getContainer();
	public void setContainer(Container container);
	public int getMaxInactiveInterval();
	public void setMaxInactiveInterval(int interval);
	public void add(Session session);
	public Session createSession();
	public Session findSession(String id);
	public Session[] findSessions();
	public void remove(Session session);
}
