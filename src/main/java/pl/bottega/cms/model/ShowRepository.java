package pl.bottega.cms.model;

public interface ShowRepository extends Repository<Show> {


    boolean checkIfExists(Long showId);

}
