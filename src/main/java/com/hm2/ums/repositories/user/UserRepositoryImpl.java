package com.hm2.ums.repositories.user;

import com.hm2.common.beans.ResponseData;
import com.hm2.common.persistencecustom.persistence.service.BaseService;
import com.hm2.ums.critrria.UserCriteria;
import com.hm2.ums.entities.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepositoryCustom {

    @PersistenceContext
    EntityManager em;

    @Autowired
    private BaseService baseService;

    @Override
    public ResponseData<List<User>> searchUser(UserCriteria criteria) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);

        Root<User> root = cq.from(User.class);

        // ==> Where criteria
        List<Predicate> paramsPredicate = new ArrayList<>();
        if (!StringUtils.isEmpty(criteria.getUsername())) {
            paramsPredicate.add(cb.like(root.get("username"), "%" + criteria.getUsername() + "%"));
        }

        cq.where(paramsPredicate.toArray(new Predicate[paramsPredicate.size()]));

        // ==> Order By
        List<Order> orderList = new ArrayList<>();
        orderList.add(cb.desc(root.get("id")));
        cq.orderBy(orderList);

        // ==> Data
        List<User> data = em.createQuery(cq).setFirstResult((criteria.getPage() - 1) * criteria.getPageSize()) // offset
                .setMaxResults(criteria.getPageSize()).getResultList(); // limit

        // ==> Count
        CriteriaQuery<Long> cqCount = cb.createQuery(Long.class);
        Root<User> rootCount = cqCount.from(User.class);
        cqCount.where(paramsPredicate.toArray(new Predicate[0]));
        cqCount.select(cb.count(rootCount));
        Long count = em.createQuery(cqCount).getSingleResult();

        // ==> Set Response
        ResponseData<List<User>> responseData = new ResponseData<List<User>>();
        responseData.setData(data);
        responseData.setRows(count);
        return responseData;

    }
//    @Override
//    public List<User> searchUser(UserCriteria criteria) {
//        StringBuilder sql = new StringBuilder();
//        List<Object> params = new ArrayList<>();
//        this.querySearchUser(sql, params, criteria);
//        String query = MysqlUtils.limitForDataTable(sql.toString(), criteria.getStart(), criteria.getLength());
//        return jdbcTemplate.query(query, params.toArray(), userMapper); // BeanPropertyRowMapper.newInstance(User.class)
//    	return null;
//    }
//
//    @Override
//    public Integer searchUserCount(UserCriteria criteria) {
//        StringBuilder sql = new StringBuilder();
//        List<Object> params = new ArrayList<>();
//        this.querySearchUser(sql, params, criteria);
//        return jdbcTemplate.queryForObject(MysqlUtils.countForDataTable(sql.toString()), params.toArray(), Integer.class);
//    }
//
//    private void querySearchUser(StringBuilder sql , List<Object> params, UserCriteria criteria) {
//        sql.append("select * from t_ums_user where 1=1 ");
//        if (!StringUtils.isEmpty(criteria.getUsername())){
//            sql.append("and username like ?");
//            params.add("%" + criteria.getUsername()+ "%");
//        }
//        if (!StringUtils.isEmpty(criteria.getEmail())){
//            sql.append("and email like ?");
//            params.add("%" + criteria.getEmail()+ "%");
//        }
//        if (!StringUtils.isEmpty(criteria.getIsActive())){
//            sql.append(" and is_active = ?");
//            params.add(criteria.getIsActive());
//        }
//    }
//
//    private static final RowMapper<User> userMapper = new RowMapper<User>() {
//		@Override
//		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
//            User user = new User();
//			user.setUsername(rs.getString("username"));
////			user.setEmail(rs.getString("email"));
//			user.setId(rs.getLong("user_id"));
////			user.setIsActive(rs.getString("is_active"));
//            user.setCreatedDate(rs.getTimestamp("created_date"));
//            user.setUpdatedDate(rs.getTimestamp("updated_date"));
//			return user;
//		}
//	};

}
