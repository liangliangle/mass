package com.mass.dto.query;

/**
 * @Auther :huiqiang
 * @Description :
 * @Date: Create in 下午12:57 2018/5/25 2018
 * @Modify:
 */
public class ExportRegister {

    private Long id;
    private String name;
    private Integer relation;
    private ExportRegister chindle;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRelation() {
        return relation;
    }

    public void setRelation(Integer relation) {
        this.relation = relation;
    }

    public ExportRegister getChindle() {
        return chindle;
    }

    public void setChindle(ExportRegister chindle) {
        this.chindle = chindle;
    }
}
