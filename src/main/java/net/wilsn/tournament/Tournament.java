package net.wilsn.tournament;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.*;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tournament extends PanacheEntity {
    @NonNull
    @Getter
    private String name;
    @NonNull
    @Getter
    private LocalDate startDate;
}
