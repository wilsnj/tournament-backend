package net.wilsn.tournament;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Hole extends PanacheEntity implements Comparable<Hole>{
    @Getter
    private int number;
    @Getter
    private int par;
    @Getter
    private int handicap;

    @Override
    public int compareTo(Hole o) {
        return Integer.compare(number, o.number);
    }
}
