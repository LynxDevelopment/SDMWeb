package com.lynxit.xweb.i18n;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.lynxit.utils.SortedProperties;
import com.lynxit.utils.fileobserver.FileChangedEvent;
import com.lynxit.utils.fileobserver.FileChangesListener;

/**
 * @author luca.zenti
 * 
 */
public class DynamicResourceBundle extends ResourceBundle implements FileChangesListener
{
    private static final Logger logger_ = Logger.getLogger(DynamicResourceBundle.class);
    
    private Properties properties_;
    private String baseName_;
    private Locale locale_;
    private boolean isFallback_;

    private boolean isDirty_;
    private long selfChangedTimestamp_;
    
    /**
     * Construct a new localized bundle instance.
     * 
     * @param baseName
     * @param locale
     * @throws IOException
     */
    public DynamicResourceBundle(String baseName, Locale locale)
    {
        super();
        baseName_ = baseName;
        locale_ = locale;
        isFallback_ = locale == null;
        init();
    }

    /**
     * Construct a new fallback (no locale set) bundle instance.
     * 
     * @param baseName
     */
    public DynamicResourceBundle(String baseName)
    {
        this(baseName, null);        
    }
    
    /**
     * Initialization shared across constructors.
     * 
     * @throws IOException
     */
    private void init()
    {
        // set the parent if needed
        if(locale_ != null)
        {
            if(!StringUtils.isEmpty(locale_.getVariant()))
                setParent(I18nSystem.getInstance().getBundle(baseName_, new Locale(locale_.getLanguage(), locale_.getCountry())));
            else if(!StringUtils.isEmpty(locale_.getCountry()))
                setParent(I18nSystem.getInstance().getBundle(baseName_, new Locale(locale_.getLanguage())));
            else if(!StringUtils.isEmpty(locale_.getLanguage()))
                setParent(I18nSystem.getInstance().getBundle(baseName_));
        }
        
        File bundleFile = I18nSystem.getInstance().getBundleFile(baseName_, locale_);
        properties_ = new SortedProperties();
        
        if(bundleFile.exists())
        {
            try
            {
                loadFromFile(bundleFile);
            }
            catch (IOException e)
            {
                logger_.error("Unable to read properties from file " + bundleFile, e);
            }
        }
        
        // add self as a listener of changes on the file
        I18nSystem.getInstance().addBundleListener(bundleFile, this);

    }

    /**
     * Loads the map from the given properties file.
     * 
     * @param bundleFile
     * @throws IOException
     */
    private void loadFromFile(File bundleFile) throws IOException
    {
        properties_.clear();
        // load properties from file
        InputStream inStream = new BufferedInputStream(new FileInputStream(bundleFile));
        try
        {
            properties_.load(inStream);
        }
        finally
        {
            inStream.close();
        }
    }

    /**
     * @param key
     * @return
     * @see java.util.ResourceBundle#handleGetObject(java.lang.String)
     */
    @Override
    protected Object handleGetObject(String key)
    {
        if(key == null)
            return "";
        Object object = properties_.get(key);
        if(object == null && isFallback_)
            return key;
        return object;
    }

    /**
     * @return
     * @see java.util.ResourceBundle#getKeys()
     */
	@Override
    @SuppressWarnings("unchecked")
    public Enumeration<String> getKeys()
    {
        return (Enumeration<String>) properties_.propertyNames();
    }
    
    @SuppressWarnings("unchecked")
    public Collection getKeySet()
    {
        return properties_.keySet();
    }
    
    /**
     * @return
     * @see java.util.ResourceBundle#getLocale()
     */
    @Override
    public Locale getLocale()
    {
        return locale_;
    }

    public DynamicResourceBundle getParent()
    {
        return (DynamicResourceBundle) parent;
    }
    
    /**
     * @param event
     * @see com.lynxit.utils.fileobserver.FileChangesListener#fileCreated(com.lynxit.utils.fileobserver.FileChangedEvent)
     */
    public void fileCreated(FileChangedEvent event)
    {
        try
        {
            loadFromFile(event.getFile());
        }
        catch (IOException e)
        {
            logger_.error("Unable to read properties file " + event.getFile(), e);
        }
    }

    /**
     * @param event
     * @see com.lynxit.utils.fileobserver.FileChangesListener#fileChanged(com.lynxit.utils.fileobserver.FileChangedEvent)
     */
    public void fileChanged(FileChangedEvent event)
    {
        // if it wasn't changed by itself
        if(event.getFile().lastModified() == selfChangedTimestamp_)
            return;
        
        try
        {
            loadFromFile(event.getFile());
        }
        catch (IOException e)
        {
            logger_.error("Unable to read properties file " + event.getFile(), e);
        }
    }

    /**
     * @param event
     * @see com.lynxit.utils.fileobserver.FileChangesListener#fileDeleted(com.lynxit.utils.fileobserver.FileChangedEvent)
     */
    public void fileDeleted(FileChangedEvent event)
    {
        // clear the properties object
        properties_.clear();
    }

    public boolean isFallback()
    {
        return isFallback_;
    }
    
    @SuppressWarnings("unchecked")
	public Collection getEntries()
    {
        return properties_.entrySet();
    }
    
    public Properties getProperties()
    {
        return properties_;
    }

    /**
     * @param key
     * @param value
     */
    public void setProperty(String key, String value)
    {
        if(StringUtils.isEmpty(value))
        {
            // remove the property
            properties_.remove(key);
            isDirty_ = true;
        }
        else if(!value.equals(properties_.get(key)))
        {
            properties_.setProperty(key, value);
            isDirty_ = true;
        }        
    }
    
    void save()
    {
        if(!isDirty_)
            return;
        
        File bundleFile = I18nSystem.getInstance().getBundleFile(baseName_, locale_);
        try
        {
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(bundleFile));
            try
            {
                properties_.store(out, "Saved automatically");
            }
            finally
            {
                out.close();
            }
        }
        catch (IOException e)
        {
            logger_.error("Unable to save properties file '" + bundleFile + "'" , e);
        }
        
        selfChangedTimestamp_ = bundleFile.lastModified();
        isDirty_ = false;
    }

    public String getBaseName()
    {
        return baseName_;
    }
}
