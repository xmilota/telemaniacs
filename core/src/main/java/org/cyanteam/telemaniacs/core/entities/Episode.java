package org.cyanteam.telemaniacs.core.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

/**
 * Entity representing an episode of the transmission
 * @author Michael Le
 */
@Entity
@Table(name = "Episodes")
public class Episode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    @ManyToOne(optional = false)
    private Channel channel;

    @NotNull
    @Column(nullable = false)
    @ManyToOne(optional = false)
    private Transmission transmission;

    private String name;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime startDate;

    private boolean isRerun;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OffsetDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(OffsetDateTime startDate) {
        this.startDate = startDate;
    }

    public boolean isRerun() {
        return isRerun;
    }

    public void setRerun(boolean rerun) {
        isRerun = rerun;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || !(other instanceof Episode)) return false;

        final Episode episode = (Episode) other;

        if ((transmission == null && episode.getTransmission() != null)
                || !transmission.equals(episode.getTransmission()))
            return false;
        if ((name == null && episode.getName() != null)
                || !name.equals(episode.getName()))
            return false;
        if ((startDate == null && episode.getStartDate() != null)
                || !startDate.equals(episode.getStartDate()))
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (transmission != null ? transmission.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "EpisodeDaoImpl {" +
                "id=" + id + ", " +
                "transmission=" + transmission + ", " +
                "name='" + name + '\'' + ", " +
                "startDate=" + startDate + ", " +
                "isRerun=" + isRerun + ", " +
                '}';
    }
}
