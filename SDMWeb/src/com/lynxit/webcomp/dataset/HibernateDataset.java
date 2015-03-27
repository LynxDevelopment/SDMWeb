package com.lynxit.webcomp.dataset;

import java.util.Iterator;

import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.type.TypeFactory;

import com.lynxit.webcomp.dataset.filter.DatasetFilter;

public class HibernateDataset extends DataSetStateBean
{
    private static final Logger logger_ = Logger.getLogger(HibernateDataset.class);
    
    /**
     *
     */
    private static final long serialVersionUID = 987465418354856L;
    
    public HibernateDataset(String datasource)
    {
        super(datasource);
    }

    protected QueryExecutionResult executeQuery(int startRow, int results, Object connection) throws NamingException
    {
        if (!(connection instanceof Session))
        {
            throw new NamingException("Connection is not a hibernate Session, " + connection);
        }
        Session session = (Session) connection;

        // create the Query
        String completeQuery = getCompleteQuery();
        Query query = session.createQuery(completeQuery);

        if (logger_.isDebugEnabled())
            logger_.debug("Complete query: " + query.getQueryString());

        // set parameters
        setParameters(query);


        int availableRows;
        Object[] result;

        // page the result
        if (results > 0)
        {
            /*
             * retrieves the total rows number To avoid loading large datasets completely when not necessary
             * (because of paging) it tests whether the original query does not contain the group by clause and in this
             * case composes a "select count(*)" query. This method cannot be applyed to complex queries since hql
             * does not allow the syntax "select ... from (nestedQuery)"
             */
        	//TEMPORAL FIX: Add startWith "select distinct" condition to resolve distinct count querys.
            if((getQuery().indexOf(" group by ") > 0) || (getQuery().startsWith("select distinct")))
            {
                availableRows = query.list().size();
            }
            else
            {
                String simpleQuery = getFilteredQuery();
                if(!completeQuery.startsWith("from "))
                {
                    simpleQuery = simpleQuery.substring(simpleQuery.indexOf(" from "));
                }
                Query countQuery = session.createQuery("select count(*) " + simpleQuery);
                setParameters(countQuery);
                availableRows = ((Number)countQuery.uniqueResult()).intValue();
            }
            
            query.setFirstResult(startRow);
            query.setMaxResults(results);

            result = query.list().toArray();
            
        }
        else
        {
            result = query.list().toArray();
            availableRows = result.length;
        }
        
        return new QueryExecutionResult(new String[] { "" }, result, availableRows);
    }

    private void setParameters(Query query)
    {
        Iterator params = getParams().iterator();
        int i = 0;
        while (params.hasNext())
        {
            Object value = params.next();
            logger_.debug("setting param " + i + ": '" + value + "'");
            query.setParameter(i, value, TypeFactory.heuristicType(value.getClass().getName()));
            i++;
        }

        // set filter parameters
        DatasetFilter filter = getDatasetFilter();
        if (filter != null && filter.getFilterString().length() > 0)
        {
            Iterator iter = filter.getFilterParams().iterator();
            while (iter.hasNext())
            {
                Object param = iter.next();
                logger_.debug("setting filter param " + i + ": '" + param + "'");
                query.setParameter(i, param, TypeFactory.heuristicType(param.getClass().getName()));
                i++;
            }
        }
    }

    /**
     * @return The sql query including generated filtering and ordering clauses.
     */
    public String getCompleteQuery()
    {
        String completeQuery = getFilteredQuery();
        OrderByClause orderBy = getOrderByClause();
        if (orderBy != null)
            completeQuery = completeQuery + " order by " + orderBy;
        return completeQuery;
    }

    public String getFilteredQuery()
    {
        String filteredQuery = getQuery();
        DatasetFilter filter = getDatasetFilter();
        // completeQuery = "from (" + completeQuery + ") obj ";
        if (filter != null && filter.getFilterString().length() > 0)
            filteredQuery = filteredQuery + " where " + filter.getFilterString();
        return filteredQuery;
    }

	public Iterator getIterator(Object connection) throws NamingException
	{
		if (!(connection instanceof Session))
        {
            throw new NamingException("Connection is not a hibernate Session, " + connection);
        }
        Session session = (Session) connection;

        // create the Query
        String completeQuery = getCompleteQuery();
        Query query = session.createQuery(completeQuery);
        
//      set parameters
        setParameters(query);
        
		return query.iterate();
	}
	 
}
