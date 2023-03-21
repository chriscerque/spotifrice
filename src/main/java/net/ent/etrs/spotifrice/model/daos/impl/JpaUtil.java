package net.ent.etrs.spotifrice.model.daos.impl;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JpaUtil {
    private static EntityManager em;

    static {
        JpaUtil.em = Persistence.createEntityManagerFactory("pu-scoreur").createEntityManager(); // pu name
    }

    public static EntityManager getEm() {
        return JpaUtil.em;
    }
}
