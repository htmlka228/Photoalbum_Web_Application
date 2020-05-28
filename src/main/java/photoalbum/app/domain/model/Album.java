package photoalbum.app.domain.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Album implements Serializable {

	private static final long serialVersionUID = -6935037869317618871L;
	
	private Long id;
	private Long profileId;
	private String albumName;
	private Long numberOfPhotos;
	private Set<AccesLevel> accesLevel;
	
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getProfileId() {
		return profileId;
	}
	
	public void setProfileId(Long profileId) {
		this.profileId = profileId;
	}
	
	public String getAlbumName() {
		return albumName;
	}
	
	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}
	
	public Long getNumberOfPhotos() {
		return numberOfPhotos;
	}
	
	public void setNumberOfPhotos(Long numberOfPhotos) {
		this.numberOfPhotos = numberOfPhotos;
	}
	
	public Set<AccesLevel> getAccesLevel() {
		if (accesLevel == null) {
			accesLevel = new HashSet<>();
		}
		return accesLevel;
	}

	@Override
	public String toString() {
		return "Album [id=" + id + ", profileId=" + profileId + ", albumName=" + albumName + ", numberOfPhotos="
				+ numberOfPhotos + ", accesLevel=" + accesLevel + "]";
	}
	
	public String getHighLevelRole() {

	    List<String> allRoles = new ArrayList<>();

	    for (AccesLevel accesLevel : this.getAccesLevel()) {
            allRoles.add(accesLevel.toString());
        }

	    if (allRoles.contains(AccesLevel.CLOSE.toString())) {
	        return AccesLevel.CLOSE.toString();
	    } else if(allRoles.contains(AccesLevel.FRIENDS.toString())) {
	        return AccesLevel.FRIENDS.toString();
	    } else {
	        return AccesLevel.PUBLIC.toString();
	    }

	}
	

}