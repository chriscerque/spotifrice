package net.ent.etrs.spotifrice.model.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

//JPA
@Entity
@Table(name = "ALBUM")
//@NamedQueries(value = {
//        @NamedQuery(name = "album.readAll", query = "SELECT a FROM Album a"),
//        @NamedQuery(name = "album.searchByNom", query = "SELECT a FROM Album a WHERE a.nom=:nom "),
//        @NamedQuery(name = "album.compterAlbums", query = "SELECT COUNT(a) FROM Album a WHERE a.nom =:nom AND a.artiste=:artiste")
//
//})

//LBK
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Album extends AbstractEntity {

    @Getter
    @Setter
    @Column(name = "NOM")
    String nom;

    @Getter
    @Setter
    @Column(name = "ARTISTE")
    String artiste;

    @OneToMany(mappedBy = "sonAlbum")
    Collection<RessourceMp3> lstRessources = new ArrayList<>();

    public Collection<RessourceMp3> getLstRessources() {
        return Collections.unmodifiableCollection(lstRessources);
    }

    public void ajouterRessourceMp3(RessourceMp3 mp3) {
        this.lstRessources.add(mp3);
    }

    @Override
    public String toString() {
        return this.nom;
    }


}
