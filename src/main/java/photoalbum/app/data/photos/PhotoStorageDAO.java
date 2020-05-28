package photoalbum.app.data.photos;

import java.util.List;

import javax.sql.DataSource;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import photoalbum.app.data.PhotoStorage;
import photoalbum.app.data.relationships.RelationshipsRowMapper;
import photoalbum.app.domain.model.Photo;

public class PhotoStorageDAO implements PhotoStorage{
	
	private Logger logger = Logger.getLogger(this.getClass().getName());
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Photo getPhotoById(Long id) {
		StringBuilder sql = new StringBuilder("SELECT * FROM photos WHERE id = ? ORDER BY date");
		Photo photo = jdbcTemplate.queryForObject(sql.toString(), new Object[] {id}, new PhotoRowMapper());
		return photo;
	}
	
	@Override
	public List<Photo> getPhotosByUser(Long profile_id) {
		StringBuilder sql = new StringBuilder("SELECT * FROM photos WHERE profile_id = ? ORDER BY date");
		List<Photo> photos = (List<Photo>) jdbcTemplate.queryForObject(sql.toString(), new Object[] {profile_id}, new PhotoRowMapper());
		return photos;
	}

	@Override
	public List<Photo> getPhotosByAlbum(Long album_id) {
		StringBuilder sql = new StringBuilder("SELECT * FROM photos WHERE album_id = ? ORDER BY date");
		List<Photo> photos = (List<Photo>) jdbcTemplate.queryForObject(sql.toString(), new Object[] {album_id}, new PhotoRowMapper());
		return photos;
	}

	@Override
	public List<Photo> getPhotosByRating(float rating) {
		StringBuilder sql = new StringBuilder("SELECT * FROM photos WHERE rating = ? ORDER BY date");
		List<Photo> photos = (List<Photo>) jdbcTemplate.queryForObject(sql.toString(), new Object[] {rating}, new PhotoRowMapper());
		return photos;
	}

	@Override
	public void uploadPhoto(Long profile_id, Long album_id, String description, String link_photo) {
		String insertQuery = "INSERT INTO photos (profile_id, album_id, description, date, link_photo) VALUES (?, ?, ?, now(), ?)";
		Object[] data = new Object[] {profile_id, album_id, description, link_photo};
		int rowAffected = jdbcTemplate.update(insertQuery, data);
		
		if (rowAffected == 0) {
			logger.error("Error during insert record for Photos");
		}
		
	}

	@Override
	public void deletePhoto(Long id) {
		String updateQuery = "DELETE FROM photos WHERE id = ?";
		Object[] data = new Object[] {id};
		int rowAffected = jdbcTemplate.update(updateQuery, data);

		if (rowAffected == 0) {
			logger.error("Error during delete record for Photos");
		}
	}
}