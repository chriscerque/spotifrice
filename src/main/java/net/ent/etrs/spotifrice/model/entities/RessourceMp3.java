package net.ent.etrs.spotifrice.model.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import net.ent.etrs.spotifrice.model.references.Genre;

import javax.persistence.*;

//JPA
@Entity
@Table(name = "RESSOURCE_MP3")
//LBK
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

@NamedQueries(
        value = {@NamedQuery(name = "ressourceMp3.readAll", query = "SELECT r FROM RessourceMp3 r"),
                @NamedQuery(name = "ressourceMp3.estDejaReference", query = "SELECT r FROM RessourceMp3 r WHERE r.cheminComplet= :chemin")
        })
public class RessourceMp3 extends AbstractEntity {
    @Getter
    @Setter
    @Column(name = "TITRE")
    String titre;

    @Getter
    @Setter
    @Column(name = "CHEMIN_COMPLET")
    String cheminComplet;

    @Getter
    @Setter
    @Column(name = "TAILLE")
    Long taille;

    @Enumerated(EnumType.STRING)
    @Column(name = "GENRE")
    @Getter
    @Setter
    Genre sonGenre;

    //Coté Maitre
    @ManyToOne(cascade = CascadeType.PERSIST)
    @Getter
    @Setter
    Album sonAlbum;
}
