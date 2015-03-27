package com.lynxspa.sdm.core.utils;

import java.util.Collection;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.core.GenericTypeResolver;

import com.lynxspa.sdm.core.utils.collections.SafeAutoPopulatingList;

public final class CollectionUtils {
	private CollectionUtils() {
	}

	@SuppressWarnings("unchecked")
	public static <T> List<T> safeList(List<T> list) {
		Class<T> classT = (Class<T>) GenericTypeResolver.resolveTypeArgument(list.getClass(), List.class);
		return new SafeAutoPopulatingList<T>(list, classT);
	}

	@SuppressWarnings("unchecked")
	public static <T> Collection<T> filter(Session session, Collection<T> collection, String query, Object ...params) {
		Query filterQuery = session.createFilter(collection, String.format(query, params));
		return filterQuery.list();
	}
	
	public static Query filterQuery(Session session, Collection<?> collection, String query) {
		Query filterQuery = session.createFilter(collection, query);
		return filterQuery;
	}
}
 