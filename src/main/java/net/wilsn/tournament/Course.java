package net.wilsn.tournament;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.*;
import org.hibernate.annotations.SortNatural;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.SortedSet;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Course extends PanacheEntity {
    @NonNull
    @Getter
    private String name;
    @NonNull
    @SortNatural
    @Getter
    @OneToMany(cascade = {CascadeType.PERSIST})
    private SortedSet<Hole> holes;
}
