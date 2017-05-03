package com.zyght.riesgopsicosocial.session;

/**
 * Created by Arley Mauricio Duarte on 3/27/17.
 */

public class User {
    private String id;
    private String username;

    private String company_id;

    public String getCompanyId() {
        return company_id;
    }

    public void setCompanyId(String companyId) {
        this.company_id = companyId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
