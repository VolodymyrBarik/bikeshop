package org.bikeshop.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;

@Entity
@Getter
@Setter
@SQLDelete(sql = "UPDATE brands SET is_deleted=true WHERE id=?")
@FilterDef(name = "notDeleted", parameters = @ParamDef(name = "isDeleted", type = Boolean.class))
@Filter(name = "notDeleted", condition = "is_deleted = :isDeleted")
@Table(name = "brands")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;
    private String description;
    private String logo;
    @Column(nullable = false)
    private boolean isDeleted = false;
    @Column(nullable = false)
    private boolean isEnabled = false;
}
