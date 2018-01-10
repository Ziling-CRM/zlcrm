package net.ziling.crm.entity;

public class Domain {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column zc_domain.domain_id
     *
     * @mbggenerated
     */
    private String domainId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column zc_domain.domain
     *
     * @mbggenerated
     */
    private String domain;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column zc_domain.domain_op
     *
     * @mbggenerated
     */
    private String domainOp;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zc_domain
     *
     * @mbggenerated
     */
    public Domain(String domainId, String domain, String domainOp) {
        this.domainId = domainId;
        this.domain = domain;
        this.domainOp = domainOp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zc_domain
     *
     * @mbggenerated
     */
    public Domain() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column zc_domain.domain_id
     *
     * @return the value of zc_domain.domain_id
     *
     * @mbggenerated
     */
    public String getDomainId() {
        return domainId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column zc_domain.domain_id
     *
     * @param domainId the value for zc_domain.domain_id
     *
     * @mbggenerated
     */
    public void setDomainId(String domainId) {
        this.domainId = domainId == null ? null : domainId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column zc_domain.domain
     *
     * @return the value of zc_domain.domain
     *
     * @mbggenerated
     */
    public String getDomain() {
        return domain;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column zc_domain.domain
     *
     * @param domain the value for zc_domain.domain
     *
     * @mbggenerated
     */
    public void setDomain(String domain) {
        this.domain = domain == null ? null : domain.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column zc_domain.domain_op
     *
     * @return the value of zc_domain.domain_op
     *
     * @mbggenerated
     */
    public String getDomainOp() {
        return domainOp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column zc_domain.domain_op
     *
     * @param domainOp the value for zc_domain.domain_op
     *
     * @mbggenerated
     */
    public void setDomainOp(String domainOp) {
        this.domainOp = domainOp == null ? null : domainOp.trim();
    }
}