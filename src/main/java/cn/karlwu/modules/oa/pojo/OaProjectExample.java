package cn.karlwu.modules.oa.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OaProjectExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OaProjectExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIsNull() {
            addCriterion("company_id is null");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIsNotNull() {
            addCriterion("company_id is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyIdEqualTo(String value) {
            addCriterion("company_id =", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotEqualTo(String value) {
            addCriterion("company_id <>", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdGreaterThan(String value) {
            addCriterion("company_id >", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdGreaterThanOrEqualTo(String value) {
            addCriterion("company_id >=", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdLessThan(String value) {
            addCriterion("company_id <", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdLessThanOrEqualTo(String value) {
            addCriterion("company_id <=", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdLike(String value) {
            addCriterion("company_id like", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotLike(String value) {
            addCriterion("company_id not like", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIn(List<String> values) {
            addCriterion("company_id in", values, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotIn(List<String> values) {
            addCriterion("company_id not in", values, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdBetween(String value1, String value2) {
            addCriterion("company_id between", value1, value2, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotBetween(String value1, String value2) {
            addCriterion("company_id not between", value1, value2, "companyId");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("type like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("type not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andEnjoyUseridsIsNull() {
            addCriterion("enjoy_userids is null");
            return (Criteria) this;
        }

        public Criteria andEnjoyUseridsIsNotNull() {
            addCriterion("enjoy_userids is not null");
            return (Criteria) this;
        }

        public Criteria andEnjoyUseridsEqualTo(String value) {
            addCriterion("enjoy_userids =", value, "enjoyUserids");
            return (Criteria) this;
        }

        public Criteria andEnjoyUseridsNotEqualTo(String value) {
            addCriterion("enjoy_userids <>", value, "enjoyUserids");
            return (Criteria) this;
        }

        public Criteria andEnjoyUseridsGreaterThan(String value) {
            addCriterion("enjoy_userids >", value, "enjoyUserids");
            return (Criteria) this;
        }

        public Criteria andEnjoyUseridsGreaterThanOrEqualTo(String value) {
            addCriterion("enjoy_userids >=", value, "enjoyUserids");
            return (Criteria) this;
        }

        public Criteria andEnjoyUseridsLessThan(String value) {
            addCriterion("enjoy_userids <", value, "enjoyUserids");
            return (Criteria) this;
        }

        public Criteria andEnjoyUseridsLessThanOrEqualTo(String value) {
            addCriterion("enjoy_userids <=", value, "enjoyUserids");
            return (Criteria) this;
        }

        public Criteria andEnjoyUseridsLike(String value) {
            addCriterion("enjoy_userids like", value, "enjoyUserids");
            return (Criteria) this;
        }

        public Criteria andEnjoyUseridsNotLike(String value) {
            addCriterion("enjoy_userids not like", value, "enjoyUserids");
            return (Criteria) this;
        }

        public Criteria andEnjoyUseridsIn(List<String> values) {
            addCriterion("enjoy_userids in", values, "enjoyUserids");
            return (Criteria) this;
        }

        public Criteria andEnjoyUseridsNotIn(List<String> values) {
            addCriterion("enjoy_userids not in", values, "enjoyUserids");
            return (Criteria) this;
        }

        public Criteria andEnjoyUseridsBetween(String value1, String value2) {
            addCriterion("enjoy_userids between", value1, value2, "enjoyUserids");
            return (Criteria) this;
        }

        public Criteria andEnjoyUseridsNotBetween(String value1, String value2) {
            addCriterion("enjoy_userids not between", value1, value2, "enjoyUserids");
            return (Criteria) this;
        }

        public Criteria andViewUseridsIsNull() {
            addCriterion("view_userids is null");
            return (Criteria) this;
        }

        public Criteria andViewUseridsIsNotNull() {
            addCriterion("view_userids is not null");
            return (Criteria) this;
        }

        public Criteria andViewUseridsEqualTo(String value) {
            addCriterion("view_userids =", value, "viewUserids");
            return (Criteria) this;
        }

        public Criteria andViewUseridsNotEqualTo(String value) {
            addCriterion("view_userids <>", value, "viewUserids");
            return (Criteria) this;
        }

        public Criteria andViewUseridsGreaterThan(String value) {
            addCriterion("view_userids >", value, "viewUserids");
            return (Criteria) this;
        }

        public Criteria andViewUseridsGreaterThanOrEqualTo(String value) {
            addCriterion("view_userids >=", value, "viewUserids");
            return (Criteria) this;
        }

        public Criteria andViewUseridsLessThan(String value) {
            addCriterion("view_userids <", value, "viewUserids");
            return (Criteria) this;
        }

        public Criteria andViewUseridsLessThanOrEqualTo(String value) {
            addCriterion("view_userids <=", value, "viewUserids");
            return (Criteria) this;
        }

        public Criteria andViewUseridsLike(String value) {
            addCriterion("view_userids like", value, "viewUserids");
            return (Criteria) this;
        }

        public Criteria andViewUseridsNotLike(String value) {
            addCriterion("view_userids not like", value, "viewUserids");
            return (Criteria) this;
        }

        public Criteria andViewUseridsIn(List<String> values) {
            addCriterion("view_userids in", values, "viewUserids");
            return (Criteria) this;
        }

        public Criteria andViewUseridsNotIn(List<String> values) {
            addCriterion("view_userids not in", values, "viewUserids");
            return (Criteria) this;
        }

        public Criteria andViewUseridsBetween(String value1, String value2) {
            addCriterion("view_userids between", value1, value2, "viewUserids");
            return (Criteria) this;
        }

        public Criteria andViewUseridsNotBetween(String value1, String value2) {
            addCriterion("view_userids not between", value1, value2, "viewUserids");
            return (Criteria) this;
        }

        public Criteria andStartByIsNull() {
            addCriterion("start_by is null");
            return (Criteria) this;
        }

        public Criteria andStartByIsNotNull() {
            addCriterion("start_by is not null");
            return (Criteria) this;
        }

        public Criteria andStartByEqualTo(String value) {
            addCriterion("start_by =", value, "startBy");
            return (Criteria) this;
        }

        public Criteria andStartByNotEqualTo(String value) {
            addCriterion("start_by <>", value, "startBy");
            return (Criteria) this;
        }

        public Criteria andStartByGreaterThan(String value) {
            addCriterion("start_by >", value, "startBy");
            return (Criteria) this;
        }

        public Criteria andStartByGreaterThanOrEqualTo(String value) {
            addCriterion("start_by >=", value, "startBy");
            return (Criteria) this;
        }

        public Criteria andStartByLessThan(String value) {
            addCriterion("start_by <", value, "startBy");
            return (Criteria) this;
        }

        public Criteria andStartByLessThanOrEqualTo(String value) {
            addCriterion("start_by <=", value, "startBy");
            return (Criteria) this;
        }

        public Criteria andStartByLike(String value) {
            addCriterion("start_by like", value, "startBy");
            return (Criteria) this;
        }

        public Criteria andStartByNotLike(String value) {
            addCriterion("start_by not like", value, "startBy");
            return (Criteria) this;
        }

        public Criteria andStartByIn(List<String> values) {
            addCriterion("start_by in", values, "startBy");
            return (Criteria) this;
        }

        public Criteria andStartByNotIn(List<String> values) {
            addCriterion("start_by not in", values, "startBy");
            return (Criteria) this;
        }

        public Criteria andStartByBetween(String value1, String value2) {
            addCriterion("start_by between", value1, value2, "startBy");
            return (Criteria) this;
        }

        public Criteria andStartByNotBetween(String value1, String value2) {
            addCriterion("start_by not between", value1, value2, "startBy");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNull() {
            addCriterion("start_time is null");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNotNull() {
            addCriterion("start_time is not null");
            return (Criteria) this;
        }

        public Criteria andStartTimeEqualTo(Date value) {
            addCriterion("start_time =", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotEqualTo(Date value) {
            addCriterion("start_time <>", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThan(Date value) {
            addCriterion("start_time >", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("start_time >=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThan(Date value) {
            addCriterion("start_time <", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThanOrEqualTo(Date value) {
            addCriterion("start_time <=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeIn(List<Date> values) {
            addCriterion("start_time in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotIn(List<Date> values) {
            addCriterion("start_time not in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeBetween(Date value1, Date value2) {
            addCriterion("start_time between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotBetween(Date value1, Date value2) {
            addCriterion("start_time not between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andEstimatedEndTimeIsNull() {
            addCriterion("estimated_end_time is null");
            return (Criteria) this;
        }

        public Criteria andEstimatedEndTimeIsNotNull() {
            addCriterion("estimated_end_time is not null");
            return (Criteria) this;
        }

        public Criteria andEstimatedEndTimeEqualTo(Date value) {
            addCriterion("estimated_end_time =", value, "estimatedEndTime");
            return (Criteria) this;
        }

        public Criteria andEstimatedEndTimeNotEqualTo(Date value) {
            addCriterion("estimated_end_time <>", value, "estimatedEndTime");
            return (Criteria) this;
        }

        public Criteria andEstimatedEndTimeGreaterThan(Date value) {
            addCriterion("estimated_end_time >", value, "estimatedEndTime");
            return (Criteria) this;
        }

        public Criteria andEstimatedEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("estimated_end_time >=", value, "estimatedEndTime");
            return (Criteria) this;
        }

        public Criteria andEstimatedEndTimeLessThan(Date value) {
            addCriterion("estimated_end_time <", value, "estimatedEndTime");
            return (Criteria) this;
        }

        public Criteria andEstimatedEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("estimated_end_time <=", value, "estimatedEndTime");
            return (Criteria) this;
        }

        public Criteria andEstimatedEndTimeIn(List<Date> values) {
            addCriterion("estimated_end_time in", values, "estimatedEndTime");
            return (Criteria) this;
        }

        public Criteria andEstimatedEndTimeNotIn(List<Date> values) {
            addCriterion("estimated_end_time not in", values, "estimatedEndTime");
            return (Criteria) this;
        }

        public Criteria andEstimatedEndTimeBetween(Date value1, Date value2) {
            addCriterion("estimated_end_time between", value1, value2, "estimatedEndTime");
            return (Criteria) this;
        }

        public Criteria andEstimatedEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("estimated_end_time not between", value1, value2, "estimatedEndTime");
            return (Criteria) this;
        }

        public Criteria andOverByIsNull() {
            addCriterion("over_by is null");
            return (Criteria) this;
        }

        public Criteria andOverByIsNotNull() {
            addCriterion("over_by is not null");
            return (Criteria) this;
        }

        public Criteria andOverByEqualTo(String value) {
            addCriterion("over_by =", value, "overBy");
            return (Criteria) this;
        }

        public Criteria andOverByNotEqualTo(String value) {
            addCriterion("over_by <>", value, "overBy");
            return (Criteria) this;
        }

        public Criteria andOverByGreaterThan(String value) {
            addCriterion("over_by >", value, "overBy");
            return (Criteria) this;
        }

        public Criteria andOverByGreaterThanOrEqualTo(String value) {
            addCriterion("over_by >=", value, "overBy");
            return (Criteria) this;
        }

        public Criteria andOverByLessThan(String value) {
            addCriterion("over_by <", value, "overBy");
            return (Criteria) this;
        }

        public Criteria andOverByLessThanOrEqualTo(String value) {
            addCriterion("over_by <=", value, "overBy");
            return (Criteria) this;
        }

        public Criteria andOverByLike(String value) {
            addCriterion("over_by like", value, "overBy");
            return (Criteria) this;
        }

        public Criteria andOverByNotLike(String value) {
            addCriterion("over_by not like", value, "overBy");
            return (Criteria) this;
        }

        public Criteria andOverByIn(List<String> values) {
            addCriterion("over_by in", values, "overBy");
            return (Criteria) this;
        }

        public Criteria andOverByNotIn(List<String> values) {
            addCriterion("over_by not in", values, "overBy");
            return (Criteria) this;
        }

        public Criteria andOverByBetween(String value1, String value2) {
            addCriterion("over_by between", value1, value2, "overBy");
            return (Criteria) this;
        }

        public Criteria andOverByNotBetween(String value1, String value2) {
            addCriterion("over_by not between", value1, value2, "overBy");
            return (Criteria) this;
        }

        public Criteria andOverTimeIsNull() {
            addCriterion("over_time is null");
            return (Criteria) this;
        }

        public Criteria andOverTimeIsNotNull() {
            addCriterion("over_time is not null");
            return (Criteria) this;
        }

        public Criteria andOverTimeEqualTo(Date value) {
            addCriterion("over_time =", value, "overTime");
            return (Criteria) this;
        }

        public Criteria andOverTimeNotEqualTo(Date value) {
            addCriterion("over_time <>", value, "overTime");
            return (Criteria) this;
        }

        public Criteria andOverTimeGreaterThan(Date value) {
            addCriterion("over_time >", value, "overTime");
            return (Criteria) this;
        }

        public Criteria andOverTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("over_time >=", value, "overTime");
            return (Criteria) this;
        }

        public Criteria andOverTimeLessThan(Date value) {
            addCriterion("over_time <", value, "overTime");
            return (Criteria) this;
        }

        public Criteria andOverTimeLessThanOrEqualTo(Date value) {
            addCriterion("over_time <=", value, "overTime");
            return (Criteria) this;
        }

        public Criteria andOverTimeIn(List<Date> values) {
            addCriterion("over_time in", values, "overTime");
            return (Criteria) this;
        }

        public Criteria andOverTimeNotIn(List<Date> values) {
            addCriterion("over_time not in", values, "overTime");
            return (Criteria) this;
        }

        public Criteria andOverTimeBetween(Date value1, Date value2) {
            addCriterion("over_time between", value1, value2, "overTime");
            return (Criteria) this;
        }

        public Criteria andOverTimeNotBetween(Date value1, Date value2) {
            addCriterion("over_time not between", value1, value2, "overTime");
            return (Criteria) this;
        }

        public Criteria andSpeedProcessIsNull() {
            addCriterion("speed_process is null");
            return (Criteria) this;
        }

        public Criteria andSpeedProcessIsNotNull() {
            addCriterion("speed_process is not null");
            return (Criteria) this;
        }

        public Criteria andSpeedProcessEqualTo(Double value) {
            addCriterion("speed_process =", value, "speedProcess");
            return (Criteria) this;
        }

        public Criteria andSpeedProcessNotEqualTo(Double value) {
            addCriterion("speed_process <>", value, "speedProcess");
            return (Criteria) this;
        }

        public Criteria andSpeedProcessGreaterThan(Double value) {
            addCriterion("speed_process >", value, "speedProcess");
            return (Criteria) this;
        }

        public Criteria andSpeedProcessGreaterThanOrEqualTo(Double value) {
            addCriterion("speed_process >=", value, "speedProcess");
            return (Criteria) this;
        }

        public Criteria andSpeedProcessLessThan(Double value) {
            addCriterion("speed_process <", value, "speedProcess");
            return (Criteria) this;
        }

        public Criteria andSpeedProcessLessThanOrEqualTo(Double value) {
            addCriterion("speed_process <=", value, "speedProcess");
            return (Criteria) this;
        }

        public Criteria andSpeedProcessIn(List<Double> values) {
            addCriterion("speed_process in", values, "speedProcess");
            return (Criteria) this;
        }

        public Criteria andSpeedProcessNotIn(List<Double> values) {
            addCriterion("speed_process not in", values, "speedProcess");
            return (Criteria) this;
        }

        public Criteria andSpeedProcessBetween(Double value1, Double value2) {
            addCriterion("speed_process between", value1, value2, "speedProcess");
            return (Criteria) this;
        }

        public Criteria andSpeedProcessNotBetween(Double value1, Double value2) {
            addCriterion("speed_process not between", value1, value2, "speedProcess");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("state is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("state is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(String value) {
            addCriterion("state =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(String value) {
            addCriterion("state <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(String value) {
            addCriterion("state >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(String value) {
            addCriterion("state >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(String value) {
            addCriterion("state <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(String value) {
            addCriterion("state <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLike(String value) {
            addCriterion("state like", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotLike(String value) {
            addCriterion("state not like", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<String> values) {
            addCriterion("state in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<String> values) {
            addCriterion("state not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(String value1, String value2) {
            addCriterion("state between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(String value1, String value2) {
            addCriterion("state not between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andDesIsNull() {
            addCriterion("des is null");
            return (Criteria) this;
        }

        public Criteria andDesIsNotNull() {
            addCriterion("des is not null");
            return (Criteria) this;
        }

        public Criteria andDesEqualTo(String value) {
            addCriterion("des =", value, "des");
            return (Criteria) this;
        }

        public Criteria andDesNotEqualTo(String value) {
            addCriterion("des <>", value, "des");
            return (Criteria) this;
        }

        public Criteria andDesGreaterThan(String value) {
            addCriterion("des >", value, "des");
            return (Criteria) this;
        }

        public Criteria andDesGreaterThanOrEqualTo(String value) {
            addCriterion("des >=", value, "des");
            return (Criteria) this;
        }

        public Criteria andDesLessThan(String value) {
            addCriterion("des <", value, "des");
            return (Criteria) this;
        }

        public Criteria andDesLessThanOrEqualTo(String value) {
            addCriterion("des <=", value, "des");
            return (Criteria) this;
        }

        public Criteria andDesLike(String value) {
            addCriterion("des like", value, "des");
            return (Criteria) this;
        }

        public Criteria andDesNotLike(String value) {
            addCriterion("des not like", value, "des");
            return (Criteria) this;
        }

        public Criteria andDesIn(List<String> values) {
            addCriterion("des in", values, "des");
            return (Criteria) this;
        }

        public Criteria andDesNotIn(List<String> values) {
            addCriterion("des not in", values, "des");
            return (Criteria) this;
        }

        public Criteria andDesBetween(String value1, String value2) {
            addCriterion("des between", value1, value2, "des");
            return (Criteria) this;
        }

        public Criteria andDesNotBetween(String value1, String value2) {
            addCriterion("des not between", value1, value2, "des");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNull() {
            addCriterion("create_by is null");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNotNull() {
            addCriterion("create_by is not null");
            return (Criteria) this;
        }

        public Criteria andCreateByEqualTo(String value) {
            addCriterion("create_by =", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotEqualTo(String value) {
            addCriterion("create_by <>", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThan(String value) {
            addCriterion("create_by >", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThanOrEqualTo(String value) {
            addCriterion("create_by >=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThan(String value) {
            addCriterion("create_by <", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThanOrEqualTo(String value) {
            addCriterion("create_by <=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLike(String value) {
            addCriterion("create_by like", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotLike(String value) {
            addCriterion("create_by not like", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByIn(List<String> values) {
            addCriterion("create_by in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotIn(List<String> values) {
            addCriterion("create_by not in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByBetween(String value1, String value2) {
            addCriterion("create_by between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotBetween(String value1, String value2) {
            addCriterion("create_by not between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNull() {
            addCriterion("create_date is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("create_date is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(Date value) {
            addCriterion("create_date =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(Date value) {
            addCriterion("create_date <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(Date value) {
            addCriterion("create_date >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("create_date >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(Date value) {
            addCriterion("create_date <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(Date value) {
            addCriterion("create_date <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<Date> values) {
            addCriterion("create_date in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<Date> values) {
            addCriterion("create_date not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(Date value1, Date value2) {
            addCriterion("create_date between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(Date value1, Date value2) {
            addCriterion("create_date not between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andUpdateByIsNull() {
            addCriterion("update_by is null");
            return (Criteria) this;
        }

        public Criteria andUpdateByIsNotNull() {
            addCriterion("update_by is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateByEqualTo(String value) {
            addCriterion("update_by =", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotEqualTo(String value) {
            addCriterion("update_by <>", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThan(String value) {
            addCriterion("update_by >", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThanOrEqualTo(String value) {
            addCriterion("update_by >=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThan(String value) {
            addCriterion("update_by <", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThanOrEqualTo(String value) {
            addCriterion("update_by <=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLike(String value) {
            addCriterion("update_by like", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotLike(String value) {
            addCriterion("update_by not like", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByIn(List<String> values) {
            addCriterion("update_by in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotIn(List<String> values) {
            addCriterion("update_by not in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByBetween(String value1, String value2) {
            addCriterion("update_by between", value1, value2, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotBetween(String value1, String value2) {
            addCriterion("update_by not between", value1, value2, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNull() {
            addCriterion("update_date is null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNotNull() {
            addCriterion("update_date is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateEqualTo(Date value) {
            addCriterion("update_date =", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotEqualTo(Date value) {
            addCriterion("update_date <>", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThan(Date value) {
            addCriterion("update_date >", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("update_date >=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThan(Date value) {
            addCriterion("update_date <", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThanOrEqualTo(Date value) {
            addCriterion("update_date <=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIn(List<Date> values) {
            addCriterion("update_date in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotIn(List<Date> values) {
            addCriterion("update_date not in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateBetween(Date value1, Date value2) {
            addCriterion("update_date between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotBetween(Date value1, Date value2) {
            addCriterion("update_date not between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andRemarksIsNull() {
            addCriterion("remarks is null");
            return (Criteria) this;
        }

        public Criteria andRemarksIsNotNull() {
            addCriterion("remarks is not null");
            return (Criteria) this;
        }

        public Criteria andRemarksEqualTo(String value) {
            addCriterion("remarks =", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotEqualTo(String value) {
            addCriterion("remarks <>", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksGreaterThan(String value) {
            addCriterion("remarks >", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("remarks >=", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLessThan(String value) {
            addCriterion("remarks <", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLessThanOrEqualTo(String value) {
            addCriterion("remarks <=", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLike(String value) {
            addCriterion("remarks like", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotLike(String value) {
            addCriterion("remarks not like", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksIn(List<String> values) {
            addCriterion("remarks in", values, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotIn(List<String> values) {
            addCriterion("remarks not in", values, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksBetween(String value1, String value2) {
            addCriterion("remarks between", value1, value2, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotBetween(String value1, String value2) {
            addCriterion("remarks not between", value1, value2, "remarks");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}