package net.ent.etrs.spotifrice.model.entities;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
@ToString(of = {"id"})
public abstract class AbstractEntity implements Serializable {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Getter
//    @Column(name = "CREATED_AT")
//    public LocalDate createdAt;
//
//    @Getter
//    @Column(name = "UPDATED_AT")
//    public LocalDate updatedAt;
//
//    @PrePersist
//    protected void prePersist() {
//        this.createdAt = LocalDate.now();
//        this.updatedAt = this.createdAt;
//    }
//
//    @PreUpdate
//    protected void preUpdate() {
//        this.updatedAt = LocalDate.now();
//    }
}
