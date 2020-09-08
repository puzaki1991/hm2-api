package com.hm2.system.repositories.loginlog;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import com.hm2.common.beans.ResponseData;
import com.hm2.system.entities.LoginLog;
import com.hm2.ums.critrria.UserCriteria;


public class LoginLogRepoImpl implements LoginLogRepoCustom{
	
	@PersistenceContext
	EntityManager em;

	@Override
	public ResponseData<List<LoginLog>> searchLoginLog(UserCriteria criteria) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<LoginLog> cq = cb.createQuery(LoginLog.class);

		Root<LoginLog> root = cq.from(LoginLog.class);

		// ==> Where criteria
		List<Predicate> paramsPredicate = new ArrayList<>();
		if (!StringUtils.isEmpty(criteria.getUsername())) {
			paramsPredicate.add(cb.equal(root.get("username"), criteria.getUsername()));
		}
		
		cq.where(paramsPredicate.toArray(new Predicate[paramsPredicate.size()]));

		// ==> Order By
		List<Order> orderList = new ArrayList<>();
		orderList.add(cb.desc(root.get("id")));
		cq.orderBy(orderList);

		// ==> Data
		List<LoginLog> data = em.createQuery(cq).setFirstResult((criteria.getPage() - 1) * criteria.getPageSize()) // offset
				.setMaxResults(criteria.getPageSize()).getResultList(); // limit

		// ==> Count
		CriteriaQuery<Long> cqCount = cb.createQuery(Long.class);
		Root<LoginLog> rootCount = cqCount.from(LoginLog.class);
		cqCount.where(paramsPredicate.toArray(new Predicate[0]));
		cqCount.select(cb.count(rootCount));
		Long count = em.createQuery(cqCount).getSingleResult();

		// ==> Set Response
		ResponseData<List<LoginLog>> responseData = new ResponseData<List<LoginLog>>();
		responseData.setData(data);
		responseData.setRows(count);
		return responseData;
	}
	
}
