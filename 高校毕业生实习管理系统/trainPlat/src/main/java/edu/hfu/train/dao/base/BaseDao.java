package edu.hfu.train.dao.base;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Filter;
import org.hibernate.LockOptions;
import org.hibernate.ReplicationMode;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.type.Type;

public interface BaseDao {
	public Session getSession();

	/**
	 * Return the persistent instance of the given entity class with the given
	 * identifier, or {@code null} if not found.
	 * <p>
	 * This method is a thin wrapper around
	 * {@link Session#get(Class, Serializable)} for
	 * convenience. For an explanation of the exact semantics of this method, please
	 * do refer to the Hibernate API documentation in the first instance.
	 * 
	 * @param entityClass a persistent class
	 * @param id          the identifier of the persistent instance
	 * @return the persistent instance, or {@code null} if not found
	 * @throws org.springframework.dao.Exception in case of Hibernate errors
	 * @see Session#get(Class, Serializable)
	 */
	public <T> T get(Class<T> entityClass, Serializable id) throws Exception;

	/**
	 * Return the persistent instance of the given entity class with the given
	 * identifier, or {@code null} if not found.
	 * <p>
	 * Obtains the specified lock mode if the instance exists.
	 * <p>
	 * This method is a thin wrapper around
	 * {@link Session#get(Class, Serializable, LockOptions)}
	 * for convenience. For an explanation of the exact semantics of this method,
	 * please do refer to the Hibernate API documentation in the first instance.
	 * 
	 * @param entityClass a persistent class
	 * @param id          the identifier of the persistent instance
	 * @param lockMode    the lock mode to obtain
	 * @return the persistent instance, or {@code null} if not found
	 * @throws org.springframework.dao.Exception in case of Hibernate errors
	 * @see Session#get(Class, Serializable,
	 *      LockOptions)
	 */
	public <T> T get(Class<T> entityClass, Serializable id, LockOptions lockMode) throws Exception;

	/**
	 * Return the persistent instance of the given entity class with the given
	 * identifier, or {@code null} if not found.
	 * <p>
	 * This method is a thin wrapper around
	 * {@link Session#get(String, Serializable)} for
	 * convenience. For an explanation of the exact semantics of this method, please
	 * do refer to the Hibernate API documentation in the first instance.
	 * 
	 * @param entityName the name of the persistent entity
	 * @param id         the identifier of the persistent instance
	 * @return the persistent instance, or {@code null} if not found
	 * @throws org.springframework.dao.Exception in case of Hibernate errors
	 * @see Session#get(Class, Serializable)
	 */
	public Object get(String entityName, Serializable id) throws Exception;

	/**
	 * Return the persistent instance of the given entity class with the given
	 * identifier, or {@code null} if not found. Obtains the specified lock mode if
	 * the instance exists.
	 * <p>
	 * This method is a thin wrapper around
	 * {@link Session#get(String, Serializable, LockOptions)}
	 * for convenience. For an explanation of the exact semantics of this method,
	 * please do refer to the Hibernate API documentation in the first instance.
	 * 
	 * @param entityName the name of the persistent entity
	 * @param id         the identifier of the persistent instance
	 * @param lockMode   the lock mode to obtain
	 * @return the persistent instance, or {@code null} if not found
	 * @throws org.springframework.dao.Exception in case of Hibernate errors
	 * @see Session#get(Class, Serializable,
	 *      LockOptions)
	 */
	public Object get(String entityName, Serializable id, LockOptions lockOptions) throws Exception;

	/**
	 * Return the persistent instance of the given entity class with the given
	 * identifier, throwing an exception if not found.
	 * <p>
	 * This method is a thin wrapper around
	 * {@link Session#load(Class, Serializable)} for
	 * convenience. For an explanation of the exact semantics of this method, please
	 * do refer to the Hibernate API documentation in the first instance.
	 * 
	 * @param entityClass a persistent class
	 * @param id          the identifier of the persistent instance
	 * @return the persistent instance
	 * @throws org.springframework.orm.ObjectRetrievalFailureException if not found
	 * @throws org.springframework.dao.Exception                       in case of
	 *                                                                 Hibernate
	 *                                                                 errors
	 * @see Session#load(Class, Serializable)
	 */
	public <T> T load(Class<T> entityClass, Serializable id) throws Exception;

	/**
	 * Return the persistent instance of the given entity class with the given
	 * identifier, throwing an exception if not found. Obtains the specified lock
	 * mode if the instance exists.
	 * <p>
	 * This method is a thin wrapper around
	 * {@link Session#load(Class, Serializable, LockOptions)}
	 * for convenience. For an explanation of the exact semantics of this method,
	 * please do refer to the Hibernate API documentation in the first instance.
	 * 
	 * @param entityClass a persistent class
	 * @param id          the identifier of the persistent instance
	 * @param lockMode    the lock mode to obtain
	 * @return the persistent instance
	 * @throws org.springframework.orm.ObjectRetrievalFailureException if not found
	 * @throws org.springframework.dao.Exception                       in case of
	 *                                                                 Hibernate
	 *                                                                 errors
	 * @see Session#load(Class, Serializable)
	 */
	public <T> T load(Class<T> entityClass, Serializable id, LockOptions lockOptions) throws Exception;

	/**
	 * Return the persistent instance of the given entity class with the given
	 * identifier, throwing an exception if not found.
	 * <p>
	 * This method is a thin wrapper around
	 * {@link Session#load(String, Serializable)} for
	 * convenience. For an explanation of the exact semantics of this method, please
	 * do refer to the Hibernate API documentation in the first instance.
	 * 
	 * @param entityName the name of the persistent entity
	 * @param id         the identifier of the persistent instance
	 * @return the persistent instance
	 * @throws org.springframework.orm.ObjectRetrievalFailureException if not found
	 * @throws org.springframework.dao.Exception                       in case of
	 *                                                                 Hibernate
	 *                                                                 errors
	 * @see Session#load(Class, Serializable)
	 */
	public Object load(String entityName, Serializable id) throws Exception;

	/**
	 * Return the persistent instance of the given entity class with the given
	 * identifier, throwing an exception if not found.
	 * <p>
	 * Obtains the specified lock mode if the instance exists.
	 * <p>
	 * This method is a thin wrapper around
	 * {@link Session#load(String, Serializable, LockOptions)}
	 * for convenience. For an explanation of the exact semantics of this method,
	 * please do refer to the Hibernate API documentation in the first instance.
	 * 
	 * @param entityName the name of the persistent entity
	 * @param id         the identifier of the persistent instance
	 * @param lockMode   the lock mode to obtain
	 * @return the persistent instance
	 * @throws org.springframework.orm.ObjectRetrievalFailureException if not found
	 * @throws org.springframework.dao.Exception                       in case of
	 *                                                                 Hibernate
	 *                                                                 errors
	 * @see Session#load(Class, Serializable)
	 */
	public Object load(String entityName, Serializable id, LockOptions lockOptions) throws Exception;

	/**
	 * Return all persistent instances of the given entity class. Note: Use queries
	 * or criteria for retrieving a specific subset.
	 * 
	 * @param entityClass a persistent class
	 * @return a {@link List} containing 0 or more persistent instances
	 * @throws org.springframework.dao.Exception if there is a Hibernate error
	 * @see Session#createCriteria
	 */
	public <T> List<T> loadAll(Class<T> entityClass) throws Exception;

	/**
	 * Load the persistent instance with the given identifier into the given object,
	 * throwing an exception if not found.
	 * <p>
	 * This method is a thin wrapper around
	 * {@link Session#load(Object, Serializable)} for
	 * convenience. For an explanation of the exact semantics of this method, please
	 * do refer to the Hibernate API documentation in the first instance.
	 * 
	 * @param entity the object (of the target class) to load into
	 * @param id     the identifier of the persistent instance
	 * @throws org.springframework.orm.ObjectRetrievalFailureException if not found
	 * @throws org.springframework.dao.Exception                       in case of
	 *                                                                 Hibernate
	 *                                                                 errors
	 * @see Session#load(Object, Serializable)
	 */
	public void load(Object entity, Serializable id) throws Exception;

	/**
	 * Re-read the state of the given persistent instance.
	 * 
	 * @param entity the persistent instance to re-read
	 * @throws org.springframework.dao.Exception in case of Hibernate errors
	 * @see Session#refresh(Object)
	 */
	public void refresh(Object entity) throws Exception;

	/**
	 * Re-read the state of the given persistent instance. Obtains the specified
	 * lock mode for the instance.
	 * 
	 * @param entity   the persistent instance to re-read
	 * @param lockMode the lock mode to obtain
	 * @throws org.springframework.dao.Exception in case of Hibernate errors
	 * @see Session#refresh(Object, LockOptions)
	 */
	public void refresh(Object entity, LockOptions lockOptions) throws Exception;

	/**
	 * Check whether the given object is in the Session cache.
	 * 
	 * @param entity the persistence instance to check
	 * @return whether the given object is in the Session cache
	 * @throws org.springframework.dao.Exception if there is a Hibernate error
	 * @see Session#contains
	 */
	public boolean contains(Object entity) throws Exception;

	/**
	 * Remove the given object from the {@link Session} cache.
	 * 
	 * @param entity the persistent instance to evict
	 * @throws org.springframework.dao.Exception in case of Hibernate errors
	 * @see Session#evict
	 */
	public void evict(Object entity) throws Exception;

	/**
	 * Force initialization of a Hibernate proxy or persistent collection.
	 * 
	 * @param proxy a proxy for a persistent object or a persistent collection
	 * @throws Exception if we can't initialize the proxy, for example because it is
	 *                   not associated with an active Session
	 * @see org.hibernate.Hibernate#initialize
	 */
	public void initialize(Object proxy) throws Exception;

	/**
	 * Return an enabled Hibernate {@link Filter} for the given filter name. The
	 * returned {@code Filter} instance can be used to set filter parameters.
	 * 
	 * @param filterName the name of the filter
	 * @return the enabled Hibernate {@code Filter} (either already enabled or
	 *         enabled on the fly by this operation)
	 * @throws IllegalStateException if we are not running within a transactional
	 *                               Session (in which case this operation does not
	 *                               make sense)
	 */
	public Filter enableFilter(String filterName) throws IllegalStateException;

	// -------------------------------------------------------------------------
	// Convenience methods for storing individual objects
	// -------------------------------------------------------------------------
	/**
	 * Persist the given transient instance.
	 * 
	 * @param entity the transient instance to persist
	 * @return the generated identifier
	 * @throws org.springframework.dao.Exception in case of Hibernate errors
	 * @see Session#save(Object) merge（） �?
	 * 
	 *      １．如果对象的idertifier(以下�?称为id)为空或在数据库不存在，则进行inert动作（此时如果对象的id有�?�也
	 *      将被hibernate自动生成的ID覆盖�? ２．如果id存在，则进行update动作
	 * 
	 *      replicate（） �?
	 * 
	 *      Persist the state of the given detached instance, reusing the current
	 *      identifier value
	 * 
	 *      使用背景�?
	 * 
	 *      假设你的对象的ID是用hibernate 负责生成的，但现在你想在数据库中插入�?条已经指定ID的记录，
	 * 
	 *      如果你用save（） 不会报异常，但它会生成一条由hibernate生成的ID 的记�?
	 * 
	 *      如果你用merge（） 跟用save �?�?
	 * 
	 *      如果用saveOrUpdate 如果数据库中不存在你指定的ID 则不会发生操作，如果存在，则会修改数据库的记录，而不是重新生成一条新记录
	 * 
	 *      如果用persist（） 会报异常�?
	 * 
	 *      哈哈 �?后只剩下 replicate（） 方法�?
	 * 
	 *      rereplicate（）会完成你心愿�?
	 * 
	 *      注意�? 此时 ID 的生成策�? �? uuid.hex �? oracle10g �? sql2000 上都经过测试�?
	 * 
	 *      别的生成策略在不同数据库上可能有问题，尤其像 native �? 由数据库管理生成ID �?
	 * 
	 *      示例代码�?
	 * 
	 *      MyObject myobj = new MyObject(); myobj.setId("encodinglife")
	 *      myobj.setOtherData("foobar"); hsession.replicate(myobj,
	 *      ReplicationMode.EXCEPTION);
	 */
	public <T> Serializable save(T t) throws Exception;

	/**
	 * Persist the given transient instance.
	 * 
	 * @param entityName the name of the persistent entity
	 * @param entity     the transient instance to persist
	 * @return the generated identifier
	 * @throws org.springframework.dao.Exception in case of Hibernate errors
	 * @see Session#save(String, Object)
	 */
	public <T> Serializable save(String entityName, T t) throws Exception;

	/**
	 * Update the given persistent instance, associating it with the current
	 * Hibernate {@link Session}.
	 * 
	 * @param entity the persistent instance to update
	 * @throws org.springframework.dao.Exception in case of Hibernate errors
	 * @see Session#update(Object)
	 */
	public <T> void update(T t) throws Exception;

	/**
	 * Update the given persistent instance, associating it with the current
	 * Hibernate {@link Session}.
	 * 
	 * @param entityName the name of the persistent entity
	 * @param entity     the persistent instance to update
	 * @throws org.springframework.dao.Exception in case of Hibernate errors
	 * @see Session#update(String, Object)
	 */
	public <T> void update(String entityName, T t) throws Exception;

	/**
	 * Save or update the given persistent instance, according to its id (matching
	 * the configured "unsaved-value"?). Associates the instance with the current
	 * Hibernate {@link Session}.
	 * 
	 * @param entity the persistent instance to save or update (to be associated
	 *               with the Hibernate {@code Session})
	 * @throws Exception in case of Hibernate errors
	 * @see Session#saveOrUpdate(Object)
	 */
	public <T> void saveOrUpdate(T t) throws Exception;

	/**
	 * Save or update the given persistent instance, according to its id (matching
	 * the configured "unsaved-value"?). Associates the instance with the current
	 * Hibernate {@code Session}.
	 * 
	 * @param entityName the name of the persistent entity
	 * @param entity     the persistent instance to save or update (to be associated
	 *                   with the Hibernate {@code Session})
	 * @throws Exception in case of Hibernate errors
	 * @see Session#saveOrUpdate(String, Object)
	 */
	public <T> void saveOrUpdate(String entityName, T t) throws Exception;

	/**
	 * Persist the state of the given detached instance according to the given
	 * replication mode, reusing the current identifier value.
	 * 
	 * @param entity          the persistent object to replicate
	 * @param replicationMode the Hibernate ReplicationMode
	 * @throws Exception in case of Hibernate errors
	 * @see Session#replicate(Object, ReplicationMode)
	 */
	public <T> void replicate(T t, ReplicationMode replicationMode) throws Exception;

	/**
	 * Persist the state of the given detached instance according to the given
	 * replication mode, reusing the current identifier value.
	 * 
	 * @param entityName      the name of the persistent entity
	 * @param entity          the persistent object to replicate
	 * @param replicationMode the Hibernate ReplicationMode
	 * @throws Exception in case of Hibernate errors
	 * @see Session#replicate(String, Object,
	 *      ReplicationMode)
	 */
	public <T> void replicate(String entityName, T t, ReplicationMode replicationMode) throws Exception;

	/**
	 * Persist the given transient instance. Follows JSR-220 semantics.
	 * <p>
	 * Similar to {@code save}, associating the given object with the current
	 * Hibernate {@link Session}.
	 * 
	 * @param entity the persistent instance to persist
	 * @throws org.springframework.dao.Exception in case of Hibernate errors
	 * @see Session#persist(Object)
	 * @see #save
	 */
	public <T> void persist(T t) throws Exception;

	/**
	 * Persist the given transient instance. Follows JSR-220 semantics.
	 * <p>
	 * Similar to {@code save}, associating the given object with the current
	 * Hibernate {@link Session}.
	 * 
	 * @param entityName the name of the persistent entity
	 * @param entity     the persistent instance to persist
	 * @throws org.springframework.dao.Exception in case of Hibernate errors
	 * @see Session#persist(String, Object)
	 * @see #save
	 */
	public <T> void persist(String entityName, T t) throws Exception;

	/**
	 * Copy the state of the given object onto the persistent object with the same
	 * identifier. Follows JSR-220 semantics.
	 * <p>
	 * Similar to {@code saveOrUpdate}, but never associates the given object with
	 * the current Hibernate Session. In case of a new entity, the state will be
	 * copied over as well.
	 * <p>
	 * Note that {@code merge} will <i>not</i> update the identifiers in the
	 * passed-in object graph (in contrast to TopLink)! Consider registering
	 * Spring's {@code IdTransferringMergeEventListener} if you would like to have
	 * newly assigned ids transferred to the original object graph too.
	 * 
	 * @param entity the object to merge with the corresponding persistence instance
	 * @return the updated, registered persistent instance
	 * @throws org.springframework.dao.Exception in case of Hibernate errors
	 * @see Session#merge(Object)
	 * @see #saveOrUpdate
	 * @see org.springframework.orm.hibernate3.support.IdTransferringMergeEventListener
	 */
	public <T> T merge(T entity) throws Exception;

	/**
	 * Copy the state of the given object onto the persistent object with the same
	 * identifier. Follows JSR-220 semantics.
	 * <p>
	 * Similar to {@code saveOrUpdate}, but never associates the given object with
	 * the current Hibernate {@link Session}. In the case of a new
	 * entity, the state will be copied over as well.
	 * <p>
	 * Note that {@code merge} will <i>not</i> update the identifiers in the
	 * passed-in object graph (in contrast to TopLink)! Consider registering
	 * Spring's {@code IdTransferringMergeEventListener} if you would like to have
	 * newly assigned ids transferred to the original object graph too.
	 * 
	 * @param entityName the name of the persistent entity
	 * @param entity     the object to merge with the corresponding persistence
	 *                   instance
	 * @return the updated, registered persistent instance
	 * @throws org.springframework.dao.Exception in case of Hibernate errors
	 * @see Session#merge(String, Object)
	 * @see #saveOrUpdate
	 */
	public <T> T merge(String entityName, T entity) throws Exception;

	/**
	 * Delete the given persistent instance.
	 * 
	 * @param entity the persistent instance to delete
	 * @throws org.springframework.dao.Exception in case of Hibernate errors
	 * @see Session#delete(Object)
	 */
	public <T> void delete(T entity) throws Exception;

	/**
	 * Delete the given persistent instance.
	 * 
	 * @param entityName the name of the persistent entity
	 * @param entity     the persistent instance to delete
	 * @throws org.springframework.dao.Exception in case of Hibernate errors
	 * @see Session#delete(Object)
	 */
	public <T> void delete(String entityName, T entity) throws Exception;

	/**
	 * Delete all given persistent instances.
	 * <p>
	 * This can be combined with any of the find methods to delete by query in two
	 * lines of code.
	 * 
	 * @param entities the persistent instances to delete
	 * @throws org.springframework.dao.Exception in case of Hibernate errors
	 * @see Session#delete(Object)
	 */
	public <T> void deleteAll(Collection<T> entities) throws Exception;

	/**
	 * Flush all pending saves, updates and deletes to the database.
	 * <p>
	 * Only invoke this for selective eager flushing, for example when JDBC code
	 * needs to see certain changes within the same transaction. Else, it is
	 * preferable to rely on auto-flushing at transaction completion.
	 * 
	 * @throws org.springframework.dao.Exception in case of Hibernate errors
	 * @see Session#flush
	 */
	public void flush() throws Exception;

	/**
	 * Remove all objects from the {@link Session} cache, and cancel
	 * all pending saves, updates and deletes.
	 * 
	 * @throws org.springframework.dao.Exception in case of Hibernate errors
	 * @see Session#clear
	 */
	public void clear() throws Exception;

	// -------------------------------------------------------------------------
	// Convenience finder methods for HQL strings
	// -------------------------------------------------------------------------

	/**
	 * Execute an HQL query.
	 * 
	 * @param queryString a query expressed in Hibernate's query language
	 * @return a {@link List} containing the results of the query execution
	 * @throws org.springframework.dao.Exception in case of Hibernate errors
	 * @see Session#createQuery
	 */
	public <T> List<T> find(String hql) throws Exception;

	public List<Map<String, Object>> findMap(String hql) throws Exception;

	/**
	 * Execute an HQL query, binding one value to a "?" parameter in the query
	 * string.
	 * 
	 * @param queryString a query expressed in Hibernate's query language
	 * @param value       the value of the parameter
	 * @return a {@link List} containing the results of the query execution
	 * @throws org.springframework.dao.Exception in case of Hibernate errors
	 * @see Session#createQuery
	 */

	public <T> List<T> find(String hql, Object value) throws Exception;

	public List<Map<String, Object>> findMap(String hql, Object value) throws Exception;

	/**
	 * Execute an HQL query, binding a number of values to "?" parameters in the
	 * query string.
	 * 
	 * @param queryString a query expressed in Hibernate's query language
	 * @param values      the values of the parameters
	 * @return a {@link List} containing the results of the query execution
	 * @throws org.springframework.dao.Exception in case of Hibernate errors
	 * @see Session#createQuery
	 */
	public <T> List<T> find(String hql, Object... values) throws Exception;

	/**
	 * Execute an HQL query, binding a number of values to ":param" parameters in
	 * the query string.
	 * 
	 * @param hql
	 * @param params map<bindparam,bindvalue>
	 * @return
	 * @throws Exception
	 */
	public <T> List<T> find(String hql, Map<String, Object> params) throws Exception;

	/**
	 * Execute an HQL query, binding a number of values to "?" parameters in the
	 * query string.
	 * 
	 * @param hql
	 * @param values
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> findMap(String hql, Object... values) throws Exception;

	/**
	 * 
	 * @param hql
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> findMap(String hql, Map<String, Object> params) throws Exception;

	/**
	 * Execute an HQL query, binding one value to a ":" named parameter in the query
	 * string.
	 * 
	 * @param queryString a query expressed in Hibernate's query language
	 * @param paramName   the name of the parameter
	 * @param value       the value of the parameter
	 * @return a {@link List} containing the results of the query execution
	 * @throws org.springframework.dao.Exception in case of Hibernate errors
	 * @see Session#getNamedQuery(String)
	 */

	public List<Object> findByNamedParam(String hql, String paramName, Object value) throws Exception;

	/**
	 * Execute an HQL query, binding a number of values to ":" named parameters in
	 * the query string.
	 * 
	 * @param queryString a query expressed in Hibernate's query language
	 * @param paramNames  the names of the parameters
	 * @param values      the values of the parameters
	 * @return a {@link List} containing the results of the query execution
	 * @throws org.springframework.dao.Exception in case of Hibernate errors
	 * @see Session#getNamedQuery(String)
	 */

	public List<Object> findByNamedParam(String hql, String[] paramNames, Object[] values) throws Exception;

	/**
	 * Execute an HQL query, binding the properties of the given bean to
	 * <i>named</i> parameters in the query string.
	 * 
	 * @param queryString a query expressed in Hibernate's query language
	 * @param valueBean   the values of the parameters
	 * @return a {@link List} containing the results of the query execution
	 * @throws org.springframework.dao.Exception in case of Hibernate errors
	 * @see org.hibernate.Query#setProperties
	 * @see Session#createQuery
	 */

	public List<Object> findByValueBean(String hql, Object valueBean) throws Exception;

	// -------------------------------------------------------------------------
	// Convenience finder methods for named queries
	// -------------------------------------------------------------------------

	/**
	 * Execute a named query.
	 * <p>
	 * A named query is defined in a Hibernate mapping file.
	 * 
	 * @param queryName the name of a Hibernate query in a mapping file
	 * @return a {@link List} containing the results of the query execution
	 * @throws org.springframework.dao.Exception in case of Hibernate errors
	 * @see Session#getNamedQuery(String)
	 */

	public List<Object> findByNamedQuery(String queryName) throws Exception;

	/**
	 * Execute a named query, binding one value to a "?" parameter in the query
	 * string.
	 * <p>
	 * A named query is defined in a Hibernate mapping file.
	 * 
	 * @param queryName the name of a Hibernate query in a mapping file
	 * @param value     the value of the parameter
	 * @return a {@link List} containing the results of the query execution
	 * @throws org.springframework.dao.Exception in case of Hibernate errors
	 * @see Session#getNamedQuery(String)
	 */

	public List<Object> findByNamedQuery(String queryName, Object value) throws Exception;

	/**
	 * Execute a named query binding a number of values to "?" parameters in the
	 * query string.
	 * <p>
	 * A named query is defined in a Hibernate mapping file.
	 * 
	 * @param queryName the name of a Hibernate query in a mapping file
	 * @param values    the values of the parameters
	 * @return a {@link List} containing the results of the query execution
	 * @throws org.springframework.dao.Exception in case of Hibernate errors
	 * @see Session#getNamedQuery(String)
	 */

	public List<Object> findByNamedQuery(String queryName, Object... values) throws Exception;

	/**
	 * Execute a named query, binding one value to a ":" named parameter in the
	 * query string.
	 * <p>
	 * A named query is defined in a Hibernate mapping file.
	 * 
	 * @param queryName the name of a Hibernate query in a mapping file
	 * @param paramName the name of parameter
	 * @param value     the value of the parameter
	 * @return a {@link List} containing the results of the query execution
	 * @throws org.springframework.dao.Exception in case of Hibernate errors
	 * @see Session#getNamedQuery(String)
	 */

	public List<Object> findByNamedQueryAndNamedParam(String queryName, String paramName, Object value)
			throws Exception;

	/**
	 * Execute a named query, binding a number of values to ":" named parameters in
	 * the query string.
	 * <p>
	 * A named query is defined in a Hibernate mapping file.
	 * 
	 * @param queryName  the name of a Hibernate query in a mapping file
	 * @param paramNames the names of the parameters
	 * @param values     the values of the parameters
	 * @return a {@link List} containing the results of the query execution
	 * @throws org.springframework.dao.Exception in case of Hibernate errors
	 * @see Session#getNamedQuery(String)
	 */

	public List<Object> findByNamedQueryAndNamedParam(String queryName, String[] paramNames, Object[] values)
			throws Exception;

	/**
	 * Execute a named query, binding the properties of the given bean to ":" named
	 * parameters in the query string.
	 * <p>
	 * A named query is defined in a Hibernate mapping file.
	 * 
	 * @param queryName the name of a Hibernate query in a mapping file
	 * @param valueBean the values of the parameters
	 * @return a {@link List} containing the results of the query execution
	 * @throws org.springframework.dao.Exception in case of Hibernate errors
	 * @see org.hibernate.Query#setProperties
	 * @see Session#getNamedQuery(String)
	 */

	public List<Object> findByNamedQueryAndValueBean(String queryName, Object valueBean) throws Exception;

	// -------------------------------------------------------------------------
	// Convenience finder methods for detached criteria
	// -------------------------------------------------------------------------

	/**
	 * Execute a query based on a given Hibernate criteria object.
	 * 
	 * @param criteria the detached Hibernate criteria object. <b>Note: Do not reuse
	 *                 criteria objects! They need to recreated per execution, due
	 *                 to the suboptimal design of Hibernate's criteria
	 *                 facility.</b>
	 * @return a {@link List} containing 0 or more persistent instances
	 * @throws org.springframework.dao.Exception in case of Hibernate errors
	 * @see DetachedCriteria#getExecutableCriteria(Session)
	 */

	public List<Object> findByCriteria(DetachedCriteria criteria) throws Exception;

	/**
	 * Execute a query based on the given Hibernate criteria object.
	 * 
	 * @param criteria   the detached Hibernate criteria object. <b>Note: Do not
	 *                   reuse criteria objects! They need to recreated per
	 *                   execution, due to the suboptimal design of Hibernate's
	 *                   criteria facility.</b>
	 * @param pageNo     the index of the first result object to be retrieved
	 *                   (numbered from 0)
	 * @param maxResults the maximum number of result objects to retrieve (or <=0
	 *                   for no limit)
	 * @return a {@link List} containing 0 or more persistent instances
	 * @throws org.springframework.dao.Exception in case of Hibernate errors
	 * @see DetachedCriteria#getExecutableCriteria(Session)
	 * @see org.hibernate.Criteria#setpageNo(int)
	 * @see org.hibernate.Criteria#setMaxResults(int)
	 */

	public List<Object> findByCriteria(DetachedCriteria criteria, int pageNo, int maxResults) throws Exception;

	// -------------------------------------------------------------------------
	// Convenience query methods for iteration and bulk updates/deletes
	// -------------------------------------------------------------------------

	/**
	 * Execute a query for persistent instances.
	 * <p>
	 * Returns the results as an {@link Iterator}. Entities returned are initialized
	 * on demand. See the Hibernate API documentation for details.
	 * 
	 * @param queryString a query expressed in Hibernate's query language
	 * @return an {@link Iterator} containing 0 or more persistent instances
	 * @throws org.springframework.dao.Exception in case of Hibernate errors
	 * @see Session#createQuery
	 * @see org.hibernate.Query#iterate
	 */
	public Iterator<?> iterate(String hql) throws Exception;

	/**
	 * Execute a query for persistent instances, binding one value to a "?"
	 * parameter in the query string.
	 * <p>
	 * Returns the results as an {@link Iterator}. Entities returned are initialized
	 * on demand. See the Hibernate API documentation for details.
	 * 
	 * @param queryString a query expressed in Hibernate's query language
	 * @param value       the value of the parameter
	 * @return an {@link Iterator} containing 0 or more persistent instances
	 * @throws org.springframework.dao.Exception in case of Hibernate errors
	 * @see Session#createQuery
	 * @see org.hibernate.Query#iterate
	 */

	public Iterator<?> iterate(String hql, Object value) throws Exception;

	/**
	 * Execute a query for persistent instances, binding a number of values to "?"
	 * parameters in the query string.
	 * <p>
	 * Returns the results as an {@link Iterator}. Entities returned are initialized
	 * on demand. See the Hibernate API documentation for details.
	 * 
	 * @param queryString a query expressed in Hibernate's query language
	 * @param values      the values of the parameters
	 * @return an {@link Iterator} containing 0 or more persistent instances
	 * @throws org.springframework.dao.Exception in case of Hibernate errors
	 * @see Session#createQuery
	 * @see org.hibernate.Query#iterate
	 */
	public Iterator<?> iterate(String hql, Object... values) throws Exception;

	/**
	 * Immediately close an {@link Iterator} created by any of the various
	 * {@code iterate(..)} operations, instead of waiting until the session is
	 * closed or disconnected.
	 * 
	 * @param it the {@code Iterator} to close
	 * @throws Exception if the {@code Iterator} could not be closed
	 * @see org.hibernate.Hibernate#close
	 */
	public void closeIterator(Iterator<?> it) throws Exception;

	/**
	 * Update/delete all objects according to the given query.
	 * 
	 * @param queryString an update/delete query expressed in Hibernate's query
	 *                    language
	 * @return the number of instances updated/deleted
	 * @throws org.springframework.dao.Exception in case of Hibernate errors
	 * @see Session#createQuery
	 * @see org.hibernate.Query#executeUpdate
	 */
	public int bulkUpdate(String queryString) throws Exception;

	public int bulkSqlUpdate(String queryString) throws Exception;

	/**
	 * Update/delete all objects according to the given query, binding one value to
	 * a "?" parameter in the query string.
	 * 
	 * @param queryString an update/delete query expressed in Hibernate's query
	 *                    language
	 * @param value       the value of the parameter
	 * @return the number of instances updated/deleted
	 * @throws org.springframework.dao.Exception in case of Hibernate errors
	 * @see Session#createQuery
	 * @see org.hibernate.Query#executeUpdate
	 */
	public int bulkUpdate(String queryString, Object value) throws Exception;

	public int bulkSqlUpdate(String queryString, Object value) throws Exception;

	/**
	 * Update/delete all objects according to the given query, binding a number of
	 * values to "?" parameters in the query string.
	 * 
	 * @param queryString an update/delete query expressed in Hibernate's query
	 *                    language
	 * @param values      the values of the parameters
	 * @return the number of instances updated/deleted
	 * @throws org.springframework.dao.Exception in case of Hibernate errors
	 * @see Session#createQuery
	 * @see org.hibernate.Query#executeUpdate
	 */
	public int bulkUpdate(String queryString, Object... values) throws Exception;

	public int bulkSqlUpdate(String queryString, Object... values) throws Exception;

	/*
	 * 分页查询
	 */
	public <T> List<T> findByPage(final String hql, final int pageNo, final int maxResults) throws Exception;

	public <T> List<T> findByPage(final String hql, final Object param, final int pageNo, final int maxResults)
			throws Exception;

	public <T> List<T> findByPage(final String hql, final Object[] params, final int pageNo, final int maxResults)
			throws Exception;

	/**
	 * 使用 :param 方式绑定
	 * 
	 * @param hql
	 * @param params
	 * @param pageNo
	 * @param maxResults
	 * @return
	 * @throws Exception
	 */
	public <T> List<T> findByPage(final String hql, final Map<String, Object> params, final int pageNo,
			final int maxResults) throws Exception;

	public List<Map<String, Object>> findByPageMap(final String hql, final int pageNo, final int maxResults)
			throws Exception;

	public List<Map<String, Object>> findByPageMap(final String hql, final Object param, final int pageNo,
			final int maxResults) throws Exception;

	public List<Map<String, Object>> findByPageMap(final String hql, final Object[] params, final int pageNo,
			final int maxResults) throws Exception;

	public List<Map<String, Object>> findByPageMap(final String hql, final Map<String, Object> params, final int pageNo,
			final int maxResults) throws Exception;

	// 单实体bean 查询方法，目前仅支持�?单类型查询（String Integer �?,有需要自行添�?
	public <T> List<T> queryBean(T t) throws Exception;

	// 单实体bean 查询方法，目前仅支持�?单类型查询（String Integer �?,有需要自行添�?
	public <T> List<T> queryBean(T t, int pageNo, int maxResults) throws Exception;

	/**
	 * 根据实体bean 形成hql以及对应的参数列�? Map<String,Object> key="hql"-->查询语句
	 * key="param"-->参数列表 格式为java.util.List<String>
	 **/
	public <T> Map<String, Object> beanToHql(T t);

	public int queryBeanCountByHql(final String hql, final Object[] params) throws Exception;

	public <T> int queryBeanCount(T t) throws Exception;

	// 原生sql语句查询
	/**
	 * 使用数据库方�?查询
	 * 
	 * @param sql
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> findBySQL(String sql, final Object[] params) throws Exception;
	/**
	 * 使用SQL查询，但会映射到实体
	 * @param <T>
	 * @param sql
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public <T> List<T> findBeanBySQL(String sql,Class<T> entity,final Object[] params) throws Exception;
	/**
	 * 使用SQL查询，但会映射到实体
	 * @param <T>
	 * @param sql
	 * @param entity
	 * @param pageNo
	 * @param maxResults
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public <T> List<T> findBeanBySQL(String sql,Class<T> entity,final int pageNo, 
			final int maxResults,final Object[] params) throws Exception;

	/**
	 * 使用数据库方�?查询
	 * 
	 * @param sql select 语句
	 */
	public List<Map<String, Object>> findBySQL(String sql) throws Exception;
	/**
	 * 使用SQL查询，但会映射到实体
	 * @param <T>
	 * @param sql
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public <T> List<T> findBeanBySQL(String sql,Class<T> entity) throws Exception;

	/**
	 * 使用数据库方�?查询
	 * 
	 * @param sql                select 语句
	 * @param colNameANDdataType sql输出列的名字和数据库类型
	 */
	public List<Map<String, Object>> findBySQL(String sql, Map<String, Type> colNameANDdataType) throws Exception;

	/**
	 * 使用数据库方�?查询
	 * 
	 * @param sql                select 语句
	 * @param params             查询条件的参�?
	 * @param colNameANDdataType sql输出列的名字和数据库类型
	 */
	public List<Map<String, Object>> findBySQL(String sql, final Object[] params, Map<String, Type> colNameANDdataType)
			throws Exception;

	// 原生sql语句查询 分页
	public List<Map<String, Object>> findBySQL(String sql, final int pageNo, final int maxResults) throws Exception;

	// 原生sql语句查询 分页
	public List<Map<String, Object>> findBySQL(String sql, Map<String, Type> colNameANDdataType, final int pageNo,
			final int maxResults) throws Exception;

	// 原生sql语句查询,参数 分页
	public List<Map<String, Object>> findBySQL(String sql, final Object[] params, Map<String, Type> colNameANDdataType,
			final int pageNo, final int maxResults) throws Exception;

}
