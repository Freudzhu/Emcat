package com.emcat.loader;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import com.emcat.lifecycle.LifeCycle;
import com.emcat.lifecycle.LifeCycleException;
import com.emcat.lifecycle.LifecycleListener;


public class WebAppClassLoader extends URLClassLoader implements LifeCycle{

	public WebAppClassLoader(ClassLoader parent) {
		super(new URL[0], parent);
		system = getSystemClassLoader();
		this.parent = parent;
	}
	
	protected HashMap resourceEntries = new HashMap();
	
	protected ArrayList<String> repositories = new ArrayList<String>();
	 
	 /**
     * The parent class loader.
     */
    private ClassLoader parent = null;


    /**
     * The system class loader.
     */
    private ClassLoader system = null;


    /**
     * Has this component been started?
     */
    protected boolean started = false; 
    
    
    public void addRepository(String repository) {

        	repositories.add(repository);

    }
    
    public Class loadClass(String name)
            throws ClassNotFoundException {
    	 Class clazz = null;
    	 if (!started) {
             throw new ClassNotFoundException(name);
         }
    	 //search from the local cache
    	 clazz = findLoadedClass0(name);
         if (clazz != null) {
             return (clazz);
         }
         //search from the parent local cache
         clazz = findLoadedClass(name);
         if(clazz != null)
        	 return (clazz);
         //search by the systemClassLoader
         try {
             clazz = system.loadClass(name);
             if (clazz != null) {
                 return (clazz);
             }
         } catch (ClassNotFoundException e) {
             // Ignore
         }
         clazz = findClass1(name);
		 if (clazz != null) {
		     return (clazz);
		 }
         throw new ClassNotFoundException(name);

    }
    private Class findLoadedClass0(String name){
    	if(name == null){
    		return (null);
    	}
    	Class clz = (Class) resourceEntries.get(name);
    	if(clz != null)
    		return clz;
    	return (null);
    }
    private Class findClass1(String name){
    	
    	if(name == null){
    		return null;
    	}
        for(String resp : repositories){
        	String userPathString = System.getProperty("user.dir");
        	userPathString += File.separator + resp;
        	File file = new File(userPathString);
        	try {
				super.addURL(file.toURL());
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        }
        Class clz = null;
		try {
			clz = super.loadClass(name);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if(clz!=null){
        	resourceEntries.put(name, clz);
        }
        return clz;
        
    }

	@Override
	public void start() throws LifeCycleException {
		// TODO Auto-generated method stub
		started = true;
	}

	@Override
	public void stop() throws LifeCycleException {
		// TODO Auto-generated method stub
		started = false;
		resourceEntries.clear();
		
	}

	@Override
	public void addLifeCycleListener(LifecycleListener listener)
			throws LifeCycleException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeLifeCycleListener(LifecycleListener listener)
			throws LifeCycleException {
		// TODO Auto-generated method stub
		
	}

}
