package net.wilsn.tournament;

import io.quarkus.hibernate.orm.rest.data.panache.PanacheEntityResource;

import java.util.UUID;

public interface TournamentsResource extends PanacheEntityResource<Tournament, Long> {
}
