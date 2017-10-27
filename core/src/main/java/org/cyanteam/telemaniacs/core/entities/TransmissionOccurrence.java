package org.cyanteam.telemaniacs.core.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Entity representing an occurrence of the transmission.
 * (may alse be a series episode, part of a movie etc.)
 * @author Michael Le
 */
@Entity
@Table(name = "TransmissionOccurrences")
public class TransmissionOccurrence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Channel channel;

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

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
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

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
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
        if (!(other instanceof TransmissionOccurrence)) return false;

        final TransmissionOccurrence transmissionOccurrence = (TransmissionOccurrence) other;

        if (getChannel() != null
                ? !getChannel().equals(transmissionOccurrence.getChannel())
                : transmissionOccurrence.getChannel() != null)
            return false;

        if (getTransmission() != null
                ? !getTransmission().equals(transmissionOccurrence.getTransmission())
                : transmissionOccurrence.getTransmission() != null)
            return false;

        if (getName() != null
                ? !getName().equals(transmissionOccurrence.getName())
                : transmissionOccurrence.getName() != null)
            return false;

        if (getName() != null
                ? !getName().equals(transmissionOccurrence.getName())
                : transmissionOccurrence.getName() != null)
            return false;

        if (getStartDate() != null
                ? !getStartDate().equals(transmissionOccurrence.getStartDate())
                : transmissionOccurrence.getStartDate() != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (getTransmission() != null ? getTransmission().hashCode() : 0);
        result = 31 * result + (getChannel() != null ? getChannel().hashCode() : 0);
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getStartDate() != null ? getStartDate().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TransmissionOccurence {" +
                "id=" + getId() + ", " +
                "channel=" + getChannel() + ", " +
                "transmission=" + getTransmission() + ", " +
                "name='" + getName() + '\'' + ", " +
                "startDate=" + getStartDate() + ", " +
                "isRerun=" + isRerun() + ", " +
                '}';
    }
}
