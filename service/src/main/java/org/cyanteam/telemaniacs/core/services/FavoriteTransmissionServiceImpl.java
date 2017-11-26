package org.cyanteam.telemaniacs.core.services;

import org.cyanteam.telemaniacs.core.entities.Transmission;
import org.cyanteam.telemaniacs.core.entities.User;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Simona Tinkova
 */
public class FavoriteTransmissionServiceImpl implements FavoriteTransmissionsService {
	@Override
	public void followTransmission(Transmission transmission, User user) {
		List<Transmission> favoriteTransmissions = user.getFavouriteTransmissions();

		if (!favoriteTransmissions.contains(transmission)) {
			favoriteTransmissions.add(transmission);
		}

		user.setFavouriteTransmissions(favoriteTransmissions);
	}

	@Override
	public void unfollowTransmission(Transmission transmission, User user) {
		List<Transmission> favoriteTransmissions = user.getFavouriteTransmissions();

		if (favoriteTransmissions.contains(transmission)) {
			favoriteTransmissions.remove(transmission);
		}

		user.setFavouriteTransmissions(favoriteTransmissions);
	}

	@Override
	public List<Transmission> getFavoriteTransmissionsByUser(User user) {
		return user.getFavouriteTransmissions();
	}

	@Override
	public List<Transmission> getUpcomingFavoriteTransmissionsByUser(User user, Duration maxTimeSpan) {
		return user.getFavouriteTransmissions().stream()
				.filter(p -> p.getOccurrences().stream()
						.anyMatch(o ->
								Duration.between(o.getStartDate(), LocalDateTime.now()).compareTo(maxTimeSpan) > 0))
				.collect(Collectors.toList());

	}
}
