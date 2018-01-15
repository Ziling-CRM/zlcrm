package net.ziling.crm.dao;

import net.ziling.crm.entity.Project;

public interface ProjectMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zc_project
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String proId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zc_project
     *
     * @mbggenerated
     */
    int insert(Project record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zc_project
     *
     * @mbggenerated
     */
    int insertSelective(Project record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zc_project
     *
     * @mbggenerated
     */
    Project selectByPrimaryKey(String proId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zc_project
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Project record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zc_project
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Project record);

    /**
     * 通过projectId查找单个project
     * @param proId
     * @return
     */
    Project selectProjectByProId(String proId);
}