package com.yonyou.common.repository;

import static org.springframework.data.jpa.repository.query.QueryUtils.DELETE_ALL_QUERY_STRING;
import static org.springframework.data.jpa.repository.query.QueryUtils.applyAndBind;
import static org.springframework.data.jpa.repository.query.QueryUtils.getQueryString;
import static org.springframework.data.jpa.repository.query.QueryUtils.toOrders;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Session;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.convert.QueryByExamplePredicateBuilder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.query.Jpa21Utils;
import org.springframework.data.jpa.repository.query.JpaEntityGraph;
import org.springframework.data.jpa.repository.support.CrudMethodMetadata;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.data.repository.support.PageableExecutionUtils.TotalSupplier;
import org.springframework.util.Assert;

import com.yonyou.common.repository.err.PersistException;

/**
* @author jensen.chen
* @version 2017年8月9日 下午9:39:08
*/
public class BaseRepositoryImpl<T,K extends Serializable> implements BaseRepository<T,K> {
	
	@PersistenceContext protected EntityManager em ;
	
	private Class<T> entityClass;
	
	private CrudMethodMetadata metadata;

	@SuppressWarnings("unchecked")
	public BaseRepositoryImpl()
	{
	    entityClass =(Class<T>) ((ParameterizedType) getClass()
	                                .getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	protected Session getSession()
	{
		return  (Session) em.getDelegate();
	}
	
	@Override
	public List<T> findAll() {
		return getQuery(null, (Sort) null).getResultList();
	}

	@Override
	public List<T> findAll(Sort sort) {
		return getQuery(null, sort).getResultList();
	}

	@Override
	public List<T> findAll(Iterable<K> ids) {
	
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<T> query = builder.createQuery(entityClass);
		Root<T> root = query.from(entityClass);
		return em.createQuery(query.where(root.get("id").in(ids))).getResultList();
	}

	@Override
	public <S extends T> List<S> save(Iterable<S> entities) {
		List<S> result = new ArrayList<S>();

		if (entities == null) {
			return result;
		}

		for (S entity : entities) {
			result.add(save(entity));
		}

		return result;
	}

	@Override
	public void flush() {
		em.flush();
	}

	@Override
	public <S extends T> S saveAndFlush(S entity) {
		S result = save(entity);
		flush();

		return result;
	}

	@Override
	public void deleteInBatch(Iterable<T> entities) {
		if (!entities.iterator().hasNext()) {
			return;
		}

		applyAndBind(getQueryString(DELETE_ALL_QUERY_STRING, entityClass.getSimpleName()), entities, em)
				.executeUpdate();
		
	}

	@Override
	public void deleteAllInBatch() {
		em.createQuery(getDeleteAllQueryString()).executeUpdate();
	}
	
	

	@Override
	public T getOne(K id) {
		return em.getReference(getDomainClass(), id);
	}

	@Override
	public <S extends T> List<S> findAll(Example<S> example) {
		return getQuery(new ExampleSpecification<S>(example), example.getProbeType(), (Sort) null).getResultList();
	}

	@Override
	public <S extends T> List<S> findAll(Example<S> example, Sort sort) {
		return getQuery(new ExampleSpecification<S>(example), example.getProbeType(), sort).getResultList();
	}

	@Override
	public Page<T> findAll(Pageable pageable) {
		if (null == pageable) {
			return new PageImpl<T>(findAll());
		}

		return findAll((Specification<T>) null, pageable);
	}
	
	public Page<T> findAll(Specification<T> spec, Pageable pageable) {

		TypedQuery<T> query = getQuery(spec, pageable);
		return pageable == null ? new PageImpl<T>(query.getResultList())
				: readPage(query, getDomainClass(), pageable, spec);
	}

	@Override
	public <S extends T> S save(S entity) {
		try {
			if (PropertyUtils.getProperty(entity, "id")==null) {
				em.persist(entity);
				return entity;
			} else {
				return em.merge(entity);
			}
		} catch (Exception e) {
			throw new PersistException(e);
		}
	}

	@Override
	public T findOne(K id) {

		Class<T> domainType = getDomainClass();

		if (metadata == null) {
			return em.find(domainType, id);
		}

		LockModeType type = metadata.getLockModeType();

		Map<String, Object> hints = getQueryHints();

		return type == null ? em.find(domainType, id, hints) : em.find(domainType, id, type, hints);
	}

	@Override
	public boolean exists(K id) {
		return findOne(id) != null;
	}

	@Override
	public long count() {
		return em.createQuery(getCountQueryString(), Long.class).getSingleResult();
	}

	@Override
	public void delete(K id) {
		T entity = findOne(id);

		if (entity == null) {
			throw new EmptyResultDataAccessException(
					String.format("No %s entity with id %s exists!", entityClass.getSimpleName(), id), 1);
		}

		delete(entity);
	}

	@Override
	public void delete(T entity) {
		em.remove(em.contains(entity) ? entity : em.merge(entity));
	}

	@Override
	public void delete(Iterable<? extends T> entities) {
		for (T entity : entities) {
			delete(entity);
		}
	}

	@Override
	public void deleteAll() {
		for (T element : findAll()) {
			delete(element);
		}
	}

	@Override
	public <S extends T> S findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends T> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends T> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <S extends T> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}
	
	protected TypedQuery<T> getQuery(Specification<T> spec, Sort sort) {
		return getQuery(spec, getDomainClass(), sort);
	}
	
	protected Class<T> getDomainClass()
	{
		return this.entityClass;
	}
	
	protected <S extends T> TypedQuery<S> getQuery(Specification<S> spec, Class<S> domainClass, Sort sort) {

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<S> query = builder.createQuery(domainClass);

		Root<S> root = applySpecificationToCriteria(spec, domainClass, query);
		query.select(root);

		if (sort != null) {
			query.orderBy(toOrders(sort, root, builder));
		}

		return applyRepositoryMethodMetadata(em.createQuery(query));
	}
	
	private <S, U extends T> Root<U> applySpecificationToCriteria(Specification<U> spec, Class<U> domainClass,
			CriteriaQuery<S> query) {

		Root<U> root = query.from(domainClass);

		if (spec == null) {
			return root;
		}

		CriteriaBuilder builder = em.getCriteriaBuilder();
		Predicate predicate = spec.toPredicate(root, query, builder);

		if (predicate != null) {
			query.where(predicate);
		}

		return root;
	}

	private <S> TypedQuery<S> applyRepositoryMethodMetadata(TypedQuery<S> query) {

		if (metadata == null) {
			return query;
		}

		LockModeType type = metadata.getLockModeType();
		TypedQuery<S> toReturn = type == null ? query : query.setLockMode(type);

		applyQueryHints(toReturn);

		return toReturn;
	}

	private void applyQueryHints(Query query) {

		for (Entry<String, Object> hint : getQueryHints().entrySet()) {
			query.setHint(hint.getKey(), hint.getValue());
		}
	}
	
	protected Map<String, Object> getQueryHints() {

		if (metadata.getEntityGraph() == null) {
			return metadata.getQueryHints();
		}

		Map<String, Object> hints = new HashMap<String, Object>();
		hints.putAll(metadata.getQueryHints());

		hints.putAll(Jpa21Utils.tryGetFetchGraphHints(em, getEntityGraph(), getDomainClass()));

		return hints;
	}

	private JpaEntityGraph getEntityGraph() {
		String fallbackName = this.entityClass.getSimpleName() + "." + metadata.getMethod().getName();
		return new JpaEntityGraph(metadata.getEntityGraph(), fallbackName);
	}
	
	private String getDeleteAllQueryString() {
		return getQueryString(DELETE_ALL_QUERY_STRING, entityClass.getSimpleName());
	}
	
	private static class ExampleSpecification<T> implements Specification<T> {

		private final Example<T> example;

		/**
		 * Creates new {@link ExampleSpecification}.
		 *
		 * @param example
		 */
		public ExampleSpecification(Example<T> example) {

			Assert.notNull(example, "Example must not be null!");
			this.example = example;
		}

		/*
		 * (non-Javadoc)
		 * @see org.springframework.data.jpa.domain.Specification#toPredicate(javax.persistence.criteria.Root, javax.persistence.criteria.CriteriaQuery, javax.persistence.criteria.CriteriaBuilder)
		 */
		@Override
		public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
			return QueryByExamplePredicateBuilder.getPredicate(root, cb, example);
		}
	}
	
	protected TypedQuery<T> getQuery(Specification<T> spec, Pageable pageable) {

		Sort sort = pageable == null ? null : pageable.getSort();
		return getQuery(spec, getDomainClass(), sort);
	}
	
	
	protected <S extends T> Page<S> readPage(TypedQuery<S> query, final Class<S> domainClass, Pageable pageable,
			final Specification<S> spec) {

		query.setFirstResult(pageable.getOffset());
		query.setMaxResults(pageable.getPageSize());

		return PageableExecutionUtils.getPage(query.getResultList(), pageable, new TotalSupplier() {

			@Override
			public long get() {
				return executeCountQuery(getCountQuery(spec, domainClass));
			}
		});
	}

	protected <S extends T> TypedQuery<Long> getCountQuery(Specification<S> spec, Class<S> domainClass) {

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);

		Root<S> root = applySpecificationToCriteria(spec, domainClass, query);

		if (query.isDistinct()) {
			query.select(builder.countDistinct(root));
		} else {
			query.select(builder.count(root));
		}

		// Remove all Orders the Specifications might have applied
		query.orderBy(Collections.<Order> emptyList());

		return em.createQuery(query);
	}
	

	private String getCountQueryString() {

		return getQueryString("select count(id) from %s x", entityClass.getSimpleName());
	}
	
	private static Long executeCountQuery(TypedQuery<Long> query) {

		Assert.notNull(query, "TypedQuery must not be null!");

		List<Long> totals = query.getResultList();
		Long total = 0L;

		for (Long element : totals) {
			total += element == null ? 0 : element;
		}

		return total;
	}

}
