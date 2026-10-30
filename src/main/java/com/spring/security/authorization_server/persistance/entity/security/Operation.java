package com.spring.security.authorization_server.persistance.entity.security;

import jakarta.persistence.*;

@Entity
@Table(name = "operation")
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // Ej: READ_ALL_PRODUCTS
    private String path; // Ej. /{productId}/disabled  el path base estaría en el modulo y sera /products
    private String http_method;
    private boolean permit_all;

//    @ManyToOne
//    // muchas operaciones estarán relacionadas a un mismo modulo
//    @JoinColumn(name = "module_id")
//    private Module module;
    @Column(name = "module_id")
    private long moduleId;

    public long getModuleId() {
        return moduleId;
    }

    public void setModuleId(long moduleId) {
        this.moduleId = moduleId;
    }

    //=== setter and setters
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
    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }

    public boolean isPermit_all() {
        return  this.permit_all;
    }

    public void setPermit_all(boolean permit_all) {
        this.permit_all = permit_all;
    }


    public String getHttp_method() {
        return http_method;
    }
    public void setHttp_method(String http_method) {
        this.http_method = http_method;
    }

}
