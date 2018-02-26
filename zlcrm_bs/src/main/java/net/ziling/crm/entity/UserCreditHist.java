package net.ziling.crm.entity;

public class UserCreditHist {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_credit_hist.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_credit_hist.user_id
     *
     * @mbggenerated
     */
    private String userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_credit_hist.credit_rate
     *
     * @mbggenerated
     */
    private String creditRate;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_credit_hist
     *
     * @mbggenerated
     */
    public UserCreditHist(Integer id, String userId, String creditRate) {
        this.id = id;
        this.userId = userId;
        this.creditRate = creditRate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_credit_hist
     *
     * @mbggenerated
     */
    public UserCreditHist() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_credit_hist.id
     *
     * @return the value of user_credit_hist.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_credit_hist.id
     *
     * @param id the value for user_credit_hist.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_credit_hist.user_id
     *
     * @return the value of user_credit_hist.user_id
     *
     * @mbggenerated
     */
    public String getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_credit_hist.user_id
     *
     * @param userId the value for user_credit_hist.user_id
     *
     * @mbggenerated
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_credit_hist.credit_rate
     *
     * @return the value of user_credit_hist.credit_rate
     *
     * @mbggenerated
     */
    public String getCreditRate() {
        return creditRate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_credit_hist.credit_rate
     *
     * @param creditRate the value for user_credit_hist.credit_rate
     *
     * @mbggenerated
     */
    public void setCreditRate(String creditRate) {
        this.creditRate = creditRate == null ? null : creditRate.trim();
    }
}